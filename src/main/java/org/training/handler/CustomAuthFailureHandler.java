package org.training.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final String BAD_CREDENTIALS_ERR_MSG = "Bad credentials";
    private static final String USER_IS_BLOCKED_ERR_MSG = "User is blocked";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws ServletException, IOException {
        setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);
        String errMessage = BAD_CREDENTIALS_ERR_MSG;
        if (exception.getMessage().contains("blocked")) {
            errMessage = USER_IS_BLOCKED_ERR_MSG;
        }
        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errMessage);
    }
}
