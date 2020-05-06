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
        String sql = "SELECT *, cars.id AS ID, brand_name AS brand FROM cars" +
                    " JOIN models ON cars.model = models.model" +
                    " JOIN brands ON models.brand_id = brands.id ORDER BY cars.id";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return template.query(sql, rowMapper);
    }

    public Car addCar(Car c){
        String sql = "INSERT INTO cars VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, c.getRegistration_number(), c.getFirst_registration(),
                c.getFuel_type(), c.getOdometer(), c.getModel(), c.getCar_type(), c.getGear_type(),
                c.isAir_conditioning(), c.getCcm(), c.getHp(), c.getSeat_type(), c.getSeat_number(), c.isCruise_control());
        return null;
    }

    public Car findCarById(int id){
        String sql = "SELECT *, cars.id AS ID, brand_name AS brand FROM cars" +
                " JOIN models ON cars.model = models.model" +
                " JOIN brands ON models.brand_id = brands.id WHERE cars.id = ?";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public Car updateCar(int id, Car c) {
        String sql = "UPDATE cars SET registration_number = ?, odometer = ? WHERE id = ?";
        template.update(sql, c.getRegistration_number(), c.getOdometer(), c.getId());
        System.out.println(c.getId());
        System.out.println(id);
        return null;
    }

    public boolean deleteCar(int id){
        String sql = "DELETE FROM cars WHERE id = ?";
        template.update(sql, id);
        return false;
    }
}

