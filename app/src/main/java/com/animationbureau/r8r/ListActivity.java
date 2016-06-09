package com.animationbureau.r8r;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.list_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("R8S");
        //TODO add up button

        DatabaseHandler db = new DatabaseHandler(this);

        final ArrayList<R8s> r8sList = db.getAllR8s();

        Collections.reverse(r8sList);
        r8sAdapter adapter = new r8sAdapter(this,r8sList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                R8s r8s = r8sList.get(position);
                //TODO add dialog box with details
                Toast toast = Toast.makeText(getApplicationContext(), r8s.getWhy(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
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
                DatabaseHandler db = new DatabaseHandler(this);
                db.deleteAllR8s();

                final ArrayList<R8s> r8sList = db.getAllR8s();
                Collections.reverse(r8sList);
                r8sAdapter adapter = new r8sAdapter(this,r8sList);
                ListView listView = (ListView) findViewById(R.id.list_view);
                listView.setAdapter(adapter);

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
