package com.CSC450.ars.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.CSC450.ars.domain.AdLocationVisit;
import com.CSC450.ars.domain.Keyword;
import com.CSC450.ars.domain.Page;
import com.CSC450.ars.domain.StatDisplay;
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
		return "dashboard";
	}
	
	@RequestMapping(value = "/rate_existing", method = RequestMethod.GET)
	public String rateExisting(Model model) throws SQLException {
		return "rate_existing";
	}
	
	@RequestMapping(value = "/viewPages", method = RequestMethod.GET)
	public String viewPages(Model model) throws SQLException {
		//List<StatDisplay> pages = blah_blah_calculate_page_stats_blah();
		//model.addAttribute("pages", pages);
		StatDisplay<Page> display = new StatDisplay<Page>(4, "Test", .5, .5, 80000, 600, "A");
		List<StatDisplay<Page>> pages = new ArrayList<StatDisplay<Page>>();
		pages.add(display);
		model.addAttribute("pages", pages);
		return "pages";
	}
	
	@RequestMapping(value = "/viewAds", method = RequestMethod.GET)
	public String viewAds(Model model) {
		//List<StatDisplay> ads = blah_blah_calculate_ad_stats_blah();
		//model.addAttribute("ads", ads);
		StatDisplay<AdLocationVisit> display = new StatDisplay<AdLocationVisit>(1, "This is a test and a blah blah blah", .5, .5, 80000, 600, "B");
		List<StatDisplay<AdLocationVisit>> ads = new ArrayList<StatDisplay<AdLocationVisit>>();
		for(int i = 0; i < 10; i++) {
		ads.add(display);
		}
		model.addAttribute("ads", ads);
		return "ads";
	}
	
	@RequestMapping(value = "/viewKeywords", method = RequestMethod.GET)
	public String viewKeywords(Model model) {
		//List<StatDisplay> keywords = blah_blah_calculate_ad_stats_blah();
		//model.addAttribute("keywords", keywords);
		return "keywords";
	}
	
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String settings(Model model) throws SQLException {
		return "settings";
	}
	
	@RequestMapping(value = "/save_settings", method = RequestMethod.POST)
	public String saveSettings(Model model, @RequestParam("activeRatioWeight") Double activeRatioWeight,
			@RequestParam("focusRatioWeight") Double focusRatioWeight) {
		//Save weights here - or whatever we're going to do with them. Do it here.
		System.out.println("activeRatioWeight: " + activeRatioWeight);
		System.out.println("focusRatioWeight: " + focusRatioWeight);
		return "redirect:/";
	}
	
	
}
