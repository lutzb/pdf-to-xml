package com.shared.action;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import com.dto.businessauto.BusinessAutoApplicationDTO;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.xfa.XfaForm;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.lob.businessauto.BusinessAutoDataMapper;
import com.lob.businessauto.BusinessAutoSaveQuoteRequestBuilder;
import com.nationwide.dto.savequote.request.SaveQuoteRequestDTO;
import com.nationwide.dto.savequote.response.SaveQuoteResponseDTO;
import com.nationwide.service.savequote.SaveQuoteService;

public class BusinessAutoAcord127SaveQuote {
	
	public static void main(String[] args) throws IOException, TransformerException {
		System.out.println("Hello there.. sir.");
		
		InputStream inputStream = WorkersCompAcord130SaveQuote.class.getResourceAsStream("/acord-127.pdf");
		PdfDocument pdf = new PdfDocument(new PdfReader(inputStream));
		PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);
		XfaForm xfa = form.getXfaForm();
		Document doc = xfa.getDomDocument();
		
		BusinessAutoApplicationDTO dataDTO = BusinessAutoDataMapper.mapAcord127ToDTO(doc);
		String accountNumber = "ACP     3203082752";
		
		SaveQuoteRequestDTO requestDTO = BusinessAutoSaveQuoteRequestBuilder.buildRequest(dataDTO, accountNumber);
		SaveQuoteResponseDTO responseDTO = SaveQuoteService.saveQuote(requestDTO);
		System.out.println(responseDTO.getResult().getQuoteID());
	}
}