package com.zjy.demo.controller;

import com.zjy.demo.entity.Area;
import com.zjy.demo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/superadmin")/*上面是跟路由的路径*/
public class AreaController {

    @Autowired
    private AreaService areaService;

    /*
    显示列表
     */
    @RequestMapping(value="/listarea" , method = RequestMethod.GET)
    private Map<String,Object> listArea(){
        Map<String,Object>  modelMap = new HashMap<String , Object>();
        List<Area> list = areaService.getAreaList();
        modelMap.put("areaList",list);
        return modelMap;
    }
    /*
    通过id查找
    通过此路径访问:http://localhost:8080/demo/superadmin/getareabyid?areaId=1
    */
    @RequestMapping(value="/getareabyid" , method = RequestMethod.GET)
    private Map<String, Object> getAreaById(Integer areaId){
        Map<String,Object>  modelMap = new HashMap<String , Object>();
        Area area = areaService.getAreaById(areaId);
        modelMap.put("area",area);
        return modelMap;
    }
    /*添加区域信息*/
    @RequestMapping(value="/addarea" , method = RequestMethod.POST)
    private Map<String, Object> addArea(@RequestBody Area area){/*传入json*/
        Map<String,Object>  modelMap = new HashMap<String , Object>();
        modelMap.put("success",areaService.addArea(area));
        return modelMap;
    }
    /*修改区域信息*/
    @RequestMapping(value="/modifyarea" , method = RequestMethod.POST)
    private Map<String, Object> modifyArea(@RequestBody Area area){/*传入json*/
        Map<String,Object>  modelMap = new HashMap<String , Object>();
        modelMap.put("success",areaService.modifyArea(area));
        return modelMap;
    }
    @RequestMapping(value="/removearea" , method = RequestMethod.GET)
    private Map<String, Object> removeArea(Integer areaId){
        Map<String,Object>  modelMap = new HashMap<String , Object>();
        modelMap.put("success",areaService.deleteArea(areaId));
        return modelMap;
    }
}
