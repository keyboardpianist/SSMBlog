package com.deltaplus.controller;

import com.deltaplus.beans.Article;
import com.deltaplus.beans.ArticleDetail;
import com.deltaplus.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ArticlesController {

    class Code{
        private Integer code;

        public Code(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
    }
    
    private final ArticlesService articlesService;

    @Autowired
    public ArticlesController(ArticlesService articlesService)
    {
        System.out.println("ArticlesController init");
        this.articlesService = articlesService;
    }

    @RequestMapping(value="/Upload", method= {RequestMethod.POST})
    public @ResponseBody Code uploadFile(@RequestParam("file[]") MultipartFile[] file)//ajax表单形式上传文件必须使用RequestParam(非json数据)
    {
        Code code = new Code(200);
        try {
                //SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());
                //System.out.println(formatter.format(date));
                //String curTime = formatter.format(date);
                for (MultipartFile mFile : file)
                {
                    //System.out.println(mFile.getOriginalFilename() + ":");
                    Article article = new Article();
                    ArticleDetail articleDetail = new ArticleDetail();
                    article.setTitle(mFile.getOriginalFilename().split("\\.")[0]);
                    InputStream inputStream = mFile.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String lineContent = null;
                    StringBuffer content = new StringBuffer();
                    while((lineContent = bufferedReader.readLine()) != null)
                    {
                        //System.out.println(content);
                        content.append(lineContent + "\n");
                    }
                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStream.close();

                    articleDetail.setDetail(content.toString());
                    content.delete(0, content.length());
                    article.setArticleDetail(articleDetail);
                    article.setCreatetime(date);
                    //System.out.println(article.getTitle());
                    articlesService.insertArticle(article);
            }
            /*SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            System.out.println(formatter.format(date));*/

            //System.out.println(fileName);
            /*String headPath = "D://" + fileName;
            File dest = new File(headPath);

            // 获取文件的二进制数组
            byte[] bytes = file.getBytes();
            // 上传到数据库
            student.setHead(bytes);

            // 把数据封装到对象中
            student.setHeadPath(headPath);
            studentService.regist(student);

            // 把文件写到磁盘
            file.transferTo(dest);*/
        } catch (Exception e) {
            e.printStackTrace();
            code.setCode(500);
        } finally {
            return code;
        }
    }

    @RequestMapping(value="/Article/{aid}", method= {RequestMethod.POST})
    public @ResponseBody Article getArticle(@PathVariable("aid") Integer aid) throws Exception{
        Article article = articlesService.getArticle(aid);

        return article;
    }

    @RequestMapping(value="/List", method= {RequestMethod.POST})
    public @ResponseBody List<Article> getArticleList() throws Exception{
        List<Article> list = articlesService.getArticleList();
        return list;
    }

    @RequestMapping(value="/AddArticle", method= {RequestMethod.POST})
    public void insertArticle(@RequestBody Article article) throws Exception{
        articlesService.insertArticle(article);
    }
}
