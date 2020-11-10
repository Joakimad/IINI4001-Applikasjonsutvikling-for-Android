package com.example.sudoku;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

public class GameActivity extends FragmentActivity implements View.OnClickListener {

    BoardFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Log.d("JOAKIM-GAME", Locale.getDefault().getDisplayName());

        initButtons();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        int x = -1;
        int[][] easy = {
                {7, 4, 6, 5, x, 9, x, x, 3}, // Box 1
                {2, 1, x, x, x, x, x, x, 5}, // Box 2
                {x, 3, 8, 1, x, 2, x, 7, x}, // Box 3
                {6, x, 8, 4, 7, 2, x, x, x}, // Box 4
                {1, x, 7, x, x, 8, 3, x, x}, // Box 5
                {x, x, 4, x, x, x, 6, x, x}, // Box 6
                {x, x, 7, 3, 9, 4, x, x, x}, // Box 7
                {x, x, 6, 8, x, x, 4, 7, 3}, // Box 8
                {3, x, 5, 7, x, x, x, x, 1}, // Box 9
        };

        int[][] medium = {
                {5, x, 9, 7, x, x, x, x, 6}, // Box 1
                {6, 8, x, x, 1, 3, x, x, x}, // Box 2
                {x, 4, x, 5, x, 8, 1, x, x}, // Box 3
                {x, x, x, x, 4, x, x, x, x}, // Box 4
                {x, x, 1, x, 2, x, x, 4, 5}, // Box 5
                {4, x, x, x, x, 1, x, x, x}, // Box 6
                {2, x, x, x, x, 5, x, x, x}, // Box 7
                {4, x, x, 1, x, 2, 5, 3, x}, // Box 8
                {6, 8, 5, x, x, x, x, x, 9}, // Box 9
        };

        int[][] hard = {
                {4, x, x, x, x, 5, x, x, x}, // Box 1
                {8, x, 5, x, x, x, x, x, x}, // Box 2
                {x, x, 9, 8, 6, x, x, x, x}, // Box 3
                {x, 7, x, 3, x, x, x, x, 6}, // Box 4
                {x, x, 9, x, 5, x, x, 7, x}, // Box 5
                {5, x, x, x, x, x, 1, x, x}, // Box 6
                {x, 3, x, 5, x, x, x, 6, x}, // Box 7
                {x, x, x, x, 3, 7, 9, x, x}, // Box 8
                {x, x, 4, 2, x, x, x, x, x}, // Box 9
        };

        Bundle bundle = new Bundle();
        Bundle extras = getIntent().getExtras();
        int difficulty = extras.getInt("difficulty");

        int[][] board;
        switch (difficulty) {
            case 0:
                board = readFile(R.raw.easy_default);
                break;
            case 1:
                board = readFile(R.raw.medium_default);
                break;
            case 2:
                board = readFile(R.raw.hard_default);
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


    private int[][] readFile(int id) {
        int[][] board = new int[9][9];
        try {
            InputStream is = getResources().openRawResource(id);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
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