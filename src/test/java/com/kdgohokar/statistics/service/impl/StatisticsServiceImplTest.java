package com.kdgohokar.statistics.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.kdgohokar.statistics.repository.TransactionRepository;
import com.kdgohokar.statistics.vo.Statistics;
import com.kdgohokar.statistics.vo.Transaction;

@RunWith(SpringRunner.class)
public class StatisticsServiceImplTest {
	
	@InjectMocks
	private StatisticsServiceImpl statisticsService;
	
	@Mock
	private TransactionRepository transactionRepo;

	@Test
	public void testGetStats_shouldReturnStats() throws Exception {
		List<Transaction> transactions = Arrays.asList(Transaction.builder()
				.amount(500.0)
				.timestamp(1531428467583L)
				.build());
		
		when(transactionRepo.findAll()).thenReturn(transactions);
		Statistics statistics = statisticsService.getStats();
		
		assertEquals((Double) 500.0, statistics.getMax());
		assertEquals((Double) 500.0, statistics.getMin());
		assertEquals((Double) 500.0, statistics.getAvg());
		assertEquals((Double) 500.0, statistics.getSum());
		assertEquals((Long) 1L, statistics.getCount());
		
	}
	
	@Test
	public void testGetStats_shouldNotReturnStats() throws Exception {
		
		Statistics statistics = statisticsService.getStats();
		
		assertEquals((Double) Double.NEGATIVE_INFINITY, statistics.getMax());
		assertEquals((Double) Double.POSITIVE_INFINITY, statistics.getMin());
		assertEquals((Double) 0.0, statistics.getAvg());
		assertEquals((Double) 0.0, statistics.getSum());
		assertEquals((Long) 0L, statistics.getCount());
		
	}

}
