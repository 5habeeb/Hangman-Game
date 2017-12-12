# Exhibitors view Component
[Events view](https://github.com/armada-ths/armada-android/tree/master/app/src/main/java/se/ths/kth/Aramda/components/events) consists of two parts, [master](https://github.com/armada-ths/armada-android/tree/master/app/src/main/java/se/ths/kth/Aramda/components/events/master) and [detail](https://github.com/armada-ths/armada-android/tree/master/app/src/main/java/se/ths/kth/Aramda/components/events/detail) directories.
A part of this code is shared with the matching component, where the exhibitor list view is used to view the matching results view.

####Master Directory 
This part contains the application logic for the Exhibitors list view. The xml layouts used with it are: 

 - [fragment_exhibitors.xml](https://github.com/armada-ths/armada-android/blob/master/app/src/main/res/layout/fragment_exhibitors.xml) for dispalying events on a RecycleView
 - [list_item_exhibitor.xml](https://github.com/armada-ths/armada-android/blob/master/app/src/main/res/layout/list_item_exhibitor.xml) for event cardview
 - [list_item_exhibitor_header.xml](https://github.com/armada-ths/armada-android/blob/master/app/src/main/res/layout/list_item_exhibitor_header.xml) for event header view
 - [list_item_exhibitor_matching_header.xml](https://github.com/armada-ths/armada-android/blob/master/app/src/main/res/layout/list_item_exhibitor_matching_header.xml) and [list_item_exhibitor_matching_footer.xml](https://github.com/armada-ths/armada-android/blob/master/app/src/main/res/layout/list_item_exhibitor_matching_footer.xml) header and footer for matching results view.

Please check these links for more details about RecycleView [RecyclerView in Android](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html) and [Creating Lists and Cards in RecyclecView](https://developer.android.com/training/material/lists-cards.html)

####Detail Directory 
This part contains the application logic for the contents of the exhibitors. Data Binding Library is used to minimize the code necessary to bind the logic with the layouts. 
The layout used with this part are:

 - [fragment_exhibitor_detail.xml](https://github.com/armada-ths/armada-android/blob/master/app/src/main/res/layout/exhibitor_detail_content.xml) In this layout there is DraggableCoordinatorLayout which provides the scrolling effects and inside of it the [exhibitor_detail_content.xml](https://github.com/armada-ths/armada-android/blob/master/app/src/main/res/layout/exhibitor_detail_content.xml) in included.
 -  [exhibitor_detail_content.xml](https://github.com/armada-ths/armada-android/blob/master/app/src/main/res/layout/exhibitor_detail_content.xml) contains all the views for creating the layout.