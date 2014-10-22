package com.adsweetcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.adsweetcher.ads.AdMobSweetcherNetwork;
import com.adsweetcher.ads.ChartboostSweetcherNetwork;
import com.adsweetcher.ads.RevMobSweetcherNetwork;
import com.adsweetcher.service.SweetcherMgr;

public class SampleActivity extends Activity {

    private final String urlToRead = "http://adsweetcher.com/api/token/userid/<ADSWEETCHER_APP_TOKEN>";

    SweetcherMgr switcherMgr = SweetcherMgr.getInstance(
            urlToRead,
            new AdMobSweetcherNetwork(this, "<ADMOB_ADUNIT_ID_HERE>"),
            new RevMobSweetcherNetwork(this),
            new ChartboostSweetcherNetwork(this, "<CHARTBOOST_APP_ID>", "<CHARTBOOST_APP_SIGNATURE>")
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        switcherMgr.init();
        switcherMgr.onCreate();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResume() {
        super.onResume();
        switcherMgr.onResume();

    }


    @Override
    public void onPause() {
        super.onPause();
        switcherMgr.onPause();
    }


    @Override
    protected void onStart() {
        super.onStart();

        switcherMgr.onStart();
    }


    @Override
    protected void onStop() {
        super.onStop();
        switcherMgr.onStop();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        switcherMgr.onDestroy();
    }
}
