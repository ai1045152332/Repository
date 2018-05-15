package com.honghe.recordweb.service.project.dtype;

import com.honghe.recordhibernate.dao.DTypeDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.SpecDao;
import com.honghe.recordhibernate.entity.Dtype;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * 设备类型
 * User: zhanghongbin
 * Date: 14-10-28
 * Time: 上午11:01
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DTypeService {
    private final static Logger logger = Logger.getLogger(DTypeService.class);
    @Resource
    private DTypeDao dTypeDao;
    @Resource
    private SpecDao specDao;

    /**
     * todo 加注释
     * @param dtype
     * @return
     */
    @Transactional
    public boolean save(Dtype dtype) {
        try {
            dTypeDao.save(dtype);
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * todo 加注释
     * @param dtypeId
     * @return
     */
    public boolean hasDtype(int dtypeId) {
        try {
            return specDao.hasDtype(dtypeId);
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * todo 加注释
     * @param name
     * @return
     */
    public boolean isDType(String name) {
        try {
            return dTypeDao.isDType(name);
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * todo 加注释
     * @param dtype
     * @return
     */
    @Transactional
    public boolean update(Dtype dtype) {
        try {
            dTypeDao.updata(dtype);
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * todo 加注释
     * @param id
     * @return
     */
    @Transactional
    public boolean delete(int id) {
        Dtype dtype = new Dtype();
        dtype.setDtypeId(id);
        try {
            dTypeDao.delete(dtype);
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * todo 加注释
     * @param currentPageSize
     * @return
     */
    public Page<Dtype> getDTypeList(int currentPageSize) {
        try {
            Page<Dtype> page = new Page<Dtype>(currentPageSize, 10);
            dTypeDao.getDTypeList(page);
            return page;
        } catch (Exception e) {
            logger.error("", e);
            return null;
        }
    }

    /**
     * todo 加注释
     * @return
     */
    public List<Dtype> getDTypeListService() {
        List<Dtype> dtypeList = null;
        try {
            dtypeList = dTypeDao.getTypeList();
            return dtypeList;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }
}
