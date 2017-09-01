package com.yidongzhong.main.model;

import java.util.List;

/**
 * Created by zex on 2017/8/15.
 */

public class HomeInfo {

    /**
     * pointProduct : [{"name":"test8手机-积分兑换","pic":{"ext":"png","url":"/asset/images/y24.png","name":null,"size":null},"point":3000,"id":4},{"name":"华为手机-积分兑换","pic":{"ext":"jpg","url":"/asset/testPic/19.jpg","name":null,"size":null},"point":5000,"id":6},{"name":"小米手机-积分兑换","pic":{"ext":"jpg","url":"/asset/testPic/18.jpg","name":null,"size":null},"point":3000,"id":18}]
     * share : {"memberNickName":"Jackson","headPicUrl":"/asset/testPic/1.png","memberLevel":3,"drawnQty":null,"drawnTime":null,"shareTime":"2017-07-26 04:52:09","content":"这个一定中活动太棒了，买了几次就中了大奖。","lotteryCode":null,"productPics":[{"ext":null,"url":"/asset/testPic/2.png","name":null,"size":null},{"ext":null,"url":"/asset/testPic/3.png","name":null,"size":null},{"ext":null,"url":"/asset/testPic/4.png","name":null,"size":null}],"lotteryPeriodNum":5,"lotteryName":"苹果手机8","id":1}
     * slidePic : [{"ext":"png","url":"/asset/images/y1.png","name":null,"size":null},{"ext":"png","url":"/asset/images/y1.png","name":null,"size":null},{"ext":"png","url":"/asset/images/y1.png","name":null,"size":null},{"ext":"png","url":"/asset/images/y1.png","name":null,"size":null}]
     */

    private ShareBean share;
    private List<PointProductBean> pointProduct;
    private List<SlidePicBean> slidePic;

    public ShareBean getShare() {
        return share;
    }

    public void setShare(ShareBean share) {
        this.share = share;
    }

    public List<PointProductBean> getPointProduct() {
        return pointProduct;
    }

    public void setPointProduct(List<PointProductBean> pointProduct) {
        this.pointProduct = pointProduct;
    }

    public List<SlidePicBean> getSlidePic() {
        return slidePic;
    }

    public void setSlidePic(List<SlidePicBean> slidePic) {
        this.slidePic = slidePic;
    }

    public static class ShareBean {
        /**
         * memberNickName : Jackson
         * headPicUrl : /asset/testPic/1.png
         * memberLevel : 3
         * drawnQty : null
         * drawnTime : null
         * shareTime : 2017-07-26 04:52:09
         * content : 这个一定中活动太棒了，买了几次就中了大奖。
         * lotteryCode : null
         * productPics : [{"ext":null,"url":"/asset/testPic/2.png","name":null,"size":null},{"ext":null,"url":"/asset/testPic/3.png","name":null,"size":null},{"ext":null,"url":"/asset/testPic/4.png","name":null,"size":null}]
         * lotteryPeriodNum : 5
         * lotteryName : 苹果手机8
         * id : 1
         */

        private String memberNickName;
        private String headPicUrl;
        private int memberLevel;
        private Object drawnQty;
        private Object drawnTime;
        private String shareTime;
        private String content;
        private Object lotteryCode;
        private int lotteryPeriodNum;
        private String lotteryName;
        private int id;
        private List<ProductPicsBean> productPics;
        private String province;
        private String city;
        private String pageViewCount;
        private String commentCount;
        private String likeCount;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPageViewCount() {
            return pageViewCount;
        }

        public void setPageViewCount(String pageViewCount) {
            this.pageViewCount = pageViewCount;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getMemberNickName() {
            return memberNickName;
        }

        public void setMemberNickName(String memberNickName) {
            this.memberNickName = memberNickName;
        }

        public String getHeadPicUrl() {
            return headPicUrl;
        }

        public void setHeadPicUrl(String headPicUrl) {
            this.headPicUrl = headPicUrl;
        }

        public int getMemberLevel() {
            return memberLevel;
        }

        public void setMemberLevel(int memberLevel) {
            this.memberLevel = memberLevel;
        }

        public Object getDrawnQty() {
            return drawnQty;
        }

        public void setDrawnQty(Object drawnQty) {
            this.drawnQty = drawnQty;
        }

        public Object getDrawnTime() {
            return drawnTime;
        }

        public void setDrawnTime(Object drawnTime) {
            this.drawnTime = drawnTime;
        }

        public String getShareTime() {
            return shareTime;
        }

        public void setShareTime(String shareTime) {
            this.shareTime = shareTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getLotteryCode() {
            return lotteryCode;
        }

        public void setLotteryCode(Object lotteryCode) {
            this.lotteryCode = lotteryCode;
        }

        public int getLotteryPeriodNum() {
            return lotteryPeriodNum;
        }

        public void setLotteryPeriodNum(int lotteryPeriodNum) {
            this.lotteryPeriodNum = lotteryPeriodNum;
        }

        public String getLotteryName() {
            return lotteryName;
        }

        public void setLotteryName(String lotteryName) {
            this.lotteryName = lotteryName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<ProductPicsBean> getProductPics() {
            return productPics;
        }

        public void setProductPics(List<ProductPicsBean> productPics) {
            this.productPics = productPics;
        }

        public static class ProductPicsBean {
            /**
             * ext : null
             * url : /asset/testPic/2.png
             * name : null
             * size : null
             */

            private Object ext;
            private String url;
            private Object name;
            private Object size;

            public Object getExt() {
                return ext;
            }

            public void setExt(Object ext) {
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

    public static class PointProductBean {
        /**
         * name : test8手机-积分兑换
         * pic : {"ext":"png","url":"/asset/images/y24.png","name":null,"size":null}
         * point : 3000
         * id : 4
         */

        private String name;
        private PicBean pic;
        private int point;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public PicBean getPic() {
            return pic;
        }

        public void setPic(PicBean pic) {
            this.pic = pic;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public static class PicBean {
            /**
             * ext : png
             * url : /asset/images/y24.png
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

    public static class SlidePicBean {
        /**
         * ext : png
         * url : /asset/images/y1.png
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
