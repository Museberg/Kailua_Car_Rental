package com.example.demo.repository;

import com.example.demo.Model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractRepo {
    @Autowired
    JdbcTemplate template;

    public List<Contract> fetchAll(){
        String sql = "SELECT * from contracts";
        RowMapper<Contract> rowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return template.query(sql, rowMapper);
    }
    public Contract addContract(Contract c){
        return null;
    }
    public Contract findContractById(int id){
        return null;
    }
    public Contract updateContact(int id, Contract c){
        return null;
    }
    public boolean deleteContract(int id){
        return false;
    }
}
