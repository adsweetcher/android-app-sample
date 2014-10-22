package com.adsweetcher.ads;

import android.app.Activity;

import com.adsweetcher.service.SweetcherNetwork;
import com.chartboost.sdk.Chartboost;

public class ChartboostSweetcherNetwork extends SweetcherNetwork {

    private Activity activity;

    private Chartboost chartboost;

    private String appId;

    private String appSignature;

    public ChartboostSweetcherNetwork(Activity activity, String appId, String appSignature) {
        this.activity = activity;
        this.appId = appId;
        this.appSignature = appSignature;
    }


    @Override
    public String getId() {
        return "chartboost";
    }


    @Override
    public void onCreate() {

        chartboost = Chartboost.sharedChartboost();
        chartboost.onCreate(activity, appId, appSignature, null);
    }


    @Override
    public void onStart() {
        if (chartboost == null) {
            chartboost = Chartboost.sharedChartboost();
            chartboost.onCreate(activity, appId, appSignature, null);
        }

        this.chartboost.onStart(activity);

        // Show an interstitial. This and other interstital/MoreApps cache/show calls should be used after onStart().
        this.chartboost.showInterstitial();
    }


    @Override
    public void onResume() {
        if (chartboost == null) {
            onCreate();
            onStart();
        }
    }


    @Override
    public void onStop() {
        if (chartboost == null) return;
        chartboost.onStop(activity);
    }


    @Override
    public void onDestroy() {
        if (chartboost == null) return;
        chartboost.onDestroy(activity);
    }

}
