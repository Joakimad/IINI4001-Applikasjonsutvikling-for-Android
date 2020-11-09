package com.example.sudoku;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class GameActivity extends FragmentActivity implements View.OnClickListener {

    public int active_number;
    BoardFragment grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initButtons();

        int x = -1;
        int[][] board_values = {
                {x, 7, x, x, x, 5, 2, x, x}, // Box 1
                {x, 2, 9, x, x, x, 5, x, 1}, // 2
                {1, x, x, 9, 6, x, x, x, x}, // 3
                {8, 2, x, 9, 3, 6, x, x, 7}, // 4
                {1, x, x, x, x, x, 8, 3, x}, // 5
                {7, x, 3, 2, x, 8, x, 9, 6}, // 6
                {3, x, 1, 7, x, 2, 4, x, 9}, // 7
                {x, x, 7, 9, x, x, x, x, 3}, // 8
                {6, x, 9, 5, x, 1, x, x, x}  // 9
        };

        Bundle bundle = new Bundle();
        String msg = "test2";
        bundle.putString("message", msg);
        bundle.putSerializable("values", board_values);
        grid = new BoardFragment();

        grid.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, grid);
        transaction.commit();
    }

    private void initButtons() {
        Button b1 = findViewById(R.id.button_1);
        Button b2 = findViewById(R.id.button_2);
        Button b3 = findViewById(R.id.button_3);
        Button b4 = findViewById(R.id.button_4);
        Button b5 = findViewById(R.id.button_5);
        Button b6 = findViewById(R.id.button_6);
        Button b7 = findViewById(R.id.button_7);
        Button b8 = findViewById(R.id.button_8);
        Button b9 = findViewById(R.id.button_9);
        Button bX = findViewById(R.id.button_X);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        bX.setOnClickListener(this);
    }

    //implement the onClick method here
    public void onClick(View v) {
        // Perform action on click
        switch (v.getId()) {
            case R.id.button_1:
                grid.setActiveNumber(1);
                break;
            case R.id.button_2:
                grid.setActiveNumber(2);
                break;
            case R.id.button_3:
                grid.setActiveNumber(3);
                break;
            case R.id.button_4:
                grid.setActiveNumber(4);
                break;
            case R.id.button_5:
                grid.setActiveNumber(5);
                break;
            case R.id.button_6:
                grid.setActiveNumber(6);
                break;
            case R.id.button_7:
                grid.setActiveNumber(7);
                break;
            case R.id.button_8:
                grid.setActiveNumber(8);
                break;
            case R.id.button_9:
                grid.setActiveNumber(9);
                break;
            case R.id.button_X:
                grid.setActiveNumber(-1);
                break;
        }
    }

    public int getActive_number() {
        return active_number;
    }
}