package com.kdgohokar.statistics.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdgohokar.statistics.repository.TransactionRepository;
import com.kdgohokar.statistics.service.TransactionsService;
import com.kdgohokar.statistics.vo.Transaction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionsServiceImpl implements TransactionsService {

	@Autowired
	private TransactionRepository transactionRepo;
	
	@Override
	public boolean createTransaction(Transaction transaction) {
		log.info("Creating transaction for : {}", transaction);
		return transactionRepo.add(transaction);
	}

}
