package com.example.asiablr.pingpong;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends ActionBarActivity {

    ViewGroup sceneFrame;
    private Scene mScene1, mScene2, mScene3;
    private int screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sceneFrame = (ViewGroup) findViewById(R.id.sceneRoot);
        mScene1 = Scene.getSceneForLayout(sceneFrame, R.layout.scene1, this);
        mScene2 = Scene.getSceneForLayout(sceneFrame, R.layout.scene2, this);
        mScene3 = Scene.getSceneForLayout(sceneFrame, R.layout.scene3, this);
        screen = 1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // called when frame layout is clicked
    public void frameClick(View v) {
        Transition fade = TransitionInflater.from(this).inflateTransition(R.transition.fade_transition);
        if (1 == screen) {
            TransitionManager.go(mScene2, fade);
            screen = 2;
        } else if (2 == screen){ // ping pong
            TransitionManager.go(mScene3, fade);
            screen = 3;
        } else {
            TransitionManager.go(mScene1, fade);
            screen = 1;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
