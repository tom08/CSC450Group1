package com.CSC450.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CSC450.ars.domain.AdLocationVisit;
import com.CSC450.ars.domain.Keyword;

@Service
public class KeywordDao {
private EntityManagerFactory emFactory;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public KeywordDao() {
		emFactory = Persistence.createEntityManagerFactory("persistenceUnit");
		entityManager = emFactory.createEntityManager();
	}
	
	@Transactional
	public List<Keyword> getAll(){
		return entityManager.createQuery("FROM Keyword", Keyword.class).getResultList();
	}
	
	@Transactional
	public void save(Keyword word){
		entityManager.getTransaction().begin();
		entityManager.merge(word);
		entityManager.getTransaction().commit();
	}
}
