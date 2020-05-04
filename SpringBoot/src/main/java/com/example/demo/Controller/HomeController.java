package com.example.demo.Controller;

import com.example.demo.Model.Car;
import com.example.demo.Model.CarModel;
import com.example.demo.service.CarModelService;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CarService carService;
    @Autowired
    CarModelService carModelService;

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

    @GetMapping("/cars/view-one/{id}")
    public String viewCar(@PathVariable("id") int id, Model model) {
        model.addAttribute("car", carService.findCarById(id));
        return "home/cars/view-one";
    }

    @GetMapping("/cars/create")
    public String createCar(Model model) {
        List<CarModel> carModels = carModelService.fetchAll();
        model.addAttribute("carModels", carModels);
        return "home/cars/create";
    }

    @PostMapping("/cars/create")
    public String addCar(@ModelAttribute Car car) {
        carService.addCar(car);
        return "redirect:/cars/list";
    }

    @GetMapping("/cars/edit/{id}")
    public String editCar(@PathVariable("id") int id, Model model) {
        model.addAttribute("car", carService.findCarById(id));
        System.out.println("Edit controller ID: " + id);
        return "home/cars/edit";
    }

    @PostMapping("/cars/update")
    public String updateCar(@ModelAttribute Car car) {
        System.out.println("Controller ID: " + car.getId());
        carService.updateCar(car.getId(), car);
        System.out.println("Controller Odometer: " + car.getOdometer());
        return "redirect:/cars/list";
    }

    @GetMapping("/cars/delete/{id}")
    public String deleteCar(@PathVariable("id") int id) {
        carService.deleteCar(id);
        return "redirect:/cars/list";
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
