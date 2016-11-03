package com.CSC450.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.CSC450.ars.domain.AdLocationVisit;
import com.CSC450.ars.domain.Keyword;
import com.CSC450.ars.domain.Page;

public class ARSDatabaseUtil {
	
	private static String URL = "jdbc:mysql://127.0.0.1:3306/adData";
	private static String USERNAME = "ars";
	private static String PASSWORD = "password";
	
	public static String AD_LOCATION_VISIT = "ad_location_visit";
	public static String PAGE = "page";
	public static String KEYWORD = "keyword";
	public static String PAGE_KEYWORDS = "page_keywords";
	
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
		adVL.setCreatedAt(rs.getTimestamp("created_at"));
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
	
	public static void updatePage_KeywordTablePageId(long pageId, long[] keywordIds) throws SQLException {
		Connection conn = getConnection();
		String query = "insert into " + PAGE_KEYWORDS + " values (?, ?)";
		PreparedStatement stmt;
		for(int i = 0; i < keywordIds.length; i++) {
			stmt = conn.prepareStatement(query);
			stmt.setLong(1, keywordIds[i]);
			stmt.setLong(2, pageId);
			stmt.execute();
		}
	}
	
	public static void updatePage_KeywordTableKeywordId(long keywordId, long[] pageIds) throws SQLException {
		Connection conn = getConnection();
		String query = "insert into " + PAGE_KEYWORDS + " values (?, ?)";
		PreparedStatement stmt;
		for(int i = 0; i < pageIds.length; i++) {
			stmt = conn.prepareStatement(query);
			stmt.setLong(1, keywordId);
			stmt.setLong(2, pageIds[i]);
			stmt.execute();
		}
		conn.close();
	}

}
