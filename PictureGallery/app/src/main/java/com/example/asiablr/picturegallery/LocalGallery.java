package com.example.asiablr.picturegallery;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


public class LocalGallery extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_gallery);

        // grid stuff
        GridView pictureGrid = (GridView) findViewById(R.id.LocalGridView);
        pictureGrid.setAdapter(new ImageAdapter(this));

        pictureGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LocalGallery.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_local_gallery, menu);
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

    private class ImageAdapter extends BaseAdapter {

        private Context localContext;

        // only available constructor
        public ImageAdapter(Context c) {
            super();
            localContext = c;
        }

        @Override
        public int getCount() {
            return Images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            int width = 150, height = 200;

            if (convertView == null) {
                // looks to be a new view or deleted
                imageView = new ImageView(localContext);
                imageView.setLayoutParams(new GridView.LayoutParams(width, height));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(4,4,4,4);
            } else {
                // view is still active
                imageView = (ImageView) convertView;
            }

            ResizeImage image = new ResizeImage();
//            imageView.setImageBitmap(image.resizeImage(getResources(), Images[position], width, height));
            image.resizeAndDisplayAsync(imageView, getResources(), Images[position], width, height);
//            imageView.setImageDrawable(image.resizeImage(getResources(), Images[position], width, height));
            return imageView;
        }

        private Integer[] Images = {
                R.drawable.sample0, R.drawable.sample1, R.drawable.sample2,
                R.drawable.sample3, R.drawable.sample4, R.drawable.sample5,
                R.drawable.sample6, R.drawable.sample7, R.drawable.sample8,
                R.drawable.sample9, R.drawable.sample10, R.drawable.sample11,
                R.drawable.sample12, R.drawable.sample13,
        };
    }
}
