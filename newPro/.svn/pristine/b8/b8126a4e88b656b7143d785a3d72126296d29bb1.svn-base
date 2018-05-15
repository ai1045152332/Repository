package com.honghe.recordweb.service.project.role;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.RoleDao;
import com.honghe.recordhibernate.entity.Role;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.SessionManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wj on 2014-10-11.
 */
@Service
public class RoleService {

    private final static Logger logger = Logger.getLogger(RoleService.class);

    @Resource
    private RoleDao roleDao;

    /**
     * 返回所有角色列表
     *
     * @return String json格式的字符串，异常为“”
     */
    @Transactional
    public List<Role> roleListService() {
        try {
            List list = null;
            list = roleDao.getRoleList();
            return list;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 根据page类方法，返回分页的分组数据
     *
     * @param page Page<Role>
     * @return List<Role>
     */
    @Transactional
    public void roleListService(Page<Role> page) {
        try {
            roleDao.getRoleListBackendByPage(page);
        } catch (Exception error) {
//            error.printStackTrace();
            logger.error("", error);
        }
    }

    /**
     * 返回角色列表，不包括应用开发和工程模式
     *
     * @return String json格式的字符串，异常为“”
     */
    @Transactional
    public List<Role> roleListConditionService() {
        try {
            List list = null;
            User currUser = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USERBACK);
            int currUserId = currUser.getUserId();
            String condition = "";
            if (currUserId == 1 || currUserId == 2) {
                condition = "where roleId != 1 ";
            } else {
                condition = "where roleId != 1 and roleId != 2";
            }
            list = roleDao.getRoleList(condition);
            return list;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 返回前台角色列表，不包括应用开发和工程模式
     *
     * @param
     * @return List<Role>
     */
    @Transactional
    public List<Role> roleListFrontConditionService() {
        try {
            List list = null;
            String condition = "where role_id != 1 and role_id !=2";
            list = roleDao.getRoleList(condition);
            return list;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 返回角色列表，不包括应用开发和工程模式
     *
     * @param page Page<Role>
     * @return List<Role>
     */
    @Transactional
    public void roleListConditionService(Page<Role> page) {
        try {
            String condition = "where role_id != 1 and role_id !=2";
            roleDao.getRoleListFrontendByPage(condition, page);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * 返回所有角色列表（Json格式）     *
     *
     * @return String json格式的字符串，异常为“”
     */
    @Transactional
    public List<Role> getRoleByNameService(String rolename) {
        try {
            List list = null;
            list = roleDao.getRoleByName(rolename);
            return list;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 保存角色信息
     *
     * @param role Role 自定义角色信息，authStr String 权限字符串
     * @return boolean
     */
    @Transactional
    public boolean addRoleService(Role role, String authStr) {
        try {
            if (roleDao.saveRole(role)) {
                if (!authStr.equals("")) {
                    String[] auths = authStr.split(",");
                    for (String auth : auths) {
                        int authid = Integer.parseInt(auth);
                        addRoleAuthService(role.getRoleId(), authid);
                    }
                }
                return true;
            }
            return false;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 给角色分配一个权限
     *
     * @param roleId int 角色id
     * @param authId int 权限id
     * @return
     */
    @Transactional
    public boolean addRoleAuthService(int roleId, int authId) {
        try {
            roleDao.addAuthToRole(roleId, authId);
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * 根据Id获取角色信息
     *
     * @param roleId int 角色id
     * @return Role
     */
    @Transactional
    public Role getRoleinfoByIdService(int roleId) {
        try {
            Role role = null;
            role = roleDao.getRoleById(roleId);
            return role;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 更新角色信息
     *
     * @param role Role自定义角色类型，authStr String类型
     * @return
     */
    @Transactional
    public boolean updateRoleService(Role role, String authStr) {
        try {
            if (roleDao.updataRole(role)) {
                if (authStr != null && !authStr.equals("")) {
                    if (delRoleAuthService(role.getRoleId())) {
                        String[] auths = authStr.split(",");
                        for (String auth : auths) {
                            int authid = Integer.parseInt(auth);
                            addRoleAuthService(role.getRoleId(), authid);
                        }
                    }
                }
                return true;
            }
            return false;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 删除某角色的所有权限关系
     *
     * @param id int 角色id
     * @return boolean  true删除成功，false失败
     */
    @Transactional
    public boolean delRoleAuthService(int id) {
        try {
            roleDao.delRoleAuthBySql(id);
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * 删除角色信息
     *
     * @param role Role
     * @return
     */
    @Transactional
    public boolean delRoleService(Role role) {
        try {
            int id = role.getRoleId();
            if (roleAuthService(id)) {
                if (delRoleAuthService(id)) {
                    if (roleDao.delRole(role)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                if (roleDao.delRole(role)) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * 删除角色信息
     *
     * @param id int 角色id
     * @return
     */
    @Transactional
    public boolean delRoleByIdService(int id) {
        try {
            if (roleAuthService(id)) {
                if (delRoleAuthService(id)) {
                    if (roleDao.delRole(id)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                if (roleDao.delRole(id)) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * 删除多个用户信息
     *
     * @param ids String 用户id，逗号分隔
     * @return
     */
    @Transactional
    public boolean delRoleListService(String ids) {
        try {
            int i = 0;
            String[] uids = ids.split(",");
            for (String uid : uids) {
                int id = Integer.parseInt(uid);
                if (roleAuthService(id)) {
                    if (delRoleAuthService(id)) {
                        roleDao.delRole(id);
                        i++;
                    }
                } else {
                    roleDao.delRole(id);
                    i++;
                }
            }
            if (i < uids.length) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * 判断某一id的角色是否被分配权限
     *
     * @param id int 角色id
     * @return boolean true 表示分配，false表示未分配
     */
    @Transactional
    public boolean roleAuthService(int id) {
        try {
            return roleDao.isRoleAuthExsist(id);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 判断某一id的角色是否分配给用户
     *
     * @param id int 角色id
     * @return boolean true 表示分配，false表示未分配
     */
    @Transactional
    public boolean roleUserService(int id) {
        try {
            return roleDao.isRoleUserExsist(id);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }
}
