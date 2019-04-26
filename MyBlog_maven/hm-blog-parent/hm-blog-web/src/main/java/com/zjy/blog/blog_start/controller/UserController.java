package com.zjy.blog.blog_start.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.ConstraintViolationException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjy.blog.blog_start.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zjy.blog.blog_start.domain.Authority;
import com.zjy.blog.blog_start.domain.User;
import com.zjy.blog.blog_start.service.AuthorityService;
import com.zjy.blog.blog_start.service.UserService;
import com.zjy.blog.blog_start.util.ConstraintViolationExceptionHandler;
import com.zjy.blog.blog_start.vo.Response;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * User 控制器.
 * 
 * @since 1.0.0 2019
 * @author 赵健宇
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;

	@Autowired
    private HttpUtils httpUtils;

    /**
     * 查询所有用户
     *
     * @param async 同步/异步
     * @param pageIndex 起始
     * @param pageSize 每页size
     * @param name 姓名
     * @param model model
     * @return
     */
    @GetMapping
    public ModelAndView list(@RequestParam(value="async",required=false) boolean async,
            @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
            @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
            @RequestParam(value="name",required=false,defaultValue="") String name,
            Model model) {

        Pageable pageable = new PageRequest(pageIndex, pageSize);
        /**
         *
         *  注释掉过去调用本地服务
        Page<User> page = userService.listUsersByNameLike(name, pageable);

        List<User> list = page.getContent();

        model.addAttribute("page", page);
        model.addAttribute("userList", list);
        return new ModelAndView(async==true?"users/list :: #mainContainerRepleace":"users/list", "userModel", model);
        **/


        String resultJson = (String) httpUtils.get("http://localhost:9001?" +
                "pageIndex="+pageIndex
                +"&pageSize="+pageSize+
                "&name="+name);
        JSONArray page2 = JSONArray.parseArray(resultJson);
        List contentList = new ArrayList();
        for (int i = 0; i < page2.size(); i++) {
            JSONObject json = (JSONObject) page2.get(i);
            User user = json.toJavaObject(User.class);
            List auth = (List) json.get("authorities");

            //设置权限
            user.setAuthorities(handlerAuth(auth));

            contentList.add(user);
        }
        Page<User> userPage = new PageImpl<User>(contentList,pageable,contentList.size());
        /**
         * 当前所在页面数据列表
         */
        model.addAttribute("page", userPage);
        model.addAttribute("userList", contentList);
        return new ModelAndView(async==true?"users/list :: #mainContainerRepleace":"users/list", "userModel", model);
    }
	
    /**
     * 获取创建表单页面
     * @param model
     * @return
     */
    @GetMapping("/add")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user", new User(null, null, null, null));
        return new ModelAndView("users/add", "userModel", model);
    }

	
    /**
     * 保存或者修改用户
     *
     * @param user
     * @return
     */
    @PostMapping
    public ResponseEntity<Response> saveOrUpateUser(User user, Long authorityId) {

        List<Authority> authorities = new ArrayList<>();

        authorities.add(authorityService.getAuthorityById(authorityId));
        user.setAuthorities(authorities);
        
        try {

            User result = userService.saveOrUpateUser(user);
        }  catch (ConstraintViolationException e)  {
            log.warn("保存或者修改用户失败",e);
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        }

        return ResponseEntity.ok().body(new Response(true, "处理成功", user));
    }

	
    /**
     * 删除用户
     * @param id
     * @param model
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id, Model model) {
        try {
            userService.removeUser(id);
        } catch (Exception e) {
            return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
        }
        return  ResponseEntity.ok().body( new Response(true, "处理成功"));
    }
	
    /**
     * 获取修改用户的界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "edit/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {

        String resultJson = (String) httpUtils.get("http://localhost:9001/edit/"+id);
        User user = JSON.parseObject(resultJson,User.class);

        List auth = (List) JSONObject.parseObject(resultJson).get("authorities");

        user.setAuthorities(handlerAuth(auth));

        model.addAttribute("user", user);
        return new ModelAndView("users/edit", "userModel", model);

    }

    /**
     * 对权限进行二次处理
     *
     * @param auth 权限列表
     * @return 权限list
     */
    public List<Authority> handlerAuth(List auth){
        List<Authority> array = new ArrayList<>();
        for (int j = 0; j < auth.size(); j++) {
            Authority authority = new Authority();
            authority.setId((long) (j+1));
            authority.setName((String) ((JSONObject)auth.get(j)).get("authority"));
            array.add(authority);
        }
        return array;
    }
}
