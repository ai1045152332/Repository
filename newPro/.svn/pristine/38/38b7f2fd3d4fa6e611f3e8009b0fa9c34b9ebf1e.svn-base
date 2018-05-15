package com.honghe.recordweb.action.project.authority;

import com.honghe.recordhibernate.dao.AuthorityDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.Authority;
import com.honghe.recordhibernate.entity.Role;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.service.project.authority.AuthorityService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Moon on 2014/8/14.
 */
public class AuthorityAction extends BaseAction
{
    private final static Logger logger = Logger.getLogger(AuthorityAction.class);
    @Resource
    private AuthorityService authorityService;

    public Integer getAuthId() {
        return authId;
    }
    public void setAuthId(Integer authId) {
        this.authId = authId;
    }
    private Integer authId;

    public void setAuthname(String authname)
    {
        this.authname = authname;
    }
    public String getAuthname()
    {
        return this.authname;
    }
    private String authname;

    public void setAuthword(String authword)
    {
        this.authword = authword;
    }
    public String getAuthword()
    {
        return this.authword;
    }
    private String authword;

    public void setAuthdesc(String authdesc)
    {
        this.authdesc = authdesc;
    }
    public String getAuthdesc()
    {
        return this.authdesc;
    }
    private String authdesc;

    public Integer getAuthrange() {
        return authrange;
    }
    public void setAuthrange(Integer authrange) {
        this.authrange = authrange;
    }
    private Integer authrange;

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

    public List getAuthoritylist() {
        return authoritylist;
    }
    public void setAuthoritylist(List authoritylist) {
        this.authoritylist = authoritylist;
    }
    private List authoritylist;

    public Authority getAuthorityinfo() {
        return authorityinfo;
    }
    public void setAuthorityinfo(Authority authorityinfo) {
        this.authorityinfo = authorityinfo;
    }
    private Authority authorityinfo;

    public String authorityList()
    {
        if(this.currentPage==0 )
        {
            this.currentPage =1;
        }
        Page<Authority> page = new Page<Authority>(currentPage, 13);
        authorityService.authorityListService(page);
        this.pageCount = page.getTotalPageSize();
        this.authoritylist = page.getResult();
        return "authlist";
    }

    public String authorityDetail()
    {
        if(authId != -1)
        {
            this.authorityinfo = (Authority)authorityService.getAuthorityinfoByIdService(authId);
        }
        return "roledetail";
    }
    public void addAuthority()
    {
        try
        {
            JSONObject json = new JSONObject();
            if (authname.equals("") || authword.equals(""))
            {
                json.put("success",false);
                json.put("msg","请填写完整！");
            }
            else
            {
                List<Authority> authlist = null;

                authlist = authorityService.getAuthorityByNameService(authname);
                if (authlist!=null)
                {
                    json.put("success",false);
                    json.put("msg","权限名称重复！");
                }
                else
                {
                    authlist = authorityService.getAuthorityByWordService(authword);
                    if (authlist!=null)
                    {
                        json.put("success",false);
                        json.put("msg","权限字符重复！");
                    }
                    else
                    {
                        Authority authority = new Authority();
                        authority.setAuthName(authname);
                        authority.setAuthWord(authword);
                        authority.setAuthDesc(authdesc);
                        authority.setAuthRange(authrange);
                        authorityService.addAuthorityService(authority);
                        json.put("success",true);
                        json.put("msg","添加成功！");
                    }
                }
            }
            writeJSON(json.toString());
        }
        catch (Exception e)
        {
            logger.error("",e);
            //e.printStackTrace();
        }
    }

    public void editAuthority()
    {
        try
        {
            JSONObject json = new JSONObject();
            Authority auth = authorityService.getAuthorityinfoByIdService(authId);
            if (auth==null)
            {
                json.put("success",false);
                json.put("msg","没有找到要更新的数据！");
            }
            else
            {
                if(authname != "" && authname != null && !auth.getAuthName().equals(authname)) {
                    List<Authority> authlist = authorityService.getAuthorityByNameService(authname);
                    if (authlist!=null)
                    {
                        json.put("success",false);
                        json.put("msg","权限名称重复！");
                    }
                    else {
                        auth.setAuthName(authname);
                    }
                }
                if(!auth.getAuthWord().equals(authword)) {
                    List<Authority> authlist = authorityService.getAuthorityByWordService(authword);
                    if(authlist!=null)
                    {
                        json.put("success",false);
                        json.put("msg","权限字符重复！");
                    }
                    else {
                        auth.setAuthWord(authword);
                    }
                }
                if(json.size()<=0) {
                    auth.setAuthRange(authrange);
                    auth.setAuthDesc(authdesc);

                    if (authorityService.updateAuthorityService(auth)) {
                        json.put("success", true);
                        json.put("msg", "修改成功！");
                    } else {
                        json.put("success", true);
                        json.put("msg", "修改失败！");
                    }
                }
            }
            writeJSON(json.toString());
        }
        catch (Exception e)
        {
            logger.error("",e);
            //e.printStackTrace();
        }
    }

    public void delAuthority()
    {
        JSONObject json = new JSONObject();
        if(authorityService.authRoleService(authId))
        {
            json.put("success",false);
            json.put("msg","该权限已经分配给角色，请先清除关系再删除");
        }
        else {
            Authority authority = authorityService.getAuthorityinfoByIdService(authId);
            if(authority==null)
            {
                json.put("success", true);
                json.put("msg", "删除成功！");
            }
            else {
                if (authorityService.delAuthorityService(authority)) {
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
}
