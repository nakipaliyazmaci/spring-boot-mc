package com.sbmc.nali.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbmc.nali.orderservice.dto.OrderLineItemsDto;
import com.sbmc.nali.orderservice.dto.OrderRequest;
import com.sbmc.nali.orderservice.model.Order;
import com.sbmc.nali.orderservice.model.OrderLineItem;
import com.sbmc.nali.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

	private final OrderRepository orderRepository;
	
	public void placeOrder(OrderRequest request) 
	{
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItem> orderLineItemList = request.getDtoList()
			   .stream()
			   .map(this::mapToDto)
			   .toList();
		order.setOrderLineItem(orderLineItemList);
		orderRepository.save(order);
	}

	private OrderLineItem mapToDto(OrderLineItemsDto orderLineItemsDto) 
	{
		OrderLineItem item = new OrderLineItem();
		item.setPrice(orderLineItemsDto.getPrice());
		item.setQuantity(orderLineItemsDto.getQuantity());
		item.setSkuCode(orderLineItemsDto.getSkuCode());
		log.debug(orderLineItemsDto.toString());
		return item;
	}

}
