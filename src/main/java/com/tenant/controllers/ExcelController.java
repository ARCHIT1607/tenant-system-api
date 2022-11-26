package com.tenant.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tenant.entity.Item;
import com.tenant.helper.ExcelHelper;
import com.tenant.helper.ResponseMessage;
import com.tenant.service.ExcelService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/excel")
public class ExcelController {

	@Autowired
	ExcelService fileService;

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam(name = "userName") String userName) {
		String message = "";

		if (ExcelHelper.hasExcelFormat(file)) {
			try {
				fileService.save(file, userName);

				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				e.printStackTrace();
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}

		message = "Please upload an excel file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}

	@GetMapping("/items")
	public ResponseEntity<List<Item>> getAllTutorials() {
		try {
			List<Item> items = fileService.getAllTutorials();

			if (items.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(items, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/downloadTemplate")
	public ResponseEntity downloadTemplate() {
		String fileName = "/Items.xlsx";
		InputStream is = this.getClass().getResourceAsStream(fileName);
		try {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + fileName)
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(IOUtils.toByteArray(is));
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/download")
	public ResponseEntity<Resource> getFile(@RequestParam(name = "fromDate") String fromDate,
			@RequestParam(name = "toDate") String toDate, @RequestParam(name = "userName") String userName,
			@RequestParam(name = "itemName") String itemName) {
		String filename = "Report.xlsx";
		InputStreamResource file = null;
		try {
			file = new InputStreamResource(fileService.load(fromDate, toDate, userName, itemName));
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
//	    	else {
//		    	InputStreamResource file = new InputStreamResource(fileService.load(null,null,userName));
//
//			    return ResponseEntity.ok()
//			        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
//			        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
//			        .body(file);
//	    	}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
