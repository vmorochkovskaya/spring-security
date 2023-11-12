package org.training;

import com.google.common.cache.LoadingCache;
import org.training.model.CachedValue;
import org.training.service.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginAttemptService loginAttemptService;

    @GetMapping("/login")
    public String login(ModelMap modelMap, @RequestParam("error") Optional<String> error) {
        error.ifPresent(err -> {
            modelMap.addAttribute("error", err);
        });
        return "login";
    }

    @GetMapping("/blocked")
    public String login(Model model) {
        LoadingCache<String, CachedValue> cachedAttempts = loginAttemptService.getCachedAttempts();
        Map<String, CachedValue> cachedValuesMap = cachedAttempts.asMap();
        Map<Object, Object> blockedUsers = cachedValuesMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().getBlockedTimeStamp()));
        if (!blockedUsers.isEmpty()) {
            model.addAttribute("blockedUsers", blockedUsers);
        }
        return "blocked";
    }
}
