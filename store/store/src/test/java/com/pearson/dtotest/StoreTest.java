package com.pearson.dtotest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pearson.dto.Store;

public class StoreTest {

	String json = "{\"storeId\":\"1\",\"postCode\":\"56655\",\"city\":\"BLR\",\"address\":\"KRT\",\"openingDate\":\"21/10/2022\"}";
	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void serializationTest() throws JsonProcessingException, JSONException {
		Store store = new Store("1", "56655", "BLR", "KRT", "21/10/2022");
		String writeValueAsString = mapper.writeValueAsString(store);
		JSONAssert.assertEquals(json, writeValueAsString, true);
	}

	@Test
	public void deserializationTest() throws JsonProcessingException, JSONException {
		Store store = mapper.readValue(json, Store.class);
		assertEquals("1", store.getStoreId());
	}

}
