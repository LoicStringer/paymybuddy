package com.paymybuddy.model;

import org.springframework.stereotype.Component;

import com.paymybuddy.entity.Operation;
import com.paymybuddy.entity.Providing;

@Component
public class ProvidingOperation {
	
	private Providing providing;
	private Operation operation;
	
	public ProvidingOperation() {
	}

	public ProvidingOperation(Providing providing, Operation operation) {
		super();
		this.providing = providing;
		this.operation = operation;
	}

	public Providing getProviding() {
		return providing;
	}

	public void setProviding(Providing providing) {
		this.providing = providing;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((operation == null) ? 0 : operation.hashCode());
		result = prime * result + ((providing == null) ? 0 : providing.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProvidingOperation other = (ProvidingOperation) obj;
		if (operation == null) {
			if (other.operation != null)
				return false;
		} else if (!operation.equals(other.operation))
			return false;
		if (providing == null) {
			if (other.providing != null)
				return false;
		} else if (!providing.equals(other.providing))
			return false;
		return true;
	}
	
	
	

}
