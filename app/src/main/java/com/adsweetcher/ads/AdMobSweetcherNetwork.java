package com.adsweetcher.ads;

import android.app.Activity;
import android.widget.LinearLayout;

import com.adsweetcher.R;
import com.adsweetcher.service.SweetcherNetwork;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class AdMobSweetcherNetwork extends SweetcherNetwork {

    private String adUnitId;

    private Activity activity;

    private AdView adView;

    public AdMobSweetcherNetwork(Activity activity, String adUnitId) {
        this.activity = activity;
        this.adUnitId = adUnitId;
    }


    @Override
    public String getId() {
        return "admob";
    }


    @Override
    public void init() {
        adView = new AdView(activity);
    }


    @Override
    public void onCreate() {

        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(adUnitId);

        // Add the AdView to the view hierarchy. The view will have no size
        // until the ad is loaded.
        LinearLayout layout = getLinearLayout();
        layout.addView(adView);

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device.
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        adView.loadAd(adRequest);

    }


    private LinearLayout getLinearLayout() {
        return (LinearLayout) activity.findViewById(R.id.linearLayout);
    }


    @Override
    public void onResume() {
        if (adView != null) {
            adView.resume();
        } else {
            init();
            this.onCreate();
        }
    }


    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
            onDestroy();
            adView = null;
        }
    }


    @Override
    public void onDestroy() {
        // Destroy the AdView.
        if (adView != null) {
            adView.destroy();
            getLinearLayout().removeView(adView);
            adView = null;
        }

    }
}
