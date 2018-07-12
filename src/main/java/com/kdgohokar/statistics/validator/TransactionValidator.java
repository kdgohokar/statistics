package com.kdgohokar.statistics.validator;

import static com.kdgohokar.statistics.util.StatisticsUtil.TIME_LIMIT_IN_SECONDS;

import java.time.Duration;
import java.time.Instant;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kdgohokar.statistics.vo.Transaction;

/**
 * @author kirangohokar on 12-07-2018
 * Validator class for Transaction validation.
 *
 */
@Component
public class TransactionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Transaction.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Transaction transaction = (Transaction) target;
		// Validate mandatory fields.
		if(null == transaction.getAmount() && null == transaction.getTimestamp()) {
			errors.rejectValue("amount", null, "amount and timestamp both can't be null");
			errors.rejectValue("timestamp", null, "amount and timestamp both can't be null");
		}
		
		// Validate if transaction is not older than TIME_LIMIT_IN_SECONDS
		if(Duration.between(Instant.ofEpochMilli(transaction.getTimestamp()), Instant.now()).getSeconds() > TIME_LIMIT_IN_SECONDS) {
			errors.rejectValue("timestamp", null, "transaction timestamp should be from last " + TIME_LIMIT_IN_SECONDS + " seconds");
		}
		
	}

}
