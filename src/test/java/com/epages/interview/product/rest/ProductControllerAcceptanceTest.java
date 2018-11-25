package com.epages.interview.product.rest;

import static com.epages.interview.product.ProductTestFixture.given;
import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.epages.interview.InterviewApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = { InterviewApplication.class })
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.ANY)
@AutoConfigureTestEntityManager
public class ProductControllerAcceptanceTest {

	private static String EXPECTED_PAYLOAD = "{\"Brand A\" : [{\"id\": 1,\"name\": \"Product A\",\"price\": 12.99,\"event\": \"ON SALE\" }, {\"id\": 2,\"name\": \"Product B\",\"price\": 7.99}], \"Brand B\" : [{\"id\": 3,\"name\": \"Product C\",\"price\": 14.99}, {\"id\": 4,\"name\": \"Product D\",\"price\": 10.99}] }";

	@Autowired
	private MockMvc mvc;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	@Transactional
	public void givenProductsAcceptance_whenGet_thenPayloadIsAsClientExpects() throws Exception {
		given()
				.product(1L, "Product A", "Brand A", 12.99D, true)
				.product(2L, "Product B", "Brand A", 7.99D, false)
				.product(3L, "Product C", "Brand B", 14.99D, false)
				.product(4L, "Product D", "Brand B", 10.99D, false)
				.idBd(testEntityManager);

		MvcResult result = mvc.perform(get("/api/products").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		then(result.getResponse().getContentAsString()).isEqualToIgnoringWhitespace(EXPECTED_PAYLOAD);
	}
}
