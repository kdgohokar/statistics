package com.kdgohokar.statistics.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kdgohokar.statistics.service.StatisticsService;
import com.kdgohokar.statistics.vo.Statistics;

@RunWith(SpringRunner.class)
public class StatisticsControllerTest {

	@InjectMocks
	private StatisticsController statsController;

	@Mock
	private StatisticsService service;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(statsController).build();
	}

	@Test
	public void testHandleStatistics_shouldReturnStats() throws Exception {
		
		final String jsonResponse = "{\n" + 
				"    \"sum\": 10.0,\n" + 
				"    \"avg\": 10.0,\n" + 
				"    \"max\": 10.0,\n" + 
				"    \"min\": 10.0,\n" + 
				"    \"count\": 1\n" + 
				"}";
		
		Statistics statistics = Statistics.builder()
				.sum(10.0)
				.avg(10.0)
				.max(10.0)
				.min(10.0)
				.count(1L)
				.build();
		
		when(service.getStats()).thenReturn(statistics);
		
		mockMvc.perform(get("/statistics"))
		.andExpect(status().isOk())
		.andExpect(content().json(jsonResponse));
	}
	
	@Test
	public void testHandleStatistics_shouldNotReturnStats() throws Exception {
		
		final String jsonResponse = "{\n" + 
				"  \"sum\": 0.0,\n" + 
				"  \"avg\": 0.0,\n" + 
				"  \"max\": \"-Infinity\",\n" + 
				"  \"min\": \"Infinity\",\n" + 
				"  \"count\": 0\n" + 
				"}";
		
		Statistics statistics = Statistics.builder()
				.sum(0.0)
				.avg(0.0)
				.max(Double.NEGATIVE_INFINITY)
				.min(Double.POSITIVE_INFINITY)
				.count(0L)
				.build();
		
		when(service.getStats()).thenReturn(statistics);
		
		mockMvc.perform(get("/statistics"))
		.andExpect(status().isOk())
		.andExpect(content().json(jsonResponse));
	}

}
