package com.sifei.controller;

import com.sifei.domain.User;
import com.sifei.exception.BusinessException;
import com.sifei.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "用户控制器")
@RequestMapping("user")
//@SessionAttributes(value = "abc")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/query",method = {RequestMethod.POST})
    @ApiOperation(value = "查询所有用户")
    public List<User> queryUser(){
        List<User> users = userMapper.queryUserList();
        return users;
    }


    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    @ApiOperation(value = "注册")
    public User add( String username, String password,String password2, String name){

        if (password!=password2){
            throw new BusinessException("密码输入不一致！！！！");
        }
//        int i=1/0;
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        if (user.getUsername()==null||user.getPassword()==null||user.getName()==null){
            throw new BusinessException("请输入完整的数据！！！！");
        }
        userMapper.add(user);
        return user;
    }



    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @ApiOperation(value = "登入")
    public User login( HttpServletRequest request,  @RequestParam(name = "username") String username , String password){


        if (userMapper.searchUserName(username)){

            Boolean judgelogin = userMapper.Judgelogin(username, password);
            System.out.println(judgelogin);

            if (userMapper.Judgelogin(username,password)){
                User login = userMapper.Login(username, password);
                request.getSession().setAttribute("abc",login);
                System.out.println("登入成功！");
                return login;
            }
            throw new BusinessException("密码错误！");

        }
        throw new BusinessException("用户名不存在");
    }



    @RequestMapping(value = "/modify", method = {RequestMethod.POST})
    @ApiOperation(value = "修改密码")
    public User modify(@Valid String username,String password ,String  newpassword){

        User login = userMapper.Login(username, password);
        login.setPassword(newpassword);
         userMapper.update(login);
        System.out.println("成功！");
        return login;

    }

}
