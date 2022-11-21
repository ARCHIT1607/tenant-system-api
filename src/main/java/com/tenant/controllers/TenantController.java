package com.tenant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tenant.entity.Tenant;
import com.tenant.repositories.TenantRepository;

@RestController
@CrossOrigin("*")
public class TenantController {

	@Autowired
	private TenantRepository tenantRepo;
	
	@Autowired
	BCryptPasswordEncoder passwordEncode;
	

	@PostMapping("/login")
	public ResponseEntity<String> getUserDetails(@RequestParam(name = "userName") String userName,
			@RequestParam(name = "password") String password) {
		Tenant user = tenantRepo.getUserDetails(userName);
		try {
			if (user == null) {
				throw new Exception("User doesn't Exist");
			} else if (passwordEncode.matches(password, user.getPassword())) {
				return new ResponseEntity<String>("Success", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Password is incorrect", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody Tenant tenant) {
		Tenant user = tenantRepo.getUserDetails(tenant.getUsername());
		try {
			if (user.getUsername().toUpperCase().equals(tenant.getUsername().toUpperCase())) {
				throw new Exception("user already exists");
			}
			tenant.setPassword(passwordEncode.encode(tenant.getPassword()));
			return new ResponseEntity<Object>(tenantRepo.save(tenant), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/test")
	public String test() {
		return "App is up";
	}

}
