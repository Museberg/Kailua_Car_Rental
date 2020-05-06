package com.example.demo.repository;

import com.example.demo.Model.Address;
import com.example.demo.Model.Renter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RenterRepo {
    @Autowired
    JdbcTemplate template;

    public List<Renter> fetchAll(){
        String sql = "SELECT *, addresses.id AS ad_id FROM renters JOIN addresses ON renters.address_id = addresses.id ORDER BY renters.id";
        RowMapper<Renter> rowMapper = new BeanPropertyRowMapper<>(Renter.class);
        return template.query(sql, rowMapper);
    }
    public Renter addRenter(Renter r){
        String sqlZipCode = "INSERT INTO zip_codes VALUES (?, ?, ?)";
        template.update(sqlZipCode, r.getZip(), r.getCity(), r.getCountry());

        String sqlAddress = "INSERT INTO addresses VALUES (0, ?, ?, ?, ?)";
        template.update(sqlAddress, r.getStreet_name(), r.getStreet_number(), r.getApartment_number(), r.getZip());

        String sqlGetId = "SELECT id FROM addresses WHERE street_name = ? && street_number = ? && apartment_number = ? && zip_code = ?";
        RowMapper<Address> rowMapper = new BeanPropertyRowMapper<>(Address.class);
        List<Address> addressList = template.query(sqlGetId, rowMapper, r.getStreet_name(), r.getStreet_number(), r.getApartment_number(), r.getZip());
        int addressId = addressList.get(0).getId();


        String sql = "INSERT INTO renters VALUES (0, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, r.getFirst_name(), r.getLast_name(), r.getTelephone(), r.getEmail(), r.getDriver_license(),
                    r.getDriver_since(), addressId);
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
