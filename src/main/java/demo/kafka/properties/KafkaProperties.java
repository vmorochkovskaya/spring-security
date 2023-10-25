package demo.kafka.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("kafka")
@Getter
@Setter
public class KafkaProperties {
    private String bootstrapServers;
    private String schemaRegistryUrl;
    private String clusterKey;
    private String clusterSecret;
    private String schemaRegistryKey;
    private String schemaRegistrySecret;
}
