/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.controllers;

import com.m3.cardealership.dao.MakeDao;
import com.m3.cardealership.dao.ModelDao;
import com.m3.cardealership.dao.SaleDao;
import com.m3.cardealership.dao.VehicleDao;
import com.m3.cardealership.entities.Make;
import com.m3.cardealership.entities.Sale;
import com.m3.cardealership.entities.Vehicle;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Daniel
 */
@Controller
public class SalesController {
    
    @Autowired 
    SaleDao saleDao;
    
    @Autowired 
    MakeDao makeDao;
    
    @Autowired
    ModelDao modelDao;
    
    @Autowired
    VehicleDao vehicleDao;
    
    @GetMapping("Sales") //MSRP
    public String getVehicles(Model model){
        List<Vehicle> vehicles = vehicleDao.getAllVehicles();
        model.addAttribute("vehicles",vehicles); 
        return "Sales";
    }
    
    @RequestMapping(value={"/", "/sales", "/index.html"}, method= RequestMethod.GET)
    public String getVehicles(Model model, HttpServletRequest request){
        
        String likeQuery = request.getParameter("likeQuery");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        String minYear = request.getParameter("minYear");
        String maxYear = request.getParameter("maxYear");
        
        List<Vehicle> vehicles = vehicleDao.getVehicleBySearch(Boolean.TRUE, likeQuery, minPrice, maxPrice, minYear, maxYear);
        
        model.addAttribute("vehicles", vehicles);
        
        return "Sales";

    @GetMapping("Sales/query") //Search like queries
    public String getVehicles(Model model,String queries){
        return "Sales";
    }
    
    
    
    @GetMapping("Purchase")
    public String addSale(Integer id, Model model) {
        Vehicle vehicle = vehicleDao.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        return "Purchase";
    }
    
    
    @Transactional
    @PostMapping("Purchase/query")
    public String performAddSale(Vehicle vehicle, HttpServletRequest request) {
        
        String salespersonId = request.getParameter("salespersonId");
        String customerName = request.getParameter("customerName");
        String customerEmail = request.getParameter("customerEmail");
        String customerAddress = request.getParameter("customerAddress");
        String customerAddress2 = request.getParameter("customerAddress2");
        String customerCity = request.getParameter("customerCity");
        String customerZipCode = request.getParameter("customerZipCode");
        String purchasePrice = request.getParameter("purchasePrice");
        String purchaseType = request.getParameter("purchaseType");
        
        Sale sale = new Sale();
        sale.setSalespersonId(Integer.valueOf(salespersonId));
        sale.setCustomerName(customerName);
        sale.setCustomerEmail(customerEmail);
        sale.setCustomerAddress(customerAddress);
        sale.setCustomerAddress2(customerAddress2);
        sale.setCustomerCity(customerCity);
        sale.setCustomerZipCode(customerZipCode);
        sale.setPurchasePrice(Integer.valueOf(purchasePrice));
        sale.setPurchaseType(purchaseType);
        
     
        saleDao.addSale(sale);
        vehicleDao.deleteVehicleById(vehicle.getVehicleId());

        return "redirect:/Sales";
    }
    
    
    @PostMapping("addCarMake")
    public String addCarMake(HttpServletRequest request){
        
        String makeName = request.getParameter("makeName");
        String userId = request.getParameter("userId");
        
        Make make = new Make();
        make.setDateAdded(LocalDate.now());
        make.setMakeName(makeName);
        make.setUserId(Integer.valueOf(userId));
        
        makeDao.addMake(make);
        return "addCarMake";
    }
    
    @Transactional
    @PostMapping ("addCarModel")
    public String addCarModel(HttpServletRequest request){
        String makeName = request.getParameter("makeName");
        String modelName = request.getParameter("modelName");
        String userId = request.getParameter("userId");
        
        com.m3.cardealership.entities.Model newModel = new com.m3.cardealership.entities.Model();
        
        newModel.setMakeId(makeDao.getMakeFromMakeName(makeName).getMakeId());
        newModel.setModelName(modelName);
        newModel.setDateAdded(LocalDate.now());
        newModel.setUserId(Integer.valueOf(userId));
        
        modelDao.addModel(newModel);
        
        return "addCarModel";
    }
    
}
