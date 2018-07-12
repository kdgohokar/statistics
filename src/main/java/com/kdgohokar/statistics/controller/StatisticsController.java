package com.kdgohokar.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdgohokar.statistics.service.StatisticsService;
import com.kdgohokar.statistics.vo.Statistics;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kirangohokar on 12-07-2018
 * The Statistics controller
 */
@Slf4j
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

	@Autowired
	private StatisticsService service;

	@GetMapping
	public Statistics handleStatistics() {
		log.info("Got the request for statistics...");
		Statistics statistics = service.getStats();
		log.info("Returning stats : {}", statistics);
		return statistics;
	}

}
