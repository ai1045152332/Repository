package com.zjy.cloudnote.controller;

import com.zjy.cloudnote.dao.CnGroupMapper;
import com.zjy.cloudnote.entity.CnGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("cloudnote")
public class GroupController {
    @Autowired
    private CnGroupMapper cnGroupMapper;

    @RequestMapping(value="/grouplist",method = RequestMethod.GET)
    private Map<String ,Object> list(){
        Map<String,Object> map = new HashMap<>();
        Long a = 1001l;
        CnGroup list = cnGroupMapper.selectByPrimaryKey(a);
        map.put("groupList",list);
        return map;
    }
}
