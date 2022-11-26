package com.tenant.mapper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenant.entity.Item;

@Mapper
public interface ItemMapper {

//	@Query("SELECT u FROM Item u WHERE u.userName = ?1 Order By u.id")
	public List<Item> getItemListByUserName(String UserName);

//	@Query("SELECT u FROM Item u WHERE u.id = ?1 Order By u.id")
	public Item getItemById(Long id);

//	@Query("SELECT u FROM Item u WHERE u.createdDate BETWEEN ?1 AND ?2 AND u.userName=?3 AND itemName=?4")
	public List<Item> filterItem(@Param("from") String fromDate, @Param("to") String toDate, @Param("userName") String userName,
			@Param("itemName") String itemName);

//	@Query("SELECT COUNT(u.itemName) as itemCount, SUM(u.price) as expense FROM Item u WHERE u.createdDate BETWEEN ?1 AND ?2 AND u.userName=?3")
	public Map filterDashboard(@Param("from") LocalDate from, @Param("to") LocalDate to, @Param("userName") String userName);

//	@Query("SELECT COUNT(u.itemName) as itemCount, SUM(u.price) as expense FROM Item u WHERE u.userName=?1")
	public Map dashboard(String userName);

//	@Query("SELECT u FROM Item u WHERE u.userName=?1")
	public List<Item> downloadReportNoFilter(String userName);

//	@Query("SELECT u FROM Item u WHERE u.createdDate BETWEEN ?1 AND ?2 AND u.userName=?3")
	public List<Item> downloadReportFilter(@Param("from") String fromDate, @Param("to") String toDate, @Param("userName") String userName,
			@Param("itemName") String itemName);

//	@Query("SELECT DISTINCT UPPER(u.itemName) FROM Item u WHERE u.userName = ?1")
	public List<String> getAllItemName(String userName);

//	@Query("SELECT DISTINCT UPPER(u.shopName) FROM Item u WHERE u.userName = ?1")
	public List<String> getAllShopName(String userName);

	public Item getItemById(long id);

	public void updateItem(Item oldItem);

	public void addItem(Item item);

	public void deleteById(long id);
}
