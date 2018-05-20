package com.zjy.cloudnote.service.impl;

import com.zjy.cloudnote.dao.CnGroupDao;
import com.zjy.cloudnote.entity.CnGroup;
import com.zjy.cloudnote.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    private CnGroupDao cnGroupDao;

    @Override
    public int deleteByPrimaryKey(Long groupId) {
        return 0;
    }

    @Override
    public int insert(CnGroup record) {
        return 0;
    }

    @Override
    public CnGroup selectByPrimaryKey(Long groupId) {
        return null;
    }

    @Override
    public List<CnGroup> selectAll() {
        List list = cnGroupDao.selectAll();
        return list;
    }

    @Override
    public int updateByPrimaryKey(CnGroup record) {
        return 0;
    }
}
