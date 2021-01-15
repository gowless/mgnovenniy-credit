package com.mgnovenniycredit;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustAttribution;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.LogLevel;
import com.adjust.sdk.OnAttributionChangedListener;
import com.facebook.applinks.AppLinkData;
import com.onesignal.OneSignal;


public class MainClass extends Application {

    //initializing variables
    public static String trackerToken, trackerName, network, campaign, adgroup, creative, adid, suid1, suid2, suid3;
    public static Float font;
    //one-signal app id
    private static final String ONESIGNAL_APP_ID = "3c1ab221-301c-429a-a2bc-096f2f2da60b";

    public static final String APPID = "com.mgnovenniycredit";
    public static final String REGION = "ru";

    //fb attribution subid method
    private void getFBAtts(){
        SharedPreferences settings = getSharedPreferences("LOCAL", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        //checking on first launch
        if (settings.contains("firstlaunch")){

        } else {
            //switch case
            switch (settings.getString("network", "")) {
                case "Facebook Installs":
                    //configure applink facebook SDK
                    AppLinkData.fetchDeferredAppLinkData(this,
                            appLinkData -> {
                                try {
                                    suid1 = appLinkData.getTargetUri().getQueryParameter("sub1");
                                    Log.d("test", appLinkData.getTargetUri().toString());
                                    suid2 = appLinkData.getTargetUri().getQueryParameter("sub2");
                                    suid3 = appLinkData.getTargetUri().getQueryParameter("sub3");
                                    editor.putString("sub1", suid1);
                                    editor.putString("sub2", suid2);
                                    editor.putString("sub3", suid3);
                                    editor.apply();
                                } catch (Exception e) {
                                    suid1 = "brokendeep";
                                    suid2 = "brokendeep";
                                    suid3 = "brokendeep";
                                    editor.putString("sub1", suid1);
                                    editor.putString("sub2", suid2);
                                    editor.putString("sub3", suid3);
                                    editor.apply();
                                }
                            }
                    );

                    break;

                case "Google Ads UAC":
                    suid1 = "uac";
                    suid2 = "uac";
                    suid3 = "uac";
                    editor.putString("sub1", suid1);
                    editor.putString("sub2", suid2);
                    editor.putString("sub3", suid3);
                    editor.apply();
                    break;

                case "Organic":
                    suid1 = "organic";
                    suid2 = "organic";
                    suid3 = "organic";
                    editor.putString("sub1", suid1);
                    editor.putString("sub2", suid2);
                    editor.putString("sub3", suid3);
                    editor.apply();
                    break;

                case "Unattributed":
                    suid1 = "unattributed";
                    suid2 = "unattributed";
                    suid3 = "unattributed";
                    editor.putString("sub1", suid1);
                    editor.putString("sub2", suid2);
                    editor.putString("sub3", suid3);
                    editor.apply();
                    break;


            }
        }

        //adding tag of first launch
        if(settings.contains("firstlaunch")){

        } else {
            editor.putInt("firstlaunch", 1);
            editor.apply();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Configure adjust SDK.
        String appToken = "yebqteksfvnk";
        String environment = AdjustConfig.ENVIRONMENT_SANDBOX;
        AdjustConfig config = new AdjustConfig(this, appToken, environment);
        // enable all logs
        config.setLogLevel(LogLevel.VERBOSE);
        font = getResources().getConfiguration().fontScale;
        config.setOnAttributionChangedListener(new OnAttributionChangedListener() {
            @Override
            public void onAttributionChanged(AdjustAttribution attribution) {
                SharedPreferences settings = getSharedPreferences("LOCAL", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                trackerToken = attribution.trackerToken;
                trackerName = attribution.trackerName;
                network = attribution.network;
                campaign = attribution.campaign;
                adgroup = attribution.adgroup;
                creative = attribution.creative;
                adid = attribution.adid;


                //put to sharedprefs
                editor.putString("trackerToken", trackerToken);
                Log.d("adjust", network);
                editor.putString("trackerName", trackerName);
                editor.putString("network", network);
                editor.putString("campaign", campaign);
                editor.putString("adgroup", adgroup);
                editor.putString("creative", creative);
                editor.putString("adid", adid);
                editor.apply();
            }
        });
        Adjust.onCreate(config);

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        //calling method to get fb attribution
        getFBAtts();

        registerActivityLifecycleCallbacks(new AdjustLifecycleCallbacks());

    }

    private static final class AdjustLifecycleCallbacks implements ActivityLifecycleCallbacks {
        @Override
        public void onActivityPreCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityPostCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityPreStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPostStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPreResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {
            Adjust.onResume();
        }

        @Override
        public void onActivityPostResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPrePaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {
            Adjust.onPause();
        }

        @Override
        public void onActivityPostPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPreStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPostStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPreSaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityPostSaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityPreDestroyed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPostDestroyed(@NonNull Activity activity) {

        }

    }


}
