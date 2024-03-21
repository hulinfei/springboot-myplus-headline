package com.cchu.service;

import com.cchu.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cchu.utils.Result;

/**
* @author 000
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-01-28 13:24:42
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();
}
