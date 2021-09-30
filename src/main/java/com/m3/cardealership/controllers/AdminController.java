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
import com.m3.cardealership.entities.User;
import com.m3.cardealership.entities.Vehicle;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import com.m3.cardealership.entities.Vehicle;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author Daniel
 */

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    UserDao userDao;
    
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
 
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public AdminController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
       this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }
    
    @GetMapping("/vehicles")
    public String displayVehicles(Model model){
        List<Vehicle> vehicles = vehicledao.getAllVehicles();
        model.addAttribute("vehicles", vehicles);
        
        return "Vehicles";
    }
    
    @GetMapping("/vehicles/query")
    public String getVehicles(Model model, HttpServletRequest request){
        String likeQuery = "";
        String minPrice = "0";
        String maxPrice = "9999999999999";
        String minYear = "0";
        String maxYear = "9999";
        if(request.getParameter("likeQuery") != null){
            likeQuery = request.getParameter("likeQuery");
            minPrice = request.getParameter("minPrice");
            maxPrice = request.getParameter("maxPrice");
            minYear = request.getParameter("minYear");
            maxYear = request.getParameter("maxYear");
        }
        List<Vehicle> vehicles = vehicledao.getVehicleBySearch(Boolean.TRUE, likeQuery, minPrice, maxPrice, minYear, maxYear);
        
        model.addAttribute("vehicles", vehicles);
        
        return "Sales";
    }
    
    @PostMapping("/addVehicle")
    public String addVehicle(Vehicle vehicle, HttpServletRequest request){
        Make make = makedao.getMakeFromMakeName(request.getParameter("makeName"));
        com.m3.cardealership.entities.Model model = modeldao.getModelFromModelName(request.getParameter("modelName"));

        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setDateAdded(LocalDate.now());
        
        vehicledao.addVehicle(vehicle);
 
        return "redirect:/vehicles";
    }

    @GetMapping("/editVehicle")
    public String editVehicle(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Vehicle vehicle = vehicledao.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        
        List<Make> makes = makedao.getAllMakes();
        model.addAttribute("makes", makes);
        
        return "editVehicle";
    }    
    
    @PostMapping("/editVehicle")
    public String performEditVehicle(HttpServletRequest request) {
        
        int id = Integer.parseInt(request.getParameter("vehicleId"));
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
        
        return "redirect:/vehicles";
    }
    
    
    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        
        // 1. UserDao adds new user to database
        userDao.addUser(user);
        
        // 2. Reload the in memory user details
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(user.getUserType()));
        inMemoryUserDetailsManager.createUser(new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getPassword(),  grantedAuthorityList));

        return "redirect:/admin";
    }
    
    @GetMapping("/editUser")
    public String getEditUser(Integer id, Model model) {
        User user = userDao.getUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }
    
    @PostMapping("/editUser")
    public String editUser(User user){
        // 1. UserDao updates existing user in database
        userDao.updateUser(user);
        
        // 2. Reload the in memory user details
        inMemoryUserDetailsManager.deleteUser(user.getUserEmail());
        
        return "redirect:/admin";
    }

    @GetMapping("users")
    public String displayUsers(Model model){
        model.addAttribute("users",userdao.getAllUsers());
        return "users";
    }

    @GetMapping("/specials")
    public String displaySpecials(Model model){
        List<Special> specials = specialdao.getAllSpecials();
        model.addAttribute("specials", specials);
        return "specials";
    }
    
    @PostMapping("/specials")
    public String addSpecial(Special special, Model model){        
        specialdao.addSpecial(special);
        return "redirect:/specials";
    }


    @PostMapping("specials/deleteSpecial")
    public String deleteSpecial(HttpServletRequest request) {
        specialdao.deleteSpecialByTitle(request.getParameter("specialTitle"));
        return "redirect:/specials";

    }   
    
    private boolean convertToBoolean(String value) {
    boolean returnValue = false;
    if ("1".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) || 
        "true".equalsIgnoreCase(value) || "on".equalsIgnoreCase(value))
        returnValue = true;
    return returnValue;
}
    
    
    
}
