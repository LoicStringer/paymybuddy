package com.paymybuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.dao.TaxDAO;
import com.paymybuddy.entity.Tax;

@ExtendWith(MockitoExtension.class)
class TaxServiceTest {
	
	@InjectMocks
	private TaxService taxService;
	
	@Mock
	private TaxDAO taxDao;

	private static Tax hugeTax;
	
	@BeforeAll
	static void setUp() {
		
		hugeTax = new Tax (1,0.5,"Huge tax");
	}
	
	@Test
	void getTaxTest() {
		
		when(taxDao.findById(1)).thenReturn(Optional.of(hugeTax));
		
		assertEquals(taxService.getTax(1),hugeTax);
	}
	
	@Test
	void addTaxTest() {
		
		when(taxDao.save(any(Tax.class))).thenReturn(hugeTax);
		
		assertEquals(taxService.addTaxToDb(hugeTax).getTaxDescription(),"Huge tax");
	}

}
