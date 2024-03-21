package com.cchu.controller;

import com.cchu.pojo.vo.PortalVo;
import com.cchu.service.HeadlineService;
import com.cchu.service.TypeService;
import com.cchu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("portal")
public class PortalContrller {
    @Autowired
    private TypeService typeService;
    @Autowired
    private HeadlineService headlineService;


    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        Result result = typeService.findAllTypes();
        return result;
    }
    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){
        Result result = headlineService.findNewsPage(portalVo);
        return result;
    }
    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid){
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }

}
