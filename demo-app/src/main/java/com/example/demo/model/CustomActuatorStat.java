package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomActuatorStat {
    private String id;
    private String name;
    private String status;
}
