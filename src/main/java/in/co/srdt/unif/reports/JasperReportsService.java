package in.co.srdt.unif.reports;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import in .co.srdt.unif.storage.FileSystemStorageService;
import in .co.srdt.unif.storage.StorageService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class JasperReportsService implements ReportService {

  private final StorageService storageService = new FileSystemStorageService();

  @Override
  public byte[] generatePDFReport(String inputFileName, Map < String, Object > params) {

	  return generatePDFReport(ExportFormat.PDF, inputFileName, params, new JREmptyDataSource());
  }

  @Override
	public byte[] generatePDFReport(ExportFormat format, String inputFileName, Map<String, Object> params) {
		return generatePDFReport(format, inputFileName, params, new JREmptyDataSource());
	}
  
  @Override
  public byte[] generatePDFReport(ExportFormat format, String inputFileName, Map<String, Object> params,JRDataSource dataSource) {

    byte[] bytes = null;

    JasperReport jasperReport = null;

    try {
    		// Check if a compiled report exists
    		if (storageService.jasperFileExists(inputFileName)) 
    		{
    			jasperReport = (JasperReport) JRLoader.loadObject(storageService.loadJasperFile(inputFileName));
    		}
    		// Compile report from source and save
    		else 
    		{
    			String jrxml = storageService.loadJrxmlFile(inputFileName);

    			jasperReport = JasperCompileManager.compileReport(jrxml);
    			// Save compiled report. Compiled report is loaded next time
    			JRSaver.saveObject(jasperReport, storageService.loadJasperFile(inputFileName));
    		}
      
    		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
    		
    		if (format == ExportFormat.PDF)
				bytes = generatePDF(jasperPrint);
			else if (format == ExportFormat.XLSX)
				bytes = generateExcel(jasperPrint);
        

//    		bytes = JasperExportManager.exportReportToPdf(jasperPrint);
    }
    catch(JRException | IOException e) {
      e.printStackTrace();

    }

    return bytes;
  }

  private byte[] generatePDF(JasperPrint jasperPrint) throws JRException {
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
  
  private byte[] generateExcel(JasperPrint jasperPrint) throws JRException {
		byte[] bytes = null;
		SimpleExporterInput input = new SimpleExporterInput(jasperPrint);
		try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {
			SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(input);
			exporter.setExporterOutput(output);
			exporter.exportReport();
			bytes = byteArray.toByteArray();
			output.close();

		} catch (IOException e) {
			e.printStackTrace();;
		}
		return bytes;
	}
}