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
	
	public void save(Keyword keyword) throws SQLException {
		conn = ARSDatabaseUtil.getConnection();
		query = "insert into keyword values(?,?)";
		stmt = conn.prepareStatement(query);
		stmt.setLong(1, keyword.getId());
		stmt.setString(2, keyword.getKeywordName());
		stmt.execute();
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
		return keyword;
	}
	
}
