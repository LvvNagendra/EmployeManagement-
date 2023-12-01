//package com.example.service;
//
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.model.Employee;
//import com.example.reposatory.EmployeeReposatory;
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//	@Autowired
//	EmployeeReposatory reposatory;
//	@Override
//	public UserDetails loadUserByUsername(String employeeName) throws UsernameNotFoundException {
//		Employee user = reposatory.findByEmployeeName(employeeName);
//		 return new org.springframework.security.core.userdetails.User(user.getEmployeeName(), user.getPassword(), new ArrayList<>());
//	}
//
//}
