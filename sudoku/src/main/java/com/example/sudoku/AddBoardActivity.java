package com.example.sudoku;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddBoardActivity extends AppCompatActivity implements View.OnClickListener {

    BoardFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_board);

        initButtons();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        int x = -1;
        int[][] empty = {
                {x, x, x, x, x, x, x, x, x}, // Box 1
                {x, x, x, x, x, x, x, x, x}, // Box 2
                {x, x, x, x, x, x, x, x, x}, // Box 3
                {x, x, x, x, x, x, x, x, x}, // Box 4
                {x, x, x, x, x, x, x, x, x}, // Box 5
                {x, x, x, x, x, x, x, x, x}, // Box 6
                {x, x, x, x, x, x, x, x, x}, // Box 7
                {x, x, x, x, x, x, x, x, x}, // Box 8
                {x, x, x, x, x, x, x, x, x}, // Box 9
        };

        Bundle bundle = new Bundle();
        bundle.putSerializable("values", empty);

        fragment = new BoardFragment();
        fragment.setArguments(bundle);
        ft.add(R.id.fragment_container, fragment);
        ft.commit();
    }

    private void initButtons() {
        Button buttonAddBoardEasy = findViewById(R.id.button_addBoard_easy);
        Button buttonAddBoardMed = findViewById(R.id.button_add_board_medium);
        Button buttonAddBoardHard = findViewById(R.id.button_addBoard_hard);
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
        buttonAddBoardEasy.setOnClickListener(this);
        buttonAddBoardMed.setOnClickListener(this);
        buttonAddBoardHard.setOnClickListener(this);
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
            case R.id.button_addBoard_easy:
                saveToStorage(0);
                break;
            case R.id.button_add_board_medium:
                saveToStorage(1);
                break;
            case R.id.button_addBoard_hard:
                saveToStorage(2);
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


    private void saveToStorage(int difficulty) {

        Context context = this;
        int[][] board = fragment.getValues();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
        String now = formatter.format(c.getTime());

        String filename = difficulty + "|" + now + ".csv";

        File file = new File(context.getFilesDir(), filename);

        try {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    builder.append(board[i][j] + "");
                    if (j < board.length - 1)
                        builder.append(",");
                }
                builder.append("\n");
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(builder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}