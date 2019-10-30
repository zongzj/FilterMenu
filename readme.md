# FilterMenu
商品筛选,条件筛选,展开,折叠菜单


# 功能
  - 1.属性设置单选多选(可以直接设置Flowlayout的属性)
  - 2.弹出菜单是否保持上次筛选记录
  - 3.可扩展为底部弹窗
# 截图
  - <img  border="0"  src="https://github.com/zongzj/FilterMenu/blob/a8984334af3b0e45584efa220fcdb087242da87b/demo.gif">
### 可扫码下载安装
  - <img  border="0"  src="https://github.com/zongzj/FilterMenu/blob/master/f0jH.png">
  - [下载 APK-Demo](https://www.pgyer.com/f0jH)
# 如何修改样式
 - 标签的样式可以通过修改filter_flow_tv.xml
# 原理
 - RecycleView的item使用Flowlayout展示子标签
 - RecycleView的实体类里添加isCheck保持标签是否被选中的属性
 - 可以通过RecycleView添加headView或者footView扩展其他样式
#说明:
  - Flowlayout使用的是https://github.com/hongyangAndroid/FlowLayout
  - Recycleview的工具类使用的是https://github.com/CymChad/BaseRecyclerViewAdapterHelper