package com.guodf.owner.mapper;

import com.guodf.owner.entities.SysCmsLoginInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SysCmsLoginInfoMapper {

    SysCmsLoginInfo getInfo(@Param(value = "username") String username);
}