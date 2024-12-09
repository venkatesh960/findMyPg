package com.example.findmypg.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.findmypg.entities.Configuration;
import com.example.findmypg.entities.Owner;
import com.example.findmypg.owner.OwnerRegistrationRepo;

@Service
public class ConfigurationService {

	@Autowired
	private OwnerRegistrationRepo ownerRegistrationRepo;
	
	@Autowired
	private ConfigurationRepositry configurationRepositry;
	
	public List<ConfigurationDTO> getAllConfigValue(Long ownerId) {
		Optional<Owner> owner = ownerRegistrationRepo.findById(ownerId);
		List<ConfigurationDTO> configurationDTOs=new ArrayList<ConfigurationDTO>();
		owner.ifPresent(ownerr->{
			ConfigurationDTO configurationDTO=new  ConfigurationDTO();
			List<Configuration> listofOwnerConfigValue = configurationRepositry.findByOwner(ownerr);
			if (listofOwnerConfigValue!=null) {
				listofOwnerConfigValue.forEach(confi->{
					configurationDTO.setConfigKey(confi.getKey());
					configurationDTO.setConfiValue(confi.getValue());
					configurationDTO.setDescription(confi.getDescription());
					configurationDTOs.add(configurationDTO);
				});
			}
		});
		
		return configurationDTOs;
	}

	public String getConfigValue(Long onwerId, String key) {
		Optional<Owner> owner = ownerRegistrationRepo.findById(onwerId);
		if(owner.isPresent()){
			Configuration configValue = configurationRepositry.findByOwnerAndKey(owner.get(),key);
			return configValue.getValue();
		}
		return null;
	}

}
