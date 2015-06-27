package com.example.asiablr.databasebasics;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class RetrieveActivity extends ActionBarActivity {

    private FeedReaderDbHelper DbHelper;
    private SQLiteDatabase db;
    private TextView empDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        DbHelper = new FeedReaderDbHelper(getApplicationContext());
        db = DbHelper.getReadableDatabase();
        empDetails = (TextView) findViewById(R.id.EmpDetails);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_retrieve, menu);
        return true;
    }

    @Override
    protected void onResume() {
        String name, age, empid, out = "";

        // open the db and dump the contents
        Cursor c = db.query(FeedReaderContract.FeedEntry.TABLE_NAME, null, null, null, null, null, null);

        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            name = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_EMP_NAME));
            age = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_EMP_AGE));
            empid = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_EMP_ID));
            out = out + name + "," + age + "," + empid + '\n';
            c.moveToNext();
        }
        empDetails.setText(out);

        super.onResume();
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
