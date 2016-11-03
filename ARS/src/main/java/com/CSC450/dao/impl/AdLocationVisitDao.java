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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CSC450.ars.domain.AdLocationVisit;

@Service
public class AdLocationVisitDao {

	private Connection conn;
	private String query;
	private PreparedStatement stmt;

	public List<AdLocationVisit> getAll() throws SQLException {
		List<AdLocationVisit> adLVs = new ArrayList<AdLocationVisit>();
		conn = ARSDatabaseUtil.getConnection();
		query = "select id, focus_ratio, active_ratio, total_spent, page_id, page_location, keyword_id from ad_location_visit";
		stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			adLVs.add(ARSDatabaseUtil.createAdLocationVisit(rs));
		}
		conn.close();
		return adLVs;
	}
	
	public void save(AdLocationVisit adLV) throws SQLException {
		conn = ARSDatabaseUtil.getConnection();
		query = "insert into ad_location_visit values(?,?,?,?,?,?,?)";
		stmt = conn.prepareStatement(query);
		stmt.setLong(1, adLV.getId());
		stmt.setString(2, adLV.getPageLocation());
		stmt.setDouble(3, adLV.getFocusRatio());
		stmt.setDouble(4, adLV.getActiveRatio());
		stmt.setDouble(5, adLV.getTotalSpent());
		stmt.setLong(6, adLV.getPageId());
		stmt.setLong(7, adLV.getKeywordId());
		stmt.execute();
	}
	
	/*@PersistenceContext
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
	}*/
}
