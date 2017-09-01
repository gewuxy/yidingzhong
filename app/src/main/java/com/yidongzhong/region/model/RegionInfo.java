package com.yidongzhong.region.model;

import com.yidongzhong.main.model.HomeInfo.SlidePicBean;

import java.util.List;

/**
 * Created by zex on 2017/8/25.
 */

public class RegionInfo {

    private List<WillListByZoneBean> willListByZone;
    private List<NewDlistBean> newDlist;
    private List<SlidePicBean> slidePic;

    public List<WillListByZoneBean> getWillListByZone() {
        return willListByZone;
    }

    public void setWillListByZone(List<WillListByZoneBean> willListByZone) {
        this.willListByZone = willListByZone;
    }

    public List<NewDlistBean> getNewDlist() {
        return newDlist;
    }

    public void setNewDlist(List<NewDlistBean> newDlist) {
        this.newDlist = newDlist;
    }

    public List<SlidePicBean> getSlidePic() {
        return slidePic;
    }

    public void setSlidePic(List<SlidePicBean> slidePic) {
        this.slidePic = slidePic;
    }

    public static class WillListByZoneBean {
        /**
         * id : 18
         * name : 手机话费 100
         * periodNum : 18
         * endTime :
         * needQty : 100
         * soldQty : 21
         * productLabel :
         * productPic : {"name":null,"size":null,"url":"/asset/testPic/12 jpg","ext":"jpg"}
         */

        private int id;
        private String name;
        private int periodNum;
        private String endTime;
        private int needQty;
        private int soldQty;
        private String productLabel;
        private SlidePicBean productPic;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPeriodNum() {
            return periodNum;
        }

        public void setPeriodNum(int periodNum) {
            this.periodNum = periodNum;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getNeedQty() {
            return needQty;
        }

        public void setNeedQty(int needQty) {
            this.needQty = needQty;
        }

        public int getSoldQty() {
            return soldQty;
        }

        public void setSoldQty(int soldQty) {
            this.soldQty = soldQty;
        }

        public String getProductLabel() {
            return productLabel;
        }

        public void setProductLabel(String productLabel) {
            this.productLabel = productLabel;
        }

        public SlidePicBean getProductPic() {
            return productPic;
        }

        public void setProductPic(SlidePicBean productPic) {
            this.productPic = productPic;
        }
    }

    public static class NewDlistBean {
        /**
         * id : 2
         * name : 苹果手机 8 红色版
         * periodNum : 21
         * endTime : 2017-07-27 04:02:28
         * drawTime : 2017-08-09 10:00:04
         * productLabel : null
         * productPic : {"name":null,"size":null,"url":"/asset/testPic/2 png","ext":"png"}
         * memberNickName : Jackson
         * lotteryCode : 100234666
         * needQty : 1000
         * drawnQty : 10
         */

        private int id;
        private String name;
        private int periodNum;
        private String endTime;
        private String drawTime;
        private Object productLabel;
        private SlidePicBean productPic;
        private String memberNickName;
        private String lotteryCode;
        private int needQty;
        private int drawnQty;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPeriodNum() {
            return periodNum;
        }

        public void setPeriodNum(int periodNum) {
            this.periodNum = periodNum;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getDrawTime() {
            return drawTime;
        }

        public void setDrawTime(String drawTime) {
            this.drawTime = drawTime;
        }

        public Object getProductLabel() {
            return productLabel;
        }

        public void setProductLabel(Object productLabel) {
            this.productLabel = productLabel;
        }

        public SlidePicBean getProductPic() {
            return productPic;
        }

        public void setProductPic(SlidePicBean productPic) {
            this.productPic = productPic;
        }

        public String getMemberNickName() {
            return memberNickName;
        }

        public void setMemberNickName(String memberNickName) {
            this.memberNickName = memberNickName;
        }

        public String getLotteryCode() {
            return lotteryCode;
        }

        public void setLotteryCode(String lotteryCode) {
            this.lotteryCode = lotteryCode;
        }

        public int getNeedQty() {
            return needQty;
        }

        public void setNeedQty(int needQty) {
            this.needQty = needQty;
        }

        public int getDrawnQty() {
            return drawnQty;
        }

        public void setDrawnQty(int drawnQty) {
            this.drawnQty = drawnQty;
        }
    }
}
