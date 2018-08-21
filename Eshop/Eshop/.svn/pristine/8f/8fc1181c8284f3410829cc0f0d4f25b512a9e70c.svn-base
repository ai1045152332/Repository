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
@Table(name="product_type")
public class ProductTypeBean {
	private int id;
	private String name;
	private String sort;
	private String createDate;
	
	private AdminBean adminBean;
	private List<ProductPropertyBean> productPropertyBeans;
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
	@OneToMany(mappedBy="productTypeBean")
	public List<ProductPropertyBean> getProductPropertyBeans() {
		return productPropertyBeans;
	}
	public void setProductPropertyBeans(
			List<ProductPropertyBean> productPropertyBeans) {
		this.productPropertyBeans = productPropertyBeans;
	}
}
