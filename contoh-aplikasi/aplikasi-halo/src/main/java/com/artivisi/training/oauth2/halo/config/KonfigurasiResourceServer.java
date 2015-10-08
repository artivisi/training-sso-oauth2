package com.artivisi.training.oauth2.halo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
public class KonfigurasiResourceServer {
    private static final String RESOURCE_ID = "belajar";
    
    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends
            ResourceServerConfigurerAdapter {
        
        
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            RemoteTokenServices tokenService = new RemoteTokenServices();
            tokenService.setClientId("clientauthcode");
            tokenService.setClientSecret("123456");
            tokenService.setCheckTokenEndpointUrl("http://localhost:10000/oauth/check_token");
            
            resources
                    .resourceId(RESOURCE_ID)
                    .tokenServices(tokenService);
        }
        

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/api/halo").hasRole("OPERATOR")
                    .antMatchers("/api/waktu").authenticated()
                    ;
        }
    }
}
