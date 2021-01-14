package com.mgnovenniycredit.activities;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.applinks.AppLinkData;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mgnovenniycredit.MainClass;
import com.mgnovenniycredit.R;
import com.mgnovenniycredit.models.post.get.Data;
import com.mgnovenniycredit.models.post.get.Liste;
import com.mgnovenniycredit.network.Initializator;
import com.mgnovenniycredit.network.Interface;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashActivity extends AppCompatActivity {

    //attribution strings
    public static String  net, cam, adg, cre;

    //fragments lists of offers
    public static List<Liste> listDataAll;
    public static List<Liste> listDataBad;
    public static List<Liste> listDataZero;

    //https://api.mocki.io/v1/fe341b7d
    //url base
    public static final String APP_ID = "e0f0c81b-2bf4-482e-8862-80474131223b";
    //category empty check field
    public static Boolean isEmpty;
    //number of tabs field
    public static int numberOfTabs;
    //names of each tab
    public static String first, second, third;

    public static List<Liste> testDataList = new ArrayList<>();
    public static List<Liste> testDataList2 = new ArrayList<>();


    //carrier name string
    String carrier;


    //static AD_ID
    public static String ad_id;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //getting fb subid's
        if(MainClass.network == "Facebook Installs"){
            //getting fb subid's
            getDeepLink();
        }

        //get firebase instance
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //add event to firebase
        Bundle bundle = new Bundle();
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);

        //get ad_id
        getID();

        //getting carrier ISO name
        getCarrier();

        //checking carrier to match current country code SIM
        if (carrier.equals("ua")) {
            getJsonData();
        } else {
            getJsonDataCloak();
        }

    }


    //starting CloakActivity
    public void getCloak() {
        startActivity(new Intent(SplashActivity.this, CloakActivity.class));
    }

    //get carrier name
    public void getCarrier() {
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        carrier = manager.getSimCountryIso();
    }

    //starting BeforeMain activity
    public void getBeforeMain() {
        startActivity(new Intent(SplashActivity.this, BeforeMainActivity.class));
    }

    //getting advertising ID
    public void getID() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(SplashActivity.this);
                    ad_id = adInfo != null ? adInfo.getId() : null;

                    assert ad_id != null;

                } catch (Exception e) {

                }
            }
        });
    }

    //setting to get json file and parse it to models in main case
    public void getJsonData() {
        Interface apiInterfaceCount = Initializator.getClient().create(Interface.class);
        Call<Data> call = apiInterfaceCount.getData(MainClass.REGION, MainClass.APPID);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(@NonNull Call<Data> call, @NonNull Response<Data> response) {
                assert response.body() != null;
                listDataAll = response.body().getList();
                isEmpty = response.body().getCategories().isEmpty();
                numberOfTabs = response.body().getCategories().size();
                listDataBad = response.body().getList();
                listDataZero = response.body().getList();

                //iterator for 2-nd tab (new list)
                Iterator<Liste> x = listDataZero.iterator();
                while (x.hasNext()){
                    Liste s = x.next();
                    if (s.getCategories().contains("zero")){
                        testDataList.add(s);
                    } else {
                        //x.remove();
                    }
                }


                //iterator for 3-rd tab (bad list)
                Iterator<Liste> i = listDataBad.iterator();
                while (i.hasNext()){
                    Liste m = i.next();
                    if (m.getCategories().contains("badCreditHistory")){
                        testDataList2.add(m);

                    } else {
                        //  i.remove();
                    }
                }


                //switching between numbers of tabs (maximum - 3)
                switch (numberOfTabs) {
                    case 0:
                        break;
                    case 1:
                        first = response.body().getCategories().get(0).getLabel();
                        break;
                    case 2:
                        first = response.body().getCategories().get(0).getLabel();
                        second = response.body().getCategories().get(1).getLabel();
                        break;
                    case 3:
                        first = response.body().getCategories().get(0).getLabel();
                        second = response.body().getCategories().get(1).getLabel();
                        third = response.body().getCategories().get(2).getLabel();
                        break;
                }
                //open MainActivity
                getBeforeMain();
            }

            @Override
            public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {
                //starting MainActivity when failed to connect
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        });

    }



    //setting to get json file and parse it to models in case of cloak
     public void getJsonDataCloak(){
         Interface apiInterfaceCount = Initializator.getClient().create(Interface.class);
         Call<Data> call = apiInterfaceCount.getData(MainClass.REGION, MainClass.APPID);
         call.enqueue(new Callback<Data>() {
             @Override
             public void onResponse(@NonNull Call<Data> call, @NonNull Response<Data> response) {
                 assert response.body() != null;
                 listDataAll = response.body().getList();
                 isEmpty = response.body().getCategories().isEmpty();
                 numberOfTabs = response.body().getCategories().size();
                 listDataBad = response.body().getList();
                 listDataZero = response.body().getList();

                 //iterator for 2-nd tab (new list)
                 Iterator<Liste> x = listDataZero.iterator();
                 while (x.hasNext()){
                     Liste s = x.next();
                     if (s.getCategories().contains("zero")){
                         testDataList.add(s);
                     } else {
                         //x.remove();
                     }
                 }


                 //iterator for 3-rd tab (bad list)
                 Iterator<Liste> i = listDataBad.iterator();
                 while (i.hasNext()){
                     Liste m = i.next();
                     if (m.getCategories().contains("badCreditHistory")){
                         testDataList2.add(m);

                     } else {
                         //  i.remove();
                     }
                 }

                 //switching between numbers of tabs (maximum - 3)
                 switch (numberOfTabs) {
                     case 0:
                         break;
                     case 1:
                         first = response.body().getCategories().get(0).getLabel();
                         break;
                     case 2:
                         first = response.body().getCategories().get(0).getLabel();
                         second = response.body().getCategories().get(1).getLabel();
                         break;
                     case 3:
                         first = response.body().getCategories().get(0).getLabel();
                         second = response.body().getCategories().get(1).getLabel();
                         third = response.body().getCategories().get(2).getLabel();
                         break;
                 }
                 //open cloak
               getBeforeMain();
             }

             @Override
             public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {
                 //starting MainActivity when failed to connect
                 startActivity(new Intent(SplashActivity.this, MainActivity.class));
             }
         });

     }

    public void getDeepLink(){
        try {
            AppLinkData.fetchDeferredAppLinkData(getApplicationContext(), appLinkData -> {
                if (appLinkData == null || appLinkData.getTargetUri() == null) {
                    MainClass.subid1 = "failedFBDeepLink";
                    MainClass.subid2 = "failedFBDeepLink";
                    MainClass.subid3 = "failedFBDeepLink";
                } else {
                    MainClass.subid1 = appLinkData.getTargetUri().getQueryParameter("sub1");
                    MainClass.subid2 = appLinkData.getTargetUri().getQueryParameter("sub2");
                    MainClass.subid3 = appLinkData.getTargetUri().getQueryParameter("sub3");
                }
            });
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


}