package com.cchu.controller;

import com.cchu.pojo.User;
import com.cchu.service.UserService;
import com.cchu.utils.JwtHelper;
import com.cchu.utils.Result;
import com.cchu.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtHelper jwtHelper;


    @PostMapping("login")
    public Result login(@RequestBody User user){
        Result result = userService.login(user);
        return result;
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token){
        Result result = userService.getUserInfo(token);
        return result;
    }
    @GetMapping("userList")
    public Result userList(){
        Result result = userService.userList();
        return result;
    }
    @PostMapping("checkUserName")
    public Result checkUserName(@RequestBody String username){
        Result result = userService.checkUserName(username);
        return result;
    }
    @PostMapping("regist")
    public Result regist(@RequestBody User user){
        Result result = userService.regist(user);
        return result;
    }
    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token){
        boolean expiration = jwtHelper.isExpiration(token);
        if (expiration) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        return Result.ok(null);
    }
}
