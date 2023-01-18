package com.sbmc.nali.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbmc.nali.orderservice.model.Order;

public interface OrderRepository  extends JpaRepository<Order, Long> {

}
