package com.CSC450.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	private Connection conn;
	private String query;
	private PreparedStatement stmt;

	public List<AdLocationVisit> getAll() throws SQLException {
		List<AdLocationVisit> adLVs = new ArrayList<AdLocationVisit>();
		conn = ARSDatabaseUtil.getConnection();
		query = "select * from " + ARSDatabaseUtil.AD_LOCATION_VISIT;
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
		query = "insert into " + ARSDatabaseUtil.AD_LOCATION_VISIT + " values(?,?,?,?,?,?,?)";
		stmt = conn.prepareStatement(query);
		stmt.setLong(1, adLV.getId());
		stmt.setString(2, adLV.getPageLocation());
		stmt.setDouble(3, adLV.getFocusRatio());
		stmt.setDouble(4, adLV.getActiveRatio());
		stmt.setDouble(5, adLV.getTotalSpent());
		stmt.setLong(6, adLV.getPageId());
		stmt.setTimestamp(7, new Timestamp(new Date().getTime()));
		stmt.execute();
		conn.close();
	}
	
	public AdLocationVisit getById(long id) throws SQLException {
		conn = ARSDatabaseUtil.getConnection();
		query = "select * from " + ARSDatabaseUtil.AD_LOCATION_VISIT + " where id = ?";
		stmt = conn.prepareStatement(query);
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		
		AdLocationVisit adLV = null;
		if(rs.next()) {
			adLV = ARSDatabaseUtil.createAdLocationVisit(rs);
		}
		conn.close();
		return adLV;
	}
	
	public List<AdLocationVisit> getByPageId(long pageId) throws SQLException {
		List<AdLocationVisit> adLVs = new ArrayList<AdLocationVisit>();
		conn = ARSDatabaseUtil.getConnection();
		query = "select * from " + ARSDatabaseUtil.AD_LOCATION_VISIT + " where page_id = ?";
		stmt = conn.prepareStatement(query);
		stmt.setLong(1, pageId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			adLVs.add(ARSDatabaseUtil.createAdLocationVisit(rs));
		}

		conn.close();
		return adLVs;
	}
	
	public long count() throws SQLException {
		long count = 0;
		conn = ARSDatabaseUtil.getConnection();
		query = "select COUNT(id) count from " + ARSDatabaseUtil.AD_LOCATION_VISIT;
		stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			count = rs.getLong("count");
		}
		conn.close();
		return count;
	}
	
	public long countDistinct() throws SQLException {
		long count = 0;
		conn = ARSDatabaseUtil.getConnection();
		query = "select COUNT(DISTINCT page_location) count from " + ARSDatabaseUtil.AD_LOCATION_VISIT;
		stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			count = rs.getLong("count");
		}
		conn.close();
		return count;
	}
	
	public AdLocationVisit getLatest() throws SQLException {
		conn = ARSDatabaseUtil.getConnection();
		query = "select * from " + ARSDatabaseUtil.AD_LOCATION_VISIT + " order by created_at desc limit 1";
		stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		
		AdLocationVisit adLV = null;
		if(rs.next()) {
			adLV = ARSDatabaseUtil.createAdLocationVisit(rs);
		}
		conn.close();
		return adLV;
	}
}
