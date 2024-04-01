package org.example.menuparser.service;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.example.menuparser.model.MenuItem;
import org.springframework.stereotype.Service;

@Service
public class CsvFileWriter implements DataWriter<MenuItem> {
    @Override
    public void writeToFile(List<MenuItem> data, String fileName) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName + ".csv"))) {
            String[] header = {"Category", "Name", "Description"};
            writer.writeNext(header);
            for (MenuItem menu : data) {
                String[] menuItem = {menu.getCategory(), menu.getName(), menu.getDescription()};
                writer.writeNext(menuItem);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
