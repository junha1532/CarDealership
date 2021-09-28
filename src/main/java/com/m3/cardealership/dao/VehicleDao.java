/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.dao;

import com.m3.cardealership.entities.Make;
import com.m3.cardealership.entities.Model;
import com.m3.cardealership.entities.Vehicle;
import java.util.List;

/**
 *
 * @author junha
 */
public interface VehicleDao {
    Vehicle getVehicleById(int id);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getFeaturedVehicles();
    
    Vehicle addVehicle(Vehicle vehicle);
    void updateVehicle(Vehicle vehicle);
    void deleteVehicleById(int id);
    
    Model getModelforVehicle(int id);
    Make getMakeforVehicle(int id);
    
    
    List<Vehicle> getVehicleBySearch(Boolean isNew, String likeQuery, String minPrice, String maxPrice, String minYear, String maxYear);
    List<Vehicle> getVehicleBySearch(Boolean isNew);
}