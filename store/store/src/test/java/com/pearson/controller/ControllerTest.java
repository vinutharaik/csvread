package com.pearson.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pearson.dto.Store;
import com.pearson.service.StoreServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StoreController.class)
public class ControllerTest {

	@MockBean
	private StoreServiceImpl implementation;

	@InjectMocks
	private StoreController storeController;

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	void testFetchStoreDetailsByID() throws UnsupportedEncodingException, Exception {

		Store store = new Store();
		store.setStoreId("1525eec4-7bed-4597-bf5a-e06fcede5f4f");
		store.setPostCode("563322");
		store.setCity("bangalore");
		store.setAddress("krt");
		store.setOpeningDate("12/12/1776");

		Mockito.when(implementation.getStoreById(Mockito.anyString())).thenReturn(store);
		String jsonObject = mapper.writeValueAsString(store);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/getByStoreId/1525eec4-7bed-4597-bf5a-e06fcede5f4f")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertThat(result.getResponse().getContentAsString()).contains("1525eec4-7bed-4597-bf5a-e06fcede5f4f");

	}
	
}
