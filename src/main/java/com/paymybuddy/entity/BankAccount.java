package com.paymybuddy.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name = "BANK_ACCOUNT")
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BANK_ACCOUNT_ID")
	private long bankAccountId;

	@NaturalId
	@NotNull
	@Column(name = "BANK_ACCOUNT_IBAN", length = 35, unique = true, nullable = false)
	private String bankAccountIban;

	@NotNull
	@Column(name = "BANK_ACCOUNT_HOLDER_NAME", length = 30, nullable = false)
	private String bankAccountHolderName;

	@Column(name = "BANK_ACCOUNT_DESCRIPTION", length = 20)
	private String bankAccountDescription;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn(name = "ACCOUNT_HOLDER_ID")
	private Account holderAccount;

	@JsonIgnore
	@OneToMany(mappedBy = "bankAccount", orphanRemoval = true,cascade = CascadeType.ALL)
	private List<Providing> providingsToAccount = new ArrayList<Providing>();

	public BankAccount() {
	}
	
	public void addProviding(Providing providing) {
		this.providingsToAccount.add(providing);
		providing.setBankAccount(this);
	}

	public long getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(long bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public String getBankAccountIban() {
		return bankAccountIban;
	}

	public void setBankAccountIban(String bankAccountIban) {
		this.bankAccountIban = bankAccountIban;
	}

	public String getBankAccountHolderName() {
		return bankAccountHolderName;
	}

	public void setBankAccountHolderName(String bankAccountHolderName) {
		this.bankAccountHolderName = bankAccountHolderName;
	}

	public String getBankAccountDescription() {
		return bankAccountDescription;
	}

	public void setBankAccountDescription(String bankAccountDescription) {
		this.bankAccountDescription = bankAccountDescription;
	}

	public Account getHolderAccount() {
		return holderAccount;
	}

	public void setHolderAccount(Account holderAccount) {
		this.holderAccount = holderAccount;
	}

	public List<Providing> getProvidingsToAccount() {
		return providingsToAccount;
	}

	public void setProvidingsToAccount(List<Providing> providingsToAccount) {
		this.providingsToAccount = providingsToAccount;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(bankAccountIban);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		return Objects.equals(bankAccountIban, other.getBankAccountIban());
	}

	@Override
	public String toString() {
		return "BankAccount [bankAccountId=" + bankAccountId + ", bankAccountIban=" + bankAccountIban
				+ ", bankAccountHolderName=" + bankAccountHolderName + ", bankAccountDescription="
				+ bankAccountDescription + ", holderAccount=" + holderAccount.getAccountId() + ", providingsToAccount="
				+ providingsToAccount + "]";
	}

}
