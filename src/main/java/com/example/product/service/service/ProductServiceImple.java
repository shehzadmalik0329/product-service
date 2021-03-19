package com.example.product.service.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.product.service.model.Product;
import com.example.product.service.repository.ProductRepository;

@Service
public class ProductServiceImple implements ProductService{
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product saveProduct(Product product) {
		Long id = product.getId();
		
		Optional<Product> existingProduct = productRepository.findById(id);
		
		if(!existingProduct.isPresent()){
			return productRepository.save(product);
		}
		return null;
	}

	@Override
	public Product updateProduct(Long id, Product product) {
		Optional<Product> existingProduct = productRepository.findById(id);
		Product newProduct = null;
		if(existingProduct.isPresent()){
			Product oldProduct = existingProduct.get();
			oldProduct.setRetailPrice(product.getRetailPrice());
			oldProduct.setDiscountedPrice(product.getDiscountedPrice());
			oldProduct.setAvailability(product.getAvailability());
			
			newProduct = productRepository.save(oldProduct);
		}
		return newProduct;
	}

	@Override
	public Product getProduct(Long id) {
		Optional<Product> product = productRepository.findById(id);
		
		if(product.isPresent()){
			return product.get();
		}
		return null;
	}

	@Override
	public List<Product> getProducts(String category) {
		List<Product> products = null;
		
		if(ObjectUtils.isEmpty(category)){
			products = productRepository.findAll();
			products.sort(Comparator.comparing(Product::getId));
		} else {
			products = productRepository.findByCategory(category);
			Collections.sort(products, Comparator.comparing(Product::getAvailability,
					(p1,p2) -> -p1.compareTo(p2))
					.thenComparing(Product::getDiscountedPrice)
					.thenComparing((p1, p2) -> -(p1.getId().compareTo(p2.getId())) ));
		}
		
		return products;
	}

}
