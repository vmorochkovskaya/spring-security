package org.training.handler;

import org.training.service.LoginAttemptService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    private LoginAttemptService loginAttemptService;

    private UserDetailsService users;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {
        Object principal = authenticationFailureBadCredentialsEvent.getAuthentication().getPrincipal();
        if (principal instanceof String) {
            String username = (String) principal;
            if (users.loadUserByUsername(username) != null) {
                loginAttemptService.loginFailed(username);
            }
            if (loginAttemptService.isBlocked(username)) {
                throw new LockedException("User is blocked");
            }
        }
    }
}
