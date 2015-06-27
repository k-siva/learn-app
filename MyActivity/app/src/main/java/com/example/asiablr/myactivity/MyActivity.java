package com.example.asiablr.myactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MyActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.asiablr.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    // called when user click Send button
    public void sendMessage(View view) {
        // do something here
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText text = (EditText) findViewById(R.id.edit_message);
        String msg = text.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, msg);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_mymenu:

                return true;
            case R.id.action_search:

                return true;
            case R.id.action_settings:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
