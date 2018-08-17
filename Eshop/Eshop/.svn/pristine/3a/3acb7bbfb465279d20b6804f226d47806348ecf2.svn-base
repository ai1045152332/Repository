package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shop.bean.ProductPropertyOptionBean;
import com.shop.dao.ProductPropertyOptionDao;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class ProductPropertyOptionService {

	@Autowired
	private ProductPropertyOptionDao productPropertyOptionDao;
	
	public boolean add(ProductPropertyOptionBean productPropertyOptionBean) {
		//数据库操作
		return productPropertyOptionDao.add(productPropertyOptionBean)!=null;
	}

	/*public List<ProductPropertyOptionBean> getAll() {
		return productPropertyOptionDao.getAll("from ProductPropertyOptionBean");
	}*/

	public ProductPropertyOptionBean getById(int id) {
		return productPropertyOptionDao.getById(ProductPropertyOptionBean.class, id);
	}

	public void update(ProductPropertyOptionBean productPropertyOptionBean) {
		productPropertyOptionDao.updateById("update ProductPropertyOptionBean set name=?, sort=? where id=?", productPropertyOptionBean.getId(), 
				productPropertyOptionBean.getName(),productPropertyOptionBean.getSort());
	}

	public void del(int id) {
		ProductPropertyOptionBean bean = new ProductPropertyOptionBean();
		bean.setId(id);
		productPropertyOptionDao.delete(bean);
	}

	public List<ProductPropertyOptionBean> getAll() {
		return productPropertyOptionDao.getList("from ProductPropertyOptionBean");
	}

	public int getCount() {
		return productPropertyOptionDao.getCount("select count(id) from ProductPropertyOptionBean");
	}

	public List<ProductPropertyOptionBean> getByPage(int start, int length) {
		return productPropertyOptionDao.getListForPage("from ProductPropertyOptionBean", start, length);
	}

	/**
	 * 获取指定属性的所有选项
	 * @param propertyId
	 * @return
	 */
	public List<ProductPropertyOptionBean> getByPropertyId(int propertyId) {
		return productPropertyOptionDao.getList("from ProductPropertyOptionBean where productPropertyBean.id=?", propertyId);
	}

	/**
	 * 判断该属性下是否有选项
	 * @param id
	 * @return
	 */
	public boolean hasOption(int propertyId) {
		ProductPropertyOptionBean bean = productPropertyOptionDao.getByCondition("from ProductPropertyOptionBean where productPropertyBean.id=?", propertyId);
		return bean!=null;
	}
}
