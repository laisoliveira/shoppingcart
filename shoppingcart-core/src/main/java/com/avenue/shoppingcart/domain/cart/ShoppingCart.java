package com.avenue.shoppingcart.domain.cart;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

public class ShoppingCart {

	private String id;
	private Set<ShoppingCartItem> shoppingCartItems;
	
	public ShoppingCart(String id) {
		this.id = id;
		this.shoppingCartItems = new LinkedHashSet<ShoppingCartItem>();
	}
	
	void add(final ShoppingCartItem shoppingCartItem){
		if(!shoppingCartItems.add(shoppingCartItem)){
			ShoppingCartItem foundCartItem = findShoppingCartItemEqual(shoppingCartItem);
			shoppingCartItem.setQuantity(foundCartItem.getQuantity()+shoppingCartItem.getQuantity());
			updateQuantity(shoppingCartItem);
		}
	}
	
	void remove(ShoppingCartItem shoppingCartItem){
		shoppingCartItems.remove(shoppingCartItem);
	}
	
	void updateQuantity(final ShoppingCartItem shoppingCartItem){
		if(shoppingCartItems.remove(shoppingCartItem)){
			shoppingCartItems.add(shoppingCartItem);
		}
	}
	
	void clear(){
		shoppingCartItems.clear();
	}
	
	private ShoppingCartItem findShoppingCartItemEqual(final ShoppingCartItem shoppingCartItem){
		ShoppingCartItem foundCartItem = (ShoppingCartItem)CollectionUtils.find(shoppingCartItems, new Predicate() {
			public boolean evaluate(Object arg0) {
				ShoppingCartItem cartItem = (ShoppingCartItem) arg0;
				return cartItem.equals(shoppingCartItem);
			}
		});
		return foundCartItem;
	}
	
	public BigDecimal getPrice() {
		BigDecimal price = BigDecimal.ZERO;
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			price = price.add(shoppingCartItem.getPrice());
		}
		return price;
	}
	
	public String getId() {
		return id;
	}
	
	public Collection<ShoppingCartItem> getShoppingCartItems() {
		LinkedHashSet<ShoppingCartItem> shoppingCartItems = new LinkedHashSet<ShoppingCartItem>();
		for (ShoppingCartItem shoppingCartItem : this.shoppingCartItems) {
			shoppingCartItems.add(new ShoppingCartItem(shoppingCartItem.getProduct(), shoppingCartItem.getQuantity()));	
		}
		return shoppingCartItems;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ShoppingCart other = (ShoppingCart) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
