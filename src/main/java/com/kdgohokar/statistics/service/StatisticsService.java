package com.kdgohokar.statistics.service;

import com.kdgohokar.statistics.vo.Statistics;

/**
 * @author kirangohokar on 12-07-2018
 * The interface StatisticsService
 */
public interface StatisticsService {
	
	/**
	 * Get transactions statistics for the last 60 seconds
	 * @return {@code Statistics}
	 */
	Statistics getStats();

}
