package com.example.zhill.viewflipperdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    public ViewFlipper flipper;
    private float lastX;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int mCurrentLayoutState = 0;
        flipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        final TextView mTextView1 = (TextView) findViewById(R.id.textView1);
        final ImageView imageView = (ImageView) findViewById(R.id.imageview);
        final ImageView imageView1 = (ImageView) findViewById(R.id.imageview1);
        final ImageView imageView2 = (ImageView) findViewById(R.id.imageview2);

        final Button button = findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flipper.isFlipping()) {
                    flipper.stopFlipping();
                } else {
                    flipper.startFlipping();
                }
            }
        });


        TextView mTextView2 = (TextView) findViewById(R.id.textView2);
        flipper.setFlipInterval(3000);
        imageView.setImageResource(R.mipmap.ic_launcher);
        imageView1.setImageResource(R.drawable.pup);
        imageView2.setImageResource(R.drawable.germanpup);
        flipper.startFlipping();
        mTextView1.setText("APPS ARE COOL!!!!!!!");
        flipper.setInAnimation(this,R.anim.slide_in_from_right);
        flipper.setOutAnimation(this,R.anim.space);
        flipper.startFlipping();
    }

    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //store the value when first touched
                lastX = touchevent.getX();
                break;
            case MotionEvent.ACTION_UP:
                //store the last value
                float currentX = touchevent.getX();
                //if the current is greater then they swiped right
                if( currentX > lastX) {
                    flipper.setInAnimation(this, R.anim.slide_in_from_left);
                    // Current screen goes out from right.
                    flipper.setOutAnimation(this, R.anim.slide_out_to_right);

                    flipper.showNext();
                }else {
                    //if swiped left then show the last
                    flipper.setInAnimation(this, R.anim.slide_in_from_right);
                    // Current screen goes out from left.
                    flipper.setOutAnimation(this, R.anim.slide_out_to_left);

                    flipper.showPrevious();
                }
                default:
                    flipper.setInAnimation(this,R.anim.slide_in_from_right);
                    flipper.setOutAnimation(this,R.anim.space);
                break;
        }
        return false;
    }
}