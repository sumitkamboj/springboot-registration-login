package com.hellokoding.auth.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hellokoding.auth.model.User;
import com.hellokoding.auth.repository.UserRepository;
import com.hellokoding.auth.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
	
	@Autowired
    private UserDetailsService userDetailsService;
	@Autowired
	private UserRepository userRepository;
	@GetMapping(value="/{username}", produces = "application/json")
	public ResponseEntity<Object> getUserByUserName(@PathVariable("username") String username){
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		return new ResponseEntity<>(userDetails.getUsername()+userDetails.getPassword(),HttpStatus.OK);
	}
	
	@GetMapping(value="/all", produces = "application/json")
	public ResponseEntity<List<User>> getAllUser(){
		return ResponseEntity.ok(userRepository.findAll());
		
	}
}
