package org.example.menuparser.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.menuparser.model.GenerateReportRequest;
import org.example.menuparser.service.ReportCreator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CsvController {
    private final ReportCreator reportCreator;

    @PostMapping ("/generate-csv-report")
    public String createReport(@RequestBody @Valid GenerateReportRequest reportRequest) {
        reportCreator.createReport(reportRequest.getUrl(), reportRequest.getFileName());
        return "Data has been successfully saved to the file!";
    }
}
