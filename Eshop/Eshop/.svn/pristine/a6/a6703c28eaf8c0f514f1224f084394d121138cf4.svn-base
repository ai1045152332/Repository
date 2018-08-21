package com.shop.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.shop.bean.UserBean;
import com.shop.dao.UserDao;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class UserService {
	@Autowired
	private UserDao userDao;
	public UserBean login(UserBean userBean) {
		return userDao.getByCondition("from UserBean where username=? and password=?", userBean.getUsername(),userBean.getPassword());
	}
	public boolean register(UserBean userBean) {
		return userDao.add(userBean)!=null;
	}
	public UserBean getByUsername(String username) {
		return userDao.getByCondition("from UserBean where username=?", username);
	}
}
