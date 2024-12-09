package com.example.findmypg.configuration;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.findmypg.entities.Configuration;
import com.example.findmypg.entities.Owner;

@Repository
public interface ConfigurationRepositry extends JpaRepository<Configuration, Long> {

	List<Configuration> findByOwner(Owner ownerr);

	Configuration findByOwnerAndKey(Owner ownerr, String key);

}
