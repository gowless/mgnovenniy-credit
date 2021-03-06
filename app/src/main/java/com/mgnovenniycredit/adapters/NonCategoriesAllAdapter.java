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
import com.mgnovenniycredit.MainClass;
import com.mgnovenniycredit.R;
import com.mgnovenniycredit.activities.MainActivity;
import com.mgnovenniycredit.activities.SplashActivity;
import com.mgnovenniycredit.models.post.get.Liste;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


public class NonCategoriesAllAdapter extends RecyclerView.Adapter<NonCategoriesAllAdapter.ViewHolder> {
    public static String campaign, campaign_id, creative_id, creative, adgroup, adgroup_id, string;


    private FirebaseAnalytics mFirebaseAnalytics;

    Context context;
    List<Liste> dataList;

    public void setDataList(List<Liste> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }



    public NonCategoriesAllAdapter(Context context, List<Liste> dataList) {
        this.context = context;
        this.dataList = dataList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (MainClass.font > 1 & MainClass.font <= 1.25){
            if (viewType == 1) {
                view = inflater.inflate(R.layout.container_main, parent, false);
            } else {
                view = inflater.inflate(R.layout.container_main, parent, false);
            }
        } else if (MainClass.font >= 1.3){
            if (viewType == 1) {
                view = inflater.inflate(R.layout.container_main, parent, false);
            } else {
                view = inflater.inflate(R.layout.container_main, parent, false);
            }
        } else {
            if (viewType == 1) {
                view = inflater.inflate(R.layout.container_main, parent, false);
            } else {
                view = inflater.inflate(R.layout.container_main, parent, false);
            }
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
        holder.firstCreditSum.setText(firstCreditSum + "₽");
        holder.percentRate.setText(percentRate + "%");
        holder.timeToGet.setText(dataList.get(position).getTimeSolution().getFrom().toString()+" минут");
        //holder.payLoanTime.setText("от " + dataList.get(position).getTerm().getFrom().toString() + " до " + dataList.get(position).getTerm().getTo().toString() +" дней");
        holder.payLoanTime.setText(dataList.get(position).getTerm().getFrom().toString() + "-" + dataList.get(position).getTerm().getTo().toString()+ " дней");
        holder.nextCreditSum.setText(dataList.get(position).getAmount().getTo().toString()+"₽");


        //setting image holder with glide
        Glide.with(context)
                .load(dataList.get(position)
                        .getImg())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
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


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        //declaring items
        ProgressBar progressBarGlide;
      //  ConstraintLayout click_layout;
        TextView firstCreditSum, percentRate, timeToGet, payLoanTime, nextCreditSum;;
        ImageView imgCompany;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //initializing
            progressBarGlide = itemView.findViewById(R.id.progressBarGlide);
          //  click_layout = itemView.findViewById(R.id.click_layout);
            button = itemView.findViewById(R.id.button);
            imgCompany = itemView.findViewById(R.id.imgCompany);
            firstCreditSum = itemView.findViewById(R.id.firstCreditSum);
            percentRate = itemView.findViewById(R.id.percentRate);
            timeToGet = itemView.findViewById(R.id.timeToGet);
            payLoanTime = itemView.findViewById(R.id.payLoanTime);
            nextCreditSum = itemView.findViewById(R.id.nextCreditSum);


        }
    }

    @Override
    public int getItemViewType(int position) {

        if (dataList.get(position).getTop() == null || dataList.get(position).getTop()) {
            return 1;
        } else {
            return 2;
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
        adgroup = SplashActivity.adg.substring(0, SplashActivity.adg.indexOf("(") - 1);
        adgroup_id = SplashActivity.adg.substring(SplashActivity.adg.indexOf("(") + 1, SplashActivity.adg.indexOf(")"));

        //creative
        creative = SplashActivity.cre.substring(0, SplashActivity.cre.indexOf("(") - 1);
        creative_id = SplashActivity.cre.substring(SplashActivity.cre.indexOf("(") + 1, SplashActivity.cre.indexOf(")"));

        //campaing
        campaign = SplashActivity.cam.substring(0, SplashActivity.cam.indexOf("(") - 1);
        campaign_id = SplashActivity.cam.substring(SplashActivity.cam.indexOf("(") + 1, SplashActivity.cam.indexOf(")"));

    }

    public void googleAdsGetData() {
        campaign = SplashActivity.cam.substring(0, SplashActivity.cam.indexOf("(") - 1);
        campaign_id = SplashActivity.cam.substring(SplashActivity.cam.indexOf("(") + 1, SplashActivity.cam.indexOf(")"));

    }


    public String parseLinkFromApi(int position) {

        // https://tds.pdl-profit.com?affid=18827&offer_id=1158&subid={client_id}&subid2={advertising_id}&subid3={app}&utm_source={source}&utm_campaign={campaign}&utm_adgroup={adgroup}&utm_adposition={adset}&utm_creative={chanel}"
        final Liste liste = dataList.get(position);
        //Main URI declaring and initialising
        String mainEditedURI = liste.getUrl();
        //manipulating with main string, changing parameters
        mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{client_id}"), MainActivity.subid1);
        mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{advertising_id}"), MainActivity.subid2);
        mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{app}"), MainActivity.subid3);

        //if organic/non-organic campaign
        if (SplashActivity.net.equals("Organic")) {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{source}"), "organic");
        } else if (SplashActivity.net.equals("Unattributed")) {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{source}"), "unattributed");
        } else {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{source}"), SplashActivity.net);
        }


        //if organic/non-organic campaign
        if (SplashActivity.cam == "") {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{campaign}"), "organic");
        } else {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{campaign}"), SplashActivity.cam);

        }


        //if organic/non-organic adgroup
        if (SplashActivity.adg == "") {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{adgroup}"), "organic");
        } else {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{adgroup}"), SplashActivity.adg);
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
