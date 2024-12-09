package com.example.findmypg.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/findmypg/configuration")
public class ConfigurationController {

	@Autowired
	private ConfigurationService configurationService;

	@GetMapping(value = "/getAllConfiValue")
	private List<ConfigurationDTO> getAllConfigValue(@RequestParam Long ownerId) {
		return configurationService.getAllConfigValue(ownerId);
	}
	
	@GetMapping(value = "/getValue")
	private String getConfigValue(@RequestParam Long onwerId, String key)
	{
		return configurationService.getConfigValue(onwerId,key);
	}

}
