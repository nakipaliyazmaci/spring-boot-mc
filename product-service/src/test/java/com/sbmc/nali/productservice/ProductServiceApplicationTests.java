package com.sbmc.nali.productservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbmc.nali.productservice.dto.ProductRequest;
import com.sbmc.nali.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@RequiredArgsConstructor
class ProductServiceApplicationTests {
	
	private  MockMvc mockMvc;
	private  ObjectMapper objectMapper;
	private  ProductRepository productRepository;
	
	
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.1");
	
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}
	
	@Test
	void createProductTest() throws Exception 
	{
		ProductRequest productRequest = getProductRequest();
		String productReq = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
											   .contentType(MediaType.APPLICATION_JSON)
											   .content(productReq))
												.andExpect(status().isCreated());
	}
	
	@Test 
	void getAllProductValidationTest()
	{
		Assertions.assertEquals(productRepository.findAll().size(),1);
	}
	
	private ProductRequest getProductRequest() 
	{
		return ProductRequest.builder()
							 .name("test-1")
							 .description("test product description")
							 .price(new BigDecimal("12"))
							 .build();
	}
}
