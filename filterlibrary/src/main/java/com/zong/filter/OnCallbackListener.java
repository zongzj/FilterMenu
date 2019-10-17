package com.zong.filter;

public interface OnCallbackListener {
    /**
     * 选择完成后
     */
    void onComplete();

    /**
     * 选中某个属性
     * @param posParent 父标签位置
     * @param posChild  子标签位置
     */
    void onSelected(int posParent, int posChild);
    /**
     * 取消选中某个属性
     * @param posParent 父标签位置
     * @param posChild  子标签位置
     */
    void onUnSelected(int posParent, int posChild);

    void onCancel();
}
