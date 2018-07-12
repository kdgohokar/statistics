package com.kdgohokar.statistics.repository.impl;

import static com.kdgohokar.statistics.util.StatisticsConstants.TIME_LIMIT_IN_SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.kdgohokar.statistics.vo.Transaction;


@RunWith(SpringRunner.class)
public class TransactionRepositoryImplTest {

	@InjectMocks
	private TransactionRepositoryImpl transactionRepositoryImpl;
	
	@Test
	public void testAdd_shouldAddTransaction() throws Exception {
		Transaction transaction = Transaction.builder()
				.amount(100.0)
				.timestamp(Instant.now().toEpochMilli())
				.build();
		assertTrue(transactionRepositoryImpl.add(transaction));
		assertEquals(1, transactionRepositoryImpl.findAll().size());
	}
	
	@Test
	public void testAdd_shouldNotReturnOlderTransaction() throws Exception {
		Transaction transaction = Transaction.builder()
				.amount(100.0)
				.timestamp(Instant.now().minusSeconds(TIME_LIMIT_IN_SECONDS+1).toEpochMilli())
				.build();
		assertTrue(transactionRepositoryImpl.add(transaction));
		assertEquals(0, transactionRepositoryImpl.findAll().size());
	}

}
