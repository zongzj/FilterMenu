package com.zong.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zong.filter.CategoryBean;
import com.zong.filter.DialogFilterFragment;
import com.zong.filter.DialogFilterUtils;
import com.zong.filter.GetJsonDataUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String JsonData = new GetJsonDataUtil().getJson(this, "menu.json");//获取assets目录下的json文件数据
        final DialogFilterUtils dialogFilterUtils = new DialogFilterUtils();
        //注意:数据初始化一次就好. 不然菜单选择数据会清空
        final List<CategoryBean> categoryList = new Gson().fromJson(JsonData, new TypeToken<List<CategoryBean>>() {
        }.getType());
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFilterUtils.filterDialog(MainActivity.this, categoryList, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (CategoryBean bean : categoryList) {
                            for (CategoryBean.ChildBean childBean : bean.getChild()) {
                                if (childBean.isIsCheck()) {
                                    Log.i(TAG, "选中id: " + childBean.getId());
                                    Log.i(TAG, "选中名称: " + childBean.getTypename());
                                }
                            }
                        }

                    }
                });

            }
        });

    }
}
