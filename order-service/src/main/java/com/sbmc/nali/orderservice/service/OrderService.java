package com.sbmc.nali.orderservice.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sbmc.nali.orderservice.dto.OrderLineItemsDto;
import com.sbmc.nali.orderservice.dto.OrderRequest;
import com.sbmc.nali.orderservice.model.Order;
import com.sbmc.nali.orderservice.model.OrderLineItem;
import com.sbmc.nali.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	
	public void placeOrder(OrderRequest request) 
	{
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		request.getDtoList()
			   .stream()
			   .map(orderLineItemsDto -> mapToDto(orderLineItemsDto));
		
		orderRepository.save(order);
	}

	private OrderLineItem mapToDto(OrderLineItemsDto orderLineItemsDto) 
	{
		OrderLineItem item = new OrderLineItem();
		item.setPrice(orderLineItemsDto.getPrice());
		item.setQuantity(orderLineItemsDto.getQuantity());
		item.setSkuCode(orderLineItemsDto.getSkuCode());
		return item;
	}

}
