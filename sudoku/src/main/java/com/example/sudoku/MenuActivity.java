package com.example.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button playBtn;
    Button addBtn;
    Button languageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        playBtn = findViewById(R.id.button_play);
        playBtn.setOnClickListener(this);
        addBtn = findViewById(R.id.button_addBoard);
        addBtn.setOnClickListener(this);
        languageBtn = findViewById(R.id.button_language);
        languageBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_play:

                //TODO ADD SELECTOR FOR DIFFICULTIES
                Intent intent = new Intent(this, GameActivity.class);
                startActivity(intent);

            case R.id.button_addBoard:

                //TODO ADD BOARD IMPLEMENTATION
                Toast.makeText(this, "Button 2 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_language:
                //TODO CHANGE LANGUAGE
                Toast.makeText(this, "Button 3 clicked", Toast.LENGTH_SHORT).show();


                break;
        }
    }
}
