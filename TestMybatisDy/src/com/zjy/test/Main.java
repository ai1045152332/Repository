package com.zjy.test;

import org.junit.Before;
import org.junit.Test;

import com.zjy.beans.Student;
import com.zjy.dao.IStudentDao;
import com.zjy.dao.StudentDaoImpl;

public class Main {

	private IStudentDao dao;

	@Before
	public void before() {
		dao = new StudentDaoImpl();
	}

	// @Test
	// public void insertTest() {
	// Student stu = new Student("萨达37", 21, 98.9);
	// dao.insertStudentCacheId(stu);
	// System.out.println(stu.getId());
	// }
	//
	// @Test
	// public void deleteTest() {
	// Student stu = new Student("萨达37", 21, 98.9);
	// dao.deleteStudentById(38);
	// System.out.println(stu.getId());
	// }
	//
	// @Test
	// public void updateTest() {
	// Student stu = new Student("我是新的37", 37, 37);
	// stu.setId(37);
	// dao.updateStudent(stu);
	// System.out.println(stu.getId());
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
	@Test
	/* 按照id查询学生 */
	public void selectOneTest() {
		int id = 35;
		Student student = dao.selectStudentById(id);
		System.out.println(student);
	}
}
