package com.zversal.beans;

public class Product {
	private String Id;
	private String name;
	private int quantity;
	private String inAvailable;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	@Override
	public String toString() {
		return "Id:" + getId() + " Name:" + getName() + " Quantity:" + getQuantity() + " isLive:" + getInAvailable();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getInAvailable() {
		return inAvailable;
	}

	public void setInAvailable(String inAvailable) {
		this.inAvailable = inAvailable;
	}
}
