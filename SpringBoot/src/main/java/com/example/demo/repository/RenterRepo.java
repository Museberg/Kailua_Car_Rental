package com.example.demo.repository;

import com.example.demo.Model.Renter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RenterRepo {
    @Autowired
    JdbcTemplate template;

    public List<Renter> fetchAll(){
        String sql = "SELECT * from renters";
        RowMapper<Renter> rowMapper = new BeanPropertyRowMapper<>(Renter.class);
        return template.query(sql, rowMapper);
    }
    public Renter addRenter(Renter r){
        return null;
    }
    public Renter findRenterById(int id){
        return null;
    }
    public Renter updateRenter(int id, Renter r){
        return null;
    }
    public boolean deleteRenter(int id){
        return false;
    }
}
