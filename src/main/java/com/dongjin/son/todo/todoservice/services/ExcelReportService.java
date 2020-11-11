package com.dongjin.son.todo.todoservice.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReportService {

    public static void main(String[] args) throws FileNotFoundException {

        ExcelReportService es = new ExcelReportService();
        Workbook workbook = new SXSSFWorkbook();
        es.buildExcelDocument(workbook);

        try (FileOutputStream outputStream = new FileOutputStream("JavaBooks.xlsx")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static List<String> headerNames;
//    private static final String CONTENT_DISPOSITION_HEADER_VALUE = "attachment; filename=\"VenueAuditSearchResults.xlsx\"";


    void buildExcelDocument(Workbook workbook) {
//    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

//        List<VenueAuditSummaryDocument> venueAuditSummaryList = (List<VenueAuditSummaryDocument>) model.get(VENUE_AUDIT_SUMMARY_LIST_MODEL_ATTR);
//        Map<String, String> territoryIdNameMap = (Map<String, String>) model.get(TERRIROTY_ID_NAME_MAP_MODEL_ATTR);

        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);

        Sheet reportSheet = workbook.createSheet("Report");

        //create Header
        setHeader(reportSheet, headerStyle);

        //populate rows
        setRows(reportSheet);
//        setRows(venuesAudit, venuesAuditvenueAuditSummaryList, territoryIdNameMap);

        //Setting filter
        reportSheet.setAutoFilter(new CellRangeAddress(0, 0, 0, headerNames.size() - 1));

//        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, CONTENT_DISPOSITION_HEADER_VALUE);
    }

    private void setHeader(Sheet venuesAudit, CellStyle headerStyle) {
        Row header = venuesAudit.createRow(0);

        headerNames = new ArrayList<String>();

        headerNames.add("ID");
        headerNames.add("Title");

        for (int hc = 0; hc < headerNames.size(); hc++) {
            Cell headerCell = header.createCell(hc);
            setHeaderValueAndStyle(headerCell, headerStyle, headerNames.get(hc));
        }
    }

    private void setHeaderValueAndStyle(Cell cell, CellStyle cellStyle, String name) {
        cell.setCellValue(name);
        cell.setCellStyle(cellStyle);
    }

    private void setRows(Sheet venues) {
        int rowNum = 1;
        Row row = venues.createRow(rowNum++);
        int c = 0;
        row.createCell(c++).setCellValue("1");
        row.createCell(c++).setCellValue("snowpiercer");
        //------------------------------
        row = venues.createRow(rowNum++);
        c = 0;
        row.createCell(c++).setCellValue("2");
        row.createCell(c++).setCellValue("game of thrones");


//        for (VenueAuditSummaryDocument venueAuditSummary : venueAuditSummaryList) {
//            Row row = venues.createRow(rowNum++);
//            int c = 0;
//            row.createCell(c++).setCellValue(venueAuditSummary.getVenueSequence());
//            row.createCell(c++).setCellValue(venueAuditSummary.getName());
//            row.createCell(c++).setCellValue(venueAuditSummary.getShortName());
//            row.createCell(c++).setCellValue(venueAuditSummary.getCity());
//            row.createCell(c++).setCellValue(venueAuditSummary.getState());
//            Optional<String> territory = Optional.ofNullable(territoryIdNameMap.get(venueAuditSummary.getTerritoryId()));
//            row.createCell(c++).setCellValue(territory.orElse(venueAuditSummary.getTerritoryId()));
//            row.createCell(c++).setCellValue(venueAuditSummary.getAttributeName());
//            row.createCell(c++).setCellValue(venueAuditSummary.getOldValue());
//            row.createCell(c++).setCellValue(venueAuditSummary.getNewValue());
//            row.createCell(c++).setCellValue(venueAuditSummary.getModifiedDate());
//            row.createCell(c++).setCellValue(venueAuditSummary.getModifiedBy());
//        }
//        System.out.println("Completed creating (adding rows) to VenueAuditSummary Excel View, count:" + venueAuditSummaryList.size());
    }

    /*
    private void setRows(Sheet venues, List<VenueAuditSummaryDocument> venueAuditSummaryList, Map<String, String> territoryIdNameMap) {
        int rowNum = 1;
        for (VenueAuditSummaryDocument venueAuditSummary : venueAuditSummaryList) {
            Row row = venues.createRow(rowNum++);
            int c = 0;
            row.createCell(c++).setCellValue(venueAuditSummary.getVenueSequence());
            row.createCell(c++).setCellValue(venueAuditSummary.getName());
            row.createCell(c++).setCellValue(venueAuditSummary.getShortName());
            row.createCell(c++).setCellValue(venueAuditSummary.getCity());
            row.createCell(c++).setCellValue(venueAuditSummary.getState());
            Optional<String> territory = Optional.ofNullable(territoryIdNameMap.get(venueAuditSummary.getTerritoryId()));
            row.createCell(c++).setCellValue(territory.orElse(venueAuditSummary.getTerritoryId()));
            row.createCell(c++).setCellValue(venueAuditSummary.getAttributeName());
            row.createCell(c++).setCellValue(venueAuditSummary.getOldValue());
            row.createCell(c++).setCellValue(venueAuditSummary.getNewValue());
            row.createCell(c++).setCellValue(venueAuditSummary.getModifiedDate());
            row.createCell(c++).setCellValue(venueAuditSummary.getModifiedBy());
        }
        log.info("Completed creating (adding rows) to VenueAuditSummary Excel View, count:" + venueAuditSummaryList.size());
    }
*/


}
