/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.controllers;

import com.m3.cardealership.dao.MakeDao;
import com.m3.cardealership.dao.ModelDao;
import com.m3.cardealership.dao.SaleDao;
import com.m3.cardealership.dao.UserDao;
import com.m3.cardealership.dao.VehicleDao;
import com.m3.cardealership.entities.Make;
import com.m3.cardealership.entities.Sale;
import com.m3.cardealership.entities.Vehicle;
import java.time.LocalDate;
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
@RequestMapping("/sales")
public class SalesController {
    
    @Autowired 
    SaleDao saleDao;
    
    @Autowired 
    MakeDao makeDao;
    
    @Autowired
    ModelDao modelDao;
    
    @Autowired
    VehicleDao vehicleDao;
    
    @Autowired 
    UserDao userdao;
    
    @GetMapping("/purchases")
    public String getPurchases(){
        return "purchases"; 
    }

    
    @RequestMapping(value={"/purchases/query"}, method= RequestMethod.GET)
    public String getVehicles(Model model, HttpServletRequest request){
        String likeQuery = "";
        String minPrice = "0";
        String maxPrice = "9999999999999";
        String minYear = "0";
        String maxYear = "9999";

        String isNew = "Both";
//        if(request.getParameter("likeQuery") != null){
            likeQuery = request.getParameter("likeQuery");
            minPrice = request.getParameter("minPrice");
            maxPrice = request.getParameter("maxPrice");
            minYear = request.getParameter("minYear");
            maxYear = request.getParameter("maxYear");
//        }

        isNew = request.getParameter("isNew");
        
        
        List<Vehicle> vehicles = vehicleDao.getVehicleBySearch(isNew, likeQuery, minPrice, maxPrice, minYear, maxYear);
        
        
        model.addAttribute("vehicles", vehicles);       
        return "purchases";
    }

    
   
    @GetMapping("/purchase")
    public String addSale(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Vehicle vehicle = vehicleDao.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        return "Purchase";
    }
    
    
    @Transactional
    @PostMapping("PurchaseSend")
    public String performAddSale(Vehicle vehicle, HttpServletRequest request) {
        
        String customerName = request.getParameter("customerName");
        String customerEmail = request.getParameter("customerEmail");
        String customerAddress = request.getParameter("customerAddress");
        String customerAddress2 = request.getParameter("customerAddress2");
        String customerCity = request.getParameter("customerCity");
        String customerZipCode = request.getParameter("customerZipCode");
        String customerState = request.getParameter("customerState");
        String purchasePrice = request.getParameter("purchasePrice");
        String purchaseType = request.getParameter("purchaseType");
        String userName = request.getParameter("userName");
        
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        int salespersonId = userdao.getUserIdByEmail(userName);

        Sale sale = new Sale();
        sale.setSalespersonId(salespersonId);
        sale.setCustomerName(customerName);
        sale.setCustomerEmail(customerEmail);
        sale.setCustomerAddress(customerAddress);
        sale.setCustomerAddress2(customerAddress2);
        sale.setCustomerCity(customerCity);
        sale.setCustomerZipCode(customerZipCode);
        sale.setPurchasePrice(Integer.valueOf(purchasePrice));
        sale.setPurchaseType(purchaseType);
        sale.setPurchaseDate(LocalDate.now());
////        
//        System.out.println(userName);
//        System.out.println(salespersonId);
//        System.out.println(customerName);
//        System.out.println(customerEmail);
//        System.out.println(customerAddress);
//        System.out.println(customerAddress2 );
//        System.out.println(customerCity );
//        System.out.println(customerZipCode);
//        System.out.println(purchasePrice );
//       System.out.println(purchaseType);

       System.out.println(sale.toString());
        saleDao.addSale(sale);
        vehicleDao.deleteVehicleById(vehicle.getVehicleId());

        return "redirect:/sales/purchases";
    }
    
    @GetMapping("/makes")
    public String showCarMakes(Model model){
        List<Make> makes = makeDao.getAllMakes();
        model.addAttribute("makes", makes);
        return "Makes";
    }
    
    
    
    @PostMapping("/addMake")
    public String addCarMake(HttpServletRequest request){
        
        String makeName = request.getParameter("makeName");
        String userName = request.getParameter("userName");
        int createPersonId = userdao.getUserIdByEmail(userName);
        
        Make make = new Make();
        make.setDateAdded(LocalDate.now());
        make.setMakeName(makeName);
        make.setUserId(createPersonId);
        make.setUserEmail(userName);
        
        makeDao.addMake(make);
        return "redirect:/sales/makes";
    }
    
    @GetMapping("/models")
    public String showCarModels(Model model){
        List<com.m3.cardealership.entities.Model> models = modelDao.getAllModels();
        model.addAttribute("models", models);
        
        List<Make> makes = makeDao.getAllMakes();
        model.addAttribute("makes", makes);
        
        return "Models";
    }
    
    @Transactional
    @PostMapping ("/addModel")
    public String addCarModel(HttpServletRequest request){
        String makeName = request.getParameter("makeName");
        String modelName = request.getParameter("modelName");
        String userName = request.getParameter("userName");
        int createPersonId = userdao.getUserIdByEmail(userName);
        
        com.m3.cardealership.entities.Model newModel = new com.m3.cardealership.entities.Model();
        
        newModel.setMakeId(makeDao.getMakeFromMakeName(makeName).getMakeId());
        newModel.setMakeName(makeName);
        newModel.setModelName(modelName);
        newModel.setDateAdded(LocalDate.now());
        newModel.setUserId(createPersonId);
        newModel.setUserEmail(userName);
        
        modelDao.addModel(newModel);
        
        return "redirect:/sales/models";
    }
    
}
