package com.promineotech.guitar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;
import com.promineotech.guitar.controller.support.FetchGuitarTestSupport;
import com.promineotech.guitar.entity.Guitar;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(
    scripts = {"classpath:flyway/migrations/V1.0__Guitar_Schema.sql",
        "classpath:flyway/migrations/V1.1__Guitar_Data.sql"},
    config = @SqlConfig(encoding = "utf-8"))
class FetchGuitarTest extends FetchGuitarTestSupport {
 
  @Test
  void testThatGuitarsAreReturnedWhenAValidModelIsSupplied() {
    //System.out.println(getBaseUri());
    // Given: A valid model and URI
    String model = "912CE_TAYLOR";
    String uri = String.format("%s?model=%s",  getBaseUri(), model);
    System.out.println(uri);
    
    // When:  a connection is made to the URI
    ResponseEntity<List<Guitar>> response = 
        getRestTemplate().exchange(uri, HttpMethod.GET, null, 
            new ParameterizedTypeReference<>() {});
    
    // Then: A success (200) status code is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);  
    
    // And: the actual list returned is the same as expected list
    List<Guitar> expected = buildExpected();
    System.out.println(expected);
    assertThat(response.getBody()).isEqualTo(expected);
    
  }


}

