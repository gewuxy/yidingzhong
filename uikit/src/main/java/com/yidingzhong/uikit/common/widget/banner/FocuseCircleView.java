package com.yidingzhong.uikit.common.widget.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yidingzhong.uikit.R;

/**
 * Created by zex on 2017/8/14.
 */

public class FocuseCircleView extends LinearLayout {
    private Context context;
    private int count;

    public FocuseCircleView(Context context) {
        super(context);
        init(context);
    }

//	public FocuseCircleView(Context mContext, AttributeSet attrs, int defStyle) {
//		super(mContext, attrs, defStyle);
//		init(mContext);
//	}

    public FocuseCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        this.context = context;
        this.setOrientation(LinearLayout.HORIZONTAL);
    }

    /***
     * 设置检点图数目
     * @param count
     */
    public void setCount(int count){
        this.count = count;
        this.removeAllViews();
        for(int i = 0;i<count;i++){
            ImageView img = new ImageView(context);
            img.setImageResource(R.drawable.app_focus_point_unselect);
            img.setPadding(5, 5, 5, 5);
            this.addView(img, i);
        }
    }

    public void setCount(int count,int imageSrouce){
        this.count = count;
        for(int i = 0;i<count;i++){
            ImageView img = new ImageView(context);
            img.setImageResource(imageSrouce);
            img.setPadding(5, 5, 5, 5);
            this.addView(img, i);
        }
    }

    public void setCurrentFocuse(int current,int defaultId,int currentId){
        for(int i = 0;i<count;i++){
            ImageView img = (ImageView) this.getChildAt(i);
            if(i==current){
                img.setImageResource(currentId);
            }else{
                img.setImageResource(defaultId);
            }
        }
    }

    /***
     * 设置当前焦点
     * @param current
     */
    public void setCurrentFocuse(int current){
        for(int i = 0;i<count;i++){
            ImageView img = (ImageView) this.getChildAt(i);
            if(i==current){
                img.setImageResource(R.drawable.app_focus_point_selected);
            }else{
                img.setImageResource(R.drawable.app_focus_point_unselect);
            }
        }
    }
}
