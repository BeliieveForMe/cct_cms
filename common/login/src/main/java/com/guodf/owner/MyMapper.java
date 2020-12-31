package com.guodf.owner;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @ClassName MyMapper
 * @description:
 * @author: BeliieveForMe-GuoDF
 * @Email: 731998663@qq.com
 * @github: https://github.com/BeliieveForMe
 * @date: Created in 2020/12/29 9:21
 * @Version 1.0.0
 */

public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}