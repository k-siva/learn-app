package com.example.asiablr.fragmentbasics;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BottomFragment extends Fragment {

    private TextView textout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottomfragment, container, false);
        textout = (TextView)view.findViewById(R.id.textouput);

        // inflate the bottom fragment
        return view;
    }

    // display the text output after use presses the Send button
    public void DisplayText(String text) {
        textout.setText(text);
    }

}
