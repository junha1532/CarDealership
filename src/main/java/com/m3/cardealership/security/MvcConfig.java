/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.security;

/**
 *
 * @author pbott
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
<<<<<<< HEAD
		registry.addViewController("/Index.html").setViewName("Index.html");
		registry.addViewController("/").setViewName("Index");
                registry.addViewController("/StyleSheet.css").setViewName("StyleSheet.css");
                registry.addViewController("/Specials.html").setViewName("Specials");
                registry.addViewController("/Contact.html").setViewName("Contact");
		registry.addViewController("/home/index").setViewName("Index");
                registry.addViewController("/index").setViewName("Index");
                registry.addViewController("/Inventory/New").setViewName("InventoyNew");
                registry.addViewController("/Inventory/New/query").setViewName("InventoyNew");
                registry.addViewController("/Inventory/Used").setViewName("InventoryUsed");
                registry.addViewController("/Inventory/Used/query").setViewName("InventoryUsed");
=======
		registry.addViewController("/Index.html").setViewName("Index");
                registry.addViewController("/Index").setViewName("Index.html");
		registry.addViewController("/").setViewName("Index");
                registry.addViewController("/Inventory/Specials").setViewName("Specials");
                registry.addViewController("/Admin/Specials").setViewName("Specials");
                registry.addViewController("/Contact.html").setViewName("Contact");
		registry.addViewController("/home/index").setViewName("Index");
                registry.addViewController("/Inventory/New").setViewName("InventoryNew");
                registry.addViewController("/StyleSheet.css").setViewName("StyleSheet.css");
                registry.addViewController("*/StyleSheet.css").setViewName("StyleSheet.css");
                registry.addViewController("/Inventory/UsedInventory.html").setViewName("UsedInventory");
                registry.addViewController("Inventory/Script1.js").setViewName("Script1.js");
                
>>>>>>> df32d39c79c0c60c91f44c93d0b695e6502920b0
                registry.addViewController("/admin").setViewName("admin");
                registry.addViewController("/forbidden").setViewName("forbidden");
                registry.addViewController("/403").setViewName("forbidden");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/login").setViewName("login");
                registry.addViewController("/StyleSheet.css").setViewName("StyleSheet.css");

        }
        
            @Configuration
            public class ThymeleafConfig {

                @Bean
                public SpringSecurityDialect springSecurityDialect(){
                    return new SpringSecurityDialect();
                }
            }
}
