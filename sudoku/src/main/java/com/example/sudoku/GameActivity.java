package com.example.sudoku;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.GridView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class GameActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        int x = -1;
        int[] board_values = {
                x, 7, x, x, 2, 9, 1, x, x, // 1
                x, x, 5, x, x, x, 9, 6, x, // 2
                2, x, x, 5, x, 1, x, x, x, // 3
                8, 2, x, 1, x, x, 7, x, 3, // 4
                9, 3, 6, x, x, x, 2, x, 8, // 5
                x, x, 7, 8, 3, x, x, 9, 6, // 6
                3, x, 1, x, x, 7, 6, x, 9, // 7
                7, x, 2, 9, x, x, 5, x, 1, // 8
                4, x, 9, x, x, 3, x, x, x  // 9
        };

        Bundle bundle = new Bundle();
        String msg = "test2";
        bundle.putString("message", msg);
        bundle.putIntArray("values", board_values);

        BoardFragment grid = new BoardFragment();

        grid.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, grid);
        transaction.commit();
    }
}