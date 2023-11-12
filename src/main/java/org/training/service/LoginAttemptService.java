package org.training.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.training.model.CachedValue;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {
    public static final int MAX_ATTEMPTS = 3;
    public static final int BLOCK_DURATION_SEC = 30;
    private final LoadingCache<String, CachedValue> cachedAttempts;


    public LoginAttemptService() {
        cachedAttempts = CacheBuilder.newBuilder()
                .expireAfterWrite(BLOCK_DURATION_SEC, TimeUnit.SECONDS)
                .build(new CacheLoader<>() {
                    @Override
                    public CachedValue load(String key) throws Exception {
                        return new CachedValue(0, LocalDateTime.now());
                    }
                });
    }

    public void loginFailed(String userName) {
        CachedValue cachedValue = new CachedValue();
        try {
            cachedValue = cachedAttempts.get(userName);
            cachedValue.setAttempts(cachedValue.getAttempts() + 1);
        } catch (ExecutionException e) {
            cachedValue.setAttempts(0);
        }

        if (isBlocked(userName) && cachedValue.getBlockedTimeStamp() == null) {
            cachedValue.setBlockedTimeStamp(LocalDateTime.now());
        }
        cachedAttempts.put(userName, cachedValue);
    }

    public boolean isBlocked(String userName) {
        try {
            return cachedAttempts.get(userName).getAttempts() >= MAX_ATTEMPTS;
        } catch (ExecutionException e) {
            return false;
        }

    }

    public LoadingCache<String, CachedValue> getCachedAttempts() {
      return this.cachedAttempts;
    }
}
