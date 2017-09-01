package com.yidongzhong.region.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yidingzhong.uikit.common.widget.banner.RecyclingPagerAdapter;
import com.yidongzhong.R;
import com.yidongzhong.main.model.HomeInfo;
import com.yidongzhong.network.ApiClient;
import com.yidongzhong.region.model.RegionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zex on 2017/8/25.
 */

public class BannerPagerAdapter2 extends RecyclingPagerAdapter {
    private Context context;
    private List<RegionInfo.NewDlistBean> pPtInfoList = new ArrayList<>();

    private boolean isInfiniteLoop;

    public BannerPagerAdapter2.OnClickListener mClickResponse;

    public BannerPagerAdapter2(Context context) {
        this.context = context;
        this.pPtInfoList = new ArrayList<>();
    }

    public void setImageUrlList(List<RegionInfo.NewDlistBean> pPtInfoList) {
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
            return isInfiniteLoop ? Integer.MAX_VALUE :
                    pPtInfoList.size() % 2 == 0 ? pPtInfoList.size() / 2 : pPtInfoList.size() / 2 + 1;
        }
    }

    private int getPosition(int position) {
        return isInfiniteLoop ? (pPtInfoList.size() == 0) ? -1 : position % pPtInfoList.size()
                : position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup container) {
        BannerPagerAdapter2.ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.banner_view2,null);
            holder = new BannerPagerAdapter2.ViewHolder();
            holder.simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.product);
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.total = (TextView) view.findViewById(R.id.total);
            holder.code = (TextView) view.findViewById(R.id.code);
            holder.prizeUser = (TextView) view.findViewById(R.id.prize_name);
            holder.now = (TextView) view.findViewById(R.id.num);
            holder.prizeNumber = (TextView) view.findViewById(R.id.prize_number);

            holder.simpleDraweeView2 = (SimpleDraweeView) view.findViewById(R.id.product2);
            holder.name2 = (TextView) view.findViewById(R.id.name2);
            holder.total2 = (TextView) view.findViewById(R.id.total2);
            holder.code2 = (TextView) view.findViewById(R.id.code2);
            holder.prizeUser2 = (TextView) view.findViewById(R.id.prize_name2);
            holder.now2 = (TextView) view.findViewById(R.id.num2);
            holder.prizeNumber2 = (TextView) view.findViewById(R.id.prize_number2);

            view.setTag(holder);
        } else {
            holder = (BannerPagerAdapter2.ViewHolder) view.getTag();
        }
        RegionInfo.NewDlistBean bean = pPtInfoList.get(2 * position);
        Uri uri = Uri.parse(ApiClient.BASE_URL + bean.getProductPic().getUrl());
        holder.simpleDraweeView.setImageURI(uri);
        holder.name.setText(bean.getName());
        holder.total.setText(bean.getNeedQty());
        holder.code.setText(bean.getLotteryCode());
        holder.prizeUser.setText(bean.getMemberNickName());
        holder.now.setText(bean.getDrawnQty());
        holder.prizeNumber.setText(bean.getLotteryCode());

        RegionInfo.NewDlistBean bean2  = pPtInfoList.get(2 * position + 1);
        holder.simpleDraweeView2.setImageURI(Uri.parse(ApiClient.BASE_URL + bean2.getProductPic().getUrl()));
        holder.name2.setText(bean2.getName());
        holder.total2.setText(bean2.getNeedQty());
        holder.code2.setText(bean2.getLotteryCode());
        holder.prizeUser2.setText(bean2.getMemberNickName());
        holder.now2.setText(bean2.getDrawnQty());
        holder.prizeNumber2.setText(bean2.getLotteryCode());
        return view;
    }

    private class ViewHolder {
        SimpleDraweeView simpleDraweeView,simpleDraweeView2;
        private TextView name,total,code,prizeUser,now,prizeNumber;
        private TextView name2,total2,code2,prizeUser2,now2,prizeNumber2;
    }

    public BannerPagerAdapter2 setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }

    public void setOnClickListener(BannerPagerAdapter2.OnClickListener mClickResponse) {
        this.mClickResponse = mClickResponse;
    }


    public interface OnClickListener {
        void onClick(int postion, Object response);
    }
}
