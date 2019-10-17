package com.zong.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zong.filter.FilterMenu;
import com.zong.filter.OnCallbackListener;
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
                new FilterMenu.Builder()
                        .setActivity(MainActivity.this)
                        .setListBeanList(categoryList)
                        .setRecover(true)
                        .setSelectOne(false)
                        .setCalbackListener(new OnCallbackListener() {
                            @Override
                            public void onComplete() {
                            }
                            @Override
                            public void onSelected(int posParent, int posChild) {
                            }
                            @Override
                            public void onUnSelected(int posParent, int posChild) {

                            }
                            @Override
                            public void onCancel() {
                            }
                        })
                        .show();


            }
        });

    }
}
