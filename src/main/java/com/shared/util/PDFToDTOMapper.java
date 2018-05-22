package com.shared.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dto.workerscomp.Acord130DTO;
import com.dto.workerscomp.Address;
import com.dto.workerscomp.BillingAuditInfo;
import com.dto.workerscomp.CommercialBusiness;
import com.dto.workerscomp.ContactInfo;
import com.dto.workerscomp.IncludedExcluded;
import com.dto.workerscomp.Location;
import com.dto.workerscomp.Policy;
import com.dto.workerscomp.Producer;
import com.dto.workerscomp.SubmissionStatus;

public class PDFToDTOMapper {

	public static Acord130DTO mapDataToDTO(Document doc) {
		Map<String, String> dataMap = parseXMLDoc(doc);
		
		// From the key-value map of data, lets build a DTO that makes sense and aligns with the PDF itself.
		Acord130DTO result = new Acord130DTO();
		result.setCompletionDate(dataMap.get("Form_CompletionDate_A"));
		result.setProducer(buildProducer(dataMap));
		result.setNamedInsured(buildCommercialBusiness(dataMap));
		result.setStatusOfSubmission(buildSubmissionStatus(dataMap));
		result.setBillingAuditInfo(buildBillingAuditInfo(dataMap));
		result.setLocations(buildLocations(dataMap));
		result.setPolicy(buildPolicy(dataMap));
		result.setContactInfo(buildContactInfo(dataMap));
		result.setIncludedExcluded(buildIncludedExcludedAlphabet(dataMap));
		
		return result;
	}

	// The purpose of this method is to take the raw XML data from the PDF and return a key-value map 
	//  that is easier to work with.  To view the raw XML data, check /ExampleData/accord-130-data.xml
	private static Map<String, String> parseXMLDoc(Document doc) {
		Map<String, String> dataMap = new HashMap<String, String>();
		
		NodeList nodes = doc.getElementsByTagName("F");
		Node acordData = nodes.item(0);

		for (Node childNode = acordData.getFirstChild(); childNode != null;) {
			
			System.out.println(childNode.getNodeName() + " ===> " + childNode.getTextContent());
			dataMap.put(childNode.getNodeName(), childNode.getTextContent());
			childNode = childNode.getNextSibling();
		}
		
		return dataMap;
	}
	
	private static Producer buildProducer(Map<String, String> dataMap) {
		Producer producer = new Producer();
		producer.setFullName(dataMap.get("Producer_FullName_A"));

		Address mailingAddress = new Address();
		mailingAddress.setLineOne(dataMap.get("Producer_MailingAddress_LineOne_A"));
		mailingAddress.setCity(dataMap.get("Producer_MailingAddress_CityName_A"));
		mailingAddress.setStateOrProvinceCode(dataMap.get("Producer_MailingAddress_StateOrProvinceCode_A"));
		mailingAddress.setPostalCode(dataMap.get("Producer_MailingAddress_PostalCode_A"));
		producer.setMailingAddress(mailingAddress);
		
		producer.setFullName(dataMap.get("Producer_ContactPerson_FullName_A"));
		producer.setOfficePhone(dataMap.get("Producer_ContactPerson_PhoneNumber_A"));
		producer.setMobilePhone(dataMap.get("Producer_ContactPerson_CellPhoneNumber_A"));
		producer.setFaxNumber(dataMap.get("Producer_FaxNumber_A"));
		producer.setEmailAddress(dataMap.get("Producer_ContactPerson_EmailAddress_A"));
		producer.setProducerCode(dataMap.get("Insurer_ProducerIdentifier_A"));
		producer.setProducerSubCode(dataMap.get("Insurer_SubProducerIdentifier_A"));
		producer.setAgencyCustomerID(dataMap.get("Producer_CustomerIdentifier_A"));
		
		return producer;
	}

	private static CommercialBusiness buildCommercialBusiness(Map<String, String> dataMap) {
		CommercialBusiness commercialBusiness = new CommercialBusiness();
		commercialBusiness.setCompanyName(dataMap.get("Insurer_FullName_A"));
		commercialBusiness.setUnderwriter(dataMap.get("Insurer_Underwriter_FullName_A"));
		commercialBusiness.setApplicantName(dataMap.get("NamedInsured_FullName_A"));
		commercialBusiness.setOfficePhone(dataMap.get("NamedInsured_Primary_PhoneNumber_A"));
		commercialBusiness.setMobilePhone(dataMap.get("NamedInsured_Secondary_PhoneNumber_A"));
		
		Address mailingAddress = new Address();
		mailingAddress.setLineOne(dataMap.get("NamedInsured_MailingAddress_LineOne_A"));
		mailingAddress.setLineTwo(dataMap.get("NamedInsured_MailingAddress_LineTwo_A"));
		mailingAddress.setCity(dataMap.get("NamedInsured_MailingAddress_CityName_A"));
		mailingAddress.setStateOrProvinceCode(dataMap.get("NamedInsured_MailingAddress_StateOrProvinceCode_A"));
		mailingAddress.setPostalCode(dataMap.get("NamedInsured_MailingAddress_PostalCode_A"));
		commercialBusiness.setMailingAddress(mailingAddress);
		
		commercialBusiness.setYrsInBusiness(DataHelper.parseInt(dataMap.get("NamedInsured_InBusinessYearCount_A")));
		commercialBusiness.setSicCode(dataMap.get("NamedInsured_SICCode_A"));
		commercialBusiness.setNaicsCode(dataMap.get("NamedInsured_NAICSCode_A"));
		commercialBusiness.setWebsiteAddress(dataMap.get("NamedInsured_Primary_WebsiteAddress_A"));
		commercialBusiness.setEmailAddress(dataMap.get("NamedInsured_Primary_EmailAddress_A"));
		
		commercialBusiness.setSoleProprietor(DataHelper.checkbox.get(dataMap.get("NamedInsured_LegalEntity_SoleProprietorIndicator_A")));
		commercialBusiness.setPartnership(DataHelper.checkbox.get(dataMap.get("NamedInsured_LegalEntity_PartnershipIndicator_A")));
		commercialBusiness.setCorporation(DataHelper.checkbox.get(dataMap.get("NamedInsured_LegalEntity_CorporationIndicator_A")));
		commercialBusiness.setSubchapterSCorp(DataHelper.checkbox.get(dataMap.get("NamedInsured_LegalEntity_SubchapterSCorporationIndicator_A")));
		commercialBusiness.setLimitedLiabilityCorp(DataHelper.checkbox.get(dataMap.get("NamedInsured_LegalEntity_LimitedLiabilityCorporationIndicator_A")));
		commercialBusiness.setJointVenture(DataHelper.checkbox.get(dataMap.get("NamedInsured_LegalEntity_JointVentureIndicator_A")));
		commercialBusiness.setTrust(DataHelper.checkbox.get(dataMap.get("NamedInsured_LegalEntity_TrustIndicator_A")));
		commercialBusiness.setOther(DataHelper.checkbox.get(dataMap.get("NamedInsured_LegalEntity_OtherIndicator_A")));
		commercialBusiness.setOtherDesc(dataMap.get("NamedInsured_LegalEntity_OtherDescription_A"));
		
		commercialBusiness.setCreditBureauName(dataMap.get("NamedInsured_CreditBureauName_A"));
		commercialBusiness.setCreditBureauID(dataMap.get("NamedInsured_CreditBureauIdentifier_A"));
		commercialBusiness.setTaxIdentifier(DataHelper.parseInt(dataMap.get("NamedInsured_TaxIdentifier_A")));
		commercialBusiness.setNcciRiskID(dataMap.get("NamedInsured_NCCIRiskIdentifier_A"));
		commercialBusiness.setRatingBureauID(DataHelper.parseInt(dataMap.get("NamedInsured_RatingBureauIdentifier_A")));
		
		return commercialBusiness;
	}
	
	private static SubmissionStatus buildSubmissionStatus(Map<String, String> dataMap) {
		SubmissionStatus status = new SubmissionStatus();
		status.setQuote(DataHelper.checkbox.get(dataMap.get("Policy_Status_QuoteIndicator_A")));
		status.setBound(DataHelper.checkbox.get(dataMap.get("Policy_Status_BoundIndicator_A")));
		status.setAssignedRisk(DataHelper.checkbox.get(dataMap.get("Policy_Status_AssignedRiskIndicator_A")));
		status.setIssuePolicy(DataHelper.checkbox.get(dataMap.get("Policy_Status_IssueIndicator_A")));
		return status;
	}
	
	private static BillingAuditInfo buildBillingAuditInfo(Map<String, String> dataMap) {
		BillingAuditInfo billingAuditInfo = new BillingAuditInfo();
		billingAuditInfo.setAgencyBill(DataHelper.checkbox.get(dataMap.get("Policy_Payment_ProducerBillIndicator_A")));
		billingAuditInfo.setDirectBill(DataHelper.checkbox.get(dataMap.get("Policy_Payment_DirectBillIndicator_A")));
		billingAuditInfo.setPaymentAnnual(DataHelper.checkbox.get(dataMap.get("Policy_Payment_AnnualIndicator_A")));
		billingAuditInfo.setPaymentSemiAnnual(DataHelper.checkbox.get(dataMap.get("Policy_Payment_SemiAnnualIndicator_A")));
		billingAuditInfo.setPaymentQuarterly(DataHelper.checkbox.get(dataMap.get("Policy_Payment_QuarterlyIndicator_A")));
		billingAuditInfo.setAuditAtExpiration(DataHelper.checkbox.get(dataMap.get("Policy_Audit_AtExpirationIndicator_A")));
		billingAuditInfo.setAuditSemiAnnual(DataHelper.checkbox.get(dataMap.get("Policy_Audit_SemiAnnualIndicator_A")));
		billingAuditInfo.setAuditQuarterly(DataHelper.checkbox.get(dataMap.get("Policy_Audit_QuarterlyIndicator_A")));
		billingAuditInfo.setAuditMonthly(DataHelper.checkbox.get(dataMap.get("Policy_Audit_MonthlyIndicator_A")));
		return billingAuditInfo;
	}

	private static List<Location> buildLocations(Map<String, String> dataMap) {
		List<Location> locations = new ArrayList<Location>();
		List<String> rowIdentifiers = new ArrayList<String>();
		rowIdentifiers.add("A");
		rowIdentifiers.add("B");
		rowIdentifiers.add("C");
		
		for (String rowIdentifier : rowIdentifiers) {
			Location location = new Location();
			location.setLocationNumber(DataHelper.parseInt(dataMap.get("Location_ProducerIdentifier_" + rowIdentifier)));
			location.setHighestFloor(dataMap.get("Location_HighestFloorCount_" + rowIdentifier));
			
			Address locationAddress = new Address();
			locationAddress.setLineOne(dataMap.get("Location_PhysicalAddress_LineOne_" + rowIdentifier));
			locationAddress.setCity(dataMap.get("Location_PhysicalAddress_CityName_" + rowIdentifier));
			locationAddress.setStateOrProvinceCode(dataMap.get("Location_PhysicalAddress_StateOrProvinceCode_" + rowIdentifier));
			locationAddress.setPostalCode(dataMap.get("Location_PhysicalAddress_PostalCode_" + rowIdentifier));
			
			locations.add(location);
		}

		return locations;
	}

	private static Policy buildPolicy(Map<String, String> dataMap) {
		Policy policy = new Policy();

		policy.setPropEffectiveDate(dataMap.get("Policy_EffectiveDate_A"));
		policy.setPropExpirationDate(dataMap.get("Policy_ExpirationDate_A"));
		policy.setAnniversaryRatingDate(dataMap.get("Policy_NormalAnniversaryRatingDate_A"));
		policy.setParticipating(DataHelper.checkbox.get(dataMap.get("Policy_ParticipatingIndicator_A")));
		policy.setNonParticipating(DataHelper.checkbox.get(dataMap.get("Policy_NonParticipatingIndicator_A")));
		policy.setRetroPlan(dataMap.get("Policy_RetrospectiveRatingPlan_A"));
		policy.setWorkersCompStates(dataMap.get("WorkersCompensation_PartOne_StateOrProvinceCode_A"));
		policy.setEmployerLiabilityEachAccident(dataMap.get("WorkersCompensationEmployersLiability_EmployersLiability_EachAccidentLimitAmount_A"));
		policy.setEmployerLiabilityDiseasePolicy(dataMap.get("WorkersCompensationEmployersLiability_EmployersLiability_DiseasePolicyLimitAmount_A"));
		policy.setEmployerLiabilityDiseaseEachEmployee(dataMap.get("WorkersCompensationEmployersLiability_EmployersLiability_DiseaseEachEmployeeLimitAmount_A"));
		policy.setOtherStatesIns(dataMap.get("WorkersCompensation_PartThree_StateOrProvinceCode_A"));
		policy.setDeductiblesMedical(DataHelper.checkbox.get(dataMap.get("WorkersCompensation_DeductibleType_MedicalIndicator_A")));
		policy.setDeductiblesIndemnity(DataHelper.checkbox.get(dataMap.get("WorkersCompensation_DeductibleType_IndemnityIndicator_A")));
		policy.setDeductibleAmount(DataHelper.parseInt(dataMap.get("WorkersCompensation_DeductibleAmount_A")));
		policy.setOtherCoveragesUSLH(DataHelper.checkbox.get(dataMap.get("WorkersCompensation_Coverage_USLHIndicator_A")));
		policy.setOtherCoveragesVoluntaryComp(DataHelper.checkbox.get(dataMap.get("WorkersCompensation_Coverage_VoluntaryCompensationIndicator_A")));
		policy.setOtherCoveragesForeignCov(DataHelper.checkbox.get(dataMap.get("WorkersCompensation_Coverage_ForeignCoverageIndicator_A")));
		policy.setOtherCoveragesManagedCare(DataHelper.checkbox.get(dataMap.get("WorkersCompensation_Coverage_ManagedCareOptionIndicator_A")));
		policy.setTotalEstimatedAnnualPremium(DataHelper.parseInt(dataMap.get("WorkersCompensationLineOfBusiness_TotalEstimatedAnnualPremiumAllStatesAmount_A")));
		policy.setTotalMinimumPremium(DataHelper.parseInt(dataMap.get("WorkersCompensationLineOfBusiness_TotalMinimumPremiumAllStatesAmount_A")));
		policy.setTotalDepositPremium(DataHelper.parseInt(dataMap.get("WorkersCompensationLineOfBusiness_TotalDepositPremiumAllStatesAmount_A")));
		
		return policy;
	}
	
	private static List<ContactInfo> buildContactInfo(Map<String, String> dataMap) {
		List<ContactInfo> contactInfoList = new ArrayList<ContactInfo>();
		
		ContactInfo inspection = new ContactInfo();
		inspection.setType("Inspection");
		inspection.setName(dataMap.get("NamedInsured_InspectionContact_FullName_A"));
		inspection.setOfficePhone(dataMap.get("NamedInsured_InspectionContact_PhoneNumber_A"));
		inspection.setMobilePhone(dataMap.get("NamedInsured_InspectionContact_CellPhoneNumber_A"));
		inspection.setEmail(dataMap.get("NamedInsured_InspectionContact_EmailAddress_A"));
		
		ContactInfo accounting = new ContactInfo();
		accounting.setType("Accounting");
		accounting.setName(dataMap.get("NamedInsured_AccountingContact_FullName_A"));
		accounting.setOfficePhone(dataMap.get("NamedInsured_AccountingContact_PhoneNumber_A"));
		accounting.setMobilePhone(dataMap.get("NamedInsured_AccountingContact_CellPhoneNumber_A"));
		accounting.setEmail(dataMap.get("NamedInsured_AccountingContact_EmailAddress_A"));
		
		ContactInfo claims = new ContactInfo();
		claims.setType("Claims");
		claims.setName(dataMap.get("NamedInsured_ClaimContact_FullName_A"));
		claims.setOfficePhone(dataMap.get("NamedInsured_ClaimContact_PhoneNumber_A"));
		claims.setMobilePhone(dataMap.get("NamedInsured_ClaimContact_CellPhoneNumber_A"));
		claims.setEmail(dataMap.get("NamedInsured_ClaimContact_EmailAddress_A"));
		
		contactInfoList.add(inspection);
		contactInfoList.add(accounting);
		contactInfoList.add(claims);
		return contactInfoList;
	}

	private static List<IncludedExcluded> buildIncludedExcludedAlphabet(Map<String, String> dataMap) {
		List<IncludedExcluded> includedExcluded = new ArrayList<IncludedExcluded>();
		List<String> rowIdentifiers = new ArrayList<String>();
		rowIdentifiers.add("A");
		rowIdentifiers.add("B");
		rowIdentifiers.add("C");
		rowIdentifiers.add("D");
		
		for (String rowIdentifier : rowIdentifiers) {
			IncludedExcluded individual = new IncludedExcluded();
			individual.setState(dataMap.get("WorkersCompensation_Individual_StateOrProvinceCode_" + rowIdentifier));
			individual.setLocationNumber(DataHelper.parseInt(dataMap.get("WorkersCompensation_Individual_LocationProducerIdentifier_" + rowIdentifier)));
			individual.setName(dataMap.get("WorkersCompensation_Individual_FullName_" + rowIdentifier));
			individual.setDateOfBirth(dataMap.get("WorkersCompensation_Individual_BirthDate_" + rowIdentifier));
			individual.setTitle(dataMap.get("WorkersCompensation_Individual_TitleRelationshipCode_" + rowIdentifier));
			individual.setOwnershipPercent(DataHelper.parseInt(dataMap.get("WorkersCompensation_Individual_OwnershipPercent_" + rowIdentifier)));
			individual.setDuties(dataMap.get("WorkersCompensation_Individual_DutiesDescription_" + rowIdentifier));
			individual.setIncludeExclude(dataMap.get("WorkersCompensation_Individual_IncludedExcludedCode_" + rowIdentifier));
			individual.setClassCode(dataMap.get("WorkersCompensation_Individual_RatingClassificationCode_" + rowIdentifier));
			individual.setRemunerationPayroll(dataMap.get("WorkersCompensation_Individual_RemunerationAmount_" + rowIdentifier));
			includedExcluded.add(individual);
		}

		return includedExcluded;
	}
}
