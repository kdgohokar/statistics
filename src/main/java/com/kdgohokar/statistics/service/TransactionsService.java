package com.kdgohokar.statistics.service;

import com.kdgohokar.statistics.vo.Transaction;

/**
 * @author kirangohokar on 12-07-2018
 * The interface TransactionsService
 */
public interface TransactionsService {
	
	boolean createTransaction(Transaction transaction);

}
