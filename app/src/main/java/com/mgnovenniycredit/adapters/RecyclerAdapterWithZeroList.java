package com.mgnovenniycredit.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mgnovenniycredit.R;
import com.mgnovenniycredit.activities.MainActivity;
import com.mgnovenniycredit.activities.SplashActivity;
import com.mgnovenniycredit.models.get.Liste;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


public class  RecyclerAdapterWithZeroList extends RecyclerView.Adapter< RecyclerAdapterWithZeroList.ViewHolder> {
    public static String campaign, campaign_id, creative_id, creative, adgroup, adgroup_id, string;


    private FirebaseAnalytics mFirebaseAnalytics;

    Context context;

    public void setDataList(List<Liste> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    List<Liste> dataList;

    public RecyclerAdapterWithZeroList(Context context, List<Liste> dataList) {
        this.context = context;
        this.dataList = dataList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == 1) {
            view = inflater.inflate(R.layout.container_main, parent, false);
        } else if (viewType == 2){
            view = inflater.inflate(R.layout.container_main_second, parent, false);
        } else if(viewType == 3){
            view = inflater.inflate(R.layout.container_main_third, parent, false);
        } else {
            view = inflater.inflate(R.layout.container_main_non_badge, parent, false);
        }
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Liste liste = dataList.get(position);


        String firstCreditSum = liste.getAmount().getFrom().toString();
        String percentRate = liste.getPercent().getFrom().toString();

        //setting holders to textViews
        holder.firstCreditSum.setText(firstCreditSum + "₴");
        holder.percentRate.setText(percentRate + "%");


        //setting image holder with glide
        Glide.with(context)
                .load(dataList.get(position)
                        .getImg())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBarGlide.setIndeterminate(false);
                        holder.progressBarGlide.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                       holder.progressBarGlide.setIndeterminate(false);
                       holder.progressBarGlide.setVisibility(View.GONE);
                        return false;
                    }
                })
                .centerInside()
                .into(holder.imgCompany);


     /*   holder.click_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AdjustEvent adjustEvent = new AdjustEvent("8q9nkq");
                Adjust.trackEvent(adjustEvent);


                mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);

                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, liste.getOfferId().toString());
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, liste.getOfferName());
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_CART, bundle);

                //starting default web-browser to current tab wit main URL
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(parseLinkFromApi(position)));
                v.getContext().startActivity(browserIntent);
            }
        }); */

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        //declaring items
        ProgressBar progressBarGlide;
        TextView firstCreditSum, percentRate;
        ImageView imgCompany;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //initializing
            progressBarGlide = itemView.findViewById(R.id.progressBarGlide);
           // click_layout = itemView.findViewById(R.id.click_layout);
            button = itemView.findViewById(R.id.button);
            imgCompany = itemView.findViewById(R.id.imgCompany);
            firstCreditSum = itemView.findViewById(R.id.firstCreditSum);
            percentRate = itemView.findViewById(R.id.percentRate);

        }
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return 1;
        } else if (position == 1){
            return 2;
        } else if (position == 2){
            return 3;
        } else {
            return 4;
        }
    }



    //getting current time for post request
    public String getCurrentTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("HH-mm-ss-dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    public String getId() {


        @SuppressLint("HardwareIds")
        String android_id = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return android_id;
    }


    //change variables
    public void fbAdsGetData() {
        //adgroup
        adgroup = MainActivity.adg.substring(0, MainActivity.adg.indexOf("(") - 1);
        adgroup_id = MainActivity.adg.substring(MainActivity.adg.indexOf("(") + 1, MainActivity.adg.indexOf(")"));

        //creative
        creative = MainActivity.cre.substring(0, MainActivity.cre.indexOf("(") - 1);
        creative_id = MainActivity.cre.substring(MainActivity.cre.indexOf("(") + 1, MainActivity.cre.indexOf(")"));

        //campaing
        campaign = MainActivity.cam.substring(0, MainActivity.cam.indexOf("(") - 1);
        campaign_id = MainActivity.cam.substring(MainActivity.cam.indexOf("(") + 1, MainActivity.cam.indexOf(")"));

    }

    public void googleAdsGetData() {
        campaign = MainActivity.cam.substring(0, MainActivity.cam.indexOf("(") - 1);
        campaign_id = MainActivity.cam.substring(MainActivity.cam.indexOf("(") + 1, MainActivity.cam.indexOf(")"));

    }


    public String parseLinkFromApi(int position) {

        // https://tds.pdl-profit.com?affid=18827&offer_id=1158&subid={client_id}&subid2={advertising_id}&subid3={app}&utm_source={source}&utm_campaign={campaign}&utm_adgroup={adgroup}&utm_adposition={adset}&utm_creative={chanel}"
        final Liste liste = dataList.get(position);
        //Main URI declaring and initialising
        String mainEditedURI = liste.getUrl();
        //manipulating with main string, changing parameters
        mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{client_id}"), getId());
        mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{advertising_id}"), SplashActivity.ad_id);
        mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{app}"), "com.orkotkreditru");

        //if organic/non-organic campaign
        if (MainActivity.net.equals("Organic")) {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{source}"), "organic");
        } else if (MainActivity.net.equals("Unattributed")) {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{source}"), "unattributed");
        } else {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{source}"), MainActivity.net);
        }


        //if organic/non-organic campaign
        if (MainActivity.cam == "") {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{campaign}"), "organic");
        } else {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{campaign}"), MainActivity.cam);

        }


        //if organic/non-organic adgroup
        if (MainActivity.adg == "") {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{adgroup}"), "organic");
        } else {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{adgroup}"), MainActivity.adg);
        }


        //if organic/non-organic adset
        mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{adset}"), "organic");

        //if organic/non-organic chanel
        mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{chanel}"), "organic");

        //if organic/non-organic chanel
        mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{geo}"), "ru");

        Log.d("FINISH", mainEditedURI);
        return mainEditedURI;
    }
}
