package com.CSC450.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CSC450.ars.domain.AdLocationVisit;

@Service
public class AdLocationVisitDao {
private EntityManagerFactory emFactory;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public AdLocationVisitDao() {
		emFactory = Persistence.createEntityManagerFactory("persistenceUnit");
		entityManager = emFactory.createEntityManager();
	}
	
	@Transactional
	public List<AdLocationVisit> getAll(){
		return entityManager.createQuery("FROM AdLocationVisit", AdLocationVisit.class).getResultList();
	}
	
	@Transactional
	public void save(AdLocationVisit alv){
		entityManager.getTransaction().begin();
		entityManager.merge(alv);
		entityManager.getTransaction().commit();
	}
}
