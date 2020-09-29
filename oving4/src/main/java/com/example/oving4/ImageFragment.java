package com.example.oving4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ImageFragment extends Fragment {

    private int imageId;

    public ImageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        ImageView imageView = getView().findViewById(R.id.imageView);
        TextView realNameTextView = getView().findViewById(R.id.textView);

        // Change Text
        realNameTextView.setText(getResources().getStringArray(R.array.realNames)[imageId]);

        // Change Image
        switch (imageId) {
            case 0:
                imageView.setImageResource(R.drawable.captain);
                break;
            case 1:
                imageView.setImageResource(R.drawable.hulk);
                break;
            case 2:
                imageView.setImageResource(R.drawable.ironman);
                break;
            case 3:
                imageView.setImageResource(R.drawable.thor);
                break;
        }
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}

