package com.zong.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zong.filter.FilterMenu;
import com.zong.filter.callback.OnCallbackListener;
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

        //右侧
        findViewById(R.id.btn_r).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FilterMenu.Builder()
                        .setActivity(MainActivity.this)
                        .setListBeanList(categoryList)
                        .setRecover(true)//是否恢复数据
                        .setCalbackListener(new OnCallbackListener() {
                            @Override
                            public void onComplete() {
                                //点击完成按钮
                                StringBuilder stringBuilder = new StringBuilder();
                                for (CategoryBean categoryBean : categoryList) {
                                    for (CategoryBean.ChildBean childBean : categoryBean.getChild()) {
                                        if (childBean.isIsCheck()) {
                                            stringBuilder.append(childBean.getTypename());
                                        }
                                    }
                                }
                                tv.setText(stringBuilder);
                            }

                            @Override
                            public void onSelected(int posParent, int posChild) {
                                CategoryBean categoryBean = categoryList.get(posParent); //选中的父类别
                                CategoryBean.ChildBean childBean = categoryBean.getChild().get(posChild); //选中的子类别
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

        final List<CategoryBean> categoryList2 = new Gson().fromJson(JsonData, new TypeToken<List<CategoryBean>>() {
        }.getType());

        //底部
        findViewById(R.id.btn_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FilterMenu.Builder()
                        .setActivity(MainActivity.this)
                        .setListBeanList(categoryList2)
                        .setRecover(true)
                        .setFromBottom(true)//底部弹出
                        .setCalbackListener(new OnCallbackListener() {
                            @Override
                            public void onComplete() {
                                //点击完成按钮
                                StringBuilder stringBuilder = new StringBuilder();
                                for (CategoryBean categoryBean : categoryList2) {
                                    for (CategoryBean.ChildBean childBean : categoryBean.getChild()) {
                                        if (childBean.isIsCheck()) {
                                            stringBuilder.append(childBean.getTypename());
                                        }
                                    }
                                }
                                tv.setText(stringBuilder);

                            }

                            @Override
                            public void onSelected(int posParent, int posChild) {
                                CategoryBean categoryBean = categoryList2.get(posParent); //选中的父类别
                                CategoryBean.ChildBean childBean = categoryBean.getChild().get(posChild); //选中的子类别
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
