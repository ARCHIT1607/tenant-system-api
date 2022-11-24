package com.tenant.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tenant.entity.Tenant;

@Mapper
public interface TenantMapper {

	public Tenant getUserDetails(String UserName);

	public Tenant register(Tenant tenant);
}
