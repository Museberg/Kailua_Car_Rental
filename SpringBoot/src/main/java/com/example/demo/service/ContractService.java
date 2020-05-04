package com.example.demo.service;

import com.example.demo.Model.Contract;
import com.example.demo.repository.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    @Autowired
    ContractRepo contractRepo;

    public List<Contract> fetchAll(){
        return contractRepo.fetchAll();
    }
    public Contract addContract(Contract c){
        return contractRepo.addContract(c);
    }

    public Contract findContractById(int id){
        return contractRepo.findContractById(id);
    }

    public Contract updateContract(int id, Contract c) {
        return contractRepo.updateContact(id, c);
    }

    public boolean deleteContract(int id){
        return contractRepo.deleteContract(id);
    }
}
