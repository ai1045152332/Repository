package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shop.bean.ProductTypeBean;
import com.shop.dao.ProductTypeDao;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class ProductTypeService {

	@Autowired
	private ProductTypeDao productTypeDao;
	
	public boolean add(ProductTypeBean productTypeBean) {
		//数据库操作
		return productTypeDao.add(productTypeBean)!=null;
	}

	/*public List<ProductTypeBean> getAll() {
		return productTypeDao.getAll("from ProductTypeBean");
	}*/

	public ProductTypeBean getById(int id) {
		return productTypeDao.getById(ProductTypeBean.class, id);
	}

	public void update(ProductTypeBean productTypeBean) {
		productTypeDao.updateById("update ProductTypeBean set name=?, sort=? where id=?", productTypeBean.getId(), 
				productTypeBean.getName(),productTypeBean.getSort());
	}

	public void del(int id) {
		ProductTypeBean bean = new ProductTypeBean();
		bean.setId(id);
		productTypeDao.delete(bean);
	}

	public List<ProductTypeBean> getAll() {
		return productTypeDao.getList("from ProductTypeBean");
	}

	public int getCount() {
		return productTypeDao.getCount("select count(id) from ProductTypeBean");
	}

	public List<ProductTypeBean> getByPage(int start, int length) {
		return productTypeDao.getListForPage("from ProductTypeBean", start, length);
	}
}
