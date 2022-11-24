//package com.tenant.repositories;
//
//
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Component;
//
//import com.tenant.entity.Tenant;
//
////@Component
//public interface TenantRepository extends JpaRepository<Tenant, Long>{
//	
//	@Query("SELECT u FROM Tenant u WHERE u.username = ?1")
//	public Tenant getUserDetails(String UserName);
//
//}
