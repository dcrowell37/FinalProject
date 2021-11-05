package com.pt.jeep.dao;

import java.util.List;
import com.pt.jeep.entity.Jeep;
import com.pt.jeep.entity.JeepModel;

public interface JeepSalesDao {
  /*
   * 
   * @param model
   * @param trim
   * @return
   */

  List<Jeep> fetchJeeps(JeepModel model, String trim);

}
