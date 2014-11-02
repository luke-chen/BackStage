package com.luke.model.status;

public class SuccessfulActionStatus extends ActionStatus {

	public SuccessfulActionStatus() {
		super("success", "");
	}
	
	public SuccessfulActionStatus(String description) {
		super("success", description);
	}
}
