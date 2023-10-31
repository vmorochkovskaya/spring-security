package jmp.cloud.service.implementation.main;

import jmp.cloud.service.api.main.repository.SubscriptionRepository;
import jmp.cloud.service.api.main.service.SubscriptionService;
import jmp.dto.main.Subscription;
import jmp.dto.main.dto.SubscriptionRequestDto;
import jmp.dto.main.dto.SubscriptionResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = modelMapper.map(subscriptionRequestDto, Subscription.class);
        subscription.setStartDate(LocalDate.now());
        Subscription savedSubs = subscriptionRepository.save(subscription);
        return modelMapper.map(savedSubs, SubscriptionResponseDto.class);
    }


    @Override
    public SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        return createSubscription(subscriptionRequestDto);
    }

    @Override
    public Optional<SubscriptionResponseDto> getSubscription(Long id) {
        Optional<Subscription> subs = subscriptionRepository.findById(id);
        return subs.map(subscription -> modelMapper.map(subscription, SubscriptionResponseDto.class));
    }

    @Override
    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }

    @Override
    public List<SubscriptionResponseDto> getAllSubscriptions() {
        List<Subscription> subscriptionsList = subscriptionRepository.findAll();
        return subscriptionsList.stream()
                .map(subs -> modelMapper.map(subs, SubscriptionResponseDto.class))
                .collect(Collectors.toList());
    }
}
