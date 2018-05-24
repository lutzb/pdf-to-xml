package com.lob.workerscomp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dto.workerscomp.WorkersCompApplicationDTO;
import com.nationwide.dto.savequote.request.AccountContact;
import com.nationwide.dto.savequote.request.Address;
import com.nationwide.dto.savequote.request.ClassCode;
import com.nationwide.dto.savequote.request.ContactPolicyRole;
import com.nationwide.dto.savequote.request.Coverages;
import com.nationwide.dto.savequote.request.CoveredEmployee;
import com.nationwide.dto.savequote.request.CreateSubmissionReqDTO;
import com.nationwide.dto.savequote.request.EffectiveDate;
import com.nationwide.dto.savequote.request.IntegerValue;
import com.nationwide.dto.savequote.request.ItemData;
import com.nationwide.dto.savequote.request.LineConditions;
import com.nationwide.dto.savequote.request.LineCoverages;
import com.nationwide.dto.savequote.request.LineExclusions;
import com.nationwide.dto.savequote.request.Lobs;
import com.nationwide.dto.savequote.request.Location;
import com.nationwide.dto.savequote.request.LossInfoDTO;
import com.nationwide.dto.savequote.request.ModifierTerm;
import com.nationwide.dto.savequote.request.Modifiers;
import com.nationwide.dto.savequote.request.Params;
import com.nationwide.dto.savequote.request.PeriodStart;
import com.nationwide.dto.savequote.request.SaveQuoteRequestDTO;
import com.nationwide.dto.savequote.request.ScheduleItems;
import com.nationwide.dto.savequote.request.Schedules;
import com.nationwide.dto.savequote.request.StateCoverages;
import com.nationwide.dto.savequote.request.StateModifiers;
import com.nationwide.dto.savequote.request.StringValue;
import com.nationwide.dto.savequote.request.Terms;
import com.nationwide.dto.savequote.request.TypeCodeValue;
import com.nationwide.dto.savequote.request.WC7AvailableCoveragesDTO;
import com.nationwide.dto.savequote.request.WC7ClassificationsDTO;
import com.nationwide.dto.savequote.request.WC7WorkersComp;

public class WorkersCompSaveQuoteRequestBuilder {
	
	public static SaveQuoteRequestDTO buildRequest(WorkersCompApplicationDTO dataDTO, String accountNumber) {
		SaveQuoteRequestDTO request = new SaveQuoteRequestDTO();

		Params params = new Params();
		params.setPeriodStart(buildPeriodStart(dataDTO));
		params.setCreateSubmissionReqDTO(buildCreateSubmissionReqDTO(dataDTO, accountNumber));
		params.setLossInfoDTO(buildLossInfoDTO(dataDTO));
		params.setWC7ClassificationsDTO(buildClassificationsDTO(dataDTO));
		params.setWC7AvailableCoveragesDTO(buildAvailableCoveragesDTO(dataDTO));
		request.setParams(Arrays.asList(params));
		
		return request;
	}

	protected static PeriodStart buildPeriodStart(WorkersCompApplicationDTO dataDTO) {
		PeriodStart periodStart = new PeriodStart();
		String[] effectDateData = dataDTO.getPolicy().getPropEffectiveDate().split("/");
		
		periodStart.setYear(Integer.parseInt(effectDateData[2]));
		periodStart.setMonth(Integer.parseInt(effectDateData[0]));
		periodStart.setDay(Integer.parseInt(effectDateData[1]));
		
		return periodStart;
	}

	protected static CreateSubmissionReqDTO buildCreateSubmissionReqDTO(WorkersCompApplicationDTO dataDTO, String accountNumber) {
		CreateSubmissionReqDTO createSubmissionReqDTO = new CreateSubmissionReqDTO();
		createSubmissionReqDTO.setAccountNumber(accountNumber);
		return createSubmissionReqDTO;
	}

	protected static LossInfoDTO buildLossInfoDTO(WorkersCompApplicationDTO dataDTO) {
		LossInfoDTO lossInfoDTO = new LossInfoDTO();
		Lobs lobs = new Lobs();
		
		// Dynamic data implementation blocked due to business decision (5/23/18)
		WC7WorkersComp wC7WorkersComp = new WC7WorkersComp();
		wC7WorkersComp.setPriorLossesExist(true);
		wC7WorkersComp.setNumLossesInPriorYear(0);
		wC7WorkersComp.setNumLossesInYear2(0);
		wC7WorkersComp.setNumLossesInYear3(0);
		
		lobs.setWC7WorkersComp(wC7WorkersComp);
		lossInfoDTO.setLobs(lobs);
		
		return lossInfoDTO;
	}

	protected static List<WC7ClassificationsDTO> buildClassificationsDTO(WorkersCompApplicationDTO dataDTO) {
		WC7ClassificationsDTO wC7ClassificationsDTO = new WC7ClassificationsDTO();
		wC7ClassificationsDTO.setState("NC");
		
		CoveredEmployee coveredEmployee = new CoveredEmployee();
		coveredEmployee.setBasisAmount("12000");
		coveredEmployee.setIfAnyExposure(true);
		coveredEmployee.setState("NC");
		
		ClassCode classCode = new ClassCode();
		classCode.setPublicID("zv4iejd065rlpci828shj377d6b");
		classCode.setClassification("Barber Or Beauty Parlor Supply Houses");
		classCode.setCode("8018");
		classCode.setShortDesc("Barber Or Beauty Parlor Supply Houses");
		classCode.setJurisdiction("NC");
		coveredEmployee.setClassCode(classCode);
		
		EffectiveDate effectiveDate = new EffectiveDate();
		String[] effectDateData = dataDTO.getPolicy().getPropEffectiveDate().split("/");
		
		effectiveDate.setYear(Integer.parseInt(effectDateData[2]));
		effectiveDate.setMonth(Integer.parseInt(effectDateData[0]));
		effectiveDate.setDay(Integer.parseInt(effectDateData[1]));
		coveredEmployee.setEffectiveDate(effectiveDate);
		
		Location location = new Location();
		
		Address address = new Address();
		address.setAddressLine1("7735 N TRYON ST");
		address.setCity("Charlotte");
		address.setState("NC");
		address.setPostalCode("28262");
		address.setCounty("Mecklenburg");
		location.setAddress(address);
		
		coveredEmployee.setLocation(location);
		coveredEmployee.setbCDCode("16755");
		coveredEmployee.setProhibitedClassInd(false);
		coveredEmployee.setbCDescription("Barber or Beauty Parlor Supply House");
		
		wC7ClassificationsDTO.setCoveredEmployee(Arrays.asList(coveredEmployee));
		
		return Arrays.asList(wC7ClassificationsDTO);
	}
	
	protected static WC7AvailableCoveragesDTO buildAvailableCoveragesDTO(WorkersCompApplicationDTO dataDTO) {
		WC7AvailableCoveragesDTO wC7AvailableCoveragesDTO = new WC7AvailableCoveragesDTO();
		
		wC7AvailableCoveragesDTO.setLineCoverages(buildLineCoverages());
		wC7AvailableCoveragesDTO.setStateCoverages(buildStateCoverages());
		wC7AvailableCoveragesDTO.setLineConditions(buildLineConditions());
		wC7AvailableCoveragesDTO.setLineExclusions(buildLineExclusions());
		wC7AvailableCoveragesDTO.setStateModifiers(buildStateModifiers());
		
		return wC7AvailableCoveragesDTO;
	}

	protected static List<LineCoverages> buildLineCoverages() {
		LineCoverages lineCoverages = new LineCoverages();
		lineCoverages.setName("Workers' Compensation And Employers' Liability Insurance Policy (Section 3B)");
		lineCoverages.setUpdated(true);
		List<Terms> terms = new ArrayList<Terms>();
		
		Terms term1 = new Terms();
		term1.setName("Policy Limit - Disease");
		term1.setPatternCode("WC7EmpLiabPolicyLimit");
		term1.setChosenTerm("ztfgento0qocffr04r8foj3r0e9");
		term1.setChosenTermValue("1,000,000");
		term1.setUpdated(true);
		term1.setReadOnly(false);
		
		Terms term2 = new Terms();
		term2.setName("Limit - per Accident / per Employee Disease");
		term2.setPatternCode("WC7EmpLiabLimit");
		term2.setChosenTerm("zvoi41mr4ftoi35sb0rbqkc27ca");
		term2.setChosenTermValue("400,000/400,000");
		term2.setUpdated(true);
		term2.setReadOnly(false);
		
		terms.add(term1);
		terms.add(term2);
		lineCoverages.setTerms(terms);
		
		lineCoverages.setSelected(true);
		lineCoverages.setPublicID("WC7WorkersCompEmpLiabInsurancePolicyACov");
		lineCoverages.setCoverageCategoryCode("WC7WorkCompLineCategory");
		lineCoverages.setCoverageCategoryDisplayName("Workers' Compensation Line Coverages");
		
		return Arrays.asList(lineCoverages);
	}

	protected static List<StateCoverages> buildStateCoverages() {
		StateCoverages stateCoverages = new StateCoverages();
		Coverages coverages = new Coverages();
		coverages.setName("Benefits Deductible Endorsement");
		coverages.setUpdated(true);
		Terms term1 = new Terms();
		term1.setName("Deductible");
		term1.setPatternCode("WC7Deductible");
		term1.setChosenTerm("zlth6pjnrs05e9v3sa2qa68q3v8");
		term1.setChosenTermValue("1,000");
		term1.setUpdated(true);
		term1.setReadOnly(false);
		coverages.setTerms(Arrays.asList(term1));
		coverages.setPublicID("WC7BenefitsDedCov");
		coverages.setSelected(true);
		stateCoverages.setCoverages(Arrays.asList(coverages));
		stateCoverages.setStateId(34);
		stateCoverages.setIntrastateID(null);
		return Arrays.asList(stateCoverages);
	}

	protected static List<LineConditions> buildLineConditions() {
		LineConditions lineConditions = new LineConditions();
		lineConditions.setName("Cancellation and Non-Renewal Endorsement");
		lineConditions.setUpdated(true);
		List<Terms> terms = new ArrayList<Terms>();
		
		Terms term1 = new Terms();
		term1.setName("Days Notice For Non-Payment");
		term1.setPatternCode("WC7DaysNoticeForNonPayment");
		term1.setDirectValue(12);
		term1.setUpdated(true);
		term1.setReadOnly(false);
		
		Terms term2 = new Terms();
		term2.setName("Days Notice For Any Other Reason");
		term2.setPatternCode("WC7DaysNoticeForAnyOtherReason");
		term2.setDirectValue(12);
		term2.setUpdated(true);
		term2.setReadOnly(false);
		
		Terms term3 = new Terms();
		term3.setName("Days Notice For Non-Renewal");
		term3.setPatternCode("WC7DaysNoticeForNonRenewal");
		term3.setDirectValue(12);
		term3.setUpdated(true);
		term3.setReadOnly(false);
		
		terms.add(term1);
		terms.add(term2);
		terms.add(term3);
		lineConditions.setTerms(terms);
		
		lineConditions.setSelected(true);
		lineConditions.setPublicID("WC7CancellationAndNonRenewalCond");
		lineConditions.setDescription("Cancellation and Non-Renewal Endorsement");
		lineConditions.setClauseType("Condition");
		
		Schedules schedules = new Schedules();
		schedules.setPatternName("WC7CancellationAndNonRenewalCond");
		schedules.setDeserializationClass("com.nw.cl.edgevgpa6.capabilities.policycommon.coverage.schedule.dto.patterns.PolicyContactRoleScheduledItemDTO");
		
		ScheduleItems scheduleItems = new ScheduleItems();
		scheduleItems.setDeserializationclass("com.nw.cl.edgevgpa6.capabilities.policycommon.coverage.schedule.dto.patterns.PolicyContactRoleScheduledItemDTO");
		ContactPolicyRole contactPolicyRole = new ContactPolicyRole();
		AccountContact accountContact = new AccountContact();
		accountContact.setName("Test Imtiaz");
		accountContact.setAccountHolder(false);
		accountContact.setSubtype("Company");
		accountContact.setOrganizationType("association_ext");
		accountContact.setAddresses(Arrays.asList((String) null)); 
		contactPolicyRole.setAccountContact(accountContact);
		scheduleItems.setContactPolicyRole(contactPolicyRole);
		
		schedules.setScheduleItems(Arrays.asList(scheduleItems));
		schedules.setUpdated(true);
		
		lineConditions.setSchedules(Arrays.asList(schedules));
		return Arrays.asList(lineConditions);
	}

	protected static List<LineExclusions> buildLineExclusions() {
		LineExclusions lineExclusions = new LineExclusions();
		lineExclusions.setName("Designated Workplaces Exclusion Endorsement");
		lineExclusions.setUpdated(true);
		lineExclusions.setSelected(true);
		lineExclusions.setPublicID("WC7DesignatedWorkplacesExclEndorsementExcl");
		lineExclusions.setDescription("Designated Workplaces Exclusion Endorsement");
		lineExclusions.setClauseType("Exclusion");
		
		Schedules schedules = new Schedules();
		schedules.setPatternName("WC7DesignatedWorkplacesExclEndorsementExcl");
		schedules.setDeserializationClass("com.nw.cl.edgevgpa6.capabilities.policycommon.coverage.schedule.dto.patterns.EntityScheduledItemDTO");
		
		ScheduleItems scheduleItems = new ScheduleItems();
		scheduleItems.setDeserializationclass("com.nw.cl.edgevgpa6.capabilities.policycommon.coverage.schedule.dto.patterns.EntityScheduledItemDTO");
		
		ItemData itemData = new ItemData();
		itemData.setScheduleNumber(new IntegerValue(1));
		itemData.setExcludedItem(new StringValue("Exclusion"));
		itemData.setAddressLine1(new StringValue("3850 E Independence Blvd"));
		itemData.setAddressLine2(new StringValue(null));
		itemData.setCity(new StringValue("Charlotte"));
		itemData.setJurisdiction(new TypeCodeValue("NC"));
		
		scheduleItems.setItemData(itemData);
		schedules.setScheduleItems(Arrays.asList(scheduleItems));
		schedules.setUpdated(true);
		lineExclusions.setSchedules(Arrays.asList(schedules));
				
		return Arrays.asList(lineExclusions);
	}

	protected static List<StateModifiers> buildStateModifiers() {
		StateModifiers stateModifiers = new StateModifiers();
		Modifiers modifiers = new Modifiers();
		ModifierTerm modifierTerm = new ModifierTerm();
		modifierTerm.setExpModStatusSelectedKey("Final");
		modifierTerm.setDeserializationclass("com.nw.cl.edgevgpa6.capabilities.quote.lob.wc7.quoting.modifier.dto.patterns.ModifierTermWC7ExpModDTO");
		modifiers.setModifierTerm(modifierTerm);
		modifiers.setUpdated(true);
		stateModifiers.setModifiers(Arrays.asList(modifiers));
		return Arrays.asList(stateModifiers);
	}
}
