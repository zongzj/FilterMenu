package com.zong.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zong.filter.bean.CategoryBean;
import com.zong.filter.DialogFilterUtils;
import com.zong.filter.GetJsonDataUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = (TextView) findViewById(R.id.tv);
        String JsonData = new GetJsonDataUtil().getJson(this, "menu.json");//获取assets目录下的json文件数据
        final DialogFilterUtils dialogFilterUtils = new DialogFilterUtils();
        //注意:数据初始化一次就好. 不然菜单选择数据会清空
        //注意:CategoryBean.ChildBean添加字段isCheck 用于标记选中状态
        final List<CategoryBean> categoryList = new Gson().fromJson(JsonData, new TypeToken<List<CategoryBean>>() {
        }.getType());
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFilterUtils.filterDialog(MainActivity.this, categoryList, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StringBuilder s = new StringBuilder();
                        for (CategoryBean bean : categoryList) {
                            for (CategoryBean.ChildBean childBean : bean.getChild()) {
                                if (childBean.isIsCheck()) {
                                    Log.i(TAG, "选中id: " + childBean.getId());
                                    Log.i(TAG, "选中名称: " + childBean.getTypename());
                                    s.append("选中id: " + childBean.getId() + "选中名称: " + childBean.getTypename());
                                }
                            }
                        }
                        tv.setText(s);
                    }
                });

            }
        });

    }
}
