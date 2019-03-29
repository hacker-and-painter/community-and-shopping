package com.beautifulsoup.chengfeng.config;

import com.beautifulsoup.chengfeng.kafka.PurchaseInfoListener;
import com.beautifulsoup.chengfeng.kafka.PurchaseInfoDeSerializer;
import com.beautifulsoup.chengfeng.kafka.PurchaseInfoSerializer;
import com.beautifulsoup.chengfeng.prop.KafkaProperties;
import com.beautifulsoup.chengfeng.service.dto.PurchaseInfoDto;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@EnableConfigurationProperties(value = KafkaProperties.class)
public class KafkaConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBrokerList());
        props.put(ProducerConfig.RETRIES_CONFIG, kafkaProperties.getRetries());
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, kafkaProperties.getLinger());
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, PurchaseInfoSerializer.class);
        return props;
    }
    @Bean
    public ProducerFactory<String, PurchaseInfoDto> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBrokerList());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getCGroupId());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 100);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, PurchaseInfoDeSerializer.class);
        return props;
    }
    @Bean
    public ConsumerFactory<String, PurchaseInfoDto> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }
    @Bean
    ConcurrentKafkaListenerContainerFactory<String, PurchaseInfoDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PurchaseInfoDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


    @Bean
    public PurchaseInfoListener myListener() {
        return new PurchaseInfoListener();
    }



    @Bean
    public KafkaTemplate<String,PurchaseInfoDto> kafkaTemplate() {
        KafkaTemplate<String, PurchaseInfoDto> kafkaTemplate = new KafkaTemplate<String,PurchaseInfoDto>(producerFactory());
        return kafkaTemplate;
    }


    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaProperties.getBrokerList());
        return new KafkaAdmin(configs);
    }

}
