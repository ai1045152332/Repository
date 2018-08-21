package com.shop.dao.base;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * @param <T>
 */
@Repository
public class BaseDao<T> {

	@Autowired
	public HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	public List<T> getList(String hql) {
		// System.out.println("hibernateTemplate:"+hibernateTemplate);
		return hibernateTemplate.find(hql);
	}

	@SuppressWarnings("unchecked")
	public List<T> getList(String hql, int start, int size, Object... args) {
		Session session = hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		try {
			Query query = session.createQuery(hql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					query.setParameter(i, args[i]);
				}
			}
			query.setFirstResult(start);
			query.setMaxResults(size);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> getList(String hql, Object... args) {
		Session session = hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		try {
			Query query = session.createQuery(hql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					query.setParameter(i, args[i]);
				}
			}
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 使用 in(:name) 查询
	 * 
	 * @param hql
	 * @param name
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getList(final String hql, final String name,
			final List<Object> list) {
		/*
		 * Session session =
		 * hibernateTemplate.getSessionFactory().getCurrentSession(); try {
		 * Query query = session.createQuery(hql); query.setParameterList(name,
		 * list); return query.list(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */

		List<T> result = (List<T>) hibernateTemplate
				.execute(new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setParameterList(name, list);
						List<T> list = query.list();
						return list;
					}
				});

		return result;
	}

	public T getByCondition(String hql, Object... args) {
		List<T> list = getListForPage(hql, 0, 1, args);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public int getCount(final String hql, final Object... args) {
		Integer result = ((Long) hibernateTemplate
				.execute(new HibernateCallback<Object>() {
					public Object doInHibernate(Session arg0)
							throws HibernateException, SQLException {
						Query query = arg0.createQuery(hql);
						if (args != null) {
							for (int i = 0; i < args.length; i++) {
								query.setParameter(i, args[i]);
							}
						}
						Object obj = query.uniqueResult();
						return obj == null ? (long)0 : Long.valueOf(obj.toString());
					}
				})).intValue();
		return result.intValue();
	}

	public float getSum(final String hql, final Object... args) {
		Float result = ((Long) hibernateTemplate
				.execute(new HibernateCallback<Object>() {
					public Object doInHibernate(Session arg0)
							throws HibernateException, SQLException {
						Query query = arg0.createQuery(hql);
						if (args != null) {
							for (int i = 0; i < args.length; i++) {
								query.setParameter(i, args[i]);
							}
						}
						Object obj = query.uniqueResult();
						return obj == null ? 0L : obj;
					}
				})).floatValue();
		return result.floatValue();
	}

	public void delete(T t) {
		hibernateTemplate.delete(t);
	}

	public boolean delete(String hql, Object... args) {
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
		return query.executeUpdate()>0;
	}

	public Serializable add(T t) {
		Serializable ser = hibernateTemplate.save(t);
		return ser;
	}

	public T getById(Class<? extends T> class1, int id) {
		return hibernateTemplate.get(class1, id);
	}

	public List<T> getListForPage(final String hql, final int offset,
			final int length, final Object... args) {
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) hibernateTemplate
				.execute(new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						for (int i = 0; i < args.length; i++) {
							query.setParameter(i, args[i]);
						}
						query.setFirstResult(offset);
						query.setMaxResults(length);
						// System.out.println(query.getQueryString()+"\toffset:"+offset+"\tlength:"+length);
						List<T> list = query.list();
						if (list != null && list.size() == 0 && offset > 0) {
							list = getListForPage(hql, offset - length, length);
						}
						return list;
					}
				});
		return list;
	}

	public void update(T t) {
		hibernateTemplate.update(t);
	}
	
	/**
	 * 郭威新增
	 * 未检查
	 */
	public boolean update(String hql,Object... args) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(hql);
			if(args!=null) {
				for(int i=0; i<args.length; i++) {
					query.setParameter(i, args[i]);
				}
			} 
			return query.executeUpdate()>0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateById(String hql, int id, Object... args) {
		Session session = hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		try {
			Query query = session.createQuery(hql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					query.setParameter(i, args[i]);
				}
				query.setParameter(args.length, id);
			} else {
				query.setParameter(0, id);
			}
			return query.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void addBulk(final List<T> list) {
		hibernateTemplate.execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				int count = 0;
				//Transaction ts = session.beginTransaction();
				for (Iterator<T> it = list.iterator(); it.hasNext();) {
					session.save(it.next());
					count++;
					if (count % 50 == 0) {
						session.flush();
						session.clear();
					}
				}
				//ts.commit();
				session.close();
				return null;
			}
		});
	}
	public boolean updateByIn(final String hql, final String name, final List<Object> list, Object... args) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(hql);
			query.setParameterList(name, list);
			if(args!=null) {
				for(int i=0; i<args.length; i++) {
					query.setParameter(i, args[i]);
				}
			}
			
			return query.executeUpdate()>0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
