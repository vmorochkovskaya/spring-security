package com.example.demo.actuator;

import com.example.demo.model.CustomActuatorStat;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.List;

@Endpoint(id = "statistic")
@Component
public class CustomEndpoint {

    @ReadOperation
    public List<CustomActuatorStat> myCustomActuatorEndpoint() {
        return List.of(new CustomActuatorStat("123", "name", "stat"));
    }
}
