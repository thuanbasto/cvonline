package com.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entity.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
	
	@Autowired
	SessionFactory sessionFactory;

	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	public void updateUser(User user) {
		sessionFactory.getCurrentSession().merge(user);
	}

	public void deleteUser(int id) {
		sessionFactory.getCurrentSession().delete(getUserById(id));
	}

	public User getUserById(int id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		@SuppressWarnings("deprecation")
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		return criteria.list();
	}

	public boolean checkUsername(String username) {
		User user;
		try {
			user = (User) sessionFactory.getCurrentSession().createQuery("from com.entity.User u where u.username=:username")
					.setParameter("username", username)
					.getSingleResult();
		} catch (NoResultException e) {
			user = null;
		}
		if (user == null)
			return true;
		else 
			return false;
	}

	public User getUserByUserName(String username) {
		User user = (User) sessionFactory.getCurrentSession().createQuery("from com.entity.User u where u.username=:username")
				.setParameter("username", username)
				.getSingleResult();;
		return user;
	}

}
