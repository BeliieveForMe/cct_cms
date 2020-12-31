package com.guodf.owner.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName TestController
 * @Description TOOD
 * @Author Administrator
 * @Date 2020/12/172:39
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(value = "/ttt",tags ={"測試"})
public class TestController {

    @CrossOrigin
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ApiOperation(value = "ceshi ")
    public String logTest(String name, String age) {
        log.info("logTest,name:{},age:{}", name, age);
        return "success";
    }

    //@CrossOrigin 后端开启跨域访问
    @CrossOrigin
    @PostMapping(value = "/getdata")
    public String getdata(){
        return "hxr";
    }

    /**
     * 模拟登陆接口
     * @return
     */
//    @CrossOrigin
    @PostMapping(value = "/admin/login")
    public String login(){

        return "hxr";
    }
}
