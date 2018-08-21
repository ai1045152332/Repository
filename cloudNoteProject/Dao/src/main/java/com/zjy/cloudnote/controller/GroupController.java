package com.zjy.cloudnote.controller;

import com.zjy.cloudnote.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("cloudnote")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping("/group")
    public Map<String ,Object> groupList(){
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("list",groupService.selectAll());
        return map;
    }
}
