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
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CSC450.ars.domain.AdLocationVisit;
import com.CSC450.ars.domain.Keyword;
import com.CSC450.ars.domain.Page;

@Service
public class KeywordDao {
	
	private Connection conn;
	private String query;
	private PreparedStatement stmt;
	
	public List<Keyword> getAll() throws SQLException {
		List<Keyword> keywords = new ArrayList<Keyword>();
		conn = ARSDatabaseUtil.getConnection();
		query = "select id, keyword_name from keyword";
		stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			keywords.add(ARSDatabaseUtil.createKeyword(rs));
		}
		conn.close();
		return keywords;
	}
	
	public long getLatestId() throws SQLException {
		long latest = 0;
		conn = ARSDatabaseUtil.getConnection();
		query = "select MAX(id) max from " + ARSDatabaseUtil.KEYWORD;
		stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			latest = rs.getLong("max");
		}
		conn.close();
		return latest;
	}
	
	public void save(Keyword keyword) throws SQLException {
		conn = ARSDatabaseUtil.getConnection();
		query = "insert into keyword values(?,?)";
		stmt = conn.prepareStatement(query);
		stmt.setLong(1, keyword.getId());
		stmt.setString(2, keyword.getKeywordName());
		stmt.execute();
		conn.close();
	}
	
	public Keyword getById(long id) throws SQLException {
		conn = ARSDatabaseUtil.getConnection();
		query = "select * from " + ARSDatabaseUtil.KEYWORD + " where id = ?";
		stmt = conn.prepareStatement(query);
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		
		Keyword keyword = null;
		if(rs.next()) {
			keyword = ARSDatabaseUtil.createKeyword(rs);
		}
		conn.close();
		return keyword;
	}
	
	public List<Keyword> getKeywordsByPageId(long pageId) throws SQLException {
		List<Keyword> keywords = new ArrayList<Keyword>();
		conn = ARSDatabaseUtil.getConnection();
		query = "SELECT k.id, k.keyword_name FROM addata.page_keywords pk join addata.keyword k on pk.keywords = k.id where pk.page = ?";
		stmt = conn.prepareStatement(query);
		stmt.setLong(1, pageId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			keywords.add(ARSDatabaseUtil.createKeyword(rs));
		}
		conn.close();
		return keywords;
	}
}
