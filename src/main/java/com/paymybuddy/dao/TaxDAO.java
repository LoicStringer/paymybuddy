package com.paymybuddy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.entity.Tax;

public interface TaxDAO extends JpaRepository<Tax, Integer>{

	
	
}
