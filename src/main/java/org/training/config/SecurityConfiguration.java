package org.training.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthenticationFailureHandler failureHandler) throws Exception {
        http
                .authorizeRequests((authorize) -> authorize
                        .requestMatchers((match) -> match.getRequestURI().contains("/blocked"),
                                (match) -> match.getRequestURI().contains("/login*"),
                                (match) -> match.getRequestURI().contains("/about")).permitAll()
                        .requestMatchers((match) -> match.getRequestURI().contains("/info")).hasAuthority("VIEW_INFO")
                        .requestMatchers((match) -> match.getRequestURI().contains("/admin")).hasAuthority("VIEW_ADMIN")
                        .requestMatchers((match) -> match.getRequestURI().contains("/")).authenticated()

                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin.loginPage("/login")
                        .failureHandler(failureHandler)
                        .permitAll()
                )
                .logout(logoutForm ->
                        logoutForm.deleteCookies("JSESSIONID")
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/login")
                                .permitAll()
                )
                .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService users(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public static PasswordEncoder getPasswordEncoder() {
        Map<String, PasswordEncoder> passwordEncoderMap = new HashMap<>();
        passwordEncoderMap.put("bcrypt", new BCryptPasswordEncoder());
        passwordEncoderMap.put("noop", NoOpPasswordEncoder.getInstance());
        return new DelegatingPasswordEncoder("bcrypt", passwordEncoderMap);
    }


}

