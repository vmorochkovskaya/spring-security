package jmp.cloud.service.implementation.main.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@org.springframework.context.annotation.Configuration
public class MapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        modelMapper.addConverter((MappingContext<String, LocalDate> cont) -> LocalDate.parse(cont.getSource(), formatter),
                String.class,
                LocalDate.class);

        return modelMapper;
    }
}