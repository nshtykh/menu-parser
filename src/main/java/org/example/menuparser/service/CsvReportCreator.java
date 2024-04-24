package org.example.menuparser.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.menuparser.model.MenuItem;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CsvReportCreator implements ReportCreator {
    private final DataReader<MenuItem> dataReader;
    private final DataWriter<MenuItem> dataWriter;

    @Override
    public void createReport(String resource, String fileName) {
        List<MenuItem> menuItems = dataReader.readData(resource);
        dataWriter.writeToFile(menuItems, fileName);
    }
}
