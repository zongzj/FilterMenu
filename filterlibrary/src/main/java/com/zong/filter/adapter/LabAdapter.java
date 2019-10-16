package com.zong.filter.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zong.filter.bean.CategoryBean;
import com.zong.filter.R;

import java.util.List;

public class LabAdapter extends TagAdapter {
    Activity activity;
    public LabAdapter(List<CategoryBean.ChildBean> datas, Activity activity) {
        super(datas);
        this.activity=activity;
    }

    @Override
    public View getView(FlowLayout parent, int position, Object o) {
        TextView tv = (TextView) LayoutInflater.from(activity).inflate(R.layout.filter_flow_tv, parent, false);
        CategoryBean.ChildBean bean= (CategoryBean.ChildBean)o;
        tv.setText(bean.getTypename()+"");
        return tv;
    }
}
