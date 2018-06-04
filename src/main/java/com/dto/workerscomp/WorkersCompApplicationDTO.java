package com.dto.workerscomp;

import java.util.List;
import java.util.Map;

public class WorkersCompApplicationDTO {

	private String completionDate;
	private Producer producer;
	private CommercialBusiness namedInsured;
	private SubmissionStatus statusOfSubmission;
	private BillingAuditInfo billingAuditInfo;
	private Map<Integer, WorkersCompLocation> locations;
	private WorkersCompPolicy policy;
	private List<ContactInfo> contactInfo;
	private List<IncludedExcluded> includedExcluded;
	private List<StateRatingInfo> stateRatingInfo;
	private Premium premium;
	private String remarkText;
	private List<PriorCarrierLossInfo> priorCarrierLossInfo;
	private String businessOperationsDescription;
	private GeneralInfoQuestions generalInfoQuestions;
	
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

	public Map<Integer, WorkersCompLocation> getLocations() {
		return locations;
	}

	public void setLocations(Map<Integer, WorkersCompLocation> locations) {
		this.locations = locations;
	}
	
	public WorkersCompPolicy getPolicy() {
		return policy;
	}

	public void setPolicy(WorkersCompPolicy policy) {
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

	public List<PriorCarrierLossInfo> getPriorCarrierLossInfo() {
		return priorCarrierLossInfo;
	}

	public void setPriorCarrierLossInfo(List<PriorCarrierLossInfo> priorCarrierLossInfo) {
		this.priorCarrierLossInfo = priorCarrierLossInfo;
	}

	public String getBusinessOperationsDescription() {
		return businessOperationsDescription;
	}

	public void setBusinessOperationsDescription(String businessOperationsDescription) {
		this.businessOperationsDescription = businessOperationsDescription;
	}

	public GeneralInfoQuestions getGeneralInfoQuestions() {
		return generalInfoQuestions;
	}

	public void setGeneralInfoQuestions(GeneralInfoQuestions generalInfoQuestions) {
		this.generalInfoQuestions = generalInfoQuestions;
	}

}
