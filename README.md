# HoverToolbarTabLayout
CoordinatorLayout+AppBarLayout+CollapsingToolbarLayout+Toolbar+TabLayout+ViewPager，悬停Toolbar以及Tab栏
--------------------------------------------------------

![图片预览](https://github.com/FPhoenixCorneaE/HoverToolbarTabLayout/blob/master/preview/hover.gif)

-----------------------------

xml中代码
----------
```java
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@android:color/white">

    <android.support.design.widget.AppBarLayout
        android:id="@+id/ablAppBar"
        android:layout_width="match_parent"
        android:layout_height="180dp"
        android:background="@android:color/transparent">

        <android.support.design.widget.CollapsingToolbarLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_scrollFlags="scroll|exitUntilCollapsed">

            <RelativeLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent">

                <ImageView
                    android:id="@+id/ivBanner"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:alpha="0.8"
                    android:scaleType="centerCrop"
                    android:src="@mipmap/pic_image"
                    tools:ignore="ContentDescription" />

                <ImageView
                    android:id="@+id/ivSmallImg"
                    android:layout_width="60dp"
                    android:layout_height="60dp"
                    android:layout_alignParentBottom="true"
                    android:layout_margin="15dp"
                    android:scaleType="centerCrop"
                    android:src="@mipmap/pic_image"
                    tools:ignore="ContentDescription" />

                <TextView
                    android:id="@+id/tvNickname"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_alignTop="@+id/ivSmallImg"
                    android:layout_toEndOf="@+id/ivSmallImg"
                    android:text="烽火戏诸侯"
                    android:textColor="#373737"
                    android:textSize="15sp"
                    tools:ignore="HardcodedText" />

                <TextView
                    android:id="@+id/tvPersonalizedSignature"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_below="@+id/tvNickname"
                    android:layout_marginTop="5dp"
                    android:layout_marginEnd="15dp"
                    android:layout_toEndOf="@+id/ivSmallImg"
                    android:ellipsize="end"
                    android:lines="2"
                    android:text="北方有佳人。绝世而独立。一顾倾人城。再顾倾人国。宁不知倾城与倾国。佳人难再得。"
                    android:textColor="#373737"
                    android:textSize="13sp"
                    tools:ignore="HardcodedText" />
            </RelativeLayout>

            <android.support.v7.widget.Toolbar
                android:id="@+id/tb_toolbar"
                android:layout_width="match_parent"
                android:layout_height="48dp"
                app:contentInsetEnd="0dp"
                app:contentInsetLeft="0dp"
                app:contentInsetRight="0dp"
                app:contentInsetStart="0dp"
                app:layout_collapseMode="pin">

                <RelativeLayout
                    android:id="@+id/rlTitle"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent">

                    <ImageView
                        android:id="@+id/ivBack"
                        android:layout_width="36dp"
                        android:layout_height="36dp"
                        android:layout_centerVertical="true"
                        android:layout_marginStart="10dp"
                        android:scaleType="center"
                        android:src="@drawable/ic_arrow_left"
                        tools:ignore="ContentDescription" />

                    <TextView
                        android:id="@+id/tvTitle"
                        android:layout_width="match_parent"
                        android:layout_height="match_parent"
                        android:layout_centerVertical="true"
                        android:gravity="center"
                        android:text="标题"
                        android:textColor="@android:color/black"
                        android:textSize="17sp"
                        tools:ignore="HardcodedText" />

                    <ImageView
                        android:id="@+id/ivMore"
                        android:layout_width="36dp"
                        android:layout_height="36dp"
                        android:layout_alignParentEnd="true"
                        android:layout_centerVertical="true"
                        android:layout_marginEnd="10dp"
                        android:scaleType="center"
                        android:src="@drawable/ic_more"
                        tools:ignore="ContentDescription" />
                </RelativeLayout>
            </android.support.v7.widget.Toolbar>
        </android.support.design.widget.CollapsingToolbarLayout>
    </android.support.design.widget.AppBarLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        app:layout_behavior="@string/appbar_scrolling_view_behavior">

        <android.support.design.widget.TabLayout
            android:id="@+id/tlTab"
            android:layout_width="match_parent"
            android:layout_height="40dp"
            app:tabIndicatorColor="@android:color/black"
            app:tabIndicatorHeight="3dp"
            app:tabMode="fixed"
            app:tabSelectedTextColor="@android:color/black"
            app:tabTextColor="@android:color/darker_gray" />

        <android.support.v4.view.ViewPager
            android:id="@+id/vpPager"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
    </LinearLayout>
</android.support.design.widget.CoordinatorLayout>
```
-----------------------------------

标题栏背景色渐变
------------------
```java
mAblAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float alpha = Math.abs(verticalOffset) * 1.0F / (mAblAppBar.getHeight() - mTbToolbar.getHeight());
                setToolbarAlpha(alpha);
            }
        });
```
```java
private void setToolbarAlpha(float alpha) {
        mTbToolbar.setBackgroundColor(getGradientOverlayColor(ContextCompat.getColor(this, R.color.colorPrimary), alpha > 1 ? 1 : alpha));
    }

    /**
     * 获取渐变颜色
     *
     * @param color    原始颜色
     * @param fraction 透明度比率
     * @return 渐变颜色
     */
    private int getGradientOverlayColor(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }
```
