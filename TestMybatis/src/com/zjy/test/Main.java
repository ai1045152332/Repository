package com.zjy.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zjy.beans.Student;
import com.zjy.dao.IStudentDao;
import com.zjy.utils.MybatisUtil;

public class Main {

	private IStudentDao dao;
	private SqlSession sqlSession;

	@Before
	public void before() {
		sqlSession = MybatisUtil.getSqlSession();
		dao = sqlSession.getMapper(IStudentDao.class);
	}

	@After
	public void after() {
		if (sqlSession != null) {
			sqlSession.close();
		}
	}

	@Test
	public void insertTest() {
		Student stu = new Student("萨达37", 21, 98.9);
		dao.insertStudentCacheId(stu);
		System.out.println(stu.getId());
		sqlSession.commit();
	}
	//
	// @Test
	// public void deleteTest() {
	// Student stu = new Student("萨达37", 21, 98.9);
	// dao.deleteStudentById(38);
	// System.out.println(stu.getId());
	// sqlSession.commit();
	// }
	//
	// @Test
	// public void updateTest() {
	// Student stu = new Student("我是新的37", 37, 37);
	// stu.setId(37);
	// dao.updateStudent(stu);
	// System.out.println(stu.getId());
	// sqlSession.commit();
	// }

	// @Test
	// public void selectAllTest() {
	// List<Student> allList = dao.selectAllStudents();
	// if (allList.size() > 0) {
	// for (Student student : allList) {
	// System.out.println(student);
	// }
	// }
	// }
	/**
	 * @Title: selectAllTest
	 * @Description: 了解,有多个相同数据时查询出最后一个
	 * @return Map
	 */
	// @Test
	// public void selectAllTest() {
	// Map<String, Object> map = dao.selectAllStudentsMap();
	// if (map.size() > 0) {
	// System.out.println(map.get("萨达37"));
	// }
	//
	// }
	// @Test
	// /* 按照id查询学生 */
	// public void selectOneTest() {
	// int id = 35;
	// Student student = dao.selectStudentById(id);
	// System.out.println(student);
	// }
}
