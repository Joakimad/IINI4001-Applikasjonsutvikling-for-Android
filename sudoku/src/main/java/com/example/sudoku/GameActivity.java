package com.example.sudoku;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
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
    int difficulty;

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
        difficulty = extras.getInt("difficulty");

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
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                if (fragment.isCorrectSolution()) {
                    builder.setMessage(R.string.check_solution_success);
                } else {
                    builder.setMessage(R.string.check_solution_failure);
                }
                builder.setNeutralButton(R.string.button_start_new_game, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(GameActivity.this, GameActivity.class);
                        intent.putExtra("difficulty", difficulty);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.return_to_menu, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(GameActivity.this, MenuActivity.class));
                        finish();
                    }
                });
                builder.show();
                break;
            case R.id.button_1:
                setActiveButton(1);
                break;
            case R.id.button_2:
                setActiveButton(2);
                break;
            case R.id.button_3:
                setActiveButton(3);
                break;
            case R.id.button_4:
                setActiveButton(4);
                break;
            case R.id.button_5:
                setActiveButton(5);
                break;
            case R.id.button_6:
                setActiveButton(6);
                break;
            case R.id.button_7:
                setActiveButton(7);
                break;
            case R.id.button_8:
                setActiveButton(8);
                break;
            case R.id.button_9:
                setActiveButton(9);
                break;
            case R.id.button_X:
                setActiveButton(10);
                break;
        }
    }

    private void setActiveButton(int id) {
        // this methods has some exceptions for the X button.
        String buttonID;
        for (int i = 1; i <= 10; i++) {
            if (i == 10) {
                buttonID = "button_X";
            } else {
                buttonID = "button_" + i;
            }
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            Button btn = findViewById(resID);
            btn.setBackgroundResource(R.drawable.rounded_corners);
        }

        if (id == 10) {
            id = -1;
            buttonID = "button_X";
        } else {
            buttonID = "button_" + id;
        }
        fragment.setActiveNumber(id);

        int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
        Button btn = findViewById(resID);
        btn.setBackgroundResource(R.drawable.rounded_corners_toggled);
    }
}