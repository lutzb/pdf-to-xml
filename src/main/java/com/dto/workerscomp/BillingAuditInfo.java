package com.dto.workerscomp;

public class BillingAuditInfo {
	
	private boolean agencyBill;
	private boolean directBill;
	private boolean paymentAnnual;
	private boolean paymentSemiAnnual;
	private boolean paymentQuarterly;
	private boolean auditAtExpiration;
	private boolean auditSemiAnnual;
	private boolean auditQuarterly;
	private boolean auditMonthly;
	
	public boolean isAgencyBill() {
		return agencyBill;
	}

	public void setAgencyBill(boolean agencyBill) {
		this.agencyBill = agencyBill;
	}

	public boolean isDirectBill() {
		return directBill;
	}

	public void setDirectBill(boolean directBill) {
		this.directBill = directBill;
	}

	public boolean isPaymentAnnual() {
		return paymentAnnual;
	}

	public void setPaymentAnnual(boolean paymentAnnual) {
		this.paymentAnnual = paymentAnnual;
	}

	public boolean isPaymentSemiAnnual() {
		return paymentSemiAnnual;
	}

	public void setPaymentSemiAnnual(boolean paymentSemiAnnual) {
		this.paymentSemiAnnual = paymentSemiAnnual;
	}

	public boolean isPaymentQuarterly() {
		return paymentQuarterly;
	}

	public void setPaymentQuarterly(boolean paymentQuarterly) {
		this.paymentQuarterly = paymentQuarterly;
	}

	public boolean isAuditAtExpiration() {
		return auditAtExpiration;
	}

	public void setAuditAtExpiration(boolean auditAtExpiration) {
		this.auditAtExpiration = auditAtExpiration;
	}

	public boolean isAuditSemiAnnual() {
		return auditSemiAnnual;
	}

	public void setAuditSemiAnnual(boolean auditSemiAnnual) {
		this.auditSemiAnnual = auditSemiAnnual;
	}

	public boolean isAuditQuarterly() {
		return auditQuarterly;
	}

	public void setAuditQuarterly(boolean auditQuarterly) {
		this.auditQuarterly = auditQuarterly;
	}

	public boolean isAuditMonthly() {
		return auditMonthly;
	}

	public void setAuditMonthly(boolean auditMonthly) {
		this.auditMonthly = auditMonthly;
	}

}
