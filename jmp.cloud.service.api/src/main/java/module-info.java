module jmp.cloud.service.api {
    requires jmp.dto;
    exports jmp.cloud.service.api.main.repository;
    exports jmp.cloud.service.api.main.service;
    requires java.persistence;
    requires spring.beans;
    requires spring.context;
    requires spring.data.commons;
    requires spring.boot.starter.data.jpa;
    requires spring.data.jpa;
}