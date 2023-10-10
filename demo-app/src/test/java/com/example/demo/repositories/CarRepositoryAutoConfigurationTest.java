package com.example.demo.repositories;

import com.example.demo.DemoApplication;
import com.example.demo.entities.CarEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = DemoApplication.class)
public class CarRepositoryAutoConfigurationTest {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private DataSource dataSource;
    @Value("${autoconf.db.name}")
    private String autoconfDbName;

    @Test
    public void saveCarWithAutoConfiguredDb() throws SQLException {
        CarEntity car = new CarEntity("1235", "Audi", "black");
        var conn = dataSource.getConnection();
        assertTrue(conn.getMetaData().getURL().contains(autoconfDbName));
        assertDoesNotThrow(() -> carRepository.save(car));
    }
}
