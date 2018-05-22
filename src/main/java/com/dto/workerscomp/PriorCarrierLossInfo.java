package com.dto.workerscomp;

public class PriorCarrierLossInfo {

	private String year;
	private String carrierName;
	private String policyNumber;
	private String annualPremium;
	private String modificationFactor;
	private int claimCount;
	private String amountPaid;
	private String reserve;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getAnnualPremium() {
		return annualPremium;
	}

	public void setAnnualPremium(String annualPremium) {
		this.annualPremium = annualPremium;
	}

	public String getModificationFactor() {
		return modificationFactor;
	}

	public void setModificationFactor(String modificationFactor) {
		this.modificationFactor = modificationFactor;
	}

	public int getClaimCount() {
		return claimCount;
	}

	public void setClaimCount(int claimCount) {
		this.claimCount = claimCount;
	}

	public String getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

}
