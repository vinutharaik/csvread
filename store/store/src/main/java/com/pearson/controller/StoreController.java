package com.pearson.controller;

import java.util.List;

import org.apache.tomcat.util.threads.StopPooledThreadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pearson.dto.Store;
import com.pearson.responsemessage.ResponseMessage;
import com.pearson.service.StoreServiceImpl;

@RestController
@RequestMapping("/api/v1/")
public class StoreController {
	Logger logger = LoggerFactory.getLogger(Store.class);

	@Autowired
	private StoreServiceImpl storeServiceImpl;

	@GetMapping("getAll")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(storeServiceImpl.getAll());
	}

	@GetMapping("getByStoreId/{storeId}")
	public ResponseEntity<?> getByStoreId(@PathVariable String storeId) {
		Store storeById = storeServiceImpl.getStoreById(storeId);
		if (storeById == null) {
			logger.error("No such store id is found");
			throw new StopPooledThreadException("storeId not found");
		}
		logger.info("The data is found for given stored Id");
		return new ResponseEntity<ResponseMessage>(new ResponseMessage("The data is found", storeById),HttpStatus.OK);
	}

	@GetMapping("orederedByCities")
	public ResponseEntity<?> orderedByCity() {
		List<Store> storesOrderesByCity = storeServiceImpl.getStoresOrderesByCity();
		if (storesOrderesByCity.isEmpty()) {
			logger.error("Data is not oredered based on city");
			throw new StopPooledThreadException("Data is not ordered based on city");
		}
		logger.info("Data is oreded based on city");
		return new ResponseEntity<ResponseMessage>(new ResponseMessage("The data is ordered based on City", storesOrderesByCity),HttpStatus.OK);

	}

	@GetMapping("orederedByOpeningDate")
	public ResponseEntity<?> orderedByOpeningDate() {
		List<Store> storesOrderesByDate = storeServiceImpl.getStoresOrderesByDate();
		if (storesOrderesByDate.isEmpty()) {
			logger.error("The data is not ordered based on openingDate");
			throw new StopPooledThreadException("The data is not ordered based on openingDate");
		}
		logger.info("The data is oredered based on openingDate");
		return new ResponseEntity<ResponseMessage>(new ResponseMessage("The Store Data Is", storesOrderesByDate),
				HttpStatus.OK);
	}

}
