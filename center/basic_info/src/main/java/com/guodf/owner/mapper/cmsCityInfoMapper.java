package com.guodf.owner.mapper;

import com.guodf.owner.entities.cmsCityInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface cmsCityInfoMapper {
    int deleteByPrimaryKey(Integer cityId);

    int insert(cmsCityInfo record);

    int insertSelective(cmsCityInfo record);

    cmsCityInfo selectByPrimaryKey(Integer cityId);

    int updateByPrimaryKeySelective(cmsCityInfo record);

    int updateByPrimaryKey(cmsCityInfo record);
}