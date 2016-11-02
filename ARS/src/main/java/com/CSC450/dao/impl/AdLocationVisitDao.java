package com.CSC450.dao.impl;

import java.util.List;
import java.lang.IndexOutOfBoundsException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.NoResultException;

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

	@Transactional
	public AdLocationVisit getById(long id){
		TypedQuery<AdLocationVisit> query = entityManager.createQuery("SELECT p FROM AdLocationVisit p WHERE p.id = :id", AdLocationVisit.class);
		try{
            return query.setParameter("id", id).getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
	}

	@Transactional
	public AdLocationVisit getLatest(){
		TypedQuery<AdLocationVisit> query = entityManager.createQuery("SELECT p FROM AdLocationVisit p ORDER BY p.createdAt DESC", AdLocationVisit.class);
		try{
            return query.getResultList().get(0);
        }
        catch(NoResultException e){
            return null;
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
	}
}
