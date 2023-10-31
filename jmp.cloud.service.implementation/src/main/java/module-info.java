import jmp.cloud.service.api.main.repository.SubscriptionRepository;
import jmp.cloud.service.api.main.repository.UserRepository;
import jmp.cloud.service.api.main.service.SubscriptionService;
import jmp.cloud.service.api.main.service.UserService;
import jmp.cloud.service.implementation.main.UserServiceImpl;
import jmp.cloud.service.implementation.main.SubscriptionServiceImpl;

module jmp.cloud.service.implementation {
        requires transitive jmp.cloud.service.api;
        uses UserRepository;
        uses SubscriptionRepository;
        requires jmp.dto;
        requires spring.context;
        requires modelmapper;
        requires spring.beans;
        requires spring.data.commons;
        requires spring.boot.starter.data.jpa;
        requires spring.data.jpa;
        exports jmp.cloud.service.implementation.main;
        provides UserService with UserServiceImpl;
        provides SubscriptionService with SubscriptionServiceImpl;
}