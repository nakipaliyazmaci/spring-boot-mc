package com.sbmc.nali.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sbmc.nali.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>
{
	
}
