package com.animationbureau.r8r;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int width;
    int r8ing = 0;
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

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollRater.smoothScrollTo(zeroText.getLeft() + (zeroText.getWidth() - width)/2,0);
            }
        },500);
    }

    public void clickFive(View view) {
        int x = fiveText.getLeft() + (fiveText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
    }

    public void clickFour(View view) {
        int x = fourText.getLeft() + (fourText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
    }

    public void clickThree(View view) {
        int x = threeText.getLeft() + (threeText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
    }

    public void clickTwo(View view) {
        int x = twoText.getLeft() + (twoText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
    }

    public void clickOne(View view) {
        int x = oneText.getLeft() + (oneText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
    }

    public void clickZero(View view) {
        int x = zeroText.getLeft() + (zeroText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
    }

    public void clickNegOne(View view) {
        int x = negOneText.getLeft() + (negOneText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
    }

    public void clickNegTwo(View view) {
        int x = negTwoText.getLeft() + (negTwoText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
    }

    public void clickNegThree(View view) {
        int x = negThreeText.getLeft() + (negThreeText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
    }

    public void clickNegFour(View view) {
        int x = negFourText.getLeft() + (negFourText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
    }

    public void clickNegFive(View view) {
        int x = negFiveText.getLeft() + (negFiveText.getWidth() - width)/2;
        scrollRater.smoothScrollTo(x,0);
    }
}