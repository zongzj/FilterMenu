package com.zong.filter;

import android.app.Activity;
import android.view.View;

import com.zong.filter.bean.CategoryBean;

import java.util.List;

public class FilterMenu {

    private Activity activity;
    private List<CategoryBean> beanList;
    private boolean isRecover;
    private boolean isSelectOne;
    private OnCallbackListener calbackListener;

    public FilterMenu(Activity activity, List<CategoryBean> beanList, boolean isRecover, boolean isSelectOne, OnCallbackListener calbackListener) {
        this.activity = activity;
        this.beanList = beanList;
        this.isRecover = isRecover;
        this.isSelectOne = isSelectOne;
        this.calbackListener = calbackListener;
        DialogFilterUtils filterUtils = new DialogFilterUtils();
        filterUtils.filterDialog(activity, beanList, isRecover, isSelectOne, calbackListener);
    }

    public static final class Builder {
        private Activity activity;
        private List<CategoryBean> beanList;
        private boolean isRecover = true;
        private boolean isSelectOne = false;
        private OnCallbackListener calbackListener;

        public Builder() {
        }

        public Builder setCalbackListener(OnCallbackListener calbackListener) {
            this.calbackListener = calbackListener;
            if (calbackListener == null) {
                throw new ParamsException("calbackListener cann`t null ");
            }
            return this;
        }

        public Builder setActivity(Activity activity) {
            this.activity = activity;
            return this;
        }

        public Builder setListBeanList(List<CategoryBean> listBeanList) {
            this.beanList = listBeanList;
            if (beanList == null || beanList.size() == 0) {
                throw new ParamsException("list cann`t null or  empty");
            }
            return this;
        }

        public Builder setRecover(boolean recover) {
            return this;
        }

        public Builder setSelectOne(boolean selectOne) {
            isSelectOne = selectOne;
            return this;
        }

        public FilterMenu show() {
            return new FilterMenu(activity, beanList, isRecover, isSelectOne, calbackListener);
        }
    }
}
