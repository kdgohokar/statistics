package com.kdgohokar.statistics.controller;

import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdgohokar.statistics.vo.Transaction;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/transactions")
@Slf4j
public class TransactionsController {

	@PostMapping
	public CompletableFuture<ResponseEntity<String>> handleTransacton(@Valid @RequestBody Transaction transaction, BindingResult result) {

		log.info("Got the transaction request : {}", transaction);

		CompletableFuture<ResponseEntity<String>> response;

		if(result.hasErrors()) {
			response = CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.NO_CONTENT));
		} else {
			response = CompletableFuture.supplyAsync(() -> new ResponseEntity<>(HttpStatus.CREATED));
		}

		return response;
	}

}
