package com.example.sudoku;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button playBtn;
    Button addBtn;
    Button instructionBtn;
    Button languageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

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
                //TODO ADD SELECTOR FOR DIFFICULTIES
                intent = new Intent(this, GameActivity.class);
                startActivity(intent);

            case R.id.button_addBoard:

                //TODO ADD BOARD IMPLEMENTATION
                Toast.makeText(this, "Button 2 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_instructions:
                intent = new Intent(this, InstructionsActivity.class);
                startActivity(intent);
                break;
            case R.id.button_language:
                //defaultLocale = getLo
                Locale locale_no = new Locale("bm", "NO");
                setLocale(locale_no);
                break;
        }
    }

    private void setLocale(Locale locale) {

        Locale.setDefault(locale);
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

//        Resources resources = getResources();
//        Configuration configuration = resources.getConfiguration();
//        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            configuration.setLocale(locale);
//        } else {
//            configuration.locale = locale;
//        }
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
//            getApplicationContext().createConfigurationContext(configuration);
//        } else {
//            resources.updateConfiguration(configuration, displayMetrics);
//        }

        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

}
