package com.tenant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

	
	@PostMapping("/login")
	public String getUserDetails(@RequestParam(name = "userName") String userName, @RequestParam(name="password") String password) {
		Tenant user = tenantRepo.getUserDetails(userName);
		if(user.getPassword().equals(password)) {
			return "Success";
		}else {
			return "password is wrong";
		}
	}
	
	@PostMapping("/register")
	public Tenant register(@RequestBody Tenant tenant) {
		return tenantRepo.save(tenant);
	}
	
	@GetMapping("/test")
	public String test() {
		return "App is up";
	}

}
