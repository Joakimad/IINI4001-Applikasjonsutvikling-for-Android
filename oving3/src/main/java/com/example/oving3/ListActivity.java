package com.example.oving3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    //private ArrayList<String[]> people = new ArrayList<>();
    private ArrayList<String> people = new ArrayList<>();
    private ArrayAdapter adapter;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String[] per = new String[]{"Per", "10/11/93"};
        //String[] anne = new String[]{"Anne", "03/05/99"};
        String per = "Per\n10/11/93";
        String anne = "Anne\n03/05/99";
        String jim = "Jim\n21/01/98";


        people.add(per);
        people.add(anne);
        people.add(jim);
        people.add(per);
        people.add(anne);
        people.add(jim);

        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // startActivity(new Intent(ListActivity.this, AddToListActivity.class));
                Intent intent = new Intent(ListActivity.this, AddToListActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        ListView peopleListView = findViewById(R.id.listview);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, people);
        peopleListView.setAdapter(adapter);

        peopleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListActivity.this, AddToListActivity.class);
                currentIndex = i;
                intent.putExtra("personalia", people.get(i));
                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Add to list
            //String[] newPerson = data.getStringArrayExtra("personalia");
            String newPerson = data.getStringExtra("personalia");
            people.add(newPerson);
            adapter.notifyDataSetChanged();
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            String newPerson = data.getStringExtra("personalia");
            // Update list
            people.set(currentIndex,newPerson);
            adapter.notifyDataSetChanged();
        }
    }
}