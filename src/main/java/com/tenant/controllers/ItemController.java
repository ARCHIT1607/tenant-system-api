package com.tenant.controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tenant.entity.Item;
import com.tenant.mapper.ItemMapper;

@RestController
@Transactional
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ItemController {

	@Autowired
	private ItemMapper mapper;

	@GetMapping("/getAllItems/{userName}")
	public ResponseEntity<Object> getItemListByUserName(@PathVariable("userName") String userName) {
		try {
			return new ResponseEntity<Object>(mapper.getItemListByUserName(userName), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getAllItemName/{userName}")
	public ResponseEntity<Object> getAllItemName(@PathVariable("userName") String userName) {
		try {
			return new ResponseEntity<Object>(mapper.getAllItemName(userName), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getAllShopName/{userName}")
	public ResponseEntity<Object> getAllShopName(@PathVariable("userName") String userName) {
		try {
			return new ResponseEntity<Object>(mapper.getAllShopName(userName), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/addItem")
	public ResponseEntity<Object> addItem(@RequestBody Item item) {
		try {
			System.out.println(item.getItemName());
			item.setCreatedDate(LocalDate.now().toString());
			item.setUpdatedDate(null);
			mapper.addItem(item);
			return new ResponseEntity<Object>("added successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/filterItem")
	public ResponseEntity<Object> filterItem(@RequestParam(name = "fromDate") String fromDate,
			@RequestParam(name = "toDate") String toDate, @RequestParam(name = "userName") String userName,
			@RequestParam(name = "itemName") String itemName, @RequestParam(name = "shopName") String shopName)
			throws ParseException {
		try {
			List<Item> result = mapper.filterItem(fromDate, toDate, userName, itemName, shopName);
			for (Item i : result) {
				System.out.println(i.getItemName());
			}
			return new ResponseEntity<Object>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/filterDashboard")
	public Map filterDashboard(@RequestParam(name = "fromDate") String fromDate,
			@RequestParam(name = "toDate") String toDate, @RequestParam(name = "userName") String userName)
			throws ParseException {
		LocalDate from = LocalDate.parse(fromDate, DateTimeFormatter.BASIC_ISO_DATE);
		LocalDate to = LocalDate.parse(toDate, DateTimeFormatter.BASIC_ISO_DATE);
		return mapper.filterDashboard(from, to, userName);
	}

	@GetMapping("/dashboard")
	public Map dashboard(@RequestParam(name = "userName") String userName) throws ParseException {
		return mapper.dashboard(userName);
	}

	@GetMapping("/getItemById/{id}")
	public Item getItemById(@PathVariable("id") int id) {
		return mapper.getItemById((long) id);
	}

	@PostMapping("/updateItem")
	public String udpateItem(@RequestBody Item item) throws ParseException {
		System.out.println(item.getItemName());
		Item oldItem = mapper.getItemById(item.getId());
		oldItem.setItemName(item.getItemName());
		oldItem.setPrice(item.getPrice());
		oldItem.setQuantity(item.getQuantity());
		oldItem.setShopName(item.getShopName());
		oldItem.setShopName(item.getShopName());

		oldItem.setUpdatedDate(LocalDate.now().toString());
		mapper.updateItem(oldItem);
		return "Successfully Updated";
	}

	@DeleteMapping("/deleteItem/{id}")
	public void getItemById(@PathVariable("id") long id) {

		mapper.deleteById(id);
		System.out.println("Item Deleted");
	}

}
