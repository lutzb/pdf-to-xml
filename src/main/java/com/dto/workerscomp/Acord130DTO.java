package com.dto.workerscomp;

import java.util.List;

public class Acord130DTO {

	private String completionDate;
	private Producer producer;
	private CommercialBusiness namedInsured;
	private SubmissionStatus statusOfSubmission;
	private BillingAuditInfo billingAuditInfo;
	private List<Location> locations;
	private Policy policy;
	private List<ContactInfo> contactInfo;
	private List<IncludedExcluded> includedExcluded;
	private List<StateRatingInfo> stateRatingInfo;
	private Premium premium;
	private String remarkText;

	public String getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public CommercialBusiness getNamedInsured() {
		return namedInsured;
	}

	public void setNamedInsured(CommercialBusiness namedInsured) {
		this.namedInsured = namedInsured;
	}
	
	public SubmissionStatus getStatusOfSubmission() {
		return statusOfSubmission;
	}

	public void setStatusOfSubmission(SubmissionStatus statusOfSubmission) {
		this.statusOfSubmission = statusOfSubmission;
	}

	public BillingAuditInfo getBillingAuditInfo() {
		return billingAuditInfo;
	}

	public void setBillingAuditInfo(BillingAuditInfo billingAuditInfo) {
		this.billingAuditInfo = billingAuditInfo;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	
	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public List<ContactInfo> getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(List<ContactInfo> contactInfo) {
		this.contactInfo = contactInfo;
	}

	public List<IncludedExcluded> getIncludedExcluded() {
		return includedExcluded;
	}

	public void setIncludedExcluded(List<IncludedExcluded> includedExcluded) {
		this.includedExcluded = includedExcluded;
	}

	public List<StateRatingInfo> getStateRatingInfo() {
		return stateRatingInfo;
	}

	public void setStateRatingInfo(List<StateRatingInfo> stateRatingInfo) {
		this.stateRatingInfo = stateRatingInfo;
	}

	public Premium getPremium() {
		return premium;
	}

	public void setPremium(Premium premium) {
		this.premium = premium;
	}

	public String getRemarkText() {
		return remarkText;
	}

	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}

}
