package com.CSC450.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CSC450.ars.dao.AdSpaceDao;
import com.CSC450.ars.domain.AdSpace;

@Service
public class AdSpaceDaoImpl implements AdSpaceDao{

	private EntityManagerFactory emFactory;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public AdSpaceDaoImpl() {
		emFactory = Persistence.createEntityManagerFactory("persistenceUnit");
		entityManager = emFactory.createEntityManager();
	}
	
	@Transactional
	public List<AdSpace> getAll() {
		return entityManager.createQuery("FROM AdSpace", AdSpace.class).getResultList();
	}
	
	@Transactional
	public void save(AdSpace adSpace) {
		entityManager.getTransaction().begin();
		entityManager.merge(adSpace);
		entityManager.getTransaction().commit();
	}
}
