package com.example.asiablr.simpleanimations;

import android.animation.Animator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class CrossFadeActivity extends ActionBarActivity {

    private View mContentView;
    private View mLoadingView;
    private int mShortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross_fade);

        mContentView = findViewById(R.id.content);
        mLoadingView = findViewById(R.id.loading_spinnner);

        // hide the content visibility
        mContentView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.VISIBLE);

        // use the default system short duration
        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation
        mContentView.setAlpha(0f);
        mContentView.setVisibility(View.VISIBLE);

        // animate the content view to 100% opacity, and clear any animation
        // listener set on the view
        mContentView.animate().alpha(1f).setDuration(mShortAnimationDuration).setListener(null);

        // animate the loading view to 0% opacity after animation is complete
        // set the visibility to GONE, so that loader icon disappears
        mLoadingView.animate().alpha(0f).setDuration(mShortAnimationDuration).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                // nothing to do
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mLoadingView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                //nothing to do
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //nothing to do
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cross_fade, menu);
        return true;
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
