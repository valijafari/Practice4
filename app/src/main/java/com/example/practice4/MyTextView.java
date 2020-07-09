package com.example.practice4;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class MyTextView extends androidx.appcompat.widget.AppCompatTextView {
    public MyTextView(Context context)
    {
        super(context);
    }
    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface myfont = Typeface.createFromAsset(context.getAssets(), "vazir.ttf");
        this.setTypeface(myfont);
    }


}
