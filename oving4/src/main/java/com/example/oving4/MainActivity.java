package com.example.oving4;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements ListFragment.ListListener {

    private int imageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Default to first element
        itemClicked(0);
    }

    // Handles changing image when clicking on the list
    @Override
    public void itemClicked(int clickId) {
        imageId = clickId;
        ImageFragment imgFragment = new ImageFragment();
        imgFragment.setImageId(clickId);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.imgfragment, imgFragment)
                .addToBackStack("LIST")
                .commit();
    }

    // Menu buttons
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Handles previous and next buttons changing image.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_previous:
                if (imageId > 0) {
                    itemClicked(--imageId);
                } else itemClicked(3);
                return true;
            case R.id.menu_next:

                if (imageId < 3) {
                    itemClicked(++imageId);
                } else itemClicked(0);
                return true;
        }
        return false;
    }
}
