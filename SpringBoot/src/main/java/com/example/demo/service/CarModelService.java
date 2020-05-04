package com.example.demo.service;

import com.example.demo.Model.CarModel;
import com.example.demo.repository.CarModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelService {

    @Autowired
    CarModelRepo carModelRepo;

    public List<CarModel> fetchAll(){
        return carModelRepo.fetchAll();
    }
}
