package com.kdgohokar.statistics.repository;

import java.util.List;

import com.kdgohokar.statistics.vo.Transaction;

/**
 * @author kirangohokar
 * Transaction Repository
 */
public interface TransactionRepository {
	
	boolean add(Transaction transaction);
	
	List<Transaction> findAll();

}
