package com.yidongzhong.duobao.model;

import java.util.List;

/**
 * Created by zex on 2017/9/1.
 */

public class CategoryLotteryInfo {

    private List<ProListByZoneBean> proListByZone;

    public List<ProListByZoneBean> getProListByZone() {
        return proListByZone;
    }

    public void setProListByZone(List<ProListByZoneBean> proListByZone) {
        this.proListByZone = proListByZone;
    }

    public static class ProListByZoneBean {
        /**
         * id : 7
         * name : Iphone 10
         * periodNum : 7
         * endTime : null
         * needQty : 6000
         * soldQty : 99
         * productLabel :
         * productPic : {"ext":"png","url":"/asset/testPic/3.png","name":null,"size":null}
         */

        private int id;
        private String name;
        private int periodNum;
        private Object endTime;
        private int needQty;
        private int soldQty;
        private String productLabel;
        private ProductPicBean productPic;

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

        public ProductPicBean getProductPic() {
            return productPic;
        }

        public void setProductPic(ProductPicBean productPic) {
            this.productPic = productPic;
        }

        public static class ProductPicBean {
            /**
             * ext : png
             * url : /asset/testPic/3.png
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
