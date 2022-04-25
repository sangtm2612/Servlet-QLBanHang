package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.apache.log4j.Logger;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Product.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "product")
	private List<Orderdetail> orderdetails;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name="don_gia")
	private int donGia;

	private String img;

	@Column(name="so_luong")
	private int soLuong;

	private String ten;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDonGia() {
		return this.donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getSoLuong() {
		return this.soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getTen() {
		return this.ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Orderdetail> getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(List<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", category=" + category + ", orderdetails=" + orderdetails + ", donGia=" + donGia
				+ ", img=" + img + ", soLuong=" + soLuong + ", ten=" + ten + "]";
	}
	
	@PostPersist
    public void postPersist() {
		logger.info("Insert Product [id=" + id + ", category=" + category.getTen() + ", donGia=" + donGia
				+ ", img=" + img + ", soLuong=" + soLuong + ", ten=" + ten + "]" + "by " + user.getHoTen() + "-id:" + user.getId());
    }
	
	@PostUpdate
    public void postUpdate() {
		logger.info("Update Product [id=" + id + ", category=" + category.getTen() + ", donGia=" + donGia
				+ ", img=" + img + ", soLuong=" + soLuong + ", ten=" + ten + "]" + "by " + user.getHoTen() + "-id:" + user.getId());
    }
	
	@PostRemove
    public void postDelete() {
		logger.info("Remove Product [id=" + id + ", category=" + category.getTen() + ", donGia=" + donGia
				+ ", img=" + img + ", soLuong=" + soLuong + ", ten=" + ten + "]" + "by " + user.getHoTen() + "-id:" + user.getId());
    }
	
	

}