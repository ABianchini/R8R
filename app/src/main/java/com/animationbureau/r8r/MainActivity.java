package com.animationbureau.r8r;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    int width;
    int r8ing = 0;
    String whatS = "";
    String whyS = "";

    TextView fiveText;
    TextView fourText;
    TextView threeText;
    TextView twoText;
    TextView oneText;
    TextView zeroText;
    TextView negOneText;
    TextView negTwoText;
    TextView negThreeText;
    TextView negFourText;
    TextView negFiveText;
    EditText whatEdit;
    EditText whyEdit;
    HorizontalScrollView scrollRater;

    private GestureDetectorCompat mDetector;
    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.ic_action_r8r);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {}
            @Override
            public void onCancel() {}
            @Override
            public void onError(FacebookException error) {}
        });
        Button shareButton = (Button) findViewById(R.id.buttonPost);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;

        fiveText = (TextView) findViewById(R.id.textViewFive);
        fourText = (TextView) findViewById(R.id.textViewFour);
        threeText = (TextView) findViewById(R.id.textViewThree);
        twoText = (TextView) findViewById(R.id.textViewTwo);
        oneText = (TextView) findViewById(R.id.textViewOne);
        zeroText = (TextView) findViewById(R.id.textViewZero);
        negOneText = (TextView) findViewById(R.id.textViewNegOne);
        negTwoText = (TextView) findViewById(R.id.textViewNegTwo);
        negThreeText = (TextView) findViewById(R.id.textViewNegThree);
        negFourText = (TextView) findViewById(R.id.textViewNegFour);
        negFiveText = (TextView) findViewById(R.id.textViewNegFive);

        whatEdit = (EditText) findViewById(R.id.editTextWhat);
        whyEdit = (EditText) findViewById(R.id.editTextWhy);
        scrollRater = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);

        Resources r = getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, r.getDisplayMetrics());
        negFiveText.measure(0, 0);
        fiveText.measure(0, 0);
        int fiveWidth = (width - fiveText.getMeasuredWidth())/2;
        int negFiveWidth = (width - fiveText.getMeasuredWidth())/2;
        negFiveText.setPadding(negFiveWidth,0,(int)px,0);
        fiveText.setPadding((int)px,0,fiveWidth,0);
        scrollRater.setFadingEdgeLength((int)px*8);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        whatS = sharedPref.getString("whatStored", "");
        whyS = sharedPref.getString("whyStored","");
        r8ing = sharedPref.getInt("r8ingStored",0);
        whatEdit.setText(whatS);
        whyEdit.setText(whyS);
        if (sharedPref.getBoolean("firstBoot",true)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Welcome to R8R!");
            builder.setMessage("Thanks for stopping by. Just type something you'd like to rate, click a R8ing, and give a reason.\nFeel free to browse through your old R8S as well.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            builder.setNegativeButton("Why?", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(getApplicationContext(),"Why not?",Toast.LENGTH_LONG).show();
                    dialog.cancel();
                }
            });
            AlertDialog firstDialog = builder.create();
            firstDialog.show();
            sharedPref.edit().putBoolean("firstBoot",false).apply();
        }


        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                int x = zeroText.getLeft() + (zeroText.getWidth() - width)/2;
                switch (r8ing) {
                    case -5:    x = negFiveText.getLeft() + (negFiveText.getWidth() - width)/2;
                        break;
                    case -4:    x = negFourText.getLeft() + (negFourText.getWidth() - width)/2;
                        break;
                    case -3:    x = negThreeText.getLeft() + (negThreeText.getWidth() - width)/2;
                        break;
                    case -2:    x = negTwoText.getLeft() + (negTwoText.getWidth() - width)/2;
                        break;
                    case -1:    x = negOneText.getLeft() + (negOneText.getWidth() - width)/2;
                        break;
                    case 0:     x = zeroText.getLeft() + (zeroText.getWidth() - width)/2;
                        break;
                    case 1:     x = oneText.getLeft() + (oneText.getWidth() - width)/2;
                        break;
                    case 2:     x = twoText.getLeft() + (twoText.getWidth() - width)/2;
                        break;
                    case 3:     x = threeText.getLeft() + (threeText.getWidth() - width)/2;
                        break;
                    case 4:     x = fourText.getLeft() + (fourText.getWidth() - width)/2;
                        break;
                    case 5:     x = fiveText.getLeft() + (fiveText.getWidth() - width)/2;
                        break;
                }
                scrollRater.smoothScrollTo(x,0);
            }
        },500);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velX, float velY) {
            if (velX < 0) {
                Intent intent = new Intent(MainActivity.this,ListActivity.class);
                Bundle bundle = ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.list_anim_enter,R.anim.main_anim).toBundle();
                startActivity(intent,bundle);
            }
            return true;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPref =  this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        whatS = whatEdit.getText().toString();
        whyS = whyEdit.getText().toString();
        editor.putString("whatStored",whatS);
        editor.putString("whyStored",whyS);
        editor.putInt("r8ingStored",r8ing);
        editor.apply();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("R8ING",r8ing);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        r8ing = savedInstanceState.getInt("R8ING");
        int x = zeroText.getLeft() + (zeroText.getWidth() - width)/2;
        switch (r8ing) {
            case -5:    x = negFiveText.getLeft() + (negFiveText.getWidth() - width)/2;
                        break;
            case -4:    x = negFourText.getLeft() + (negFourText.getWidth() - width)/2;
                        break;
            case -3:    x = negThreeText.getLeft() + (negThreeText.getWidth() - width)/2;
                        break;
            case -2:    x = negTwoText.getLeft() + (negTwoText.getWidth() - width)/2;
                        break;
            case -1:    x = negOneText.getLeft() + (negOneText.getWidth() - width)/2;
                        break;
            case 0:     x = zeroText.getLeft() + (zeroText.getWidth() - width)/2;
                        break;
            case 1:     x = oneText.getLeft() + (oneText.getWidth() - width)/2;
                        break;
            case 2:     x = twoText.getLeft() + (twoText.getWidth() - width)/2;
                        break;
            case 3:     x = threeText.getLeft() + (threeText.getWidth() - width)/2;
                        break;
            case 4:     x = fourText.getLeft() + (fourText.getWidth() - width)/2;
                        break;
            case 5:     x = fiveText.getLeft() + (fiveText.getWidth() - width)/2;
                        break;
        }//TODO read r8ing from scroll position, maybe
        scrollRater.smoothScrollTo(x,0);
    }

    public void clickClearAll(View view) {
        r8ing = 0;
        whatEdit.setText("");
        whyEdit.setText("");
        scrollRater.smoothScrollTo(zeroText.getLeft() + (zeroText.getWidth() - width)/2,0);
    }

    //TODO add a post button and all that FB shit

    public void clickSaveR8(View view) {
        Calendar c = Calendar.getInstance();
        String dateSaved = Integer.toString(c.get(Calendar.MONTH)+1)+"/"+Integer.toString(c.get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(c.get(Calendar.YEAR));
        whatS = whatEdit.getText().toString();
        whyS = whyEdit.getText().toString();
        R8s r8s = new R8s();
        r8s.setWhat(whatS);
        r8s.setr8(r8ing);
        r8s.setWhy(whyS);
        r8s.setDate(dateSaved);

        DatabaseHandler db = new DatabaseHandler(this);
        db.addR8s(r8s);

        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(whyEdit.getWindowToken(), 0);
        mgr.hideSoftInputFromWindow(whatEdit.getWindowToken(), 0);
        r8ing = 0;
        whatEdit.setText("");
        whyEdit.setText("");
        scrollRater.smoothScrollTo(zeroText.getLeft() + (zeroText.getWidth() - width)/2,0);
    }

    public void clickGoToR8S(View view) {
        Intent intent = new Intent(MainActivity.this,ListActivity.class);
        Bundle bundle = ActivityOptions.makeCustomAnimation(this,R.anim.list_anim_enter,R.anim.main_anim).toBundle();
        startActivity(intent,bundle);
    }

    public void clickFive(View view) {
        int x = fiveText.getLeft() + (fiveText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
        r8ing = 5;
    }

    public void clickFour(View view) {
        int x = fourText.getLeft() + (fourText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
        r8ing = 4;
    }

    public void clickThree(View view) {
        int x = threeText.getLeft() + (threeText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
        r8ing = 3;
    }

    public void clickTwo(View view) {
        int x = twoText.getLeft() + (twoText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
        r8ing = 2;
    }

    public void clickOne(View view) {
        int x = oneText.getLeft() + (oneText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
        r8ing = 1;
    }

    public void clickZero(View view) {
        int x = zeroText.getLeft() + (zeroText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
        r8ing = 0;
    }

    public void clickNegOne(View view) {
        int x = negOneText.getLeft() + (negOneText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
        r8ing = -1;
    }

    public void clickNegTwo(View view) {
        int x = negTwoText.getLeft() + (negTwoText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
        r8ing = -2;
    }

    public void clickNegThree(View view) {
        int x = negThreeText.getLeft() + (negThreeText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
        r8ing = -3;
    }

    public void clickNegFour(View view) {
        int x = negFourText.getLeft() + (negFourText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
        r8ing = -4;
    }

    public void clickNegFive(View view) {
        int x = negFiveText.getLeft() + (negFiveText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
        r8ing = -5;
    }
}
