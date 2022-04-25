package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.User;
import utils.JpaUtil;

public class UserDAO {
	private EntityManager emEntityManager;
	
	public UserDAO() {
		emEntityManager = JpaUtil.getEntityManager();
	}
	
	public User create(User entity) {
		try {
			emEntityManager.getTransaction().begin();;
			emEntityManager.persist(entity);
			emEntityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			emEntityManager.getTransaction().rollback();
			throw e;
		}
	}
	
	public User update(User entity) {
		try {
			emEntityManager.getTransaction().begin();;
			emEntityManager.merge(entity);
			emEntityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			emEntityManager.getTransaction().rollback();
			throw e;
		}
	}
	
	public User delete(User entity) {
		try {
			emEntityManager.getTransaction().begin();;
			emEntityManager.remove(entity);
			emEntityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			emEntityManager.getTransaction().rollback();
			throw e;
		}
	}
	
	public List<User> all() {
		String jpql = "SELECT obj FROM User obj";
		TypedQuery<User> query = emEntityManager.createQuery(jpql, User.class);
		return query.getResultList();
	}
	
	public User getUser(int id) {
		return emEntityManager.find(User.class, id);
	}
	
	public User findBySDT(String sdt) {
		String jpql = "SELECT obj FROM User obj WHERE obj.sdt = :sdt";
		TypedQuery<User> query = emEntityManager.createQuery(jpql, User.class);
		query.setParameter("sdt", sdt);
		return query.getSingleResult();
	}
}
