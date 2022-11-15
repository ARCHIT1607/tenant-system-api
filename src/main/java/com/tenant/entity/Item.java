package com.tenant.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "item_name")
	private String itemName;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "price")
	private double price;
	@Column(name = "shop_name")
	private String shopName;
	@Column(name = "user_name")
	private String userName;

	@Column(name = "created_date",columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private Date createdDate;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Item(long id, String itemName, int quantity, double price, String shopName, String userName) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
		this.shopName = shopName;
		this.userName = userName;
	}
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
