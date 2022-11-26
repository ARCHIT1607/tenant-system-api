package com.tenant.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tenant.entity.Item;
import com.tenant.helper.ExcelHelper;
import com.tenant.mapper.ItemMapper;
import com.tenant.repositories.ItemRepository;

@Service
public class ExcelService {
	@Autowired
	ItemRepository repository;
	
	@Autowired
	ItemMapper mapper;

	public void save(MultipartFile file, String userName) throws ParseException {
		try {
			List<Item> items = ExcelHelper.excelToItems(file.getInputStream(), userName);
			repository.saveAll(items);
		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}

	public List<Item> getAllTutorials() {
		return repository.findAll();
	}

	public ByteArrayInputStream load(String fromDate, String toDate, String userName, String itemName) {
		ByteArrayInputStream in = null;
			List<Item> items = mapper.downloadReportFilter(fromDate, toDate, userName, itemName);
			System.out.println("item size "+items.size());
			for(Item item : items) {
				System.out.println(item.getItemName());
				System.out.println(item.getShopName());
				System.out.println(item.getPrice());
				System.out.println(item.getQuantity());
				System.out.println(item.getCreatedDate());
			}
			in = ExcelHelper.itemsToExcel(items);
		return in;
//		} else {
//			System.out.println("inside non filter");
//			List<Item> items = mapper.downloadReportNoFilter(userName);
//			ByteArrayInputStream in = ExcelHelper.itemsToExcel(items);
//		    return in;
//		}
//	    List<Item> items = repository.findAll();

	}
}