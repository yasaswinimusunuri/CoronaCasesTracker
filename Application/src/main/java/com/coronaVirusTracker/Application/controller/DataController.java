package com.coronaVirusTracker.Application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.coronaVirusTracker.Application.Models.LocationDetails;
import com.coronaVirusTracker.Application.services.DataCollectionService;

@Controller
public class DataController {
   
	@Autowired
	private DataCollectionService dataCollectionService;
	
	@GetMapping("/")
	public String getData(Model model)
	{
		List<LocationDetails> details=dataCollectionService.getDetails();
		int total=details.stream().mapToInt(s ->s.getNewCases()).sum();
		model.addAttribute("LocationDetails",details);
		model.addAttribute("TotalCount",total);
		return "home";
	}
}
