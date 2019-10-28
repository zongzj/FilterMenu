package com.zong.filter.callback;
 /**
  *  author:zzj
  *  time: 2019/10/19 
  *  deprecated : 
  */
public interface OnCallbackListener {
    /**
     * 点击完成
     */
    void onComplete();

    /**
     * 选中某个属性
     *
     * @param posParent 父标签位置下标
     * @param posChild  子标签位置下标
     */
    void onSelected(int posParent, int posChild);

    /**
     * 取消选中某个属性
     *
     * @param posParent 父标签位置下标
     * @param posChild  子标签位置下标
     */
    void onUnSelected(int posParent, int posChild);

    void onCancel();
}
