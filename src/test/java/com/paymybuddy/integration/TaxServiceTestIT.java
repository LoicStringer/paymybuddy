package com.paymybuddy.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.entity.Tax;
import com.paymybuddy.exception.ResourceNotFoundException;
import com.paymybuddy.service.TaxService;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class TaxServiceTestIT {

	@Autowired 
	private TaxService taxService;
	
	private static Tax tax;
	
	@BeforeAll
	static void setUp() {
		
		tax = new Tax(2, 0.5, "Huge tax");
	}
	
	@Test
	void addTaxTest() {
		
		assertEquals(taxService.addTaxToDb(tax).getTaxDescription(),"Huge tax");
	}

	@Test
	void getTaxTest() throws ResourceNotFoundException {
		
		assertEquals(taxService.getTax(1).getTaxDescription(),"Transfer tax");
	}
	
	@Test
	void isResourceNotFoundExceptionThrownWhenTryingToGetUnknownTaxTest() {
		
		Exception exception = assertThrows(ResourceNotFoundException.class, 
				()-> taxService.getTax(10));
		
		assertEquals(exception.getMessage(),"Unknown tax.");
	}
}
