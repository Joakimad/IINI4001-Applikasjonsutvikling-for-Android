package com.example.oving2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TestRandomNumberActivity extends AppCompatActivity {

    int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button sendNumberBtn = findViewById(R.id.sendNumberBtn);
        sendNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get upper limit
                EditText maxValueNumber = findViewById(R.id.maxValueNumber);
                int maxValue = Integer.parseInt(maxValueNumber.getText().toString());

                // Sends upper limit to the other activity and waits for result
                Intent intent = new Intent(TestRandomNumberActivity.this, RandomNumberActivity.class);
                intent.putExtra("maxValue", maxValue);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            TextView resultTextView = findViewById(R.id.resultTextView);
            resultTextView.setText(Integer.toString(data.getIntExtra("randomResult", -1)));
        }
    }

}