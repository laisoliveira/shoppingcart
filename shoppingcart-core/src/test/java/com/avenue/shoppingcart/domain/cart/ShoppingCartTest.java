package com.avenue.shoppingcart.domain.cart;


import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.avenue.shoppingcart.domain.product.MockProductRepository;
import com.avenue.shoppingcart.domain.product.Product;

public class ShoppingCartTest{
    
    @Test
    public void testAddOne() throws Exception {
    	MockProductRepository mockProductRepository = new MockProductRepository();
    	Product product = mockProductRepository.loadDetails(1L);
    	ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, 1);
    	
    	ShoppingCart shoppingCart = new ShoppingCart("user1");
    	shoppingCart.add(shoppingCartItem);
    	
    	Assert.assertTrue(shoppingCart.getShoppingCartItems().contains(shoppingCartItem));
    	Assert.assertEquals(1, shoppingCart.getShoppingCartItems().size());
    	Assert.assertEquals(product.getPrice(), shoppingCart.getPrice());
    }
    
    @Test
    public void testAddDifferentProducts() throws Exception {
    	MockProductRepository mockProductRepository = new MockProductRepository();
    	
    	Product productOne = mockProductRepository.loadDetails(1L);
    	ShoppingCartItem shoppingCartItemOne = new ShoppingCartItem(productOne, 1);
    	
    	Product productTwo = mockProductRepository.loadDetails(2L);
    	ShoppingCartItem shoppingCartItemTwo = new ShoppingCartItem(productTwo, 3);
    	
    	ShoppingCart shoppingCart = new ShoppingCart("user1");
    	shoppingCart.add(shoppingCartItemOne);
    	shoppingCart.add(shoppingCartItemTwo);
    	
     	Assert.assertEquals(2, shoppingCart.getShoppingCartItems().size());
     	Assert.assertTrue(shoppingCart.getShoppingCartItems().contains(shoppingCartItemOne));
     	Assert.assertTrue(shoppingCart.getShoppingCartItems().contains(shoppingCartItemTwo));
    	Assert.assertEquals(productOne.getPrice().add(productTwo.getPrice().multiply(BigDecimal.valueOf(3))), shoppingCart.getPrice());       
    }
    
    @Test
    public void testAddSameProducts() throws Exception {
    	MockProductRepository mockProductRepository = new MockProductRepository();
    	
    	Product productOne = mockProductRepository.loadDetails(1L);
    	ShoppingCartItem shoppingCartItemOne = new ShoppingCartItem(productOne, 1);
    	
    	Product productTwo = mockProductRepository.loadDetails(1L);
    	ShoppingCartItem shoppingCartItemTwo = new ShoppingCartItem(productTwo, 3);
    	
    	ShoppingCart shoppingCart = new ShoppingCart("user1");
    	shoppingCart.add(shoppingCartItemOne);
    	shoppingCart.add(shoppingCartItemTwo);
    	
     	Assert.assertEquals(1, shoppingCart.getShoppingCartItems().size());
    	Assert.assertEquals(4, shoppingCart.getShoppingCartItems().iterator().next().getQuantity());
     	Assert.assertEquals(productTwo.getPrice().multiply(BigDecimal.valueOf(4)), shoppingCart.getPrice());       
    }
    
    @Test
    public void testClear() throws Exception {
    	MockProductRepository mockProductRepository = new MockProductRepository();
    	Product product = mockProductRepository.loadDetails(1L);
    	ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, 1);
    	
    	ShoppingCart shoppingCart = new ShoppingCart("user1");
    	shoppingCart.add(shoppingCartItem);
    	
    	shoppingCart.clear();
    	
    	Assert.assertTrue(shoppingCart.getShoppingCartItems().isEmpty());
    }
    
    @Test
    public void testRemove() throws Exception {
    	MockProductRepository mockProductRepository = new MockProductRepository();
    	Product product = mockProductRepository.loadDetails(1L);
    	ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, 1);
    	
    	ShoppingCart shoppingCart = new ShoppingCart("user1");
    	shoppingCart.add(shoppingCartItem);
    	
    	shoppingCart.remove(shoppingCartItem);
    	
    	Assert.assertFalse(shoppingCart.getShoppingCartItems().contains(shoppingCartItem));
    }
    
    @Test
    public void testUpdate() throws Exception {
    	MockProductRepository mockProductRepository = new MockProductRepository();
    	Product product = mockProductRepository.loadDetails(1L);
    	ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, 1);
    	
    	ShoppingCart shoppingCart = new ShoppingCart("user1");
    	shoppingCart.add(shoppingCartItem);
    	
    	ShoppingCartItem shoppingCartItemUpdated = new ShoppingCartItem(product, 3);
    	shoppingCart.updateQuantity(shoppingCartItemUpdated);
    	
    	Assert.assertTrue(shoppingCart.getShoppingCartItems().contains(shoppingCartItem));
    	Assert.assertEquals(shoppingCartItemUpdated.getQuantity(), shoppingCart.getShoppingCartItems().iterator().next().getQuantity());
    }
  
}
