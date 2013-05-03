package com.sandbox.service;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.avenue.shoppingcart.domain.product.Product;

@Path("/products")
public interface ProductBrowserRestService {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Product> getProducts();
	

	@GET
	@Path("/{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProductByName(@PathParam("productId") Long productId);
	
}
