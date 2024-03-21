package com.cchu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cchu.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cchu.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author 000
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-01-28 13:24:42
* @Entity com.cchu.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {
    IPage<Map> selectMyPage(IPage page,@Param("portalVl") PortalVo portalVo);

    Map queryDetailMap(Integer hid);
}




