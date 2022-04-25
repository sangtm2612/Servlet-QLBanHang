package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Category;
import entities.Order;
import utils.JpaUtil;

public class OrderDAO {
private EntityManager emEntityManager;
	
	public OrderDAO() {
		emEntityManager = JpaUtil.getEntityManager();
	}
	
	public Order create(Order entity) {
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
	
	public Order update(Order entity) {
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
	
	public Order delete(Order entity) {
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
	
	public List<Order> all() {
		String jpql = "SELECT obj FROM Order obj";
		TypedQuery<Order> query = emEntityManager.createQuery(jpql, Order.class);
		return query.getResultList();
	}
	
	public List<Order> selectOrderBySdt(String sdt) {
		String jpql = "SELECT obj FROM Order obj WHERE obj.customer.sdt = :sdt";
		TypedQuery<Order> query = emEntityManager.createQuery(jpql, Order.class);
		query.setParameter("sdt", sdt);
		return query.getResultList();
	}
	
	public Order getOrder(int id) {
		return emEntityManager.find(Order.class, id);
	}
}
