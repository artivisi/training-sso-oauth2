package com.artivisi.training.oauth2.client.authcode.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InfoController {
    
    @Autowired private OAuth2RestOperations oauthClient;
    @Autowired private OAuth2ClientContext context;
    private String apiHalo = "http://localhost:8080/api/halo";
    
    @RequestMapping("info")
    public void info(Model m){
        
        if(context == null){
            System.out.println("OAuth Client Context is null");
        } else {
            AccessTokenRequest req = context.getAccessTokenRequest();
            if(req == null){
                System.out.println("Access Token Request null");
            } else {
                String state = req.getStateKey();
                System.out.println("State : "+state);
                System.out.println("Authcode : "+req.getAuthorizationCode());
            }
        }
        
        Map<String, Object> hasil = oauthClient.getForObject(apiHalo, HashMap.class);
        System.out.println("Waktu dari server : "+hasil.get("waktu"));
        m.addAttribute("waktu", new Date().toString());
    }
}
