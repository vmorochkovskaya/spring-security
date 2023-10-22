package demo.kafka.service;

import demo.kafka.event.PaymentSent;
import demo.kafka.lib.KafkaClient;
import demo.kafka.rest.api.SendPaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    @Autowired
    private final KafkaClient kafkaClient;

    /**
     * Called via REST request.
     */
    public void process(String key, SendPaymentRequest request) {
        PaymentSent outboundEvent = PaymentSent.newBuilder()
                .setPaymentId(request.getPaymentId())
                .setAmount(request.getAmount())
                .setCurrency(request.getCurrency())
                .setToAccount(request.getToAccount())
                .setFromAccount(request.getFromAccount())
                .build();
        kafkaClient.sendMessage(key, outboundEvent);
    }
}
