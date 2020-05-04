package com.example.demo.repository;

import com.example.demo.Model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarModelRepo {
    @Autowired
    JdbcTemplate template;

    public List<CarModel> fetchAll() {
        String sql = "SELECT model, brand_name FROM models JOIN brands ON models.brand_id = brands.id";
        RowMapper<CarModel> rowMapper = new BeanPropertyRowMapper<>(CarModel.class);
        return template.query(sql, rowMapper);
    }
}
