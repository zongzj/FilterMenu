package com.zong.filter;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

public class DialogFilterUtils {
    CategoryAdapter adapter;
    View mInflate;
    List<CategoryBean.ListBean> listBeanList;
    public AlertDialog filterDialog(Activity context,
                                    List<CategoryBean.ListBean> listBeanList,
                                    final View.OnClickListener confimlListener) {
        mInflate = LayoutInflater.from(context).inflate(R.layout.filter_dialog, null, false);
        final AlertDialog alertDialog = new AlertDialog.Builder(context,
                R.style.DialogStyleRight).create();
        alertDialog.show();
        final Window window = alertDialog.getWindow();


        window.setContentView(mInflate);
        initRecycle(context, listBeanList);
        window.setLayout(
                window.getContext().getResources().getDisplayMetrics().widthPixels,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.dialog_left_right_animation);
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.setCanceledOnTouchOutside(true);
//        window.findViewById(R.id.view_line).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.hide();
//                clearSelected();
//            }
//        });
        window.findViewById(R.id.btConfim).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (confimlListener != null) {
                            confimlListener.onClick(v);
                        }
                        alertDialog.hide();
                    }
                });
        window.findViewById(R.id.btnCancel).setOnClickListener(//清除
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearSelected();
                    }
                });
        return alertDialog;
    }

    public void clearSelected() {
        mInflate = null;
//        flowLayout.onChanged();//金额清空
//        for (int i = 0; i < listBeanList.size(); i++) {
//            listBeanList.get(i).setClear(true);
//        }
        listBeanList = new ArrayList<>();
        adapter.notifyDataSetChanged();
    }

    public void initRecycle(Activity context, List<CategoryBean.ListBean> list) {
        listBeanList = list;
        RecyclerView rv = mInflate.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(context));
        adapter = new CategoryAdapter(listBeanList);
        rv.setAdapter(adapter);
    }

    private final String TAG = "DialogFilterUtils";
}
