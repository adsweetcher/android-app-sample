AndroidAppSample
================

This app present an example of using Adseewtcher API from http://adsweetcher.com.

Main classes here

 * SampleActivity - main activity
 * SweetcherHttpClient - Http client that is used to query Adsweetcher API
 * SweetcherNetwork - interface for advertisement network (see implementations AdMobSweetcherNetwork, ChartboostSweetcherNetwork,  RevMobSweetcherNetwork)
 * SweetcherMgr - core logic that handles switching advertisement in application
 
Ad networks are configure mostly in  SampleActivity and AndroidManifest.xml.
SampleActivity.java:

    private final String urlToRead = "http://adsweetcher.com/api/token/userid/<ADSWEETCHER_APP_TOKEN>";

    SweetcherMgr switcherMgr = SweetcherMgr.getInstance(
            urlToRead,
            new AdMobSweetcherNetwork(this, "<ADMOB_ADUNIT_ID_HERE>"),
            new RevMobSweetcherNetwork(this),
            new ChartboostSweetcherNetwork(this, "<CHARTBOOST_APP_ID>", "<CHARTBOOST_APP_SIGNATURE>")
    );

AndroidManifest.xml:

    <meta-data android:name="com.revmob.app.id" android:value="<REVMOB_APP_ID>"/>

You would need to obtain tokens and replace :

 * ADSWEETCHER_APP_TOKEN - Adsweetcher token. can be obtained from Adsweetcher.com in application token section
 * ADMOB_ADUNIT_ID_HERE - this value is obtained from Admob
 * CHARTBOOST_APP_ID, CHARTBOOST_APP_SIGNATURE  - these value are obtained from Chartboost
 * REVMOB_APP_ID - application id is obtained form Revmob

