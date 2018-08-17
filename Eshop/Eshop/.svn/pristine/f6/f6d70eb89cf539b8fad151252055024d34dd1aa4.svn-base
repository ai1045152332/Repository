package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shop.bean.ProductBean;
import com.shop.dao.ProductDao;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	public boolean add(ProductBean productBean) {
		//数据库操作
		return productDao.add(productBean)!=null;
	}

	public ProductBean getById(int id) {
		return productDao.getById(ProductBean.class, id);
	}

	public void update(ProductBean productBean) {
		if(productBean.getPic()!=null) {
			//update pic
			productDao.updateById("update ProductBean set name=?, price=?, originalPrice=?, num=?, productTypeBean.id=?, propertys=?, intro=?, pic=? where id=?", productBean.getId(), 
					productBean.getName(),productBean.getPrice(),productBean.getOriginalPrice(), productBean.getNum(), productBean.getProductTypeBean().getId(), productBean.getPropertys(), productBean.getIntro(), productBean.getPic());
		} else {
			productDao.updateById("update ProductBean set name=?, price=?, originalPrice=?, num=?, productTypeBean.id=?, propertys=?, intro=? where id=?", productBean.getId(), 
					productBean.getName(),productBean.getPrice(),productBean.getOriginalPrice(), productBean.getNum(), productBean.getProductTypeBean().getId(), productBean.getPropertys(), productBean.getIntro());
		}
	}

	public void del(int id) {
		ProductBean bean = new ProductBean();
		bean.setId(id);
		productDao.delete(bean);
	}

	public List<ProductBean> getAll() {
		return productDao.getList("from ProductBean");
	}

	public int getCount() {
		return productDao.getCount("select count(id) from ProductBean");
	}

	public List<ProductBean> getByPage(int start, int length) {
		return productDao.getListForPage("from ProductBean", start, length);
	}

	/**
	 * 如果该选项被商品使用则返回true
	 * @param OptionId
	 * @param typeId
	 * @return
	 */
	public boolean hasOption(int optionId, int typeId) {
		List<ProductBean> beans = productDao.getList("from ProductBean where productTypeBean.id=?", typeId);
		boolean flag = false;
		if(beans!=null&&beans.size()>0) {
			for(int i=0; i<beans.size(); i++) {
				String propertys = beans.get(i).getPropertys();
				if(propertys.indexOf("-"+optionId+"-")!=-1) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	
}
 