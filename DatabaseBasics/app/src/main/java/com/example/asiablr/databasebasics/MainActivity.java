package com.example.asiablr.databasebasics;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    private EditText Name;
    private EditText Age;
    private EditText EmpID;
    private TextView DbStatus;
    private FeedReaderDbHelper DbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.EmpName);
        Age = (EditText) findViewById(R.id.AgeValue);
        EmpID = (EditText) findViewById(R.id.EmpID);
        DbStatus = (TextView) findViewById(R.id.dbstatus);

        DbHelper = new FeedReaderDbHelper(getApplicationContext());
        db = DbHelper.getWritableDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // called when save button is clicked
    public void OnSaveButtonClick(View v) {
        String name, age, id;

        name = Name.getText().toString();
        age = Age.getText().toString();
        id = EmpID.getText().toString();

        if ((name.length() > 0) && (age.length() > 0) &&
                (id.length() > 0)) {
            // create a map of values, with column names as Key
            ContentValues emp = new ContentValues();
            emp.put(FeedReaderContract.FeedEntry.COLUMN_EMP_NAME, name);
            emp.put(FeedReaderContract.FeedEntry.COLUMN_EMP_AGE, age);
            emp.put(FeedReaderContract.FeedEntry.COLUMN_EMP_ID, id);

            long rowid = db.insert(
                    FeedReaderContract.FeedEntry.TABLE_NAME,
                    null,
                    emp);
            DbStatus.setText("db " + Long.toString(rowid));
        }

    }

    // called when retrieve button is clicked
    public void OnRetrieveButtonClick(View v) {
        Intent intent = new Intent(this, RetrieveActivity.class);
        startActivity(intent);
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
