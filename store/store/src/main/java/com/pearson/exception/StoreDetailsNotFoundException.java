package com.pearson.exception;

@SuppressWarnings("serial")
public class StoreDetailsNotFoundException extends RuntimeException {
	public StoreDetailsNotFoundException(String message) {
		super(message);
	}

}
