package com.dynamodb.poc.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("dynamodb")
public class DynamodbProperties {

    private String accessKey;
    private String secretKey;
    private String region;

}
