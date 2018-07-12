package com.kdgohokar.statistics.repository.impl;

import static com.kdgohokar.statistics.util.StatisticsConstants.TIME_LIMIT_IN_SECONDS;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.kdgohokar.statistics.repository.TransactionRepository;
import com.kdgohokar.statistics.vo.Transaction;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kirangohokar
 * Implementation class of TransactionRepository
 */
@Slf4j
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

	private static ConcurrentNavigableMap<Long, Transaction> transactionMap = new ConcurrentSkipListMap<>();

	@Override
	public boolean add(Transaction transaction) {
		Long timestamp = transaction.getTimestamp();
		// convert timestamp to epoch seconds
		long toSeconds = Instant.ofEpochMilli(timestamp).getEpochSecond();
		transactionMap.put(toSeconds, transaction);
		log.info("Added new transaction with key = {} and value = {}", toSeconds, transaction);
		return true;
	}

	@Override
	public List<Transaction> findAll() {
		return getNewerTransactions()
				.values()
				.stream()
				.collect(Collectors.toList());
	}

	@Scheduled(fixedDelay = 5 * 1000)
	public void removeOlderTransactions() {
		log.info("Scheduled task executing now : {}", Instant.now());
		final ConcurrentNavigableMap<Long, Transaction> olderTransactions = getOldTransactions();
		if(olderTransactions.size() > 0) {
			log.info("Removing {} older entries from cache", olderTransactions.size());
			olderTransactions.clear();
		}
	}

	private ConcurrentNavigableMap<Long, Transaction> getOldTransactions() {
		return transactionMap.headMap(Instant.now().minusSeconds(TIME_LIMIT_IN_SECONDS).getEpochSecond());
	}

	private ConcurrentNavigableMap<Long, Transaction> getNewerTransactions() {
		return transactionMap.tailMap(Instant.now().minusSeconds(TIME_LIMIT_IN_SECONDS).getEpochSecond());
	}

}
