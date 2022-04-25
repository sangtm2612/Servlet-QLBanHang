package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Product;
import utils.JpaUtil;
import utils.hibernateUtil;

public class ProductDAO {
	private EntityManager emEntityManager;
	CategoryDAO cDao = new CategoryDAO();
	UserDAO uDao = new UserDAO();

	public ProductDAO() {
		emEntityManager = JpaUtil.getEntityManager();
	}

	public void bashInsert(List<Product> products ) throws HibernateException {
		Transaction tx =  null;
		try(Session session = hibernateUtil.getSessionFactory().openSession() ) {
			tx = session.beginTransaction();
			int i = 0;
			for (Product product : products) {
				i++;
				session.persist(product);
				if (i == 20) {
					session.flush();
					session.clear();
					i = 0;
				}
			}
			tx.commit();
		} catch (Exception e) {
			if(tx != null && tx.isActive())
				tx.rollback();
			throw e;
		}
	}

	@Transactional
	public void bashInsert1() {
		for (int i = 1; i <= 100; i++) {
			Product product = new Product();
			product.setTen("ngu" + i);
			product.setDonGia(i);
			product.setSoLuong(i);
			emEntityManager.persist(product);
			if (i == 10) {
				emEntityManager.flush();
				emEntityManager.clear();
			}
		}
	}

	public Product create(Product entity) {
		try {
			emEntityManager.getTransaction().begin();
			;
			emEntityManager.persist(entity);
			emEntityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			emEntityManager.getTransaction().rollback();
			throw e;
		}
	}

	public Product update(Product entity) {
		try {
			emEntityManager.getTransaction().begin();
			;
			emEntityManager.merge(entity);
			emEntityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			emEntityManager.getTransaction().rollback();
			throw e;
		}
	}

	public Product delete(Product entity) {
		try {
			emEntityManager.getTransaction().begin();
			;
			emEntityManager.remove(entity);
			emEntityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			emEntityManager.getTransaction().rollback();
			throw e;
		}
	}

	public List<Product> all() {
		String jpql = "SELECT obj FROM Product obj";
		TypedQuery<Product> query = emEntityManager.createQuery(jpql, Product.class);
		return query.getResultList();
	}

	public List<Product> allProductsByIdCategory(int id) {
		String jpql = "SELECT obj FROM Product obj where obj.category.id = :id";
		TypedQuery<Product> query = emEntityManager.createQuery(jpql, Product.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

	public Product getProduct(int id) {
		return emEntityManager.find(Product.class, id);
	}
}
