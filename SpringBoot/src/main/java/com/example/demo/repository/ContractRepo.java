package com.example.demo.repository;

import com.example.demo.Model.Car;
import com.example.demo.Model.CarModel;
import com.example.demo.Model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.List;

@Repository
public class ContractRepo {
    @Autowired
    JdbcTemplate template;

    public List<Contract> fetchAll(){
        String sql = "SELECT contracts.id, start_date, end_date, start_km, max_km, registration_number, models.model AS car_model, brand_name AS car_brand FROM contracts JOIN cars ON contracts.car_id = cars.id " +
                "JOIN models ON cars.model = models.model JOIN brands ON models.brand_id = brands.id";
        RowMapper<Contract> rowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return template.query(sql, rowMapper);
    }
    public Contract addContract(Contract c){
        String sql = "INSERT INTO contracts VALUES (0, ?, ?, ?, ?, ?, ?)";
        template.update(sql, c.getCar_id(), c.getRenter_id(), c.getStart_date(), c.getEnd_date(), c.getStart_km(), c.getMax_km());
        return null;
    }
    public Contract findContractById(int id){
        String sql = "SELECT contracts.id, start_date, end_date, start_km, max_km, registration_number, models.model AS car_model, brand_name AS car_brand, first_name, last_name FROM contracts JOIN cars ON contracts.car_id = cars.id " +
                "JOIN models ON cars.model = models.model JOIN brands ON models.brand_id = brands.id JOIN renters ON contracts.renter_id = renters.id WHERE contracts.id = ?";
        RowMapper<Contract> rowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return template.queryForObject(sql, rowMapper, id);
    }
    public Contract updateContact(int id, Contract c){
        String sql = "UPDATE contracts SET end_date = ?, max_km = ? WHERE id = ?";
        template.update(sql, c.getEnd_date(), c.getMax_km(), c.getId());
        return null;
    }
    public boolean deleteContract(int id){
        String sql = "DELETE FROM contracts WHERE id = ?";
        template.update(sql, id);
        return false;
    }
}
