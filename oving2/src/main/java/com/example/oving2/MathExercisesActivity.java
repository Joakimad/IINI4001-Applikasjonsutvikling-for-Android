package com.example.oving2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MathExercisesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_exercises);

        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean correctAnswer = false;


                TextView num1TextView = findViewById(R.id.num1TextView);
                TextView num2TextView = findViewById(R.id.num2TextView);
                int firstNumber = Integer.parseInt(num1TextView.getText().toString());
                int secondNumber = Integer.parseInt(num2TextView.getText().toString());

                // Find correct answer
                int correctSum = firstNumber + secondNumber;

                // Find the users answer
                EditText answerEditText = findViewById(R.id.answerEditText);
                int userAnswer = Integer.parseInt(answerEditText.getText().toString());

                System.out.println("Correct Sum: " + correctSum);
                System.out.println("User Sum: " + userAnswer);

                // Compare the two
                if (correctSum == userAnswer) {
                    correctAnswer = true;
                }

                // Displays result in a toast
                String rightAnswer = getResources().getString(R.string.rightAnswer);
                String wrongAnswer = getResources().getString(R.string.wrongAnswer);
                CharSequence text;
                if (correctAnswer) {
                    text = rightAnswer;
                } else {
                    text = wrongAnswer + " " + correctSum;
                }

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast resultToast = Toast.makeText(context, text, duration);
                resultToast.show();
                generateNewNumbers(1);
            }
        });

        Button multiplyBtn = findViewById(R.id.multiplyBtn);
        multiplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean correctAnswer = false;

                TextView num1TextView = findViewById(R.id.num1TextView);
                TextView num2TextView = findViewById(R.id.num2TextView);
                int firstNumber = Integer.parseInt(num1TextView.getText().toString());
                int secondNumber = Integer.parseInt(num2TextView.getText().toString());

                // Find correct answer
                int correctSum = firstNumber * secondNumber;

                // Find the users answer
                EditText answerEditText = findViewById(R.id.answerEditText);
                int userAnswer = Integer.parseInt(answerEditText.getText().toString());

                System.out.println("Correct Sum: " + correctSum);
                System.out.println("User Sum: " + userAnswer);

                // Compare the two
                if (correctSum == userAnswer) {
                    correctAnswer = true;
                }

                // Displays result in a toast
                String rightAnswer = getResources().getString(R.string.rightAnswer);
                String wrongAnswer = getResources().getString(R.string.wrongAnswer);
                CharSequence text;
                if (correctAnswer) {
                    text = rightAnswer;
                } else {
                    text = wrongAnswer + " " + correctSum;
                }

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast resultToast = Toast.makeText(context, text, duration);
                resultToast.show();
                generateNewNumbers(1);
            }
        });
    }

    private void generateNewNumbers(int numTextView) {
        // Get upper limit
        EditText maxValueNumber = findViewById(R.id.upperlimitEditText);
        int upperlimit = Integer.parseInt(maxValueNumber.getText().toString());

        // Sends upper limit to the other activity and waits for result
        Intent intent = new Intent(MathExercisesActivity.this, RandomNumberActivity.class);
        intent.putExtra("maxValue", upperlimit);
        startActivityForResult(intent, numTextView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView num1TextView = findViewById(R.id.num1TextView);
        TextView num2TextView = findViewById(R.id.num2TextView);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            num1TextView.setText(Integer.toString(data.getIntExtra("randomResult", -1)));
            generateNewNumbers(2);
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            num2TextView.setText(Integer.toString(data.getIntExtra("randomResult", -1)));
        }
    }
}