package com.alonso.spring.oauth2.config.tokenservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
public class TokenServiceConfig {
    @Bean
    public RemoteTokenServices tokenService() {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
        tokenService.setClientId("resource-server-clientId");
        tokenService.setClientSecret("resource-server-clientId-password");
        return tokenService;
    }
}
