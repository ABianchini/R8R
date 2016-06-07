package com.animationbureau.r8r;

import android.app.ActivityOptions;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);



    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.main_anim,R.anim.list_anim_exit);
    }

}
