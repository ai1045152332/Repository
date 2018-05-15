package com.honghe.recordweb.action.project.role;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.RoleDao;
import com.honghe.recordhibernate.entity.Authority;
import com.honghe.recordhibernate.entity.Role;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.service.project.authority.AuthorityService;
import com.honghe.recordweb.service.project.role.RoleService;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.*;

@Controller
@Scope(value="prototype")
public class RoleAction extends BaseAction
{
    @Resource
    private RoleService roleService;
    @Resource
    private AuthorityService authorityService;

    private String rolename;
    public void setRolename(String name)
    {
        this.rolename = name;
    }
    public String getRolename()
    {
        return rolename;
    }

    private String authStr;
    public String getAuthStr() {
        return authStr;
    }
    public void setAuthStr(String authStr) {
        this.authStr = authStr;
    }

    private int roleId;
    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public List getRolelist() {
        return rolelist;
    }
    public void setRolelist(List rolelist) {
        this.rolelist = rolelist;
    }
    private List rolelist;

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    private int userId;

    public List getAuthlist() {
        return authlist;
    }
    public void setAuthlist(List authlist) {
        this.authlist = authlist;
    }
    private List authlist;

    public Role getRoleinfo() {
        return roleinfo;
    }
    public void setRoleinfo(Role roleinfo) {
        this.roleinfo = roleinfo;
    }
    private Role roleinfo;

    public List getRoleauthlist() {
        return roleauthlist;
    }
    public void setRoleauthlist(List roleauthlist) {
        this.roleauthlist = roleauthlist;
    }
    private List roleauthlist;

    public String getRoleIdStr() {
        return roleIdStr;
    }
    public void setRoleIdStr(String roleIdStr) {
        this.roleIdStr = roleIdStr;
    }
    private String roleIdStr;

    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public int pageCount;

    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int currentPage;

    public String roleList()
    {
        if(this.currentPage==0 )
        {
            this.currentPage =1;
        }
        Page<Role> page = new Page<Role>(currentPage, 15);
        roleService.roleListConditionService(page);
        this.pageCount = page.getTotalPageSize();
        this.rolelist = page.getResult();
        return "rolelist";
    }

    public String roleListBack() {
        if(this.currentPage==0 )
        {
            this.currentPage =1;
        }
        Page<Role> page = new Page<Role>(currentPage, 13);
        roleService.roleListService(page);
        this.pageCount = page.getTotalPageSize();
        this.rolelist = page.getResult();
        return "rolelistback";
    }

    public void addRole()
    {
        JSONObject json = new JSONObject();
        if (rolename==null || rolename.equals(""))
        {
            json.put("success", false);
            json.put("msg", "请填写完整！");
        }
        else
        {
            List<Role> rolelist = null;
            rolelist = roleService.getRoleByNameService(rolename);
            if (rolelist != null)
            {
                json.put("success", false);
                json.put("msg", "角色名重复！");
            }
            else
            {
                Role role = new Role();
                role.setRoleName(rolename);
                if(roleService.addRoleService(role,authStr))
                {
                    json.put("success", true);
                    json.put("msg", "添加成功！");
                }
                else
                {
                    json.put("success", false);
                    json.put("msg", "添加失败！");
                }
            }
        }
        String jsonstr = json.toString();
        writeJSON(jsonstr);
    }
    /**
     * 修改角色及权限关系 *
     */
    public void editRole()
    {
        JSONObject json = new JSONObject();
        Role role = roleService.getRoleinfoByIdService(roleId);
        if (role==null)
        {
            json.put("success",false);
            json.put("msg","没有找到要更新的数据！");
        }
        else {
            if (rolename != null && !rolename.equals("") && !role.getRoleName().equals(rolename))
            {
                List<Role> rolist = roleService.getRoleByNameService(rolename);
                if (rolist == null) {
                    role.setRoleName(rolename);
                }
                else {
                    json.put("success", false);
                    json.put("msg", "角色名重复！");
                }
            }
            if(json.size()<=0)
            {
                if (roleService.updateRoleService(role, authStr)) {
                    json.put("success", true);
                    json.put("msg", "角色修改成功！");
                } else {
                    json.put("success", false);
                    json.put("msg", "角色修改失败！");
                }
            }
        }
        writeJSON(json.toString());
    }
    /**
     * 修改角色及权限关系 *
     */
    public void delRole()
    {
        JSONObject json = new JSONObject();
        if(roleService.roleUserService(roleId))
        {
            json.put("success",false);
            json.put("msg","该角色已经分配给用户，删除失败");
        }
        else {
            Role role = roleService.getRoleinfoByIdService(roleId);
            if(role == null)
            {
                json.put("success", true);
                json.put("msg", "删除成功！");
            }
            else {
                if (roleService.delRoleService(role)) {
                    json.put("success", true);
                    json.put("msg", "删除成功！");
                } else {
                    json.put("success", false);
                    json.put("msg", "删除失败！");
                }
            }
        }
        writeJSON(json.toString());
    }
    /**
     * 删除多个角色及权限关系 *
     */
    public void delRoleList()
    {
        JSONObject json = new JSONObject();
        String[] rids = roleIdStr.split(",");
        for(String rid : rids) {
            if(roleService.roleUserService(Integer.parseInt(rid)))
            {
                json.put("success", false);
                json.put("msg", "该角色已经分配给用户，删除失败");
                writeJSON(json.toString());
                return;
            }
        }

        boolean result = roleService.delRoleListService(roleIdStr);
        if(result)
        {
            json.put("success", true);
            json.put("msg", "删除成功");
        }
        else
        {
            json.put("success", false);
            json.put("msg", "删除失败");
        }
        writeJSON(json.toString());
    }
    /**
     * 添加、编辑角色 *
     * roleId为 -1，表示添加，否则为编辑
     */
    public String roleDetaile()
    {
        this.authlist = authorityService.authorityFrontListService(userId);
        if(roleId != -1)
        {
            this.roleinfo = (Role)roleService.getRoleinfoByIdService(roleId);
            Iterator iterator = roleinfo.getAuthoritys().iterator();
            List<Map> list = new ArrayList<Map>();
            while (iterator.hasNext())
            {
                Authority roleauth = (Authority)iterator.next();
                Map<Integer,String> authmap = new HashMap<Integer, String>();
                authmap.put(roleauth.getAuthId(),roleauth.getAuthName());
                list.add(authmap);
            }
            this.roleauthlist = list;
        }
        return "roledetail";
    }
    public String roleDetaileBack()
    {
        this.authlist = authorityService.authorityBackListService(userId);
        if(roleId != -1)
        {
            this.roleinfo = (Role)roleService.getRoleinfoByIdService(roleId);
            Iterator iterator = roleinfo.getAuthoritys().iterator();
            List<Map> list = new ArrayList<Map>();
            while (iterator.hasNext())
            {
                Authority roleauth = (Authority)iterator.next();
                Map<Integer,String> authmap = new HashMap<Integer, String>();
                authmap.put(roleauth.getAuthId(),roleauth.getAuthName());
                list.add(authmap);
            }
            this.roleauthlist = list;
        }
        return "roledetail";
    }
}
