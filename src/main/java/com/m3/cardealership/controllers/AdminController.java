/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.controllers;

import com.m3.cardealership.dao.MakeDao;
import com.m3.cardealership.dao.ModelDao;
import com.m3.cardealership.dao.ReportDao;
import com.m3.cardealership.dao.SpecialDao;
import com.m3.cardealership.dao.UserDao;
import com.m3.cardealership.dao.VehicleDao;
import com.m3.cardealership.entities.Make;
import com.m3.cardealership.entities.Report;
import com.m3.cardealership.entities.Special;
import com.m3.cardealership.entities.User;
import com.m3.cardealership.entities.Vehicle;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import com.m3.cardealership.entities.Vehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    @Autowired
    ReportDao reportDao;

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public AdminController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
       this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }


    @GetMapping("/Admin/Specials")
    public String displaySpecials(Model model){
        List<Special> specials = specialdao.getAllSpecials();
        model.addAttribute("specials", specials);
        return "Specials";
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
        List<Vehicle> vehicles = vehicledao.getVehicleBySearch("Both", likeQuery, minPrice, maxPrice, minYear, maxYear);
        
        model.addAttribute("vehicles", vehicles);
        
        return "Vehicles";
    }
    
    @PostMapping("/addVehicle")
    public String addVehicle(HttpServletRequest request){
        
        int userId = userdao.getUserIdByEmail(request.getParameter("userName"));
        int year = Integer.valueOf(request.getParameter("year"));
        int mileage = Integer.valueOf(request.getParameter("mileage"));
        int salePrice = Integer.valueOf(request.getParameter("salePrice"));;
        int MSRP = Integer.valueOf(request.getParameter("MSRP"));;
        String VIN = request.getParameter("VIN");
        String color =request.getParameter("color");
        String interior = request.getParameter("interior");
        String bodyStyle = request.getParameter("bodyStyle");
        String transmission = request.getParameter("transmission");
        String DESCRIPTION =  request.getParameter("DESCRIPTION"); ;
        
        boolean isFeatured = convertToBoolean(request.getParameter("isFeatured"));
        
        Make make = makedao.getMakeFromMakeName(request.getParameter("makeName"));
        com.m3.cardealership.entities.Model model = modeldao.getModelFromModelName(request.getParameter("modelName"));

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
        vehicle.setFeatured(isFeatured);
        vehicle.setDateAdded(LocalDate.now());
        vehicle.setDESCRIPTION(DESCRIPTION);
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setDateAdded(LocalDate.now());
       
        System.out.println(vehicle);
        vehicledao.addVehicle(vehicle);
 
        return "redirect:/admin/vehicles";
    }

    @GetMapping("/addVehicle")
    public String addVehicle(HttpServletRequest request, @RequestParam(defaultValue="") String makeName, Model model) {
//        
        List<Make> makes = makedao.getAllMakes();
        model.addAttribute("makes", makes);
        
        List<com.m3.cardealership.entities.Model> models = modeldao.getModelFromMakeName(makeName);
        System.out.println(models);        
        model.addAttribute("models", models);
        
        
        
        
        
        return "addVehicle";
    }    
    
    
    @GetMapping("/editVehicle")
    public String editVehicle(HttpServletRequest request, @RequestParam(defaultValue="") String makeName, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Vehicle vehicle = vehicledao.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        
        List<Make> makes = makedao.getAllMakes();
        model.addAttribute("makes", makes);
        
        List<com.m3.cardealership.entities.Model> models = modeldao.getModelFromMakeName(makeName);
        
        model.addAttribute("models", models);
        
        
        return "editVehicle";
    }    
    
    @GetMapping("/deleteVehicle")
    public String deleteVehicle(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        vehicledao.deleteVehicleById(id);
        return "vehicles";
    }
    
//    @GetMapping("/editVehicle/queryModels")
//    public String getVehicleMovels(HttpServletRequest request, Model model) {
//        String makeName = request.getParameter("makeName");
//        List<com.m3.cardealership.entities.Model> models = modeldao.getModelFromMakeName(makeName);
////        model.addAttribute("models", models);
//        
//        return "editVehicle";
//    }    
    
    @PostMapping("/vehicles/editVehicle")
    public String performEditVehicle(HttpServletRequest request) {
//        System.out.println(request.getParameter("vehicleId"));
        int id = Integer.parseInt(request.getParameter("vehicleId"));
        System.out.println(id);
        Vehicle vehicle = vehicledao.getVehicleById(id);
//        System.out.println(vehicle.toString());
//        
        Make make = makedao.getMakeFromMakeName(request.getParameter("makeName"));
        com.m3.cardealership.entities.Model model = modeldao.getModelFromModelName(request.getParameter("modelName"));
//        
//System.out.println(make.toString());
//System.out.println(model.toString());
        String VIN = request.getParameter("VIN");
        String color =request.getParameter("color");
        String interior = request.getParameter("interior");
        String bodyStyle = request.getParameter("bodyStyle");
        String transmission = request.getParameter("transmission");
        String DESCRIPTION =  request.getParameter("DESCRIPTION"); ;
        
        int userId = userdao.getUserIdByEmail(request.getParameter("userName"));
        int year = Integer.valueOf(request.getParameter("year"));
        int mileage = Integer.valueOf(request.getParameter("mileage"));
        int salePrice = Integer.valueOf(request.getParameter("salePrice"));;
        int MSRP = Integer.valueOf(request.getParameter("MSRP"));;
        
        boolean isFeatured = convertToBoolean(request.getParameter("isFeatured"));

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
        vehicle.setFeatured(isFeatured);
        vehicle.setDateAdded(LocalDate.now());
        vehicle.setDESCRIPTION(DESCRIPTION);
//        
        vehicledao.updateVehicle(vehicle);
//        
        return "redirect:/admin/vehicles";
    }
    
    @GetMapping("/addUser")
    public String getAddUser(Model model) {
    
    return "addUser";
    }
    
    @PostMapping("/addUser")
    public String addUser(User user) {
        
        // 1. UserDao adds new user to database
        userDao.addUser(user);
        
        // 2. Reload the in memory user details
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(user.getUserType()));
        inMemoryUserDetailsManager.createUser(
                        org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder().username(user.getUserEmail())
                                .password(user.getPassword())
                                .roles(user.getUserType())
                                .build());
//        inMemoryUserDetailsManager.createUser(new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getPassword(),  grantedAuthorityList));

        return "redirect:/admin/users";
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
        
        
         // 3. Reload the in memory user details
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(user.getUserType()));
        inMemoryUserDetailsManager.createUser(
                        org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder().username(user.getUserEmail())
                                .password(user.getPassword())
                                .roles(user.getUserType())
                                .build());

        return "redirect:/admin/users";
    }

    @GetMapping("/users")
    public String displayUsers(Model model){
        model.addAttribute("users",userdao.getAllUsers());
        return "users";
    }
    
    @GetMapping("/deleteUser")
    public String deleteUser(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        userdao.deleteUserById(id);
        return "redirect:/admin/users";
    }

//    @GetMapping("/specials")
//    public String displaySpecials(Model model){
//        List<Special> specials = specialdao.getAllSpecials();
//        model.addAttribute("specials", specials);
//        return "specials";
//    }
    

    @PostMapping ("addSpecial")
    public String addSpecial(HttpServletRequest request){     
        String specialTitle = request.getParameter("specialTitle");
        String specialDescription = request.getParameter("specialDescription");
 
        Special special = new Special();
        special.setSpecialTitle(specialTitle);
        special.setSpecialDescription(specialDescription);
        special.setPromotionAmount(0);//where do we get this from?
        specialdao.addSpecial(special);
        return "redirect:/Inventory/Specials";
    }   


    @GetMapping("deleteSpecial")
    public String deleteSpecial(HttpServletRequest request) {
        
        String title = request.getParameter("title");
        
        System.out.println("test");
        System.out.println(title);
        specialdao.deleteSpecialByTitle(title);
        return "redirect:/Inventory/Specials";
    }

//getUserSales
    
    //getNewCarCount
    //SELECT YEAR,makeNAME, modelName, count(
    @GetMapping("/reports")
    public String showReportsPage() {
        
        return "Reports";
    }
    
    @RequestMapping(value = "/reports/sales", method = RequestMethod.GET, params = {"!user", "!dateFrom"})
    public String showSalesReport(){
        System.out.println("NO PARAM");
        return "SalesReport";
    }
    
    @RequestMapping(value = "/reports/sales", method = RequestMethod.GET, params = {"user", "dateFrom", "dateUntil"})
    public String showSalesReport(HttpServletRequest request, Model model) {
        List<User> salespeople = userdao.getAllUsers().stream()
                .filter(u -> u.getUserType().equals("ADMIN") || u.getUserType().equals("SALES"))
                .collect(Collectors.toList());
        model.addAttribute("salespeople", salespeople);
        
        String dateFrom = request.getParameter("dateFrom");
        String dateUntil = request.getParameter("dateUntil");
        String userName = request.getParameter("user");
        
        List<Report> reports = reportDao.querySalesReport(dateFrom, dateUntil, userName);
        model.addAttribute("reports", reports);
        
        return "SalesReport";
    }
    
    @GetMapping("/reports/inventory")
    public String showReportsPage(Model model) {
        List<Report> newReports = reportDao.queryInventory(true);
        model.addAttribute("newReports", newReports);
        
        List<Report> oldReports = reportDao.queryInventory(false);
        model.addAttribute("oldReports", oldReports);
        
        return "InventoryReport";
    }

    
    
    
   
    
    private boolean convertToBoolean(String value) {
        boolean returnValue = false;
        if ("1".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) || 
            "true".equalsIgnoreCase(value) || "on".equalsIgnoreCase(value))
                returnValue = true;
        return returnValue;
    }    
}
