package com.pt.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.pt.jeep.entity.Jeep;
import com.pt.jeep.entity.JeepModel;
import lombok.Getter;
import java.util.List;
import org.springframework.http.HttpMethod;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("Test")
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
    "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, 
    config = @SqlConfig(encoding = "utf-8"))

class FetchJeepTest {
  
  @LocalServerPort
  private int serverPort;
  
  @Autowired
  private TestRestTemplate restTemplate;
  
  protected String getBaseUri() {
    return String.format("http://localhost:%d/jeeps", serverPort);
  }
 

  @Test
  void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
    // Given: A valid model, trim and URI
    JeepModel model = JeepModel.WRANGLER;
    String trim = "Sport";
    String uri = 
        String.format("http://localhost:%d/jeeps?model=%s&trim=%s", serverPort, model, trim);
    System.out.println(uri);

    // When: a connection is made to the URI
       ResponseEntity<List<Jeep>> response = restTemplate.exchange(uri, 
             HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
    
    // Then: a success (OK - 200) is returned
   assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

}
