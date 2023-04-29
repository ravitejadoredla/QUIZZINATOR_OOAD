package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

public class WebSecurityConfig {

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	@Order(1)
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authenticationProvider(authenticationProvider());
		http.authorizeHttpRequests()
			.requestMatchers("/list_users").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("email")
				.defaultSuccessUrl("/list_users")
				.permitAll()
			.and()
			.logout().logoutSuccessUrl("/").permitAll();
		
		return http.build();
	}
	
	@Order(2)
	@Bean
	public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception{
		http.authenticationProvider(authenticationProvider());
		http.authorizeHttpRequests()
			.requestMatchers("/list_users").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin()
				.loginPage("/loginprof")
				.usernameParameter("pid")
				.defaultSuccessUrl("/list_users")
				.permitAll()
			.and()
			.logout().logoutSuccessUrl("/").permitAll();
		
		return http.build();
	}
	
	
	@Order(3)
	@Bean
	public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception{
		http.authenticationProvider(authenticationProvider());
		http.authorizeHttpRequests()
			.requestMatchers("/admindashboard").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin()
				.loginPage("/loginadmin")
				.usernameParameter("adminid")
				.defaultSuccessUrl("/admindashboard")
				.permitAll()
			.and()
			.logout().logoutSuccessUrl("/").permitAll();
		
		return http.build();
	}
	
	@Configuration
	public class MvcConfig implements WebMvcConfigurer {
	    @Override
	    public void configurePathMatch(PathMatchConfigurer configurer) {
	        UrlPathHelper urlPathHelper = new UrlPathHelper();
	        urlPathHelper.setRemoveSemicolonContent(false);
	        configurer.setUrlPathHelper(urlPathHelper);
	    }
	}
	
}
