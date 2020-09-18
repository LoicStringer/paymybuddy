package com.paymybuddy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TAX")
public class Tax {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="TAX_ID")
	private int taxId;
	
	@Column(name="TAX_RATE")
	private float taxRate;
	
	@Column(name="TAX_DESCRIPTION")
	private String taxDescription;
	
	
}
