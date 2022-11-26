package com.tenant.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="ITEM_SEQ")
	@Column(name="ID")
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

	@Column(name = "created_date")
	private String createdDate;
	
	@Column(name = "updated_date")
	private String updatedDate;

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
	
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	public Item(long id, String itemName, int quantity, double price, String shopName, String userName,
			String createdDate, String updatedDate) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
		this.shopName = shopName;
		this.userName = userName;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
