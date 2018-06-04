package com.lob.workerscomp;

import static java.lang.Integer.parseInt;
import static  java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dto.workerscomp.PriorCarrierLossInfo;
import com.dto.workerscomp.StateRatingInfo;
import com.dto.workerscomp.WorkersCompAddress;
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
		request.setParams(asList(params));
		
		return request;
	}

	protected static PeriodStart buildPeriodStart(WorkersCompApplicationDTO dataDTO) {
		PeriodStart periodStart = new PeriodStart();
		String[] effectDateData = dataDTO.getPolicy().getPropEffectiveDate().split("/");
		
		periodStart.setYear(parseInt(effectDateData[2]));
		periodStart.setMonth(parseInt(effectDateData[0]));
		periodStart.setDay(parseInt(effectDateData[1]));
		
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
		WC7WorkersComp wC7WorkersComp = new WC7WorkersComp();

		if (dataDTO.getPriorCarrierLossInfo().size() > 0) {
			List<PriorCarrierLossInfo> priorCarrierLosses = dataDTO.getPriorCarrierLossInfo();
			Calendar now = Calendar.getInstance();
			int currentYear = now.get(Calendar.YEAR);
			
			Map<String, Integer> numberOfLossesMap = getLossCounts(priorCarrierLosses, currentYear);
			
			wC7WorkersComp.setPriorLossesExist(true);
			wC7WorkersComp.setNumLossesInPriorYear(numberOfLossesMap.get(String.valueOf(currentYear)));
			wC7WorkersComp.setNumLossesInYear2(numberOfLossesMap.get(String.valueOf(currentYear - 1)));
			wC7WorkersComp.setNumLossesInYear3(numberOfLossesMap.get(String.valueOf(currentYear - 2)));
		} else {
			wC7WorkersComp.setPriorLossesExist(false);
		}
		
		lobs.setWC7WorkersComp(wC7WorkersComp);
		lossInfoDTO.setLobs(lobs);
		
		return lossInfoDTO;
	}

	private static Map<String, Integer> getLossCounts(List<PriorCarrierLossInfo> priorCarrierLosses, int currentYear) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		result.put(String.valueOf(currentYear), 0);
		result.put(String.valueOf(currentYear - 1), 0);
		result.put(String.valueOf(currentYear - 2), 0);
		
		for (PriorCarrierLossInfo priorCarrierLoss : priorCarrierLosses) {
			String priorCarrierYear = priorCarrierLoss.getYear();
			if (!result.containsKey(priorCarrierYear)) {
				result.put(priorCarrierYear, priorCarrierLoss.getClaimCount());
			} else {
				result.put(priorCarrierYear, result.get(priorCarrierYear) + priorCarrierLoss.getClaimCount());
			}
		}
		
		return result;
	}

	protected static List<WC7ClassificationsDTO> buildClassificationsDTO(WorkersCompApplicationDTO dataDTO) {
		WC7ClassificationsDTO wC7ClassificationsDTO = new WC7ClassificationsDTO();
		wC7ClassificationsDTO.setState(dataDTO.getNamedInsured().getMailingAddress().getStateOrProvinceCode());
		
		List<CoveredEmployee> coveredEmployees = new ArrayList<CoveredEmployee>();
		
		for (StateRatingInfo stateRatingInfo : dataDTO.getStateRatingInfo()) {
			CoveredEmployee coveredEmployee = new CoveredEmployee();
			WorkersCompAddress stateRatingAddress = dataDTO.getLocations().get(stateRatingInfo.getLocationNumber()).getAddress();
			
			coveredEmployee.setBasisAmount(stateRatingInfo.getAnnualRemunerationPayroll());
			coveredEmployee.setIfAnyExposure(true); // Temporarily hard-coded; Not sure where to pull this from
			coveredEmployee.setState(stateRatingAddress.getStateOrProvinceCode());
			
			// TODO Get this from Commercial Classification service??? 
			ClassCode classCode = new ClassCode();
			classCode.setPublicID("zv4iejd065rlpci828shj377d6b");
			classCode.setClassification("Barber Or Beauty Parlor Supply Houses");
			classCode.setCode("8018");
			classCode.setShortDesc("Barber Or Beauty Parlor Supply Houses");
			classCode.setJurisdiction("NC");
			coveredEmployee.setClassCode(classCode);
		
			EffectiveDate effectiveDate = new EffectiveDate();
			String[] effectDateData = dataDTO.getPolicy().getPropEffectiveDate().split("/");
			
			effectiveDate.setYear(parseInt(effectDateData[2]));
			effectiveDate.setMonth(parseInt(effectDateData[0]));
			effectiveDate.setDay(parseInt(effectDateData[1]));
			coveredEmployee.setEffectiveDate(effectiveDate);
			
			Location location = new Location();
			Address address = new Address();
			address.setAddressLine1(stateRatingAddress.getLineOne());
 			address.setCity(stateRatingAddress.getCity());
			address.setState(stateRatingAddress.getStateOrProvinceCode());
			address.setPostalCode(stateRatingAddress.getPostalCode());
			address.setCounty("");  // TODO Maybe get this from address standardization service?
			location.setAddress(address);
			
			coveredEmployee.setLocation(location);
			coveredEmployee.setbCDCode("16755"); // Temporarily hard-coded; Not sure where to pull this from
			coveredEmployee.setProhibitedClassInd(false);  // Temporarily hard-coded; Not sure where to pull this from
			coveredEmployee.setbCDescription("Barber or Beauty Parlor Supply House");  // Temporarily hard-coded; Not sure where to pull this from
			
			coveredEmployees.add(coveredEmployee);
		}
		
		wC7ClassificationsDTO.setCoveredEmployee(coveredEmployees);
		
		return Arrays.asList(wC7ClassificationsDTO);
	}
	
	protected static WC7AvailableCoveragesDTO buildAvailableCoveragesDTO(WorkersCompApplicationDTO dataDTO) {
		WC7AvailableCoveragesDTO wC7AvailableCoveragesDTO = new WC7AvailableCoveragesDTO();
		
		wC7AvailableCoveragesDTO.setLineCoverages(buildLineCoverages(dataDTO));
		wC7AvailableCoveragesDTO.setStateCoverages(buildStateCoverages());
		wC7AvailableCoveragesDTO.setLineConditions(buildLineConditions());
		wC7AvailableCoveragesDTO.setLineExclusions(buildLineExclusions());
		wC7AvailableCoveragesDTO.setStateModifiers(buildStateModifiers());
		
		return wC7AvailableCoveragesDTO;
	}

	protected static List<LineCoverages> buildLineCoverages(WorkersCompApplicationDTO dataDTO) {
		LineCoverages lineCoverages = new LineCoverages();
		lineCoverages.setName("Workers' Compensation And Employers' Liability Insurance Policy (Section 3B)");  // Intentionally hard coded
		lineCoverages.setUpdated(true);  // Intentionally hard coded
		
		List<Terms> terms = new ArrayList<Terms>();
		
		Terms term1 = new Terms();
		term1.setName("Policy Limit - Disease");  // Intentionally hard coded
		term1.setPatternCode("WC7EmpLiabPolicyLimit");  // Intentionally hard coded
		term1.setChosenTerm("ztfgento0qocffr04r8foj3r0e9");  // Intentionally hard coded
		term1.setChosenTermValue(dataDTO.getPolicy().getEmployerLiabilityDiseasePolicy());
		term1.setUpdated(true);  // Intentionally hard coded
		term1.setReadOnly(false);  // Intentionally hard coded
		
		Terms term2 = new Terms();
		term2.setName("Limit - per Accident / per Employee Disease");  // Intentionally hard coded
		term2.setPatternCode("WC7EmpLiabLimit");  // Intentionally hard coded
		term2.setChosenTerm("zvoi41mr4ftoi35sb0rbqkc27ca");  // Intentionally hard coded
		term2.setChosenTermValue(dataDTO.getPolicy().getEmployerLiabilityEachAccident() + "/" + dataDTO.getPolicy().getEmployerLiabilityDiseaseEachEmployee());
		term2.setUpdated(true);  // Intentionally hard coded
		term2.setReadOnly(false);  // Intentionally hard coded
		
		terms.add(term1);
		terms.add(term2);
		lineCoverages.setTerms(terms);
		
		lineCoverages.setSelected(true);  // Intentionally hard coded
		lineCoverages.setPublicID("WC7WorkersCompEmpLiabInsurancePolicyACov");  // Intentionally hard coded
		lineCoverages.setCoverageCategoryCode("WC7WorkCompLineCategory");  // Intentionally hard coded
		lineCoverages.setCoverageCategoryDisplayName("Workers' Compensation Line Coverages");  // Intentionally hard coded
		
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
