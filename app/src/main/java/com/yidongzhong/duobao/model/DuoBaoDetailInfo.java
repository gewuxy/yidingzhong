package com.yidongzhong.duobao.model;

import com.yidongzhong.main.model.HomeInfo;
import com.yidongzhong.main.model.HomeSubInfo;

import java.util.List;

/**
 * Created by zex on 2017/8/28.
 */

public class DuoBaoDetailInfo {

    /**
     * goodDetail : {"id":7,"gallery":null,"name":"苹果10","label":"IPhone10 test","periodNum":7,"isEnd":false,"isDrawn":false,"productKind":{"modTime":"2016-12-18 12:12:48","modMan":"ysq","modIp":"127","idString":"{10}","isEnabled":true,"nodeParent":null,"fullName":"手机","sortInSameLevel":1,"picJson":"[{\"url\":\"/up/pic/3/pkind4.png\",\"name\":null,\"size\":null,\"ext\":\"png\"}]","productBigKind":"normal","productBigKindCode":1,"shortName":null,"level":1,"id":10,"code":null,"name":"手机","parent":null},"needQty":6000,"soldQty":99,"rating":null,"commentsCount":null,"info":null,"mallStateCode":null,"mallStateName":null,"services":null,"kindId":null,"proId":7,"kindName":null,"merchantId":null,"timeOnShelf":null,"prices":null,"price":null,"favorablePrice":null,"useTerms":null}
     */

    private GoodDetailBean goodDetail;

    public GoodDetailBean getGoodDetail() {
        return goodDetail;
    }

    public void setGoodDetail(GoodDetailBean goodDetail) {
        this.goodDetail = goodDetail;
    }

    public static class GoodDetailBean {
        /**
         * id : 7
         * gallery : null
         * name : 苹果10
         * label : IPhone10 test
         * periodNum : 7
         * isEnd : false
         * isDrawn : false
         * productKind : {"modTime":"2016-12-18 12:12:48","modMan":"ysq","modIp":"127","idString":"{10}","isEnabled":true,"nodeParent":null,"fullName":"手机","sortInSameLevel":1,"picJson":"[{\"url\":\"/up/pic/3/pkind4.png\",\"name\":null,\"size\":null,\"ext\":\"png\"}]","productBigKind":"normal","productBigKindCode":1,"shortName":null,"level":1,"id":10,"code":null,"name":"手机","parent":null}
         * needQty : 6000
         * soldQty : 99
         * rating : null
         * commentsCount : null
         * info : null
         * mallStateCode : null
         * mallStateName : null
         * services : null
         * kindId : null
         * proId : 7
         * kindName : null
         * merchantId : null
         * timeOnShelf : null
         * prices : null
         * price : null
         * favorablePrice : null
         * useTerms : null
         */

        private int id;
        private List<HomeInfo.SlidePicBean> gallery;
        private String name;
        private String label;
        private int periodNum;
        private boolean isEnd;
        private boolean isDrawn;
        private ProductKindBean productKind;
        private int needQty;
        private int soldQty;
        private Object rating;
        private Object commentsCount;
        private String info;
        private Object mallStateCode;
        private Object mallStateName;
        private Object services;
        private Object kindId;
        private int proId;
        private Object kindName;
        private Object merchantId;
        private Object timeOnShelf;
        private Object prices;
        private Object price;
        private Object favorablePrice;
        private Object useTerms;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<HomeInfo.SlidePicBean> getGallery() {
            return gallery;
        }

        public void setGallery(List<HomeInfo.SlidePicBean> gallery) {
            this.gallery = gallery;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getPeriodNum() {
            return periodNum;
        }

        public void setPeriodNum(int periodNum) {
            this.periodNum = periodNum;
        }

        public boolean isIsEnd() {
            return isEnd;
        }

        public void setIsEnd(boolean isEnd) {
            this.isEnd = isEnd;
        }

        public boolean isIsDrawn() {
            return isDrawn;
        }

        public void setIsDrawn(boolean isDrawn) {
            this.isDrawn = isDrawn;
        }

        public ProductKindBean getProductKind() {
            return productKind;
        }

        public void setProductKind(ProductKindBean productKind) {
            this.productKind = productKind;
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

        public Object getRating() {
            return rating;
        }

        public void setRating(Object rating) {
            this.rating = rating;
        }

        public Object getCommentsCount() {
            return commentsCount;
        }

        public void setCommentsCount(Object commentsCount) {
            this.commentsCount = commentsCount;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public Object getMallStateCode() {
            return mallStateCode;
        }

        public void setMallStateCode(Object mallStateCode) {
            this.mallStateCode = mallStateCode;
        }

        public Object getMallStateName() {
            return mallStateName;
        }

        public void setMallStateName(Object mallStateName) {
            this.mallStateName = mallStateName;
        }

        public Object getServices() {
            return services;
        }

        public void setServices(Object services) {
            this.services = services;
        }

        public Object getKindId() {
            return kindId;
        }

        public void setKindId(Object kindId) {
            this.kindId = kindId;
        }

        public int getProId() {
            return proId;
        }

        public void setProId(int proId) {
            this.proId = proId;
        }

        public Object getKindName() {
            return kindName;
        }

        public void setKindName(Object kindName) {
            this.kindName = kindName;
        }

        public Object getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(Object merchantId) {
            this.merchantId = merchantId;
        }

        public Object getTimeOnShelf() {
            return timeOnShelf;
        }

        public void setTimeOnShelf(Object timeOnShelf) {
            this.timeOnShelf = timeOnShelf;
        }

        public Object getPrices() {
            return prices;
        }

        public void setPrices(Object prices) {
            this.prices = prices;
        }

        public Object getPrice() {
            return price;
        }

        public void setPrice(Object price) {
            this.price = price;
        }

        public Object getFavorablePrice() {
            return favorablePrice;
        }

        public void setFavorablePrice(Object favorablePrice) {
            this.favorablePrice = favorablePrice;
        }

        public Object getUseTerms() {
            return useTerms;
        }

        public void setUseTerms(Object useTerms) {
            this.useTerms = useTerms;
        }

        public static class ProductKindBean {
            /**
             * modTime : 2016-12-18 12:12:48
             * modMan : ysq
             * modIp : 127
             * idString : {10}
             * isEnabled : true
             * nodeParent : null
             * fullName : 手机
             * sortInSameLevel : 1
             * picJson : [{"url":"/up/pic/3/pkind4.png","name":null,"size":null,"ext":"png"}]
             * productBigKind : normal
             * productBigKindCode : 1
             * shortName : null
             * level : 1
             * id : 10
             * code : null
             * name : 手机
             * parent : null
             */

            private String modTime;
            private String modMan;
            private String modIp;
            private String idString;
            private boolean isEnabled;
            private Object nodeParent;
            private String fullName;
            private int sortInSameLevel;
            private String picJson;
            private String productBigKind;
            private int productBigKindCode;
            private Object shortName;
            private int level;
            private int id;
            private Object code;
            private String name;
            private Object parent;

            public String getModTime() {
                return modTime;
            }

            public void setModTime(String modTime) {
                this.modTime = modTime;
            }

            public String getModMan() {
                return modMan;
            }

            public void setModMan(String modMan) {
                this.modMan = modMan;
            }

            public String getModIp() {
                return modIp;
            }

            public void setModIp(String modIp) {
                this.modIp = modIp;
            }

            public String getIdString() {
                return idString;
            }

            public void setIdString(String idString) {
                this.idString = idString;
            }

            public boolean isIsEnabled() {
                return isEnabled;
            }

            public void setIsEnabled(boolean isEnabled) {
                this.isEnabled = isEnabled;
            }

            public Object getNodeParent() {
                return nodeParent;
            }

            public void setNodeParent(Object nodeParent) {
                this.nodeParent = nodeParent;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public int getSortInSameLevel() {
                return sortInSameLevel;
            }

            public void setSortInSameLevel(int sortInSameLevel) {
                this.sortInSameLevel = sortInSameLevel;
            }

            public String getPicJson() {
                return picJson;
            }

            public void setPicJson(String picJson) {
                this.picJson = picJson;
            }

            public String getProductBigKind() {
                return productBigKind;
            }

            public void setProductBigKind(String productBigKind) {
                this.productBigKind = productBigKind;
            }

            public int getProductBigKindCode() {
                return productBigKindCode;
            }

            public void setProductBigKindCode(int productBigKindCode) {
                this.productBigKindCode = productBigKindCode;
            }

            public Object getShortName() {
                return shortName;
            }

            public void setShortName(Object shortName) {
                this.shortName = shortName;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getCode() {
                return code;
            }

            public void setCode(Object code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getParent() {
                return parent;
            }

            public void setParent(Object parent) {
                this.parent = parent;
            }
        }
    }
}
