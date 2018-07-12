package com.kdgohokar.statistics.repository;

import java.util.List;

import com.kdgohokar.statistics.vo.Transaction;

/**
 * @author kirangohokar
 * Transaction Repository
 */
public interface TransactionRepository {
	
	/**
	 * Method to add transactions from last 60 seconds.
	 * @param transaction
	 * @return {@code true} for success otherwise false.
	 */
	boolean add(Transaction transaction);
	
	/**
	 * Find all transactions.
	 * @return {@code List<Transaction>}
	 */
	List<Transaction> findAll();

}
