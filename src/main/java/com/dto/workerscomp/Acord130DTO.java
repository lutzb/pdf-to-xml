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
	private List<IncludeExclude> includedExcluded;

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

	public List<IncludeExclude> getIncludedExcluded() {
		return includedExcluded;
	}

	public void setIncludedExcluded(List<IncludeExclude> includedExcluded) {
		this.includedExcluded = includedExcluded;
	}

}
