package com.deltaplus.service;

import com.deltaplus.beans.Article;

import java.util.List;

public interface ArticlesService {
    public List<Article> getArticleList() throws Exception;
    public Article getArticle(Integer aid) throws Exception;
    public void insertArticle(Article article) throws Exception;
}
