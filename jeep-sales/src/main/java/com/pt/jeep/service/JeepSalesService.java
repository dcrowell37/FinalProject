package com.pt.jeep.service;

import java.util.List;
import com.pt.jeep.entity.Jeep;

public interface JeepSalesService {

  
  
  List<Jeep> fetchJeeps(String model, String trim);

}
