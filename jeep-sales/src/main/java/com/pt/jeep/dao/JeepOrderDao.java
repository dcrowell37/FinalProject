package com.pt.jeep.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import com.pt.jeep.entity.Color;
import com.pt.jeep.entity.Customer;
import com.pt.jeep.entity.Engine;
import com.pt.jeep.entity.Jeep;
import com.pt.jeep.entity.JeepModel;
import com.pt.jeep.entity.Option;
import com.pt.jeep.entity.Order;
import com.pt.jeep.entity.Tire;

public interface JeepOrderDao {
  List<Option> fetchOptions(List<String> optionIds);
  Optional<Customer> fetchCustomer(String customerId);
  Optional<Jeep> fetchModel(JeepModel model, String trim, int doors);
  Optional<Color> fetchColor(String colorId);
  Optional<Engine> fetchEngine(String engineId);
  Optional<Tire> fetchTire(String tireId);
  
  Order saveOrder(Customer customer, Jeep jeep, Color color, Engine engine, Tire tire,
      BigDecimal price, List<Option> options);
  
  

}
