package com.yidingzhong.uikit.common.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.R;
import com.yidingzhong.uikit.common.widget.toolbar.ToolBarOptions;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * Created by Administrator on 2017/1/20 0020.
 */

public class PictureViewPagerActivity extends BaseActivity {
    private ArrayList<String> mUrls;
    private int index;

    private boolean newPageSelected = false;

    private ViewPager mViewPager;
    private PagerAdapter adapter;
    private SimpleDraweeView image;

    public static void start(Context context, ArrayList<String> urls, int index) {
        Intent intent = new Intent();
        intent.putExtra("index", index);
        intent.putStringArrayListExtra("urls",urls);
        intent.setClass(context, PictureViewPagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_view_pager);
        parseIntent();
        initView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

    }

    private void parseIntent() {
        Intent i = getIntent();
        index = i.getIntExtra("index",0);
        mUrls = i.getStringArrayListExtra("urls");
        if(mUrls == null || mUrls.size() == 0){
            Toasty.normal(PictureViewPagerActivity.this,getString(R.string.no_urls)).show();
            finish();
        }
    }

    private void initView(){
        ToolBarOptions options = new ToolBarOptions();
        options.titleString = index + 1 + "/" + mUrls.size();
        setToolBar(R.id.toolbar,options);

        mViewPager = findView(R.id.view_pager);

        adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mUrls == null ? 0 : mUrls.size();
            }

            @Override
            public void notifyDataSetChanged() {
                super.notifyDataSetChanged();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                View layout = (View) object;
                SimpleDraweeView iv = (SimpleDraweeView) layout.findViewById(R.id.watch_image_view);
                container.removeView(layout);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return (view == object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ViewGroup layout;
                layout = (ViewGroup) LayoutInflater.from(PictureViewPagerActivity.this).inflate(R.layout.image_layout_multi_touch, null);
                layout.setBackgroundColor(Color.BLACK);

                container.addView(layout);
                layout.setTag(position);

                if (position == index) {
                    onViewPagerSelected(position);
                }

                return layout;
            }

            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }
        };

        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setCurrentItem(index);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset == 0f && newPageSelected) {
                    newPageSelected = false;
                    onViewPagerSelected(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                newPageSelected = true;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void onViewPagerSelected(int position) {
        setTitle(position + 1 + "/" + mUrls.size());
        updateCurrentImageView(position);
    }

    // 初始化每个view的image
    protected void updateCurrentImageView(final int position) {
        View currentLayout = mViewPager.findViewWithTag(position);
        if (currentLayout == null) {
            ViewCompat.postOnAnimation(mViewPager, new Runnable() {

                @Override
                public void run() {
                    updateCurrentImageView(position);
                }
            });
            return;
        };
        image = (SimpleDraweeView) currentLayout.findViewById(R.id.watch_image_view);
        image.setImageURI(Uri.parse(mUrls.get(position)));
    }
}
