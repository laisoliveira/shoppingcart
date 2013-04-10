package com.avenue.shoppingcart.domain.product;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Named;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

@Named
public class MockProductRepository implements ProductRepository{

	private List<Product> detailedProducts = new ArrayList<Product>();
	
	public MockProductRepository() {
		detailedProducts.add(new Product(1L,"product one", "description", null, new BigDecimal("20.50"), BigInteger.valueOf(3),BigInteger.valueOf(3)));
		detailedProducts.add(new Product(2L,"product two", "description", null,new BigDecimal("19.99"), BigInteger.valueOf(4),BigInteger.valueOf(4)));
		detailedProducts.add(new Product(3L,"product three","description", null, new BigDecimal("57.00"), BigInteger.TEN,BigInteger.TEN));
		detailedProducts.add(new Product(4L,"product four", "description", null,new BigDecimal("12.99"), BigInteger.valueOf(20),BigInteger.valueOf(20)));
		detailedProducts.add(new Product(5L,"product five", "description", null,new BigDecimal("30.00"), BigInteger.valueOf(7),BigInteger.valueOf(7)));
	}
	
	public void save(Product product) {
		// TODO Auto-generated method stub
	}

	public void update(Product product) {
		int index = detailedProducts.indexOf(product);
		detailedProducts.set(index, product);
	}

	public void remove(Product product) {
		// TODO Auto-generated method stub
	}

	public Product loadDetails(final Long productId) {
		Product product = (Product) CollectionUtils.find(detailedProducts, new Predicate() {
			public boolean evaluate(Object arg0) {
				Product p = (Product) arg0;
				return p.getProductId().equals(productId);
			}
		});
		return new Product(product.getProductId(), product.getName(),
				product.getDescription(), product.getPhoto(),
				product.getPrice(), product.getAvailableStockQuantity(), product.getInitialStockQuantity());
	}

	public Collection<Product> list() {
		return detailedProducts;
	}

}
