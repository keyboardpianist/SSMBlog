package com.deltaplus.service.impl;

import com.deltaplus.beans.Article;
import com.deltaplus.mapper.ArticleMapper;
import com.deltaplus.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ASI")
public class ArticlesServiceImpl implements ArticlesService {
    /**
     * 受DI容器管理的类只是一个普通的java对象，是能够被独立实例化的。通过这种方式，可以在单元测试中实例化它，而不需要启动DI容器去实例化它。
     * 如果没有DI容器耦合，不管有没有被DI容器管理，都可以实例化这个类，甚至切换到新的DI框架。简而言之，就是可以脱离spring的管理去操作这个类。
     *
     * 在使用set方式时，这是一种选择注入，可有可无，即使没有注入这个依赖，那么也不会影响整个类的运行。
     * 在使用构造器方式时已经显式注明必须强制注入。通过强制指明依赖注入来保证这个类的运行。
     */
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticlesServiceImpl(ArticleMapper articleMapper)
    {
        this.articleMapper = articleMapper;
    }

    @Override
    public List<Article> getArticleList() throws Exception {
        List<Article> list = articleMapper.getArticleList();
        return list;
    }

    @Override
    public Article getArticle(Integer aid) throws Exception {
        return articleMapper.getArticleById(aid);
    }

    @Override
    public void insertArticle(Article article) throws Exception {
        StringBuffer sb = new StringBuffer(article.getArticleDetail().getDetail());
        if (sb.length() > 200)
        {
            sb.setLength(197);
            sb.append("...");
        }
        article.setArticle_abstract(sb.toString());/*获取前200个字符作为摘要*/
        /*int newAid = articleMapper.insertArticleAbs(article);不能这么写，因为他返回的是插入的条数*/
        articleMapper.insertArticleAbs(article);
        article.getArticleDetail().setAid(article.getAid());
        articleMapper.insertArticleDetail(article.getArticleDetail());
    }
}
