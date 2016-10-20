package com.CSC450.ars.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.CSC450.ars.domain.AdLocationVisit;
import com.CSC450.ars.domain.Keyword;
import com.CSC450.ars.domain.Page;
import com.CSC450.dao.impl.AdLocationVisitDao;
import com.CSC450.dao.impl.KeywordDao;
import com.CSC450.dao.impl.PageDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private PageDao pageDao = new PageDao();
	private AdLocationVisitDao adLVDao = new AdLocationVisitDao();
	private KeywordDao keywordDao = new KeywordDao();
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("pages", pageDao.getAll());
		return "home";
	}
	
	@RequestMapping(value="/test-page", method=RequestMethod.GET)
	public String testPage(Model model) {
		model.addAttribute("page", new Page());
		model.addAttribute("availableKeywords", keywordDao.getAll());
		return "page";
	}
	
	@RequestMapping(value="save_page", method=RequestMethod.POST)
	public String savePage(Model model, @ModelAttribute("page") Page page, BindingResult result) {
		page.setKeywords(keywordDao.getAll());
		pageDao.save(page);
		return "redirect:/";
	}
	
	@RequestMapping(value="delete_page/{pageId}", method=RequestMethod.POST)
	public String deletePage(Model model, @PathVariable long pageId) {
		pageDao.deleteById(pageId);
		return "redirect:/";
	}
	
	@RequestMapping(value="/test-ad-location-visit", method=RequestMethod.GET)
	public String testAdLocationVisit(Model model) {
		model.addAttribute("adLocationVisit", new AdLocationVisit());
		return "ad_location_visit";
	}
	
	@RequestMapping(value="save_ad_location_visit", method=RequestMethod.POST)
	public String savePage(Model model, @ModelAttribute("adLocationVisit") AdLocationVisit adLocationVisit, BindingResult result) {
		adLVDao.save(adLocationVisit);
		return "redirect:/";
	}
	
	@RequestMapping(value="/test-keyword", method=RequestMethod.GET)
	public String testKeyword(Model model) {
		model.addAttribute("keyword", new Keyword());
		return "keyword";
	}
	
	@RequestMapping(value="save_keyword", method=RequestMethod.POST)
	public String saveKeyword(Model model, @ModelAttribute("keyword") Keyword keyword, BindingResult result) {
		keywordDao.save(keyword);
		return "redirect:/";
	}
	
	
}
