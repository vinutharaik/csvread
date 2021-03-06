package com.pearson.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.pearson.dto.Store;
import com.pearson.service.StoreService;

@SpringBootTest
public class StoreServiceImplTest {

	@Mock
	private StoreService service;

	@Test
	public void testFetchallStore() {
		when(service.getStoreById("1")).thenReturn(new Store("1", "555", "mys", "mys", "openingDate"));
		String storeById = service.getStoreById("1").toString();
		assertEquals("Store [storeId=1, postCode=555, city=mys, address=mys, openingDate=openingDate]", storeById);
	}

	@Test
	public void testGetStoresOrderesByCity() {
		when(service.getStoresOrderesByCity())
				.thenReturn(Arrays.asList(new Store("1", "555", "mys", "mys", "openingDate")));
		List<Store> storesOrderesByCity = service.getStoresOrderesByCity();
		assertEquals("mys", storesOrderesByCity.get(0).getAddress());
	}

	@Test
	public void testOrederedByOpeningDate() {
		when(service.getStoresOrderesByDate())
				.thenReturn(Arrays.asList(new Store("1", "555", "mys", "mys", "openingDate")));
		List<Store> storesOrderesByDate = service.getStoresOrderesByDate();
		assertEquals("openingDate", storesOrderesByDate.get(0).getOpeningDate());

	}

	@Test
	public void testGetStoreById() {
		when(service.getStoreById("1")).thenReturn(new Store("1", "555", "mys", "mys", "openingDate"));
		String storeById = service.getStoreById("1").toString();
		assertEquals("Store [storeId=1, postCode=555, city=mys, address=mys, openingDate=openingDate]", storeById);

	}

}
