/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.dao;

import com.m3.cardealership.dao.MakeDaoDB.MakeMapper;
import com.m3.cardealership.dao.ModelDaoDB.ModelMapper;
import com.m3.cardealership.entities.Make;
import com.m3.cardealership.entities.Model;
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
            final String SELECT_Vehicle_BY_ID = "SELECT * FROM vehicle WHERE vehicleId = ?";
            Vehicle vehicle = jdbc.queryForObject(SELECT_Vehicle_BY_ID, new VehicleMapper(), id);
            vehicle.setModel(getModelforVehicle(id));
            vehicle.setMake(getMakeforVehicle(id));
            return vehicle;
        } catch (DataAccessException ex) {
            return null;
        }
    }
    
    
    @Override
    public List<Vehicle> getFeaturedVehicles() {
        final String SELECT_FEATURED_VEHICLES = "SELECT * FROM vehicle WHERE featured = 1";
        List<Vehicle> featuredVehicles = jdbc.query(SELECT_FEATURED_VEHICLES, new VehicleMapper());
        associateModelAndMake(featuredVehicles);
        return featuredVehicles;
    }
    
    
    public List<Vehicle> getVehicleBySearch(String isNew, String likeQuery, String minPrice, String maxPrice, String minYear, String maxYear){
        String SELECT_FEATURED_VEHICLES = "SELECT v.* FROM VEHICLE v JOIN model m ON m.modelId = v.modelId JOIN make ma on ma.makeId = m.makeId WHERE";

        //new
        if (isNew.equalsIgnoreCase("New")){
            SELECT_FEATURED_VEHICLES += " v.mileage = 0 AND";
        }
        //used
        if(isNew.equalsIgnoreCase("Used")){ 
//            System.out.println("hi");
            SELECT_FEATURED_VEHICLES += " v.mileage >0 AND";
        }

        SELECT_FEATURED_VEHICLES+= " (ma.makeName LIKE '%" + likeQuery +"%' OR m.modelName LIKE '%" + likeQuery + "%'";
        SELECT_FEATURED_VEHICLES += " AND v.salePrice BETWEEN " + minPrice +" AND " + maxPrice;
        SELECT_FEATURED_VEHICLES += " AND v.year BETWEEN " + minYear +" AND " + maxYear;
        SELECT_FEATURED_VEHICLES += ") ORDER BY v.salePrice LIMIT 20";
        List<Vehicle> featuredVehicles = jdbc.query(SELECT_FEATURED_VEHICLES, new VehicleMapper());
        associateModelAndMake(featuredVehicles);
        return featuredVehicles;
    }
    
    @Override
    public List<Vehicle> getAllVehicles() {
        final String SELECT_ALL_VEHICLES = "SELECT * FROM vehicle";
        List<Vehicle> vehicles = jdbc.query(SELECT_ALL_VEHICLES, new VehicleMapper());
        associateModelAndMake(vehicles);
        return vehicles;
    }
    
    private void associateModelAndMake(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            vehicle.setModel(getModelforVehicle(vehicle.getVehicleId()));
            vehicle.setMake(getMakeforVehicle(vehicle.getVehicleId()));
        }
    }

    @Override
    @Transactional
    public Vehicle addVehicle(Vehicle vehicle) {
        final String INSERT_VEHICLE = "INSERT INTO vehicle(userId, VIN, Year, MakeId, ModelId, Color, Interior, BodyStyle, Transmission, Mileage, SalePrice, MSRP, featured, dateAdded, DESCRIPTION) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_VEHICLE,
                vehicle.getUserId(),
                vehicle.getVIN(),
                vehicle.getMake().getMakeId(),
                vehicle.getModel().getModelId(),
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
        return vehicle;
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
               final String UPDATE_VEHICLE = "UPDATE vehicle SET userId = ?, VIN = ?, Year = ?, MakeId = ?, ModelId = ?, Color = ?, "
                       + "Interior = ?, BodyStyle = ?, Transmission = ?, Mileage = ?, SalePrice = ?, MSRP = ?, featured = ?, "
                       + "dateAdded = ?, DESCRIPTION = ? WHERE id = ?";
        jdbc.update(UPDATE_VEHICLE, 
                vehicle.getUserId(),
                vehicle.getVIN(),
                vehicle.getMake().getMakeId(),
                vehicle.getModel().getModelId(),
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
        final String DELETE_VEHICLE = "DELETE FROM vehicle WHERE vehicleId = ?";
        jdbc.update(DELETE_VEHICLE, id);
    }

    @Override
    public Model getModelforVehicle(int id) {
        final String SELECT_MODEL_FOR_VEHICLE = "SELECT m.* FROM model m "
                + "JOIN vehicle v ON v.modelId = m.modelId WHERE v.vehicleId = ?";
        return jdbc.queryForObject(SELECT_MODEL_FOR_VEHICLE, new ModelMapper(), id);
    }

    @Override
    public Make getMakeforVehicle(int id) {
        final String SELECT_MAKE_FOR_VEHICLE = "SELECT m.* FROM  make m "
                + "JOIN vehicle v ON v.MakeId = m.MakeId WHERE v.vehicleId = ?";
        return jdbc.queryForObject(SELECT_MAKE_FOR_VEHICLE, new MakeMapper(), id);
    }



    public static final class VehicleMapper implements RowMapper<Vehicle> {

        @Override
        public Vehicle mapRow(ResultSet rs, int index) throws SQLException {
            Vehicle vehicle = new Vehicle();
            vehicle.setUserId(rs.getInt("userId"));
            vehicle.setVehicleId(rs.getInt("VehicleId"));
            vehicle.setVIN(rs.getString("VIN"));
            vehicle.setYear(rs.getInt("Year"));
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
