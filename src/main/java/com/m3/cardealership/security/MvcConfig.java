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
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/Index").setViewName("Index");
		registry.addViewController("/").setViewName("Index");
                registry.addViewController("/InventoryNew").setViewName("InventoyNew");
                registry.addViewController("/forbidden").setViewName("forbidden");
                registry.addViewController("/403").setViewName("forbidden");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
        }

}
