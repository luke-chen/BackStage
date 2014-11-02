package com.luke.model.status;

public class FailedActionStatus extends ActionStatus{
	public FailedActionStatus(String description) {
		super("failed", description);
	}
}
