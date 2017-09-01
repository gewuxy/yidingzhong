package com.yidongzhong.main.model;

import java.util.List;

/**
 * Created by zex on 2017/8/21.
 */

public class HomeSubInfo {

    private List<LotteryBean> lottery;
    private List<DrawnBean> drawn;
    private List<WinMsgBean> winMsg;
    private List<WillDrawBean> willDraw;

    public List<LotteryBean> getLottery() {
        return lottery;
    }

    public void setLottery(List<LotteryBean> lottery) {
        this.lottery = lottery;
    }

    public List<DrawnBean> getDrawn() {
        return drawn;
    }

    public void setDrawn(List<DrawnBean> drawn) {
        this.drawn = drawn;
    }

    public List<WinMsgBean> getWinMsg() {
        return winMsg;
    }

    public void setWinMsg(List<WinMsgBean> winMsg) {
        this.winMsg = winMsg;
    }

    public List<WillDrawBean> getWillDraw() {
        return willDraw;
    }

    public void setWillDraw(List<WillDrawBean> willDraw) {
        this.willDraw = willDraw;
    }

    public static class LotteryBean {
        /**
         * id : 20
         * name : 青花瓷
         * unitPrice : 20000
         * mainImg : {"ext":"jpg","url":"/asset/testPic/14.jpg","name":null,"size":null}
         * periodNum : 20
         * needQty : 2000
         * soldQty : 80
         * productLabel : 唐朝时期珍藏青花瓷
         */

        private int id;
        private String name;
        private int unitPrice;
        private MainImgBean mainImg;
        private int periodNum;
        private int needQty;
        private int soldQty;
        private String productLabel;

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

        public int getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(int unitPrice) {
            this.unitPrice = unitPrice;
        }

        public MainImgBean getMainImg() {
            return mainImg;
        }

        public void setMainImg(MainImgBean mainImg) {
            this.mainImg = mainImg;
        }

        public int getPeriodNum() {
            return periodNum;
        }

        public void setPeriodNum(int periodNum) {
            this.periodNum = periodNum;
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

        public static class MainImgBean {
            /**
             * ext : jpg
             * url : /asset/testPic/14.jpg
             * name : null
             * size : null
             */

            private String ext;
            private String url;
            private Object name;
            private Object size;

            public String getExt() {
                return ext;
            }

            public void setExt(String ext) {
                this.ext = ext;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public Object getSize() {
                return size;
            }

            public void setSize(Object size) {
                this.size = size;
            }
        }
    }

    public static class DrawnBean {
        /**
         * id : 1
         * name : 苹果手机8
         * periodNum : 5
         * endTime : null
         * drawTime : 2017-07-26 04:02:28
         * productLabel : null
         * productPic : {"ext":"png","url":"/asset/testPic/2.png","name":null,"size":null}
         * memberNickName : Jackson
         * lotteryCode : 100438925
         * needQty : 1000
         * drawnQty : 3
         */

        private int id;
        private String name;
        private int periodNum;
        private Object endTime;
        private String drawTime;
        private Object productLabel;
        private ProductPicBean productPic;
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

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
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

        public ProductPicBean getProductPic() {
            return productPic;
        }

        public void setProductPic(ProductPicBean productPic) {
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

        public static class ProductPicBean {
            /**
             * ext : png
             * url : /asset/testPic/2.png
             * name : null
             * size : null
             */

            private String ext;
            private String url;
            private Object name;
            private Object size;

            public String getExt() {
                return ext;
            }

            public void setExt(String ext) {
                this.ext = ext;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public Object getSize() {
                return size;
            }

            public void setSize(Object size) {
                this.size = size;
            }
        }
    }

    public static class WinMsgBean {
        /**
         * memberNickName : Jackson
         * prizeName : 苹果手机8  土豪金版
         */

        private String memberNickName;
        private String prizeName;

        public String getMemberNickName() {
            return memberNickName;
        }

        public void setMemberNickName(String memberNickName) {
            this.memberNickName = memberNickName;
        }

        public String getPrizeName() {
            return prizeName;
        }

        public void setPrizeName(String prizeName) {
            this.prizeName = prizeName;
        }
    }

    public static class WillDrawBean {
        /**
         * id : 3
         * name : 手机抽大奖1
         * periodNum : 3
         * endTime : 2017-07-27 04:02:28
         * needQty : 1000
         * soldQty : 1000
         * productLabel : null
         * productPic : {"ext":"png","url":"/asset/testPic/2.png","name":null,"size":null}
         */

        private int id;
        private String name;
        private int periodNum;
        private String endTime;
        private int needQty;
        private int soldQty;
        private Object productLabel;
        private ProductPicBeanX productPic;

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

        public Object getProductLabel() {
            return productLabel;
        }

        public void setProductLabel(Object productLabel) {
            this.productLabel = productLabel;
        }

        public ProductPicBeanX getProductPic() {
            return productPic;
        }

        public void setProductPic(ProductPicBeanX productPic) {
            this.productPic = productPic;
        }

        public static class ProductPicBeanX {
            /**
             * ext : png
             * url : /asset/testPic/2.png
             * name : null
             * size : null
             */

            private String ext;
            private String url;
            private Object name;
            private Object size;

            public String getExt() {
                return ext;
            }

            public void setExt(String ext) {
                this.ext = ext;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public Object getSize() {
                return size;
            }

            public void setSize(Object size) {
                this.size = size;
            }
        }
    }
}
