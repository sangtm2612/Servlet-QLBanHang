package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Category;
import entities.Order;
import entities.Orderdetail;
import utils.JpaUtil;

public class OrderDetailDAO {
private EntityManager emEntityManager;
	
	public OrderDetailDAO() {
		emEntityManager = JpaUtil.getEntityManager();
	}
	
	public Orderdetail create(Orderdetail entity) {
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
	
	public Orderdetail update(Orderdetail entity) {
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
	
	public Orderdetail delete(Orderdetail entity) {
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
	
	public List<Orderdetail> all() {
		String jpql = "SELECT obj FROM Orderdetail obj";
		TypedQuery<Orderdetail> query = emEntityManager.createQuery(jpql, Orderdetail.class);
		return query.getResultList();
	}
	
	public Orderdetail getOrderdetail(int id) {
		return emEntityManager.find(Orderdetail.class, id);
	}
}
