package com.example.asiablr.fragmentbasics;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class TopFragment extends Fragment {

    private static EditText textinput;

    // we cant use intent for Fragments
    TopFragmentListener customIntent;

    public interface TopFragmentListener {
        public void sendMessage(String text);
    }

    @Override
    public void onAttach(Activity activity) { // provides the activity
        super.onAttach(activity);
        try {
            customIntent = (TopFragmentListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement sendMessage()");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.topfragment, container, false);
        textinput = (EditText) view.findViewById(R.id.mytextbox);

        final Button sendButton = (Button) view.findViewById(R.id.sendbutton);

        sendButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        sendButtonClick(v);
                    }
                }
        );

        // inflate top fragment
        return view;
    }

    // handle the send button click here
    public void sendButtonClick(View v) {
        customIntent.sendMessage(textinput.getText().toString());
    }

}
