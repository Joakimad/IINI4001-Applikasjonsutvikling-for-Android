package com.example.sudoku;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    BoardFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initButtons();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        int difficulty = extras.getInt("difficulty");

        int[][] board;
        switch (difficulty) {
            case 0:
                board = getRandomBoard(0);
                break;
            case 1:
                board = getRandomBoard(1);
                break;
            case 2:
                board = getRandomBoard(2);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + difficulty);
        }

        bundle.putSerializable("values", board);

        fragment = new BoardFragment();
        fragment.setArguments(bundle);
        ft.add(R.id.fragment_container, fragment);
        ft.commit();
    }

    private int[][] getRandomBoard(int difficulty) {

        ArrayList<String> availableBoards = new ArrayList<>();

        // Check files
        String[] files = GameActivity.this.fileList();
        for (String file : files) {
            if (file.startsWith(String.valueOf(difficulty))) {
                availableBoards.add(file);
            }
        }

        // Choose the only one available.
        if (availableBoards.size() == 1) {
            return readFile(availableBoards.get(0));
        }
        // Choose randomly from those available.
        Random random = new Random();
        String selectedBoard = availableBoards.get(random.nextInt(availableBoards.size()));
        return readFile(selectedBoard);
    }

    private int[][] readFile(String filename) {
        int[][] board = new int[9][9];
        try {
            File dir = getFilesDir();
            File file = new File(dir, filename);

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] cols = line.split(",");
                int col = 0;
                for (String c : cols) {
                    if (c.equals("x")) {
                        c = "-1";
                    }
                    board[row][col] = Integer.parseInt(c);
                    col++;
                }
                row++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("JOAKIM", "\n" + Arrays.deepToString(board).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        return board;
    }

    private void initButtons() {
        Button button_solve = findViewById(R.id.button_solve);
        Button button1 = findViewById(R.id.button_1);
        Button button2 = findViewById(R.id.button_2);
        Button button3 = findViewById(R.id.button_3);
        Button button4 = findViewById(R.id.button_4);
        Button button5 = findViewById(R.id.button_5);
        Button button6 = findViewById(R.id.button_6);
        Button button7 = findViewById(R.id.button_7);
        Button button8 = findViewById(R.id.button_8);
        Button button9 = findViewById(R.id.button_9);
        Button buttonX = findViewById(R.id.button_X);
        button_solve.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonX.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_solve:
                if (fragment.isCorrectSolution()) Log.d("JOAKIM", "No Errors");
                else Log.d("JOAKIM", "There are Errors");
                break;
            case R.id.button_1:
                fragment.setActiveNumber(1);
                break;
            case R.id.button_2:
                fragment.setActiveNumber(2);
                break;
            case R.id.button_3:
                fragment.setActiveNumber(3);
                break;
            case R.id.button_4:
                fragment.setActiveNumber(4);
                break;
            case R.id.button_5:
                fragment.setActiveNumber(5);
                break;
            case R.id.button_6:
                fragment.setActiveNumber(6);
                break;
            case R.id.button_7:
                fragment.setActiveNumber(7);
                break;
            case R.id.button_8:
                fragment.setActiveNumber(8);
                break;
            case R.id.button_9:
                fragment.setActiveNumber(9);
                break;
            case R.id.button_X:
                fragment.setActiveNumber(-1);
                break;
        }
    }
}