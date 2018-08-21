package com.shop.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.shop.bean.AdminBean;
import com.shop.dao.AdminDao;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	
	public boolean add(AdminBean adminBean) {
		//数据库操作
		return adminDao.add(adminBean)!=null;
	}

	/*public List<AdminBean> getAll() {
		return adminDao.getAll("from AdminBean");
	}*/

	public AdminBean getById(int id) {
		return adminDao.getById(AdminBean.class, id);
	}

	/*public void update(AdminBean adminBean) {
		adminDao.updateById("update AdminBean set name=?, address=?, major=? where id=?", adminBean.getId(), 
				adminBean.getName(),adminBean.getAddress(),adminBean.getMajor());
	}*/

	public void del(int id) {
		AdminBean bean = new AdminBean();
		bean.setId(id);
		adminDao.delete(bean);
	}

	public AdminBean login(AdminBean adminBean) {
		return adminDao.getByCondition("from AdminBean where username=? and password=?", adminBean.getUsername(), adminBean.getPassword());
	}

	public AdminBean getByUsername(String username) {
		return adminDao.getByCondition("from AdminBean where username=?", username);
	}

	public List<AdminBean> getAll() {
		return adminDao.getList("from AdminBean");
	}
	public int getCount() {
		return adminDao.getCount("select count(id) from AdminBean");
	}
	public List<AdminBean> getByPage(int start, int length) {
		return adminDao.getListForPage("from AdminBean", start, length);
	}
}
