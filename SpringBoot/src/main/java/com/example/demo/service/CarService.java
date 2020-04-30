package com.example.demo.service;

import com.example.demo.Model.Car;
import com.example.demo.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepo carRepo;

    public List<Car> fetchAll(){
        return carRepo.fetchAll();
    }

    public Car addCar(Car c){
        return carRepo.addCar(c);
    }

    public Car findCarById(int id){
        return carRepo.findCarById(id);
    }

    public Car updateCar(int id, Car c) {
        return carRepo.updateCar(id, c);
    }

    public boolean deleteCar(int id){
        return carRepo.deleteCar(id);
    }
}
