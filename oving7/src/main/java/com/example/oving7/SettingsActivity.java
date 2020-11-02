package com.example.oving7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends Activity {

    String[] colors;
    int selected_color = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void goBackButton(View v){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();

    }

}