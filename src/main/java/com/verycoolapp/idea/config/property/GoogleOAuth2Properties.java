package com.verycoolapp.idea.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.google")
@Component
@Getter
@Setter
public class GoogleOAuth2Properties {

    private String clientId;
    private String clientSecret;
}
