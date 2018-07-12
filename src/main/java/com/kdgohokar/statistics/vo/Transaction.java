package com.kdgohokar.statistics.vo;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kirangohokar on 12-07-2018
 * Transaction VO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
	
	@NotNull
	private Double amount;
	
	@NotNull
	private Long timestamp;

}
