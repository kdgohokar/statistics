package com.kdgohokar.statistics.service.impl;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdgohokar.statistics.repository.TransactionRepository;
import com.kdgohokar.statistics.service.StatisticsService;
import com.kdgohokar.statistics.vo.Statistics;
import com.kdgohokar.statistics.vo.Transaction;

/**
 * @author kirangohokar on 12-07-2018
 * Implementation class for StatisticsService
 */

@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Autowired
	private TransactionRepository transactionRepo;
	
	@Override
	public Statistics getStats() {
		List<Transaction> transactions = transactionRepo.findAll();
		DoubleSummaryStatistics statistics = transactions.stream().mapToDouble(Transaction::getAmount).summaryStatistics();
		return Statistics.builder()
		.sum(statistics.getSum())
		.avg(statistics.getAverage())
		.max(statistics.getMax())
		.min(statistics.getMin())
		.count(statistics.getCount())
		.build();
	}

}
