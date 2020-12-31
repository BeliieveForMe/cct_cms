package com.guodf.owner.controller;

import com.guodf.owner.BaseOut;
import com.guodf.owner.entities.SysCmsLoginInfo;
import com.guodf.owner.mapper.SysCmsLoginInfoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.Map;


/**
 * @ClassName 登录接口
 * @Description TOOD
 * @Author Administrator
 * @Date 2020/12/17 2:39
 * @Version 1.0
 **/
@RestController
@Slf4j
@RequestMapping("/V1/admin")
@Api(value = "/V1/admin",tags ={"登录接口"})
public class LoginController {

    @Resource
    SysCmsLoginInfoMapper sysCmsLoginInfoMapper;

    /**
     * 登陆接口
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/login")
    @ApiOperation(value = "登录接口调用", notes = "登录接口调用")
    @ResponseBody
    public BaseOut loginRequest(@RequestBody Map<String, String> param, @Context HttpServletRequest request){
        BaseOut out = new BaseOut();
        try {
            String username = param.get("username");
            String password = param.get("password");
            SysCmsLoginInfo sysCmsLoginInfo=sysCmsLoginInfoMapper.getInfo(username);
            String passwords = sysCmsLoginInfo.getPassword().toString();
            if (passwords.equals(password)){
                out.setResult(200);
                out.setInfo("密码正确，登录成功");
            }else{
                out.setResult(400);
                out.setInfo("密码错误，登录失败");
            }
            return out;
        }catch (Exception e){
            e.printStackTrace();
        }
        return out;
    }
}
