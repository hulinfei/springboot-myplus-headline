package com.cchu.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cchu.pojo.User;
import com.cchu.service.UserService;
import com.cchu.mapper.UserMapper;
import com.cchu.utils.JwtHelper;
import com.cchu.utils.MD5Util;
import com.cchu.utils.Result;
import com.cchu.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 000
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-01-28 13:24:42
*/
/*
 * 登录业务
 * 1、根据账号，查询用户对象
 * 2、如果用户对象为null，查询失败，账号错误
 * 3、对比密码，失败，返回503错误
 * 4、根据用户id生成一个token，token->result返回
 * */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = userMapper.selectOne(lambdaQueryWrapper);
        if (loginUser == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
//        对比密码
        if (StringUtils.isEmpty(user.getUserPwd()) && MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())){
//            登录成功  根据用户id生成token  将token返回
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            Map data = new HashMap<>();
            data.put(token, token);
            return Result.ok(data);
        }
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }
/*
* 根据token获取用户信息
* 1、token是否在有效期
* 2、根据token解析userId
* 3、根据用户id查询用户数据
* 4、去掉密码，封装result结果返回
* */
    @Override
    public Result getUserInfo(String token) {
        boolean expiration = jwtHelper.isExpiration(token);
        if (expiration) {
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        int userId = jwtHelper.getUserId(token).intValue();
        User user = userMapper.selectById(userId);
        user.setUserPwd("");
        Map data = new HashMap<>();
        data.put("loginUser", user);
        return Result.ok(data);
    }

    @Override
    public Result userList() {
        List<User> list = userMapper.selectList(null);
        return Result.ok(list);
    }
/*
* 检查账号是否可用
* 1、根据账号count查询
* 2、count==0可用
* 3、count！=0 不可用
* */
    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        Long count = userMapper.selectCount(queryWrapper);
        if (count == 0){
            return Result.ok(null);
        }
        return Result.build(null,ResultCodeEnum.USERNAME_USED);
    }

    @Override
    public Result regist(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        Long count = userMapper.selectCount(queryWrapper);
        if (count > 0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        userMapper.insert(user);
        return Result.ok(null);

    }
}




