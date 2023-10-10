package com.example.demo.repositories;

import com.example.demo.DemoApplication;
import com.example.demo.entities.CarEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(properties =
        "spring.autoconfigure.exclude=com.example.autoconfigure.configuration.OwnDataSourceConfiguration")
class CarRepositoryCustomConfigurationTest {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private DataSource dataSource;
    @Value("${custom.db.name}")
    private String customDbName;

    @Test
    public void saveCarWithCustomConfiguredDb() {
        new ApplicationContextRunner().withClassLoader(new FilteredClassLoader(DataSource.class))
                .run((context) -> assertTrue(dataSource.getConnection().getMetaData().getURL().contains(customDbName)));
        CarEntity car = new CarEntity("1235", "Audi", "black");
        assertDoesNotThrow(() -> carRepository.save(car));
    }
}