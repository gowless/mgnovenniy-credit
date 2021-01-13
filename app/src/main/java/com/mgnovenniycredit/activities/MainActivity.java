package com.mgnovenniycredit.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustEvent;
import com.google.android.material.tabs.TabLayout;
import com.mgnovenniycredit.R;
import com.mgnovenniycredit.adapters.uitabbed.SectionsPagerAdapter;


public class MainActivity extends AppCompatActivity {

    //section pager
    SectionsPagerAdapter sectionsPagerAdapter;

    public static String  net, cam, adg, cre, subid1, subid2, subid3;

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

    //top alert
    ConstraintLayout topAlert;

    //top constraint
    ConstraintLayout topConstraint;

    //tab layout
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting prefs to variables
        getPrefs();

        //top constraint init
        topConstraint = findViewById(R.id.topConstraint);
        //init Section Pager instance
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        //declaring vars
        setDeclaring();

        //section pager initialization to display tabs
        setupViewPager();

        //network callback for getting data
        setNetworkCallBacks();

        //check for network connection
        if (isNetworkAvailable()) {

        } else {
            //event to track non-ethernet cases
            AdjustEvent adjustEvent = new AdjustEvent("kkkmir");
            Adjust.trackEvent(adjustEvent);
            setNonEthernetCase();
        }


        //calling function of clicked tab info icon on top
     /*   infoTabIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting info activity
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
            }
        }); */
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.GONE);

        //setting top alert onClick
        topAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topAlert.setVisibility(View.GONE);
            }
        });

        topConstraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
            }
        });


        for(int i=0; i < tabLayout.getTabCount(); i++) {
            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(0, 0, 10, 0);
            tab.requestLayout();
        }

    }



    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    //network availability check
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    //setting ViewPager
    private void setupViewPager(){
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs_layout);
        tabs.setupWithViewPager(viewPager);
    }

    //setting Network Callbacks
    private void setNetworkCallBacks(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities == null){

            }
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback(){
                @Override
                public void onAvailable(@NonNull Network network) {
                     /*
                    here you can add some features when ethernet comes back
                     */
                }
                @Override
                public void onLost(@NonNull Network network) {
                     /*
                    here you can add some features when ethernet connection lost
                     */
                }
            });
        }
    }

    // declaring main objects
    private void setDeclaring(){
        //textview and image of non-inherent case
        textView = findViewById(R.id.text_non_Ithernet);
        imageView = findViewById(R.id.non_Ithernet);
        //recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        //progress bar
        progressBar = findViewById(R.id.progressBar);
        //top alert
        topAlert = findViewById(R.id.topAlert);
        //tabs layout
        tabLayout = findViewById(R.id.tabs_layout);
        //text view pls
        textViewPls = findViewById(R.id.text_pls);
    }

    //setting image and text in non-ethernet case
    private void setNonEthernetCase(){
        textView.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        topAlert.setVisibility(View.GONE);
        textViewPls.setVisibility(View.VISIBLE);
    }

    private void getPrefs(){
        SharedPreferences settings = getSharedPreferences("LOCAL", Context.MODE_PRIVATE);
        net = settings.getString("network", "");
        cam = settings.getString("campaign", "");
        adg = settings.getString("adgroup", "");
        cre = settings.getString("creative", "");
        subid1 = settings.getString("sub1", "");
        subid2 = settings.getString("sub2", "");
        subid3 = settings.getString("sub3", "");
    }


}
