package com.example.sudoku;

import android.content.Context;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.media.RatingCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.annotation.ColorInt;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class BoardFragment extends Fragment {

    public int activeNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int[][] values = (int[][]) this.getArguments().getSerializable("values");

        Context c = getActivity();
        GridLayout grid = new GridLayout(c);
        grid.setRowCount(3);
        grid.setColumnCount(3);
        //grid.setBackgroundResource(R.color.colorAccent);

        for (int i = 0; i < 9; i++) {
            GridLayout box = new GridLayout(c);
            box.setRowCount(3);
            box.setColumnCount(3);

            GridLayout.LayoutParams box_params = new GridLayout.LayoutParams();

            box_params.width = 0;
            box_params.height = 0;
            box_params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f);
            box_params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f);

            int marginInDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
            box_params.setMargins(marginInDp, marginInDp, marginInDp, marginInDp);
            box.setLayoutParams(box_params);

            for (int j = 0; j < 9; j++) {
                final Button btn = new Button(c);
                //Button btn = new Button(new ContextThemeWrapper(c, R.style.Cell),null,R.style.ButtonStyle);
                btn.setBackgroundResource(R.drawable.grid_cell);
                btn.setTextSize(21);

                final boolean fixedValue = values[i][j] != -1;
                if (fixedValue) {
                    Typeface tf = Typeface.SANS_SERIF;
                    btn.setTypeface(tf);
                    btn.setText(String.format(Locale.ENGLISH, "%d", values[i][j]));
                }

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 0;
                params.height = 0;
                params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f);
                params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f);
                btn.setLayoutParams(params);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (fixedValue) {
                            return;
                        } else if (activeNumber == -1) {
                            btn.setText("");
                            return;
                        }
                        btn.setTextColor(getResources().getColor(R.color.colorAccent));
                        btn.setText(String.valueOf(activeNumber));
                    }
                });

                box.addView(btn);
            }
            grid.addView(box);
        }


        // SAFE

//            GridLayout grid = new GridLayout(c);
//            grid.setRowCount(9);
//            grid.setColumnCount(9);
//
//            for (int i = 0; i < 9 * 9; i++) {
//                Button btn = new Button(c);
//                //Button btn = new Button(new ContextThemeWrapper(c, R.style.Cell),null,R.style.ButtonStyle);
//                btn.setBackgroundResource(R.drawable.grid_cell);
//                btn.setTextSize(21);
//                Typeface tf = Typeface.SANS_SERIF;
//                btn.setTypeface(tf);
//
//                /**
//                 *         <item name="android:textColor">@color/colorSecondary</item>
//                 *         <item name="android:textSize">21sp</item>
//                 *         <item name="android:fontFamily">sans-serif-condensed-light</item>
//                 *         <item name="android:layout_gravity">fill</item>
//                 */
//
//                boolean fixedValue = values[i] != -1;
//                if (fixedValue) {
//                    btn.setText(String.format(Locale.ENGLISH, "%d",values[i]));
//                }
//
//                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
//
//                params.width = 0;
//                params.height = GridLayout.LayoutParams.WRAP_CONTENT;
//                params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f);
//                btn.setLayoutParams(params);
//
//                grid.addView(btn)

        //return inflater.inflate(R.layout.fragment_board, container, false);
        return grid;
    }

    public void setActiveNumber(int activeNumber) {
        this.activeNumber = activeNumber;
    }
}



