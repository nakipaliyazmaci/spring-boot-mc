package com.sbmc.nali.inventoryservice.service;

import org.springframework.stereotype.Service;

import com.sbmc.nali.inventoryservice.repository.InventoryRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService 
{
	private final InventoryRepository inventoryRepository;
	
	@Transactional
	public boolean isInStock(String skuCode) 
	{
		return inventoryRepository.findBySkuCode(skuCode).isPresent();
		
	}

}
