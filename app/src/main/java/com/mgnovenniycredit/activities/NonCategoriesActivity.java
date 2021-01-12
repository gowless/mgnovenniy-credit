package com.mgnovenniycredit.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustEvent;
import com.mgnovenniycredit.R;
import com.mgnovenniycredit.adapters.NonCategoriesAllAdapter;


public class NonCategoriesActivity extends AppCompatActivity {


    //recyclerview
    RecyclerView recyclerView;

    //progress bar
    ProgressBar progressBar;

    //info tab icon declaring
    ImageView infoTabIcon;

    //img non-ithernet
    ImageView imageView;

    //text non-ithernet
    TextView textView;

    //text pls
    TextView textViewPls;

    //constraint
    ConstraintLayout topConstraint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_categories);

        //declaring items
        declaringItems();

        //check for network connection
        if(isNetworkAvailable()){
            settingAdapter();
        } else {
            //event to track non-ethernet cases
            AdjustEvent adjustEvent = new AdjustEvent("kkkmir");
            Adjust.trackEvent(adjustEvent);

            //setting non-ethernet page
            setNonEthernetCase();
        }

        topConstraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NonCategoriesActivity.this, InfoDetailsActivity.class));
            }
        });


    }


    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    //checking network availability
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    //setting adapter
    private  void settingAdapter(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        NonCategoriesAllAdapter recyclerAdapterAllMain = new NonCategoriesAllAdapter(getApplicationContext(), SplashActivity.listDataAll);
        recyclerAdapterAllMain.setDataList(SplashActivity.listDataAll);
        recyclerView.setAdapter(recyclerAdapterAllMain);
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.GONE);
    }

    //initializing items
    private void declaringItems(){
        //textview and image of non-inherent case
        textView = findViewById(R.id.text_non_Ithernet);
        imageView = findViewById(R.id.non_Ithernet);
        //constraint click
        topConstraint = findViewById(R.id.topConstraint);

        //recyclerview
        recyclerView = findViewById(R.id.recyclerView);

        //progress bar
        progressBar = findViewById(R.id.progressBar);

        //info tab icon init
       // infoTabIcon = findViewById(R.id.info_tab_icon);
        //text view pls
        textViewPls = findViewById(R.id.text_pls);
    }

    //setting image and text in non-ethernet case
    private void setNonEthernetCase(){
        textView.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
       // infoTabIcon.setVisibility(View.GONE);
        textViewPls.setVisibility(View.VISIBLE);
    }

}