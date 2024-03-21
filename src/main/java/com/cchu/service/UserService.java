package com.cchu.service;

import com.cchu.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cchu.utils.Result;

/**
* @author 000
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-01-28 13:24:42
*/
public interface UserService extends IService<User> {

    Result login(User user);


    Result getUserInfo(String token);

    Result userList();
    Result checkUserName(String username);

    Result regist(User user);

}
