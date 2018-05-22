package com.shared.action;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import com.dto.workerscomp.Acord130DTO;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.xfa.XfaForm;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.nationwide.dto.savequote.request.SaveQuoteRequestDTO;
import com.nationwide.dto.savequote.response.SaveQuoteResponseDTO;
import com.nationwide.service.savequote.SaveQuoteService;
import com.shared.util.PDFToDTOMapper;
import com.workerscomp.savequote.SaveQuoteRequestBuilder;

public class Acord130PdfSaveQuote {
	
	public static void main(String[] args) throws IOException, TransformerException {
		System.out.println("Hello there.. sir.");
		
		InputStream inputStream = Acord130PdfSaveQuote.class.getResourceAsStream("/Acord-130.pdf");
		PdfDocument pdf = new PdfDocument(new PdfReader(inputStream));
		PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);
		XfaForm xfa = form.getXfaForm();
		Document doc = xfa.getDomDocument();
		
		Acord130DTO dataDTO = PDFToDTOMapper.mapDataToDTO(doc);
		
		SaveQuoteRequestDTO requestDTO = SaveQuoteRequestBuilder.buildRequest(dataDTO);
		SaveQuoteResponseDTO responseDTO = SaveQuoteService.saveQuote(requestDTO);
		System.out.println(responseDTO.getResult().getQuoteID());
	}
}