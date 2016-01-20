# custom-drop-down-selection
自定义下拉选择控件
##遇到的问题
* 问题描述：在点击下拉菜单的item时，无法获取焦点
* 解决方法：设置popupwindow的属性：
  popupWindow.setFocusable(true);
  popupWindow.setBackgroundDrawable(new BitmapDrawable());
  注意：这两个属性的设置必须放在popupWindow.showAsDropDown(et_content, 0, 0);之前，否则无效
* 问题描述：假设listview的item中有Button、ImageButton、CheckBox等会强制获取焦点的view，此时listview的itwm无法获取焦点，无法被点击
*  解决方法： 给item的跟布局增加以下属性：
    android:descendantFocusability="blocksDescendants"
    设置之后，Button可以获取焦点，其他控件也可以获取焦点
