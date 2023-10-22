package demo.kafka.lib;

import demo.kafka.event.PaymentSent;
import demo.kafka.properties.KafkaDemoProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaClient {
    @Autowired
    private final KafkaDemoProperties properties;

    @Autowired
    private final KafkaTemplate<String, PaymentSent> kafkaTemplate;

    public void sendMessage(String key, PaymentSent outboundEvent) {
        try {
            final ProducerRecord<String, PaymentSent> record =
                    new ProducerRecord<>(properties.getOutboundTopic(), key, outboundEvent);
            kafkaTemplate.send(record);
        } catch (Exception e) {
            String message = "Error sending message to topic " + properties.getOutboundTopic();
            log.error(message);
            throw new RuntimeException(message, e);
        }
    }
}
