/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.controllers;

import com.m3.cardealership.dao.ContactDao;
import com.m3.cardealership.dao.SpecialDao;
import com.m3.cardealership.dao.VehicleDao;
import com.m3.cardealership.entities.Contact;
import org.springframework.ui.Model;
import com.m3.cardealership.entities.Special;
import com.m3.cardealership.entities.Vehicle;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Daniel
 */

@Controller
public class HomeController {
    
    @Autowired 
    ContactDao contactDao;
    
    @Autowired 
    SpecialDao specialdao;
    
    @Autowired 
    VehicleDao vehicledao;
    
    @GetMapping("/Specials")
    public String displaySpecials(Model model){
        List<Special> specials = specialdao.getAllSpecials();
        model.addAttribute("specials", specials);
        return "Specials";
    }
    

    @RequestMapping(value={"", "/home"}, method= RequestMethod.GET)
    public String displayAll(Model model){
        List<Special> specials = specialdao.getAllSpecials();
        model.addAttribute("specials", specials);
      
        List<Vehicle> featuredVehicles = vehicledao.getFeaturedVehicles();
        model.addAttribute("featuredVehicles",featuredVehicles);
        
        return "Index";
    }
    
    @PostMapping("addContact")
    public String addContact(HttpServletRequest request) {
        String contactName = request.getParameter("customerName");
        String message = request.getParameter("message");
        String contactEmail = request.getParameter("customerEmail");
        String contactPhone = request.getParameter("customerPhone");
        
        Contact contact = new Contact();
        contact.setContactName(contactName);
        contact.setMessage(message);
        contact.setContactEmail(contactEmail);
        contact.setContactPhone(contactPhone);
        contactDao.addContact(contact);
        
        return "redirect:/Contact";
    }
    


    
   
}
