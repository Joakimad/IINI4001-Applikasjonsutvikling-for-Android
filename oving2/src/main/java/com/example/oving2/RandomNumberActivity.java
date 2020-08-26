package com.example.oving2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RandomNumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int maxValue = getIntent().getIntExtra("maxValue", 100);

        Button randomBtn = findViewById(R.id.randomBtn);
        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Generates Random Number
                int min = 0;
                int max = maxValue;
                int randomNum = min + (int) (Math.random() * ((max - min) + 1));
                System.out.println("Random Number = " + randomNum);

                /* Displays result in a toast
                Context context = getApplicationContext();
                CharSequence text = "" + randomNum;
                int duration = Toast.LENGTH_SHORT;
                Toast resultToast = Toast.makeText(context, text, duration);
                resultToast.show();
                */

                // Sends result in an intent
                Intent resultIntent = new Intent();
                resultIntent.putExtra("randomResult", randomNum);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
