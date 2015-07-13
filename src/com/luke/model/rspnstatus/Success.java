package com.luke.model.rspnstatus;

public class Success extends ResponseStatus {
	public Success() {
		super(1, null, null);
	}
	
	public Success(Object data) {
		super(1, null, data);
	}
	
	public Success(String info, Object data) {
		super(1, info, data);
	}
}
