package com.avenue.shoppingcart.domain.cart;

import java.math.BigDecimal;

import com.avenue.shoppingcart.domain.product.Product;

public class ShoppingCartItem {

	private Product product;
	private Integer quantity;
	
	public ShoppingCartItem(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return this.product.getPrice().multiply(new BigDecimal(this.quantity));
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCartItem other = (ShoppingCartItem) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
	
	
}
