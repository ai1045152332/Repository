package com.honghe.recordweb.service.project.authority;

import com.honghe.recordhibernate.dao.AuthorityDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.Authority;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wj on 2014-10-11.
 */
@Service
public class AuthorityService {
    private final static Logger logger = Logger.getLogger(AuthorityService.class);
    @Resource
    private AuthorityDao authorityDao;

    /**
     * 返回所有权限列表
     *
     * @return List<Authority>
     */
    @Transactional
    public List<Authority> authorityListService() {
        try {
            List list = null;
            list = authorityDao.getAuthorityList();
            return list;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 根据userId取前台显示权限列表 userId=-1：导播权限集合、巡课权限集合、角色权限；userId！=-1：属于该用户的导播权限集合、巡课权限集合、角色权限。
     *
     * @param userId int 用户Id
     * @return List<Authority>
     */
    @Transactional
    public List<Authority> authorityFrontListService(int userId) {
        try {
            List list = null;
            list = authorityDao.getAuthorityFrontList(userId);
            return list;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 根据userId取后台显示权限列表 userId=-1：导播权限集合、巡课权限集合、角色权限；
     * userId=1、userId=2:产品定义权限集合、导播权限集合、巡课权限集合、角色权限；
     * 其他：属于该用户的导播权限集合、巡课权限集合、角色权限。
     *
     * @param userId int 用户Id
     * @return List<Authority>
     */
    @Transactional
    public List<Authority> authorityBackListService(int userId) {
        try {
            List list = null;
            list = authorityDao.getAuthorityBackList(userId);
            return list;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 根据权限名获取所有权限列表
     *
     * @param authname String
     * @return
     */
    @Transactional
    public List<Authority> getAuthorityByNameService(String authname) {
        try {
            List list = null;
            list = authorityDao.getAuthorityNameList(authname);
            return list;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 根据权限WORD获取所有权限列表
     *
     * @param word String
     * @return
     */
    @Transactional
    public List<Authority> getAuthorityByWordService(String word) {
        try {
            List list = null;
            list = authorityDao.getAuthorityWordList(word);
            return list;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 根据Id获取权限信息
     *
     * @param authId int 权限id
     * @return Authority
     */
    @Transactional
    public Authority getAuthorityinfoByIdService(int authId) {
        try {
            Authority authority = null;
            authority = authorityDao.getAluthorityById(authId);
            return authority;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 根据page类方法，返回分页的分组数据
     *
     * @param page Page<Authority>
     * @return
     */
    @Transactional
    public void authorityListService(Page<Authority> page) {
        try {
            authorityDao.getAuthorityListBackendByPage(page);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * 保存权限信息
     *
     * @param authority Authority 自定义权限信息
     * @return boolean
     */
    @Transactional
    public boolean addAuthorityService(Authority authority) {
        try {
            authorityDao.saveAuthority(authority);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 更新权限信息
     *
     * @param authority Authority 自定义权限信息
     * @return
     */
    @Transactional
    public boolean updateAuthorityService(Authority authority) {
        try {
            if (authorityDao.updatAuthority(authority)) {
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
     * 删除权限信息
     *
     * @param id int 权限id
     * @return
     */
    @Transactional
    public boolean delAuthorityService(int id) {
        try {
            authorityDao.delAuthority(id);
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * 删除权限信息
     *
     * @param authority Authority
     * @return
     */
    @Transactional
    public boolean delAuthorityService(Authority authority) {
        try {
            authorityDao.delAuthority(authority);
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * 删除多个权限信息
     *
     * @param ids String 权限id，逗号分隔
     * @return
     */
    @Transactional
    public boolean delAuthorityListService(String ids) {
        try {
            int i = 0;
            String[] aids = ids.split(",");
            for (String aid : aids) {
                int id = Integer.parseInt(aid);
                authorityDao.delAuthority(id);
                i++;
            }
            if (i < aids.length) {
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
     * 判断某一id的权限是否分配给角色
     *
     * @param id int 权限id
     * @return boolean true 表示分配，false表示未分配
     */
    @Transactional
    public boolean authRoleService(int id) {
        try {
            return authorityDao.isAuthRoleExsist(id);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }
}
