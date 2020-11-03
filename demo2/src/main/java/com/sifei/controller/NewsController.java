package com.sifei.controller;


import com.sifei.domain.News;
import com.sifei.domain.User;
import com.sifei.mapper.NewsMapper;
import com.sifei.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Api(value = "新闻控制器")
@RequestMapping("news")
public class NewsController {

    protected final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private NewsMapper newsMapper;


//    @RequestMapping("/news")

//    public void news(ModelMap modelMap ,String news){
//         User user = (User)modelMap.get("id");
//         Integer id = user.getId();
//         userMapper.news(news,id);
//    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加新闻")
    public void add(String title,String news, HttpServletRequest request){

        User user =(User) request.getSession().getAttribute("abc");
        System.out.println(user);
        Integer id = user.getId();
        newsMapper.news(title,news,id);
    }
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    @ApiOperation(value = "根据标题或新闻内容查找新闻")
    public List<News> search(String search){

        List<News> result = newsMapper.search(search);
        return result;
    }


    @RequestMapping(value = "/paging-query",method = RequestMethod.POST)
    @ApiOperation(value = "新闻的分页查询")
    public List<News> searchAll(int page,int size){

        if (page==1||page==0){
            page=1;

        }else {
            page= (page-1)*size;
        }
        List<News> news = newsMapper.searchAll(page,size);
        return news;

    }

    @RequestMapping(value = "/search-user-news",method = RequestMethod.POST)
    @ApiOperation(value = "查询用户所发布的所有新闻")
    public List<News> searchUserAll(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("abc");
        List<News> result = newsMapper.searchUserAll(user.getId());
        return result;
    }

    @RequestMapping(value = "/search-new-title",method = RequestMethod.POST)
    @ApiOperation(value = "根据标题查找新闻")
    public News searchNews(String title){
        News news = newsMapper.searchNews(title);
        return news;
    }

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @ApiOperation(value = "发表评论")
    public String comment( String title,HttpServletRequest request,String text){
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("abc");
        Integer uid = user.getId();
        News news = newsMapper.searchNews(title);
        Integer nid = news.getId();
        newsMapper.comment(uid, nid, text);
        System.out.println("成功！");
        return text;
    }


    @RequestMapping(value = "/search-id",method = RequestMethod.POST)
    @ApiOperation(value = "根据id查找新闻")
    public News searchNews(int id){
        News news = newsMapper.searchId(id);
        return news;
    }


}
