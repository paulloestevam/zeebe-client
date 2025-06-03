package com.example.zeebeclient.config;

import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZeebeConfig {

    @Value("${zeebe.client.cloud.clusterId}")
    private String clusterId;

    @Value("${zeebe.client.cloud.clientId}")
    private String clientId;

    @Value("${zeebe.client.cloud.clientSecret}")
    private String clientSecret;

    @Value("${zeebe.client.cloud.region}")
    private String region;

    @Bean
    public ZeebeClient zeebeClient() {
        return ZeebeClient.newCloudClientBuilder()
                .withClusterId(clusterId)
                .withClientId(clientId)
                .withClientSecret(clientSecret)
                .withRegion(region)
                .build();
    }
}
