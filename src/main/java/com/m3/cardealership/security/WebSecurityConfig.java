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
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/index").permitAll()
                                .antMatchers("/InventoryNew").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
                        .and()
                        .exceptionHandling().accessDeniedPage("/403")
                        .and()
			.logout()
				.permitAll();
        }
        
        @RequestMapping("/403")
        public String accessDenied() {
            return "/forbidden";
        }
        
        

	@Bean
        public UserDetailsService users() {
            // The builder will ensure the passwords are encoded before saving in memory
            UserBuilder users = User.withDefaultPasswordEncoder();
            UserDetails user = users
                .username("user")
                .password("password")
                .roles("USER")
                .build();
            UserDetails admin = users
                .username("admin")
                .password("password")
                .roles("USER", "ADMIN")
                .build();
            return new InMemoryUserDetailsManager(user, admin);
        }
        
//        @Autowired
//	DataSource dataSource;
//	
//	@Autowired
//	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//		
//	  auth.jdbcAuthentication().dataSource(dataSource)
//		.usersByUsernameQuery(
//			"select userEmail,password, 1 from user where userEmail=?")
//		.authoritiesByUsernameQuery(
//			"select userEmail, userType from user where userEmail=?")
//                  .passwordEncoder(new BCryptPasswordEncoder());
//	}
//        
//        @Bean
//        public PasswordEncoder passwordEncoder() {
//            return new BCryptPasswordEncoder();
//        }
        
        
}