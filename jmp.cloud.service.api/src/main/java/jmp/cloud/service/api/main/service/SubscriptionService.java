package jmp.cloud.service.api.main.service;


import jmp.dto.main.dto.SubscriptionRequestDto;
import jmp.dto.main.dto.SubscriptionResponseDto;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {
    SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto);

    SubscriptionResponseDto updateSubscription(SubscriptionRequestDto user);

    Optional<SubscriptionResponseDto> getSubscription(Long id);

    void deleteSubscription(Long id);

    List<SubscriptionResponseDto> getAllSubscriptions();
}

