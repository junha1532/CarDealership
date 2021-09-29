/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.controllers;

import com.m3.cardealership.dao.ContactDao;
import com.m3.cardealership.dao.VehicleDao;
import com.m3.cardealership.entities.Contact;
import com.m3.cardealership.entities.Vehicle;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Daniel
 */
@Controller
@RequestMapping("/Inventory")
public class InventoryController {

    @Autowired
    VehicleDao vehicleDao;
    
    @Autowired
    ContactDao contactDao;
    

    //pathvariable
    @GetMapping("/Detail/{id}")
    public String vehicleDetail(Integer id, Model model) {
        Vehicle vehicle = vehicleDao.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        return "Detail";
    }
    
    //MSRP
    @GetMapping ("/New")
    public String showNewVehicles(Model model){
        Boolean isNew = true;
        List<Vehicle> vehicles = vehicleDao.getVehicleBySearch(isNew);
        model.addAttribute("vehicles",vehicles);
        return "InventoryNew";
    }
    
    
    //search queries
    @GetMapping ("/New/query")
    public String showNewVehicles(Model model, HttpServletRequest request){
        Boolean isNew = true;
        String likeQuery = request.getParameter("likeQuery");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        String minYear = request.getParameter("minYear");
        String maxYear = request.getParameter("maxYear");
        
        List<Vehicle> vehicles = vehicleDao.getVehicleBySearch(isNew, likeQuery, minPrice, maxPrice, minYear, maxYear);
        model.addAttribute("vehicles",vehicles);
        return "InventoryNew";
    }
    
    
    //sorted by MSRP
    @GetMapping ("/Used")
    public String showUsedVehicles(Model model){
        Boolean isNew = false;
        List<Vehicle> vehicles = vehicleDao.getVehicleBySearch(isNew);
        model.addAttribute("vehicles",vehicles);
        return "UsedInventory";
    }
    
    
    //SEARCH query
    @GetMapping ("/Used/query")
    public String showUsedVehicles(Model model, HttpServletRequest request){
        Boolean isNew = false;
        String likeQuery = request.getParameter("likeQuery");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        String minYear = request.getParameter("minYear");
        String maxYear = request.getParameter("maxYear");
        
        List<Vehicle> vehicles = vehicleDao.getVehicleBySearch(isNew, likeQuery, minPrice, maxPrice, minYear, maxYear);
        model.addAttribute("vehicles",vehicles);
        return "InventoryNew";
    }
    
    
    @PostMapping("/Contact/add")
    public String addContact(Integer id, HttpServletRequest request) {
        Vehicle vehicle = vehicleDao.getVehicleById(id);
        
        String contactName = request.getParameter("customerName");
        String message = request.getParameter("message");
        String contactEmail = request.getParameter("customerEmail");
        String contactPhone = request.getParameter("customerPhone");
        
        Contact contact = new Contact();
        contact.setContactName(contactName);
        contact.setMessage(vehicle.getVIN()+"\n"+message);
        contact.setContactEmail(contactEmail);
        contact.setContactPhone(contactPhone);
        
        contactDao.addContact(contact);
        
        return "redirect:/Detail/" + id;
    }
    
    
}