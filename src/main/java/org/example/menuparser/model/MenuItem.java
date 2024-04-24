package org.example.menuparser.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuItem {
    private String category;
    private String name;
    private String description;
}
