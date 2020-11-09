package com.example.sudoku;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.fragment.app.Fragment;

import java.util.Arrays;

public class BoardFragment extends Fragment {

    private int activeNumber = 1;
    int[][] values;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context c = getActivity();

        // Get the values to be drawn unto the board
        assert getArguments() != null;
        values = (int[][]) getArguments().getSerializable("values");

        // Defines the grid view that will house the smaller grids
        GridLayout grid = new GridLayout(c);
        grid.setColumnCount(3);
        grid.setRowCount(3);

        for (int i = 0; i < 9; i++) {
            // Defines the grid that will house the buttons
            GridLayout box = new GridLayout(c);
            box.setColumnCount(3);
            box.setRowCount(3);

            // Correctly distributes height and width
            GridLayout.LayoutParams box_param = new GridLayout.LayoutParams(GridLayout.spec(
                    GridLayout.UNDEFINED, GridLayout.FILL, 1f),
                    GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f));
            box_param.height = 0;
            box_param.width = 0;
            box.setLayoutParams(box_param);

            for (int j = 0; j < 9; j++) {
                Cell cell = new Cell(i, j, c);
                box.addView(cell.getBtn());
            }
            grid.addView(box);
        }

        return grid;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_board, container, false);
    }

    public boolean isCorrectSolution() {
        Log.d("JOAKIM", "Sending Values:");
        Log.d("JOAKIM", " \n" + Arrays.deepToString(values).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        boolean all_boxes_are_correct = true;
        boolean all_vertical_lines_are_correct = true;
        boolean all_horizontal_lines_are_correct = true;
        int[] idealValues = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        int[][] values_in_lines = new int[9][9];

        int[] line1 = new int[9];
        int[] line2 = new int[9];
        int[] line3 = new int[9];

        int counter = 0;
        int row_counter = 0;
        // Check if boxes are correct
        for (int i = 0; i < 9; i++) {
            int[] sorted = Arrays.copyOf(values[i], values[i].length);
            Arrays.sort(sorted);
            if (!Arrays.equals(sorted, idealValues)) all_boxes_are_correct = false;

//            h_line1.add(values[i][0]);
//            h_line1.add(values[i][1]);
//            h_line1.add(values[i][2]);
//            h_line2.add(values[i][3]);
//            h_line2.add(values[i][4]);
//            h_line2.add(values[i][5]);
//            h_line3.add(values[i][6]);
//            h_line3.add(values[i][7]);
//            h_line3.add(values[i][8]);

            Log.d("JOAKIM", i + "counter: " + counter);

            line1[counter] = values[i][0];
            line2[counter] = values[i][3];
            line3[counter++] = values[i][6];
            line1[counter] = values[i][1];
            line2[counter] = values[i][4];
            line3[counter++] = values[i][7];
            line1[counter] = values[i][2];
            line2[counter] = values[i][5];
            line3[counter++] = values[i][8];
            Log.d("JOAKIM", i + " line1: " + Arrays.toString(line1));
            Log.d("JOAKIM", i + " line2: " + Arrays.toString(line2));
            Log.d("JOAKIM", i + " line2: " + Arrays.toString(line3));
            // 3 6

            if ((i + 1) % 3 == 0) {
                Log.d("JOAKIM", "---\nH-Line 1 " + Arrays.toString(line1));
                Log.d("JOAKIM", "H-Line 2 " + Arrays.toString(line2));
                Log.d("JOAKIM", "H-Line 3 " + Arrays.toString(line3));

                // Reset counter
                counter = 0;

                // Archive rows for so that we can check vertically.
                for (int j = 0; j < 9; j++) {
                    values_in_lines[row_counter][j] = line1[j];
                    values_in_lines[row_counter + 1][j] = line2[j];
                    values_in_lines[row_counter + 2][j] = line3[j];
                }
                row_counter += 3;

                // Check horizontal lines.
                Arrays.sort(line1);
                Arrays.sort(line2);
                Arrays.sort(line3);
                if (!Arrays.equals(line1, idealValues) || !Arrays.equals(line2, idealValues) || !Arrays.equals(line3, idealValues))
                    all_horizontal_lines_are_correct = false;
            }
        }

        // Check vertical lines.
        int[] v_line;
        for (int i = 0; i < 9; i++) {
            v_line = getColumn(values_in_lines, i);
            Arrays.sort(v_line);
            if (!Arrays.equals(v_line, idealValues)) all_vertical_lines_are_correct = false;
        }

        Log.d("JOAKIM", "Lines:\n" + Arrays.deepToString(values_in_lines).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        Log.d("JOAKIM", "Boxes: " + all_boxes_are_correct);
        Log.d("JOAKIM", "Vertical: " + all_vertical_lines_are_correct);
        Log.d("JOAKIM", "Horizontal: " + all_horizontal_lines_are_correct);

        return all_boxes_are_correct && all_vertical_lines_are_correct && all_horizontal_lines_are_correct;
    }

    public static int[] getColumn(int[][] array, int index) {
        int[] column = new int[array[0].length];
        for (int i = 0; i < column.length; i++) {
            column[i] = array[i][index];
        }
        return column;
    }

    public void setActiveNumber(int activeNumber) {
        this.activeNumber = activeNumber;
    }

    private class Cell implements View.OnClickListener {

        private int i;
        private int j;
        Button btn;

        public Cell(int i, int j, Context c) {
            this.i = i;
            this.j = j;

            btn = new Button(c);

            // Correctly distributes height and width
            GridLayout.LayoutParams btn_param = new GridLayout.LayoutParams(GridLayout.spec(
                    GridLayout.UNDEFINED, GridLayout.FILL, 1f),
                    GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f));
            btn_param.height = 0;
            btn_param.width = 0;
            btn.setLayoutParams(btn_param);

            // Fill in the fixed values.
            assert values != null;
            int value = values[i][j];
            boolean fixedValue = value != -1;
            if (fixedValue) {
                btn.setText(String.valueOf(value));
            } else {
                btn.setOnClickListener(this);
            }
        }

        public Button getBtn() {
            return btn;
        }

        @Override
        public void onClick(View view) {
            Button btn = (Button) view;
            if (activeNumber == -1) btn.setText("");
            else btn.setText(String.valueOf(activeNumber));
            values[i][j] = activeNumber;
        }
    }
}