package com.animationbureau.r8r;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<R8s> r8sList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.list_toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setTitle("R8S");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("R8S");

        final DatabaseHandler db = new DatabaseHandler(this);
        r8sList = db.getAllR8s();
        Collections.reverse(r8sList);
        r8sAdapter adapter = new r8sAdapter(this,r8sList);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                r8sList = db.getAllR8s();
                Collections.reverse(r8sList);
                R8s r8s = r8sList.get(position);
                detailsDialog(r8s);
            }
        });
    }

    public void detailsDialog(final R8s r8s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final DatabaseHandler db = new DatabaseHandler(this);

        builder.setTitle(r8s.getWhat());
        builder.setMessage(r8s.getr8()+": "+r8s.getWhy());
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Share", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String whyS = r8s.getWhy();
                String whatS = r8s.getWhat();
                int r8ing = r8s.getr8();

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, whatS+" (R8: "+Integer.toString(r8ing)+")\n"+whyS);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent,"Share your R8:"));
            }
        });
        builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                db.deleteR8s(r8s);

                final ArrayList<R8s> r8sList = db.getAllR8s();
                final r8sAdapter adapter = new r8sAdapter(getApplicationContext(),r8sList);
                Collections.reverse(r8sList);
                listView = (ListView) findViewById(R.id.list_view);
                listView.setAdapter(adapter);
                dialog.cancel();
            }
        });
        AlertDialog r8ingDetails = builder.create();
        r8ingDetails.show();
        r8ingDetails.getWindow().getAttributes();
        TextView tvMessage = (TextView) r8ingDetails.findViewById(android.R.id.message);
        tvMessage.setTextSize(24);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.deleteR8s:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final DatabaseHandler db = new DatabaseHandler(this);

                builder.setTitle("Confirm Delete");
                builder.setMessage("Are you sure you want to delete all of your R8S?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db.deleteAllR8s();
                        finish();
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog r8ingDetails = builder.create();
                r8ingDetails.show();
                return true;
            case R.id.showChart:
                Intent intent = new Intent(ListActivity.this,AnalyticsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.main_anim,R.anim.list_anim_exit);
    }

}
