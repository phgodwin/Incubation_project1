package com.lbg.demo.dtos;

import com.lbg.demo.domain.Item;

public class ItemDTO {

	private Integer id;

	private String name;

	private Float price;

	private Integer quantity;

	private Integer cartId;

	public ItemDTO(Item item) {
		this.setId(item.getId());
		this.setName(item.getName());
		this.setPrice(item.getPrice());
		this.setQuantity(item.getQuantity());
		this.setCartId(item.getCart().getId());
	}

	public ItemDTO() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

}
