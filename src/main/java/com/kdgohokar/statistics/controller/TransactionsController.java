package com.kdgohokar.statistics.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdgohokar.statistics.service.TransactionsService;
import com.kdgohokar.statistics.validator.TransactionValidator;
import com.kdgohokar.statistics.vo.Transaction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/transactions")
public class TransactionsController implements Controller {

	@Autowired
	private TransactionsService service;
	
	@Autowired
	private TransactionValidator validator;
	
	@PostMapping
	public ResponseEntity<String> handleTransacton(@Valid @RequestBody Transaction transaction, BindingResult result) {

		log.info("Got the transaction request : {}", transaction);

		ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);

		if(result.hasErrors()) {
			log.error(stringifyErrors(result));
			return response;
		} else {
			if(service.createTransaction(transaction)) {
				response = new ResponseEntity<>(HttpStatus.CREATED);
			}
		}

		return response;
	}
	
	
	@InitBinder
	protected void init(WebDataBinder binder) {
		binder.addValidators(validator);
	}

}
