package com.zong.filter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String JsonData = new GetJsonDataUtil().getJson(this, "menu.json");//获取assets目录下的json文件数据
        DialogFilterUtils dialogFilterUtils = new DialogFilterUtils();
        CategoryBean mList = new Gson().fromJson(JsonData, CategoryBean.class);
        dialogFilterUtils.filterDialog(this, mList.getList(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
