package com.example.demo.service;

import com.example.demo.Model.Car;
import com.example.demo.Model.Renter;
import com.example.demo.repository.RenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenterService {

    @Autowired
    RenterRepo renterRepo;

    public List<Renter> fetchAll(){
        return renterRepo.fetchAll();
    }

    public Renter addRenter(Renter r){
        return renterRepo.addRenter(r);
    }

    public Renter findRenterById(int id){
        return renterRepo.findRenterById(id);
    }

    public Renter updateRenter(int id, Renter r) {
        return renterRepo.updateRenter(id, r);
    }

    public boolean deleteRenter(int id){
        return renterRepo.deleteRenter(id);
    }
}
