# FilterMenu
商品筛选,条件筛选,展开,折叠菜单


# 功能
  - 1.属性设置单选多选(可以直接设置Flowlayout的属性)
  - 2.弹出菜单是否保持上次筛选记录
  - 3.设置默认弹出键盘，数字键盘和字母键盘
  - 4.输入框尺寸
# 截图
# 如何修改样式
 - 标签的样式可以通过修改filter_flow_tv.xml
# 原理
 - RecycleView的item使用Flowlayout
 - RecycleView的数据源里添加isCheck保持标签是否被选中的属性
 - 可以通过RecycleView添加headView或者footView扩展其他样式
#说明:
  - Flowlayout使用的是https://github.com/hongyangAndroid/FlowLayout
  - Recycleview的工具类使用的是https://github.com/CymChad/BaseRecyclerViewAdapterHelper()