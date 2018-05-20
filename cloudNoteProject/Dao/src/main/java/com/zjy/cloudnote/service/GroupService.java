package com.zjy.cloudnote.service;

import com.zjy.cloudnote.entity.CnGroup;
import java.util.List;

public interface GroupService {
    int deleteByPrimaryKey(Long groupId);

    int insert(CnGroup record);

    CnGroup selectByPrimaryKey(Long groupId);

    List<CnGroup> selectAll();

    int updateByPrimaryKey(CnGroup record);
}
