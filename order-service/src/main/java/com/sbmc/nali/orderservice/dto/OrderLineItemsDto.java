package com.sbmc.nali.orderservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderLineItemsDto 
{
	private Long id;	
	private String skuCode;
	private BigDecimal price;
	private Integer quantity;
}
