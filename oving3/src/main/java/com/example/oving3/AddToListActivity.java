package com.example.oving3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddToListActivity extends AppCompatActivity {
    private TextView enterDateTextView;
    private DatePickerDialog.OnDateSetListener enterDateTextViewListener;
    private Calendar cal = Calendar.getInstance();
    private int year = cal.get(Calendar.YEAR);
    private int month = cal.get(Calendar.MONTH);
    private int day = cal.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_list);

        final EditText addNameEditText = findViewById(R.id.addNameEditText);
        enterDateTextView = findViewById(R.id.enterDateTextView);


        String data = getIntent().getStringExtra("personalia");
        if (data != null) {
            String[] nameAndDate = data.split("\n");
            addNameEditText.setText(nameAndDate[0]);
            String date = nameAndDate[1];
            enterDateTextView.setText(date);
            String[] dateArr = date.split("/");
            day = Integer.parseInt(dateArr[0]);
            month = Integer.parseInt(dateArr[1]) - 1;
            year = Integer.parseInt(dateArr[2]);
        }

        enterDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        AddToListActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        enterDateTextViewListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        enterDateTextViewListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                enterDateTextView.setText(date);
            }
        };

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = addNameEditText.getText().toString();
                String birthdate = enterDateTextView.getText().toString();
                //String[] personalia = new String[]{name, birthdate};
                String personalia = name + "\n" + birthdate;

                // Sends result in an intent
                Intent resultIntent = new Intent();
                resultIntent.putExtra("personalia", personalia);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}