package com.flowers.microservice.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.flowers.microservice.product.ProductApplication;
import com.flowers.microservice.product.domain.*;
import com.flowers.microservice.product.service.ProductService;
import com.sun.security.auth.UserPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("restriction")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductApplication.class)
@WebAppConfiguration
public class ProductControllerTest {

	private static final ObjectMapper mapper = new ObjectMapper();

	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductService productService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	public void shouldGetProductByName() throws Exception {

		final Product product = new Product();
		product.setName("test");

		when(productService.findByName(product.getName())).thenReturn(product);

		mockMvc.perform(get("/" + product.getName()))
				.andExpect(jsonPath("$.name").value(product.getName()))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldGetCurrentProduct() throws Exception {

		final Product product = new Product();
		product.setName("test");

		when(productService.findByName(product.getName())).thenReturn(product);

		mockMvc.perform(get("/current").principal(new UserPrincipal(product.getName())))
				.andExpect(jsonPath("$.name").value(product.getName()))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldSaveCurrentProduct() throws Exception {

		Saving saving = new Saving();
		saving.setAmount(new BigDecimal(1500));
		saving.setCurrency(Currency.USD);
		saving.setInterest(new BigDecimal("3.32"));
		saving.setDeposit(true);
		saving.setCapitalization(false);

		Item grocery = new Item();
		grocery.setTitle("Grocery");
		grocery.setAmount(new BigDecimal(10));
		grocery.setCurrency(Currency.USD);
		grocery.setPeriod(TimePeriod.DAY);
		grocery.setIcon("meal");

		Item salary = new Item();
		salary.setTitle("Salary");
		salary.setAmount(new BigDecimal(9100));
		salary.setCurrency(Currency.USD);
		salary.setPeriod(TimePeriod.MONTH);
		salary.setIcon("wallet");

		final Product product = new Product();
		product.setName("test");
		product.setNote("test note");
		product.setLastSeen(new Date());
		product.setSaving(saving);
		product.setExpenses(ImmutableList.of(grocery));
		product.setIncomes(ImmutableList.of(salary));

		String json = mapper.writeValueAsString(product);

		mockMvc.perform(put("/current").principal(new UserPrincipal(product.getName())).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldFailOnValidationTryingToSaveCurrentProduct() throws Exception {

		final Product product = new Product();
		product.setName("test");

		String json = mapper.writeValueAsString(product);

		mockMvc.perform(put("/current").principal(new UserPrincipal(product.getName())).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void shouldRegisterNewProduct() throws Exception {

		final User user = new User();
		user.setUsername("test");
		user.setPassword("password");

		String json = mapper.writeValueAsString(user);
		System.out.println(json);
		mockMvc.perform(post("/").principal(new UserPrincipal("test")).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldFailOnValidationTryingToRegisterNewProduct() throws Exception {

		final User user = new User();
		user.setUsername("t");

		String json = mapper.writeValueAsString(user);

		mockMvc.perform(post("/").principal(new UserPrincipal("test")).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isBadRequest());
	}
}
