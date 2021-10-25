package com.acme.sales.OrderLine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
	
	List<OrderLine> findOrderLineByOrderId(int orderId);

}
