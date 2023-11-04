module jmp.dto {
    exports jmp.dto.main;
    exports jmp.dto.main.dto;
    requires java.persistence;
    requires spring.beans;
    requires spring.context;
    requires spring.boot.starter.data.jpa;
    requires io.swagger.v3.oas.annotations;
}