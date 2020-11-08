package com.example.sudoku;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.annotation.Nullable;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class SudokuView extends GridLayout {

    Context c = getContext();

    public SudokuView(Context context) {
        super(context);
        init(null);
    }

    public SudokuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SudokuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public SudokuView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {

        setRowCount(9);
        setColumnCount(9);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Button btn = new Button(c);
                btn.setText(i);

                LayoutParams params = new SudokuView.LayoutParams();
                params.height = WRAP_CONTENT;
                params.width = 0;


                btn.setLayoutParams(params);

                addView(btn);
            }
        }

    }
}
