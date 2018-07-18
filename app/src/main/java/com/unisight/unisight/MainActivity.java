package com.unisight.unisight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private TextView weatherInfoText;
    private ListView categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherInfoText = findViewById(R.id.textview_weather_info);
        categoryList = findViewById(R.id.listview_category);

        weatherInfoText.setContentDescription("잠실동 오후 2:34, 비가 조금 내리고, 미세먼지 농도가 다소 높으니 외출시 마스크를 꼭 착용하세요");

        ArrayList<DynamicListViewItem> data = new ArrayList<>();
        data.add(new DynamicListViewItem("상세 날씨", "기온, 강수량, 미세먼지 농도 등 상세한 날씨 정보를 확인합니다"));
        data.add(new DynamicListViewItem("하이라이트 뉴스", "이 시각 주요뉴스를 조회합니다"));
        data.add(new DynamicListViewItem("유용한 앱", "전체 0개"));

        DynamicListViewAdapter adapter = new DynamicListViewAdapter(this, data);

        categoryList.setAdapter(adapter);
        categoryList.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_support:
                Toast.makeText(this, "카카오톡 플러스친구로 상담을 요청합니다 (준비중)", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Intent weatherIntent = new Intent(this, WeatherActivity.class);
                startActivity(weatherIntent);
                break;
            case 1:
                Intent newsIntent = new Intent(this, NewsActivity.class);
                startActivity(newsIntent);
                break;
            case 2:
                break;
            default:
                break;
        }
    }
}