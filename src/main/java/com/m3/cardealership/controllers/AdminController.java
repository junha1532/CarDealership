/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.controllers;

import com.m3.cardealership.dao.VehicleDao;
import com.m3.cardealership.entities.Vehicle;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Daniel
 */

@Controller
public class AdminController {
    
    @Autowired
    VehicleDao vehicledao;
    
    @GetMapping("Vehicles")
    public String DisplayVehicles(Model model){
        List<Vehicle> vehicles = vehicledao.getAllVehicles();
        model.addAttribute("vehicles", vehicles);
        return "Vehicles";
    }
    
    @PostMapping("/AddVehicle")
    public String AddVehicle(){
        return "redirect:/Vehicles";
    }
    
    //GetAllVehicles
    //EditVehicle -> editvehicle page with the vehicle
    //editvehicleUPDATE -> modified vehicle
    
    
    //getallusers
    
    //adduser
    //edituser
    
    //addSpecial
    //deleteSpecial
    
    
    
    
}
