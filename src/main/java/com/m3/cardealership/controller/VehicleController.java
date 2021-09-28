/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.controller;

import com.m3.cardealership.dao.ContactDao;
import com.m3.cardealership.dao.MakeDao;
import com.m3.cardealership.dao.ModelDao;
import com.m3.cardealership.dao.SaleDao;
import com.m3.cardealership.dao.SpecialDao;
import com.m3.cardealership.dao.UserDao;
import com.m3.cardealership.dao.VehicleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author junha
 */
@Controller
public class VehicleController {
    
    @Autowired
    UserDao userDao;
    
    @Autowired
    SaleDao saleDao;
    
    @Autowired
    SpecialDao specialDao;
    
    @Autowired
    ContactDao contactDao;
    
    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    MakeDao makeDao;

    @Autowired
    ModelDao modelDao;
    

}
