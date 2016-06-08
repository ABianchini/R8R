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

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.list_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("R8S");

        final ArrayList<R8s> r8sList = new ArrayList<R8s>();

        R8s r8s1 = new R8s();
        r8s1.setID(1);
        r8s1.setWhat("Harry Potter Ride");
        r8s1.setr8(-5);
        r8s1.setWhy("Tween line simulator 2k16");
        r8sList.add(r8s1);
        R8s r8s2 = new R8s();
        r8s2.setID(2);
        r8s2.setWhat("This App");
        r8s2.setr8(-2);
        r8s2.setWhy("I can't take selfies with it.");
        r8sList.add(r8s2);

        r8sAdapter adapter = new r8sAdapter(this,r8sList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                R8s r8s = r8sList.get(position);
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
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            case R.id.deleteR8s:
                //TODO create "delete all for database" and "reload list"
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
