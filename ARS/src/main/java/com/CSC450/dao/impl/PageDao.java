package com.CSC450.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.CSC450.ars.domain.AdLocationVisit;
import com.CSC450.ars.domain.Keyword;
import com.CSC450.ars.domain.Page;

public class PageDao {
	
	private Connection conn;
	private String query;
	private PreparedStatement stmt;
	
	public List<Page> getAll() throws SQLException {
		List<Page> pages = new ArrayList<Page>();
		conn = ARSDatabaseUtil.getConnection();
		query = "select id, url from page";
		stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			pages.add(ARSDatabaseUtil.createPage(rs));
		}
		conn.close();
		return pages;
	}
	
	public void save(Page page) throws SQLException {
		conn = ARSDatabaseUtil.getConnection();
		query = "insert into page values(?,?)";
		stmt = conn.prepareStatement(query);
		stmt.setLong(1, page.getId());
		stmt.setString(2, page.getUrl());
		stmt.execute();
		conn.close();
		ARSDatabaseUtil.updatePage_KeywordTablePageId(page.getId(), page.getKeywordIds());
	}
	
	public Page getById(long id) throws SQLException {
		conn = ARSDatabaseUtil.getConnection();
		query = "select * from " + ARSDatabaseUtil.PAGE + " where id = ?";
		stmt = conn.prepareStatement(query);
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		
		Page page = null;
		if(rs.next()) {
			page = ARSDatabaseUtil.createPage(rs);
		}
		conn.close();
		return page;
	}
	
	public List<Page> getPagesByKeywordId(long keywordId) throws SQLException {
		List<Page> pages = new ArrayList<Page>();
		conn = ARSDatabaseUtil.getConnection();
		query = "SELECT p.id, p.url FROM addata.page_keywords pk join addata.page p on p.id = pk.page where pk.keywords = ?";
		stmt = conn.prepareStatement(query);
		stmt.setLong(1, keywordId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			pages.add(ARSDatabaseUtil.createPage(rs));
		}
		return pages;
	}
	/*private EntityManagerFactory emFactory;
	
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
	public void save(Page page){
		entityManager.getTransaction().begin();
		entityManager.merge(page);
		entityManager.getTransaction().commit();
	}
	
	@Transactional
	public void deleteById(long pageId) {
		entityManager.getTransaction().begin();
		entityManager.createQuery("DELETE FROM Page p WHERE p.id = :id").setParameter("id", pageId).executeUpdate();
		entityManager.getTransaction().commit();
	}

	
	@Transactional
	public Page getById(long id){
		TypedQuery<Page> query = entityManager.createQuery("SELECT p FROM Page p WHERE p.id = :id", Page.class);
		return query.setParameter("id", id).getSingleResult();
	}*/
	
}
