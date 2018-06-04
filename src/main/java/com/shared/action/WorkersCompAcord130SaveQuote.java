package com.shared.action;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import com.dto.workerscomp.WorkersCompApplicationDTO;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.xfa.XfaForm;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.lob.workerscomp.WorkersCompDataMapper;
import com.lob.workerscomp.WorkersCompSaveQuoteRequestBuilder;
import com.nationwide.dto.savequote.request.SaveQuoteRequestDTO;
import com.nationwide.dto.savequote.response.SaveQuoteResponseDTO;
import com.nationwide.service.savequote.SaveQuoteService;

public class WorkersCompAcord130SaveQuote {
	
	public static void main(String[] args) throws IOException, TransformerException {
		System.out.println("Hello there.. sir.");
		
		InputStream inputStream = WorkersCompAcord130SaveQuote.class.getResourceAsStream("/acord-130.pdf");
		PdfDocument pdf = new PdfDocument(new PdfReader(inputStream));
		PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);
		XfaForm xfa = form.getXfaForm();
		Document doc = xfa.getDomDocument();
		
		WorkersCompApplicationDTO dataDTO = WorkersCompDataMapper.mapAcord130ToDTO(doc);
		String accountNumber = "ACP     3203082752";
		
		SaveQuoteRequestDTO requestDTO = WorkersCompSaveQuoteRequestBuilder.buildRequest(dataDTO, accountNumber);
		SaveQuoteResponseDTO responseDTO = SaveQuoteService.saveQuote(requestDTO);
		System.out.println(responseDTO.getResult().getQuoteID());
	}
}