package com.sbmc.nali.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbmc.nali.productservice.dto.ProductRequest;
import com.sbmc.nali.productservice.dto.ProductResponse;
import com.sbmc.nali.productservice.model.Product;
import com.sbmc.nali.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService 
{
	private final ProductRepository productRepository;
	
	public void createProduct(ProductRequest request) 
	{
		Product product = Product.builder()
						  .name(request.getName())
						  .description(request.getDescription())
						  .price(request.getPrice())
						  .build();
		
		productRepository.save(product);
		log.info("product is saved, details:"+product.toString());
	}

	public List<ProductResponse> getAllProducts() {
		List<Product> productList = productRepository.findAll();
		return productList.stream().map(this::mapToProductResponse).toList();
	}

	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder()
							  .id(product.getId())
							  .description(product.getDescription())
							  .name(product.getName())
							  .price(product.getPrice())
							  .build();
	}
}
