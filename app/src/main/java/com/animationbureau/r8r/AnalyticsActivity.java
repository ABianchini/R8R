package com.animationbureau.r8r;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalyticsActivity extends AppCompatActivity {
    ArrayList<R8s> r8sList;
    int[] r8Array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.analytics_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Analytics");

        final DatabaseHandler db = new DatabaseHandler(this);
        r8sList = db.getAllR8s();
        int[] tempArray = new int[r8sList.size()];
        for(int i=0; i < r8sList.size(); i++) {
            tempArray[i] = r8sList.get(i).getr8();
//            R8s r8s = r8sList.get(position);
        }
        r8Array = tempArray;
        TextView r8Count = (TextView) findViewById(R.id.textR8Count);
        r8Count.setText("Total R8S: "+Integer.toString(r8Array.length));
        TextView r8List = (TextView) findViewById(R.id.textR8List);
        r8List.setText("List of R8S: "+Arrays.toString(r8Array));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
