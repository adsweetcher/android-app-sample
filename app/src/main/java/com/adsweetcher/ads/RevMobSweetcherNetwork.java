package com.adsweetcher.ads;

import android.app.Activity;

import com.adsweetcher.service.SweetcherNetwork;
import com.revmob.RevMob;

public class RevMobSweetcherNetwork extends SweetcherNetwork {

    private Activity activity;

    public RevMobSweetcherNetwork(Activity activity) {
        this.activity = activity;
    }

    private RevMob revmob;

    @Override
    public String getId() {
        return "revmob";
    }


    @Override
    public void onCreate() {
        create();
    }

    private void create() {
        revmob = RevMob.start(activity);
        revmob.showFullscreen(activity);
    }


    @Override
    public void onStart() {
    }


    @Override
    public void onResume() {
        if (revmob == null) {
            create();
        }
    }


    @Override
    public void onStop() {
        revmob = null;
    }
}
