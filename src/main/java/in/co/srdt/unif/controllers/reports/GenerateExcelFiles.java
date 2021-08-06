package in .co.srdt.unif.controllers.reports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IgnoredErrorType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import in .co.srdt.unif.model.reports.AllBankDetails;
import in .co.srdt.unif.model.reports.BankAdviceRembModel;
import in .co.srdt.unif.model.reports.BankSalaryAdvice;
import in .co.srdt.unif.model.reports.BankTypeReportModel;
import in .co.srdt.unif.model.reports.EPFModel;
import in .co.srdt.unif.model.reports.LwpReport;
import in .co.srdt.unif.model.reports.ModPayBillComModel;
import in .co.srdt.unif.model.reports.ModPayBillModel;
import in .co.srdt.unif.model.reports.NPSModel;
import in .co.srdt.unif.model.reports.PayrollSalaryCards;
import in .co.srdt.unif.model.reports.PersonalData;
import in.co.srdt.unif.model.reports.ReimbTravellingReport;
import in .co.srdt.unif.model.reports.VendorDetailsModel;
import in .co.srdt.unif.model.reports.VendorPersonDetails;
import in .co.srdt.unif.model.reports.VendorSummeryReport;

public class GenerateExcelFiles {

  private final CheckAlphaNumeric alphanum = new CheckAlphaNumeric();

  /////////////////////////// BANK ADVICE
  /////////////////////////// //////////////////////////////////////////////////////////
  public void WriteBankAdviceRegister(BankSalaryAdvice data[], String date, String buname, String calmonth, String banktype, ServletOutputStream stream) throws IOException {
    int count = 0;
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Sheet 1");
    sheet.addIgnoredErrors(CellRangeAddress.valueOf("A1:XFD1048576"), IgnoredErrorType.NUMBER_STORED_AS_TEXT);

    int rowNum = 0;
    int colNum = 0;
    double grossamt = 0, netpay = 0, net = 0, totalded = 0, tds = 0, otherded = 0;
    if (data.length != 0) {
      List < BankSalaryAdvice > preg = Arrays.asList(data);

      LinkedHashMap < String, Object > employeedatamap = null;
      List < Map < String, Object >> employeedatalist = new ArrayList < > ();

      for (BankSalaryAdvice payregister: preg) {
        count++;
        employeedatamap = new LinkedHashMap < > ();
        employeedatamap.put("S No", count);
        employeedatamap.put("Emp ID", payregister.getPersonnumber());
        employeedatamap.put("Employee Name", payregister.getPersonname());
        employeedatamap.put("Gross Amount", payregister.getGrossamount());
        employeedatamap.put("TDS", payregister.getTds());
        totalded = Double.parseDouble(payregister.getTotaldeduction());
        tds = Double.parseDouble(payregister.getTds());
        otherded = totalded - tds;

        employeedatamap.put("Other Deduction", otherded);
        employeedatamap.put("Net Amount", payregister.getNeypay());
        net += Double.parseDouble(payregister.getNeypay());

        employeedatalist.add(employeedatamap);

      }

      Set < String > payregkeysets = employeedatalist.get(0).keySet();

      // ***** Style For Heading Row ****//
      XSSFFont font = workbook.createFont();
      font.setFontHeightInPoints((short) 14);
      font.setFontName("Arial");
      font.setColor(IndexedColors.BLACK.getIndex());
      font.setBold(true);
      font.setItalic(false);

      CellStyle style = workbook.createCellStyle();
      style.setAlignment(HorizontalAlignment.LEFT);
      style.setVerticalAlignment(VerticalAlignment.CENTER);

      // Setting font to style
      style.setFont(font);

      XSSFFont partyfont = workbook.createFont();
      partyfont.setFontHeightInPoints((short) 10);
      partyfont.setFontName("Times New Roman");
      partyfont.setColor(IndexedColors.BLACK.getIndex());
      partyfont.setBold(true);
      partyfont.setItalic(false);

      CellStyle partystyle = workbook.createCellStyle();
      partystyle.setAlignment(HorizontalAlignment.LEFT);
      partystyle.setVerticalAlignment(VerticalAlignment.CENTER);

      // Setting font to style
      partystyle.setFont(partyfont);

      CellStyle dtstyle = workbook.createCellStyle();
      dtstyle.setAlignment(HorizontalAlignment.RIGHT);
      dtstyle.setVerticalAlignment(VerticalAlignment.CENTER);

      // Setting font to style

      XSSFFont font1 = workbook.createFont();
      font1.setFontHeightInPoints((short) 9);
      font1.setFontName("Times New Roman");
      font1.setColor(IndexedColors.BLACK.getIndex());
      font1.setBold(true);
      font1.setItalic(false);

      CellStyle empdetailsstyle = workbook.createCellStyle();
      empdetailsstyle.setAlignment(HorizontalAlignment.CENTER);
      empdetailsstyle.setVerticalAlignment(VerticalAlignment.CENTER);
      empdetailsstyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
      empdetailsstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

      empdetailsstyle.setBorderBottom(BorderStyle.THIN);
      empdetailsstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      empdetailsstyle.setBorderTop(BorderStyle.THIN);
      empdetailsstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      empdetailsstyle.setBorderLeft(BorderStyle.THIN);
      empdetailsstyle.setLeftBorderColor(IndexedColors.WHITE.getIndex());
      // Setting font to style
      empdetailsstyle.setFont(font1);

      XSSFFont datafont = workbook.createFont();
      datafont.setFontHeightInPoints((short) 10);
      datafont.setFontName("Calibri");
      datafont.setColor(IndexedColors.BLACK.getIndex());
      datafont.setBold(false);
      datafont.setItalic(false);

      XSSFFont datafont1 = workbook.createFont();
      datafont1.setFontHeightInPoints((short) 10);
      datafont1.setFontName("Calibri");
      datafont1.setColor(IndexedColors.BLACK.getIndex());
      datafont1.setBold(true);
      datafont1.setItalic(false);

      CellStyle datastyle = workbook.createCellStyle();
      datastyle.setAlignment(HorizontalAlignment.LEFT);
      datastyle.setVerticalAlignment(VerticalAlignment.CENTER);
      datastyle.setFillForegroundColor(IndexedColors.WHITE.index);
      datastyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

      datastyle.setBorderBottom(BorderStyle.THIN);
      datastyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      datastyle.setBorderRight(BorderStyle.THIN);
      datastyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
      datastyle.setBorderTop(BorderStyle.THIN);
      datastyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      datastyle.setBorderLeft(BorderStyle.THIN);
      datastyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      // Setting font to style
      datastyle.setFont(datafont);

      CellStyle datastyle1 = workbook.createCellStyle();
      datastyle1.setAlignment(HorizontalAlignment.RIGHT);
      datastyle1.setVerticalAlignment(VerticalAlignment.CENTER);
      datastyle1.setFillForegroundColor(IndexedColors.WHITE.index);
      datastyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

      datastyle1.setBorderBottom(BorderStyle.THIN);
      datastyle1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      datastyle1.setBorderRight(BorderStyle.THIN);
      datastyle1.setRightBorderColor(IndexedColors.BLACK.getIndex());
      datastyle1.setBorderTop(BorderStyle.THIN);
      datastyle1.setTopBorderColor(IndexedColors.BLACK.getIndex());
      datastyle1.setBorderLeft(BorderStyle.THIN);
      datastyle1.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      datastyle1.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
      // Setting font to style
      datastyle1.setFont(datafont);

      CellStyle datastyle2 = workbook.createCellStyle();
      datastyle2.setAlignment(HorizontalAlignment.RIGHT);
      datastyle2.setVerticalAlignment(VerticalAlignment.CENTER);
      datastyle2.setFillForegroundColor(IndexedColors.WHITE.index);
      datastyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      datastyle2.setBorderBottom(BorderStyle.THIN);
      datastyle2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      datastyle2.setBorderRight(BorderStyle.THIN);
      datastyle2.setRightBorderColor(IndexedColors.BLACK.getIndex());
      datastyle2.setBorderTop(BorderStyle.THIN);
      datastyle2.setTopBorderColor(IndexedColors.BLACK.getIndex());
      datastyle2.setBorderLeft(BorderStyle.THIN);
      datastyle2.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      // Setting font to style
      datastyle2.setFont(datafont);

      CellStyle datastyle3 = workbook.createCellStyle();
      datastyle3.setAlignment(HorizontalAlignment.RIGHT);
      datastyle3.setVerticalAlignment(VerticalAlignment.CENTER);
      datastyle3.setFillForegroundColor(IndexedColors.WHITE.index);
      datastyle3.setFillPattern(FillPatternType.SOLID_FOREGROUND);

      datastyle3.setBorderBottom(BorderStyle.THIN);
      datastyle3.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      datastyle3.setBorderRight(BorderStyle.THIN);
      datastyle3.setRightBorderColor(IndexedColors.BLACK.getIndex());
      datastyle3.setBorderTop(BorderStyle.THIN);
      datastyle3.setTopBorderColor(IndexedColors.BLACK.getIndex());
      datastyle3.setBorderLeft(BorderStyle.THIN);
      datastyle3.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      datastyle3.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
      // Setting font to style
      datastyle3.setFont(datafont1);

      Row row = sheet.createRow(rowNum++);
      Cell headingcell = row.createCell(colNum++);

      headingcell.setCellValue("UPMRC Bank Advice Register(Salary) Report for M/O " + calmonth + " - " + buname);
      headingcell.setCellStyle(style);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A1:H1"));

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell headingcell1 = row.createCell(colNum++);
      headingcell1.setCellValue("Bank Advice # " + banktype + "     /          ");

      sheet.addMergedRegion(CellRangeAddress.valueOf("A2:E2"));

      colNum = 5;
      Cell dtcell = row.createCell(colNum++);
      dtcell.setCellStyle(dtstyle);
      dtcell.setCellValue("Date");
      Cell dtcell1 = row.createCell(colNum++);
      dtcell1.setCellValue(date);

      row = sheet.createRow(rowNum++);
      row = sheet.createRow(rowNum++);

      // Print all data from JSON along with headings
      row = sheet.createRow(rowNum++);
      colNum = 0;
      //int i = 0;
      for (String str: payregkeysets) {
        if (str.equals("S No")) {
          colNum = 0;
          sheet.setColumnWidth(colNum, 2500);
          Cell cell = row.createCell(colNum);
          cell.setCellValue(str);
          cell.setCellStyle(empdetailsstyle);
        } else if (str.equals("Emp ID")) {
          colNum = 1;
          sheet.setColumnWidth(colNum, 2500);
          Cell cell = row.createCell(colNum);
          cell.setCellValue(str);
          cell.setCellStyle(empdetailsstyle);
        } else if (str.equals("Employee Name")) {
          colNum = 2;
          sheet.setColumnWidth(colNum, 6000);
          Cell cell = row.createCell(colNum);
          cell.setCellValue(str);
          cell.setCellStyle(empdetailsstyle);
        } else if (str.equals("Gross Amount")) {
          colNum = 3;
          sheet.setColumnWidth(colNum, 6000);
          Cell cell = row.createCell(colNum);
          cell.setCellValue(str);
          cell.setCellStyle(empdetailsstyle);
        } else if (str.equals("TDS")) {
          colNum = 4;
          sheet.setColumnWidth(colNum, 6000);
          Cell cell = row.createCell(colNum);
          cell.setCellValue(str);
          cell.setCellStyle(empdetailsstyle);
        } else if (str.equals("Other Deduction")) {
          colNum = 5;
          sheet.setColumnWidth(colNum, 6000);
          Cell cell = row.createCell(colNum);
          cell.setCellValue(str);
          cell.setCellStyle(empdetailsstyle);
        } else if (str.equals("Net Amount")) {
          colNum = 6;
          sheet.setColumnWidth(colNum, 6000);
          Cell cell = row.createCell(colNum);
          cell.setCellValue(str);
          cell.setCellStyle(empdetailsstyle);
        }
      }

      for (Map < String, Object > dt: employeedatalist) {
        row = sheet.createRow(rowNum++);
        colNum = 0;
        payregkeysets = dt.keySet();
        for (String str: payregkeysets) {
          //					if (! (dt.get("Gross Amount").toString().equals("0.0") && dt.get("Other Deduction").toString().equals("0.0") && dt.get("Net Amount").toString().equals("0.0") && dt.get("TDS").toString().equals("0.0"))) 
          //					{
          if (str.equals("S No")) {
            colNum = 0;
            Cell cell = row.createCell(colNum);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(dt.get(str).toString());
            cell.setCellStyle(datastyle2);
          } else if (str.equals("Emp ID")) {
            colNum = 1;
            Cell cell = row.createCell(colNum);
            String coldata = dt.get(str).toString();
            if (alphanum.ifAlphaNumeric(coldata)) {
              int pno = Integer.parseInt(coldata);
              //								System.out.println("Person Number ::: "+pno);
              cell.setCellType(CellType.NUMERIC);
              cell.setCellValue(pno);
              cell.setCellStyle(datastyle2);
            } else {
              String pno = coldata;
              //								System.out.println("Person Number ::: "+pno);
              cell.setCellType(CellType.STRING);
              cell.setCellValue(pno);
              cell.setCellStyle(datastyle2);
            }
          } else if (str.equals("Employee Name")) {
            colNum = 2;
            Cell cell = row.createCell(colNum);
            cell.setCellType(CellType.STRING);
            if (dt.get(str) == null) {
              cell.setCellValue("");
            } else {
              cell.setCellValue(dt.get(str).toString());
            }
            cell.setCellStyle(datastyle);
          } else if (str.equals("Gross Amount")) {
            colNum = 3;
            Cell cell = row.createCell(colNum);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            cell.setCellStyle(datastyle1);
          } else if (str.equals("TDS")) {
            colNum = 4;
            Cell cell = row.createCell(colNum);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            cell.setCellStyle(datastyle1);
          } else if (str.equals("Other Deduction")) {
            colNum = 5;
            Cell cell = row.createCell(colNum);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            cell.setCellStyle(datastyle1);
          } else if (str.equals("Net Amount")) {
            colNum = 6;
            Cell cell = row.createCell(colNum);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            cell.setCellStyle(datastyle1);
          }

          //					} else {
          //						System.out.println(":::: ROW NUMBER :::: " + rowNum);
          //						Row removingRow = sheet.getRow(rowNum);
          //						if (removingRow != null) {
          //							//				            sheet.removeRow(removingRow);
          //
          //						}
          //					}
        }
      }

      //			System.out.println("Row Num ===> " + rowNum);
      row = sheet.createRow(rowNum++);
      colNum = 0;

      Cell totcell = row.createCell(colNum++);
      String merged = "A" + rowNum + ":C" + rowNum;
      sheet.addMergedRegion(CellRangeAddress.valueOf(merged));
      totcell.setCellValue("Total");
      totcell.setCellStyle(datastyle3);

      colNum = 3;
      int rowNumm = rowNum - 1;
      Cell totgross = row.createCell(colNum++);
      String grossformula = "SUM(D6:D" + rowNumm + ")";
      totgross.setCellFormula(grossformula);
      totgross.setCellStyle(datastyle3);

      Cell totOther = row.createCell(colNum++);
      String otherformula = "SUM(E6:E" + rowNumm + ")";
      totOther.setCellFormula(otherformula);
      totOther.setCellStyle(datastyle3);

      Cell totnet = row.createCell(colNum++);
      String netformula = "SUM(F6:F" + rowNumm + ")";
      totnet.setCellFormula(netformula);
      //net = totnet.getNumericCellValue();
      //			System.out.println("NET ===> "+net);
      totnet.setCellStyle(datastyle3);

      Cell totTDS = row.createCell(colNum++);
      String tdsformula = "SUM(G6:G" + rowNumm + ")";
      totTDS.setCellFormula(tdsformula);
      totTDS.setCellStyle(datastyle3);

      row = sheet.createRow(rowNum++);

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell partycell = row.createCell(colNum++);
      partycell.setCellValue("Total No. of Parties: - " + count);
      partycell.setCellStyle(partystyle);

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell partycell1 = row.createCell(colNum++);
      ConvertNumToWord cnum = new ConvertNumToWord();

      String numtowords = cnum.convertNumber((long) net);
      partycell1.setCellValue("Total Rs. " + numtowords + " Only. (Rs. " + (long) net + ")");
      partycell1.setCellStyle(partystyle);

    } else {
      XSSFFont font = workbook.createFont();
      font.setFontHeightInPoints((short) 14);
      font.setFontName("Arial");
      font.setColor(IndexedColors.BLACK.getIndex());
      font.setBold(true);
      font.setItalic(false);

      CellStyle style = workbook.createCellStyle();
      style.setAlignment(HorizontalAlignment.LEFT);
      style.setVerticalAlignment(VerticalAlignment.CENTER);

      // Setting font to style
      style.setFont(font);

      Row row = sheet.createRow(rowNum++);
      Cell headingcell = row.createCell(colNum++);
      headingcell.setCellValue("UPMRC Bank Advice Register(Salary) Report for M/O " + calmonth + " - " + buname);
      headingcell.setCellStyle(style);

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell cell = row.createCell(colNum);
      cell.setCellValue("No Data Found!!!");

      sheet.addMergedRegion(CellRangeAddress.valueOf("A1:H1"));
    }

    workbook.write(stream);
    workbook.close();
  }

  //////////////////////// Employee Reimbursement Bank Transaction Register
  //////////////////////// //////////////////////////////////////////

  public void WriteEmpRembBankTransactionRegister(BankAdviceRembModel data[], String date, String buname,
    String calmonth, ServletOutputStream stream) throws IOException {
    int count = 0;
    int sum = 0;
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Sheet 1");
    sheet.addIgnoredErrors(CellRangeAddress.valueOf("A1:XFD1048576"), IgnoredErrorType.NUMBER_STORED_AS_TEXT);

    int rowNum = 0;
    int colNum = 0;

    if (data.length != 0) {
      List < BankAdviceRembModel > breg = Arrays.asList(data);

      LinkedHashMap < String, Object > employeedatamap = null;
      List < Map < String, Object >> employeedatalist = new ArrayList < > ();

      for (BankAdviceRembModel bankreg: breg) {
        count++;
        employeedatamap = new LinkedHashMap < > ();
        employeedatamap.put("Sno#", count);
        employeedatamap.put("Emp#", bankreg.getPersonnumber());
        employeedatamap.put("Beneficiary Name", bankreg.getPersonname());
        employeedatamap.put("Account No", bankreg.getBankaccountnumber());
        employeedatamap.put("IFSC Code", bankreg.getBranchifsccode());
        employeedatamap.put("Bank Name", bankreg.getBankname());
        employeedatamap.put("Reimbursement", bankreg.getReimapprovedamt());
        sum += bankreg.getReimapprovedamt();
        employeedatalist.add(employeedatamap);
      }

      Set < String > bankkeysets = employeedatalist.get(0).keySet();

      // STYLE

      XSSFFont font = workbook.createFont();
      font.setFontHeightInPoints((short) 11);
      font.setFontName("Times New Roman");
      font.setColor(IndexedColors.BLACK.getIndex());
      font.setBold(false);
      font.setItalic(false);

      CellStyle style = workbook.createCellStyle();
      style.setAlignment(HorizontalAlignment.LEFT);
      style.setVerticalAlignment(VerticalAlignment.TOP);

      // Setting font to style
      style.setFont(font);

      CellStyle style1 = workbook.createCellStyle();
      style1.setAlignment(HorizontalAlignment.LEFT);
      style1.setVerticalAlignment(VerticalAlignment.BOTTOM);
      style1.setIndention((short) 8);
      // Setting font to style
      style1.setFont(font);

      Row row = sheet.createRow(rowNum++);

      Cell bacell = row.createCell(colNum++);
      bacell.setCellValue("Bank Advice No.");
      bacell.setCellStyle(style);

      Cell bacell1 = row.createCell(colNum++);
      bacell1.setCellValue("                / LMRC/ R & A/ 17 18 /");
      bacell1.setCellStyle(style);

      Cell bacell2 = row.createCell(colNum++);
      bacell2.setCellValue("Date");
      bacell2.setCellStyle(style);

      Cell bacell3 = row.createCell(colNum++);
      bacell3.setCellValue(date);
      bacell3.setCellStyle(style);

      row = sheet.createRow(rowNum++);

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell tocell = row.createCell(colNum++);
      tocell.setCellValue("To,  ");
      tocell.setCellStyle(style1);

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell bmcell = row.createCell(colNum++);
      bmcell.setCellValue("BRANCH MANAGER");
      bmcell.setCellStyle(style1);

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell bnkcell = row.createCell(colNum++);
      bnkcell.setCellValue("HDFC BANK");
      bnkcell.setCellStyle(style1);

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell addcell1 = row.createCell(colNum++);
      addcell1.setCellValue("Tekari Chambers ");
      addcell1.setCellStyle(style1);

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell addcell2 = row.createCell(colNum++);
      addcell2.setCellValue("Ashok Marg, Lucknow -01");
      addcell2.setCellStyle(style1);

      row = sheet.createRow(rowNum++);

      row = sheet.createRow(rowNum++);
      colNum = 0;

      Cell regcell = row.createCell(colNum++);
      regcell.setCellValue(
        "REG : PAYMENT ADVICE FROM UTTAR PRADESH METRO RAIL CORPORATION LTD.  A/C No.: 50200009236810");
      regcell.setCellStyle(style);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A9:G9"));

      row = sheet.createRow(rowNum++);
      row = sheet.createRow(rowNum++);

      row = sheet.createRow(rowNum++);
      colNum = 0;

      Cell reqcell = row.createCell(colNum++);
      reqcell.setCellValue(
        "Kindly arrange to pay through NEFT/RTGS and make Bankers Cheque/DD to the following parties and advice as UTR No. in confirmation of Payment.");
      reqcell.setCellStyle(style1);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A12:G12"));

      row = sheet.createRow(rowNum++);

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell totalcell = row.createCell(colNum++);
      ConvertNumToWord cnw = new ConvertNumToWord();
      String numtowords = cnw.convertNumber(sum);

      totalcell.setCellValue("Total Rs. " + numtowords + " Only  (Rs. " + sum + ")");
      totalcell.setCellStyle(style1);

      row = sheet.createRow(rowNum++);

      XSSFFont headingfont = workbook.createFont();
      headingfont.setFontHeightInPoints((short) 11);
      headingfont.setFontName("Calibri");
      headingfont.setColor(IndexedColors.BLACK.getIndex());
      headingfont.setBold(true);
      headingfont.setItalic(false);

      CellStyle headingstyle = workbook.createCellStyle();
      headingstyle.setAlignment(HorizontalAlignment.CENTER);
      headingstyle.setVerticalAlignment(VerticalAlignment.TOP);
      headingstyle.setFillForegroundColor(IndexedColors.AQUA.index);
      headingstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

      headingstyle.setBorderBottom(BorderStyle.THIN);
      headingstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      headingstyle.setBorderTop(BorderStyle.THIN);
      headingstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      headingstyle.setBorderLeft(BorderStyle.THIN);
      headingstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

      // Setting font to style
      headingstyle.setFont(headingfont);

      CellStyle headingstyle1 = workbook.createCellStyle();
      headingstyle1.setAlignment(HorizontalAlignment.CENTER);
      headingstyle1.setVerticalAlignment(VerticalAlignment.TOP);
      headingstyle1.setFillForegroundColor(IndexedColors.WHITE.index);
      headingstyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      headingstyle1.setFont(headingfont);

      XSSFFont datafont = workbook.createFont();
      datafont.setFontHeightInPoints((short) 11);
      datafont.setFontName("Calibri");
      datafont.setColor(IndexedColors.BLACK.getIndex());
      datafont.setBold(false);
      datafont.setItalic(false);

      CellStyle datastyle = workbook.createCellStyle();
      datastyle.setAlignment(HorizontalAlignment.LEFT);
      datastyle.setVerticalAlignment(VerticalAlignment.TOP);
      datastyle.setBorderBottom(BorderStyle.THIN);
      datastyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      datastyle.setBorderTop(BorderStyle.THIN);
      datastyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      datastyle.setBorderLeft(BorderStyle.THIN);
      datastyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

      // Setting font to style
      datastyle.setFont(datafont);

      CellStyle datastyle1 = workbook.createCellStyle();
      datastyle1.setAlignment(HorizontalAlignment.RIGHT);
      datastyle1.setVerticalAlignment(VerticalAlignment.TOP);
      datastyle1.setBorderBottom(BorderStyle.THIN);
      datastyle1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      datastyle1.setBorderTop(BorderStyle.THIN);
      datastyle1.setTopBorderColor(IndexedColors.BLACK.getIndex());
      datastyle1.setBorderLeft(BorderStyle.THIN);
      datastyle1.setLeftBorderColor(IndexedColors.BLACK.getIndex());

      // Setting font to style
      datastyle1.setFont(datafont);

      CellStyle datastyle2 = workbook.createCellStyle();
      datastyle2.setAlignment(HorizontalAlignment.RIGHT);
      datastyle2.setVerticalAlignment(VerticalAlignment.TOP);
      datastyle2.setBorderBottom(BorderStyle.THIN);
      datastyle2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      datastyle2.setBorderTop(BorderStyle.THIN);
      datastyle2.setTopBorderColor(IndexedColors.BLACK.getIndex());
      datastyle2.setBorderLeft(BorderStyle.THIN);
      datastyle2.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      // Setting font to style
      datastyle2.setFont(datafont);

      CellStyle datastyle3 = workbook.createCellStyle();
      datastyle3.setAlignment(HorizontalAlignment.RIGHT);
      datastyle3.setVerticalAlignment(VerticalAlignment.TOP);
      // Setting font to style
      datastyle3.setFont(datafont);

      row = sheet.createRow(rowNum++);
      colNum = 0;

      int i = 0;
      for (String str: bankkeysets) {
        Cell cell = row.createCell(colNum++);
        cell.setCellValue(str);
        cell.setCellStyle(headingstyle);
        if (colNum == 0 || colNum == 1) {
          sheet.setColumnWidth(i, 4500);
        } else {
          sheet.setColumnWidth(i, 7000);
        }
        i++;
      }

      for (Map < String, Object > dt: employeedatalist) {
        row = sheet.createRow(rowNum++);
        colNum = 0;
        bankkeysets = dt.keySet();

        for (String str: bankkeysets) {
          Cell cell = row.createCell(colNum);
          cell.setCellValue(dt.get(str).toString());

          if (colNum == 0) {
            cell.setCellType(CellType.NUMERIC);
            int d = Integer.parseInt(dt.get(str).toString());
            cell.setCellValue(d);
            cell.setCellStyle(datastyle1);
          } else if (colNum == 1) {
            String coldata = dt.get(str).toString();
            //						if(!alphanum.isAlphaNumeric(coldata))
            //						{
            //							int pno = Integer.parseInt(coldata);
            //							cell.setCellType(CellType.NUMERIC);
            //							cell.setCellValue(pno);
            //							cell.setCellStyle(datastyle1);
            //						}
            //						else
            //						{
            //							continue;
            //						}
            if (alphanum.ifAlphaNumeric(coldata)) {
              int pno = Integer.parseInt(coldata);
              //							System.out.println("Person Number ::: "+pno);
              cell.setCellType(CellType.NUMERIC);
              cell.setCellValue(pno);
              cell.setCellStyle(datastyle1);
            } else {
              String pno = coldata;
              //							System.out.println("Person Number ::: "+pno);
              cell.setCellType(CellType.STRING);
              cell.setCellValue(pno);
              cell.setCellStyle(datastyle1);
              //							continue;
            }
          } else if (colNum == 6) {
            cell.setCellType(CellType.NUMERIC);
            int d = Integer.parseInt(dt.get(str).toString());
            cell.setCellValue(d);
            cell.setCellStyle(datastyle2);
            cell.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
          } else {
            cell.setCellType(CellType.STRING);
            cell.setCellValue(dt.get(str).toString());
            cell.setCellStyle(datastyle);
          }

          colNum++;
        }
      }

      row = sheet.createRow(rowNum++);
      colNum = 5;
      Cell gtcell = row.createCell(colNum++);
      gtcell.setCellValue("Grand Total: ");
      gtcell.setCellStyle(headingstyle1);

      Cell sumcell = row.createCell(colNum++);
      int rowNumm = rowNum - 1;
      String formula = "SUM(G17:G" + rowNumm + ")";
      sumcell.setCellFormula(formula);
      // sumcell.setCellValue(sum);
      sumcell.setCellStyle(datastyle3);
      sumcell.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));

      row = sheet.createRow(rowNum++);

      row = sheet.createRow(rowNum++);
      colNum = 0;

      XSSFFont font1 = workbook.createFont();
      font1.setFontHeightInPoints((short) 11);
      font1.setFontName("Times New Roman");
      font1.setColor(IndexedColors.BLACK.getIndex());
      font1.setBold(true);
      font1.setItalic(false);

      CellStyle style2 = workbook.createCellStyle();
      style2.setAlignment(HorizontalAlignment.LEFT);
      style2.setVerticalAlignment(VerticalAlignment.TOP);

      // Setting font to style
      style2.setFont(font1);

      Cell partyCell = row.createCell(colNum++);
      partyCell.setCellValue("Total No. of Parties: - " + count);
      partyCell.setCellStyle(style2);
      partyCell.getCellStyle().setIndention((short) 8);

      row = sheet.createRow(rowNum++);
      colNum = 0;

      Cell partyCell1 = row.createCell(colNum++);
      partyCell1.setCellValue("Total Rs. " + numtowords + " Only  (Rs. " + sum + ")");
      partyCell1.setCellStyle(style2);
      partyCell1.getCellStyle().setIndention((short) 8);
    } else {
      Row row = sheet.createRow(rowNum);
      Cell cell = row.createCell(colNum);
      cell.setCellValue("No Data Found!!");
    }

    workbook.write(stream);
    workbook.close();
  }

  ////////////////////////Employee Salary Bank Transaction Register    //////////////////////////////////////////

  public void WriteEmpSalBankTransactionRegister(BankTypeReportModel data[], String date, String buname,
    String calmonth, ServletOutputStream stream) throws IOException {
    int count = 0;
    int sum = 0;

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Sheet 1");

    sheet.addIgnoredErrors(CellRangeAddress.valueOf("A1:G1048576"), IgnoredErrorType.NUMBER_STORED_AS_TEXT);
    int rowNum = 0;
    int colNum = 0;

    if (data.length != 0) {
      List < BankTypeReportModel > breg = Arrays.asList(data);

      LinkedHashMap < String, Object > employeedatamap = null;
      List < Map < String, Object >> employeedatalist = new ArrayList < > ();

      for (BankTypeReportModel bankreg: breg) {
        count++;
        employeedatamap = new LinkedHashMap < > ();
        employeedatamap.put("Sno#", count);
        employeedatamap.put("Emp#", bankreg.getPersonnumber());
        employeedatamap.put("Beneficiary Name", bankreg.getPersonname());
        employeedatamap.put("Account No", bankreg.getBankaccountnumber());
        employeedatamap.put("IFSC Code", bankreg.getBranchifsccode());
        employeedatamap.put("Bank Name", bankreg.getBankname());
        employeedatamap.put("Net Salary", bankreg.getNetpay());
        sum += bankreg.getNetpay();
        employeedatalist.add(employeedatamap);
      }

      Set < String > bankkeysets = employeedatalist.get(0).keySet();

      // STYLE

      XSSFFont font = workbook.createFont();
      font.setFontHeightInPoints((short) 11);
      font.setFontName("Times New Roman");
      font.setColor(IndexedColors.BLACK.getIndex());
      font.setBold(false);
      font.setItalic(false);

      CellStyle style = workbook.createCellStyle();
      style.setAlignment(HorizontalAlignment.LEFT);
      style.setVerticalAlignment(VerticalAlignment.TOP);

      // Setting font to style
      style.setFont(font);

      CellStyle style1 = workbook.createCellStyle();
      style1.setAlignment(HorizontalAlignment.LEFT);
      style1.setVerticalAlignment(VerticalAlignment.BOTTOM);
      style1.setIndention((short) 8);
      // Setting font to style
      style1.setFont(font);

      Row row = sheet.createRow(rowNum++);

      Cell bacell = row.createCell(colNum++);
      bacell.setCellValue("Bank Advice No.");
      bacell.setCellStyle(style);

      Cell bacell1 = row.createCell(colNum++);
      bacell1.setCellValue("                / LMRC/ R & A/ 17 18 /");
      bacell1.setCellStyle(style);

      Cell bacell2 = row.createCell(colNum++);
      bacell2.setCellValue("Date");
      bacell2.setCellStyle(style);

      Cell bacell3 = row.createCell(colNum++);
      bacell3.setCellValue(date);
      bacell3.setCellStyle(style);

      row = sheet.createRow(rowNum++);

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell tocell = row.createCell(colNum++);
      tocell.setCellValue("To,  ");
      tocell.setCellStyle(style1);

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell bmcell = row.createCell(colNum++);
      bmcell.setCellValue("BRANCH MANAGER");
      bmcell.setCellStyle(style1);

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell bnkcell = row.createCell(colNum++);
      bnkcell.setCellValue("HDFC BANK");
      bnkcell.setCellStyle(style1);

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell addcell1 = row.createCell(colNum++);
      addcell1.setCellValue("Tekari Chambers ");
      addcell1.setCellStyle(style1);

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell addcell2 = row.createCell(colNum++);
      addcell2.setCellValue("Ashok Marg, Lucknow -01");
      addcell2.setCellStyle(style1);

      row = sheet.createRow(rowNum++);

      row = sheet.createRow(rowNum++);
      colNum = 0;

      Cell regcell = row.createCell(colNum++);
      regcell.setCellValue(
        "REG : PAYMENT ADVICE FROM UTTAR PRADESH METRO RAIL CORPORATION LTD.  A/C No.: 50200009236810");
      regcell.setCellStyle(style);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A9:G9"));

      row = sheet.createRow(rowNum++);
      row = sheet.createRow(rowNum++);

      row = sheet.createRow(rowNum++);
      colNum = 0;

      Cell reqcell = row.createCell(colNum++);
      reqcell.setCellValue(
        "Kindly arrange to pay through NEFT/RTGS and make Bankers Cheque/DD to the following parties and advice as UTR No. in confirmation of Payment.");
      reqcell.setCellStyle(style1);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A12:G12"));

      row = sheet.createRow(rowNum++);

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell totalcell = row.createCell(colNum++);
      ConvertNumToWord cnw = new ConvertNumToWord();
      String numtowords = cnw.convertNumber(sum);

      totalcell.setCellValue("Total Rs. " + numtowords + " Only  (Rs. " + sum + ")");
      totalcell.setCellStyle(style1);

      row = sheet.createRow(rowNum++);

      XSSFFont headingfont = workbook.createFont();
      headingfont.setFontHeightInPoints((short) 11);
      headingfont.setFontName("Calibri");
      headingfont.setColor(IndexedColors.BLACK.getIndex());
      headingfont.setBold(true);
      headingfont.setItalic(false);

      CellStyle headingstyle = workbook.createCellStyle();
      headingstyle.setAlignment(HorizontalAlignment.CENTER);
      headingstyle.setVerticalAlignment(VerticalAlignment.TOP);
      headingstyle.setFillForegroundColor(IndexedColors.AQUA.index);
      headingstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

      headingstyle.setBorderBottom(BorderStyle.THIN);
      headingstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      headingstyle.setBorderTop(BorderStyle.THIN);
      headingstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      headingstyle.setBorderLeft(BorderStyle.THIN);
      headingstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

      // Setting font to style
      headingstyle.setFont(headingfont);

      CellStyle headingstyle1 = workbook.createCellStyle();
      headingstyle1.setAlignment(HorizontalAlignment.CENTER);
      headingstyle1.setVerticalAlignment(VerticalAlignment.TOP);
      headingstyle1.setFillForegroundColor(IndexedColors.WHITE.index);
      headingstyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      headingstyle1.setFont(headingfont);

      XSSFFont datafont = workbook.createFont();
      datafont.setFontHeightInPoints((short) 11);
      datafont.setFontName("Calibri");
      datafont.setColor(IndexedColors.BLACK.getIndex());
      datafont.setBold(false);
      datafont.setItalic(false);

      CellStyle datastyle = workbook.createCellStyle();
      datastyle.setAlignment(HorizontalAlignment.LEFT);
      datastyle.setVerticalAlignment(VerticalAlignment.TOP);
      datastyle.setBorderBottom(BorderStyle.THIN);
      datastyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      datastyle.setBorderTop(BorderStyle.THIN);
      datastyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      datastyle.setBorderLeft(BorderStyle.THIN);
      datastyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

      // Setting font to style
      datastyle.setFont(datafont);

      CellStyle datastyle1 = workbook.createCellStyle();
      datastyle1.setAlignment(HorizontalAlignment.RIGHT);
      datastyle1.setVerticalAlignment(VerticalAlignment.TOP);
      datastyle1.setBorderBottom(BorderStyle.THIN);
      datastyle1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      datastyle1.setBorderTop(BorderStyle.THIN);
      datastyle1.setTopBorderColor(IndexedColors.BLACK.getIndex());
      datastyle1.setBorderLeft(BorderStyle.THIN);
      datastyle1.setLeftBorderColor(IndexedColors.BLACK.getIndex());

      // Setting font to style
      datastyle1.setFont(datafont);

      CellStyle datastyle2 = workbook.createCellStyle();
      datastyle2.setAlignment(HorizontalAlignment.RIGHT);
      datastyle2.setVerticalAlignment(VerticalAlignment.TOP);
      datastyle2.setBorderBottom(BorderStyle.THIN);
      datastyle2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      datastyle2.setBorderTop(BorderStyle.THIN);
      datastyle2.setTopBorderColor(IndexedColors.BLACK.getIndex());
      datastyle2.setBorderLeft(BorderStyle.THIN);
      datastyle2.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      // Setting font to style
      datastyle2.setFont(datafont);

      CellStyle datastyle3 = workbook.createCellStyle();
      datastyle3.setAlignment(HorizontalAlignment.RIGHT);
      datastyle3.setVerticalAlignment(VerticalAlignment.TOP);
      // Setting font to style
      datastyle3.setFont(datafont);

      row = sheet.createRow(rowNum++);
      colNum = 0;

      int i = 0;
      for (String str: bankkeysets) {
        Cell cell = row.createCell(colNum++);
        cell.setCellValue(str);
        cell.setCellStyle(headingstyle);
        if (colNum == 0 || colNum == 1) {
          sheet.setColumnWidth(i, 4500);
        } else {
          sheet.setColumnWidth(i, 7000);
        }
        i++;
      }

      for (Map < String, Object > dt: employeedatalist) {
        row = sheet.createRow(rowNum++);
        colNum = 0;
        bankkeysets = dt.keySet();

        for (String str: bankkeysets) {
          Cell cell = row.createCell(colNum);
          cell.setCellValue(dt.get(str).toString());

          if (colNum == 0) {
            cell.setCellType(CellType.NUMERIC);
            int d = Integer.parseInt(dt.get(str).toString());
            cell.setCellValue(d);
            cell.setCellStyle(datastyle1);
          } else if (colNum == 1) {
            String coldata = dt.get(str).toString();
            // if(!alphanum.isAlphaNumeric(coldata))
            // {
            // int pno = Integer.parseInt(coldata);
            // cell.setCellType(CellType.NUMERIC);
            // cell.setCellValue(pno);
            // cell.setCellStyle(datastyle1);
            // }
            // else
            // {
            // continue;
            // }
            if (alphanum.ifAlphaNumeric(coldata)) {
              int pno = Integer.parseInt(coldata);

              cell.setCellType(CellType.NUMERIC);
              cell.setCellValue(pno);
              cell.setCellStyle(datastyle1);
            } else {
              String pno = coldata;

              cell.setCellType(CellType.STRING);
              cell.setCellValue(pno);
              cell.setCellStyle(datastyle1);
              // continue;
            }
          } else if (colNum == 6) {
            cell.setCellType(CellType.NUMERIC);
            int d = Integer.parseInt(dt.get(str).toString());
            cell.setCellValue(d);
            cell.setCellStyle(datastyle2);
            cell.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
          } else {
            cell.setCellType(CellType.STRING);
            cell.setCellValue(dt.get(str).toString());
            cell.setCellStyle(datastyle);
          }

          colNum++;
        }
      }

      row = sheet.createRow(rowNum++);
      colNum = 5;
      Cell gtcell = row.createCell(colNum++);
      gtcell.setCellValue("Grand Total: ");
      gtcell.setCellStyle(headingstyle1);

      Cell sumcell = row.createCell(colNum++);
      int rowNumm = rowNum - 1;
      String formula = "SUM(G17:G" + rowNumm + ")";
      sumcell.setCellFormula(formula);
      // sumcell.setCellValue(sum);
      sumcell.setCellStyle(datastyle3);
      sumcell.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));

      row = sheet.createRow(rowNum++);

      row = sheet.createRow(rowNum++);
      colNum = 0;

      XSSFFont font1 = workbook.createFont();
      font1.setFontHeightInPoints((short) 11);
      font1.setFontName("Times New Roman");
      font1.setColor(IndexedColors.BLACK.getIndex());
      font1.setBold(true);
      font1.setItalic(false);

      CellStyle style2 = workbook.createCellStyle();
      style2.setAlignment(HorizontalAlignment.LEFT);
      style2.setVerticalAlignment(VerticalAlignment.TOP);

      // Setting font to style
      style2.setFont(font1);

      Cell partyCell = row.createCell(colNum++);
      partyCell.setCellValue("Total No. of Parties: - " + count);
      partyCell.setCellStyle(style2);
      partyCell.getCellStyle().setIndention((short) 8);

      row = sheet.createRow(rowNum++);
      colNum = 0;

      Cell partyCell1 = row.createCell(colNum++);
      partyCell1.setCellValue("Total Rs. " + numtowords + " Only  (Rs. " + sum + ")");
      partyCell1.setCellStyle(style2);
      partyCell1.getCellStyle().setIndention((short) 8);
    } else {
      Row row = sheet.createRow(rowNum);
      Cell cell = row.createCell(colNum);
      cell.setCellValue("No Data Found!!");
    }

    workbook.write(stream);
    workbook.close();
  }

  //////////////////////////////////////////// Payroll Register MOD
  //////////////////////////////////////////// //////////////////////////////////////////////////

  public void WritePayrollRegisterMOD(List < LinkedHashMap < String, String >> data, String cmnth, String pmnth, ServletOutputStream stream) throws IOException {

    //		System.out.println("STR   DATA:::::: " + data);
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Sheet 1");
    int rowNum = 0;
    int colNum = 0;
    int count = 1;

    sheet.addIgnoredErrors(CellRangeAddress.valueOf("A1:XFD1048576"), IgnoredErrorType.NUMBER_STORED_AS_TEXT);

    CellStyle earningstyle = workbook.createCellStyle();
    earningstyle.setAlignment(HorizontalAlignment.LEFT);
    earningstyle.setVerticalAlignment(VerticalAlignment.CENTER);
    earningstyle.setFillForegroundColor(IndexedColors.GOLD.index);
    earningstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    earningstyle.setBorderBottom(BorderStyle.THIN);
    earningstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    earningstyle.setBorderTop(BorderStyle.THIN);
    earningstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
    earningstyle.setBorderLeft(BorderStyle.THIN);
    earningstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    earningstyle.setBorderRight(BorderStyle.THIN);
    earningstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

    CellStyle earningstyle1 = workbook.createCellStyle();
    earningstyle1.setAlignment(HorizontalAlignment.LEFT);
    earningstyle1.setVerticalAlignment(VerticalAlignment.CENTER);
    earningstyle1.setBorderBottom(BorderStyle.THIN);
    earningstyle1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    earningstyle1.setBorderTop(BorderStyle.THIN);
    earningstyle1.setTopBorderColor(IndexedColors.BLACK.getIndex());
    earningstyle1.setBorderLeft(BorderStyle.THIN);
    earningstyle1.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    earningstyle1.setBorderRight(BorderStyle.THIN);
    earningstyle1.setRightBorderColor(IndexedColors.BLACK.getIndex());

    CellStyle deductionstyle = workbook.createCellStyle();
    deductionstyle.setAlignment(HorizontalAlignment.LEFT);
    deductionstyle.setVerticalAlignment(VerticalAlignment.CENTER);
    deductionstyle.setFillForegroundColor(IndexedColors.TURQUOISE.index);
    deductionstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    deductionstyle.setBorderBottom(BorderStyle.THIN);
    deductionstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    deductionstyle.setBorderTop(BorderStyle.THIN);
    deductionstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
    deductionstyle.setBorderLeft(BorderStyle.THIN);
    deductionstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    deductionstyle.setBorderRight(BorderStyle.THIN);
    deductionstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

    CellStyle deductionstyle1 = workbook.createCellStyle();
    deductionstyle1.setAlignment(HorizontalAlignment.LEFT);
    deductionstyle1.setVerticalAlignment(VerticalAlignment.CENTER);
    deductionstyle1.setBorderBottom(BorderStyle.THIN);
    deductionstyle1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    deductionstyle1.setBorderTop(BorderStyle.THIN);
    deductionstyle1.setTopBorderColor(IndexedColors.BLACK.getIndex());
    deductionstyle1.setBorderLeft(BorderStyle.THIN);
    deductionstyle1.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    deductionstyle1.setBorderRight(BorderStyle.THIN);
    deductionstyle1.setRightBorderColor(IndexedColors.BLACK.getIndex());

    CellStyle contributionstyle = workbook.createCellStyle();
    contributionstyle.setAlignment(HorizontalAlignment.LEFT);
    contributionstyle.setVerticalAlignment(VerticalAlignment.CENTER);
    contributionstyle.setFillForegroundColor(IndexedColors.LAVENDER.index);
    contributionstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    contributionstyle.setBorderBottom(BorderStyle.THIN);
    contributionstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    contributionstyle.setBorderTop(BorderStyle.THIN);
    contributionstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
    contributionstyle.setBorderLeft(BorderStyle.THIN);
    contributionstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    contributionstyle.setBorderRight(BorderStyle.THIN);
    contributionstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

    CellStyle contributionstyle1 = workbook.createCellStyle();
    contributionstyle1.setAlignment(HorizontalAlignment.LEFT);
    contributionstyle1.setVerticalAlignment(VerticalAlignment.CENTER);
    contributionstyle1.setBorderBottom(BorderStyle.THIN);
    contributionstyle1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    contributionstyle1.setBorderTop(BorderStyle.THIN);
    contributionstyle1.setTopBorderColor(IndexedColors.BLACK.getIndex());
    contributionstyle1.setBorderLeft(BorderStyle.THIN);
    contributionstyle1.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    contributionstyle1.setBorderRight(BorderStyle.THIN);
    contributionstyle1.setRightBorderColor(IndexedColors.BLACK.getIndex());

    XSSFFont headingfont = workbook.createFont();
    headingfont.setFontHeightInPoints((short) 16);
    headingfont.setFontName("Calibri");
    headingfont.setColor(IndexedColors.BLACK.getIndex());
    headingfont.setBold(true);
    headingfont.setItalic(false);

    CellStyle headingstyle = workbook.createCellStyle();
    headingstyle.setAlignment(HorizontalAlignment.LEFT);
    headingstyle.setVerticalAlignment(VerticalAlignment.BOTTOM);
    headingstyle.setBorderBottom(BorderStyle.THIN);
    headingstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    headingstyle.setBorderTop(BorderStyle.THIN);
    headingstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
    headingstyle.setBorderLeft(BorderStyle.THIN);
    headingstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    headingstyle.setBorderRight(BorderStyle.THIN);
    headingstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
    headingstyle.setFont(headingfont);

    CellStyle headingstyle1 = workbook.createCellStyle();
    headingstyle1.setAlignment(HorizontalAlignment.LEFT);
    headingstyle1.setVerticalAlignment(VerticalAlignment.BOTTOM);
    headingstyle1.setFont(headingfont);

    XSSFFont font = workbook.createFont();
    font.setFontHeightInPoints((short) 11);
    font.setFontName("Calibri");
    font.setColor(IndexedColors.BLACK.getIndex());
    font.setBold(true);
    font.setItalic(false);

    CellStyle style = workbook.createCellStyle();
    style.setAlignment(HorizontalAlignment.RIGHT);
    style.setVerticalAlignment(VerticalAlignment.CENTER);
    style.setWrapText(true);
    // Setting font to style
    style.setFont(font);

    XSSFFont dataheadingfont = workbook.createFont();
    dataheadingfont.setFontHeightInPoints((short) 11);
    dataheadingfont.setFontName("Calibri");
    dataheadingfont.setColor(IndexedColors.BLACK.getIndex());
    dataheadingfont.setBold(true);
    dataheadingfont.setItalic(false);

    CellStyle dataheadingstyle1 = workbook.createCellStyle();
    dataheadingstyle1.setAlignment(HorizontalAlignment.LEFT);
    dataheadingstyle1.setVerticalAlignment(VerticalAlignment.TOP);
    dataheadingstyle1.setWrapText(true);
    dataheadingstyle1.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
    dataheadingstyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    dataheadingstyle1.setBorderLeft(BorderStyle.THIN);
    dataheadingstyle1.setLeftBorderColor(IndexedColors.BLACK.index);
    dataheadingstyle1.setBorderTop(BorderStyle.THIN);
    dataheadingstyle1.setTopBorderColor(IndexedColors.BLACK.index);
    dataheadingstyle1.setBorderRight(BorderStyle.THIN);
    dataheadingstyle1.setRightBorderColor(IndexedColors.BLACK.index);
    dataheadingstyle1.setBorderBottom(BorderStyle.THIN);
    dataheadingstyle1.setBottomBorderColor(IndexedColors.BLACK.index);

    // Setting font to style
    dataheadingstyle1.setFont(dataheadingfont);

    CellStyle dataheadingstyle2 = workbook.createCellStyle();
    dataheadingstyle2.setAlignment(HorizontalAlignment.LEFT);
    dataheadingstyle2.setVerticalAlignment(VerticalAlignment.TOP);
    dataheadingstyle2.setWrapText(true);
    dataheadingstyle2.setFillForegroundColor(IndexedColors.GOLD.index);
    dataheadingstyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    dataheadingstyle2.setBorderLeft(BorderStyle.THIN);
    dataheadingstyle2.setLeftBorderColor(IndexedColors.BLACK.index);
    dataheadingstyle2.setBorderTop(BorderStyle.THIN);
    dataheadingstyle2.setTopBorderColor(IndexedColors.BLACK.index);
    dataheadingstyle2.setBorderRight(BorderStyle.THIN);
    dataheadingstyle2.setRightBorderColor(IndexedColors.BLACK.index);
    dataheadingstyle2.setBorderBottom(BorderStyle.THIN);
    dataheadingstyle2.setBottomBorderColor(IndexedColors.BLACK.index);

    // Setting font to style
    dataheadingstyle2.setFont(dataheadingfont);

    CellStyle datastyle3 = workbook.createCellStyle();
    datastyle3.setAlignment(HorizontalAlignment.LEFT);
    datastyle3.setVerticalAlignment(VerticalAlignment.TOP);
    datastyle3.setWrapText(true);
    datastyle3.setFillForegroundColor(IndexedColors.TURQUOISE.index);
    datastyle3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    datastyle3.setBorderLeft(BorderStyle.THIN);
    datastyle3.setLeftBorderColor(IndexedColors.BLACK.index);
    datastyle3.setBorderTop(BorderStyle.THIN);
    datastyle3.setTopBorderColor(IndexedColors.BLACK.index);
    datastyle3.setBorderRight(BorderStyle.THIN);
    datastyle3.setRightBorderColor(IndexedColors.BLACK.index);
    datastyle3.setBorderBottom(BorderStyle.THIN);
    datastyle3.setBottomBorderColor(IndexedColors.BLACK.index);

    // Setting font to style
    datastyle3.setFont(dataheadingfont);

    CellStyle dataheadingstyle4 = workbook.createCellStyle();
    dataheadingstyle4.setAlignment(HorizontalAlignment.LEFT);
    dataheadingstyle4.setVerticalAlignment(VerticalAlignment.TOP);
    dataheadingstyle4.setWrapText(true);
    dataheadingstyle4.setFillForegroundColor(IndexedColors.LAVENDER.index);
    dataheadingstyle4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    dataheadingstyle4.setBorderLeft(BorderStyle.THIN);
    dataheadingstyle4.setLeftBorderColor(IndexedColors.BLACK.index);
    dataheadingstyle4.setBorderTop(BorderStyle.THIN);
    dataheadingstyle4.setTopBorderColor(IndexedColors.BLACK.index);
    dataheadingstyle4.setBorderRight(BorderStyle.THIN);
    dataheadingstyle4.setRightBorderColor(IndexedColors.BLACK.index);
    dataheadingstyle4.setBorderBottom(BorderStyle.THIN);
    dataheadingstyle4.setBottomBorderColor(IndexedColors.BLACK.index);

    // Setting font to style
    dataheadingstyle4.setFont(dataheadingfont);

    XSSFFont datafont = workbook.createFont();
    datafont.setFontHeightInPoints((short) 11);
    datafont.setFontName("Calibri");
    datafont.setColor(IndexedColors.BLACK.getIndex());
    datafont.setBold(false);
    datafont.setItalic(false);

    CellStyle datastyle4 = workbook.createCellStyle();
    datastyle4.setAlignment(HorizontalAlignment.LEFT);
    datastyle4.setVerticalAlignment(VerticalAlignment.CENTER);
    datastyle4.setWrapText(true);
    datastyle4.setBorderLeft(BorderStyle.THIN);
    datastyle4.setLeftBorderColor(IndexedColors.BLACK.index);
    datastyle4.setBorderTop(BorderStyle.THIN);
    datastyle4.setTopBorderColor(IndexedColors.BLACK.index);
    datastyle4.setBorderRight(BorderStyle.THIN);
    datastyle4.setRightBorderColor(IndexedColors.BLACK.index);
    datastyle4.setBorderBottom(BorderStyle.THIN);
    datastyle4.setBottomBorderColor(IndexedColors.BLACK.index);

    // Setting font to style
    datastyle4.setFont(datafont);

    CellStyle datastyle4right = workbook.createCellStyle();
    datastyle4right.setAlignment(HorizontalAlignment.RIGHT);
    datastyle4right.setVerticalAlignment(VerticalAlignment.CENTER);
    datastyle4right.setWrapText(true);
    datastyle4right.setBorderLeft(BorderStyle.THIN);
    datastyle4right.setLeftBorderColor(IndexedColors.BLACK.index);
    datastyle4right.setBorderTop(BorderStyle.THIN);
    datastyle4right.setTopBorderColor(IndexedColors.BLACK.index);
    datastyle4right.setBorderRight(BorderStyle.THIN);
    datastyle4right.setRightBorderColor(IndexedColors.BLACK.index);
    datastyle4right.setBorderBottom(BorderStyle.THIN);
    datastyle4right.setBottomBorderColor(IndexedColors.BLACK.index);

    // Setting font to style
    datastyle4right.setFont(datafont);

    CellStyle datastyleearn = workbook.createCellStyle();
    datastyleearn.setAlignment(HorizontalAlignment.RIGHT);
    datastyleearn.setVerticalAlignment(VerticalAlignment.CENTER);
    datastyleearn.setWrapText(true);
    datastyleearn.setBorderLeft(BorderStyle.THIN);
    datastyleearn.setLeftBorderColor(IndexedColors.BLACK.index);
    datastyleearn.setBorderTop(BorderStyle.THIN);
    datastyleearn.setTopBorderColor(IndexedColors.BLACK.index);
    datastyleearn.setBorderRight(BorderStyle.THIN);
    datastyleearn.setRightBorderColor(IndexedColors.BLACK.index);
    datastyleearn.setBorderBottom(BorderStyle.THIN);
    datastyleearn.setBottomBorderColor(IndexedColors.BLACK.index);
    datastyleearn.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));

    // Setting font to style
    datastyleearn.setFont(datafont);

    CellStyle datastyleded = workbook.createCellStyle();
    datastyleded.setAlignment(HorizontalAlignment.RIGHT);
    datastyleded.setVerticalAlignment(VerticalAlignment.CENTER);
    datastyleded.setWrapText(true);
    datastyleded.setBorderLeft(BorderStyle.THIN);
    datastyleded.setLeftBorderColor(IndexedColors.BLACK.index);
    datastyleded.setBorderTop(BorderStyle.THIN);
    datastyleded.setTopBorderColor(IndexedColors.BLACK.index);
    datastyleded.setBorderRight(BorderStyle.THIN);
    datastyleded.setRightBorderColor(IndexedColors.BLACK.index);
    datastyleded.setBorderBottom(BorderStyle.THIN);
    datastyleded.setBottomBorderColor(IndexedColors.BLACK.index);
    datastyleded.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));

    // Setting font to style
    datastyleded.setFont(datafont);

    CellStyle datastylecont = workbook.createCellStyle();
    datastylecont.setAlignment(HorizontalAlignment.RIGHT);
    datastylecont.setVerticalAlignment(VerticalAlignment.CENTER);
    datastylecont.setWrapText(true);
    datastylecont.setBorderLeft(BorderStyle.THIN);
    datastylecont.setLeftBorderColor(IndexedColors.BLACK.index);
    datastylecont.setBorderTop(BorderStyle.THIN);
    datastylecont.setTopBorderColor(IndexedColors.BLACK.index);
    datastylecont.setBorderRight(BorderStyle.THIN);
    datastylecont.setRightBorderColor(IndexedColors.BLACK.index);
    datastylecont.setBorderBottom(BorderStyle.THIN);
    datastylecont.setBottomBorderColor(IndexedColors.BLACK.index);
    datastylecont.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
    // Setting font to style
    datastylecont.setFont(datafont);

    Row row = sheet.createRow(rowNum++);
    colNum = 0;
    Cell dedstylecell = row.createCell(colNum++);
    dedstylecell.setCellStyle(deductionstyle);
    Cell ded = row.createCell(colNum++);
    ded.setCellValue("Deductions");
    ded.setCellStyle(deductionstyle1);
    sheet.setColumnWidth(1, 5500);

    Cell headingcell = row.createCell(colNum++);
    String heading = "Memorandum of Difference's " + cmnth.substring(cmnth.indexOf("-") + 1) + " Payroll Register - Employee + Earning Level";
    headingcell.setCellValue(heading);
    headingcell.setCellStyle(headingstyle);
    sheet.addMergedRegion(CellRangeAddress.valueOf("C1:K3"));
    sheet.setColumnWidth(3, 5000);

    row = sheet.createRow(rowNum++);
    colNum = 0;
    Cell earnstylecell = row.createCell(colNum++);
    earnstylecell.setCellStyle(earningstyle);
    Cell earn = row.createCell(colNum++);
    earn.setCellValue("Earnings");
    earn.setCellStyle(earningstyle1);
    sheet.setColumnWidth(1, 5500);

    //Cell blankcell = row.createCell(colNum++);		

    row = sheet.createRow(rowNum++);
    colNum = 0;
    Cell contristylecell = row.createCell(colNum++);
    contristylecell.setCellStyle(contributionstyle);
    Cell contri = row.createCell(colNum++);
    contri.setCellValue("Contributions");
    contri.setCellStyle(contributionstyle1);
    sheet.setColumnWidth(1, 5500);

    row = sheet.createRow(rowNum++);

    String currmnth = cmnth.substring(cmnth.indexOf("-") + 1);
    String premnth = pmnth.substring(pmnth.indexOf("-") + 1);

    row = sheet.createRow(rowNum++);
    colNum = 0;

    Cell headingcell1 = row.createCell(colNum++);
    headingcell1.setCellValue("Current Month");
    headingcell1.setCellStyle(style);
    sheet.addMergedRegion(CellRangeAddress.valueOf("A5:B5"));

    colNum = 2;
    Cell headingcell2 = row.createCell(colNum++);
    headingcell2.setCellValue(currmnth);
    headingcell2.setCellStyle(headingstyle1);
    sheet.setColumnWidth(2, 5000);

    colNum = 4;
    Cell headingcell3 = row.createCell(colNum++);
    headingcell3.setCellValue("Previous Month");
    headingcell3.setCellStyle(style);
    sheet.setColumnWidth(4, 8500);

    Cell headingcell4 = row.createCell(colNum++);
    headingcell4.setCellValue(premnth);
    headingcell4.setCellStyle(headingstyle1);
    sheet.setColumnWidth(2, 5000);

    colNum = 7;
    Cell headingcell5 = row.createCell(colNum++);
    headingcell5.setCellValue("Report For");
    headingcell5.setCellStyle(style);

    Cell headingcell6 = row.createCell(colNum++);
    headingcell6.setCellValue("All Employees");
    headingcell6.setCellStyle(headingstyle1);

    row = sheet.createRow(rowNum++);
    colNum = 0;

    Cell headingcell8 = row.createCell(colNum++);
    headingcell8.setCellValue("Current Mth Payroll Run");
    headingcell8.setCellStyle(style);
    sheet.addMergedRegion(CellRangeAddress.valueOf("A6:B6"));

    colNum = 2;
    Cell headingcell9 = row.createCell(colNum++);
    headingcell9.setCellValue(currmnth.concat(" Calendar Month"));
    headingcell9.setCellStyle(headingstyle1);
    sheet.addMergedRegion(CellRangeAddress.valueOf("C6:D6"));

    colNum = 4;
    Cell headingcell10 = row.createCell(colNum++);
    headingcell10.setCellValue("Prev Mth Payroll Run");
    headingcell10.setCellStyle(style);

    Cell headingcell11 = row.createCell(colNum++);
    headingcell11.setCellValue(premnth.concat(" Calendar Month"));
    headingcell11.setCellStyle(headingstyle1);
    sheet.addMergedRegion(CellRangeAddress.valueOf("F6:G6"));

    colNum = 7;
    Cell headingcell12 = row.createCell(colNum++);
    headingcell12.setCellValue("Report Type");
    headingcell12.setCellStyle(style);

    Cell headingcell13 = row.createCell(colNum++);
    headingcell13.setCellValue("By Employee");
    headingcell13.setCellStyle(headingstyle1);

    sheet.setColumnWidth(5, 5000);
    sheet.setColumnWidth(6, 5000);
    sheet.setColumnWidth(7, 5000);
    sheet.setColumnWidth(8, 5000);

    row = sheet.createRow(rowNum++);

    Set < String > employeekeysets = null;
    Cell cell;
    if (data.size() != 0) {
      Set < String > allkeysets = data.get(0).keySet();
      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell snocell = row.createCell(colNum++);
      snocell.setCellValue("S. No".toUpperCase());
      snocell.setCellStyle(dataheadingstyle1);
      int i = 0;

      for (String str: allkeysets) {
        if (str.equals("Person Number")) {
          //colNum = 1;
          cell = row.createCell(colNum++);
          cell.setCellValue("Emp".toUpperCase().concat("ID"));
          cell.setCellStyle(dataheadingstyle1);
        } else if (str.equals("Name")) {
          //colNum = 2;
          cell = row.createCell(colNum++);
          cell.setCellValue("Emp Name".toUpperCase());
          cell.setCellStyle(dataheadingstyle1);
        } else if (str.equals("Department")) {
          //colNum = 3;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle1);
        } else if (str.equals("Attendance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 4;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle1);
        } else if (str.equals("Attendance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 5;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle1);
        } else if (str.equals("Basic Salary_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 6;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Basic Salary_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 7;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 8;
          cell = row.createCell(colNum++);
          cell.setCellValue("Basic Pay-Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_Basic Salary_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 9;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_Basic Salary_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 10;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 11;
          cell = row.createCell(colNum++);
          cell.setCellValue("Basic Arr-Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Grade Pay_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 12;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Grade Pay_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 13;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 14;
          cell = row.createCell(colNum++);
          cell.setCellValue("Grade Pay-Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Personal Pay_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 15;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Personal Pay_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 16;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 17;
          cell = row.createCell(colNum++);
          cell.setCellValue("Personal Pay-Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Special Pay_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 18;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Special Pay_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 19;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 20;
          cell = row.createCell(colNum++);
          cell.setCellValue("Special Pay-Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Dearness Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 21;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Dearness Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 22;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 23;
          cell = row.createCell(colNum++);
          cell.setCellValue("Dearness Allowance-Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_Dearness Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 24;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_Dearness Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 25;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 26;
          cell = row.createCell(colNum++);
          cell.setCellValue("ARR_Dearness Allowance Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("House Rent Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 27;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("House Rent Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 28;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 29;
          cell = row.createCell(colNum++);
          cell.setCellValue("House Rent Allowance Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_House Rent Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 30;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_House Rent Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 31;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 32;
          cell = row.createCell(colNum++);
          cell.setCellValue("ARR_House Rent Allowance Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Deputation Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 33;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Deputation Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 34;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 35;
          cell = row.createCell(colNum++);
          cell.setCellValue("Deputation Allowance Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_Deputation Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 36;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_Deputation Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 37;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 38;
          cell = row.createCell(colNum++);
          cell.setCellValue("ARR_Deputation Allowance Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("National Holiday Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 39;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("National Holiday Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 40;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 41;
          cell = row.createCell(colNum++);
          cell.setCellValue("National Holiday Allowance Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_National Holiday Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 42;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_National Holiday Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 43;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 44;
          cell = row.createCell(colNum++);
          cell.setCellValue("ARR_National Holiday Allowance Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Cafeteria Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 45;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Cafeteria Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 46;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 47;
          cell = row.createCell(colNum++);
          cell.setCellValue("Cafeteria Allowance Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_Cafeteria Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 48;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_Cafeteria Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 49;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 50;
          cell = row.createCell(colNum++);
          cell.setCellValue("ARR_Cafeteria Allowance Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Transport Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 51;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Transport Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 52;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 53;
          cell = row.createCell(colNum++);
          cell.setCellValue("Transport Allowance Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_Transport Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 54;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_Transport Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 55;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 56;
          cell = row.createCell(colNum++);
          cell.setCellValue("ARR_Transport Allowance Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Medical Benefit_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 57;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Medical Benefit_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 58;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 59;
          cell = row.createCell(colNum++);
          cell.setCellValue("Medical Benefit Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_Medical Benefit_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 60;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_Medical Benefit_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 61;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 62;
          cell = row.createCell(colNum++);
          cell.setCellValue("ARR_Medical Benefit Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Honorarium Pay_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 63;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Honorarium Pay_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 64;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 65;
          cell = row.createCell(colNum++);
          cell.setCellValue("Honorarium Pay Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_Honorarium Pay_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 66;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_Honorarium Pay_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 67;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 68;
          cell = row.createCell(colNum++);
          cell.setCellValue("ARR_Honorarium Pay Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Family Planning Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 69;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Family Planning Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 70;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 71;
          cell = row.createCell(colNum++);
          cell.setCellValue("Family Planning Allowance Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Consultant Fee_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 72;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Consultant Fee_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 73;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 74;
          cell = row.createCell(colNum++);
          cell.setCellValue("Consultant Fee Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_Consultant Fee_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 75;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("ARR_Consultant Fee_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 76;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 77;
          cell = row.createCell(colNum++);
          cell.setCellValue("ARR_Consultant Fee Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Miscellaneous Payment_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 78;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Miscellaneous Payment_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 79;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 80;
          cell = row.createCell(colNum++);
          cell.setCellValue("Miscellaneous Payment Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Leave Encashment_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 81;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Leave Encashment_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 82;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 83;
          cell = row.createCell(colNum++);
          cell.setCellValue("Leave Encashment Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Quarter Payment_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 39;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Quarter Payment_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 40;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 41;
          cell = row.createCell(colNum++);
          cell.setCellValue("Quarter Payment Difference".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Total Earning_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 84;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Total Earning_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 85;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle2);

          //colNum = 86;
          cell = row.createCell(colNum++);
          cell.setCellValue("Total Earning Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle2);
        } else if (str.equals("Empl SPF Contribution_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 87;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Empl SPF Contribution_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 88;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);

          //colNum = 89;
          cell = row.createCell(colNum++);
          cell.setCellValue("Empl SPF Contribution Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Empl SNPS Contribution_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 90;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Empl SNPS Contribution_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 91;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);

          //colNum = 92;
          cell = row.createCell(colNum++);
          cell.setCellValue("Empl SNPS Contribution Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Empl GPF Contribution_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 93;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Empl GPF Contribution_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 94;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);

          //colNum = 95;
          cell = row.createCell(colNum++);
          cell.setCellValue("Empl GPF Contribution Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Electricity Bill Deduction_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 96;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Electricity Bill Deduction_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 97;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);

          //colNum = 98;
          cell = row.createCell(colNum++);
          cell.setCellValue("Electricity Bill Deduction Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Electricity Charge_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 99;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Electricity Charge_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 100;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);

          //colNum = 101;
          cell = row.createCell(colNum++);
          cell.setCellValue("Electricity Charge Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Lease Recovery_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 102;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Lease Recovery_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 103;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);

          //colNum = 104;
          cell = row.createCell(colNum++);
          cell.setCellValue("Lease Recovery Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Loan Advance Recovery_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 105;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Loan Advance Recovery_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 106;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);

          //colNum = 107;
          cell = row.createCell(colNum++);
          cell.setCellValue("Loan Advance Recovery Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Veh Excess Use Recv_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 108;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Veh Excess Use Recv_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 109;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);

          //colNum = 110;
          cell = row.createCell(colNum++);
          cell.setCellValue("Veh Excess Use Recv Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Vehicle Recv_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 111;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Vehicle Recv_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 112;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);

          //colNum = 113;
          cell = row.createCell(colNum++);
          cell.setCellValue("Vehicle Recv Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Miscellaneous Recovery_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 114;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Miscellaneous Recovery_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 115;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);

          //colNum = 116;
          cell = row.createCell(colNum++);
          cell.setCellValue("Miscellaneous Recovery Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Arrear GPF_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 117;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Arrear GPF_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 118;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);

          //colNum = 119;
          cell = row.createCell(colNum++);
          cell.setCellValue("Arrear GPF Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
        } else if (str.equals("Quarter Rent Recovery_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 120;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Quarter Rent Recovery_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 121;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);

          //colNum = 122;
          cell = row.createCell(colNum++);
          cell.setCellValue("Quarter Rent Recovery Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("HRA Recovery_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 123;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("HRA Recovery_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 124;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);

          //colNum = 125;
          cell = row.createCell(colNum++);
          cell.setCellValue("HRA Recovery Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Income Tax_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4)))) || str.equals("TDS_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 126;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Income Tax_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4)))) || str.equals("TDS_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 127;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);

          //colNum = 128;
          cell = row.createCell(colNum++);
          cell.setCellValue("Income Tax/TDS Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Empl Vol PF Contribution_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 129;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Empl Vol PF Contribution_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 130;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);

          //colNum = 131;
          cell = row.createCell(colNum++);
          cell.setCellValue("Empl Vol PF Contribution Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Empl GIS Cont_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 132;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Empl GIS Cont_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 133;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);

          //colNum = 134;
          cell = row.createCell(colNum++);
          cell.setCellValue("Empl GIS Cont Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Empl GSLI Contribution_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 135;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Empl GSLI Contribution_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 136;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);

          //colNum = 137;
          cell = row.createCell(colNum++);
          cell.setCellValue("Empl GSLI Contribution Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Food Recovery_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 138;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Food Recovery_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 139;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);

          //colNum = 140;
          cell = row.createCell(colNum++);
          cell.setCellValue("Food Recovery Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("LMRC Vehicle Advance Recovery_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 141;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("LMRC Vehicle Advance Recovery_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 142;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);

          //colNum = 143;
          cell = row.createCell(colNum++);
          cell.setCellValue("LMRC Vehicle Advance Recovery Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Total Deduction_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 144;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Total Deduction_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 145;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);

          //colNum = 146;
          cell = row.createCell(colNum++);
          cell.setCellValue("Total Deduction Diff".toUpperCase());
          cell.setCellStyle(datastyle3);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Empr PF Cont_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 147;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Empr PF Cont_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 148;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);

          //colNum = 149;
          cell = row.createCell(colNum++);
          cell.setCellValue("Empr PF Cont Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Empr EPS Cont_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 150;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Empr EPS Cont_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 151;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);

          //colNum = 152;
          cell = row.createCell(colNum++);
          cell.setCellValue("Empr EPS Cont Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Empr NPS Cont_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 153;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Empr NPS Cont_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 154;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);

          //colNum = 155;
          cell = row.createCell(colNum++);
          cell.setCellValue("Empr NPS Cont Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Pension Cont_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 156;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Pension Cont_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 157;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);

          //colNum = 158;
          cell = row.createCell(colNum++);
          cell.setCellValue("Pension Cont Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Empr GSLI Cont_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 159;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Empr GSLI Cont_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 160;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);

          //colNum = 161;
          cell = row.createCell(colNum++);
          cell.setCellValue("Empr GSLI Cont Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Empr Leave Salary Cont_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 162;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Empr Leave Salary Cont_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 163;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);

          //colNum = 164;
          cell = row.createCell(colNum++);
          cell.setCellValue("Empr Leave Salary Cont Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Total Contribution_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
          //colNum = 165;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);
        } else if (str.equals("Total Contribution_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
          //colNum = 167;
          cell = row.createCell(colNum++);
          cell.setCellValue(str.toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);

          //colNum = 167;
          cell = row.createCell(colNum++);
          cell.setCellValue("Total Contribution Diff".toUpperCase());
          cell.setCellStyle(dataheadingstyle4);
          sheet.setColumnWidth(colNum, 5000);
        }

        //				else
        //				{
        //					
        //					cell= row.createCell(++colNum);
        //					cell.setCellValue(str.toUpperCase());
        //					cell.setCellStyle(dataheadingstyle2);
        //				}
        i++;
        sheet.setColumnWidth(i, 5000);

      }

      //System.out.println("All Keysets Size :::::"+allkeysets.size());
      Cell datacell;
      double prevamt = 0, curamt = 0, diffamt = 0;
      for (Map < String, String > dt: data) {

        row = sheet.createRow(rowNum++);
        colNum = 0;

        Cell sncell = row.createCell(colNum++);
        sncell.setCellValue(count++);
        sncell.setCellStyle(datastyle4right);

        employeekeysets = dt.keySet();

        for (String str: allkeysets) {
          if (str.equals("Person Number")) {
            //colNum = 1;
            datacell = row.createCell(colNum++);
            String coldata = dt.get(str).toString();
            if (alphanum.ifAlphaNumeric(coldata)) {
              int pno = Integer.parseInt(coldata);
              //							System.out.println("Person Number ::: "+pno);
              datacell.setCellType(CellType.NUMERIC);
              datacell.setCellValue(pno);
              datacell.setCellStyle(datastyle4right);
            } else {
              String pno = coldata;
              //							System.out.println("Person Number ::: "+pno);
              datacell.setCellType(CellType.STRING);
              datacell.setCellValue(pno);
              datacell.setCellStyle(datastyle4right);
            }

          } else if (str.equals("Name")) {
            //colNum = 2;
            datacell = row.createCell(colNum++);
            datacell.setCellValue(dt.get(str).toString());
            //						datacell.setCellValue("Emp Name".toUpperCase());
            datacell.setCellStyle(datastyle4);
          } else if (str.equals("Department")) {
            //colNum = 3;
            datacell = row.createCell(colNum++);
            datacell.setCellValue(dt.get(str).toString());
            //						datacell.setCellValue(str.toUpperCase());
            datacell.setCellStyle(datastyle4);
          } else if (str.equals("Attendance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 4;
            datacell = row.createCell(colNum++);
            String coldata = dt.get(str);
            if (alphanum.ifAlphaNumeric(coldata)) {
              int pno = Integer.parseInt(coldata);
              //							System.out.println("Person Number ::: "+pno);
              datacell.setCellType(CellType.NUMERIC);
              datacell.setCellValue(pno);
              datacell.setCellStyle(datastyle4right);
            } else {
              String pno = coldata;
              //							System.out.println("Person Number ::: "+pno);
              datacell.setCellType(CellType.STRING);
              datacell.setCellValue(pno);
              datacell.setCellStyle(datastyle4right);
            }
            //						System.out.println("curr Att :"+dt.get(str));
            //						datacell.setCellValue(dt.get(str));
            ////					datacell.setCellValue(str.toUpperCase());
            //						datacell.setCellStyle(datastyle4right);
          } else if (str.equals("Attendance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 5;
            datacell = row.createCell(colNum++);
            String coldata = dt.get(str);
            if (alphanum.ifAlphaNumeric(coldata)) {
              int pno = Integer.parseInt(coldata);
              //							System.out.println("Person Number ::: "+pno);
              datacell.setCellType(CellType.NUMERIC);
              datacell.setCellValue(pno);
              datacell.setCellStyle(datastyle4right);
            } else {
              String pno = coldata;
              //							System.out.println("Person Number ::: "+pno);
              datacell.setCellType(CellType.STRING);
              datacell.setCellValue(pno);
              datacell.setCellStyle(datastyle4right);
            }
            //System.out.println("prev Att :"+dt.get(str));
            //						datacell.setCellValue(dt.get(str));
            //						datacell.setCellValue(str.toUpperCase());
            //						datacell.setCellStyle(datastyle4right);
          } else if (str.equals("Basic Salary_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 6;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Basic Salary_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 7;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 8;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_Basic Salary_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 9;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_Basic Salary_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 10;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 11;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Grade Pay_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 12;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Grade Pay_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 13;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 14;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Personal Pay_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 15;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Personal Pay_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 16;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 17;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Special Pay_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 18;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Special Pay_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 19;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 20;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Dearness Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 21;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Dearness Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 22;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 23;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_Dearness Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 24;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_Dearness Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 25;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 26;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("House Rent Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 27;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("House Rent Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 28;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 29;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            diffamt = curamt - prevamt;
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_House Rent Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 30;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_House Rent Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 31;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 32;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Deputation Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 33;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Deputation Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 34;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 35;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_Deputation Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 36;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_Deputation Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 37;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 38;
            datacell = row.createCell(colNum++);
            diffamt = curamt - diffamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("National Holiday Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 39;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("National Holiday Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 40;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 41;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_National Holiday Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 42;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_National Holiday Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 43;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 44;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Cafeteria Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 45;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Cafeteria Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 46;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 47;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_Cafeteria Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 48;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_Cafeteria Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 49;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 50;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Transport Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 51;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Transport Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 52;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 53;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_Transport Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 54;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_Transport Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 55;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 56;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Medical Benefit_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 57;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Medical Benefit_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 58;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 59;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_Medical Benefit_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 60;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_Medical Benefit_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 61;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 62;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Honorarium Pay_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 63;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Honorarium Pay_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 64;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 65;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_Honorarium Pay_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 66;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_Honorarium Pay_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 67;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 68;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Family Planning Allowance_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 69;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Family Planning Allowance_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 70;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 71;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Consultant Fee_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 72;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Consultant Fee_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 73;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 74;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_Consultant Fee_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 75;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("ARR_Consultant Fee_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 76;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 77;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Miscellaneous Payment_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 78;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Miscellaneous Payment_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 79;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 80;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Leave Encashment_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 81;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Leave Encashment_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 82;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 83;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Quarter Payment_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 81;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Quarter Payment_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 82;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 83;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Total Earning_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 84;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Total Earning_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 85;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleearn);

            //colNum = 86;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleearn);
          } else if (str.equals("Empl SPF Contribution_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 87;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Empl SPF Contribution_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 88;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 89;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Empl SNPS Contribution_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 90;

            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Empl SNPS Contribution_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 91;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 92;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Empl GPF Contribution_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 93;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Empl GPF Contribution_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 94;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 95;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Electricity Bill Deduction_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 96;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Electricity Bill Deduction_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 97;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 98;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Electricity Charge_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 99;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Electricity Charge_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 100;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 101;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Lease Recovery_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 102;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Lease Recovery_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 103;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 104;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Loan Advance Recovery_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 105;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Loan Advance Recovery_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 106;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 107;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Veh Excess Use Recv_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 108;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Veh Excess Use Recv_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 109;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 110;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Vehicle Recv_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 111;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Vehicle Recv_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 112;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 113;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Miscellaneous Recovery_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 114;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Miscellaneous Recovery_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 115;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 116;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Arrear GPF_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 117;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Arrear GPF_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 118;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 119;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);
          } else if (str.equals("Quarter Rent Recovery_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 120;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

          } else if (str.equals("Quarter Rent Recovery_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 121;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 122;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);

          } else if (str.equals("HRA Recovery_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 123;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

          } else if (str.equals("HRA Recovery_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 124;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 125;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);

          } else if (str.equals("Income Tax_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4)))) || str.equals("TDS_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 126;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

          } else if (str.equals("Income Tax_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4)))) || str.equals("TDS_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 127;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 128;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);

          } else if (str.equals("Empl Vol PF Contribution_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 129;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

          } else if (str.equals("Empl Vol PF Contribution_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 130;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 131;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);

          } else if (str.equals("Empl GIS Cont_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 132;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

          } else if (str.equals("Empl GIS Cont_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 133;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 134;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);

          } else if (str.equals("Empl GSLI Contribution_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 135;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

          } else if (str.equals("Empl GSLI Contribution_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 136;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 137;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);

          } else if (str.equals("Food Recovery_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 138;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

          } else if (str.equals("Food Recovery_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 139;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 140;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);

          } else if (str.equals("LMRC Vehicle Advance Recovery_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 141;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

          } else if (str.equals("LMRC Vehicle Advance Recovery_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 142;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 143;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);

          } else if (str.equals("Total Deduction_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 144;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

          } else if (str.equals("Total Deduction_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 145;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastyleded);

            //colNum = 146;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastyleded);

          } else if (str.equals("Empr PF Cont_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 147;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastylecont);

          } else if (str.equals("Empr PF Cont_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 148;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastylecont);

            //colNum = 149;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastylecont);

          } else if (str.equals("Empr EPS Cont_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 150;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastylecont);

          } else if (str.equals("Empr EPS Cont_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 151;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastylecont);

            //colNum = 152;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastylecont);

          } else if (str.equals("Empr NPS Cont_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 153;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastylecont);

          } else if (str.equals("Empr NPS Cont_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 154;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastylecont);

            //colNum = 155;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastylecont);
          } else if (str.equals("Pension Cont_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 156;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastylecont);

          } else if (str.equals("Pension Cont_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 157;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastylecont);

            //colNum = 158;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastylecont);

          } else if (str.equals("Empr GSLI Cont_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 159;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastylecont);

          } else if (str.equals("Empr GSLI Cont_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 160;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastylecont);

            //colNum = 161;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastylecont);

          } else if (str.equals("Empr Leave Salary Cont_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 162;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastylecont);

          } else if (str.equals("Empr Leave Salary Cont_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 163;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastylecont);

            //colNum = 164;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastylecont);

          } else if (str.equals("Total Contribution_".concat(currmnth.substring(0, 3).concat("_").concat(currmnth.substring(4))))) {
            //colNum = 165;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            curamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastylecont);

          } else if (str.equals("Total Contribution_".concat(premnth.substring(0, 3).concat("_").concat(premnth.substring(4))))) {
            //colNum = 166;
            datacell = row.createCell(colNum++);
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            prevamt = Double.parseDouble(dt.get(str).toString());
            datacell.setCellStyle(datastylecont);

            //colNum = 167;
            datacell = row.createCell(colNum++);
            diffamt = curamt - prevamt;
            datacell.setCellType(CellType.NUMERIC);
            datacell.setCellValue(diffamt);
            datacell.setCellStyle(datastylecont);

          }
        }
      }
    }

    workbook.write(stream);
    workbook.close();
  }

  ////////////////////////// EPF REPORT
  ////////////////////////// ///////////////////////////////////////////////////

  public void WriteEPFReport(EPFModel data[], String buname, String calmonth, ServletOutputStream stream)
  throws IOException {

    calmonth = calmonth.substring(calmonth.indexOf("-") + 1);

    int count = 0;
    double totbasda = 0, totarrbasda = 0, totwages = 0, volpf = 0;
    double dedbasda12 = 0.0, arrdedbasda12 = 0.0, adminchg = 0.0, tothl = 0.0;

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Sheet 1");
    sheet.addIgnoredErrors(CellRangeAddress.valueOf("A1:XFD1048576"), IgnoredErrorType.NUMBER_STORED_AS_TEXT);
    int rowNum = 0;
    int colNum = 0;

    //		

    if (data.length != 0) {
      List < EPFModel > preg = Arrays.asList(data);

      LinkedHashMap < String, Object > employeedatamap = null;
      List < Map < String, Object >> employeedatalist = new ArrayList < > ();

      for (EPFModel epfregister: preg) {
        count++;
        totbasda = 0;
        totarrbasda = 0;
        dedbasda12 = 0;
        arrdedbasda12 = 0;
        volpf = 0;
        totwages = 0;
        adminchg = 0;
        tothl = 0;
        employeedatamap = new LinkedHashMap < > ();
        employeedatamap.put("Sr. No.", count);
        employeedatamap.put("EMP ID", epfregister.getPersonnumber());
        employeedatamap.put("Name of the Employee", epfregister.getPersonname());
        employeedatamap.put("Emp Type", epfregister.getNatureofempl());
        employeedatamap.put("Post", epfregister.getPosition());
        employeedatamap.put("Days", epfregister.getWorkingday());
        employeedatamap.put("Gross Salary", epfregister.getGrosssalary_c1());
        employeedatamap.put("Basic+G.P", epfregister.getBasicandfp_d());
        employeedatamap.put("Basic Arrear", epfregister.getArrbasic_d1());
        employeedatamap.put("DA  @ 7.3%", epfregister.getDearnessallowance_e());
        employeedatamap.put("DA Arrear", epfregister.getArrdearnessallowance_e1());
        employeedatamap.put("Total", epfregister.getTotal_f());
        employeedatamap.put("Arrear Payment (Basic+DA)", epfregister.getArrearpayment_g());
        employeedatamap.put("Employee Deductions @12 % of Basic+DA", epfregister.getEmployeededuction_h());
        employeedatamap.put("Employee Deductions @12 % of Basic+DA (Arrear)", epfregister.getEmployeededuction_i());
        employeedatamap.put("Employee Voluntary PF Contribution", epfregister.getEmployeevpf_i1());
        employeedatamap.put("Contr. Of Employer @12% of Basic+DA", epfregister.getControfemployee_j());
        employeedatamap.put("Contr. Of Employer @12% of Basic+DA on Arrear Payment", epfregister.getControfemployee_k());
        employeedatamap.put("Admin Charges @ 1% of Total wages", epfregister.getAdmincharge_l());
        employeedatamap.put("Total_", epfregister.getTotal_m());

        employeedatalist.add(employeedatamap);
      }

      Set < String > payregkeysets = employeedatalist.get(0).keySet();

      // ***** Style For Heading Row ****//
      XSSFFont font = workbook.createFont();
      font.setFontHeightInPoints((short) 16);
      font.setFontName("Calibri");
      font.setColor(IndexedColors.BLACK.getIndex());
      font.setBold(true);
      font.setUnderline(HSSFFont.U_SINGLE);
      font.setItalic(false);

      CellStyle style = workbook.createCellStyle();
      style.setAlignment(HorizontalAlignment.CENTER);
      style.setVerticalAlignment(VerticalAlignment.CENTER);
      style.setBorderTop(BorderStyle.THIN);
      style.setTopBorderColor(IndexedColors.BLACK.getIndex());
      style.setBorderBottom(BorderStyle.THIN);
      style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      style.setBorderLeft(BorderStyle.THIN);
      style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      style.setBorderRight(BorderStyle.THIN);
      style.setRightBorderColor(IndexedColors.BLACK.getIndex());
      // Setting font to style
      style.setFont(font);

      // heading row 1
      Row row = sheet.createRow(rowNum++);
      Cell headingCell = row.createCell(colNum++);
      headingCell.setCellValue("Uttar Pradesh Metro Rail Corporation Limited");
      sheet.addMergedRegion(CellRangeAddress.valueOf("A1:T1"));
      headingCell.setCellStyle(style);

      // heading row 2
      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell headingCell2 = row.createCell(colNum++);
      headingCell2.setCellValue(
        "Deductions and Contributions of EPF from salary of UPMRC officers for the month of " + calmonth);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A2:T2"));
      headingCell2.setCellStyle(style);

      // ***** Style For Data Heading Row ****//
      XSSFFont dataheadingfont = workbook.createFont();
      dataheadingfont.setFontHeightInPoints((short) 8);
      dataheadingfont.setFontName("Calibri");
      dataheadingfont.setColor(IndexedColors.BLACK.getIndex());
      dataheadingfont.setBold(true);
      // dataheadingfont.setUnderline(HSSFFont.U_SINGLE);
      dataheadingfont.setItalic(false);

      CellStyle dataheadingstyle = workbook.createCellStyle();
      dataheadingstyle.setWrapText(true);
      dataheadingstyle.setAlignment(HorizontalAlignment.CENTER);
      dataheadingstyle.setVerticalAlignment(VerticalAlignment.CENTER);
      dataheadingstyle.setBorderTop(BorderStyle.THIN);
      dataheadingstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      dataheadingstyle.setBorderBottom(BorderStyle.THIN);
      dataheadingstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      dataheadingstyle.setBorderLeft(BorderStyle.THIN);
      dataheadingstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      dataheadingstyle.setBorderRight(BorderStyle.THIN);
      dataheadingstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
      // Setting font to style
      dataheadingstyle.setFont(dataheadingfont);

      // formula Headings

      // colheading style

      XSSFFont colfont = workbook.createFont();
      colfont.setFontHeightInPoints((short) 8);
      colfont.setFontName("Calibri");
      colfont.setColor(IndexedColors.BLACK.getIndex());
      colfont.setBold(true);
      colfont.setUnderline(HSSFFont.U_SINGLE);
      colfont.setItalic(false);

      CellStyle colstyle = workbook.createCellStyle();
      colstyle.setAlignment(HorizontalAlignment.CENTER);
      colstyle.setVerticalAlignment(VerticalAlignment.CENTER);
      colstyle.setBorderTop(BorderStyle.THIN);
      colstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      colstyle.setBorderBottom(BorderStyle.THIN);
      colstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      colstyle.setBorderLeft(BorderStyle.THIN);
      colstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      colstyle.setBorderRight(BorderStyle.THIN);
      colstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
      // Setting font to style
      colstyle.setFont(colfont);

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell fcell1 = row.createCell(colNum++);
      fcell1.setCellValue("A");
      fcell1.setCellStyle(colstyle);

      Cell fcell2 = row.createCell(colNum++);
      fcell2.setCellValue("");
      fcell2.setCellStyle(colstyle);

      Cell fcell3 = row.createCell(colNum++);
      fcell3.setCellValue("B");
      fcell3.setCellStyle(colstyle);

      Cell fcell4 = row.createCell(colNum++);
      fcell4.setCellValue("");
      fcell4.setCellStyle(colstyle);

      Cell fcell5 = row.createCell(colNum++);
      fcell5.setCellValue("C");
      fcell5.setCellStyle(colstyle);

      Cell fcell6 = row.createCell(colNum++);
      fcell6.setCellValue("");
      fcell6.setCellStyle(colstyle);

      Cell fcell7 = row.createCell(colNum++);
      fcell7.setCellValue("c1");
      fcell7.setCellStyle(colstyle);

      Cell fcell8 = row.createCell(colNum++);
      fcell8.setCellValue("D");
      fcell8.setCellStyle(colstyle);

      Cell fcell9 = row.createCell(colNum++);
      fcell9.setCellValue("D.1");
      fcell9.setCellStyle(colstyle);

      Cell fcell10 = row.createCell(colNum++);
      fcell10.setCellValue("E");
      fcell10.setCellStyle(colstyle);

      Cell fcell11 = row.createCell(colNum++);
      fcell11.setCellValue("E.1");
      fcell11.setCellStyle(colstyle);

      Cell fcell12 = row.createCell(colNum++);
      fcell12.setCellValue("F = D + E");
      fcell12.setCellStyle(colstyle);

      Cell fcell13 = row.createCell(colNum++);
      fcell13.setCellValue("G=D.1+E.1"); //
      fcell13.setCellStyle(colstyle);

      Cell fcell14 = row.createCell(colNum++);
      fcell14.setCellValue("H = F * 12%");
      fcell14.setCellStyle(colstyle);

      Cell fcell15 = row.createCell(colNum++);
      fcell15.setCellValue("I =  G * 12%");
      fcell15.setCellStyle(colstyle);

      Cell fcell16 = row.createCell(colNum++);
      fcell16.setCellValue("i(1)");
      fcell16.setCellStyle(colstyle);

      Cell fcell17 = row.createCell(colNum++);
      fcell17.setCellValue("J = F * 12%");
      fcell17.setCellStyle(colstyle);

      Cell fcell18 = row.createCell(colNum++);
      fcell18.setCellValue("K  = G*12%");
      fcell18.setCellStyle(colstyle);

      Cell fcell19 = row.createCell(colNum++);
      fcell19.setCellValue("L = (F+G) * 1%");
      fcell19.setCellStyle(colstyle);

      Cell fcell20 = row.createCell(colNum++);
      fcell20.setCellValue("M = (H TO L)");
      fcell20.setCellStyle(colstyle);

      // Main Headings
      row = sheet.createRow(rowNum++);
      row.setHeight((short) 1500);
      colNum = 0;
      int i = 0;
      for (String str: payregkeysets) {
        i++;
        Cell cell = row.createCell(colNum++);
        cell.setCellValue(str);
        cell.setCellStyle(dataheadingstyle);
        if (colNum == 0 || colNum == 1 || colNum == 3 || colNum == 5) {
          sheet.setColumnWidth(i, 1500);
        } else if (colNum == 2) {
          sheet.setColumnWidth(i, 4500);
        } else {
          sheet.setColumnWidth(i, 2800);
        }
      }

      // ***** Style For Data Row ****//
      XSSFFont datafont = workbook.createFont();
      datafont.setFontHeightInPoints((short) 11);
      datafont.setFontName("Calibri");
      datafont.setColor(IndexedColors.BLACK.getIndex());
      datafont.setBold(false);
      // datafont.setUnderline(HSSFFont.U_SINGLE);
      datafont.setItalic(false);

      CellStyle datastyle = workbook.createCellStyle();
      // datastyle.setWrapText(true);
      datastyle.setAlignment(HorizontalAlignment.LEFT);
      datastyle.setVerticalAlignment(VerticalAlignment.CENTER);
      datastyle.setBorderTop(BorderStyle.THIN);
      datastyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      datastyle.setBorderBottom(BorderStyle.THIN);
      datastyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      datastyle.setBorderLeft(BorderStyle.THIN);
      datastyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      datastyle.setBorderRight(BorderStyle.THIN);
      datastyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
      // Setting font to style
      datastyle.setFont(datafont);

      CellStyle datastyleright = workbook.createCellStyle();
      // datastyle.setWrapText(true);
      datastyleright.setAlignment(HorizontalAlignment.RIGHT);
      datastyleright.setVerticalAlignment(VerticalAlignment.CENTER);
      datastyleright.setBorderTop(BorderStyle.THIN);
      datastyleright.setTopBorderColor(IndexedColors.BLACK.getIndex());
      datastyleright.setBorderBottom(BorderStyle.THIN);
      datastyleright.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      datastyleright.setBorderLeft(BorderStyle.THIN);
      datastyleright.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      datastyleright.setBorderRight(BorderStyle.THIN);
      datastyleright.setRightBorderColor(IndexedColors.BLACK.getIndex());
      // Setting font to style
      datastyleright.setFont(datafont);

      CellStyle datastylerightformat = workbook.createCellStyle();
      // datastyle.setWrapText(true);
      datastylerightformat.setAlignment(HorizontalAlignment.RIGHT);
      datastylerightformat.setVerticalAlignment(VerticalAlignment.CENTER);
      datastylerightformat.setBorderTop(BorderStyle.THIN);
      datastylerightformat.setTopBorderColor(IndexedColors.BLACK.getIndex());
      datastylerightformat.setBorderBottom(BorderStyle.THIN);
      datastylerightformat.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      datastylerightformat.setBorderLeft(BorderStyle.THIN);
      datastylerightformat.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      datastylerightformat.setBorderRight(BorderStyle.THIN);
      datastylerightformat.setRightBorderColor(IndexedColors.BLACK.getIndex());
      datastylerightformat.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
      // Setting font to style
      datastylerightformat.setFont(datafont);

      for (Map < String, Object > dt: employeedatalist) {
        row = sheet.createRow(rowNum++);

        colNum = 0;
        payregkeysets = dt.keySet();
        for (String str: payregkeysets) {
          Cell cell = row.createCell(colNum);
          if (colNum == 0 || colNum == 5) {
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            cell.setCellStyle(datastyleright);
          } else if (colNum == 1) {
            String coldata = dt.get(str).toString();
            // System.out.println("COLDATA ::: "+coldata );
            //						if(!alphanum.isAlphaNumeric(coldata))
            //						{
            //							int pno = Integer.parseInt(coldata);
            //							//System.out.println("Person Number ::: "+pno);
            //							cell.setCellType(CellType.NUMERIC);
            //							cell.setCellValue(pno);
            //							cell.setCellStyle(datastyleright);
            //						}
            //						else
            //						{
            //							continue;
            //						}

            if (alphanum.ifAlphaNumeric(coldata)) {
              int pno = Integer.parseInt(coldata);
              //							System.out.println("Person Number ::: "+pno);
              cell.setCellType(CellType.NUMERIC);
              cell.setCellValue(pno);
              cell.setCellStyle(datastyleright);
            } else {
              String pno = coldata;
              //							System.out.println("Person Number ::: "+pno);
              cell.setCellType(CellType.STRING);
              cell.setCellValue(pno);
              cell.setCellStyle(datastyleright);
              //							continue;
            }
          } else if (colNum == 2 || colNum == 3 || colNum == 4) {
            cell.setCellType(CellType.STRING);
            cell.setCellValue(dt.get(str).toString());
            cell.setCellStyle(datastyle);
          } else {
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            cell.setCellStyle(datastylerightformat);
          }
          colNum++;
        }
      }

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell totalcell = row.createCell(colNum++);
      totalcell.setCellValue("Total");
      totalcell.setCellStyle(datastyleright);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A" + rowNum + ":F" + rowNum));

      colNum = 6;
      int rowNumm = rowNum - 1;

      Cell totcell2 = row.createCell(colNum++);
      totcell2.setCellFormula("SUM(G5:G" + rowNumm + ")");
      totcell2.setCellStyle(datastylerightformat);

      Cell totcell3 = row.createCell(colNum++);
      totcell3.setCellFormula("SUM(H5:H" + rowNumm + ")");
      totcell3.setCellStyle(datastylerightformat);

      Cell totcell4 = row.createCell(colNum++);
      totcell4.setCellFormula("SUM(I5:I" + rowNumm + ")");
      totcell4.setCellStyle(datastylerightformat);

      Cell totcell5 = row.createCell(colNum++);
      totcell5.setCellFormula("SUM(J5:J" + rowNumm + ")");
      totcell5.setCellStyle(datastylerightformat);

      Cell totcell6 = row.createCell(colNum++);
      totcell6.setCellFormula("SUM(K5:K" + rowNumm + ")");
      totcell6.setCellStyle(datastylerightformat);

      Cell totcell7 = row.createCell(colNum++);
      totcell7.setCellFormula("SUM(L5:L" + rowNumm + ")");
      totcell7.setCellStyle(datastylerightformat);

      Cell totcell8 = row.createCell(colNum++);
      totcell8.setCellFormula("SUM(M5:M" + rowNumm + ")");

      Cell totcell9 = row.createCell(colNum++);
      totcell9.setCellFormula("SUM(N5:N" + rowNumm + ")");
      totcell9.setCellStyle(datastylerightformat);

      Cell totcell10 = row.createCell(colNum++);
      totcell10.setCellFormula("SUM(O5:O" + rowNumm + ")");
      totcell10.setCellStyle(datastylerightformat);

      Cell totcell11 = row.createCell(colNum++);
      totcell11.setCellFormula("SUM(P5:P" + rowNumm + ")");
      totcell11.setCellStyle(datastylerightformat);

      Cell totcell12 = row.createCell(colNum++);
      totcell12.setCellFormula("SUM(Q5:Q" + rowNumm + ")");
      totcell12.setCellStyle(datastylerightformat);

      Cell totcell13 = row.createCell(colNum++);
      totcell13.setCellFormula("SUM(R5:R" + rowNumm + ")");
      totcell13.setCellStyle(datastylerightformat);

      Cell totcell14 = row.createCell(colNum++);
      totcell14.setCellFormula("SUM(S5:S" + rowNumm + ")");
      totcell14.setCellStyle(datastylerightformat);

      Cell totcell15 = row.createCell(colNum++);
      totcell15.setCellFormula("SUM(T5:T" + rowNumm + ")");
      totcell15.setCellStyle(datastylerightformat);
    } else {
      Row row = sheet.createRow(rowNum);
      Cell cell = row.createCell(colNum);
      cell.setCellValue("No Data Found!!!");
    }

    workbook.write(stream);
    workbook.close();
  }

  /* EPF REPORT ENDS on 25/05/2021 */

  ///////////////////////////////// EMPLOYEE MOD REGISTER
  ///////////////////////////////// //////////////////////////////////
  public void WriteEmpModRegister(List < LinkedHashMap < String, String >> data, String busname, String current_month, String calper_current, String previous_month, String calper_previous, ServletOutputStream stream)
  throws IOException {
    double curr_total_earning = 0;
    double curr_total_deduction = 0;
    double curr_netpay = 0;
    double curr_inctax = 0;
    double curr_total_contri = 0;
    double pre_total_earning = 0;
    double pre_total_deduction = 0;
    double pre_netpay = 0;
    double pre_inctax = 0;
    double pre_total_contri = 0;
    double diff_total_earning = 0;
    double chper_earning = 0;
    String chper_earning1 = "";
    double diff_total_deduction = 0;
    double chper_deduction = 0;
    String chper_deduction1 = "";
    double diff_netpay = 0;
    double chper_netpay = 0;
    String chper_netpay1 = "";
    double diff_inctax = 0;
    double chper_inctax = 0;
    String chper_inctax1 = "";
    double diff_total_contri = 0;
    double chper_contri = 0;
    String chper_contri1 = "";
    String cur_cal = current_month.substring(4);
    String pre_cal = previous_month.substring(4);
    int count = 0;

    int curr = 0, pre = 0, diff = 0;

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Sheet 1");
    sheet.addIgnoredErrors(CellRangeAddress.valueOf("A1:XFD1048576"), IgnoredErrorType.NUMBER_STORED_AS_TEXT);
    //		sheet.setColumnWidth(0, 18);

    int rowNum = 0;
    int colNum = 0;

    XSSFFont headingfont = workbook.createFont();
    headingfont.setFontHeightInPoints((short) 16);
    headingfont.setFontName("Calibri");
    headingfont.setColor(IndexedColors.BLACK.getIndex());
    headingfont.setBold(true);

    CellStyle headingstyle = workbook.createCellStyle();
    headingstyle.setAlignment(HorizontalAlignment.CENTER);
    headingstyle.setVerticalAlignment(VerticalAlignment.CENTER);
    headingstyle.setFont(headingfont);

    Row headingrow = sheet.createRow(rowNum++);
    Cell headingcell = headingrow.createCell(colNum++);
    headingcell.setCellValue("Memorandum of Differerences " + cur_cal + ": Payroll Register - Employee Level (" + busname + ")");
    headingcell.setCellStyle(headingstyle);
    sheet.addMergedRegion(CellRangeAddress.valueOf("A1:L1"));

    Row blankrow = sheet.createRow(rowNum++);

    Row headingRow1 = sheet.createRow(rowNum++);
    colNum = 0;

    XSSFFont headingfont1 = workbook.createFont();
    headingfont1.setFontHeightInPoints((short) 14);
    headingfont1.setFontName("Calibri");
    headingfont1.setColor(IndexedColors.BLACK.getIndex());
    headingfont1.setBold(true);

    CellStyle headingstyle1 = workbook.createCellStyle();
    headingstyle1.setAlignment(HorizontalAlignment.LEFT);
    headingstyle1.setVerticalAlignment(VerticalAlignment.CENTER);
    headingstyle1.setFont(headingfont1);

    Cell headingcell1 = headingRow1.createCell(colNum++);
    headingcell1.setCellValue("Current Month : " + cur_cal);
    headingcell1.setCellStyle(headingstyle1);
    sheet.addMergedRegion(CellRangeAddress.valueOf("A3:D3"));

    colNum = 4;
    Cell headingcell2 = headingRow1.createCell(colNum++);
    headingcell2.setCellValue("Previous Month : " + pre_cal);
    headingcell2.setCellStyle(headingstyle1);
    sheet.addMergedRegion(CellRangeAddress.valueOf("E3:H3"));

    colNum = 8;
    Cell headingcell3 = headingRow1.createCell(colNum++);
    headingcell3.setCellValue("Report For : All Employees");
    headingcell3.setCellStyle(headingstyle1);
    sheet.addMergedRegion(CellRangeAddress.valueOf("I3:L3"));

    Row headingRow2 = sheet.createRow(rowNum++);
    colNum = 0;

    Cell headingcell4 = headingRow2.createCell(colNum++);
    headingcell4.setCellValue("Current Mth Payroll Run : " + calper_current + " " + cur_cal);
    headingcell4.setCellStyle(headingstyle1);
    sheet.addMergedRegion(CellRangeAddress.valueOf("A4:D4"));

    colNum = 4;
    Cell headingcell5 = headingRow2.createCell(colNum++);
    headingcell5.setCellValue("Previous Mth Payroll Run : " + calper_previous + " " + pre_cal);
    headingcell5.setCellStyle(headingstyle1);
    sheet.addMergedRegion(CellRangeAddress.valueOf("E4:H4"));

    colNum = 8;
    Cell headingcell6 = headingRow2.createCell(colNum++);
    headingcell6.setCellValue("Report Type : Summary");
    headingcell6.setCellStyle(headingstyle1);
    sheet.addMergedRegion(CellRangeAddress.valueOf("I4:L4"));

    Row blankrow1 = sheet.createRow(rowNum++);

    Row headingRow3 = sheet.createRow(rowNum++);
    colNum = 0;

    XSSFFont headingfont2 = workbook.createFont();
    headingfont2.setFontHeightInPoints((short) 14);
    headingfont2.setFontName("Calibri");
    headingfont2.setColor(IndexedColors.BLACK.getIndex());
    headingfont2.setBold(true);

    CellStyle headingstyle2 = workbook.createCellStyle();
    headingstyle2.setAlignment(HorizontalAlignment.CENTER);
    headingstyle2.setVerticalAlignment(VerticalAlignment.CENTER);
    headingstyle2.setFont(headingfont2);

    Cell headingcell7 = headingRow3.createCell(colNum++);
    headingcell7.setCellValue(" ");
    sheet.addMergedRegion(CellRangeAddress.valueOf("A6:D6"));

    colNum = 4;
    Cell headingcell8 = headingRow3.createCell(colNum++);
    headingcell8.setCellValue("Current Month ");
    headingcell8.setCellStyle(headingstyle2);
    sheet.addMergedRegion(CellRangeAddress.valueOf("E6:J6"));

    colNum = 10;
    Cell headingcell9 = headingRow3.createCell(colNum++);
    headingcell9.setCellValue("Previous Month");
    headingcell9.setCellStyle(headingstyle2);
    sheet.addMergedRegion(CellRangeAddress.valueOf("K6:P6"));

    colNum = 16;
    Cell headingcell10 = headingRow3.createCell(colNum++);
    headingcell10.setCellValue("Difference [Current - Prev Month]");
    headingcell10.setCellStyle(headingstyle2);
    sheet.addMergedRegion(CellRangeAddress.valueOf("Q6:AA6"));

    Set < String > employeekeysets = null;

    XSSFFont dataheadingfont = workbook.createFont();
    dataheadingfont.setFontHeightInPoints((short) 12);
    dataheadingfont.setFontName("Calibri");
    dataheadingfont.setColor(IndexedColors.BLACK.getIndex());    
    dataheadingfont.setBold(true);

    CellStyle dataheadingstyle = workbook.createCellStyle();
    dataheadingstyle.setWrapText(true);
    dataheadingstyle.setAlignment(HorizontalAlignment.CENTER);
    dataheadingstyle.setVerticalAlignment(VerticalAlignment.CENTER);
    dataheadingstyle.setBorderLeft(BorderStyle.THIN);
    dataheadingstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    dataheadingstyle.setBorderTop(BorderStyle.THIN);
    dataheadingstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
    dataheadingstyle.setBorderRight(BorderStyle.THIN);
    dataheadingstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
    dataheadingstyle.setBorderBottom(BorderStyle.THIN);
    dataheadingstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    dataheadingstyle.setFont(dataheadingfont);

    XSSFFont datafont = workbook.createFont();
    datafont.setFontHeightInPoints((short) 12);
    datafont.setFontName("Calibri");
    datafont.setColor(IndexedColors.BLACK.getIndex());

    CellStyle datastyle = workbook.createCellStyle();
    datastyle.setAlignment(HorizontalAlignment.LEFT);
    datastyle.setVerticalAlignment(VerticalAlignment.CENTER);
    datastyle.setBorderLeft(BorderStyle.THIN);
    datastyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    datastyle.setBorderTop(BorderStyle.THIN);
    datastyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
    datastyle.setBorderRight(BorderStyle.THIN);
    datastyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
    datastyle.setBorderBottom(BorderStyle.THIN);
    datastyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    datastyle.setFont(datafont);

    CellStyle datastyle1 = workbook.createCellStyle();
    datastyle1.setAlignment(HorizontalAlignment.RIGHT);
    datastyle1.setVerticalAlignment(VerticalAlignment.CENTER);
    datastyle1.setBorderLeft(BorderStyle.THIN);
    datastyle1.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    datastyle1.setBorderTop(BorderStyle.THIN);
    datastyle1.setTopBorderColor(IndexedColors.BLACK.getIndex());
    datastyle1.setBorderRight(BorderStyle.THIN);
    datastyle1.setRightBorderColor(IndexedColors.BLACK.getIndex());
    datastyle1.setBorderBottom(BorderStyle.THIN);
    datastyle1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    datastyle1.setFont(datafont);

    CellStyle datastyleformat = workbook.createCellStyle();
    datastyleformat.setAlignment(HorizontalAlignment.RIGHT);
    datastyleformat.setVerticalAlignment(VerticalAlignment.CENTER);
    datastyleformat.setBorderLeft(BorderStyle.THIN);
    datastyleformat.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    datastyleformat.setBorderTop(BorderStyle.THIN);
    datastyleformat.setTopBorderColor(IndexedColors.BLACK.getIndex());
    datastyleformat.setBorderRight(BorderStyle.THIN);
    datastyleformat.setRightBorderColor(IndexedColors.BLACK.getIndex());
    datastyleformat.setBorderBottom(BorderStyle.THIN);
    datastyleformat.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    datastyleformat.setFont(datafont);
    datastyleformat.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));

    if (data.size() != 0) {
      Set < String > allkeysets = data.get(0).keySet();
      Row row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell snocell = row.createCell(colNum++);
      snocell.setCellValue("S. No.");
      sheet.autoSizeColumn(0);
      snocell.setCellStyle(dataheadingstyle);

      int i = 0;
      Cell cell;
      for (String str: allkeysets) {
        if (str.equals("Person Number")) {
          colNum = 1;
          cell = row.createCell(colNum++);
          cell.setCellValue("Emp ".concat("ID"));
          cell.setCellStyle(dataheadingstyle);
        } else if (str.equals("Name")) {
          colNum = 2;
          
          cell = row.createCell(colNum++);
          cell.setCellValue("Emp Name");
          cell.setCellStyle(dataheadingstyle);
        } else if (str.equals("Department")) {
          colNum = 3;
          sheet.autoSizeColumn(colNum);
          cell = row.createCell(colNum++);
          cell.setCellValue(str);
          cell.setCellStyle(dataheadingstyle);
        } else if (str.equals("Attendance_".concat(cur_cal.substring(0, 3).concat("_").concat(cur_cal.substring(4))))) {
          colNum = 4;
          sheet.autoSizeColumn(colNum);
          cell = row.createCell(colNum++);
          cell.setCellValue("Attendance Days");
          cell.setCellStyle(dataheadingstyle);
        } else if (str.equals("Total Earning_".concat(cur_cal.substring(0, 3).concat("_").concat(cur_cal.substring(4))))) {
          colNum = 5;
          cell = row.createCell(colNum++);
          cell.setCellValue("Total Earnings");
          cell.setCellStyle(dataheadingstyle);
        } else if (str.equals("Total Deduction_".concat(cur_cal.substring(0, 3).concat("_").concat(cur_cal.substring(4))))) {
          colNum = 6;
          cell = row.createCell(colNum++);
          cell.setCellValue("Total Deductions");
          cell.setCellStyle(dataheadingstyle);
        } else if (str.equals("Net Pay_".concat(cur_cal.substring(0, 3).concat("_").concat(cur_cal.substring(4))))) {
          colNum = 7;
          cell = row.createCell(colNum++);
          cell.setCellValue("Net Pay");
          cell.setCellStyle(dataheadingstyle);
        } else if (str.equals("Income Tax_".concat(cur_cal.substring(0, 3).concat("_").concat(cur_cal.substring(4))))) {
          colNum = 8;
          cell = row.createCell(colNum++);
          cell.setCellValue("Income Tax Deduction");
          cell.setCellStyle(dataheadingstyle);
        } else if (str.equals("Total Contribution_".concat(cur_cal.substring(0, 3).concat("_").concat(cur_cal.substring(4))))) {
          colNum = 9;
          cell = row.createCell(colNum++);
          cell.setCellValue("Total Contributions");
          cell.setCellStyle(dataheadingstyle);
        } else if (str.equals("Attendance_".concat(pre_cal.substring(0, 3).concat("_").concat(pre_cal.substring(4))))) {
          colNum = 10;
          cell = row.createCell(colNum++);
          cell.setCellValue("Attendance Days");
          cell.setCellStyle(dataheadingstyle);
        } else if (str.equals("Total Earning_".concat(pre_cal.substring(0, 3).concat("_").concat(pre_cal.substring(4))))) {
          colNum = 11;
          cell = row.createCell(colNum++);
          cell.setCellValue("Total Earnings");
          cell.setCellStyle(dataheadingstyle);
        } else if (str.equals("Total Deduction_".concat(pre_cal.substring(0, 3).concat("_").concat(pre_cal.substring(4))))) {
          colNum = 12;
          cell = row.createCell(colNum++);
          cell.setCellValue("Total Deductions");
          cell.setCellStyle(dataheadingstyle);
        } else if (str.equals("Net Pay_".concat(pre_cal.substring(0, 3).concat("_").concat(pre_cal.substring(4))))) {
          colNum = 13;
          cell = row.createCell(colNum++);
          cell.setCellValue("Net Pay");
          cell.setCellStyle(dataheadingstyle);
        } else if (str.equals("Income Tax_".concat(pre_cal.substring(0, 3).concat("_").concat(pre_cal.substring(4))))) {
          colNum = 14;
          cell = row.createCell(colNum++);
          cell.setCellValue("Income Tax Deduction");
          cell.setCellStyle(dataheadingstyle);
        } else if (str.equals("Total Contribution_".concat(pre_cal.substring(0, 3).concat("_").concat(pre_cal.substring(4))))) {
          colNum = 15;
          cell = row.createCell(colNum++);
          cell.setCellValue("Total Contributions");
          cell.setCellStyle(dataheadingstyle);
        }
        
        for(int j=1;j<allkeysets.size();j++)
        {
        	sheet.autoSizeColumn(j);        	
        }
      }
      colNum = 16;
      cell = row.createCell(colNum++);
      cell.setCellValue("Attendance Days");
      sheet.autoSizeColumn(16); 
      cell.setCellStyle(dataheadingstyle);

      colNum = 17;
      cell = row.createCell(colNum++);
      cell.setCellValue("Total Earnings");
      sheet.autoSizeColumn(17); 
      cell.setCellStyle(dataheadingstyle);

      colNum = 18;
      cell = row.createCell(colNum++);
      cell.setCellValue("% Change");
      sheet.autoSizeColumn(18); 
      cell.setCellStyle(dataheadingstyle);

      colNum = 19;
      cell = row.createCell(colNum++);
      cell.setCellValue("Total Deductions");
      sheet.autoSizeColumn(19); 
      cell.setCellStyle(dataheadingstyle);

      colNum = 20;
      cell = row.createCell(colNum++);
      cell.setCellValue("% Change");
      sheet.autoSizeColumn(20); 
      cell.setCellStyle(dataheadingstyle);

      colNum = 21;
      cell = row.createCell(colNum++);
      cell.setCellValue("Net Pay");
      sheet.autoSizeColumn(21); 
      cell.setCellStyle(dataheadingstyle);

      colNum = 22;
      cell = row.createCell(colNum++);
      cell.setCellValue("% Change");
      sheet.autoSizeColumn(22); 
      cell.setCellStyle(dataheadingstyle);

      colNum = 23;
      cell = row.createCell(colNum++);
      cell.setCellValue("Income Tax Deductions");
      sheet.autoSizeColumn(23); 
      cell.setCellStyle(dataheadingstyle);

      colNum = 24;
      cell = row.createCell(colNum++);
      cell.setCellValue("% Change");
      sheet.autoSizeColumn(24); 
      cell.setCellStyle(dataheadingstyle);

      colNum = 25;
      cell = row.createCell(colNum++);
      cell.setCellValue("Total Contributions");
      sheet.autoSizeColumn(25); 
      cell.setCellStyle(dataheadingstyle);

      colNum = 26;
      cell = row.createCell(colNum++);
      cell.setCellValue("% Change");
      sheet.autoSizeColumn(26); 
      cell.setCellStyle(dataheadingstyle);

      Row datarow;
      colNum = 0;

      for (Map < String, String > dt: data) {
        datarow = sheet.createRow(rowNum++);
        colNum = 0;
        cell = datarow.createCell(colNum++);
        cell.setCellType(CellType.NUMERIC);
        cell.setCellValue(++count);
        cell.setCellStyle(datastyle1);
        sheet.autoSizeColumn(0); 
        for (String str: allkeysets) {
          if (str.equals("Person Number")) {
            colNum = 1;
            cell = datarow.createCell(colNum++);
            String coldata = dt.get(str).toString();

            if (alphanum.ifAlphaNumeric(coldata)) {
              int pno = Integer.parseInt(coldata);
              cell.setCellType(CellType.NUMERIC);
              cell.setCellStyle(datastyle1);
              cell.setCellValue(pno);
              sheet.autoSizeColumn(1); 
            } else {
              String pno = coldata;
              cell.setCellType(CellType.STRING);
              cell.setCellStyle(datastyle);
              cell.setCellValue(pno);
              sheet.autoSizeColumn(1); 
            }
          } else if (str.equals("Name")) {
            colNum = 2;
            cell = datarow.createCell(colNum++);
            cell.setCellType(CellType.STRING);
            cell.setCellStyle(datastyle);
            cell.setCellValue(dt.get(str).toString());
            sheet.autoSizeColumn(2); 
          } else if (str.equals("Department")) {
            colNum = 3;
            cell = datarow.createCell(colNum++);
            cell.setCellType(CellType.STRING);
            cell.setCellStyle(datastyle);
            cell.setCellValue(dt.get(str).toString());
            sheet.autoSizeColumn(3);
          } else if (str.equals("Attendance_".concat(cur_cal.substring(0, 3).concat("_").concat(cur_cal.substring(4))))) {
            colNum = 4;
            cell = datarow.createCell(colNum++);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellStyle(datastyle1);
            curr = Integer.parseInt(dt.get(str).toString());
            cell.setCellValue(dt.get(str).toString());
            sheet.autoSizeColumn(4); 
          } else if (str.equals("Total Earning_".concat(cur_cal.substring(0, 3).concat("_").concat(cur_cal.substring(4))))) {
            colNum = 5;
            cell = datarow.createCell(colNum++);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellStyle(datastyleformat);
            cell.setCellValue(dt.get(str).toString());
            curr_total_earning = Double.parseDouble(dt.get(str).toString());
            sheet.autoSizeColumn(5); 
          } else if (str.equals("Total Deduction_".concat(cur_cal.substring(0, 3).concat("_").concat(cur_cal.substring(4))))) {
            colNum = 6;
            cell = datarow.createCell(colNum++);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellStyle(datastyleformat);
            cell.setCellValue(dt.get(str).toString());
            sheet.autoSizeColumn(6); 
            curr_total_deduction = Double.parseDouble(dt.get(str).toString());

          } else if (str.equals("Net Pay_".concat(cur_cal.substring(0, 3).concat("_").concat(cur_cal.substring(4))))) {
            colNum = 7;
            cell = datarow.createCell(colNum++);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellStyle(datastyleformat);
            cell.setCellValue(dt.get(str).toString());
            sheet.autoSizeColumn(7); 
            curr_netpay = Double.parseDouble(dt.get(str).toString());

          } else if (str.equals("Income Tax_".concat(cur_cal.substring(0, 3).concat("_").concat(cur_cal.substring(4)))) || str.equals("TDS_".concat(cur_cal.substring(0, 3).concat("_").concat(cur_cal.substring(4))))) {
            colNum = 8;
            cell = datarow.createCell(colNum++);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellStyle(datastyleformat);
            cell.setCellValue(dt.get(str).toString());
            sheet.autoSizeColumn(8); 
            curr_inctax = Double.parseDouble(dt.get(str).toString());

          } else if (str.equals("Total Contribution_".concat(cur_cal.substring(0, 3).concat("_").concat(cur_cal.substring(4))))) {
            colNum = 9;
            cell = datarow.createCell(colNum++);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellStyle(datastyleformat);
            cell.setCellValue(dt.get(str).toString());
            sheet.autoSizeColumn(9); 
            curr_total_contri = Double.parseDouble(dt.get(str).toString());

          } else if (str.equals("Attendance_".concat(pre_cal.substring(0, 3).concat("_").concat(pre_cal.substring(4))))) {
            colNum = 10;
            cell = datarow.createCell(colNum++);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellStyle(datastyle1);
            cell.setCellValue(dt.get(str).toString());
            sheet.autoSizeColumn(10); 
            pre = Integer.parseInt(dt.get(str).toString());

          } else if (str.equals("Total Earning_".concat(pre_cal.substring(0, 3).concat("_").concat(pre_cal.substring(4))))) {
            colNum = 11;
            cell = datarow.createCell(colNum++);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellStyle(datastyleformat);
            cell.setCellValue(dt.get(str).toString());
            sheet.autoSizeColumn(11); 
            pre_total_earning = Double.parseDouble(dt.get(str).toString());

            if (curr_total_earning != 0 && pre_total_earning != 0) {
              chper_earning = ((curr_total_earning - pre_total_earning) / pre_total_earning) * 100;
            } else {
              chper_earning = 0.00;
            }

          } else if (str.equals("Total Deduction_".concat(pre_cal.substring(0, 3).concat("_").concat(pre_cal.substring(4))))) {
            colNum = 12;
            cell = datarow.createCell(colNum++);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellStyle(datastyleformat);
            cell.setCellValue(dt.get(str).toString());
            sheet.autoSizeColumn(12); 
            pre_total_deduction = Double.parseDouble(dt.get(str).toString());

            if (curr_total_deduction != 0 && pre_total_deduction != 0) {
              chper_deduction = ((curr_total_deduction - pre_total_deduction) / pre_total_deduction) * 100;
            } else {
              chper_deduction = 0.00;
            }
            
          } else if (str.equals("Net Pay_".concat(pre_cal.substring(0, 3).concat("_").concat(pre_cal.substring(4))))) {
            colNum = 13;
            cell = datarow.createCell(colNum++);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellStyle(datastyleformat);
            cell.setCellValue(dt.get(str).toString());
            sheet.autoSizeColumn(13); 
            pre_total_contri = Double.parseDouble(dt.get(str).toString());

            if (curr_netpay != 0 && pre_netpay != 0) {
              chper_netpay = ((curr_netpay - pre_netpay) / pre_netpay) * 100;
            } else {
              chper_netpay = 0.00;
            }
            

          } else if (str.equals("Income Tax_".concat(pre_cal.substring(0, 3).concat("_").concat(pre_cal.substring(4)))) || str.equals("TDS_".concat(pre_cal.substring(0, 3).concat("_").concat(pre_cal.substring(4))))) {
            colNum = 14;
            cell = datarow.createCell(colNum++);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellStyle(datastyleformat);
            cell.setCellValue(dt.get(str).toString());
            sheet.autoSizeColumn(14); 
            pre_inctax = Double.parseDouble(dt.get(str).toString());

            if (curr_inctax != 0 && pre_inctax != 0) {
              chper_inctax = ((curr_inctax - pre_inctax) / pre_inctax) * 100;
            } else {
              chper_inctax = 0.00;
            }
           

          } else if (str.equals("Total Contribution_".concat(pre_cal.substring(0, 3).concat("_").concat(pre_cal.substring(4))))) {
            colNum = 15;
            cell = datarow.createCell(colNum++);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellStyle(datastyleformat);
            cell.setCellValue(dt.get(str).toString());
            sheet.autoSizeColumn(15); 
            pre_total_contri = Double.parseDouble(dt.get(str).toString());

            if (curr_total_contri != 0 && pre_total_contri != 0) {
              chper_contri = ((curr_total_contri - pre_total_contri) / pre_total_contri) * 100;
            } else {
              chper_contri = 0.00;
            }
            

          }
        }

        if (chper_earning < 20) {
			chper_earning1 = "Less than 20%";
		} else if (chper_earning >= 20 && chper_earning < 30) {
			chper_earning1 = "20% to 30%";
		} else if (chper_earning >= 30 && chper_earning < 40) {
			chper_earning1 = "30% to 40%";
		} else if (chper_earning >= 40 && chper_earning < 50) {
			chper_earning1 = "40% to 50%";
		} else if (chper_earning >= 50) {
			chper_earning1 = "Above 50%";
		}

		if (chper_deduction < 20) {
			chper_deduction1 = "Less than 20%";
		} else if (chper_deduction >= 20 && chper_deduction < 30) {
			chper_deduction1 = "20% to 30%";
		} else if (chper_deduction >= 30 && chper_deduction < 40) {
			chper_deduction1 = "30% to 40%";
		} else if (chper_deduction >= 40 && chper_deduction < 50) {
			chper_deduction1 = "40% to 50%";
		} else if (chper_deduction >= 50) {
			chper_deduction1 = "Above 50%";
		}

		if (chper_contri < 20) {
			chper_contri1 = "Less than 20%";
		} else if (chper_contri >= 20 && chper_contri < 30) {
			chper_contri1 = "20% to 30%";
		} else if (chper_contri >= 30 && chper_contri < 40) {
			chper_contri1 = "30% to 40%";
		} else if (chper_contri >= 40 && chper_contri < 50) {
			chper_contri1 = "40% to 50%";
		} else if (chper_contri >= 50) {
			chper_contri1 = "Above 50%";
		}

		if (chper_netpay < 20) {
			chper_netpay1 = "Less than 20%";
		} else if (chper_netpay >= 20 && chper_netpay < 30) {
			chper_netpay1 = "20% to 30%";
		} else if (chper_netpay >= 30 && chper_netpay < 40) {
			chper_netpay1 = "30% to 40%";
		} else if (chper_netpay >= 40 && chper_netpay < 50) {
			chper_netpay1 = "40% to 50%";
		} else if (chper_netpay >= 50) {
			chper_netpay1 = "Above 50%";
		}

		if (chper_inctax < 20) {
			chper_inctax1 = "Less than 20%";
		} else if (chper_inctax >= 20 && chper_inctax < 30) {
			chper_inctax1 = "20% to 30%";
		} else if (chper_inctax >= 30 && chper_inctax < 40) {
			chper_inctax1 = "30% to 40%";
		} else if (chper_inctax >= 40 && chper_inctax < 50) {
			chper_inctax1 = "40% to 50%";
		} else if (chper_inctax >= 50) {
			chper_inctax1 = "Above 50%";
		}
        colNum = 16;
        cell = datarow.createCell(colNum++);
        diff = curr - pre;
        cell.setCellType(CellType.NUMERIC);
        cell.setCellStyle(datastyle1);
        cell.setCellValue(diff);
        sheet.autoSizeColumn(16); 

        colNum = 17;
        cell = datarow.createCell(colNum++);
        diff_total_earning = curr_total_earning - pre_total_earning;
        cell.setCellType(CellType.NUMERIC);
        cell.setCellStyle(datastyleformat);
        cell.setCellValue(diff_total_earning);
        sheet.autoSizeColumn(17); 

        colNum = 18;
        cell = datarow.createCell(colNum++);
        cell.setCellType(CellType.STRING);
        cell.setCellStyle(datastyle);
        cell.setCellValue(chper_earning1);
        sheet.autoSizeColumn(18); 

        colNum = 19;
        cell = datarow.createCell(colNum++);
        diff_total_deduction = curr_total_deduction - pre_total_deduction;
        cell.setCellType(CellType.NUMERIC);
        cell.setCellStyle(datastyleformat);
        cell.setCellValue(diff_total_deduction);
        sheet.autoSizeColumn(19); 

        colNum = 20;
        cell = datarow.createCell(colNum++);
        cell.setCellType(CellType.STRING);
        cell.setCellStyle(datastyle);
        cell.setCellValue(chper_deduction1);
        sheet.autoSizeColumn(20); 
        
        colNum = 21;
        cell = datarow.createCell(colNum++);
        diff_netpay = curr_netpay - pre_netpay;
        cell.setCellType(CellType.NUMERIC);
        cell.setCellStyle(datastyleformat);
        cell.setCellValue(diff_netpay);
        sheet.autoSizeColumn(21); 

        colNum = 22;
        cell = datarow.createCell(colNum++);
        cell.setCellType(CellType.STRING);
        cell.setCellStyle(datastyle);
        cell.setCellValue(chper_netpay1);
        sheet.autoSizeColumn(22); 

        colNum = 23;
        cell = datarow.createCell(colNum++);
        diff_inctax = curr_inctax - pre_inctax;
        cell.setCellType(CellType.NUMERIC);
        cell.setCellStyle(datastyleformat);
        cell.setCellValue(diff_inctax);
        sheet.autoSizeColumn(23); 

        colNum = 24;
        cell = datarow.createCell(colNum++);
        cell.setCellType(CellType.STRING);
        cell.setCellStyle(datastyle);
        cell.setCellValue(chper_inctax1);
        sheet.autoSizeColumn(24); 

        colNum = 25;
        cell = datarow.createCell(colNum++);
        diff_total_contri = curr_total_contri - pre_total_contri;
        cell.setCellType(CellType.NUMERIC);
        cell.setCellStyle(datastyleformat);
        cell.setCellValue(diff_total_contri);
        sheet.autoSizeColumn(25); 

        colNum = 26;
        cell = datarow.createCell(colNum++);
        cell.setCellType(CellType.STRING);
        cell.setCellStyle(datastyle);
        cell.setCellValue(chper_contri1);
        sheet.autoSizeColumn(26); 
        
      }
    }

    workbook.write(stream);
    workbook.close();
  }

  /////////////////////////////////// PAY ROLL REGISTER
  /////////////////////////////////// /////////////////////////////////////////////////////////////////

  public void WritePayrollRegister(List < Map < String, Object >> data, String buname, String calmonth, String naturemp,
    ServletOutputStream stream) throws IOException {
    int ec = 0, dc = 0, cc = 0, ecmax = 0, dcmax = 0, ccmax = 0;
    int count = 0;
    calmonth = calmonth.substring(calmonth.indexOf("-") + 1);

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Sheet 1");
    sheet.addIgnoredErrors(CellRangeAddress.valueOf("A1:XFD1048576"), IgnoredErrorType.NUMBER_STORED_AS_TEXT);
    int rowNum = 0;
    int colNum = 0;
    int c = 0;

    if (data != null) {
      Set < String > keysets = data.get(0).keySet();

      System.out.println("Creating excel");
      Row earnrow = sheet.createRow(rowNum++);
      Cell earncell1 = earnrow.createCell(0);
      CellStyle earnStyle = workbook.createCellStyle();
      earnStyle.setFillForegroundColor(IndexedColors.GOLD.index);
      earnStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      earnStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      earnStyle.setBorderTop(BorderStyle.THIN);
      earnStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      earnStyle.setBorderLeft(BorderStyle.THIN);
      earnStyle.setLeftBorderColor(IndexedColors.WHITE.getIndex());
      earnStyle.setWrapText(true);
      earnStyle.setVerticalAlignment(VerticalAlignment.CENTER);
      earnStyle.setAlignment(HorizontalAlignment.CENTER);
      earncell1.setCellStyle(earnStyle);

      Cell earncell2 = earnrow.createCell(1);
      earncell2.setCellValue("Earnings");

      Row dedrow = sheet.createRow(rowNum++);
      Cell dedcell1 = dedrow.createCell(0);
      CellStyle dedStyle = workbook.createCellStyle();
      dedStyle.setFillForegroundColor(IndexedColors.AQUA.index);
      dedStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      dedStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      dedStyle.setBorderTop(BorderStyle.THIN);
      dedStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      dedStyle.setBorderLeft(BorderStyle.THIN);
      dedStyle.setLeftBorderColor(IndexedColors.WHITE.getIndex());
      dedStyle.setWrapText(true);
      dedStyle.setVerticalAlignment(VerticalAlignment.CENTER);
      dedStyle.setAlignment(HorizontalAlignment.CENTER);
      dedcell1.setCellStyle(dedStyle);

      Cell dedcell2 = dedrow.createCell(1);
      dedcell2.setCellValue("Deductions");

      Row contrirow = sheet.createRow(rowNum++);
      Cell contricell1 = contrirow.createCell(0);
      CellStyle contriStyle = workbook.createCellStyle();
      contriStyle.setFillForegroundColor(IndexedColors.LAVENDER.index);
      contriStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      contriStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      contriStyle.setBorderTop(BorderStyle.THIN);
      contriStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      contriStyle.setBorderLeft(BorderStyle.THIN);
      contriStyle.setLeftBorderColor(IndexedColors.WHITE.getIndex());
      contriStyle.setWrapText(true);
      contriStyle.setVerticalAlignment(VerticalAlignment.CENTER);
      contriStyle.setAlignment(HorizontalAlignment.CENTER);
      contricell1.setCellStyle(contriStyle);

      Cell contricell2 = contrirow.createCell(1);
      contricell2.setCellValue("Contributions");

      Row row = sheet.createRow(rowNum++);

      XSSFFont TitleFont = workbook.createFont();
      TitleFont.setBold(true);
      TitleFont.setFontHeight(15);
      TitleFont.setColor(IndexedColors.BLUE_GREY.getIndex());
      CellStyle titleStyle = workbook.createCellStyle();
      titleStyle.setFont(TitleFont);

      Cell TitleCell = row.createCell(0);
      TitleCell.setCellValue("UPMRC Payroll Register For Calendar - " + calmonth + " for " + naturemp + " Employees of " + buname);

      TitleCell.setCellStyle(titleStyle);

      CellStyle redstyle = workbook.createCellStyle();
      redstyle.setFillForegroundColor(IndexedColors.RED.getIndex());
      redstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      //sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 8));

      XSSFFont headerFont = workbook.createFont();

      headerFont.setBold(true);
      headerFont.setColor(IndexedColors.WHITE.getIndex());
      headerFont.setFontHeight(10);
      CellStyle style = workbook.createCellStyle();

      row = sheet.createRow(rowNum++);

      Cell snocell = row.createCell(colNum++); // Done by asmita on 4-May-2021
      snocell.setCellValue("Sr. No");
      style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
      style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      style.setBorderBottom(BorderStyle.THIN);
      style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      style.setBorderTop(BorderStyle.THIN);
      style.setTopBorderColor(IndexedColors.BLACK.getIndex());
      style.setBorderLeft(BorderStyle.THIN);
      style.setLeftBorderColor(IndexedColors.WHITE.getIndex());
      style.setWrapText(true);
      style.setVerticalAlignment(VerticalAlignment.CENTER);
      style.setAlignment(HorizontalAlignment.CENTER);
      style.setFont(headerFont);
      snocell.setCellStyle(style); // Done by asmita on 4-May-2021

      int i = 0;
      int cn = 0; //Done By ASMITA 23-03-2021
      for (String str: keysets) {
        sheet.setColumnWidth(i, 4000);

        if (str.contains("Projected") || str.contains("Perquisites")) {
          style.setFillForegroundColor(IndexedColors.ORCHID.getIndex());
          sheet.setColumnWidth(i, 4500);
        } else {
          style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        }

        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        style.setWrapText(true);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(headerFont);

        Cell cell = row.createCell(colNum++);
        if (str.contains("_er")) {
          cell.setCellValue(str.substring(0, str.length() - 3));
          cell.setCellStyle(earnStyle);
        } else if (str.contains("_de")) {
          cell.setCellValue(str.substring(0, str.length() - 3));
          cell.setCellStyle(dedStyle);
        } else if (str.contains("_st")) {
          cell.setCellValue(str.substring(0, str.length() - 3));
          cell.setCellStyle(contriStyle);
        } else {
          cell.setCellValue(str);
          cell.setCellStyle(style);
        }

        /* Done by Asmita 23-03-2021 STARTS */
        if (str.contains("Net Pay")) {
          cn = colNum - 1;
        }
        /* Done by Asmita 23-03-2021 ENDS */
        //	            cell.setCellStyle(style);
        i++;
      }

      CellStyle style1 = workbook.createCellStyle();
      style1.setBorderRight(BorderStyle.THIN);
      style1.setRightBorderColor(IndexedColors.BLACK.getIndex());

      int cnt = 0; // Done by asmita on 4-May-2021
      for (Map < String, Object > dt: data) {
        row = sheet.createRow(rowNum++);
        cnt++;
        colNum = 0;
        keysets = dt.keySet();

        Cell sncell = row.createCell(colNum++); // Done by asmita on 4-May-2021
        sncell.setCellValue(cnt); // Done by asmita on 4-May-2021

        for (String str: keysets) {
        	
          XSSFFont cellFont = workbook.createFont();
          cellFont.setFontHeight(10);
          Cell cell = row.createCell(colNum);
          //cell.setCellValue(dt.get(str).toString());
          //|| colNum == 4  || colNum == 5 || colNum == 6

          /* Done by Asmita on 07-May-2021 Starts */
          if (colNum == 1)
          // if(colNum == 0 || colNum == 1 || colNum == 2)
          {
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(Integer.parseInt(dt.get(str).toString()));
          } else if (colNum == 2 || colNum == 3 || colNum == 4 || colNum == 5|| colNum == 6)
          // if(colNum == 0 || colNum == 1 || colNum == 2)
          {
            cell.setCellType(CellType.STRING);
            cell.setCellValue(dt.get(str).toString());
          } else {
            cell.setCellType(CellType.NUMERIC);

            double d = Double.parseDouble(dt.get(str).toString());
            cell.setCellValue(d);
            //System.out.println("value: "+d);
          }
          /* Done by Asmita on 07-May-2021 Ends */

          /* DONE BY ASMITA 23-03-2021 STARTS */

          if (colNum == cn && cell.getNumericCellValue() < 0) {
            cell.setCellStyle(redstyle);
          }
          /* Done by Asmita 23-03-2021 ENDS */
          colNum++;
          /*if(cnt % 2 == 0){
              CellStyle style = workbook.createCellStyle();
              style.setFillForegroundColor(IndexedColors.LIME.index);
              style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
              style.setBorderRight(BorderStyle.THIN);
              style.setRightBorderColor(IndexedColors.BLACK.getIndex());
              cell.setCellStyle(style);
          } else {*/
          style1.setFont(cellFont);
          cell.setCellStyle(style1);
          //}
        }
      }

    } else {
      Row row = sheet.createRow(rowNum);
      Cell cell = row.createCell(colNum);
      cell.setCellValue("No Data found!!!");
    }

    workbook.write(stream);
    workbook.close();
  }
  ////////////////////////// NPS REPORT
  ////////////////////////// ///////////////////////////////////////////////////

  public void WriteNPSReport(NPSModel data[], String buname, String calmonth, ServletOutputStream stream)
  throws IOException {

    calmonth = calmonth.substring(calmonth.indexOf("-") + 1);
    String month = calmonth.substring(0, 3);
    String year = calmonth.substring(calmonth.indexOf("-") + 1);
    int count = 0;
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Sheet 1");
    sheet.addIgnoredErrors(CellRangeAddress.valueOf("A1:XFD1048576"), IgnoredErrorType.NUMBER_STORED_AS_TEXT);
    int rowNum = 0;
    int colNum = 0;
    int m = 0;
    switch (month) {
    case "JAN":
      m = 1;
      break;
    case "FEB":
      m = 2;
      break;
    case "MAR":
      m = 3;
      break;
    case "APR":
      m = 4;
      break;
    case "MAY":
      m = 5;
      break;
    case "JUN":
      m = 6;
      break;
    case "JUL":
      m = 7;
      break;
    case "AUG":
      m = 8;
      break;
    case "SEP":
      m = 9;
      break;
    case "OCT":
      m = 10;
      break;
    case "NOV":
      m = 11;
      break;
    case "DEC":
      m = 12;
      break;
    }

    if (data.length != 0) {
      List < NPSModel > preg = Arrays.asList(data);

      LinkedHashMap < String, Object > employeedatamap = null;
      List < Map < String, Object >> employeedatalist = new ArrayList < > ();

      for (NPSModel npsregister: preg) {
        count++;
        employeedatamap = new LinkedHashMap < > ();
        employeedatamap.put("Sr.No.", count);
        employeedatamap.put("CBO REG NO", "6521616");
        employeedatamap.put("Emp.No.", npsregister.getPersonnumber());
        employeedatamap.put("Emp Type", npsregister.getNatureofempl());
        employeedatamap.put("Name of Officials", npsregister.getPersonname());
        employeedatamap.put("Post Name ", npsregister.getPosition());
        employeedatamap.put("PRAN No.", npsregister.getPrannumber());
        employeedatamap.put("Parent Deptt", npsregister.getDeptname());
        employeedatamap.put("Pay Scale ", npsregister.getGradename());
        employeedatamap.put("Employer Contribution \n {14% of Basic+DA  and 2.5% of Basic }",
          npsregister.getNps());
        employeedatamap.put("Employee Contribution \n {10% of Basic+DA  & 7.5% of Basic }",
          npsregister.getSnps());
        employeedatamap.put("Total", 0);
        // employeedatamap.put("CONTRIBUTION TYPE A/C/O C=Regular contribution,
        // A=Arrears, O= Adhoc", "0");
        // employeedatamap.put("Month", month);

        employeedatamap.put("CONTRIBUTION TYPE A/C/O C=Regular contribution, A=Arrears, O= Adhoc", "C");
        employeedatamap.put("Month", m);
        employeedatamap.put("Contribution Year", year);
        employeedatalist.add(employeedatamap);
      }

      Set < String > payregkeysets = employeedatalist.get(0).keySet();

      // ***** Style For Heading Row ****//
      XSSFFont font = workbook.createFont();
      font.setFontHeightInPoints((short) 18);
      font.setFontName("Calibri");
      font.setColor(IndexedColors.BLACK.getIndex());
      font.setBold(true);
      font.setUnderline(HSSFFont.U_SINGLE);
      font.setItalic(false);

      CellStyle style = workbook.createCellStyle();
      style.setAlignment(HorizontalAlignment.CENTER);
      style.setVerticalAlignment(VerticalAlignment.CENTER);
      style.setBorderTop(BorderStyle.THIN);
      style.setTopBorderColor(IndexedColors.BLACK.getIndex());
      style.setBorderBottom(BorderStyle.THIN);
      style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      style.setBorderLeft(BorderStyle.THIN);
      style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      style.setBorderRight(BorderStyle.THIN);
      style.setRightBorderColor(IndexedColors.BLACK.getIndex());
      // Setting font to style
      style.setFont(font);

      // heading row 1
      Row row = sheet.createRow(rowNum++);
      Cell headingCell = row.createCell(colNum++);
      headingCell.setCellValue("Uttar Pradesh Metro Rail Corporation Limited");
      sheet.addMergedRegion(CellRangeAddress.valueOf("A1:Q1"));
      headingCell.setCellStyle(style);

      // heading row 2
      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell headingCell2 = row.createCell(colNum++);
      headingCell2.setCellValue(
        "Deductions and Contribution of NPS for the staff deputed from various Department to LMRC from salary in " +
        calmonth);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A2:Q2"));
      headingCell2.setCellStyle(style);

      // ***** Style For Data Heading Row ****//
      XSSFFont dataheadingfont = workbook.createFont();
      dataheadingfont.setFontHeightInPoints((short) 11);
      dataheadingfont.setFontName("Calibri");
      dataheadingfont.setColor(IndexedColors.BLACK.getIndex());
      dataheadingfont.setBold(true);
      // dataheadingfont.setUnderline(HSSFFont.U_SINGLE);
      dataheadingfont.setItalic(false);

      CellStyle dataheadingstyle = workbook.createCellStyle();
      dataheadingstyle.setWrapText(true);
      dataheadingstyle.setAlignment(HorizontalAlignment.CENTER);
      dataheadingstyle.setVerticalAlignment(VerticalAlignment.CENTER);
      dataheadingstyle.setBorderTop(BorderStyle.THIN);
      dataheadingstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      dataheadingstyle.setBorderBottom(BorderStyle.THIN);
      dataheadingstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      dataheadingstyle.setBorderLeft(BorderStyle.THIN);
      dataheadingstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      dataheadingstyle.setBorderRight(BorderStyle.THIN);
      dataheadingstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
      // Setting font to style
      dataheadingstyle.setFont(dataheadingfont);

      // Main Headings
      row = sheet.createRow(rowNum++);
      row.setHeight((short) 2500);
      colNum = 0;
      int i = 0;
      for (String str: payregkeysets) {
        i++;
        Cell cell = row.createCell(colNum++);
        cell.setCellValue(str);
        cell.setCellStyle(dataheadingstyle);
        if (colNum == 4 || colNum == 5 || colNum == 8) {
          sheet.setColumnWidth(i, 7000);
        } else {
          sheet.setColumnWidth(i, 3500);
        }

      }

      // ***** Style For Data Row ****//
      XSSFFont datafont = workbook.createFont();
      datafont.setFontHeightInPoints((short) 11);
      datafont.setFontName("Calibri");
      datafont.setColor(IndexedColors.BLACK.getIndex());
      datafont.setBold(false);
      // datafont.setUnderline(HSSFFont.U_SINGLE);
      datafont.setItalic(false);

      CellStyle datastyle = workbook.createCellStyle();
      // datastyle.setWrapText(true);
      datastyle.setAlignment(HorizontalAlignment.LEFT);
      datastyle.setVerticalAlignment(VerticalAlignment.CENTER);
      datastyle.setBorderTop(BorderStyle.THIN);
      datastyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      datastyle.setBorderBottom(BorderStyle.THIN);
      datastyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      datastyle.setBorderLeft(BorderStyle.THIN);
      datastyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      datastyle.setBorderRight(BorderStyle.THIN);
      datastyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
      // Setting font to style
      datastyle.setFont(datafont);

      CellStyle datastyleright = workbook.createCellStyle();
      // datastyle.setWrapText(true);
      datastyleright.setAlignment(HorizontalAlignment.RIGHT);
      datastyleright.setVerticalAlignment(VerticalAlignment.CENTER);
      datastyleright.setBorderTop(BorderStyle.THIN);
      datastyleright.setTopBorderColor(IndexedColors.BLACK.getIndex());
      datastyleright.setBorderBottom(BorderStyle.THIN);
      datastyleright.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      datastyleright.setBorderLeft(BorderStyle.THIN);
      datastyleright.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      datastyleright.setBorderRight(BorderStyle.THIN);
      datastyleright.setRightBorderColor(IndexedColors.BLACK.getIndex());
      // Setting font to style
      datastyleright.setFont(datafont);

      CellStyle datastylerightformat = workbook.createCellStyle();
      // datastyle.setWrapText(true);
      datastylerightformat.setAlignment(HorizontalAlignment.RIGHT);
      datastylerightformat.setVerticalAlignment(VerticalAlignment.CENTER);
      datastylerightformat.setBorderTop(BorderStyle.THIN);
      datastylerightformat.setTopBorderColor(IndexedColors.BLACK.getIndex());
      datastylerightformat.setBorderBottom(BorderStyle.THIN);
      datastylerightformat.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      datastylerightformat.setBorderLeft(BorderStyle.THIN);
      datastylerightformat.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      datastylerightformat.setBorderRight(BorderStyle.THIN);
      datastylerightformat.setRightBorderColor(IndexedColors.BLACK.getIndex());
      datastylerightformat.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
      // Setting font to style
      datastylerightformat.setFont(datafont);

      for (Map < String, Object > dt: employeedatalist) {
        row = sheet.createRow(rowNum++);

        colNum = 0;
        payregkeysets = dt.keySet();
        for (String str: payregkeysets) {
          Cell cell = row.createCell(colNum);
          if (colNum == 0) {

            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(Integer.parseInt(dt.get(str).toString()));
            cell.setCellStyle(datastyleright);
          } else if (colNum == 1) {

            cell.setCellType(CellType.STRING);
            cell.setCellValue(dt.get(str).toString());
            cell.setCellStyle(datastyleright);
          } else if (colNum == 2) {
            String coldata = dt.get(str).toString();

            if (alphanum.ifAlphaNumeric(coldata)) {
              int pno = Integer.parseInt(coldata);
              //							System.out.println("Person Number ::: "+pno);
              cell.setCellType(CellType.NUMERIC);
              cell.setCellValue(pno);
              cell.setCellStyle(datastyleright);
            } else {
              String pno = coldata;
              //							System.out.println("Person Number ::: "+pno);
              cell.setCellType(CellType.STRING);
              cell.setCellValue(pno);
              cell.setCellStyle(datastyleright);
              //							continue;
            }
          } else if (colNum == 3 || colNum == 4 || colNum == 5 || colNum == 6 || colNum == 7 || colNum == 8 ||
            colNum == 12 || colNum == 13) {
            String coldata = "";
            if (dt.get(str) != null) {
              coldata = dt.get(str).toString();
            } else {
              coldata = "";
            }

            //						System.out.println("COLDATA col 3 ====> "+coldata);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(coldata);
            cell.setCellStyle(datastyle);
          } else if (colNum == 9 || colNum == 10) {
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            cell.setCellStyle(datastylerightformat);
          } else if (colNum == 11) {
            cell.setCellType(CellType.NUMERIC);
            cell.setCellFormula("Sum(J" + rowNum + ":K" + rowNum + ")");
            cell.setCellStyle(datastylerightformat);

          } else if (colNum == 14) {
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(Double.parseDouble(dt.get(str).toString()));
            cell.setCellStyle(datastyleright);
          }
          colNum++;
        }
      }

      row = sheet.createRow(rowNum++);
      int rowNumm = rowNum - 1;
      colNum = 0;
      Cell gtcell = row.createCell(colNum);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A" + rowNum + ":I" + rowNum));
      gtcell.setCellValue("Grand Total");
      gtcell.setCellStyle(datastyleright);

      colNum = 9;
      Cell totemplrcell = row.createCell(colNum++);
      totemplrcell.setCellFormula("Sum(J4:J" + rowNumm + ")");
      totemplrcell.setCellStyle(datastylerightformat);

      Cell totemplcell = row.createCell(colNum++);
      totemplcell.setCellFormula("Sum(K4:K" + rowNumm + ")");
      totemplcell.setCellStyle(datastylerightformat);

      Cell totcell = row.createCell(colNum++);
      totcell.setCellFormula("Sum(L4:L" + rowNumm + ")");
      totcell.setCellStyle(datastylerightformat);
    } else {
      Row row = sheet.createRow(rowNum);
      Cell cell = row.createCell(colNum);
      cell.setCellValue("No Data Found!!!");
    }

    workbook.write(stream);
    workbook.close();
  }

  /* NPS REPORT UPDATED ON 22-04-2021 */

  ////////////////////// SALARY SUMMARY BILL /////////////////////////////////////

  public void WriteBankModRegister(AllBankDetails[] allBankDetails, String bus_name, String banktype,
    String natureemp, ServletOutputStream stream) throws IOException {
    int count = 0;

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Sheet 1");

    sheet.addIgnoredErrors(CellRangeAddress.valueOf("A1:XFD1048576"), IgnoredErrorType.NUMBER_STORED_AS_TEXT);
    int rowNum = 0;
    int colNum = 0;

    if (allBankDetails.length != 0) {
      List < AllBankDetails > bankdetails = Arrays.asList(allBankDetails);

      LinkedHashMap < String, Object > employeedatamap = null;
      List < Map < String, Object >> employeedatalist = new ArrayList < > ();

      for (AllBankDetails bankdet: bankdetails) {
        count++;
        employeedatamap = new LinkedHashMap < > ();
        employeedatamap.put("S. No", count);
        employeedatamap.put("Emp ID", bankdet.getPersonnumber());
        employeedatamap.put("Employee Name", bankdet.getName());
        employeedatamap.put("Employee Category", bankdet.getNatureofemployement());
        employeedatamap.put("Previous Bank Name", bankdet.getPrevbankname());
        employeedatamap.put("Previous Bank Account No", bankdet.getPrevtbankaccountnumber());
        employeedatamap.put("Previous IFSC Code", bankdet.getPrevbranchifsccode());
        employeedatamap.put("Previous Start Date", "");
        employeedatamap.put("Previous End Date", "");
        employeedatamap.put("Current Bank Name", bankdet.getCurrentbankname());
        employeedatamap.put("Current Bank Acc No", bankdet.getCurrentbankaccountnumber());
        employeedatamap.put("Current IFSC Code", bankdet.getCurrentbranchifsccode());
        employeedatamap.put("Current Start Date", "");
        employeedatamap.put("Current End Date", "");

        employeedatalist.add(employeedatamap);
      }

      Set < String > payregkeysets = employeedatalist.get(0).keySet();

      // ***** Style For Heading Row ****//
      XSSFFont headingfont = workbook.createFont();
      headingfont.setFontHeightInPoints((short) 14);
      headingfont.setFontName("Calibri");
      headingfont.setColor(IndexedColors.BLACK.getIndex());
      headingfont.setBold(true);
      headingfont.setItalic(false);

      CellStyle headingstyle = workbook.createCellStyle();
      headingstyle.setAlignment(HorizontalAlignment.LEFT);
      headingstyle.setVerticalAlignment(VerticalAlignment.CENTER);

      // Setting font to style
      headingstyle.setFont(headingfont);

      XSSFFont headingfont1 = workbook.createFont();
      headingfont1.setFontHeightInPoints((short) 10);
      headingfont1.setFontName("Calibri");
      headingfont1.setColor(IndexedColors.BLACK.getIndex());
      headingfont1.setBold(true);
      headingfont1.setItalic(false);

      CellStyle headingstyle1 = workbook.createCellStyle();
      headingstyle1.setAlignment(HorizontalAlignment.LEFT);
      headingstyle1.setVerticalAlignment(VerticalAlignment.CENTER);
      headingstyle1.setBorderTop(BorderStyle.THIN);
      headingstyle1.setTopBorderColor(IndexedColors.BLACK.getIndex());
      headingstyle1.setBorderRight(BorderStyle.THIN);
      headingstyle1.setRightBorderColor(IndexedColors.BLACK.getIndex());
      headingstyle1.setBorderBottom(BorderStyle.THIN);
      headingstyle1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      headingstyle1.setBorderLeft(BorderStyle.THIN);
      headingstyle1.setLeftBorderColor(IndexedColors.BLACK.getIndex());

      // Setting font to style
      headingstyle1.setFont(headingfont1);

      Row row = sheet.createRow(rowNum++);
      Cell headingcell = row.createCell(colNum++);

      headingcell.setCellValue("UPMRC Bank MOD Report for " + natureemp + " Employees of " + bus_name + " having " +
        banktype + " account");
      headingcell.setCellStyle(headingstyle);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A1:N1"));

      row = sheet.createRow(rowNum++);
      colNum = 0;
      Cell headingCell1 = row.createCell(colNum++);
      headingCell1.setCellValue("");
      sheet.addMergedRegion(CellRangeAddress.valueOf("A2:D2"));

      Cell headingCell2 = row.createCell(colNum++);
      headingCell2.setCellValue("Old Bank Details");
      headingCell2.setCellStyle(headingstyle1);
      sheet.addMergedRegion(CellRangeAddress.valueOf("E2:I2"));

      Cell headingCell3 = row.createCell(colNum++);
      headingCell3.setCellValue("New Bank Details");
      headingCell3.setCellStyle(headingstyle1);
      sheet.addMergedRegion(CellRangeAddress.valueOf("J2:N2"));

      // Print all data from JSON along with headings
      row = sheet.createRow(rowNum++);
      colNum = 0;
      int i = 0;
      for (String str: payregkeysets) {
        Cell cell = row.createCell(colNum++);
        cell.setCellValue(str);
        cell.setCellStyle(headingstyle1);
        if (colNum == 0 || colNum == 1) {
          sheet.setColumnWidth(i, 2500);
        } else {
          sheet.setColumnWidth(i, 6000);
        }
        i++;
      }

      for (Map < String, Object > dt: employeedatalist) {
        row = sheet.createRow(rowNum++);
        colNum = 0;
        payregkeysets = dt.keySet();
        for (String str: payregkeysets) {
          Cell cell = row.createCell(colNum);
          if (colNum == 0) {
            if (dt.get(str) != null) {
              cell.setCellType(CellType.NUMERIC);
              cell.setCellValue(dt.get(str).toString());
            } else {
              cell.setCellType(CellType.NUMERIC);
              cell.setCellValue("");
            }

            // cell.setCellStyle(datastyle2);
          } else if (colNum == 1) {
            String coldata = "";

            if (dt.get(str) != null) {
              coldata = dt.get(str).toString();
              if (alphanum.ifAlphaNumeric(coldata)) {
                int pno = Integer.parseInt(coldata);
                //								System.out.println("Person Number ::: "+pno);
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue(pno);
                // cell.setCellStyle(datastyle2);
              } else {
                String pno = coldata;
                //								System.out.println("Person Number ::: "+pno);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(pno);
                // cell.setCellStyle(datastyle2);
                //								continue;
              }
            } else {
              cell.setCellValue("");
            }

          } else {
            if (dt.get(str) != null) {
              cell.setCellType(CellType.STRING);
              cell.setCellValue(dt.get(str).toString());
            } else {
              cell.setCellValue("");
            }

            //						cell.setCellStyle(datastyle);
          }
          colNum++;
        }
      }
    } else {
      Row row = sheet.createRow(rowNum);
      Cell cell = row.createCell(colNum);
      cell.setCellValue("No Data Found!!!");
    }

    workbook.write(stream);
    workbook.close();
  }

  public void WriteEmployeeDetails(PersonalData[] personalDatas, ServletOutputStream stream) throws IOException {

    int count = 0;

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Sheet 1");

    sheet.addIgnoredErrors(CellRangeAddress.valueOf("A1:XFD1048576"), IgnoredErrorType.NUMBER_STORED_AS_TEXT);
    int rowNum = 0;
    int colNum = 0;

    if (personalDatas.length != 0) {
      List < PersonalData > empdetails = Arrays.asList(personalDatas);

      LinkedHashMap < String, Object > employeedatamap = null;
      List < Map < String, Object >> employeedatalist = new ArrayList < > ();

      for (PersonalData empdet: empdetails) {
        if (empdet != null) {
          count++;
          employeedatamap = new LinkedHashMap < > ();
          employeedatamap.put("S. No", count);
          employeedatamap.put("Emp ID", empdet.getEmpid());
          employeedatamap.put("Employee Name", empdet.getEmpname());
          employeedatamap.put("Father's Name", empdet.getFathername());
          employeedatamap.put("Date of Birth", empdet.getDob());
          employeedatamap.put("Gender", empdet.getGender());
          employeedatamap.put("Address", empdet.getAddress());
          employeedatamap.put("Phone Number", empdet.getPhone());
          employeedatamap.put("Email ID", empdet.getEmail());
          employeedatamap.put("Marital Status", empdet.getMarital());
          employeedatamap.put("Employee Status", empdet.getEmpstatus());
          employeedatamap.put("Work Location", empdet.getWorklocation());
          employeedatamap.put("Assignment Status", empdet.getAssignmentstatus());
          employeedatamap.put("Designation", empdet.getDesignation());
          employeedatamap.put("Department", empdet.getDepartment());
          employeedatamap.put("Date of Joining", empdet.getDoj());
          employeedatamap.put("PAN No", empdet.getPan());
          employeedatamap.put("Aadhar No", empdet.getAdhar());
          employeedatamap.put("PRAN No", empdet.getPran());
          employeedatamap.put("UAN No", empdet.getUan());
          employeedatamap.put("PF No", empdet.getPfnumber());
          employeedatamap.put("Month of Increment", empdet.getMonthofincrement());
          employeedatamap.put("Bank Name", empdet.getBankname());
          employeedatamap.put("Bank A/C No", empdet.getBankaccount());
          employeedatamap.put("IFSC Code", empdet.getIfsc());
          employeedatamap.put("Nature of Employment", empdet.getNatureofemployment());
          employeedatamap.put("Employee Category", empdet.getEmpcategory());
          employeedatamap.put("Staff Type", empdet.getStaff());
          employeedatamap.put("Project/OM", empdet.getProjectom());
          employeedatamap.put("Job", empdet.getJob());
          employeedatamap.put("Company Paid Accomodation", empdet.getComppaidaccom());
          employeedatamap.put("Company Lease Accomodation", empdet.getComppaidaccomlease());
          employeedatamap.put("HRA", empdet.getHra());
          employeedatamap.put("Vehicle Used", empdet.getVehicleused());
          employeedatamap.put("Reason of Leaving", empdet.getLeavingreason());
          employeedatamap.put("Notified Date", empdet.getNotifieddate());
          employeedatamap.put("Actual Termination", empdet.getActualtermination());
          employeedatamap.put("Last Standard Process", empdet.getLaststandardprocess());
          employeedatamap.put("Final Process Date", empdet.getFinalprocessdate());
          employeedatalist.add(employeedatamap);
        }
      }
      Set < String > empdetkeysets = employeedatalist.get(0).keySet();

      XSSFFont headingfont = workbook.createFont();
      headingfont.setFontHeightInPoints((short) 14);
      headingfont.setFontName("Calibri");
      headingfont.setColor(IndexedColors.BLACK.getIndex());
      headingfont.setBold(true);
      headingfont.setItalic(false);

      CellStyle headingstyle = workbook.createCellStyle();
      headingstyle.setAlignment(HorizontalAlignment.LEFT);
      headingstyle.setVerticalAlignment(VerticalAlignment.CENTER);

      // Setting font to style
      headingstyle.setFont(headingfont);

      XSSFFont colheadingfont = workbook.createFont();
      colheadingfont.setFontHeightInPoints((short) 10);
      colheadingfont.setFontName("Calibri");
      colheadingfont.setColor(IndexedColors.BLACK.getIndex());
      colheadingfont.setBold(true);
      colheadingfont.setItalic(false);

      CellStyle colheadingstyle = workbook.createCellStyle();
      colheadingstyle.setAlignment(HorizontalAlignment.LEFT);
      colheadingstyle.setVerticalAlignment(VerticalAlignment.CENTER);
      colheadingstyle.setBorderTop(BorderStyle.THIN);
      colheadingstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      colheadingstyle.setBorderRight(BorderStyle.THIN);
      colheadingstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
      colheadingstyle.setBorderBottom(BorderStyle.THIN);
      colheadingstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      colheadingstyle.setBorderLeft(BorderStyle.THIN);
      colheadingstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

      // Setting font to style
      colheadingstyle.setFont(colheadingfont);
      colheadingstyle.setWrapText(true);

      XSSFFont coldatafont = workbook.createFont();
      coldatafont.setFontHeightInPoints((short) 10);
      coldatafont.setFontName("Calibri");
      coldatafont.setColor(IndexedColors.BLACK.getIndex());
      coldatafont.setBold(false);
      coldatafont.setItalic(false);

      CellStyle coldatastyle = workbook.createCellStyle();
      coldatastyle.setAlignment(HorizontalAlignment.LEFT);
      coldatastyle.setVerticalAlignment(VerticalAlignment.CENTER);
      coldatastyle.setBorderTop(BorderStyle.THIN);
      coldatastyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      coldatastyle.setBorderRight(BorderStyle.THIN);
      coldatastyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
      coldatastyle.setBorderBottom(BorderStyle.THIN);
      coldatastyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      coldatastyle.setBorderLeft(BorderStyle.THIN);
      coldatastyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

      // Setting font to style
      coldatastyle.setFont(coldatafont);
      coldatastyle.setWrapText(true);

      CellStyle coldatastyleright = workbook.createCellStyle();
      coldatastyleright.setAlignment(HorizontalAlignment.RIGHT);
      coldatastyleright.setVerticalAlignment(VerticalAlignment.CENTER);
      coldatastyleright.setBorderTop(BorderStyle.THIN);
      coldatastyleright.setTopBorderColor(IndexedColors.BLACK.getIndex());
      coldatastyleright.setBorderRight(BorderStyle.THIN);
      coldatastyleright.setRightBorderColor(IndexedColors.BLACK.getIndex());
      coldatastyleright.setBorderBottom(BorderStyle.THIN);
      coldatastyleright.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      coldatastyleright.setBorderLeft(BorderStyle.THIN);
      coldatastyleright.setLeftBorderColor(IndexedColors.BLACK.getIndex());

      // Setting font to style
      coldatastyleright.setFont(coldatafont);
      coldatastyleright.setWrapText(true);

      Row row = sheet.createRow(rowNum++);
      Cell headingRowCell = row.createCell(colNum++);
      headingRowCell.setCellValue("UPMRC EMPLOYEE  DETAILS");
      headingRowCell.setCellStyle(headingstyle);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));

      // Print all data from JSON along with headings
      row = sheet.createRow(rowNum++);
      colNum = 0;
      int i = 0;
      for (String str: empdetkeysets) {
        Cell cell = row.createCell(colNum);
        cell.setCellValue(str);
        cell.setCellStyle(colheadingstyle);

        if (colNum == 0 || colNum == 1) {
          sheet.setColumnWidth(i, 2500);
        } else if (colNum == 4 || colNum == 5 || colNum == 12 || colNum == 15) {
          sheet.setColumnWidth(i, 4500);
        } else if (colNum == 6 || colNum == 8) {
          sheet.setColumnWidth(i, 8000);
        } else {
          sheet.setColumnWidth(i, 6000);
        }
        i++;
        colNum++;
      }

      for (Map < String, Object > dt: employeedatalist) {
        row = sheet.createRow(rowNum++);
        colNum = 0;
        empdetkeysets = dt.keySet();
        for (String str: empdetkeysets) {
          Cell cell = row.createCell(colNum);

          if (colNum == 0 || colNum == 7 || colNum == 17 || colNum == 23) {
            if (dt.get(str) != null) {
              cell.setCellType(CellType.NUMERIC);
              cell.setCellValue(dt.get(str).toString());
              cell.setCellStyle(coldatastyleright);
            } else {
              cell.setCellType(CellType.NUMERIC);
              cell.setCellValue("");
              cell.setCellStyle(coldatastyleright);
            }
          } else if (colNum == 1) {
            String coldata = "";

            if (dt.get(str) != null) {
              coldata = dt.get(str).toString();
              if (alphanum.ifAlphaNumeric(coldata)) {
                int pno = Integer.parseInt(coldata);
                //								System.out.println("Person Number ::: "+pno);
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue(pno);
                cell.setCellStyle(coldatastyleright);
              } else {
                String pno = coldata;
                //								System.out.println("Person Number ::: "+pno);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(pno);
                cell.setCellStyle(coldatastyleright);
                //								continue;
              }
            } else {
              cell.setCellValue("");
            }

          } else {
            if (dt.get(str) != null) {
              cell.setCellType(CellType.STRING);
              cell.setCellValue(dt.get(str).toString());
              cell.setCellStyle(coldatastyle);
            } else {
              cell.setCellType(CellType.STRING);
              cell.setCellValue("");
              cell.setCellStyle(coldatastyle);
            }
          }
          colNum++;
        }
      }
    } else {
      Row row = sheet.createRow(rowNum);
      Cell cell = row.createCell(colNum);
      cell.setCellValue("No Data Found!!!");
    }

    workbook.write(stream);
    workbook.close();

  }

  public void WriteLWPReport(LwpReport[] lwpmodel, ServletOutputStream stream) throws IOException {

    int count = 0;

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Sheet 1");

    sheet.addIgnoredErrors(CellRangeAddress.valueOf("A1:XFD1048576"), IgnoredErrorType.NUMBER_STORED_AS_TEXT);
    int rowNum = 0;
    int colNum = 0;

    if (lwpmodel.length != 0) {
      List < LwpReport > lwpdetails = Arrays.asList(lwpmodel);

      LinkedHashMap < String, Object > lwpdatamap = null;
      List < Map < String, Object >> lwpdatalist = new ArrayList < > ();

      for (LwpReport lwpdet: lwpdetails) {
        count++;
        lwpdatamap = new LinkedHashMap < > ();
        lwpdatamap.put("S. No", count);
        lwpdatamap.put("Person Number", lwpdet.getPersonNumber());
        lwpdatamap.put("Person Name", lwpdet.getPersonName());
        lwpdatamap.put("Business Unit", lwpdet.getBusinessunit());
        lwpdatamap.put("Status", lwpdet.getLwpstatus());
        lwpdatamap.put("Date", lwpdet.getLwpdate());
        lwpdatamap.put("Description", lwpdet.getLwpdescription());

        lwpdatalist.add(lwpdatamap);
      }

      Set < String > lwpkeysets = lwpdatalist.get(0).keySet();

      XSSFFont headingfont = workbook.createFont();
      headingfont.setFontHeightInPoints((short) 14);
      headingfont.setFontName("Calibri");
      headingfont.setColor(IndexedColors.BLACK.getIndex());
      headingfont.setBold(true);
      headingfont.setItalic(false);

      CellStyle headingstyle = workbook.createCellStyle();
      headingstyle.setAlignment(HorizontalAlignment.LEFT);
      headingstyle.setVerticalAlignment(VerticalAlignment.CENTER);

      // Setting font to style
      headingstyle.setFont(headingfont);

      XSSFFont colheadingfont = workbook.createFont();
      colheadingfont.setFontHeightInPoints((short) 10);
      colheadingfont.setFontName("Calibri");
      colheadingfont.setColor(IndexedColors.BLACK.getIndex());
      colheadingfont.setBold(true);
      colheadingfont.setItalic(false);

      CellStyle colheadingstyle = workbook.createCellStyle();
      colheadingstyle.setAlignment(HorizontalAlignment.LEFT);
      colheadingstyle.setVerticalAlignment(VerticalAlignment.CENTER);
      colheadingstyle.setBorderTop(BorderStyle.THIN);
      colheadingstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      colheadingstyle.setBorderRight(BorderStyle.THIN);
      colheadingstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
      colheadingstyle.setBorderBottom(BorderStyle.THIN);
      colheadingstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      colheadingstyle.setBorderLeft(BorderStyle.THIN);
      colheadingstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

      // Setting font to style
      colheadingstyle.setFont(colheadingfont);
      colheadingstyle.setWrapText(true);

      XSSFFont coldatafont = workbook.createFont();
      coldatafont.setFontHeightInPoints((short) 10);
      coldatafont.setFontName("Calibri");
      coldatafont.setColor(IndexedColors.BLACK.getIndex());
      coldatafont.setBold(false);
      coldatafont.setItalic(false);

      CellStyle coldatastyle = workbook.createCellStyle();
      coldatastyle.setAlignment(HorizontalAlignment.LEFT);
      coldatastyle.setVerticalAlignment(VerticalAlignment.CENTER);
      coldatastyle.setBorderTop(BorderStyle.THIN);
      coldatastyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      coldatastyle.setBorderRight(BorderStyle.THIN);
      coldatastyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
      coldatastyle.setBorderBottom(BorderStyle.THIN);
      coldatastyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      coldatastyle.setBorderLeft(BorderStyle.THIN);
      coldatastyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

      // Setting font to style
      coldatastyle.setFont(coldatafont);
      coldatastyle.setWrapText(true);

      CellStyle coldatastyleright = workbook.createCellStyle();
      coldatastyleright.setAlignment(HorizontalAlignment.RIGHT);
      coldatastyleright.setVerticalAlignment(VerticalAlignment.CENTER);
      coldatastyleright.setBorderTop(BorderStyle.THIN);
      coldatastyleright.setTopBorderColor(IndexedColors.BLACK.getIndex());
      coldatastyleright.setBorderRight(BorderStyle.THIN);
      coldatastyleright.setRightBorderColor(IndexedColors.BLACK.getIndex());
      coldatastyleright.setBorderBottom(BorderStyle.THIN);
      coldatastyleright.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      coldatastyleright.setBorderLeft(BorderStyle.THIN);
      coldatastyleright.setLeftBorderColor(IndexedColors.BLACK.getIndex());

      // Setting font to style
      coldatastyleright.setFont(coldatafont);
      coldatastyleright.setWrapText(true);

      Row row = sheet.createRow(rowNum++);
      Cell headingRowCell = row.createCell(colNum++);
      headingRowCell.setCellValue("UPMRC LWP Report");
      headingRowCell.setCellStyle(headingstyle);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A1:G1"));

      // Print all data from JSON along with headings
      row = sheet.createRow(rowNum++);
      colNum = 0;
      int i = 0;
      for (String str: lwpkeysets) {
        Cell cell = row.createCell(colNum);
        cell.setCellValue(str);
        cell.setCellStyle(colheadingstyle);

        if (colNum == 0 || colNum == 1) {
          sheet.setColumnWidth(i, 2500);
        } else if (colNum == 2 || colNum == 6) {
          sheet.setColumnWidth(i, 6500);
        } else if (colNum == 3) {
          sheet.setColumnWidth(i, 8500);
        } else {
          sheet.setColumnWidth(i, 4000);
        }
        i++;
        colNum++;
      }

      for (Map < String, Object > dt: lwpdatalist) {
        row = sheet.createRow(rowNum++);
        colNum = 0;
        lwpkeysets = dt.keySet();
        for (String str: lwpkeysets) {
          Cell cell = row.createCell(colNum);

          if (colNum == 0) {
            if (dt.get(str) != null) {
              cell.setCellType(CellType.NUMERIC);
              cell.setCellValue(dt.get(str).toString());
              cell.setCellStyle(coldatastyleright);
            } else {
              cell.setCellType(CellType.NUMERIC);
              cell.setCellValue("");
              cell.setCellStyle(coldatastyleright);
            }
          } else if (colNum == 1) {
            String coldata = "";

            if (dt.get(str) != null) {
              coldata = dt.get(str).toString();
              if (alphanum.ifAlphaNumeric(coldata)) {
                int pno = Integer.parseInt(coldata);
                //								System.out.prEmp ID Number ::: "+pno);
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue(pno);
                cell.setCellStyle(coldatastyleright);
              } else {
                String pno = coldata;
                //								System.out.println("Person Number ::: "+pno);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(pno);
                cell.setCellStyle(coldatastyleright);
                //								continue;
              }
            } else {
              cell.setCellValue("");
            }

          } else {
            if (dt.get(str) != null) {
              cell.setCellType(CellType.STRING);
              cell.setCellValue(dt.get(str).toString());
              cell.setCellStyle(coldatastyle);
            } else {
              cell.setCellType(CellType.STRING);
              cell.setCellValue("");
              cell.setCellStyle(coldatastyle);
            }
          }
          colNum++;
        }
      }
    } else {
      Row row = sheet.createRow(rowNum);
      Cell cell = row.createCell(colNum);
      cell.setCellValue("No Data Found!!!");
    }

    workbook.write(stream);
    workbook.close();

  }

  public void WriteVendorwiseDeductionReport(VendorDetailsModel[] vendorModel, VendorSummeryReport[] vendorSummModel,
    String vendormnth, ServletOutputStream stream) throws IOException {

    int count = 0;

    XSSFWorkbook workbook = new XSSFWorkbook();

    XSSFFont headingfont = workbook.createFont();
    headingfont.setFontHeightInPoints((short) 18);
    headingfont.setFontName("Calibri");
    headingfont.setColor(IndexedColors.BLACK.getIndex());
    headingfont.setBold(true);
    headingfont.setItalic(false);

    CellStyle headingstyle = workbook.createCellStyle();
    headingstyle.setAlignment(HorizontalAlignment.CENTER);
    headingstyle.setVerticalAlignment(VerticalAlignment.CENTER);

    // Setting font to style
    headingstyle.setFont(headingfont);

    XSSFFont headingfont1 = workbook.createFont();
    headingfont1.setFontHeightInPoints((short) 14);
    headingfont1.setFontName("Calibri");
    headingfont1.setColor(IndexedColors.BLACK.getIndex());
    headingfont1.setBold(true);
    headingfont1.setItalic(false);
    headingfont1.setUnderline(HSSFFont.U_SINGLE);

    CellStyle headingstyle1 = workbook.createCellStyle();
    headingstyle1.setAlignment(HorizontalAlignment.CENTER);
    headingstyle1.setVerticalAlignment(VerticalAlignment.CENTER);

    // Setting font to style
    headingstyle1.setFont(headingfont1);

    XSSFFont totalfont = workbook.createFont();
    totalfont.setFontHeightInPoints((short) 14);
    totalfont.setFontName("Calibri");
    totalfont.setColor(IndexedColors.BLACK.getIndex());
    totalfont.setBold(true);
    totalfont.setItalic(false);

    CellStyle totalstyle = workbook.createCellStyle();
    totalstyle.setAlignment(HorizontalAlignment.CENTER);
    totalstyle.setVerticalAlignment(VerticalAlignment.CENTER);

    // Setting font to style
    totalstyle.setFont(totalfont);

    XSSFFont txtfont = workbook.createFont();
    txtfont.setFontHeightInPoints((short) 11);
    txtfont.setFontName("Calibri");
    txtfont.setColor(IndexedColors.BLACK.getIndex());
    txtfont.setBold(false);
    txtfont.setItalic(false);

    CellStyle txtstyle = workbook.createCellStyle();
    txtstyle.setAlignment(HorizontalAlignment.RIGHT);
    txtstyle.setVerticalAlignment(VerticalAlignment.CENTER);

    // Setting font to style
    txtstyle.setFont(txtfont);

    CellStyle txtstyle1 = workbook.createCellStyle();
    txtstyle1.setAlignment(HorizontalAlignment.LEFT);
    txtstyle1.setVerticalAlignment(VerticalAlignment.CENTER);
    txtstyle1.setWrapText(true);
    // Setting font to style
    txtstyle.setFont(txtfont);

    XSSFFont txtfont1 = workbook.createFont();
    txtfont1.setFontHeightInPoints((short) 15);
    txtfont1.setFontName("Calibri");
    txtfont1.setColor(IndexedColors.BLACK.getIndex());
    txtfont1.setBold(true);
    txtfont1.setItalic(false);

    CellStyle txtstyle2 = workbook.createCellStyle();
    txtstyle2.setAlignment(HorizontalAlignment.LEFT);
    txtstyle2.setVerticalAlignment(VerticalAlignment.CENTER);
    txtstyle2.setWrapText(true);
    // Setting font to style
    txtstyle2.setFont(txtfont1);

    XSSFFont colheadingfont = workbook.createFont();
    colheadingfont.setFontHeightInPoints((short) 11);
    colheadingfont.setFontName("Calibri");
    colheadingfont.setColor(IndexedColors.BLACK.getIndex());
    colheadingfont.setBold(true);
    colheadingfont.setItalic(false);

    CellStyle colheadingstyle = workbook.createCellStyle();
    colheadingstyle.setAlignment(HorizontalAlignment.LEFT);
    colheadingstyle.setVerticalAlignment(VerticalAlignment.CENTER);
    colheadingstyle.setBorderTop(BorderStyle.THIN);
    colheadingstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
    colheadingstyle.setBorderRight(BorderStyle.THIN);
    colheadingstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
    colheadingstyle.setBorderBottom(BorderStyle.THIN);
    colheadingstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    colheadingstyle.setBorderLeft(BorderStyle.THIN);
    colheadingstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

    // Setting font to style
    colheadingstyle.setFont(colheadingfont);
    colheadingstyle.setWrapText(true);

    XSSFFont coldatafont = workbook.createFont();
    coldatafont.setFontHeightInPoints((short) 11);
    coldatafont.setFontName("Calibri");
    coldatafont.setColor(IndexedColors.BLACK.getIndex());
    coldatafont.setBold(false);
    coldatafont.setItalic(false);

    CellStyle coldatastyle = workbook.createCellStyle();
    coldatastyle.setAlignment(HorizontalAlignment.LEFT);
    coldatastyle.setVerticalAlignment(VerticalAlignment.CENTER);
    coldatastyle.setBorderTop(BorderStyle.THIN);
    coldatastyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
    coldatastyle.setBorderRight(BorderStyle.THIN);
    coldatastyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
    coldatastyle.setBorderBottom(BorderStyle.THIN);
    coldatastyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    coldatastyle.setBorderLeft(BorderStyle.THIN);
    coldatastyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

    // Setting font to style
    coldatastyle.setFont(coldatafont);
    coldatastyle.setWrapText(true);

    CellStyle coldatastyleright = workbook.createCellStyle();
    coldatastyleright.setAlignment(HorizontalAlignment.RIGHT);
    coldatastyleright.setVerticalAlignment(VerticalAlignment.CENTER);
    coldatastyleright.setBorderTop(BorderStyle.THIN);
    coldatastyleright.setTopBorderColor(IndexedColors.BLACK.getIndex());
    coldatastyleright.setBorderRight(BorderStyle.THIN);
    coldatastyleright.setRightBorderColor(IndexedColors.BLACK.getIndex());
    coldatastyleright.setBorderBottom(BorderStyle.THIN);
    coldatastyleright.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    coldatastyleright.setBorderLeft(BorderStyle.THIN);
    coldatastyleright.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    // Setting font to style
    coldatastyleright.setFont(coldatafont);
    coldatastyleright.setWrapText(true);

    CellStyle coldatastylerightformat = workbook.createCellStyle();
    coldatastylerightformat.setAlignment(HorizontalAlignment.RIGHT);
    coldatastylerightformat.setVerticalAlignment(VerticalAlignment.CENTER);
    coldatastylerightformat.setBorderTop(BorderStyle.THIN);
    coldatastylerightformat.setTopBorderColor(IndexedColors.BLACK.getIndex());
    coldatastylerightformat.setBorderRight(BorderStyle.THIN);
    coldatastylerightformat.setRightBorderColor(IndexedColors.BLACK.getIndex());
    coldatastylerightformat.setBorderBottom(BorderStyle.THIN);
    coldatastylerightformat.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    coldatastylerightformat.setBorderLeft(BorderStyle.THIN);
    coldatastylerightformat.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    coldatastylerightformat.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
    // Setting font to style
    coldatastylerightformat.setFont(coldatafont);
    coldatastylerightformat.setWrapText(true);

    int elemsum = 0, elemvalue = 0;
    int rowNum;
    int colNum;
    int total;

    for (VendorDetailsModel vendet: vendorModel) {
      if (!vendet.getPersondetails().isEmpty()) {
        count = 0;
        LinkedHashMap < String, Object > persondatamap = null;
        List < Map < String, Object >> persondatalist = new ArrayList < > ();
        List < Integer > elemsums = new ArrayList < > ();

        total = 0;
        rowNum = 0;
        colNum = 0;

        XSSFSheet vendorsheet = workbook.createSheet("Vendor#" + vendet.getVendornumber());
        vendorsheet.addIgnoredErrors(CellRangeAddress.valueOf("A1:XFD1048576"),
          IgnoredErrorType.NUMBER_STORED_AS_TEXT);

        Row headingrow1 = vendorsheet.createRow(rowNum++);
        Cell headingRowCell = headingrow1.createCell(colNum++);
        headingRowCell.setCellValue("Uttar Pradesh Metro Rail Corporation Limited");
        headingRowCell.setCellStyle(headingstyle);
        vendorsheet.addMergedRegion(CellRangeAddress.valueOf("A1:M1"));

        Row headingrow2 = vendorsheet.createRow(rowNum++);
        colNum = 0;
        Cell headingRowCell1 = headingrow2.createCell(colNum++);
        headingRowCell1.setCellValue(
          "Deductions and Recovery  for the staff deputed from "+((vendet.getDesrc1() == null) ? "" : vendet.getDesrc1())+" to UPMRCL  from salary in " +
          vendormnth.substring(3));
        headingRowCell1.setCellStyle(headingstyle1);
        vendorsheet.addMergedRegion(CellRangeAddress.valueOf("A2:M2"));

        Row payeerow = vendorsheet.createRow(rowNum++);
        colNum = 0;
        Cell vendordataCell1 = payeerow.createCell(colNum++);
        vendordataCell1.setCellValue("Payee Name: ");
        vendordataCell1.setCellStyle(txtstyle);
        vendorsheet.addMergedRegion(CellRangeAddress.valueOf("A3:C3"));

        colNum = 3;
        Cell vendordataCell2 = payeerow.createCell(colNum++);
        if (vendet.getPayeename() != null) {
          vendordataCell2.setCellValue(vendet.getPayeename());
        } else {
          vendordataCell2.setCellValue("");
        }
        vendordataCell2.setCellStyle(txtstyle1);
        vendorsheet.addMergedRegion(CellRangeAddress.valueOf("D3:M3"));

        Row payeeAddress = vendorsheet.createRow(rowNum++);
        colNum = 0;
        Cell vendorAddressdataCell1 = payeeAddress.createCell(colNum++);
        vendorAddressdataCell1.setCellValue("Address: ");
        vendorAddressdataCell1.setCellStyle(txtstyle);
        vendorsheet.addMergedRegion(CellRangeAddress.valueOf("A4:C4"));

        colNum = 3;
        Cell vendordataCell4 = payeeAddress.createCell(colNum++);
        if (vendet.getVendoraddress() != null) {
          vendordataCell4.setCellValue(vendet.getVendoraddress());
        } else {
          vendordataCell4.setCellValue("");
        }
        vendordataCell4.setCellStyle(txtstyle1);
        vendorsheet.addMergedRegion(CellRangeAddress.valueOf("D4:M4"));

        Row payeerow2 = vendorsheet.createRow(rowNum++);

        Row vendorNo = vendorsheet.createRow(rowNum++);
        colNum = 0;
        Cell vendorNodatacell = vendorNo.createCell(colNum++);
        vendorNodatacell.setCellValue("Vendor Number: ");
        vendorNodatacell.setCellStyle(txtstyle);
        vendorsheet.addMergedRegion(CellRangeAddress.valueOf("A6:C6"));

        colNum = 3;
        Cell vendordataCell6 = vendorNo.createCell(colNum++);
        if (vendet.getVendornumber() != null) {
          vendordataCell6.setCellValue(vendet.getVendornumber());
        } else {
          vendordataCell6.setCellValue("");
        }
        vendordataCell6.setCellStyle(txtstyle1);
        Set < String > elementkeys = null;
        if (!vendet.getPersondetails().isEmpty()) {

          for (VendorPersonDetails persondet: vendet.getPersondetails()) {

            count++;
            persondatamap = new LinkedHashMap < > ();
            persondatamap.put("Sno", count);
            persondatamap.put("EMP#", persondet.getPersonnumber());
            persondatamap.put("Emp Name", persondet.getPersonname());
            persondatamap.put("Position", persondet.getPosition());
            persondatamap.put("PF#", persondet.getPfnumber());
            persondatamap.put("PRAN", persondet.getPrannumber());
            persondatamap.put("UAN", persondet.getUannumber());
            persondatamap.put("Pay Scale", persondet.getPayscale());
            persondatamap.put("Basic Pay", persondet.getBasicpay());
            persondatamap.put("Grade Pay", persondet.getGradepay());
            total = persondet.getBasicpay() + persondet.getGradepay();
            persondatamap.put("Total_", total);
            elementkeys = persondet.getElementdata().keySet();

            for (String key: elementkeys) {
              persondatamap.put(key, persondet.getElementdata().get(key));

            }

            persondatalist.add(persondatamap);

          }

          Set < String > vendorkeysets = persondatalist.get(0).keySet();

          // Print all data from JSON along with headings
          Row dataheadingrow = vendorsheet.createRow(rowNum++);
          colNum = 0;
          int i = 0;
          for (String str: vendorkeysets) {
            Cell cell = dataheadingrow.createCell(colNum);
            if (str.equals("Total_")) {
              str = str.substring(0, 5);
            }
            cell.setCellValue(str);
            cell.setCellStyle(colheadingstyle);

            if (colNum == 0 || colNum == 1) {
              vendorsheet.setColumnWidth(i, 2000);
            } else if (colNum == 2 || colNum == 3 || colNum == 4 || colNum == 5 || colNum == 6 ||
              colNum == 7) {
              vendorsheet.setColumnWidth(i, 4000);
            } else {
              vendorsheet.setColumnWidth(i, 3000);
            }
            i++;
            colNum++;
          }

          for (Map < String, Object > dt: persondatalist) {
            Row datarow = vendorsheet.createRow(rowNum++);
            colNum = 0;
            vendorkeysets = dt.keySet();
            for (String str: vendorkeysets) {
              Cell cell = datarow.createCell(colNum);

              if (colNum == 0) {
                if (dt.get(str) != null) {
                  cell.setCellType(CellType.NUMERIC);
                  cell.setCellValue(dt.get(str).toString());
                  cell.setCellStyle(coldatastyleright);
                } else {
                  cell.setCellType(CellType.NUMERIC);
                  cell.setCellValue("");
                  cell.setCellStyle(coldatastyleright);
                }
              } else if (colNum == 1) {
                String coldata = "";

                if (dt.get(str) != null) {
                  coldata = dt.get(str).toString();
                  if (alphanum.ifAlphaNumeric(coldata)) {
                    int pno = Integer.parseInt(coldata);

                    cell.setCellType(CellType.NUMERIC);
                    cell.setCellValue(pno);
                    cell.setCellStyle(coldatastyleright);
                  } else {
                    String pno = coldata;

                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(pno);
                    cell.setCellStyle(coldatastyleright);

                  }
                } else {
                  cell.setCellValue("");
                }

              } else if (colNum == 2 || colNum == 3 || colNum == 4 || colNum == 5 || colNum == 6 || colNum == 7) {
                String coldata = "";

                if (dt.get(str) != null) {
                  coldata = dt.get(str).toString();
                  cell.setCellType(CellType.STRING);
                  cell.setCellValue(coldata);
                  cell.setCellStyle(coldatastyle);
                } else {
                  cell.setCellType(CellType.STRING);
                  cell.setCellValue("");
                  cell.setCellStyle(coldatastyleright);
                }

              } else {
                if (dt.get(str) != null) {
                  cell.setCellType(CellType.NUMERIC);
                  cell.setCellValue(Double.parseDouble(dt.get(str).toString()));
                  cell.setCellStyle(coldatastylerightformat);
                } else if (dt.get(str) == "00.00") {
                  cell.setCellType(CellType.NUMERIC);
                  cell.setCellValue(Double.parseDouble(dt.get(str).toString()));
                  cell.setCellStyle(coldatastylerightformat);
                } else {
                  cell.setCellType(CellType.NUMERIC);
                  cell.setCellValue("00.00");
                  cell.setCellStyle(coldatastylerightformat);
                }
              }
              colNum++;
            }
          }

          colNum = 0;
          Row totalRow = vendorsheet.createRow(rowNum++);
          int rowNumm = rowNum - 1;

          Cell totalCell1 = totalRow.createCell(colNum);
          totalCell1.setCellValue("Total");
          totalCell1.setCellStyle(totalstyle);

          vendorsheet.addMergedRegion(CellRangeAddress.valueOf("A" + rowNum + ":K" + rowNum));
          colNum = 11;

          ArrayList < String > gfg = new ArrayList < String > (Arrays.asList("Sum(L8:L" + rowNumm + ")",
            "Sum(M8:M" + rowNumm + ")", "Sum(N8:N" + rowNumm + ")", "Sum(O8:O" + rowNumm + ")",
            "Sum(P8:P" + rowNumm + ")", "Sum(Q8:Q" + rowNumm + ")", "Sum(R8:R" + rowNumm + ")", "Sum(S8:S" + rowNumm + ")", "Sum(T8:T" + rowNumm + ")"));

          for (int j = 0; j < elementkeys.size(); j++) {
            Cell totalCell = totalRow.createCell(colNum++);
            totalCell.setCellFormula(gfg.get(j));
            totalCell.setCellStyle(coldatastylerightformat);

          }
          
          Row blankRow = vendorsheet.createRow(rowNum++);
          
          Row blankRow1 = vendorsheet.createRow(rowNum++);
          
          Row blankRow2 = vendorsheet.createRow(rowNum++);
          
          Row signrow = vendorsheet.createRow(rowNum++);
          colNum = 13;
          Cell cell = signrow.createCell(colNum++);          
          cell.setCellValue("AM/HR");
          cell.setCellStyle(totalstyle);
          
          Row signrow1 = vendorsheet.createRow(rowNum++);
          colNum = 13;
          Cell cell1 = signrow1.createCell(colNum++);          
          cell1.setCellValue("UPMRCL");
          cell1.setCellStyle(totalstyle);
        }

      }
    }

    // Summary below here

    XSSFSheet vendorsummsheet = workbook.createSheet("Summary");
    vendorsummsheet.addIgnoredErrors(CellRangeAddress.valueOf("A1:XFD1048576"),
      IgnoredErrorType.NUMBER_STORED_AS_TEXT);
    XSSFFont summfont = workbook.createFont();

    summfont.setFontHeightInPoints((short) 14);
    summfont.setFontName("Calibri");
    summfont.setColor(IndexedColors.BLACK.getIndex());
    summfont.setBold(true);

    CellStyle summstyle = workbook.createCellStyle();
    summstyle.setAlignment(HorizontalAlignment.LEFT);
    summstyle.setVerticalAlignment(VerticalAlignment.CENTER);
    summstyle.setBorderLeft(BorderStyle.THIN);
    summstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    summstyle.setBorderRight(BorderStyle.THIN);
    summstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
    summstyle.setBorderTop(BorderStyle.THIN);
    summstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
    summstyle.setBorderBottom(BorderStyle.THIN);
    summstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    summstyle.setFillForegroundColor(IndexedColors.TURQUOISE.index);
    summstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    summstyle.setFont(summfont);

    XSSFFont summdatafont = workbook.createFont();

    summdatafont.setFontHeightInPoints((short) 11);
    summdatafont.setFontName("Calibri");
    summdatafont.setColor(IndexedColors.BLACK.getIndex());
    summdatafont.setBold(true);

    CellStyle summdatastyle = workbook.createCellStyle();
    summdatastyle.setAlignment(HorizontalAlignment.CENTER);
    summdatastyle.setVerticalAlignment(VerticalAlignment.CENTER);
    summdatastyle.setBorderLeft(BorderStyle.THIN);
    summdatastyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    summdatastyle.setBorderRight(BorderStyle.THIN);
    summdatastyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
    summdatastyle.setBorderTop(BorderStyle.THIN);
    summdatastyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
    summdatastyle.setBorderBottom(BorderStyle.THIN);
    summdatastyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    summdatastyle.setFont(summdatafont);

    XSSFFont summdatafont1 = workbook.createFont();

    summdatafont1.setFontHeightInPoints((short) 11);
    summdatafont1.setFontName("Calibri");
    summdatafont1.setColor(IndexedColors.BLACK.getIndex());
    summdatafont1.setBold(false);

    CellStyle summdatastyle1 = workbook.createCellStyle();
    summdatastyle1.setAlignment(HorizontalAlignment.RIGHT);
    summdatastyle1.setVerticalAlignment(VerticalAlignment.CENTER);
    summdatastyle1.setBorderLeft(BorderStyle.THIN);
    summdatastyle1.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    summdatastyle1.setBorderRight(BorderStyle.THIN);
    summdatastyle1.setRightBorderColor(IndexedColors.BLACK.getIndex());
    summdatastyle1.setBorderTop(BorderStyle.THIN);
    summdatastyle1.setTopBorderColor(IndexedColors.BLACK.getIndex());
    summdatastyle1.setBorderBottom(BorderStyle.THIN);
    summdatastyle1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    summdatastyle1.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
    summdatastyle1.setFont(summdatafont);

    rowNum = 0;
    colNum = 0;

    if (vendorSummModel.length != 0) {
      List < VendorSummeryReport > vendsumm = Arrays.asList(vendorSummModel);

      LinkedHashMap < String, Object > vendordatamap = null;
      List < Map < String, Object >> vendordatalist = new ArrayList < > ();

      for (VendorSummeryReport vendorsummrpt: vendsumm) {
        vendordatamap = new LinkedHashMap < > ();
        vendordatamap.put("Vendor", vendorsummrpt.getVendorno());
        vendordatamap.put("Name of the vendor", vendorsummrpt.getVendorname());
        vendordatamap.put("Emp #", vendorsummrpt.getNumberofemployee());
        vendordatamap.put("Amount-INR", vendorsummrpt.getTotalamount());

        vendordatalist.add(vendordatamap);
      }

      Set < String > vendorsummkeysets = vendordatalist.get(0).keySet();

      Row headingrow1 = vendorsummsheet.createRow(rowNum++);
      int i = 0;
      for (String str: vendorsummkeysets) {
        Cell cell = headingrow1.createCell(colNum);
        cell.setCellValue(str);
        cell.setCellStyle(summstyle);

        if (colNum == 0) {
          vendorsummsheet.setColumnWidth(i, 4000);
        } else if (colNum == 1) {
          vendorsummsheet.setColumnWidth(i, 15000);
        } else if (colNum == 2) {
          vendorsummsheet.setColumnWidth(i, 3000);
        } else if (colNum == 3) {
          vendorsummsheet.setColumnWidth(i, 4000);
        }
        i++;
        colNum++;
      }

      for (Map < String, Object > dt: vendordatalist) {
        Row datarow = vendorsummsheet.createRow(rowNum++);
        colNum = 0;
        vendorsummkeysets = dt.keySet();
        for (String str: vendorsummkeysets) {
          Cell cell = datarow.createCell(colNum);
          if (colNum == 0) {
            if (dt.get(str) != null) {
              cell.setCellType(CellType.NUMERIC);
              cell.setCellValue(dt.get(str).toString());
              cell.setCellStyle(summdatastyle);
            } else {
              cell.setCellType(CellType.NUMERIC);
              cell.setCellValue("");
              cell.setCellStyle(summdatastyle);
            }
          } else if (colNum == 1) {
            if (dt.get(str) != null) {
              cell.setCellType(CellType.STRING);
              cell.setCellValue(dt.get(str).toString());
              cell.setCellStyle(summdatastyle);
            } else {
              cell.setCellType(CellType.STRING);
              cell.setCellValue("");
              cell.setCellStyle(summdatastyle);
            }
          } else if (colNum == 2) {
            if (dt.get(str) != null) {
              cell.setCellType(CellType.NUMERIC);
              cell.setCellValue(Integer.parseInt(dt.get(str).toString()));
              cell.setCellStyle(summdatastyle);
            } else {
              cell.setCellType(CellType.NUMERIC);
              cell.setCellValue("0");
              cell.setCellStyle(summdatastyle);
            }
          } else if (colNum == 3) {
            if (dt.get(str) != null) {
              cell.setCellType(CellType.NUMERIC);
              cell.setCellValue(Double.parseDouble(dt.get(str).toString()));
              cell.setCellStyle(summdatastyle1);
            } else {
              cell.setCellType(CellType.NUMERIC);
              cell.setCellValue(00);
              cell.setCellStyle(summdatastyle1);
            }
          }

          colNum++;
        }
      }
    }

    workbook.write(stream);
    workbook.close();

  }

  public void WritePayrollMOD(ModPayBillComModel data, String busname, int calper, ServletOutputStream stream) throws IOException {
    int count = 0;
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Sheet 1");
    sheet.addIgnoredErrors(CellRangeAddress.valueOf("A1:XFD1048576"), IgnoredErrorType.NUMBER_STORED_AS_TEXT);

    int rowNum = 0;
    int colNum = 0;

    XSSFFont headingfont = workbook.createFont();
    headingfont.setFontHeightInPoints((short) 14);
    headingfont.setFontName("Calibri");
    headingfont.setColor(IndexedColors.BLACK.getIndex());
    headingfont.setBold(true);

    CellStyle headingStyle = workbook.createCellStyle();
    headingStyle.setAlignment(HorizontalAlignment.CENTER);
    headingStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    headingStyle.setFont(headingfont);

    XSSFFont headingfont1 = workbook.createFont();
    headingfont1.setFontHeightInPoints((short) 12);
    headingfont1.setFontName("Calibri");
    headingfont1.setColor(IndexedColors.BLACK.getIndex());
    headingfont1.setBold(true);

    CellStyle headingStyle1 = workbook.createCellStyle();
    headingStyle1.setAlignment(HorizontalAlignment.CENTER);
    headingStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
    headingStyle1.setBorderLeft(BorderStyle.THIN);
    headingStyle1.setLeftBorderColor(IndexedColors.BLACK.index);
    headingStyle1.setBorderRight(BorderStyle.THIN);
    headingStyle1.setRightBorderColor(IndexedColors.BLACK.index);
    headingStyle1.setBorderTop(BorderStyle.THIN);
    headingStyle1.setTopBorderColor(IndexedColors.BLACK.index);
    headingStyle1.setBorderBottom(BorderStyle.THIN);
    headingStyle1.setBottomBorderColor(IndexedColors.BLACK.index);
    headingStyle1.setFont(headingfont1);

    XSSFFont headingfont2 = workbook.createFont();
    headingfont2.setFontHeightInPoints((short) 12);
    headingfont2.setFontName("Calibri");
    headingfont2.setColor(IndexedColors.BLACK.getIndex());
    headingfont2.setBold(true);

    CellStyle headingStyle2 = workbook.createCellStyle();
    headingStyle2.setAlignment(HorizontalAlignment.CENTER);
    headingStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
    headingStyle2.setFont(headingfont2);

    CellStyle headingStyle3 = workbook.createCellStyle();
    headingStyle3.setAlignment(HorizontalAlignment.LEFT);
    headingStyle3.setVerticalAlignment(VerticalAlignment.CENTER);
    headingStyle3.setFont(headingfont2);

    XSSFFont dataFont = workbook.createFont();
    dataFont.setFontHeightInPoints((short) 10);
    dataFont.setFontName("Calibri");
    dataFont.setColor(IndexedColors.BLACK.getIndex());

    CellStyle dataStyle = workbook.createCellStyle();
    dataStyle.setAlignment(HorizontalAlignment.RIGHT);
    dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    dataStyle.setFont(dataFont);

    XSSFFont dataFont1 = workbook.createFont();
    dataFont1.setFontHeightInPoints((short) 14);
    dataFont1.setFontName("Calibri");
    dataFont1.setColor(IndexedColors.BLACK.getIndex());

    CellStyle dataStyle1 = workbook.createCellStyle();
    dataStyle1.setAlignment(HorizontalAlignment.CENTER);
    dataStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
    dataStyle1.setFont(dataFont1);

    XSSFFont dataFont2 = workbook.createFont();
    dataFont2.setFontHeightInPoints((short) 12);
    dataFont2.setFontName("Calibri");
    dataFont2.setColor(IndexedColors.BLACK.getIndex());

    CellStyle dataStyle2 = workbook.createCellStyle();
    dataStyle2.setAlignment(HorizontalAlignment.LEFT);
    dataStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
    dataStyle2.setFont(dataFont2);

    CellStyle dataStyle2right = workbook.createCellStyle();
    dataStyle2right.setAlignment(HorizontalAlignment.RIGHT);
    dataStyle2right.setVerticalAlignment(VerticalAlignment.CENTER);
    dataStyle2right.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
    dataStyle2right.setFont(dataFont2);

    if (data != null) {

      List < ModPayBillModel > preg = data.getPaybill();

      Row headingRow = sheet.createRow(rowNum);
      sheet.getRow(rowNum).setHeightInPoints((short) 20);

      rowNum++;
      Cell headingCell = headingRow.createCell(colNum);
      headingCell.setCellValue("Memorandom of Difference " + data.getCur_month() + ": Payroll Register - Summary For " + busname.toUpperCase());
      headingCell.setCellStyle(headingStyle);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A1:L1"));

      Row headingdata0 = sheet.createRow(rowNum);
      sheet.getRow(rowNum).setHeightInPoints((short) 20);
      rowNum++;

      Cell headingdatacell0 = headingdata0.createCell(colNum);
      headingdatacell0.setCellValue("Current Month : " + data.getCur_month());
      headingdatacell0.setCellStyle(dataStyle);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A2:D2"));
      sheet.setColumnWidth(colNum, 5000);

      colNum = 4;
      Cell headingdatacell1 = headingdata0.createCell(colNum);
      headingdatacell1.setCellValue("Previous Month : " + data.getPre_month());
      headingdatacell1.setCellStyle(dataStyle);
      sheet.addMergedRegion(CellRangeAddress.valueOf("E2:H2"));
      sheet.setColumnWidth(colNum, 5000);

      colNum = 8;
      Cell headingdatacell2 = headingdata0.createCell(colNum);
      headingdatacell2.setCellValue("Report For : All Employees");
      headingdatacell2.setCellStyle(dataStyle);
      sheet.addMergedRegion(CellRangeAddress.valueOf("I2:L2"));
      sheet.setColumnWidth(colNum, 5000);

      Row headingdata1 = sheet.createRow(rowNum);
      sheet.getRow(rowNum).setHeightInPoints((short) 20);
      rowNum++;
      colNum = 0;
      Cell headingdatacell3 = headingdata1.createCell(colNum);
      headingdatacell3.setCellValue("Current Mth Payroll Run : " + calper + " " + data.getCur_month().substring(4) + " Calendar Month");
      headingdatacell3.setCellStyle(dataStyle);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A3:D3"));

      colNum = 4;
      Cell headingdatacell4 = headingdata1.createCell(colNum);
      headingdatacell4.setCellValue("Previous Mth Payroll Run : " + (calper - 1) + " " + data.getPre_month().substring(4) + " Calendar Month");
      headingdatacell4.setCellStyle(dataStyle);
      sheet.addMergedRegion(CellRangeAddress.valueOf("E3:H3"));

      colNum = 8;
      Cell headingdatacell5 = headingdata1.createCell(colNum);
      headingdatacell5.setCellValue("Report Type : Summary");
      headingdatacell5.setCellStyle(dataStyle);
      sheet.addMergedRegion(CellRangeAddress.valueOf("I3:L3"));

      Row blankrow = sheet.createRow(rowNum++);
      Row blankrow1 = sheet.createRow(rowNum++);

      Row headingdata2 = sheet.createRow(rowNum);
      sheet.getRow(rowNum).setHeightInPoints((short) 20);
      rowNum++;
      colNum = 0;
      Cell headingdatacell6 = headingdata2.createCell(colNum);
      headingdatacell6.setCellValue("Memorandum of Differences -  " + data.getCur_month() + " : Paybill Summary");
      headingdatacell6.setCellStyle(headingStyle2);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A6:L6"));

      Row headingdata3 = sheet.createRow(rowNum);
      sheet.getRow(rowNum).setHeight((short) 1100);
      rowNum++;
      colNum = 0;
      Cell headingdatacell7 = headingdata3.createCell(colNum);
      headingdatacell7.setCellValue("Payroll Components");
      headingdatacell7.setCellStyle(headingStyle);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A7:C7"));

      colNum = 3;
      Cell headingdatacell8 = headingdata3.createCell(colNum);
      headingdatacell8.setCellValue("Current Month \n(" + data.getCur_month() + ")");
      headingdatacell8.setCellStyle(headingStyle);
      headingdatacell8.getCellStyle().setWrapText(true);
      sheet.addMergedRegion(CellRangeAddress.valueOf("D7:F7"));

      colNum = 6;
      Cell headingdatacell9 = headingdata3.createCell(colNum);
      headingdatacell9.setCellValue("Previous Month \n(" + data.getPre_month() + ")");
      headingdatacell9.setCellStyle(headingStyle);
      headingdatacell9.getCellStyle().setWrapText(true);
      sheet.addMergedRegion(CellRangeAddress.valueOf("G7:I7"));

      colNum = 9;
      Cell headingdatacell10 = headingdata3.createCell(colNum);
      headingdatacell10.setCellValue("Diff \n (Current - Prev) \n ( Increase,[Decrease] )");
      headingdatacell10.setCellStyle(headingStyle);
      headingdatacell10.getCellStyle().setWrapText(true);
      sheet.addMergedRegion(CellRangeAddress.valueOf("J7:L7"));

      Row headingdata4 = sheet.createRow(rowNum);
      sheet.getRow(rowNum).setHeight((short) 800);
      rowNum++;
      colNum = 0;
      Cell headingdatacell11 = headingdata4.createCell(colNum);
      headingdatacell11.setCellValue("No of Employees");
      headingdatacell11.setCellStyle(dataStyle1);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A8:C8"));

      colNum = 3;
      Cell headingdatacell12 = headingdata4.createCell(colNum);
      headingdatacell12.setCellValue(data.getCur_emp());
      headingdatacell12.setCellStyle(dataStyle1);
      headingdatacell12.getCellStyle().setWrapText(true);
      sheet.addMergedRegion(CellRangeAddress.valueOf("D8:F8"));

      colNum = 6;
      Cell headingdatacell13 = headingdata4.createCell(colNum);
      headingdatacell13.setCellValue(data.getPre_emp());
      headingdatacell13.setCellStyle(dataStyle1);
      headingdatacell13.getCellStyle().setWrapText(true);
      sheet.addMergedRegion(CellRangeAddress.valueOf("G8:I8"));

      colNum = 9;
      Cell headingdatacell14 = headingdata4.createCell(colNum);
      headingdatacell14.setCellValue(data.getDiff_emp());
      headingdatacell14.setCellStyle(dataStyle1);
      headingdatacell14.getCellStyle().setWrapText(true);
      sheet.addMergedRegion(CellRangeAddress.valueOf("J8:L8"));

      Row headingdata5 = sheet.createRow(rowNum);
      sheet.getRow(rowNum).setHeight((short) 400);
      rowNum++;
      colNum = 0;

      Cell earncell = headingdata5.createCell(colNum);
      earncell.setCellValue("Earnings");
      earncell.setCellStyle(headingStyle3);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A9:L9"));

      int i = 0;
      for (ModPayBillModel mpbm: preg) {
        if (mpbm.getElement_type().equals("er") && !(mpbm.getCur_pay().equals("0.0") && mpbm.getPre_pay().equals("0.0") && mpbm.getDiff_pay().equals("0.0"))) {
          Row datarow = sheet.createRow(rowNum);
          sheet.getRow(rowNum).setHeight((short) 350);
          rowNum++;
          colNum = 0;

          Cell datacell1 = datarow.createCell(colNum++);
          datacell1.setCellType(CellType.STRING);
          datacell1.setCellValue(mpbm.getAbbreviation());
          datacell1.setCellStyle(dataStyle2);
          sheet.setColumnWidth(i, 5000);
          sheet.addMergedRegion(CellRangeAddress.valueOf("A" + rowNum + ":C" + rowNum));

          colNum = 3;
          Cell datacell2 = datarow.createCell(colNum++);
          datacell2.setCellType(CellType.NUMERIC);
          datacell2.setCellValue(Double.parseDouble(mpbm.getCur_pay()));
          datacell2.setCellStyle(dataStyle2right);
          sheet.addMergedRegion(CellRangeAddress.valueOf("D" + rowNum + ":F" + rowNum));

          colNum = 6;
          Cell datacell3 = datarow.createCell(colNum++);
          datacell3.setCellType(CellType.NUMERIC);
          datacell3.setCellValue(Double.parseDouble(mpbm.getPre_pay()));
          datacell3.setCellStyle(dataStyle2right);
          sheet.addMergedRegion(CellRangeAddress.valueOf("G" + rowNum + ":I" + rowNum));

          colNum = 9;
          Cell datacell4 = datarow.createCell(colNum++);
          datacell4.setCellType(CellType.NUMERIC);
          datacell4.setCellValue(Double.parseDouble(mpbm.getDiff_pay()));
          datacell4.setCellStyle(dataStyle2right);
          sheet.addMergedRegion(CellRangeAddress.valueOf("J" + rowNum + ":L" + rowNum));

          if (colNum == 0) {
            sheet.setColumnWidth(i, 7000);
          } else {
            sheet.setColumnWidth(i, 3000);
          }

          i++;
        }
      }
      Row earningtotalrow = sheet.createRow(rowNum);
      sheet.getRow(rowNum).setHeight((short) 350);
      rowNum++;
      int rowNumm = rowNum - 1;
      colNum = 0;

      Cell totalheadingcell = earningtotalrow.createCell(colNum++);
      totalheadingcell.setCellType(CellType.STRING);
      totalheadingcell.setCellValue("Total Earnings");
      totalheadingcell.setCellStyle(headingStyle3);
      sheet.setColumnWidth(i, 5000);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A" + rowNum + ":C" + rowNum));
      for (ModPayBillModel mpbm: preg) {
        if (mpbm.getElement_type().equals("st") && mpbm.getAbbreviation().equals("Total Earning")) {
          colNum = 3;
          Cell totalcell1 = earningtotalrow.createCell(colNum++);
          totalcell1.setCellType(CellType.NUMERIC);
          totalcell1.setCellValue(Double.parseDouble(mpbm.getCur_pay()));
          totalcell1.setCellStyle(dataStyle2right);
          sheet.addMergedRegion(CellRangeAddress.valueOf("D" + rowNum + ":F" + rowNum));

          colNum = 6;
          Cell totalcell2 = earningtotalrow.createCell(colNum++);
          totalcell2.setCellType(CellType.NUMERIC);
          totalcell2.setCellValue(Double.parseDouble(mpbm.getPre_pay()));
          totalcell2.setCellStyle(dataStyle2right);
          sheet.addMergedRegion(CellRangeAddress.valueOf("G" + rowNum + ":I" + rowNum));

          colNum = 9;
          Cell totalcell3 = earningtotalrow.createCell(colNum++);
          totalcell3.setCellType(CellType.NUMERIC);
          totalcell3.setCellValue(Double.parseDouble(mpbm.getDiff_pay()));
          totalcell3.setCellStyle(dataStyle2right);
          sheet.addMergedRegion(CellRangeAddress.valueOf("J" + rowNum + ":L" + rowNum));
        }
      }

      int rowN = 0;
      Row headingdata6 = sheet.createRow(rowNum);

      sheet.getRow(rowNum).setHeight((short) 400);
      rowNum++;
      rowN = rowNum + 1;
      colNum = 0;

      Cell dedcell = headingdata6.createCell(colNum);
      dedcell.setCellValue("Deductions");
      dedcell.setCellStyle(headingStyle3);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A" + rowNum + ":L" + rowNum));

      i = 0;

      for (ModPayBillModel mpbm: preg) {
        if (mpbm.getElement_type().equals("de") && !(mpbm.getCur_pay().equals("0.0") && mpbm.getPre_pay().equals("0.0") && mpbm.getDiff_pay().equals("0.0"))) {
          Row datarow = sheet.createRow(rowNum);
          sheet.getRow(rowNum).setHeight((short) 350);
          rowNum++;
          colNum = 0;

          Cell datacell1 = datarow.createCell(colNum++);
          datacell1.setCellType(CellType.STRING);
          datacell1.setCellValue(mpbm.getAbbreviation());
          datacell1.setCellStyle(dataStyle2);
          sheet.setColumnWidth(i, 5000);
          sheet.addMergedRegion(CellRangeAddress.valueOf("A" + rowNum + ":C" + rowNum));

          colNum = 3;
          Cell datacell2 = datarow.createCell(colNum++);
          datacell2.setCellType(CellType.NUMERIC);
          datacell2.setCellValue(Double.parseDouble(mpbm.getCur_pay()));
          datacell2.setCellStyle(dataStyle2right);
          sheet.addMergedRegion(CellRangeAddress.valueOf("D" + rowNum + ":F" + rowNum));

          colNum = 6;
          Cell datacell3 = datarow.createCell(colNum++);
          datacell3.setCellType(CellType.NUMERIC);
          datacell3.setCellValue(Double.parseDouble(mpbm.getPre_pay()));
          datacell3.setCellStyle(dataStyle2right);
          sheet.addMergedRegion(CellRangeAddress.valueOf("G" + rowNum + ":I" + rowNum));

          colNum = 9;
          Cell datacell4 = datarow.createCell(colNum++);
          datacell4.setCellType(CellType.NUMERIC);
          datacell4.setCellValue(Double.parseDouble(mpbm.getDiff_pay()));
          datacell4.setCellStyle(dataStyle2right);
          sheet.addMergedRegion(CellRangeAddress.valueOf("J" + rowNum + ":L" + rowNum));

          if (colNum == 0) {
            sheet.setColumnWidth(i, 7000);
          } else {
            sheet.setColumnWidth(i, 3000);
          }

          i++;
        }
      }
      Row deductiontotalrow = sheet.createRow(rowNum);
      sheet.getRow(rowNum).setHeight((short) 350);
      rowNum++;
      rowNumm = rowNum - 1;
      colNum = 0;

      Cell totalheadingcell1 = deductiontotalrow.createCell(colNum++);
      totalheadingcell1.setCellType(CellType.STRING);
      totalheadingcell1.setCellValue("Total Deductions");
      totalheadingcell1.setCellStyle(headingStyle3);
      sheet.setColumnWidth(i, 5000);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A" + rowNum + ":C" + rowNum));
      for (ModPayBillModel mpbm: preg) {
        if (mpbm.getElement_type().equals("st") && mpbm.getAbbreviation().equals("Total Deduction")) {
          colNum = 3;
          Cell totalcell1 = deductiontotalrow.createCell(colNum++);
          totalcell1.setCellType(CellType.NUMERIC);
          System.out.println("TOTAL DED CUR " + mpbm.getCur_pay());
          totalcell1.setCellValue(Double.parseDouble(mpbm.getCur_pay()));
          totalcell1.setCellStyle(dataStyle2right);
          sheet.addMergedRegion(CellRangeAddress.valueOf("D" + rowNum + ":F" + rowNum));

          colNum = 6;
          Cell totalcell2 = deductiontotalrow.createCell(colNum++);
          totalcell2.setCellType(CellType.NUMERIC);
          totalcell2.setCellValue(Double.parseDouble(mpbm.getPre_pay()));
          totalcell2.setCellStyle(dataStyle2right);
          sheet.addMergedRegion(CellRangeAddress.valueOf("G" + rowNum + ":I" + rowNum));

          colNum = 9;
          Cell totalcell3 = deductiontotalrow.createCell(colNum++);
          totalcell3.setCellType(CellType.NUMERIC);
          totalcell3.setCellValue(Double.parseDouble(mpbm.getDiff_pay()));
          totalcell3.setCellStyle(dataStyle2right);
          sheet.addMergedRegion(CellRangeAddress.valueOf("J" + rowNum + ":L" + rowNum));
        }
      }

      Row headingdata7 = sheet.createRow(rowNum);

      sheet.getRow(rowNum).setHeight((short) 400);
      rowNum++;
      rowN = rowNum + 1;
      colNum = 0;

      Cell contributioncell = headingdata7.createCell(colNum);
      contributioncell.setCellValue("Contributions");
      contributioncell.setCellStyle(headingStyle3);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A" + rowNum + ":L" + rowNum));

      i = 0;

      for (ModPayBillModel mpbm: preg) {
        if (mpbm.getElement_type().equals("st") && !(mpbm.getCur_pay().equals("0.0") && mpbm.getPre_pay().equals("0.0") && mpbm.getDiff_pay().equals("0.0")) && !mpbm.getAbbreviation().equals("Total Earning") && !mpbm.getAbbreviation().equals("Total Deduction") && !mpbm.getAbbreviation().equals("Total Contribution") && !mpbm.getAbbreviation().equals("Net Pay")) {
          Row datarow = sheet.createRow(rowNum);
          sheet.getRow(rowNum).setHeight((short) 350);
          rowNum++;
          colNum = 0;

          Cell datacell1 = datarow.createCell(colNum++);
          datacell1.setCellType(CellType.STRING);
          datacell1.setCellValue(mpbm.getAbbreviation());
          datacell1.setCellStyle(dataStyle2);
          sheet.setColumnWidth(i, 5000);
          sheet.addMergedRegion(CellRangeAddress.valueOf("A" + rowNum + ":C" + rowNum));

          colNum = 3;
          Cell datacell2 = datarow.createCell(colNum++);
          datacell2.setCellType(CellType.NUMERIC);
          datacell2.setCellValue(Double.parseDouble(mpbm.getCur_pay()));
          datacell2.setCellStyle(dataStyle2right);
          sheet.addMergedRegion(CellRangeAddress.valueOf("D" + rowNum + ":F" + rowNum));

          colNum = 6;
          Cell datacell3 = datarow.createCell(colNum++);
          datacell3.setCellType(CellType.NUMERIC);
          datacell3.setCellValue(Double.parseDouble(mpbm.getPre_pay()));
          datacell3.setCellStyle(dataStyle2right);
          sheet.addMergedRegion(CellRangeAddress.valueOf("G" + rowNum + ":I" + rowNum));

          colNum = 9;
          Cell datacell4 = datarow.createCell(colNum++);
          datacell4.setCellType(CellType.NUMERIC);
          datacell4.setCellValue(Double.parseDouble(mpbm.getDiff_pay()));
          datacell4.setCellStyle(dataStyle2right);
          sheet.addMergedRegion(CellRangeAddress.valueOf("J" + rowNum + ":L" + rowNum));

          if (colNum == 0) {
            sheet.setColumnWidth(i, 7000);
          } else {
            sheet.setColumnWidth(i, 3000);
          }

          i++;
        }
      }

      Row contritotalrow = sheet.createRow(rowNum);
      sheet.getRow(rowNum).setHeight((short) 350);
      rowNum++;
      rowNumm = rowNum - 1;
      colNum = 0;

      Cell totalheadingcell2 = contritotalrow.createCell(colNum++);
      totalheadingcell2.setCellType(CellType.STRING);
      totalheadingcell2.setCellValue("Total Contributions");
      totalheadingcell2.setCellStyle(headingStyle3);
      sheet.setColumnWidth(i, 5000);
      sheet.addMergedRegion(CellRangeAddress.valueOf("A" + rowNum + ":C" + rowNum));

      for (ModPayBillModel mpbm: preg) {
        if (mpbm.getElement_type().equals("st") && mpbm.getAbbreviation().equals("Total Contribution")) {
          colNum = 3;
          Cell totalcell1 = contritotalrow.createCell(colNum++);
          totalcell1.setCellType(CellType.NUMERIC);
          totalcell1.setCellValue(Double.parseDouble(mpbm.getCur_pay()));
          totalcell1.setCellStyle(dataStyle2right);
          sheet.addMergedRegion(CellRangeAddress.valueOf("D" + rowNum + ":F" + rowNum));

          colNum = 6;
          Cell totalcell2 = contritotalrow.createCell(colNum++);
          totalcell2.setCellType(CellType.NUMERIC);
          totalcell2.setCellValue(Double.parseDouble(mpbm.getPre_pay()));
          totalcell2.setCellStyle(dataStyle2right);
          sheet.addMergedRegion(CellRangeAddress.valueOf("G" + rowNum + ":I" + rowNum));

          colNum = 9;
          Cell totalcell3 = contritotalrow.createCell(colNum++);
          totalcell3.setCellType(CellType.NUMERIC);
          totalcell3.setCellValue(Double.parseDouble(mpbm.getDiff_pay()));
          totalcell3.setCellStyle(dataStyle2right);
          sheet.addMergedRegion(CellRangeAddress.valueOf("J" + rowNum + ":L" + rowNum));
        }
      }

    }

    workbook.write(stream);
    workbook.close();

  }

  public void WriteEmployeeSalaryCard(PayrollSalaryCards[] data, String cal_code, String tocal_code,
    ServletOutputStream stream) throws IOException {
    int count = 0;
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Sheet 1");
    sheet.addIgnoredErrors(CellRangeAddress.valueOf("A1:XFD1048576"), IgnoredErrorType.NUMBER_STORED_AS_TEXT);

    int rowNum = 0;
    int colNum = 0;

    XSSFFont headingfont1 = workbook.createFont();
    headingfont1.setFontHeightInPoints((short) 16);
    headingfont1.setFontName("Calibri");
    headingfont1.setColor(IndexedColors.BLACK.getIndex());
    headingfont1.setBold(true);

    CellStyle headingstyle1 = workbook.createCellStyle();
    headingstyle1.setAlignment(HorizontalAlignment.CENTER);
    headingstyle1.setVerticalAlignment(VerticalAlignment.CENTER);
    headingstyle1.setFont(headingfont1);

    XSSFFont headingfont2 = workbook.createFont();
    headingfont2.setFontHeightInPoints((short) 14);
    headingfont2.setFontName("Calibri");
    headingfont2.setColor(IndexedColors.BLACK.getIndex());
    headingfont2.setBold(true);

    CellStyle headingstyle2 = workbook.createCellStyle();
    headingstyle2.setAlignment(HorizontalAlignment.CENTER);
    headingstyle2.setVerticalAlignment(VerticalAlignment.CENTER);
    headingstyle2.setFont(headingfont2);

    XSSFFont headingfont3 = workbook.createFont();
    headingfont3.setFontHeightInPoints((short) 16);
    headingfont3.setFontName("Calibri");
    headingfont3.setColor(IndexedColors.BLACK.getIndex());
    headingfont3.setBold(false);

    CellStyle headingstyle3 = workbook.createCellStyle();
    headingstyle3.setAlignment(HorizontalAlignment.LEFT);
    headingstyle3.setVerticalAlignment(VerticalAlignment.CENTER);
    headingstyle3.setFont(headingfont3);

    XSSFFont empdatafont1 = workbook.createFont();
    empdatafont1.setFontHeightInPoints((short) 11);
    empdatafont1.setFontName("Calibri");
    empdatafont1.setColor(IndexedColors.BLACK.getIndex());
    empdatafont1.setBold(true);

    CellStyle empdatastyle1 = workbook.createCellStyle();
    empdatastyle1.setAlignment(HorizontalAlignment.LEFT);
    empdatastyle1.setVerticalAlignment(VerticalAlignment.CENTER);
    empdatastyle1.setFont(empdatafont1);

    CellStyle empdatastyle2 = workbook.createCellStyle();
    empdatastyle2.setAlignment(HorizontalAlignment.LEFT);
    empdatastyle2.setVerticalAlignment(VerticalAlignment.CENTER);
    empdatastyle2.setBorderLeft(BorderStyle.THIN);
    empdatastyle2.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    empdatastyle2.setBorderTop(BorderStyle.THIN);
    empdatastyle2.setTopBorderColor(IndexedColors.BLACK.getIndex());
    empdatastyle2.setBorderRight(BorderStyle.THIN);
    empdatastyle2.setRightBorderColor(IndexedColors.BLACK.getIndex());
    empdatastyle2.setBorderBottom(BorderStyle.THIN);
    empdatastyle2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    empdatastyle2.setFont(empdatafont1);

    XSSFFont datafont2 = workbook.createFont();
    datafont2.setFontHeightInPoints((short) 11);
    datafont2.setFontName("Calibri");
    datafont2.setColor(IndexedColors.BLACK.getIndex());
    datafont2.setBold(false);

    CellStyle datastyle2 = workbook.createCellStyle();
    datastyle2.setAlignment(HorizontalAlignment.LEFT);
    datastyle2.setVerticalAlignment(VerticalAlignment.CENTER);
    datastyle2.setBorderLeft(BorderStyle.THIN);
    datastyle2.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    datastyle2.setBorderTop(BorderStyle.THIN);
    datastyle2.setTopBorderColor(IndexedColors.BLACK.getIndex());
    datastyle2.setBorderRight(BorderStyle.THIN);
    datastyle2.setRightBorderColor(IndexedColors.BLACK.getIndex());
    datastyle2.setBorderBottom(BorderStyle.THIN);
    datastyle2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    datastyle2.setFont(datafont2);

    XSSFFont datafontrghtfmt = workbook.createFont();
    datafontrghtfmt.setFontHeightInPoints((short) 11);
    datafontrghtfmt.setFontName("Calibri");
    datafontrghtfmt.setColor(IndexedColors.BLACK.getIndex());
    datafontrghtfmt.setBold(false);

    CellStyle datastylerghtfmt = workbook.createCellStyle();
    datastylerghtfmt.setAlignment(HorizontalAlignment.RIGHT);
    datastylerghtfmt.setVerticalAlignment(VerticalAlignment.CENTER);
    datastylerghtfmt.setBorderLeft(BorderStyle.THIN);
    datastylerghtfmt.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    datastylerghtfmt.setBorderTop(BorderStyle.THIN);
    datastylerghtfmt.setTopBorderColor(IndexedColors.BLACK.getIndex());
    datastylerghtfmt.setBorderRight(BorderStyle.THIN);
    datastylerghtfmt.setRightBorderColor(IndexedColors.BLACK.getIndex());
    datastylerghtfmt.setBorderBottom(BorderStyle.THIN);
    datastylerghtfmt.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    datastylerghtfmt.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
    datastylerghtfmt.setFont(datafontrghtfmt);

    if (data.length != 0) {

      List < PayrollSalaryCards > preg = Arrays.asList(data);

      LinkedHashMap < String, Object > employeedatamap = null;
      List < Map < String, Object >> employeedatalist = new ArrayList < > ();

      Row headingRow1 = sheet.createRow(rowNum++);
      Cell headingcell1 = headingRow1.createCell(colNum++);
      headingcell1.setCellValue("Uttar Pradesh Metro Rail Corporation Ltd");
      sheet.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));
      headingcell1.setCellStyle(headingstyle1);

      Row headingRow2 = sheet.createRow(rowNum++);
      colNum = 0;
      Cell headingcell2 = headingRow2.createCell(colNum++);
      headingcell2.setCellValue("EMPLOYEE  SALARY CARD - " + cal_code.substring(4) + " to " + tocal_code.substring(4));
      sheet.addMergedRegion(CellRangeAddress.valueOf("A2:E2"));
      headingcell2.setCellStyle(headingstyle2);

      Set < String > salcardskeysets = null;
      int i = 0;
      for (PayrollSalaryCards pscs: preg) {
        if (pscs.getElement_amt_list().size() > 0) {
          Row employeedatarow1 = sheet.createRow(rowNum++);
          colNum = 0;
          sheet.setColumnWidth(colNum, 8000);
          Cell empcell1 = employeedatarow1.createCell(colNum++);
          empcell1.setCellValue("Employee # " + pscs.getPerson_number());
          empcell1.setCellStyle(empdatastyle1);

          sheet.setColumnWidth(colNum, 8000);
          Cell empcell2 = employeedatarow1.createCell(colNum++);
          empcell2.setCellValue("Joining Date : " + pscs.getJoining_date());
          empcell2.setCellStyle(empdatastyle1);

          Row employeedatarow2 = sheet.createRow(rowNum++);
          colNum = 0;
          Cell empcell3 = employeedatarow2.createCell(colNum++);
          empcell3.setCellValue("Name: " + pscs.getPerson_name());
          empcell3.setCellStyle(empdatastyle1);

          Cell empcell4 = employeedatarow2.createCell(colNum++);
          empcell4.setCellValue("Designation:" + pscs.getDesignation());
          empcell4.setCellStyle(empdatastyle1);

          Row employeedatarow3 = sheet.createRow(rowNum++);
          Row employeedatarow4 = sheet.createRow(rowNum++);

          Row employeedatarow5 = sheet.createRow(rowNum++);
          colNum = 0;
          Cell empcell5 = employeedatarow5.createCell(colNum++);
          empcell5.setCellValue("Earnings");
          empcell5.setCellStyle(headingstyle3);

          Row employeedatarow6 = sheet.createRow(rowNum++);
          colNum = 0;
          Cell empcell6 = employeedatarow6.createCell(colNum++);
          empcell6.setCellValue("Payroll Element Name");
          empcell6.setCellStyle(empdatastyle2);
          sheet.setColumnWidth(colNum, 5000);
          salcardskeysets = pscs.getElement_amt_list().get(0).keySet();
          for (String str: salcardskeysets) {
            if (!str.equals("element_id") && !str.equals("element_name") && !str.equals("element_type")) {
              Cell empmnthcell = employeedatarow6.createCell(colNum++);
              empmnthcell.setCellValue(str.toUpperCase());
              empmnthcell.setCellStyle(empdatastyle2);
              sheet.setColumnWidth(colNum, 5000);
            }
          }
          Row eanrow = null;
          Cell cell = null;
          for (Map < String, String > dt: pscs.getElement_amt_list()) {
            salcardskeysets = dt.keySet();

            if (dt.get("element_type").equals("er") && !dt.get("total_amt").equals("0.0")) {
              eanrow = sheet.createRow(rowNum++);
              colNum = 0;
              cell = eanrow.createCell(colNum++);
              cell.setCellValue(dt.get("element_name"));
              cell.setCellStyle(datastyle2);

              for (String str: salcardskeysets) {
                if (!str.equals("element_id") && !str.equals("element_name") && !str.equals("element_type")) {
                  Cell empmnthdatacell = eanrow.createCell(colNum++);
                  empmnthdatacell.setCellType(CellType.NUMERIC);
                  empmnthdatacell.setCellValue(Double.parseDouble(dt.get(str)));
                  empmnthdatacell.setCellStyle(datastylerghtfmt);

                }
              }
            }
          }

          for (Map < String, String > dt: pscs.getElement_amt_list()) {
            salcardskeysets = dt.keySet();

            if (dt.get("element_type").equals("st") && dt.get("element_name").equals("Total Earning")) {
              eanrow = sheet.createRow(rowNum++);
              colNum = 0;
              cell = eanrow.createCell(colNum++);
              cell.setCellValue(dt.get("element_name").concat("s(A)"));
              cell.setCellStyle(empdatastyle2);

              for (String str: salcardskeysets) {
                if (!str.equals("element_id") && !str.equals("element_name") && !str.equals("element_type")) {
                  Cell empmnthdatacell = eanrow.createCell(colNum++);
                  empmnthdatacell.setCellType(CellType.NUMERIC);
                  empmnthdatacell.setCellValue(Double.parseDouble(dt.get(str)));
                  empmnthdatacell.setCellStyle(datastylerghtfmt);

                }
              }
            }
          }

          Row blankrow1 = sheet.createRow(rowNum++);

          Row employeedatarow7 = sheet.createRow(rowNum++);
          colNum = 0;
          Cell empcell7 = employeedatarow7.createCell(colNum++);
          empcell7.setCellValue("Deductions");
          empcell7.setCellStyle(headingstyle3);

          Row employeedatarow8 = sheet.createRow(rowNum++);
          colNum = 0;
          Cell empcell8 = employeedatarow8.createCell(colNum++);
          empcell8.setCellValue("Payroll Element Name");
          empcell8.setCellStyle(empdatastyle2);

          for (String sc: salcardskeysets) {
            if (!sc.equals("element_id") && !sc.equals("element_name") && !sc.equals("element_type")) {
              Cell empmnthcell = employeedatarow8.createCell(colNum++);
              empmnthcell.setCellValue(sc.toUpperCase());
              empmnthcell.setCellStyle(empdatastyle2);
            }
          }
          Row dedrow = null;
          Cell dedcell = null;

          for (Map < String, String > dt: pscs.getElement_amt_list()) {
            salcardskeysets = dt.keySet();

            if (dt.get("element_type").equals("de") && !dt.get("total_amt").equals("0.0")) {
              dedrow = sheet.createRow(rowNum++);
              colNum = 0;
              dedcell = dedrow.createCell(colNum++);
              dedcell.setCellValue(dt.get("element_name"));
              dedcell.setCellStyle(datastyle2);

              for (String str: salcardskeysets) {
                if (!str.equals("element_id") && !str.equals("element_name") && !str.equals("element_type")) {
                  Cell empmnthdatacell = dedrow.createCell(colNum++);
                  empmnthdatacell.setCellType(CellType.NUMERIC);
                  empmnthdatacell.setCellValue(Double.parseDouble(dt.get(str)));
                  empmnthdatacell.setCellStyle(datastylerghtfmt);
                }
              }
            }
          }

          for (Map < String, String > dt: pscs.getElement_amt_list()) {
            salcardskeysets = dt.keySet();

            if (dt.get("element_type").equals("st") && dt.get("element_name").equals("Total Deduction")) {
              eanrow = sheet.createRow(rowNum++);
              colNum = 0;
              cell = eanrow.createCell(colNum++);
              cell.setCellValue(dt.get("element_name").concat("s(B)"));
              cell.setCellStyle(empdatastyle2);

              for (String str: salcardskeysets) {
                if (!str.equals("element_id") && !str.equals("element_name") && !str.equals("element_type")) {
                  Cell empmnthdatacell = eanrow.createCell(colNum++);
                  empmnthdatacell.setCellType(CellType.NUMERIC);
                  empmnthdatacell.setCellValue(Double.parseDouble(dt.get(str)));
                  empmnthdatacell.setCellStyle(datastylerghtfmt);

                }
              }
            }
          }

          Row blankrow2 = sheet.createRow(rowNum++);
          Row blankrow3 = sheet.createRow(rowNum++);

          Row netsalrow = sheet.createRow(rowNum++);
          colNum = 0;
          Cell netsalcell1 = netsalrow.createCell(colNum++);
          netsalcell1.setCellValue("Net Salary (A - B)");
          netsalcell1.setCellStyle(empdatastyle2);

          for (Map < String, String > dt: pscs.getElement_amt_list()) {
            salcardskeysets = dt.keySet();

            if (dt.get("element_type").equals("st") && dt.get("element_name").equals("Net Pay")) {
              for (String str: salcardskeysets) {
                if (!str.equals("element_id") && !str.equals("element_name") && !str.equals("element_type")) {
                  Cell empmnthdatacell = netsalrow.createCell(colNum++);
                  empmnthdatacell.setCellType(CellType.NUMERIC);
                  empmnthdatacell.setCellValue(Double.parseDouble(dt.get(str)));
                  empmnthdatacell.setCellStyle(datastylerghtfmt);

                }
              }
            }
          }

          Row blankrow4 = sheet.createRow(rowNum++);

          Row employeedatarow9 = sheet.createRow(rowNum++);
          colNum = 0;
          Cell empcell9 = employeedatarow9.createCell(colNum++);
          empcell9.setCellValue("Employer  Contributions");
          empcell9.setCellStyle(headingstyle3);

          Row employeedatarow10 = sheet.createRow(rowNum++);
          colNum = 0;
          Cell empcell10 = employeedatarow10.createCell(colNum++);
          empcell10.setCellValue("Payroll Element Name");
          empcell10.setCellStyle(empdatastyle2);

          for (String str: salcardskeysets) {
            if (!str.equals("element_id") && !str.equals("element_name") && !str.equals("element_type")) {
              Cell empmnthcell = employeedatarow10.createCell(colNum++);
              empmnthcell.setCellValue(str.toUpperCase());
              empmnthcell.setCellStyle(empdatastyle2);
            }
          }
          Row contrirow = null;
          Cell contricell = null;
          for (Map < String, String > dt: pscs.getElement_amt_list()) {
            salcardskeysets = dt.keySet();

            if (dt.get("element_type").equals("st") && !dt.get("total_amt").equals("0.0") && !dt.get("element_name").equals("Total Earning") && !dt.get("element_name").equals("Total Deduction") && !dt.get("element_name").equals("Total Contribution") && !dt.get("element_name").equals("Net Pay")) {
              contrirow = sheet.createRow(rowNum++);
              colNum = 0;
              contricell = contrirow.createCell(colNum++);
              contricell.setCellValue(dt.get("element_name"));
              contricell.setCellStyle(datastyle2);

              for (String str: salcardskeysets) {
                if (!str.equals("element_id") && !str.equals("element_name") && !str.equals("element_type")) {
                  Cell empmnthdatacell = contrirow.createCell(colNum++);
                  empmnthdatacell.setCellType(CellType.NUMERIC);
                  empmnthdatacell.setCellValue(Double.parseDouble(dt.get(str)));
                  empmnthdatacell.setCellStyle(datastylerghtfmt);
                }
              }
            }
          }
          for (Map < String, String > dt: pscs.getElement_amt_list()) {
            salcardskeysets = dt.keySet();

            if (dt.get("element_type").equals("st") && dt.get("element_name").equals("Total Contribution")) {
              eanrow = sheet.createRow(rowNum++);
              colNum = 0;
              cell = eanrow.createCell(colNum++);
              cell.setCellValue(dt.get("element_name").concat("s"));
              cell.setCellStyle(empdatastyle2);

              for (String str: salcardskeysets) {
                if (!str.equals("element_id") && !str.equals("element_name") && !str.equals("element_type")) {
                  Cell empmnthdatacell = eanrow.createCell(colNum++);
                  empmnthdatacell.setCellType(CellType.NUMERIC);
                  empmnthdatacell.setCellValue(Double.parseDouble(dt.get(str)));
                  empmnthdatacell.setCellStyle(datastylerghtfmt);

                }
              }
            }
          }

          Row blankrow5 = sheet.createRow(rowNum++);
          Row blankrow6 = sheet.createRow(rowNum++);
        }
      }
    } else {
      Row nodatarow = sheet.createRow(rowNum++);
      Cell nodatacell = nodatarow.createCell(colNum++);
      nodatacell.setCellValue("No data found");
    }

    workbook.write(stream);
    workbook.close();
  }

  public void WriteCurrentPayrollRegister(List < Map < String, Object >> data, ServletOutputStream stream,
    String calcode) throws IOException {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Sheet 1");
    sheet.addIgnoredErrors(CellRangeAddress.valueOf("A1:XFD1048576"), IgnoredErrorType.NUMBER_STORED_AS_TEXT);

    int rowNum = 0;
    int colNum = 0;

    if (data != null) {
      Set < String > keysets = data.get(0).keySet();

      System.out.println("Creating excel");
      Row row = sheet.createRow(rowNum++);

      XSSFFont TitleFont = workbook.createFont();
      TitleFont.setBold(true);
      TitleFont.setFontHeight(15);
      TitleFont.setColor(IndexedColors.BLUE_GREY.getIndex());
      CellStyle titleStyle = workbook.createCellStyle();
      titleStyle.setFont(TitleFont);

      Cell TitleCell = row.createCell(0);
      TitleCell.setCellValue("UPMRC Payroll Register For Calendar - " + calcode.substring(4));

      TitleCell.setCellStyle(titleStyle);

      CellStyle redstyle = workbook.createCellStyle();
      redstyle.setFillForegroundColor(IndexedColors.RED.getIndex());
      redstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      //sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 8));

      XSSFFont headerFont = workbook.createFont();

      headerFont.setBold(true);
      headerFont.setColor(IndexedColors.WHITE.getIndex());
      headerFont.setFontHeight(10);
      CellStyle style = workbook.createCellStyle();

      row = sheet.createRow(rowNum++);

      Cell snocell = row.createCell(colNum++); // Done by asmita on 4-May-2021
      snocell.setCellValue("Sr. No");
      style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
      style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      style.setBorderBottom(BorderStyle.THIN);
      style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      style.setBorderTop(BorderStyle.THIN);
      style.setTopBorderColor(IndexedColors.BLACK.getIndex());
      style.setBorderLeft(BorderStyle.THIN);
      style.setLeftBorderColor(IndexedColors.WHITE.getIndex());
      style.setWrapText(true);
      style.setVerticalAlignment(VerticalAlignment.CENTER);
      style.setAlignment(HorizontalAlignment.CENTER);
      style.setFont(headerFont);
      snocell.setCellStyle(style); // Done by asmita on 4-May-2021

      int i = 0;
      int cn = 0; //Done By ASMITA 23-03-2021
      for (String str: keysets) {
        sheet.setColumnWidth(i, 4000);

        if (str.contains("Projected") || str.contains("Perquisites")) {
          style.setFillForegroundColor(IndexedColors.ORCHID.getIndex());
          sheet.setColumnWidth(i, 4500);
        } else {
          style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        }

        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        style.setWrapText(true);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(headerFont);

        Cell cell = row.createCell(colNum++);
        cell.setCellValue(str);
        /* Done by Asmita 23-03-2021 STARTS */
        if (str.contains("Net Pay")) {
          cn = colNum - 1;
        }
        /* Done by Asmita 23-03-2021 ENDS */
        cell.setCellStyle(style);
        i++;
      }

      CellStyle style1 = workbook.createCellStyle();
      style1.setBorderRight(BorderStyle.THIN);
      style1.setRightBorderColor(IndexedColors.BLACK.getIndex());

      int cnt = 0; // Done by asmita on 4-May-2021
      for (Map < String, Object > dt: data) {
        row = sheet.createRow(rowNum++);
        cnt++;
        colNum = 0;
        keysets = dt.keySet();

        Cell sncell = row.createCell(colNum++); // Done by asmita on 4-May-2021
        sncell.setCellValue(cnt); // Done by asmita on 4-May-2021

        for (String str: keysets) {
          XSSFFont cellFont = workbook.createFont();
          cellFont.setFontHeight(10);
          Cell cell = row.createCell(colNum);
          //cell.setCellValue(dt.get(str).toString());
          //|| colNum == 4  || colNum == 5 || colNum == 6

          /* Done by Asmita on 07-May-2021 Starts */
          if (colNum == 1)
          // if(colNum == 0 || colNum == 1 || colNum == 2)
          {
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(Integer.parseInt(dt.get(str).toString()));
          } else if (colNum == 2 || colNum == 3 || colNum == 4 || colNum == 5)
          // if(colNum == 0 || colNum == 1 || colNum == 2)
          {
            cell.setCellType(CellType.STRING);
            cell.setCellValue(dt.get(str).toString());
          } else {
            cell.setCellType(CellType.NUMERIC);

            double d = Double.parseDouble(dt.get(str).toString());
            cell.setCellValue(d);
            //System.out.println("value: "+d);
          }
          /* Done by Asmita on 07-May-2021 Ends */

          /* DONE BY ASMITA 23-03-2021 STARTS */

          if (colNum == cn && cell.getNumericCellValue() < 0) {
            cell.setCellStyle(redstyle);
          }
          /* Done by Asmita 23-03-2021 ENDS */
          colNum++;
          /*if(cnt % 2 == 0){
              CellStyle style = workbook.createCellStyle();
              style.setFillForegroundColor(IndexedColors.LIME.index);
              style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
              style.setBorderRight(BorderStyle.THIN);
              style.setRightBorderColor(IndexedColors.BLACK.getIndex());
              cell.setCellStyle(style);
          } else {*/
          style1.setFont(cellFont);
          cell.setCellStyle(style1);
          //}
        }
      }

    } else {
      Row row = sheet.createRow(rowNum++);
      Cell cell = row.createCell(colNum++);
      cell.setCellValue("No Data Found!!!");
    }
    workbook.write(stream);
    System.out.println("File write successfully");
  }

public void WriteTravelReimbursementReport(ReimbTravellingReport[] traveldata, String dt,ServletOutputStream outputStream) {

	XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Sheet 1");
    sheet.addIgnoredErrors(CellRangeAddress.valueOf("A1:XFD1048576"), IgnoredErrorType.NUMBER_STORED_AS_TEXT);

    int rowNum = 0;
    int colNum = 0;

    if (traveldata != null) {
    	
    	Row row1 = sheet.createRow(rowNum++);
    	Cell headcell1 = row1.createCell(colNum++);
    	headcell1.setCellValue("Uttar Pradesh Metro Rail Corporation Ltd.");
    	sheet.addMergedRegion(CellRangeAddress.valueOf("A"+rowNum+":R"+rowNum));
    	
    	colNum = 0;
    	Row row2 = sheet.createRow(rowNum++);
    	Cell headcell2 = row2.createCell(colNum++);
    	headcell2.setCellValue("No...HR/74-2(A) Vol.-II/2015");
    	sheet.addMergedRegion(CellRangeAddress.valueOf("A"+rowNum+":F"+rowNum));
    	
    	colNum = 6;
    	
    	Cell headcell3 = row2.createCell(colNum++);
    	headcell3.setCellValue("Date: "+dt);
    	sheet.addMergedRegion(CellRangeAddress.valueOf("G"+rowNum+":R"+rowNum));
    	
    	colNum = 0;
    	Row row3 = sheet.createRow(rowNum++);
    	Cell headcell4 = row3.createCell(colNum++);
    	headcell4.setCellValue("Note");
    	sheet.addMergedRegion(CellRangeAddress.valueOf("A"+rowNum+":R"+rowNum));
    	
    	colNum = 0;
    	Row row4 = sheet.createRow(rowNum++);
    	Cell headcell5 = row4.createCell(colNum++);
    	headcell5.setCellValue("Sub:- Reimbursement of Travelling, allowance, Comp TA, Fare, Cont. & Hotel Charges etc.");
    	sheet.addMergedRegion(CellRangeAddress.valueOf("A"+rowNum+":R"+rowNum));
    	
    	colNum = 0;
    	Row row5 = sheet.createRow(rowNum++);
    	Cell headcell6 = row5.createCell(colNum++);
    	headcell6.setCellValue("The undernoted staff and officers of Lucknow of UPMRC have claimed reimbursement of TA, Cont, fare and Hotel Charges on account of performing their outstation duties. The details are as under.");
    	sheet.addMergedRegion(CellRangeAddress.valueOf("A"+rowNum+":R"+rowNum));
    	
    	colNum = 0;
    	Row row6 = sheet.createRow(rowNum++);
    	Cell tableheadcell1 = row6.createCell(colNum++);
    	tableheadcell1.setCellValue("S.No.");
    	
    	
    }
	
	
}

}