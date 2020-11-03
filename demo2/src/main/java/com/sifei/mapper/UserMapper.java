package com.sifei.mapper;

import com.sifei.domain.News;
import com.sifei.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
public interface UserMapper {
     List<User> queryUserList();
     void add(User user);
     User Login(@Param( "username")String username, @Param( "password")String password);
     void update(User user);
     Boolean searchUserName(String username);
     Boolean Judgelogin(@Param( "username")String username,@Param( "password")String password );



}
