/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.dao;

import com.m3.cardealership.dao.MakeDaoDB.MakeMapper;
import com.m3.cardealership.dao.UserDaoDB.UserMapper;
import com.m3.cardealership.entities.Make;
import com.m3.cardealership.entities.User;
import com.m3.cardealership.entities.Vehicle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author junha
 */
@Repository
public class VehicleDaoDB implements VehicleDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Vehicle getVehicleById(int id) {
        try {
            final String SELECT_Vehicle_BY_ID = "SELECT * FROM vehicle WHERE id = ?";
            Vehicle vehicle = jdbc.queryForObject(SELECT_Vehicle_BY_ID, new VehicleMapper(), id);
            vehicle.setUser(getUserforVehicle(id));
            vehicle.setMake(getMakeforVehicle(id));
            return vehicle;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        final String SELECT_ALL_VEHICLES = "SELECT * FROM vehicle";
        List<Vehicle> vehicles = jdbc.query(SELECT_ALL_VEHICLES, new VehicleMapper());
        associateUserAndMake(vehicles);
        return vehicles;
    }
    
    private void associateUserAndMake(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            vehicle.setUser(getUserforVehicle(vehicle.getVehicleId()));
            vehicle.setMake(getMakeforVehicle(vehicle.getVehicleId()));
        }
    }

    @Override
    @Transactional
    public Vehicle addVehicle(Vehicle vehicle) {
        final String INSERT_VEHICLE = "INSERT INTO vehicle(userId, VIN, Year, MakeId, Model, Color, Interior, BodyStyle, Transmission, Mileage, SalePrice, MSRP, featured, dateAdded, DESCRIPTION) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_VEHICLE,
                vehicle.getUser().getUserId(),
                vehicle.getVIN(),
                vehicle.getMake().getMakeId(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getInterior(),
                vehicle.getBodyStyle(),
                vehicle.getTransmission(),
                vehicle.getMileage(),
                vehicle.getSalePrice(),
                vehicle.getMSRP(),
                vehicle.isFeatured(),
                vehicle.getDateAdded(),
                vehicle.getDESCRIPTION()
        );

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        vehicle.setVehicleId(newId);
//        insertVehicleStudent(vehicle);
        return vehicle;
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
               final String UPDATE_VEHICLE = "UPDATE vehicle SET userId = ?, VIN = ?, Year = ?, MakeId = ?, Model = ?, Color = ?, "
                       + "Interior = ?, BodyStyle = ?, Transmission = ?, Mileage = ?, SalePrice = ?, MSRP = ?, featured = ?, "
                       + "dateAdded = ?, DESCRIPTION = ? WHERE id = ?";
        jdbc.update(UPDATE_VEHICLE, 
                vehicle.getUser().getUserId(),
                vehicle.getVIN(),
                vehicle.getMake().getMakeId(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getInterior(),
                vehicle.getBodyStyle(),
                vehicle.getTransmission(),
                vehicle.getMileage(),
                vehicle.getSalePrice(),
                vehicle.getMSRP(),
                vehicle.isFeatured(),
                vehicle.getDateAdded(),
                vehicle.getDESCRIPTION(),
                vehicle.getVehicleId());
   
    }

    @Override
    public void deleteVehicleById(int id) {
        final String DELETE_VEHICLE = "DELETE FROM vehicle WHERE id = ?";
        jdbc.update(DELETE_VEHICLE, id);
    }

    @Override
    public User getUserforVehicle(int id) {
        final String SELECT_USER_FOR_VEHICLE = "SELECT u.* FROM user u "
                + "JOIN vehicle v ON v.userId = u.userId WHERE v.userId = ?";
        return jdbc.queryForObject(SELECT_USER_FOR_VEHICLE, new UserMapper(), id);
    }

    @Override
    public Make getMakeforVehicle(int id) {
        final String SELECT_MAKE_FOR_VEHICLE = "SELECT m.* FROM  make m "
                + "JOIN vehicle v ON v.MakeId = m.MakeId WHERE v.MakeId = ?";
        return jdbc.queryForObject(SELECT_MAKE_FOR_VEHICLE, new MakeMapper(), id);
    }

    public static final class VehicleMapper implements RowMapper<Vehicle> {

        @Override
        public Vehicle mapRow(ResultSet rs, int index) throws SQLException {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleId(rs.getInt("VehicleId"));
            vehicle.setVIN(rs.getString("VIN"));
            vehicle.setYear(rs.getInt("Year"));
            vehicle.setModel(rs.getString("Model"));
            vehicle.setColor(rs.getString("Color"));
            vehicle.setInterior(rs.getString("Interior"));
            vehicle.setBodyStyle(rs.getString("BodyStyle"));
            vehicle.setTransmission(rs.getString("Transmission"));
            vehicle.setMileage(rs.getInt("Mileage"));
            vehicle.setSalePrice(rs.getInt("SalePrice"));
            vehicle.setMSRP(rs.getInt("MSRP"));
            vehicle.setFeatured(rs.getBoolean("featured"));
            vehicle.setDateAdded(LocalDate.parse(rs.getString("dateAdded")));
            vehicle.setDESCRIPTION(rs.getString("DESCRIPTION"));

            return vehicle;
        }
    }

}
