package com.example.oving7;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity extends Activity implements AdapterView.OnItemSelectedListener {

    String[] colors;
    int selected_color = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        colors = new String[]{"Black", "Red", "Green", "Blue"};
        Spinner spinner = findViewById(R.id.colorSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, colors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        // Test Toast
        Toast.makeText(getApplicationContext(), colors[position], Toast.LENGTH_SHORT).show();

        selected_color = position;

        // TODO CHANGE COLOR OF LIST.
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //
    }
}