package com.mgnovenniycredit.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mgnovenniycredit.R;


public class TransparentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent);
        //starting splash activity
        startActivity(new Intent(TransparentActivity.this, SplashActivity.class));

    }
}