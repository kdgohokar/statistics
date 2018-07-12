package com.kdgohokar.statistics.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kdgohokar.statistics.service.TransactionsService;

@RunWith(SpringRunner.class)
public class TransactionsControllerTest {
	
	@InjectMocks
	private TransactionsController transactionController;
	
	@Mock
	private TransactionsService service;
	
	private MockMvc mockMvc;

	@Before
	public void setupTest() {
	    MockitoAnnotations.initMocks(this);
	    mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
	}
	
	@Test
	public void testHandleTransacton_shouldReturnCreatedForSuccess() throws Exception {
		
		final String request = "{\n" + 
				"  \"amount\":100.0,\n" + 
				"  \"timestamp\": 1531424707469\n" + 
				"}";
		
		when(service.createTransaction(any())).thenReturn(true);
		
		mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON)
				.content(request))
		.andExpect(status().isCreated());
		
	}
	
	@Test
	public void testHandleTransacton_shouldReturnNoContentForInvalidParam() throws Exception {
		
		final String request = "{\n" + 
				"  \"timestamp\": 1531424707469\n" + 
				"}";
		
		mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON)
				.content(request))
		.andExpect(status().isNoContent());
		
	}
	
	@Test
	public void testHandleTransacton_shouldReturnNoContentForFailure() throws Exception {
		
		final String request = "{\n" + 
				"  \"amount\":100.50,\n" + 
				"  \"timestamp\": 1531424707469\n" + 
				"}";
		
		when(service.createTransaction(any())).thenReturn(false);
		
		mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON)
				.content(request))
		.andExpect(status().isNoContent());
		
	}

}
