package com.yidongzhong.points.activity;

import java.util.List;

/**
 * Created by zex on 2017/9/1.
 */

public class PointsMallInfo {

    private List<PointPlistBean> pointPlist;

    public List<PointPlistBean> getPointPlist() {
        return pointPlist;
    }

    public void setPointPlist(List<PointPlistBean> pointPlist) {
        this.pointPlist = pointPlist;
    }

    public static class PointPlistBean {
        /**
         * name : test8手机-积分兑换
         * pic : null
         * point : 3000
         * picList : [{"ext":null,"url":"/asset/testPic/2.png","name":null,"size":null},{"ext":null,"url":"/asset/testPic/3.png","name":null,"size":null},{"ext":null,"url":"/asset/testPic/4.png","name":null,"size":null}]
         * id : 4
         */

        private String name;
        private Object pic;
        private int point;
        private int id;
        private List<PicListBean> picList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getPic() {
            return pic;
        }

        public void setPic(Object pic) {
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

        public List<PicListBean> getPicList() {
            return picList;
        }

        public void setPicList(List<PicListBean> picList) {
            this.picList = picList;
        }

        public static class PicListBean {
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
}
