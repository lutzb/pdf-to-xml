package com.dto.workerscomp;

public class SubmissionStatus {
	
	private boolean quote;
	private boolean bound;
	private boolean assignedRisk;
	private boolean issuePolicy;

	public boolean isQuote() {
		return quote;
	}

	public void setQuote(boolean quote) {
		this.quote = quote;
	}

	public boolean isBound() {
		return bound;
	}

	public void setBound(boolean bound) {
		this.bound = bound;
	}

	public boolean isAssignedRisk() {
		return assignedRisk;
	}

	public void setAssignedRisk(boolean assignedRisk) {
		this.assignedRisk = assignedRisk;
	}

	public boolean isIssuePolicy() {
		return issuePolicy;
	}

	public void setIssuePolicy(boolean issuePolicy) {
		this.issuePolicy = issuePolicy;
	}

}
