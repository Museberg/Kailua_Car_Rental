package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(){
        return "home/index";
    }
    @GetMapping("/cars/list")
    public String carList(){
        return "home/cars/list";
    }
    @GetMapping("/contracts/list")
    public String contractList(){
        return "home/contracts/list";
    }
    @GetMapping("/renters/list")
    public String renterList(){
        return "home/renters/list";
    }

}
