package com.pt.jeep.service;

import com.pt.jeep.entity.Order;
import com.pt.jeep.entity.OrderRequest;

public interface JeepOrderService {

  Order createOrder(OrderRequest orderRequest);

}
