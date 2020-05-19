package com.edwin.works.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.edwin.works.api.storage.CSV;

@RestController
@RequestMapping("/lancamentos")
public class UploadCSVController {
	
	@Autowired
	private CSV csv;
	@PostMapping
	public void uploadCSVFile(@RequestParam("file") MultipartFile file) {
		csv.savarCSV(file);
	}
}
