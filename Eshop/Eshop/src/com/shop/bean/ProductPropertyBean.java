package com.shop.bean;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="product_property")
public class ProductPropertyBean {
	private int id;
	private String name;
	private String sort;
	private String createDate;
	
	private AdminBean adminBean;
	private ProductTypeBean productTypeBean;
	private List<ProductPropertyOptionBean> productPropertyOptionBeans;
	
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
	@JoinColumn(name="product_type_id")
	public ProductTypeBean getProductTypeBean() {
		return productTypeBean;
	}
	public void setProductTypeBean(ProductTypeBean productTypeBean) {
		this.productTypeBean = productTypeBean;
	}
	@OneToMany(mappedBy="productPropertyBean")
	public List<ProductPropertyOptionBean> getProductPropertyOptionBeans() {
		return productPropertyOptionBeans;
	}
	public void setProductPropertyOptionBeans(
			List<ProductPropertyOptionBean> productPropertyOptionBeans) {
		this.productPropertyOptionBeans = productPropertyOptionBeans;
	}
}
