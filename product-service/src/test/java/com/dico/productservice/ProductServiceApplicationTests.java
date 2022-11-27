package com.dico.productservice;

import com.dico.productservice.Model.Product;
import com.dico.productservice.Repository.ProductRepository;
import com.dico.productservice.dto.ProductRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	/*
	  Preapre the testcontainer connection string url
	 */
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@Autowired
	private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;
	@Autowired
	private ObjectMapper objectMapper;

	 /*
	  set the connection string to replicate the original uri in this test container using dynamicPropertySource Annotation
	 */

	@DynamicPropertySource
	static  void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void shouldCreateProduct() throws Exception {
		 ProductRequest productRequest = getProductRequest();
		//convert to object mapper
	     String productRequestString =	objectMapper.writeValueAsString(productRequest);
         mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(productRequestString))
			   .andExpect(status().isCreated());
        Assertions.assertEquals(1, productRepository.findAll().size());
	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("Red Wine")
				.description("Nigerian Red bull drink")
				.price(BigDecimal.valueOf(16000))
				.build();
	}

}
