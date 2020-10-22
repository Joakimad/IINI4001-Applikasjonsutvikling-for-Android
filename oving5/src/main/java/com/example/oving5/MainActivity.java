package com.example.oving5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    private EditText nameEditText;
    private EditText cardEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w(TAG, "Contacting server...");

        nameEditText = findViewById(R.id.nameEditText);
        cardEditText = findViewById(R.id.cardEditText);

        Button addBtn = findViewById(R.id.sendUserBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), QuizActivity.class);
                intent.putExtra("name", nameEditText.getText().toString());
                intent.putExtra("card", cardEditText.getText().toString());
                startActivity(intent);
            }
        });
    }
}
