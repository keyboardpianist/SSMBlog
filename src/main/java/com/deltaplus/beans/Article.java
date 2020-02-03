package com.deltaplus.beans;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    //private static final long serialVersionUID = ;
    private int aid;
    private String title;
    private Date createtime;
    private Date updatetime;
    private String article_abstract;
    private ArticleDetail articleDetail;

    public ArticleDetail getArticleDetail() {
        return articleDetail;
    }

    public void setArticleDetail(ArticleDetail articleDetail) {
        this.articleDetail = articleDetail;
    }

    public int getAid() {
        return aid;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public String getArticle_abstract() {
        return article_abstract;
    }


    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public void setArticle_abstract(String article_abstract) {
        this.article_abstract = article_abstract;
    }

    @Override
    public String toString() {
        return "Article{" +
                "aid=" + aid +
                ", title='" + title + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", article_abstract='" + article_abstract + '\'' +
                '}';
    }
}
