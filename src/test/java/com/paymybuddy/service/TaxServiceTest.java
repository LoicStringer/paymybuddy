package com.paymybuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.paymybuddy.exception.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
class TaxServiceTest {
	
	@InjectMocks
	private TaxService taxService;
	
	@Mock
	private TaxDAO taxDao;

	private static Tax hugeTax;
	
	@BeforeAll
	static void setUp() {
		
		hugeTax = new Tax ();
		hugeTax.setTaxId(1);
		hugeTax.setTaxRate(0.5);
		hugeTax.setTaxDescription("Huge tax");
	}
	
	@Test
	void getTaxTest() throws ResourceNotFoundException {
		
		when(taxDao.findById(1)).thenReturn(Optional.of(hugeTax));
		
		assertEquals(taxService.getTax(1),hugeTax);
	}
	
	@Test
	void addTaxTest() {
		
		when(taxDao.save(any(Tax.class))).thenReturn(hugeTax);
		
		assertEquals(taxService.addTaxToDb(hugeTax).getTaxDescription(),"Huge tax");
	}

	@Test
	void resourceNotFoundExceptionTest() {
		
		when(taxDao.findById(any(Integer.class))).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class,()-> taxService.getTax(any(Integer.class)));
	}
}
