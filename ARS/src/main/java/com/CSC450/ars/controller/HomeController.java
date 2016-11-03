package com.CSC450.ars.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.io.IOException;

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

import com.CSC450.support.UpdateClient;

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
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) throws SQLException {
		model.addAttribute("pages", pageDao.getAll());
		return "home";
	}
	
	@RequestMapping(value="test_page/{pageId}", method=RequestMethod.GET)
	public String testPage(Model model, @PathVariable long pageId) throws SQLException {
		Page page = new Page();
		if(pageId > 0) {
			page = pageDao.getById(pageId);
		}
		model.addAttribute("page", page);
		return "page";
	}
	
	@RequestMapping(value="save_page", method=RequestMethod.POST)
	public String savePage(Model model, @ModelAttribute("page") Page page, BindingResult result) throws SQLException {
		//page.setKeywords(keywordDao.getAll());
		pageDao.save(page);
		return "redirect:/";
	}

	@RequestMapping(value = "/database", method = RequestMethod.GET)
	public String database_control(){
        return "database_commands";
    }

	@RequestMapping(value = "/database/update", method = RequestMethod.POST)
	public String update_from_remote(){
        UpdateClient updater = new UpdateClient();
        try{
            updater.connectToServer();
        }
        catch(IOException e){
            return "redirect:/database";
        }
        return "redirect:/";
    }
	/*
	@RequestMapping(value="delete_page/{pageId}", method=RequestMethod.POST)
	public String deletePage(Model model, @PathVariable long pageId) {
		pageDao.deleteById(pageId);
		return "redirect:/";
	}*/
	
	@RequestMapping(value="/test_ad_location_visit/{adLVId}", method=RequestMethod.GET)
	public String testAdLocationVisit(Model model, @PathVariable long adLVId) throws SQLException {
		AdLocationVisit adLV = new AdLocationVisit();
		if(adLVId > 0) {
			adLV = adLVDao.getById(adLVId);
		}
		model.addAttribute("adLocationVisit", adLV);
		return "ad_location_visit";
	}
	
	@RequestMapping(value="/view_latest_adLocation", method=RequestMethod.GET)
	public String viewLatest(Model model) throws SQLException {
		model.addAttribute("adLV", adLVDao.getLatest());
		return "view_latest";
	}
	
	@RequestMapping(value="save_ad_location_visit", method=RequestMethod.POST)
	public String savePage(Model model, @ModelAttribute("adLocationVisit") AdLocationVisit adLocationVisit, BindingResult result) throws SQLException {
		adLVDao.save(adLocationVisit);
		return "redirect:/";
	}/*
	
	@RequestMapping(value="/test-keyword", method=RequestMethod.GET)
	public String testKeyword(Model model) {
		model.addAttribute("keyword", new Keyword());
		return "keyword";
	}
	
	@RequestMapping(value="save_keyword", method=RequestMethod.POST)
	public String saveKeyword(Model model, @ModelAttribute("keyword") Keyword keyword, BindingResult result) {
		keywordDao.save(keyword);
		return "redirect:/";
	}*/
	
	
}
