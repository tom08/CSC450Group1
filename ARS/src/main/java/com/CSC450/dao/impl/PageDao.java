package com.CSC450.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.NoResultException;

import org.springframework.transaction.annotation.Transactional;

import com.CSC450.ars.domain.Page;

public class PageDao {
	private EntityManagerFactory emFactory;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public PageDao() {
		emFactory = Persistence.createEntityManagerFactory("persistenceUnit");
		entityManager = emFactory.createEntityManager();
	}
	
	@Transactional
	public List<Page> getAll(){
		return entityManager.createQuery("FROM Page", Page.class).getResultList();
	}
	
	@Transactional
	public void save(Page word){
		entityManager.getTransaction().begin();
		entityManager.merge(word);
		entityManager.getTransaction().commit();
	}
	
	@Transactional
	public Page getById(long id){
		TypedQuery<Page> query = entityManager.createQuery("SELECT p FROM Page p WHERE p.id = :id", Page.class);
		try{
            return query.setParameter("id", id).getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
	}
	
}
