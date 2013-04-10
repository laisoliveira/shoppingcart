package com.avenue.shoppingcart.domain.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import com.avenue.shoppingcart.exception.InvalidProductOperationException;

public class Product implements Serializable, Comparable<Product>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4981599177394847346L;
	private Long productId;
	private String name;
	private String description;
	private byte[] photo;
	private BigDecimal price;
	private BigInteger initialStockQuantity;
	private BigInteger availableStockQuantity;
	
	public Product(Long productId) {
		this.productId = productId;
	}
	
	public Product(Long productId, String name, BigDecimal price) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.availableStockQuantity = BigInteger.ZERO;
		this.initialStockQuantity = BigInteger.ZERO;
	}
	
	public Product(Long productId, String name, String description,
			byte[] photo, BigDecimal price, BigInteger stockQuantity, BigInteger initialStockQuantity) {
		super();
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.photo = photo;
		this.price = price;
		this.availableStockQuantity = stockQuantity;
		this.initialStockQuantity = initialStockQuantity;
	}

	protected void increaseStock(long quantity) throws InvalidProductOperationException{
		BigInteger availableStockQuantity = this.availableStockQuantity.add(BigInteger.valueOf(quantity));
		if(availableStockQuantity.compareTo(initialStockQuantity)==1){
			throw new InvalidProductOperationException("There is not enough products in stock.");
		}
		this.availableStockQuantity = availableStockQuantity;
	}
	
	void decreaseStock(long quantity) throws InvalidProductOperationException{
		if(quantity > availableStockQuantity.longValue()){
			throw new InvalidProductOperationException("There is not enough products in stock.");
		}
		availableStockQuantity = availableStockQuantity.subtract(BigInteger.valueOf(quantity));
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigInteger getAvailableStockQuantity() {
		return availableStockQuantity;
	}

	BigInteger getInitialStockQuantity() {
		return initialStockQuantity;
	}
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
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
		Product other = (Product) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

	public int compareTo(Product o) {
		return this.productId.compareTo(o.getProductId());  
	}

}
