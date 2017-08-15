package com.yidongzhong.main.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.widget.banner.RecyclingPagerAdapter;
import com.yidongzhong.R;
import com.yidongzhong.main.model.HomeInfo;
import com.yidongzhong.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zex on 2017/8/15.
 */

public class BannerPagerAdapter extends RecyclingPagerAdapter {
    private Context context;
    private List<HomeInfo.SlidePicBean> pPtInfoList = new ArrayList<>();

    private boolean isInfiniteLoop;

    public OnClickListener mClickResponse;

    public BannerPagerAdapter(Context context) {
        this.context = context;
        this.pPtInfoList = new ArrayList<>();
    }


    public void setImageUrlList(List<HomeInfo.SlidePicBean> pPtInfoList) {
        this.pPtInfoList = pPtInfoList;
        this.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        if (null == pPtInfoList)
            return 0;
        if (pPtInfoList.size() == 1) {
            return 1;
        } else {
            return isInfiniteLoop ? Integer.MAX_VALUE : pPtInfoList.size();
        }
    }

    private int getPosition(int position) {
        return isInfiniteLoop ? (pPtInfoList.size() == 0) ? -1 : position % pPtInfoList.size()
                : position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup container) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.banner_view,null);
            holder = new ViewHolder();
            holder.simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.image);
            //holder.simpleDraweeView.setAspectRatio(2.22f);
            GenericDraweeHierarchy hierarchy = holder.simpleDraweeView.getHierarchy();
            hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
            holder.simpleDraweeView.setHierarchy(hierarchy);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Uri uri = Uri.parse(ApiClient.BASE_URL + pPtInfoList.get(getPosition(position)).getUrl());
        holder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickResponse.onClick(getPosition(position), pPtInfoList.get(getPosition(position)));
            }
        });
        holder.simpleDraweeView.setImageURI(uri);
//        }

        return view;
    }

    private class ViewHolder {

        SimpleDraweeView simpleDraweeView;
    }

    public BannerPagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }

    public void setOnClickListener(OnClickListener mClickResponse) {
        this.mClickResponse = mClickResponse;
    }


    public interface OnClickListener {
        public void onClick(int postion, Object response);
    }
}
