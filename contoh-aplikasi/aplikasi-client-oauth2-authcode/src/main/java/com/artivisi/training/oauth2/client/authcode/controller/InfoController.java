package com.artivisi.training.oauth2.client.authcode.controller;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InfoController {
    
    @RequestMapping("info")
    public void info(Model m){
        m.addAttribute("waktu", new Date().toString());
    }
}
