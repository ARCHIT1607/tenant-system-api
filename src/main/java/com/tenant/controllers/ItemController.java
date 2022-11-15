package com.tenant.controllers;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tenant.entity.Item;
import com.tenant.repositories.ItemRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ItemController {

	@Autowired
	private ItemRepository itemRepo;

	@GetMapping("/getAllItems/{userName}")
	public List<Item> getItemListByUserName(@PathVariable("userName") String userName) {
		return itemRepo.getItemListByUserName(userName);
	}

	@PostMapping("/addItem")
	public Item getItemListByUserName(@RequestBody Item item) {
		System.out.println(item.getItemName());
		item.setCreatedDate(new Date(System.currentTimeMillis()));
		return itemRepo.save(item);
	}

	@GetMapping("/filterItem")
	public List<Item> filterItem(@RequestParam(name = "fromDate") String fromDate, @RequestParam(name="toDate") String toDate) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
        java.util.Date fromStartDate = sdf1.parse(fromDate); // Returns a Date format object with the pattern
        java.util.Date toEndDate = sdf1.parse(toDate); // Returns a Date format object with the pattern
        java.sql.Date from = new java.sql.Date(fromStartDate.getTime());
        java.sql.Date to = new java.sql.Date(toEndDate.getTime());
        System.out.println("fromDate "+from + " toDate "+to);
        List<Item> result = itemRepo.filterItem(from,to);
        for(Item i : result) {
        	System.out.println(i.getItemName());
        }
		return result;
	}
	
	@GetMapping("/getItemById/{id}")
	public Optional<Item> getItemById(@PathVariable("id") int id) {
		return itemRepo.findById((long) id);
	}

	@PostMapping("/updateItem")
	public Item udpateItem(@RequestBody Item item) {
		System.out.println(item.getItemName());
		Item oldItem = itemRepo.getItemById(item.getId());
		oldItem.setItemName(item.getItemName());
		oldItem.setPrice(item.getPrice());
		oldItem.setQuantity(item.getQuantity());
		oldItem.setShopName(item.getShopName());
		return itemRepo.save(oldItem);
	}

	@DeleteMapping("/deleteItem/{id}")
	public void getItemById(@PathVariable("id") long id) {

		itemRepo.deleteById(id);
		System.out.println("Item Deleted");
	}

	
}
