/**
 * 
 */
package com.kdgohokar.statistics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdgohokar.statistics.vo.Statistics;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kirangohokar on 12-07-2018
 * The Statistics controller
 */

@RestController
@RequestMapping("/statistics")
@Slf4j
public class StatisticsController {

	@GetMapping
	public Statistics handleStatistics() {
		log.info("Got the request for statistics");
		return Statistics.builder()
				.sum(0.0)
				.avg(0.0)
				.max(0.0)
				.min(0.0)
				.count(1L)
				.build();
	}

}
