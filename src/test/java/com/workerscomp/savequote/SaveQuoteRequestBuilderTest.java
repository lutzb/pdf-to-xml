package com.workerscomp.savequote;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.dto.workerscomp.WorkersCompApplicationDTO;
import com.lob.workerscomp.WorkersCompSaveQuoteRequestBuilder;
import com.dto.workerscomp.Policy;
import com.nationwide.dto.savequote.request.PeriodStart;
import com.nationwide.dto.savequote.request.SaveQuoteRequestDTO;

public class SaveQuoteRequestBuilderTest {
	
	@Test
	public void whenBuildRequestIsGivenAWorkersCompApplicationDTOItReturnsARequestDTOWithPeriodStartFilledIn() {
		WorkersCompApplicationDTO testData = new WorkersCompApplicationDTO();
		Policy policy = new Policy();
		policy.setPropEffectiveDate("1/23/2018");
		testData.setPolicy(policy);
		
		SaveQuoteRequestDTO requestDTO = WorkersCompSaveQuoteRequestBuilder.buildRequest(testData, "accountNumber");
		PeriodStart actual = requestDTO.getParams().get(0).getPeriodStart();
		
		assertEquals(2018, actual.getYear());
		assertEquals(1, actual.getMonth());
		assertEquals(23, actual.getDay());
	}

}
