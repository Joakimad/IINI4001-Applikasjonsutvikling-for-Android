package com.example.oving6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText number1EditText = null;
    EditText number2EditText = null;
    TextView equalsTextView = null;
    TextView sumTextView = null;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Server().start();

        number1EditText = findViewById(R.id.number1EditText);
        number2EditText = findViewById(R.id.number2EditText);
        equalsTextView = findViewById(R.id.equalsTextView);
        sumTextView = findViewById(R.id.sumTextView);

        Button button = (Button) findViewById(R.id.sendBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int num1 = Integer.parseInt(number1EditText.getText().toString());
                int num2 = Integer.parseInt(number2EditText.getText().toString());

                Client c = new Client(MainActivity.this, num1, num2);
                c.start();
            }
        });
    }

    public void showResponseFromServer(String response) {
        equalsTextView.setText("=");
        sumTextView.setText(response);
    }
}
