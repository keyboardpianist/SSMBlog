package com.deltaplus.beans;

import java.io.Serializable;

public class ArticleDetail implements Serializable {
    private int aid;
    private String detail;
    //private String comments;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }



    public String getDetail() {
        return detail;
    }/*@ResponseBody返回json数据参考的不是属性名，而是get方法后面的字段名*/

    public void setDetail(String detail) {
        this.detail = detail;
    }

   /* public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }*/

    @Override
    public String toString() {
        return "ArticleDetail{" +
                "aid=" + aid +
                ", detail='" + detail + '\'' +
                '}';
    }
}
