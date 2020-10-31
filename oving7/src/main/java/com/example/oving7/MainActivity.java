package com.example.oving7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private DatabaseManager db;
    private final int STATE_BOOKS = 0;
    private final int STATE_AUTHORS = 1;
    private int state;
    Button switchContentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        switchContentBtn = findViewById(R.id.changeContentBtn);
        switchContentBtn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                switchContent();
            }
        });

        try {
            getDataFromFile(R.raw.books);
        } catch (Exception e) {
            e.printStackTrace();
            String text = e.getMessage();
            TextView t = (TextView) findViewById(R.id.tw1);
            t.setText(text);
        }

    }

    public void showResults(ArrayList<String> list) {
        StringBuffer res = new StringBuffer();
        for (String s : list) {
            res.append(s).append("\n");
        }
        TextView t = (TextView) findViewById(R.id.tw1);
        t.setText(res);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivityForResult(new Intent(MainActivity.this, SettingsActivity.class), 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getDataFromFile(int id) throws Exception {
        db = new DatabaseManager(this);
        db.clean();

        InputStream is = getResources().openRawResource(id);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = reader.readLine();
        while (line != null) {
            String[] words = line.split(",");
            db.insert(words[0], words[1]);
            line = reader.readLine();
        }
        showResults(db.getAllBooks());

        reader.close();
    }

    private void switchContent() {
        if (state == STATE_AUTHORS) {
            showResults(db.getAllBooks());
            switchContentBtn.setText(R.string.authors);
            state = STATE_BOOKS;
        } else {
            showResults(db.getAllAuthors());
            switchContentBtn.setText(R.string.books);
            state = STATE_AUTHORS;
        }
    }
}
