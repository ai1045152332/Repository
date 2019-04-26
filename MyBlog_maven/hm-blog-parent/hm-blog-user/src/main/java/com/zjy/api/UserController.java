package com.zjy.api;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zjy.entity.Result;
import com.zjy.entity.User;
import com.zjy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * user controller
 * 欢迎
 *
 * @author zhaojianyu
 * @create 2018-11-14 8:04 PM
 */
@RestController
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户
     * http://192.168.0.100:9001/getUser/7
     * @return
     */
    @RequestMapping("/getUser/{id}")
    @HystrixCommand(fallbackMethod = "exceptionHandler")
    public Object getUser(@PathVariable("id")String id) {
        Result result = new Result("getUser_ACk",0);
        result.setData(userService.getUserById(Long.valueOf(id)));
        return result;
    }
    public Object exceptionHandler(@PathVariable("id")String id){
        Result result = new Result("getUser_ACk",0);
        result.setData("user2出现异常Hystrix已处理");
        return result;
    }


    /**
     * 查询所有用户
     *
     * @param async
     * @param pageIndex
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/")
    public List list(@RequestParam(value="async",required=false) boolean async,
                     @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
                     @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
                     @RequestParam(value="name",required=false,defaultValue="") String name) {

        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<User> page = userService.listUsersByNameLike(name, pageable);
        return page.getContent();
    }



    /**
     * 保存或者修改用户
     * @param user
     * @return
     */
    @PostMapping("/")
    public Result saveOrUpateUser(User user) {
        Result result = new Result("saveOrUpateUser_ACk",0);
        try {
            userService.saveOrUpateUser(user);
        }  catch (ConstraintViolationException e) {
            result.setCode(-3);
            log.error(e.toString());
            return result;
        }
        result.setData(true);
        return result;
    }


    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable("id") Long id) {
        Result result = new Result("delete_ACk",0);
        try {
            userService.removeUser(id);
        } catch (Exception e) {
            result.setCode(-3);
            log.error(e.toString());
            return result;
        }
        return result;
    }

    /**
     * 修改用户
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/edit/{id}")
    public User modifyForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        return user;
    }

}
