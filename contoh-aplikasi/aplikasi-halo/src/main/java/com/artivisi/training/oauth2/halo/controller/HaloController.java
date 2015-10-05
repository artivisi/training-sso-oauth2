package com.artivisi.training.oauth2.halo.controller;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HaloController {
    
    @RequestMapping("/halo")
    public void halo(Model m){
        m.addAttribute("waktu", new Date());
    }
}
