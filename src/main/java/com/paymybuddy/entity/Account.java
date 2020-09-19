package com.paymybuddy.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ACCOUNT")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_ID")
	private long accountId;

	@Column(name="ACCOUNT_USER_NAME",length=20,nullable=false)
	private String accountUserName;
	
	@Column(name="ACCOUNT_USER_EMAIL",length=50,nullable=false,unique=true)
	private String accountUserEmail;
	
	@Column(name="ACCOUNT_USER_PASSWORD",length=20,nullable=false)
	private String accountUserPassword;
	
	@Column(name = "ACCOUNT_BALANCE",columnDefinition="double default 0")
	private double balance;

	
	@OneToMany(mappedBy="accountFrom")
	private List<Transfer> transfersFrom;
	
	@OneToMany(mappedBy="accountTo")
	private List<Transfer> transfersTo;
	
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}


}
