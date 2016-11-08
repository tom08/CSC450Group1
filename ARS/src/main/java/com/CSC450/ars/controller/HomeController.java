package com.CSC450.ars.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.io.IOException;
import java.util.List;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import com.CSC450.support.UpdateClient;

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
	    AdLocationVisit latest_ad = adLVDao.getLatest();
	    if(latest_ad == null){
            model.addAttribute("needsUpdate", true);
            model.addAttribute("lastUpdatedDate", "Never Updated");
            model.addAttribute("numPages", 0);
            model.addAttribute("numAds", 0);
            model.addAttribute("numAdsTracked", 0);
        }
        else{
            LocalDateTime last_updated = latest_ad.getCreatedAt().toLocalDateTime();
            LocalDateTime right_now = LocalDateTime.now();
            long minutes = last_updated.until(right_now, ChronoUnit.MINUTES);
            if(minutes > 120)
                model.addAttribute("needsUpdate", true);
            else
                model.addAttribute("needsUpdate", false);
            model.addAttribute("lastUpdatedDate", last_updated);
            model.addAttribute("numPages", pageDao.count());
            model.addAttribute("numAds", adLVDao.countDistinct());
            model.addAttribute("numAdsTracked", adLVDao.count());
        }
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
	}
	
	@RequestMapping(value="save_keyword", method=RequestMethod.POST)
	public String saveKeyword(Model model, @ModelAttribute("keyword") Keyword keyword, BindingResult result) throws SQLException {
		keywordDao.save(keyword);
		return "redirect:/";
	}
	
	
}
