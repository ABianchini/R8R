package com.animationbureau.r8r;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        }
        scrollRater.smoothScrollTo(x,0);
    }

    public void clickClearAll(View view) {
        //TODO here work!
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
