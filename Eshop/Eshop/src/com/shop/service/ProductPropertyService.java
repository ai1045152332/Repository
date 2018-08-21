package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shop.bean.ProductPropertyBean;
import com.shop.dao.ProductPropertyDao;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class ProductPropertyService {

	@Autowired
	private ProductPropertyDao productPropertyDao;
	
	public boolean add(ProductPropertyBean productPropertyBean) {
		//数据库操作
		return productPropertyDao.add(productPropertyBean)!=null;
	}

	/*public List<ProductPropertyBean> getAll() {
		return productPropertyDao.getAll("from ProductPropertyBean");
	}*/

	public ProductPropertyBean getById(int id) {
		return productPropertyDao.getById(ProductPropertyBean.class, id);
	}

	public void update(ProductPropertyBean productPropertyBean) {
		productPropertyDao.updateById("update ProductPropertyBean set name=?, sort=? where id=?", productPropertyBean.getId(), 
				productPropertyBean.getName(),productPropertyBean.getSort());
	}

	public void del(int id) {
		ProductPropertyBean bean = new ProductPropertyBean();
		bean.setId(id);
		productPropertyDao.delete(bean);
	}

	public List<ProductPropertyBean> getAll() {
		return productPropertyDao.getList("from ProductPropertyBean");
	}

	public int getCount() {
		return productPropertyDao.getCount("select count(id) from ProductPropertyBean");
	}

	public List<ProductPropertyBean> getByPage(int start, int length) {
		return productPropertyDao.getListForPage("from ProductPropertyBean", start, length);
	}

	/**
	 * 根据商品分类获取商品属性
	 * @param typeId
	 * @return
	 */
	public List<ProductPropertyBean> getAllByTypeId(int typeId) {
		return productPropertyDao.getList("from ProductPropertyBean where productTypeBean.id=?", typeId);
	}

	/**
	 * 判断商品分类下是否有属性，如果有返回true，没有返回false
	 * @param typeId
	 * @return
	 */
	public boolean hasProperty(int typeId) {
		ProductPropertyBean bean = productPropertyDao.getByCondition("from ProductPropertyBean where productTypeBean.id=?", typeId);
		return bean!=null;
	}

	public List<ProductPropertyBean> getByType(int typeId) {
		return productPropertyDao.getList("from ProductPropertyBean where productTypeBean.id=?", typeId);
	}
}
