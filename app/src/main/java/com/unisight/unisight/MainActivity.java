package com.unisight.unisight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private TextView weatherInfoText;
    private Button supportButton;
    private ListView categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherInfoText = findViewById(R.id.textview_weather_info);
        supportButton = findViewById(R.id.button_support);
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
    protected void onResume() {
        super.onResume();

        NewsParser newsTask = new NewsParser();
        newsTask.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_SHORT).show();
    }
}