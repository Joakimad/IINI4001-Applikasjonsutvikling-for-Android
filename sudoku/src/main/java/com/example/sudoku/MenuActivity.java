package com.example.sudoku;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button playBtn;
    Button addBtn;
    Button instructionBtn;
    Button languageBtn;
    Boolean isDefaultLang = true;
    Locale locale_default = Locale.getDefault();
    Locale locale_en = new Locale("en", "US");
    Locale locale_no = new Locale("bm", "NO");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Locale.setDefault(locale_no);
        Configuration config = new Configuration();
        config.setLocale(locale_no);
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        Log.d("JOAKIM-MAIN", Locale.getDefault().getDisplayName());

        playBtn = findViewById(R.id.button_play);
        playBtn.setOnClickListener(this);
        addBtn = findViewById(R.id.button_addBoard);
        addBtn.setOnClickListener(this);
        instructionBtn = findViewById(R.id.button_instructions);
        instructionBtn.setOnClickListener(this);
        languageBtn = findViewById(R.id.button_language);
        languageBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button_play:

                String[] difficulties = {"Easy", "Medium", "Hard"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Pick a difficulty");
                builder.setItems(difficulties, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                        intent.putExtra("difficulty", which);
                        startActivity(intent);
                    }
                });
                builder.show();
                break;
            case R.id.button_addBoard:
                intent = new Intent(this, AddBoardActivity.class);
                startActivity(intent);
                break;
            case R.id.button_instructions:
                intent = new Intent(this, InstructionsActivity.class);
                startActivity(intent);
                break;
            case R.id.button_language:
                Locale current = Locale.getDefault();
                Log.d("JOAKIM", current.getDisplayName());
                if (current.getDisplayName().equals(locale_en.getDisplayName())) {
                    Log.d("JOAKIM", "NORSK NÅ");
                    setLocale(locale_no);
                    isDefaultLang = false;
                } else {
                    Log.d("JOAKIM", "ENG NÅ");
                    setLocale(locale_en);
                    isDefaultLang = true;
                }
                break;
        }
    }

    private void setLocale(Locale locale) {
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        this.recreate();
    }

}
