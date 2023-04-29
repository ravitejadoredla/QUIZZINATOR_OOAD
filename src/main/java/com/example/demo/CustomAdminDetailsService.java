package com.example.demo;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomAdminDetailsService implements UserDetailsService {

	private AdminRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String adminid) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Admin admin =repo.findByADMINID(adminid);
		if(admin==null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomAdminDetails(admin);
	}
	}


