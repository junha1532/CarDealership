/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.controllers;

import com.m3.cardealership.dao.MakeDao;
import com.m3.cardealership.dao.ModelDao;
import com.m3.cardealership.dao.SpecialDao;
import com.m3.cardealership.dao.UserDao;
import com.m3.cardealership.dao.VehicleDao;
import com.m3.cardealership.entities.Make;
import com.m3.cardealership.entities.Special;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import com.m3.cardealership.entities.Vehicle;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Daniel
 */

@Controller
public class AdminController {
    
    @Autowired
    VehicleDao vehicledao;
    
    @Autowired
    UserDao userdao;
    
    @Autowired
    MakeDao makedao;
    
    @Autowired
    ModelDao modeldao;
    
    @Autowired
    SpecialDao specialdao;
    
    @GetMapping("/Admin/Specials")
    public String displaySpecials(Model model){
        List<Special> specials = specialdao.getAllSpecials();
        model.addAttribute("specials", specials);
        return "Specials";
    }
    
    @GetMapping("Vehicles")
    public String displayVehicles(Model model){
        List<Vehicle> vehicles = vehicledao.getAllVehicles();
        model.addAttribute("vehicles", vehicles);
        return "Vehicles";
    }
    
    @PostMapping("/AddVehicle")
    public String addVehicle(HttpServletRequest request){
        Make make = makedao.getMakeFromMakeName(request.getParameter("makeName"));
        com.m3.cardealership.entities.Model model = modeldao.getModelFromModelName(request.getParameter("modelName"));
        
        String VIN = request.getParameter("VIN");
        String color =request.getParameter("color");
        String interior = request.getParameter("interior");
        String bodyStyle = request.getParameter("bodyStyle");
        String transmission = request.getParameter("transmission");
        String DESCRIPTION =  request.getParameter("DESCRIPTION"); ;
        
        int userId = Integer.valueOf(request.getParameter("userId"));
        int year = Integer.valueOf(request.getParameter("year"));
        int mileage = Integer.valueOf(request.getParameter("mileage"));
        int salePrice = Integer.valueOf(request.getParameter("salePrice"));;
        int MSRP = Integer.valueOf(request.getParameter("mileage"));;
        boolean featured = convertToBoolean(request.getParameter("isFeatured"));

        Vehicle vehicle = new Vehicle();
        
        vehicle.setUserId(userId);
        vehicle.setVIN(VIN);
        vehicle.setYear(year);
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setColor(color);
        vehicle.setInterior(interior);
        vehicle.setBodyStyle(bodyStyle);
        vehicle.setTransmission(transmission);
        vehicle.setMileage(mileage);
        vehicle.setSalePrice(salePrice);
        vehicle.setMSRP(MSRP);
        vehicle.setFeatured(featured);
        vehicle.setDateAdded(LocalDate.now());
        vehicle.setDESCRIPTION(DESCRIPTION);
        
 
        return "redirect:/Vehicles";
    }

    @GetMapping("editVehicle")
    public String editVehicle(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Vehicle vehicle = vehicledao.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        return "editVehicle";
    }    
    
    @PostMapping("editVehicle")
    public String performEditTeacher(HttpServletRequest request) {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Vehicle vehicle = vehicledao.getVehicleById(id);
        
        Make make = makedao.getMakeFromMakeName(request.getParameter("makeName"));
        com.m3.cardealership.entities.Model model = modeldao.getModelFromModelName(request.getParameter("modelName"));
        
        String VIN = request.getParameter("VIN");
        String color =request.getParameter("color");
        String interior = request.getParameter("interior");
        String bodyStyle = request.getParameter("bodyStyle");
        String transmission = request.getParameter("transmission");
        String DESCRIPTION =  request.getParameter("DESCRIPTION"); ;
        
        int userId = Integer.valueOf(request.getParameter("userId"));
        int year = Integer.valueOf(request.getParameter("year"));
        int mileage = Integer.valueOf(request.getParameter("mileage"));
        int salePrice = Integer.valueOf(request.getParameter("salePrice"));;
        int MSRP = Integer.valueOf(request.getParameter("mileage"));;
        boolean featured = convertToBoolean(request.getParameter("isFeatured"));
        
        vehicle.setUserId(userId);
        vehicle.setVIN(VIN);
        vehicle.setYear(year);
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setColor(color);
        vehicle.setInterior(interior);
        vehicle.setBodyStyle(bodyStyle);
        vehicle.setTransmission(transmission);
        vehicle.setMileage(mileage);
        vehicle.setSalePrice(salePrice);
        vehicle.setMSRP(MSRP);
        vehicle.setFeatured(featured);
        vehicle.setDateAdded(LocalDate.now());
        vehicle.setDESCRIPTION(DESCRIPTION);
        
        vehicledao.updateVehicle(vehicle);
        
        return "redirect:/Admin/vehicles";
    }

    @GetMapping("users")
    public String displayUsers(Model model){
        model.addAttribute("users",userdao.getAllUsers());
        return "users";
    }

//    @PostMapping("addUser")
//    public String addUser(HttpServletRequest request){
//        return "redirect:/users";
//    }

//    @GetMapping ("editUser")
//    @PostMapping ("editUser")

    @PostMapping ("addSpecial")
    public String addSpecial(HttpServletRequest request){     
        String specialTitle = request.getParameter("specialTitle");
        String specialDescription = request.getParameter("specialDescription");
 
        Special special = new Special();
        special.setSpecialTitle(specialTitle);
        special.setSpecialDescription(specialDescription);
        special.setPromotionAmount(0);//where do we get this from?
        specialdao.addSpecial(special);

        return "redirect:/Admin/Specials";
    }

    @GetMapping("deleteSpecial")
    public String deleteTeacher(HttpServletRequest request, @RequestParam("title") String title) {
        specialdao.deleteSpecialByTitle(title);
        return "redirect:/Admin/Specials";
    }   
    
    private boolean convertToBoolean(String value) {
    boolean returnValue = false;
    if ("1".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) || 
        "true".equalsIgnoreCase(value) || "on".equalsIgnoreCase(value))
        returnValue = true;
    return returnValue;
}
    
    
    
}
