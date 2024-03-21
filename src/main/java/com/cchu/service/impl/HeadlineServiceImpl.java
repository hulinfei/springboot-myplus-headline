package com.cchu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cchu.pojo.Headline;
import com.cchu.pojo.vo.PortalVo;
import com.cchu.service.HeadlineService;
import com.cchu.mapper.HeadlineMapper;
import com.cchu.utils.JwtHelper;
import com.cchu.utils.Result;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.CDATASection;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 000
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-01-28 13:24:42
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{
    @Autowired
    private HeadlineMapper headlineMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result findNewsPage(PortalVo portalVo) {
        IPage<Map> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());
        headlineMapper.selectMyPage(page,portalVo);
        List<Map> records = page.getRecords();
        Map data = new HashMap<>();
        data.put("pageData", records);
        data.put("pageNum",page.getCurrent());
        data.put("pageSize",page.getSize());
        data.put("totalPage", page.getPages());
        data.put("totalSize",page.getTotal());
        Map pageInfo = new HashMap();
        pageInfo.put("pageInfo", data);
        return Result.ok(pageInfo);
    }
/*
* 详情
* 1、修改阅读量
* 2、查询对应的数据即可【多表  头条和用户表，方法需要自定义 返回map】
* */
    @Override
    public Result showHeadlineDetail(Integer hid) {
        Map data = headlineMapper.queryDetailMap(hid);
        Map headlinMap = new HashMap();
        headlinMap.put("headline", data);
//        修改阅读量
        Headline headline = new Headline();
        headline.setHid((Integer) data.get(hid));
        headline.setVersion((Integer) data.get("version"));
        headline.setPageViews((Integer) data.get("pageView") + 1);
        headlineMapper.updateById(headline);
        return Result.ok(headlinMap);
    }

    @Override
    public Result publish(Headline headline, String token) {
        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headlineMapper.insert(headline);
        return Result.ok(null);
    }

    @Override
    public Result update(Headline headline) {
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();
        headline.setVersion(version);
        headline.setUpdateTime(new Date());
        headlineMapper.updateById(headline);
        return Result.ok(null);
    }
}




