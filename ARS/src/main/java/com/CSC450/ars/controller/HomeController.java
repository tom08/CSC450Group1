package com.CSC450.ars.controller;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Comparator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.FileInputStream;
import java.io.InputStream;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CSC450.support.ARSConfigFile;
import com.CSC450.support.UpdateClient;

import com.CSC450.ars.domain.AdLocationVisit;
import com.CSC450.ars.domain.Keyword;
import com.CSC450.ars.domain.Page;
import com.CSC450.ars.validation.ARSValidator;
import com.CSC450.dao.impl.AdLocationVisitDao;
import com.CSC450.dao.impl.KeywordDao;
import com.CSC450.dao.impl.PageDao;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
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
	public String dashboard(Model model) throws SQLException {
	    AdLocationVisit latest_ad = adLVDao.getLatest();
		// If there is no data
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
				// If longer than 2 hours, display update banner
                model.addAttribute("needsUpdate", true);
            else
                model.addAttribute("needsUpdate", false);
            model.addAttribute("lastUpdatedDate", last_updated);
            model.addAttribute("numPages", pageDao.count());
            model.addAttribute("maxAdsPerPage", adLVDao.countDistinct());
            model.addAttribute("numAdsTracked", adLVDao.count());
        }
		return "dashboard";
	}
	
	@RequestMapping(value = "/view_existing", method = RequestMethod.GET)
	public String viewExisting(Model model) throws SQLException {
		return "view_existing";
	}

	@RequestMapping(value = "/viewPages", method = RequestMethod.GET)
	public String viewPages(Model model) throws SQLException {
		model.addAttribute("pages", pageDao.getAll());
		return "pages";
	}
    
	@RequestMapping(value = "/estimate", method = RequestMethod.GET)
	public String getEstimateHome(Model model){
        return "estimateForm";
    }

    @RequestMapping("/get_similar_keywords/{token}")
    @ResponseBody
    public String getSimilarKeywords(@PathVariable String token) throws SQLException {
        // Handle ajax search for similar keywords as the user types for estimate form.
        String html = "";
        List<Keyword> keywords = keywordDao.getSimilarKeywords(token);
        if(keywords.size() > 0){
            // Build the html to show the similar keywords for the user to select from.
            html += "<h4>Select Keyword(s) to add to your hypothetical advertisement</h4>";
            for(Keyword kwd: keywords){
                html += "<div class='alert alert-info result' data-kname='"+kwd.getKeywordName()+"' ";
                html += "data-id='"+Long.toString(kwd.getId())+"'>";
                html += "<h4>"+kwd.getKeywordName()+"</h4>";
                html += "</div>";
            }
        }
        else{
            html += "<h4>No Keywords match!</h4>";
        }

        return html;
    }
	
	@RequestMapping(value = "/viewKeywords", method = RequestMethod.GET)
	public String viewKeywords(Model model) throws SQLException, IOException {
		List<Keyword> keywords = getSortedKeywords();
		if(keywords == null) {
			return "redirect:/error?message=Please set Active and Focus Ratio Weights in settings";
		}
		model.addAttribute("keywords", getSortedKeywords());
		return "keywords";
	}
	
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String settings(Model model) throws SQLException {
		return "settings";
	}
	
	@RequestMapping(value = "/save_settings", method = RequestMethod.POST)
	public String saveSettings(Model model, @RequestParam("activeRatioWeight") String activeRatioWeight,
			@RequestParam("focusRatioWeight") String focusRatioWeight, @RequestParam("min") String min_value, @RequestParam("max") String max_value) throws IOException {

		ARSValidator validator = new ARSValidator();
		// If all values are in the valid input domain
		if(validator.validateSettings(activeRatioWeight, focusRatioWeight, min_value, max_value)) {
			ARSConfigFile configFile = new ARSConfigFile();
			// Save values to config file
			configFile.write(Double.parseDouble(activeRatioWeight), Double.parseDouble(focusRatioWeight), Double.parseDouble(min_value), Double.parseDouble(max_value));
			return "redirect:/";
		}
		else {
			// Return validation errors and user input
			model.addAttribute("minValue", min_value);
			model.addAttribute("maxValue", max_value);
			model.addAttribute("active", activeRatioWeight);
			model.addAttribute("focus", focusRatioWeight);
			model.addAttribute("errorMessage", validator.getErrorMessage());
			return "settings";
		}

	}
	
	
	@RequestMapping(value="submitKeywords", method=RequestMethod.POST)
	public String getAdloctionVists(Model model, @RequestParam("keywords") List<Long> keywords) throws SQLException, IOException {
		Set<Page> pages = new HashSet<Page>();
		Set<AdLocationVisit> ad_location_visits = new HashSet<AdLocationVisit>();
		// Server throws 400 error if no keywords are selected
		// Dummy keyword always passed to server with id -1 to avoid this error
		Long throwawayKeyword = -1L;
		
		// If only dummy keyword exists, no data was passed
		if(keywords.size() == 1){
			model.addAttribute("errorMessage", "There are no keywords to send!");
			return "estimateForm";
			}
			
		// Remove dummy keyword so it isn't displayed
		keywords.remove(throwawayKeyword);
		
		// Get all pages associated with each keyword
		for(Long keyword: keywords){
			pages.addAll(pageDao.getPagesByKeywordId(keyword));
		}
		
		// Gets each ad location visit associated with each page
		for(Page page: pages){
			ad_location_visits.addAll(adLVDao.getByPageId(page.getId()));
		}

		// Get weights from config file.
		ARSConfigFile configFile = new ARSConfigFile();
		Double activeRatioWeight = configFile.get(ARSConfigFile.ACTIVE);
		Double focusRatioWeight = configFile.get(ARSConfigFile.FOCUS);
		
		// If something goes wrong accessing the config file give an error message
		if(activeRatioWeight == null || focusRatioWeight == null) {
			return "redirect:/error?message=Please set Active and Focus Ratio Weights in settings";
		}
		
		// Generate weighted averages for each keyword using ad location visits
		double sum = 0;
		for(AdLocationVisit visit: ad_location_visits){
			// Divide by 100 to convert whole numbers to percentages
			sum += visit.RatioFormula(activeRatioWeight, focusRatioWeight) / 100;
		}
		double average = sum/ad_location_visits.size();
		model.addAttribute("ad_value", generateDollarValue(average));

		// Get each keyword for context in displaying the result
		Set<Keyword> kwds = new HashSet<Keyword>();
		for(long id: keywords){
            kwds.add(keywordDao.getById(id));
        }
		model.addAttribute("keywords", kwds);

		return "displayEstimate";
	}

	@RequestMapping(value = "/database", method = RequestMethod.GET)
	public String database_control(){
        return "database_commands";
    }

	@RequestMapping(value = "/database/update", method = RequestMethod.POST)
	public String update_from_remote(){
		// Connect to proxy server to get new data
        UpdateClient updater = new UpdateClient();
        String message = "";
        String url = "redirect:/";
        try{
            updater.connectToServer();
        }
        catch(IOException e){
        	message = "There was a problem connecting to the server.";
            url = "redirect:/error?message=";
        }
        catch(SQLException e){
        	message = "There was a problem connection to the database.\n";
            url = "redirect:/error?message=";
        }
        return url + message;
    }
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(Model model, @RequestParam String message){
		model.addAttribute("errorMessage", message);
       return "error";
    }

//----------------------------------------------------------------------
	public List<Keyword> getSortedKeywords() throws SQLException, IOException {
		List<Keyword> keywords = keywordDao.getAll();
		List<Keyword> keywordsWithNoData = new ArrayList<Keyword>();
		
		// Get all pages associated with each keyword
		for(Keyword keyword: keywords){
			Set<Page> pages = new HashSet<Page>();
			pages.addAll(pageDao.getPagesByKeywordId(keyword.getId()));
			Set<AdLocationVisit> ad_location_visits = new HashSet<AdLocationVisit>();
			//Find all ad location visits associated with the keyword
			for(Page page: pages){
				ad_location_visits.addAll(adLVDao.getByPageId(page.getId()));
			}

			// Get weight values from config file
			ARSConfigFile configFile = new ARSConfigFile();
			Double activeRatioWeight = configFile.get(ARSConfigFile.ACTIVE);
			Double focusRatioWeight = configFile.get(ARSConfigFile.FOCUS);
			double sum = 0;
			double focusSum = 0;
			double activeSum = 0;
			
			//If there is a problem accessing config file return null to redirect to error page
			if(activeRatioWeight == null || focusRatioWeight == null) {
				return null;
			}
			//Calculate averages for each keyword from associated ad location visits
			for(AdLocationVisit visit: ad_location_visits){
				focusSum+= visit.getFocusRatio();
				activeSum += visit.getActiveRatio();
				// Divide by 100 to convert whole numbers to percentages
				sum += visit.RatioFormula(activeRatioWeight, focusRatioWeight) / 100;
			}
			// If data exists for keyword, set values
			if(sum != 0) {
				double average = sum/ad_location_visits.size();
				double focusAvg = focusSum / ad_location_visits.size();
				double activeAvg = activeSum / ad_location_visits.size();
				keyword.setActiveRatio(activeAvg);
				keyword.setFocusRatio(focusAvg);
				keyword.setValue(average);
				keyword.setDollarValue(generateDollarValue(average));
			}
			else {
				keywordsWithNoData.add(keyword);
			}
		}
		//Remove keywords with no data.
		keywords.removeAll(keywordsWithNoData);

		Collections.sort(keywords, new Comparator<Keyword>() {
  		public int compare(Keyword c1, Keyword c2) {
	    	if (c1.getValue() < c2.getValue()) return -1;
	    	if (c1.getValue() > c2.getValue()) return 1;
	    	return 0;
		}});
		return keywords;
	}
	
	public Double generateDollarValue(Double average) throws IOException {
		ARSConfigFile configFile = new ARSConfigFile();
		double minValue = configFile.get(ARSConfigFile.MIN);
		double maxValue = configFile.get(ARSConfigFile.MAX);
		double temp = minValue + (average * (maxValue - minValue));
		return minValue + (average * (maxValue - minValue));
	}
}
