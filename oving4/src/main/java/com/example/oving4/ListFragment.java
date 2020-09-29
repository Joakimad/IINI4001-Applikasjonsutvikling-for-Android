package com.example.oving4;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

public class ListFragment extends androidx.fragment.app.ListFragment {
    private ListListener listener;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        this.listener = (ListListener) activity;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        listener.itemClicked((int) id);
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String[] heroNames = getResources().getStringArray(R.array.heroNames);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1, heroNames);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    interface ListListener {
        void itemClicked(int id);
    }
}