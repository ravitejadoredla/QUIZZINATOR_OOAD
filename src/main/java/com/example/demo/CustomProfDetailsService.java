package com.example.demo;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomProfDetailsService implements UserDetailsService {

	private ProfRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String pid) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Professor prof =repo.findByPID(pid);
		if(prof==null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomProfDetails(prof);
	}
	}


