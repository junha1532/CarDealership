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
import com.m3.cardealership.dao.UserDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
//import com.m3.cardealership.entities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDao userdao;
    
    
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(inMemoryUserDetailsManager());
        }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/index").permitAll()
                                .antMatchers("/admin**").hasRole("ADMIN")
                                .antMatchers("/sales**").access("hasRole('SALES') or hasRole('ADMIN')")

                                .antMatchers("/Admin/**").hasRole("ADMIN")
                                .antMatchers("/Account/ChangePassword").authenticated()
//				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
                        .and()
                        .exceptionHandling().accessDeniedPage("/403")
                        .and()
			.logout().logoutSuccessUrl("/")
				.permitAll()    
                        .and()  //added
                        .httpBasic();  //added;
                
                http.csrf().disable();
        }
        
        @RequestMapping("/403")
        public String accessDenied() {
            return "/forbidden";
        }        

	@Bean
        public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
            List<com.m3.cardealership.entities.User> userList = userdao.getAllUsers();

            for(com.m3.cardealership.entities.User u : userList){
                manager.createUser(
                        User.withDefaultPasswordEncoder().username(u.getUserEmail())
                                .password(u.getPassword())
                                .roles(u.getUserType())
                                .build());
            }
            
            return manager;
        }
        
//        @Bean
//        public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//            final Properties users = new Properties();
//            users.put("user","pass,ROLE_USER,enabled"); //add whatever other user you need
//            return new InMemoryUserDetailsManager(users);
//        }
        
        
        
}