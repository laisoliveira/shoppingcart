package com.avenue.shoppingcart.domain.cart;


import org.junit.Assert;
import org.junit.Test;

import com.avenue.shoppingcart.domain.product.MockProductRepository;
import com.avenue.shoppingcart.domain.product.ProductService;
import com.avenue.shoppingcart.domain.product.ProductServiceImpl;
import com.avenue.shoppingcart.exception.InvalidProductOperationException;

public class ShoppingCartServiceTest{
    
    @Test
    public void testAddNewItem() throws Exception {
    	ProductService productService = new ProductServiceImpl(new MockProductRepository());
    	ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl(productService);
    	shoppingCartService.addNewItem("user1", 1L, 2);
    	Assert.assertFalse(shoppingCartService.listCartItems("user1").isEmpty());
    }
    
    @Test
    public void testAddNewItemWhenQuantityIsEqualProductStock() throws Exception {
    	ProductService productService = new ProductServiceImpl(new MockProductRepository());
    	ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl(productService);
    	shoppingCartService.addNewItem("user1", 1L, 3);
    	Assert.assertFalse(shoppingCartService.listCartItems("user1").isEmpty());
    }
    
    @Test(expected = InvalidProductOperationException.class)
    public void testAddNewItemWhenQuantityIsBiggerThanProductStock() throws Exception {
    	ProductService productService = new ProductServiceImpl(new MockProductRepository());
    	ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl(productService);
    	shoppingCartService.addNewItem("user1", 1L, 4);
    	Assert.assertTrue(shoppingCartService.listCartItems("user1").isEmpty());
    }
    
    @Test
    public void testAddDifferentProductsToDifferentCarts() throws Exception {
    	ProductService productService = new ProductServiceImpl(new MockProductRepository());
    	ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl(productService);
    	shoppingCartService.addNewItem("user1", 1L, 1);
    	shoppingCartService.addNewItem("user1", 2L, 3);
    	
    	shoppingCartService.addNewItem("user2", 1L, 2);
    	shoppingCartService.addNewItem("user2", 3L, 3);
    	
    	Assert.assertFalse(shoppingCartService.listCartItems("user1").isEmpty());
    	Assert.assertFalse(shoppingCartService.listCartItems("user2").isEmpty());
    }
   
}
