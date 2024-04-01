package org.example.menuparser.service;

import java.util.List;

public interface DataReader<T> {
    List<T> readData(String resource);
}
