package com.artivisi.training.oauth2.client.authcode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class KonfigurasiOauth2Client {
    
    private String urlAuthorization = "http://localhost:10000/oauth/authorize";
    private String urlToken = "http://localhost:10000/oauth/token";
    
    @Bean
    public OAuth2RestOperations oauthClient(OAuth2ClientContext context){
        OAuth2RestTemplate restClient = new OAuth2RestTemplate(resource(), context);
        return restClient;
    }
    
    @Bean
    public OAuth2ProtectedResourceDetails resource(){
        AuthorizationCodeResourceDetails authCodeClient 
                = new AuthorizationCodeResourceDetails();
        authCodeClient.setClientId("clientauthcode");
        authCodeClient.setClientSecret("123456");
        authCodeClient.setUserAuthorizationUri(urlAuthorization);
        authCodeClient.setAccessTokenUri(urlToken);
        return authCodeClient;
    }
}
