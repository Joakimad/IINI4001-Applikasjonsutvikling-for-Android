package com.example.oving5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {

    private String TAG = "QuizActivity";
    private String urlToServer = "http://tomcat.stud.iie.ntnu.no/studtomas/tallspill.jsp";
    private HttpWrapper network = new HttpWrapper(this, urlToServer);
    private TextView responseTextView;
    private EditText inputNumberEditText;
    private Button sendNumberBtn;
    private int guessCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        responseTextView = findViewById(R.id.responseTextView);
        inputNumberEditText = findViewById(R.id.inputNumberEditText);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String card = intent.getStringExtra("card");
        register(name, card);

        sendNumberBtn = findViewById(R.id.sendNumberBtn);
        sendNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guessCount++;
                makeGuess(Integer.parseInt(inputNumberEditText.getText().toString()));
            }
        });

        Button resetBtn = findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void makeGuess(int number) {
        Map<String, String> valueList = new HashMap<>();
        valueList.put("tall", Integer.toString(number));

        network.runHttpRequestInThread(HttpWrapper.HttpRequestType.HTTP_GET, valueList);

    }

    private void register(String name, String card) {
        Map<String, String> valueList = new HashMap<>();
        valueList.put("navn", name);
        valueList.put("kortnummer", card);

        Log.w(TAG, "Starting registration of user " + name + " with card: " + card);
        network.runHttpRequestInThread(HttpWrapper.HttpRequestType.HTTP_GET, valueList);
    }

    public void handleResponse(String response) {
        Log.w(TAG, String.valueOf(guessCount));
        responseTextView.setText(response);

        // Hacky måte å sjekke om rett svar er kommet.
        boolean guessedRight = !(response.startsWith("Tallet")||response.startsWith("Oppgi"));

        if (guessCount >= 3 || guessedRight) {
            sendNumberBtn.setEnabled(false);
        }
    }
}
