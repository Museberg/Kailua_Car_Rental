package com.example.demo.Controller;

import com.example.demo.Model.Car;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CarService carService;

    @GetMapping("/")
    public String index(){
        return "home/index";
    }
    @GetMapping("/cars/list")
    public String carList(Model model){
        List<Car> cars = carService.fetchAll();
        model.addAttribute("cars", cars);
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
