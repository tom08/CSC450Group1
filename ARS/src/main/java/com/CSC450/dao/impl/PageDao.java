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
import javax.persistence.Query;
import javax.persistence.NoResultException;

import org.springframework.transaction.annotation.Transactional;

import com.CSC450.ars.domain.AdLocationVisit;
import com.CSC450.ars.domain.Keyword;
import com.CSC450.ars.domain.Page;
import com.CSC450.ars.domain.Keyword;

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
		return page;
	}
}
