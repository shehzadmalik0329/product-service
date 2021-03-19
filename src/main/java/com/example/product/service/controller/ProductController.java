package com.example.product.service.controller;

import java.util.Collections;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.service.model.Product;
import com.example.product.service.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<Product> saveProduct(@RequestBody Product product){
		Product newProduct = productService.saveProduct(product);
		
		if(ObjectUtils.isEmpty(newProduct)){
			return ResponseEntity.status(400).build();
		} else {
			return ResponseEntity.status(201).body(newProduct);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id,
			@RequestBody Product product){
		Product newProduct = productService.updateProduct(id, product);
		
		if(ObjectUtils.isEmpty(newProduct)){
			return ResponseEntity.status(404).build();
		} else {
			return ResponseEntity.status(200).body(newProduct);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Long id){
		Product product = productService.getProduct(id);
		
		if(ObjectUtils.isEmpty(product)){
			return ResponseEntity.status(404).build();
		} else {
			return ResponseEntity.status(200).body(product);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) String category){
		List<Product> products = productService.getProducts(category);
		
		if(CollectionUtils.isEmpty(products)){
			return ResponseEntity.status(200).body(Collections.emptyList());
		} else {
			return ResponseEntity.status(200).body(products);
		}
	}

}
