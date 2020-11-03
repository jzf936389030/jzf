package com.sifei.mapper;

import com.sifei.domain.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface NewsMapper {
     void news(@Param( "title")String title, @Param( "news")String news, @Param( "id")Integer id);
     List<News> search(String search);
     List<News> searchAll(@Param( "page")int page,@Param( "size")int size);
     List<News> searchUserAll(Integer id);
     News searchNews(String title);
     void comment(@Param("nid")Integer nid,@Param("uid")Integer uid,@Param("text")String text);
     News searchId(int id);

}
