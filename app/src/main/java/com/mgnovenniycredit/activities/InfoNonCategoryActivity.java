package com.mgnovenniycredit.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustEvent;
import com.mgnovenniycredit.R;

import java.util.Objects;

public class InfoNonCategoryActivity extends AppCompatActivity {
    //init toolbar
    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //event to track info page opened
        AdjustEvent adjustEvent = new AdjustEvent("6kfwy2");
        Adjust.trackEvent(adjustEvent);


        Objects.requireNonNull(getSupportActionBar()).setTitle("");

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(InfoNonCategoryActivity.this, NonCategoriesActivity.class));
    }
}