package com.zjy.cloudnote.dao;

import com.zjy.cloudnote.entity.Group;

import java.util.List;

public interface GroupDao {
    List<Group> queryGroup();
    Group queryOneGroupById(Integer groupId);

}
