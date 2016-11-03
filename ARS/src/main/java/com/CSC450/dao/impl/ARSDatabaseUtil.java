package com.CSC450.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.CSC450.ars.domain.AdLocationVisit;
import com.CSC450.ars.domain.Keyword;
import com.CSC450.ars.domain.Page;

public class ARSDatabaseUtil {
	
	private static String URL = "jdbc:mysql://127.0.0.1:3306/addata";
	private static String USERNAME = "ars";
	private static String PASSWORD = "password";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	public static AdLocationVisit createAdLocationVisit(ResultSet rs) throws SQLException {
		AdLocationVisit adVL = new AdLocationVisit();
		adVL.setId(rs.getLong("id"));
		adVL.setFocusRatio(rs.getDouble("focus_ratio"));
		adVL.setActiveRatio(rs.getDouble("active_ratio"));
		adVL.setTotalSpent(rs.getDouble("total_spent"));
		adVL.setPageId(rs.getLong("page_id"));
		adVL.setPageLocation(rs.getString("page_location"));
		adVL.setKeywordId(rs.getLong("keyword_id"));
		return adVL;
	}
	
	public static Page createPage(ResultSet rs) throws SQLException {
		Page page = new Page();
		page.setId(rs.getLong("id"));
		page.setUrl(rs.getString("url"));
		return page;
	}
	
	public static Keyword createKeyword(ResultSet rs) throws SQLException {
		Keyword keyword = new Keyword();
		keyword.setId(rs.getLong("id"));
		keyword.setKeywordName(rs.getString("keyword_name"));
		return keyword;
	}

}
