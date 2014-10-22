package com.adsweetcher.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SweetcherMgr {

    private static SweetcherMgr self;

    private SweetcherNetwork current;

    private SweetcherHttpClient sweetcherHttpClient;

    private List<SweetcherNetwork> sweetcherNetworks = new ArrayList<SweetcherNetwork>();

    public SweetcherMgr(String urlToRead, SweetcherNetwork... networks) {
        if (networks.length < 1) throw new IllegalArgumentException("No networks have been passed");
        this.sweetcherHttpClient = new SweetcherHttpClient(urlToRead);
        this.sweetcherNetworks = Arrays.asList(networks);
        this.current = sweetcherNetworks.get(0);
    }

    public static SweetcherMgr getInstance(String urlToRead, SweetcherNetwork... networks) {
        if (self == null) {
            self = new SweetcherMgr(urlToRead, networks);
        }
        return self;
    }

    protected void updateCurrent() {
        String ad = sweetcherHttpClient.getAd();
        for (SweetcherNetwork sweetcherNetwork : sweetcherNetworks) {
            if (sweetcherNetwork.getId().equals(ad)) {
                current = sweetcherNetwork;
            }
        }
    }


    public void init() {
        for (SweetcherNetwork sweetcherNetwork : sweetcherNetworks) {
            sweetcherNetwork.init();
        }
    }


    public void onCreate() {
        updateCurrent();
        current.onCreate();
    }


    public void onDestroy() {
        current.onDestroy();
    }


    public void onPause() {
        current.onDestroy();
    }


    public void onResume() {
        updateCurrent();
        current.onResume();
    }


    public void onStart() {
        current.onStart();
    }


    public void onStop() {
        current.onStop();
    }
}
