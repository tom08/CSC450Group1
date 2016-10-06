package com.CSC450.ars.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.CSC450.ars.domain.AdSpace;

@Component
public interface AdSpaceDao {

	public abstract List<AdSpace> getAll();
	
	public abstract void save(AdSpace adSpace);
}
