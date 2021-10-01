/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Daniel
 */
public class ReportsController {
    //getUserSales
    
    //getNewCarCount
    //SELECT YEAR,makeNAME, modelName, count(
    
    
    
    
    //INVENTORY REPORT
    //SELECT v.year Year, ma.makeName Make, m.modelName Model, count(*) Count, sum(v.SalePrice) Stock_Value FROM VEHICLE v JOIN make ma on ma.makeid = v.makeId join model m on m.modelId = v.modelId WHERE v.mileage > 0  group by v.year,ma.MakeId;
    
    //SALES REPORT
    //SELECT concat(u.userFirstName," ", u.userLastName) User, sum(s.purchasePrice) Total_Sales, count(*) Total_Vehicles FROM user u join sale s on s.salesPersonId = u.userId group by u.userId;
    
}
