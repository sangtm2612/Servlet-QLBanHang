package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Category;
import entities.User;
import utils.JpaUtil;

public class CategoryDAO {
private EntityManager emEntityManager;
	
	public CategoryDAO() {
		emEntityManager = JpaUtil.getEntityManager();
	}
	
	public Category create(Category entity) {
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
	
	public Category update(Category entity) {
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
	
	public Category delete(Category entity) {
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
	
	public List<Category> all() {
		String jpql = "SELECT obj FROM Category obj";
		TypedQuery<Category> query = emEntityManager.createQuery(jpql, Category.class);
		return query.getResultList();
	}
	
	public Category getCategory(int id) {
		return emEntityManager.find(Category.class, id);
	}
	
	public Category getCategoryByName(String ten) {
		String jpql = "SELECT obj FROM Category obj WHERE obj.ten = :ten";
		TypedQuery<Category> query = emEntityManager.createQuery(jpql, Category.class);
		query.setParameter("ten", ten);
		return query.getSingleResult();
	}
}
