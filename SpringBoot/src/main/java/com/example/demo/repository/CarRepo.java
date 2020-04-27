package com.example.demo.repository;

import com.example.demo.Model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.RowMapper;
import java.util.List;

@Repository
public class CarRepo {
    @Autowired
    JdbcTemplate template;

    public List<Car> fetchAll(){
        String sql = "SELECT *, brand_name as brand FROM cars" +
                    " JOIN models ON cars.model = models.model" +
                    " JOIN brands ON models.brand_id = brands.id";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return template.query(sql, rowMapper);
    }

    public Car addCar(Car c){
        return null;
    }

    public Car findCarById(int id){
        return null;
    }

    public boolean deleteCar(int id){
        return false;
    }
}

