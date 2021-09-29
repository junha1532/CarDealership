/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.controllers;

import com.m3.cardealership.dao.SaleDao;
import com.m3.cardealership.dao.VehicleDao;
import com.m3.cardealership.entities.Sale;
import com.m3.cardealership.entities.Vehicle;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Daniel
 */
@Controller
public class SalesController {
    
    @Autowired 
    SaleDao saleDao;
    
    @Autowired
    VehicleDao vehicleDao;
    
    @GetMapping("Sales") //MSRP
    public String getVehicles(Model model){
        return "sales";
    }
    
        
    @GetMapping("Sales") //Search like queries
    public String getVehicles(Model model,String queries){
        return "sales";
    }
    
    
    
    @GetMapping("Purchase")
    public String addSale(Integer id, Model model) {
        Vehicle vehicle = vehicleDao.getVehicleById(id);
        model.addAttribute("course", vehicle);
        return "Purchase";
    }
    
    
    @Transactional
    @PostMapping("Purchase")
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
    
    //addCarMake
    //addCarModel
    
}
