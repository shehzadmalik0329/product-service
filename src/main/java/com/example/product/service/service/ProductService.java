package com.example.product.service.service;

import java.util.List;

import com.example.product.service.model.Product;

public interface ProductService {
	
	public Product saveProduct(Product product);
	
	public Product updateProduct(Long id, Product product);
	
	public Product getProduct(Long id);
	
	public List<Product> getProducts(String category);

}
