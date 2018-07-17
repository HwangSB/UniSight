package com.unisight.unisight;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsParser extends AsyncTask<Void, Void, List<String>> {
    @Override
    protected List<String>doInBackground(Void... voids) {
        List<String> result = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("http://news.naver.com").get();
            Elements today_main_news = doc.select(".newsnow_tx_inner");
            for (Element element : today_main_news) {
                Log.d("DEBUG-e", element.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}