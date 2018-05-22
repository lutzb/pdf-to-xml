package com.workerscomp.savequote;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.dto.workerscomp.Acord130DTO;
import com.dto.workerscomp.Policy;
import com.nationwide.dto.savequote.request.PeriodStart;
import com.nationwide.dto.savequote.request.SaveQuoteRequestDTO;
import com.workerscomp.savequote.SaveQuoteRequestBuilder;

public class SaveQuoteRequestBuilderTest {
	
	@Test
	public void whenBuildRequestIsGivenAnAcord130DTOItReturnsARequestDTOWithPeriodStartFilledIn() {
		Acord130DTO testData = new Acord130DTO();
		Policy policy = new Policy();
		policy.setPropEffectiveDate("1/23/2018");
		testData.setPolicy(policy);
		
		SaveQuoteRequestDTO requestDTO = SaveQuoteRequestBuilder.buildRequest(testData);
		PeriodStart actual = requestDTO.getParams().get(0).getPeriodStart();
		
		assertEquals(2018, actual.getYear());
		assertEquals(1, actual.getMonth());
		assertEquals(23, actual.getDay());
	}

}
