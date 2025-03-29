//package com.example.invoice.mapper;
//
//
//import com.example.invoice.dto.ReportDTO;
//import com.example.invoice.entity.Invoice;
//import com.example.invoice.entity.Report;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ReportMapper {
//
//    // Convert Report Entity to ReportDTO
//    public ReportDTO toDTO(Report report, Invoice invoice) {
//        if (report == null || invoice == null) {
//            return null;
//        }
//
//        return new ReportDTO(
//                invoice.getId(),
//                invoice.getClient().getName(),
//                report.getReportDate(),
//                invoice.getDueDate(),
//                invoice.getTotalAmount(),
//                report.getTotalRevenue(),
//                invoice.getStatus()
//        );
//    }
//
//    // Convert ReportDTO to Report Entity
//    public Report toEntity(ReportDTO reportDTO) {
//        if (reportDTO == null) {
//            return null;
//        }
//
//        Report report = new Report();
//        // Normally you would handle the mapping back from DTO to entity,
//        // but for this case it might not be necessary as you are generating the report
//        report.setReportDate(reportDTO.getIssueDate());
//        report.setReportType("INVOICE"); // Assume report type is always "INVOICE"
//        report.setTotalRevenue(reportDTO.getAmountPaid());  // Assuming amountPaid is total revenue in this case
//
//        return report;
//    }
//}