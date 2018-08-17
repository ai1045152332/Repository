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
@Table(name="order_product")
public class OrderProductBean {
	private int id;
	private float price;
	private int number;
	private String createDate;
	private OrdersBean ordersBean;
	private ProductBean productBean;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Column
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Column(name="create_date")
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@ManyToOne
	@JoinColumn(name="order_id")
	public OrdersBean getOrdersBean() {
		return ordersBean;
	}
	public void setOrdersBean(OrdersBean ordersBean) {
		this.ordersBean = ordersBean;
	}
	@ManyToOne
	@JoinColumn(name="product_id")
	public ProductBean getProductBean() {
		return productBean;
	}
	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}
}
