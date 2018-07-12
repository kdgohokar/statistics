package com.kdgohokar.statistics.controller;

import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

/**
 * @author kirangohokar
 * 
 */
public interface Controller {

	default String stringifyErrors(BindingResult result) {
		return result.getFieldErrors()
				.stream()
				.map(x -> x.getField() + " : " + x.getDefaultMessage())
				.collect(Collectors.joining(", "));
	}

}
