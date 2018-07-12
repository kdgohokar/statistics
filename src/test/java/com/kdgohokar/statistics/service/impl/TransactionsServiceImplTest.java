package com.kdgohokar.statistics.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.kdgohokar.statistics.repository.TransactionRepository;
import com.kdgohokar.statistics.vo.Transaction;

@RunWith(SpringRunner.class)
public class TransactionsServiceImplTest {

	@InjectMocks
	private TransactionsServiceImpl transactionService;
	
	@Mock
	private TransactionRepository transactionRepo;
	
	@Test
	public void testCreateTransaction_assertTrueWhenSuccess() throws Exception {
		Transaction transaction = Transaction
				.builder().amount(10.0)
				.timestamp(Instant.now().toEpochMilli())
				.build();
		
		when(transactionRepo.add(any())).thenReturn(true);
		
		assertTrue(transactionService.createTransaction(transaction));
	}
	
	@Test
	public void testCreateTransaction_assertFalseWhenFailure() throws Exception {
		Transaction transaction = Transaction
				.builder().amount(10.0)
				.timestamp(Instant.now().toEpochMilli())
				.build();
		
		when(transactionRepo.add(any())).thenReturn(false);
		
		assertFalse(transactionService.createTransaction(transaction));
	}
	
}
