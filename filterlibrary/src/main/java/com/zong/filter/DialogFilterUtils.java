package com.zong.filter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.zong.filter.adapter.CategoryAdapter;
import com.zong.filter.bean.CategoryBean;
import java.util.List;
public class DialogFilterUtils {
    CategoryAdapter adapter;
    View mInflate;
    List<CategoryBean> listBeanList;

    /**
     * @param activity
     * @param listBeanList
     * @param isRecover        再次展开时是否恢复数据
     * @param isSelectOne      单选
     * @param callbackListener
     * @return
     */
    private AlertDialog filterDialog(Activity activity,
                                     List<CategoryBean> listBeanList,
                                     final boolean isRecover,
                                     boolean isSelectOne,
                                     final OnCallbackListener callbackListener) {
        mInflate = LayoutInflater.from(activity).inflate(R.layout.filter_dialog, null, false);
        final AlertDialog alertDialog = new AlertDialog.Builder(activity,
                R.style.DialogStyleRight).create();
        alertDialog.show();
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return false;
            }
        });

        final Window window = alertDialog.getWindow();
        window.setContentView(mInflate);
        initRecycle(activity, isSelectOne, callbackListener, listBeanList);
        window.setLayout(
                window.getContext().getResources().getDisplayMetrics().widthPixels,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.dialog_left_right_animation);
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.setCanceledOnTouchOutside(true);
        window.findViewById(R.id.view_line).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.hide();
                clearSelected();
            }
        });
        window.findViewById(R.id.btConfim).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (callbackListener != null) {
                            callbackListener.onComplete();
                        }
                        if (!isRecover) {
                            clearSelected();
                        }
                        alertDialog.hide();
                    }
                });
        window.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callbackListener != null) {
                    callbackListener.onCancel();
                }
                clearSelected();
            }
        });
        return alertDialog;
    }

    private static final String TAG = "DialogFilterUtils";

    private void clearSelected() {
        mInflate = null;
        for (CategoryBean categoryBean : listBeanList) {
            for (CategoryBean.ChildBean bean : categoryBean.getChild()) {
                bean.setIsCheck(false);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void initRecycle(Activity activity, boolean isSelectOne, OnCallbackListener listener, List<CategoryBean> list) {
        listBeanList = list;
        RecyclerView rv = mInflate.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new CategoryAdapter(activity, listBeanList, isSelectOne, listener);
        rv.setAdapter(adapter);
    }

}
