package in.co.srdt.unif.reports;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

public interface ReportService {

	
	byte[] generatePDFReport(String inputFileName, Map<String, Object> params);

	byte[] generatePDFReport(ExportFormat format, String inputFileName, Map<String, Object> params);
	
	byte[] generatePDFReport(ExportFormat format,String inputFileName, Map<String, Object> params, JRDataSource dataSource);
}
