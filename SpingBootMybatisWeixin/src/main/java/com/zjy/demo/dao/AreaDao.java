package com.zjy.demo.dao;

import com.zjy.demo.entity.Area;

import java.util.List;

public interface AreaDao {
    List<Area> queryArea();
    Area queryAreaById(int i);
    int insertArea(Area area);
    int updateArea(Area area);
    int deleteArea(int areaId);

}

