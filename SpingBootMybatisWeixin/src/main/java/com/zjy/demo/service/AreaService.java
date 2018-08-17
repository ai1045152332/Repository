package com.zjy.demo.service;

import com.zjy.demo.entity.Area;

import java.util.List;

public interface AreaService {
    /*与dao巧合相同,其实这里面的方法可以有好多dao操作
    * service属于逻辑处理层
    * */
    List<Area> getAreaList();
    Area getAreaById(int i);
    boolean addArea(Area area);
    boolean modifyArea(Area area);
    boolean deleteArea(int areaId);
}
