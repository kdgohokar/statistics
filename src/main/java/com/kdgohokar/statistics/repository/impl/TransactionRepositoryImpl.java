package com.kdgohokar.statistics.repository.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.kdgohokar.statistics.repository.TransactionRepository;
import com.kdgohokar.statistics.vo.Transaction;

/**
 * @author kirangohokar
 * Implementation class of TransactionRepository
 */
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

	private static ConcurrentMap<Long, Transaction> transactionMap = new ConcurrentHashMap<>();
	
	@Override
	public boolean add(Transaction transaction) {
		transactionMap.put(transaction.getTimestamp(),transaction);
		return true;
	}

	@Override
	public List<Transaction> findAll() {
		return transactionMap.values().stream().collect(Collectors.toList());
	}

}
