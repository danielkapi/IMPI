package www.gob.mx.impi.processor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.hwpf.*;
import org.apache.poi.hwpf.extractor.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.springframework.batch.item.ItemProcessor;

import www.gob.mx.impi.model.Documento;

public class DocumentoProcessor implements ItemProcessor<Documento,Documento> {

	final static Logger logger = Logger.getLogger(DocumentoProcessor.class);

	@Override
	public Documento process(Documento doc) throws Exception {

		try {
			
//			Path path = Paths.get("C:/Users/DanielML/Documents/IMPI/docs_word/Titulo_docx.docx");
//			byte[] byteData = Files.readAllBytes(path);
			
			XWPFDocument docx = new XWPFDocument(new ByteArrayInputStream(doc.getDocumentoElectronico()));

			for (XWPFParagraph para : docx.getParagraphs()) {
				List<XWPFRun> runs = para.getRuns();
				StringBuffer tituloCompleto = new StringBuffer();
				for (XWPFRun run : runs) {
					tituloCompleto.append(run.getText(0));
				}
				doc.setTitulo(tituloCompleto.toString());
				
				doc.setFechaRegistro(new Date());
				System.out.println("String : " + tituloCompleto.toString() );
			
				String unicode = StringEscapeUtils.escapeJava(tituloCompleto.toString());
				System.out.println("unicode : " + unicode );
				 
				String Title = StringEscapeUtils.unescapeJava(unicode);
         		
		        System.out.println("Titulo decodificado: " + Title );
//		        logger.debug("titulo: " + doc.getTitulo() );
			}
			
	        /**
	        Get the total number of paragraphs
	        String[] paragraphs = we.getParagraphText();
	        System.out.println("Total Paragraphs: "+paragraphs.length);

	        for (int i = 0; i < paragraphs.length; i++) {
	            String unicode = StringEscapeUtils.escapeJava(paragraphs[i].toString());
	            
	            System.out.println("unicode : " + unicode );
	            if (i==0){
	            	word.setTitulo(unicode);
	            }
	            
	            String Title = StringEscapeUtils.unescapeJava(unicode);
	            		
	            System.out.println("String : " + Title );
	        }
	        */
		} catch (Exception e) {
			logger.error("Error al leer documento " +  e.getCause()) ;
		}
		
		
		doc.setDocumentoElectronico(null);
		return doc;
		
	}

}
