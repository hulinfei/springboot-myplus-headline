package com.cchu.service;

import com.cchu.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cchu.pojo.vo.PortalVo;
import com.cchu.utils.Result;

/**
* @author 000
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-01-28 13:24:42
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(Headline headline, String token);

    Result update(Headline headline);
}
