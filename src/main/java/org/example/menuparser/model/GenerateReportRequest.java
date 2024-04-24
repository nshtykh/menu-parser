package org.example.menuparser.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GenerateReportRequest {
    @NotBlank
    private String url;
    @NotBlank
    private String fileName;
}
