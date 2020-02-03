package com.deltaplus.mapper;

import com.deltaplus.beans.Article;
import com.deltaplus.beans.ArticleDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/*@Repository注解属于最先引入的一批，它用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean*/
@Repository
public interface ArticleMapper {
    public Integer insertArticleAbs(Article article);
    public void insertArticleDetail(ArticleDetail articleDetail);
    public Article getArticleById(Integer aid);
    public List<Article> getArticleList();
}
