package com.avenue.shoppingcart.domain.product;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

import com.avenue.shoppingcart.exception.InvalidProductOperationException;

public class ProductTest {

	@Test
	public void testDecreaseStock() throws Exception {
		MockProductRepository mockProductRepository = new MockProductRepository();
		Product product = mockProductRepository.loadDetails(1L);
		BigInteger stockBefore = product.getAvailableStockQuantity();
		product.decreaseStock(1);
		BigInteger stockAfter = product.getAvailableStockQuantity();
		Assert.assertEquals(stockBefore.subtract(BigInteger.ONE), stockAfter);
	}
	
	@Test (expected = InvalidProductOperationException.class)
	public void testDecreaseStockWhenQuantityIsBiggerThanAvailableStock() throws Exception {
		MockProductRepository mockProductRepository = new MockProductRepository();
		Product product = mockProductRepository.loadDetails(1L);
		product.decreaseStock(10);
	}
	
	@Test
	public void testIncreaseStock() throws Exception {
		MockProductRepository mockProductRepository = new MockProductRepository();
		Product product = mockProductRepository.loadDetails(1L);
		product.decreaseStock(1);
		BigInteger stockBefore = product.getAvailableStockQuantity();
		product.increaseStock(1);
		BigInteger stockAfter = product.getAvailableStockQuantity();
		Assert.assertEquals(stockBefore.add(BigInteger.ONE), stockAfter);
	}
	
	@Test (expected= InvalidProductOperationException.class)
	public void testIncreaseStockWhenQuantityIsBiggerThanAvailableStock() throws Exception {
		MockProductRepository mockProductRepository = new MockProductRepository();
		Product product = mockProductRepository.loadDetails(1L);
		product.increaseStock(5);
	}

}
