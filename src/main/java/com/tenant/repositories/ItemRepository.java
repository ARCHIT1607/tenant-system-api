package com.tenant.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.util.MultiValueMap;

import com.tenant.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

//	@Query("SELECT u FROM Item u WHERE u.userName = ?1 Order By u.id")
//	public List<Item> getItemListByUserName(String UserName);
//
//	@Query("SELECT u FROM Item u WHERE u.id = ?1 Order By u.id")
//	public Item getItemById(Long id);
//	
//	@Query("SELECT u FROM Item u WHERE 1= 1 CASE WHEN (?1 != '' AND ?2 != '') THEN ( AND u.createdDate BETWEEN ?1 AND ?2) AND u.userName=?3")
//	public List<Item> filterItem(Date from, Date to, String userName);
//	
//	@Query("SELECT COUNT(u.itemName) as itemCount, SUM(u.price) as expense FROM Item u WHERE u.createdDate BETWEEN ?1 AND ?2 AND u.userName=?3")
//	public Map filterDashboard(Date from, Date to,String userName);
//	
//	@Query("SELECT COUNT(u.itemName) as itemCount, SUM(u.price) as expense FROM Item u WHERE u.userName=?1")
//	public Map dashboard(String userName);
//	
//	@Query("SELECT u FROM Item u WHERE u.userName=?1")
//	public List<Item> downloadReportNoFilter(String userName);
//	
//	@Query("SELECT u FROM Item u WHERE u.createdDate BETWEEN ?1 AND ?2 AND u.userName=?3")
//	public List<Item> downloadReportFilter(Date from, Date to,String userName);
//
//	@Query("SELECT DISTINCT UPPER(u.itemName) FROM Item u WHERE u.userName = ?1")
//	public List<String> getAllItemName(String userName);
//	
//	@Query("SELECT DISTINCT UPPER(u.shopName) FROM Item u WHERE u.userName = ?1")
//	public List<String> getAllShopName(String userName);


}
