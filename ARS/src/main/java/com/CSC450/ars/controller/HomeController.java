package com.CSC450.ars.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.CSC450.ars.domain.AdLocationVisit;
import com.CSC450.ars.domain.Keyword;
import com.CSC450.ars.domain.Page;
import com.CSC450.dao.impl.ARSDatabaseUtil;
import com.CSC450.dao.impl.AdLocationVisitDao;
import com.CSC450.dao.impl.KeywordDao;
import com.CSC450.dao.impl.PageDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final String DASHBOARD = "/";
	private PageDao pageDao = new PageDao();
	private AdLocationVisitDao adLVDao = new AdLocationVisitDao();
	private KeywordDao keywordDao = new KeywordDao();
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws SQLException 
	 */
	@RequestMapping(value = DASHBOARD, method = RequestMethod.GET)
	public String home(Model model) throws SQLException {
		model.addAttribute("lastUpdatedDate", adLVDao.getLatest().getCreatedAt());
		model.addAttribute("numPages", pageDao.count());
		model.addAttribute("numAds", adLVDao.countDistinct());
		model.addAttribute("numAdsTracked", adLVDao.count());
		model.addAttribute("keyword", new Keyword());
		model.addAttribute("page", new Page());
		return "dashboard";
	}
	
	@RequestMapping(value = "/viewPages", method = RequestMethod.GET)
	public String viewPages(Model model) throws SQLException {
		List<Page> pages = pageDao.getAll();
		for(Page page: pages) {
			page.setKeywords(keywordDao.getKeywordsByPageId(page.getId()));
		}
		model.addAttribute("pages", pages);
		return "pages";
	}
	
	@RequestMapping(value="save_page", method=RequestMethod.POST)
	public String savePage(Model model, @ModelAttribute("page") Page page, BindingResult result) throws SQLException {
		page.setKeywords(keywordDao.getAll());
		pageDao.save(page);
		return "redirect:/";
	}
	
	@RequestMapping(value="save_ad_location_visit", method=RequestMethod.POST)
	public String savePage(Model model, @ModelAttribute("adLocationVisit") AdLocationVisit adLocationVisit, BindingResult result) throws SQLException {
		adLVDao.save(adLocationVisit);
		return "redirect:/";
	}
	
	@RequestMapping(value="save_keyword", method=RequestMethod.POST)
	public String saveKeyword(Model model, @ModelAttribute("keyword") Keyword keyword, BindingResult result) throws SQLException {
		keyword.setPages(pageDao.getAll());
		keywordDao.save(keyword);
		return "redirect:/";
	}
	
	
}
