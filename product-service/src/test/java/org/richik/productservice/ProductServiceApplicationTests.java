package org.richik.productservice;

import org.junit.jupiter.api.Test;
import org.richik.productservice.dto.ProductRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Container
	private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.26")
			.withDatabaseName("testdb")
			.withUsername("testuser")
			.withPassword("testpass")
			;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	//Setting up a dynamic property source to set the properties of the datasource
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicReg) {
		dynamicReg.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
		dynamicReg.add("spring.datasource.username", mySQLContainer::getUsername);
		dynamicReg.add("spring.datasource.password", mySQLContainer::getPassword);
	}


	@Test
	void expectedCreateProduct() throws Exception {
		ProductRequestDto productRequestDto = getProductRequest();
		String productResponse = objectMapper.writeValueAsString(productRequestDto);
		mockMvc.perform(MockMvcRequestBuilders.post("/products/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productResponse))
				.andExpect(status().isAccepted());
	}

	private ProductRequestDto getProductRequest() {
		return ProductRequestDto.builder()
				.name("Iphone 13")
				.price(49000.0)
				.description("Apple")
				.build();
	}

}
