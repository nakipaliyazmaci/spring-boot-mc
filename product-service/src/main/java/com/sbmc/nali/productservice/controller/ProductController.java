package com.sbmc.nali.productservice.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sbmc.nali.productservice.dto.ProductRequest;
import com.sbmc.nali.productservice.dto.ProductResponse;
import com.sbmc.nali.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController 
{
	private final ProductService productService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest request) 
	{
		productService.createProduct(request);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getAllProducts() 
	{
		return productService.getAllProducts();
	}
}
