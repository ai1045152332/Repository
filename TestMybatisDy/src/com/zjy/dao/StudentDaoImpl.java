package com.zjy.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.zjy.beans.Student;
import com.zjy.utils.MybatisUtil;

public class StudentDaoImpl implements IStudentDao {

	private SqlSession sqlSession;

	@Override
	public void insertStudent(Student stu) {
		try {
			sqlSession = MybatisUtil.getSqlSession();
			// 相关操作
			sqlSession.insert("insertStudent", stu);
			sqlSession.commit();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	@Override
	public void insertStudentCacheId(Student student) {
		try {
			sqlSession = MybatisUtil.getSqlSession();
			// 相关操作
			sqlSession.insert("insertStudentCacheId", student);
			sqlSession.commit();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	@Override
	public void deleteStudentById(int id) {
		try {
			sqlSession = MybatisUtil.getSqlSession();
			sqlSession.delete("deleteStudentById", id);
			sqlSession.commit();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	@Override
	public void updateStudent(Student student) {
		try {
			sqlSession = MybatisUtil.getSqlSession();
			sqlSession.update("updateStudent", student);
			sqlSession.commit();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	@Override
	public List<Student> selectAllStudents() {
		List<Student> allList = new ArrayList<Student>();
		try {
			sqlSession = MybatisUtil.getSqlSession();
			allList = sqlSession.selectList("selectAllStudents");
			sqlSession.commit();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return allList;
	}

	@Override
	public Map<String, Object> selectAllStudentsMap() {
		Map<String, Object> map = new HashMap<>();
		try {
			sqlSession = MybatisUtil.getSqlSession();
			map = sqlSession.selectMap("selectAllStudents", "name");
			sqlSession.commit();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return map;
	}

	@Override
	public Student selectStudentById(int id) {
		Student student = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			student = sqlSession.selectOne("selectStudentById", id);
			sqlSession.commit();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return student;
	}

	@Override
	public List<Student> selectStudentByName(String name) {
		return null;
	}

}
