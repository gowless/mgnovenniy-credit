package com.mgnovenniycredit.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mgnovenniycredit.R;


public class InfoDetailsActivity extends AppCompatActivity {

    //init toolbar
    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_details);


        setSupportActionBar(toolbar);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(InfoDetailsActivity.this, CloakActivity.class));
    }
}