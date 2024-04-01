package org.example.menuparser.service;

import java.util.List;

public interface DataWriter<T> {
    void writeToFile(List<T> data, String fileName);
}
