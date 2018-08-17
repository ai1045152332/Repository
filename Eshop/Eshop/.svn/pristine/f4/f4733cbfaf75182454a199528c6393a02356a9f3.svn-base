package com.shop.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="product_property_option")
public class ProductPropertyOptionBean {
	private int id;
	private String name;
	private String sort;
	private String createDate;
	
	private AdminBean adminBean;
	private ProductPropertyBean productPropertyBean;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	@Column(name="create_date")
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@ManyToOne
	@JoinColumn(name="admin_id")
	public AdminBean getAdminBean() {
		return adminBean;
	}
	public void setAdminBean(AdminBean adminBean) {
		this.adminBean = adminBean;
	}
	@ManyToOne
	@JoinColumn(name="product_property_id")
	public ProductPropertyBean getProductPropertyBean() {
		return productPropertyBean;
	}
	public void setProductPropertyBean(ProductPropertyBean productPropertyBean) {
		this.productPropertyBean = productPropertyBean;
	}
}
