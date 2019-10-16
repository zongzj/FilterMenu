package com.zong.filter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.HashSet;
import java.util.List;


public class CategoryAdapter extends BaseQuickAdapter<CategoryBean.ListBean, BaseViewHolder> {


    public CategoryAdapter( List<CategoryBean.ListBean> data) {
        super(R.layout.item_lab_title, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryBean.ListBean item) {
        helper.setText(R.id.tvTitle, item.getTypename());
        final TagFlowLayout flowLayout = helper.getView(R.id.flowlayout);//子类别
        final List<CategoryBean.ListBean.ChildBean> child = item.getChild();
        int size = child.size();
        final String[] labs = new String[size];
        for (int i = 0; i < labs.length; i++) {
            labs[i] = child.get(i).getTypename();

        }

        LabAdapter labAdapter = new LabAdapter(labs, (Activity) mContext);
        flowLayout.setAdapter(labAdapter);
        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                int id = child.get(position).getId();
//                if (ParamsBean.ID_SET != null) {
//                    if (!ParamsBean.ID_SET.isEmpty() && ParamsBean.ID_SET.contains(id)) {
//                        ParamsBean.ID_SET.remove(id);
//                    } else {
//                        ParamsBean.ID_SET.add(id);
//                    }
//
//                }
                return false;
            }
        });


//        if (item.isRecorver()) {
//            HashSet<Integer> id=new HashSet<>();
//            for (int i = 0; i <  child.size(); i++) {
//                if (ParamsBean.ID_SET.contains(child.get(i).getId())) {
//                    id.add(i);
//
//                }
//            }
//            labAdapter.setSelectedList(id);
//        }
//
        final TextView tvAll = helper.getView(R.id.tvAll);
//        if (item.isClear()) {
//            flowLayout.onChanged();
//            ParamsBean.ID_SET = new HashSet<>();
//
//        }

        if (size >= 8) {//全部展示,隐藏按钮
            tvAll.setVisibility(View.VISIBLE);
            tvAll.setText("全部");
            for (int i = 0; i < labs.length; i++) {//折叠
                if (i > 7) {
                    flowLayout.getChildAt(i).setVisibility(View.GONE);
                }
            }
        } else {
            tvAll.setVisibility(View.GONE);

        }
        tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int h = 0;
                if (tvAll.getText().toString().equals("全部")) {
                    for (int i = 0; i < labs.length; i++) {//展开
                        flowLayout.getChildAt(i).setVisibility(View.VISIBLE);
                        tvAll.setText("收起");
                        h = h + flowLayout.getChildAt(i).getHeight();
                    }
//                    flowLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, h));
//                    flowLayout.requestLayout();
                } else {
                    tvAll.setText("全部");
                    for (int i = 0; i < labs.length; i++) {//折叠
                        if (i > 7) {
                            flowLayout.getChildAt(i).setVisibility(View.GONE);
                        }
                    }
                }
            }
        });


    }
}
