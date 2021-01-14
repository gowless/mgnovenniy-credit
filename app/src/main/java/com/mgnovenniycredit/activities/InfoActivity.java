package com.mgnovenniycredit.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustEvent;
import com.mgnovenniycredit.R;

public class InfoActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //event to track info page opened
        AdjustEvent adjustEvent = new AdjustEvent("6kfwy2");
        Adjust.trackEvent(adjustEvent);


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(InfoActivity.this, MainActivity.class));
    }
}