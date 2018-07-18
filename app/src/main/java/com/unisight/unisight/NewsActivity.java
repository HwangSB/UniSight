package com.unisight.unisight;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class NewsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private boolean firstLoadNews;
    private ArrayList<DynamicListViewItem> data;
    private DynamicListViewAdapter adapter;
    private ListView contentsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.highlight_news);
        }

        firstLoadNews = true;
        data = new ArrayList<>();
        adapter = new DynamicListViewAdapter(this, data);

        contentsList = findViewById(R.id.listview_contents);
        contentsList.setAdapter(adapter);
        contentsList.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (firstLoadNews) {
            refreshNews();
            firstLoadNews = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu_news, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                refreshNews();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void refreshNews() {
        data.clear();

        NewsParser newsTask = new NewsParser();
        List<String> newsTitleList = null;

        try {
            newsTitleList = newsTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if (newsTitleList != null) {
            for (String newsTitle : newsTitleList) {
                data.add(new DynamicListViewItem(newsTitle, ""));
            }
            adapter.notifyDataSetChanged();
        }
    }
}
