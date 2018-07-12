/**
 * 
 */
package com.kdgohokar.statistics.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author kirangohokar on 12-07-2018
 * Statistics VO
 */

@Data
@Builder
@AllArgsConstructor
public class Statistics {

	private Double sum;
	private Double avg;
	private Double max;
	private Double min;
	private Long count;
	
}
