package com.sbmc.nali.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sbmc.nali.inventoryservice.model.Inventory;
import com.sbmc.nali.inventoryservice.repository.InventoryRepository;

@SpringBootApplication
public class InventoryServiceApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) 
	{
		return args->
		{
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iPhone_13");
			inventory1.setQuantity(100);
			
			Inventory inventory2= new Inventory();
			inventory2.setSkuCode("iPhone_13_pro");
			inventory2.setQuantity(100);
			
			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
		};
	}
}
