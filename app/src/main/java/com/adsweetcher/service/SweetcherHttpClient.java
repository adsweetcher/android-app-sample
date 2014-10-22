package com.adsweetcher.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SweetcherHttpClient {

    // pattern for JSON like : {'ad' : 'admob'}
    private static final Pattern AD = Pattern.compile("\\\"ad\\\"\\s*:\\s*\\\"(\\S*)\\\"");

    public static final int MAX_RESPONSE_SIZE = 256;

    private int connectionTimeout = 500;

    private int readTimeout = 500;

    private String urlToRead;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private Callable<String> callable = new Callable<String>() {
        @Override
        public String call() throws Exception {
            return doGetAd();
        }
    };

    public SweetcherHttpClient(String urlToRead) {
        this.urlToRead = urlToRead;
    }

    public String getAd() {
        try {
            return executorService.submit(callable).get();
        } catch (Exception e) {
            return "";
        }
    }

    public String doGetAd() {

        BufferedReader rd = null;
        HttpURLConnection conn = null;
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(connectionTimeout);
            conn.setReadTimeout(readTimeout);
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
                if (result.length() > MAX_RESPONSE_SIZE) {
                    // LOG here
                    return "";
                }
            }

            Matcher matcher = AD.matcher(result);
            if (matcher.find()) {
                String ad = matcher.group(1);
                return ad;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rd != null) {
                    rd.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }


    public void setConnectionTimeoutMillis(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setReadTimeoutMillis(int readTimeout) {
        this.readTimeout = readTimeout;
    }

}
