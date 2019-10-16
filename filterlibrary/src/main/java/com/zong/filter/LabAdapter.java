package com.zong.filter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

public class LabAdapter extends TagAdapter {
    Activity activity;
    public LabAdapter(Object[] datas, Activity activity) {
        super(datas);
        this.activity=activity;
    }

    @Override
    public View getView(FlowLayout parent, int position, Object o) {
        TextView tv = (TextView) LayoutInflater.from(activity).inflate(R.layout.filter_flow_tv, parent, false);
        tv.setText((String)o);
        return tv;
    }
}
