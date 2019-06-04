package com.wkz.hover;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wkz.hover.immersionbar.BarHide;
import com.wkz.hover.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView mIvBanner;
    private ImageView mIvBack;
    /**
     *
     */
    private TextView mTvTitle;
    private ImageView mIvMore;
    private RelativeLayout mRlTitle;
    private AppBarLayout mAblAppBar;
    private TabLayout mTlTab;
    private ViewPager mVpPager;
    private Toolbar mTbToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        mIvBanner = (ImageView) findViewById(R.id.ivBanner);
        mIvBack = (ImageView) findViewById(R.id.ivBack);
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        mIvMore = (ImageView) findViewById(R.id.ivMore);
        mRlTitle = (RelativeLayout) findViewById(R.id.rlTitle);
        mAblAppBar = (AppBarLayout) findViewById(R.id.ablAppBar);
        mTlTab = (TabLayout) findViewById(R.id.tlTab);
        mVpPager = (ViewPager) findViewById(R.id.vpPager);
        mTbToolbar = (Toolbar) findViewById(R.id.tbToolbar);
    }

    private void initListener() {
        mAblAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float alpha = Math.abs(verticalOffset) * 1.0F / (mAblAppBar.getHeight() - mTbToolbar.getHeight() - StatusBarTools.getStatusBarHeight(MainActivity.this));
                setToolbarBackgroundColor(alpha);
                setStatusBarColor(alpha);
            }
        });
    }

    private void setStatusBarColor(float alpha) {
        ImmersionBar.with(this)
                .statusBarColor(getGradientOverlayColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark), alpha > 1 ? 1 : alpha))
                .init();
    }

    private void setToolbarBackgroundColor(float alpha) {
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

    private void initData() {

        ImmersionBar.with(this)
                .hideBar(BarHide.FLAG_SHOW_BAR)
                .init();

        int statusHeight = StatusBarTools.getStatusBarHeight(this);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            CollapsingToolbarLayout.LayoutParams lp = (CollapsingToolbarLayout.LayoutParams) mTbToolbar.getLayoutParams();
            lp.topMargin = statusHeight;
            mTbToolbar.setLayoutParams(lp);
        }

        List<Fragment> fragments = new ArrayList<Fragment>(3) {{
            add(new TestFragment());
            add(new TestFragment());
            add(new TestFragment());
        }};

        List<String> fragmentTitles = new ArrayList<String>(3) {{
            add("Tab1");
            add("Tab2");
            add("Tab3");
        }};

        mVpPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(), fragments, fragmentTitles));
        mTlTab.setupWithViewPager(mVpPager);
    }
}
