package com.zjy.cloudnote.dao;

import com.zjy.cloudnote.entity.CnUser;
import java.util.List;

public interface CnUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(CnUser record);

    CnUser selectByPrimaryKey(Integer userId);

    List<CnUser> selectAll();

    int updateByPrimaryKey(CnUser record);
}