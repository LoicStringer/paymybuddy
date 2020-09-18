package com.paymybuddy.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TRANSACTION")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANSACTION_ID")
	private long transactionId;
	
	@Column(name="TRANSACTION_DATE")
	private Instant transactionDate;
	
	@Column(name="TRANSACTION_FEE")
	private double transactionFee;
	
	@OneToOne
	@JoinColumn(name="TRANSACTION_TAX_ID",referencedColumnName = "TAX_ID")
	private Tax transactionTax;
	
	
	
	
	
	
	
	
}
