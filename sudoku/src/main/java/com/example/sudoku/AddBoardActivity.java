package com.example.sudoku;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddBoardActivity extends AppCompatActivity implements View.OnClickListener {

    BoardFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_board);

        Log.d("JOAKIM-ADD", Locale.getDefault().getDisplayName());

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
                saveToStorage(fragment.getValues(), 0);
                break;
            case R.id.button_add_board_medium:
                saveToStorage(fragment.getValues(), 1);
                break;
            case R.id.button_addBoard_hard:
                saveToStorage(fragment.getValues(), 2);
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

    private void saveToStorage(int[][] board, int difficulty) {
        Context context = this;

        Calendar c = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
        String now = formatter.format(c.getTime());

        String filename = difficulty + "|" + now;
        Log.d("JOAKIM", "Adding file: " + filename);
        File file = new File(context.getFilesDir(), filename);

        String[] files = context.fileList();
        for (int i = 0; i < files.length; i++) {
            Log.d("JOAKIM", "Files:" + files[i]);
        }

//        StringBuilder builder = new StringBuilder();
//        for(int i = 0; i < board.length; i++)//for each row
//        {
//            for(int j = 0; j < board.length; j++)//for each column
//            {
//                builder.append(board[i][j]+"");//append to the output string
//                if(j < board.length - 1)//if this is not the last row element
//                    builder.append(",");//then add comma (if you don't like commas you can use spaces)
//            }
//            builder.append("\n");//append new line at the end of the row
//        }
//        BufferedWriter writer = new BufferedWriter(new FileWriter("/c:/sudoku" + date + ".txt"));
//        writer.write(builder.toString());//save the string representation of the board
//        writer.close();


    }
}