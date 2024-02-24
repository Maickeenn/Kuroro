package br.com.stratzord.kuroro;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest
class KuroroApplicationTests {

  static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.26")
      .withDatabaseName("test")
      .withUsername("test")
      .withPassword("test");

  @DynamicPropertySource
  public static void setDatasourceProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", mysql::getJdbcUrl);
    registry.add("spring.datasource.username", mysql::getUsername);
    registry.add("spring.datasource.password", mysql::getPassword);
  }

  @BeforeAll
  static void startDb() {
    mysql.start();
  }

  @AfterAll
  static void stopDb() {
    mysql.stop();
  }

  @Test
  void contextLoads() {
  }

}
