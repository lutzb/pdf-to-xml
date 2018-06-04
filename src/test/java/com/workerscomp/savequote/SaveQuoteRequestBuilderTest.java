package com.workerscomp.savequote;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dto.workerscomp.PriorCarrierLossInfo;
import com.dto.workerscomp.WorkersCompApplicationDTO;
import com.dto.workerscomp.WorkersCompPolicy;
import com.lob.workerscomp.WorkersCompSaveQuoteRequestBuilder;
import com.nationwide.dto.savequote.request.LossInfoDTO;
import com.nationwide.dto.savequote.request.PeriodStart;
import com.nationwide.dto.savequote.request.WC7WorkersComp;

public class SaveQuoteRequestBuilderTest extends WorkersCompSaveQuoteRequestBuilder {
	
	private WorkersCompApplicationDTO testData;
	
	@Before
	public void setup() {
		testData = new WorkersCompApplicationDTO();
	}
	
	@Test
	public void whenBuildPeriodStartIsGivenAProposedEffectiveDateItReturnsAPeriodStartWithTheDataFilledIn() {
		WorkersCompPolicy policy = new WorkersCompPolicy();
		policy.setPropEffectiveDate("1/23/2018");
		testData.setPolicy(policy);
		
		PeriodStart actual = WorkersCompSaveQuoteRequestBuilder.buildPeriodStart(testData);
		
		assertEquals(2018, actual.getYear());
		assertEquals(1, actual.getMonth());
		assertEquals(23, actual.getDay());
	}
	
	@Test
	public void whenBuildLossInfoDTOIsGivenAListOfPriorCarrierLossesItReturnsAThing() {
		List<PriorCarrierLossInfo> priorCarrierLosses = new ArrayList<PriorCarrierLossInfo>();
		testData.setPriorCarrierLossInfo(priorCarrierLosses);
		
		LossInfoDTO actual = WorkersCompSaveQuoteRequestBuilder.buildLossInfoDTO(testData);
		WC7WorkersComp actualWorkersComp = actual.getLobs().getWC7WorkersComp();
		
		assertEquals(false, actualWorkersComp.isPriorLossesExist());
	}

}
