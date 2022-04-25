package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Category;
import entities.Customer;
import entities.Order;
import utils.JpaUtil;

public class CustomerDAO {
private EntityManager emEntityManager;
	
	public CustomerDAO() {
		emEntityManager = JpaUtil.getEntityManager();
	}
	
	public Customer create(Customer entity) {
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
	
	public Customer update(Customer entity) {
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
	
	public Customer delete(Customer entity) {
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
	
	public List<Customer> all() {
		String jpql = "SELECT obj FROM Customer obj";
		TypedQuery<Customer> query = emEntityManager.createQuery(jpql, Customer.class);
		return query.getResultList();
	}
	
	public Customer getCustomer(int id) {
		return emEntityManager.find(Customer.class, id);
	}
	
	public Customer finBySDT(String sdt) {
		String jpql = "SELECT obj FROM Customer obj WHERE obj.sdt = :sdt";
		TypedQuery<Customer> query = emEntityManager.createQuery(jpql, Customer.class);
		query.setParameter("sdt", sdt);
		return query.getSingleResult();
	}
	
	
}
