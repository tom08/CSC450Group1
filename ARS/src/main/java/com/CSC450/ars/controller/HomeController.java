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

import com.CSC450.support.UpdateClient;

import com.CSC450.ars.domain.AdLocationVisit;
import com.CSC450.ars.domain.Keyword;
import com.CSC450.ars.domain.Page;
import com.CSC450.ars.domain.StatDisplay;
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
    
	@RequestMapping(value = "/estimate", method = RequestMethod.GET)
	public String getEstimateHome(Model model){

        return "estimateForm";
    }

    @RequestMapping("/get_similar_keywords/{token}")
    @ResponseBody
    public String getSimilarKeywords(@PathVariable String token) throws SQLException {
        // Handle ajax search for similar keywords as the user types.

        List<Keyword> keywords = keywordDao.getSimilarKeywords(token);

        String html = "";
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
			@RequestParam("focusRatioWeight") Double focusRatioWeight, @RequestParam("min") double min_value, @RequestParam("max") double max_value) throws IOException {
		//Save weights here - or whatever we're going to do with them. Do it here.
		
		JsonObject configFile = Json.createObjectBuilder()
				.add("activeRatioWeight", activeRatioWeight)
				.add("focusRatioWeight", focusRatioWeight)
				.add("min", min_value)
				.add("max", max_value)
				.build();
		
		OutputStream outputFile = new FileOutputStream("./ARSConfigFile.txt");
		JsonWriter writer = Json.createWriter(outputFile);
		writer.writeObject(configFile);
		writer.close();
		return "redirect:/";
	}
	
	
	@RequestMapping(value="submitKeywords", method=RequestMethod.POST)
	public String getAdloctionVists(Model model, @RequestParam("keywords") List<Long> keywords) throws SQLException, IOException {
		Set<Page> pages = new HashSet<Page>();
		Set<AdLocationVisit> ad_location_visits = new HashSet<AdLocationVisit>();
		for(Long keyword: keywords){
			pages.addAll(pageDao.getPagesByKeywordId(keyword));
		}
		for(Page page: pages){
			ad_location_visits.addAll(adLVDao.getByPageId(page.getId()));
		}

		//AdLocationVisit.RatioFormula();
		double sum = 0;
		for(AdLocationVisit visit: ad_location_visits){
			sum += visit.RatioFormula(0.4, 0.5);
		}
		double average = sum/ad_location_visits.size();
		
		InputStream inputFile = new FileInputStream("./ARSConfigFile.txt");
		JsonReader jsonReader = Json.createReader(inputFile);
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		inputFile.close();
		JsonNumber minNum = jsonObject.getJsonNumber("min");
		double minValue = minNum.doubleValue();
		JsonNumber maxNum = jsonObject.getJsonNumber("max");
		double maxValue = maxNum.doubleValue();
		double dollarValue = minValue + (average * (maxValue - minValue));
		model.addAttribute("ad_value", dollarValue);

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
        UpdateClient updater = new UpdateClient();
        try{
            updater.connectToServer();
        }
        catch(IOException e){
            return "redirect:/database";
        }
        return "redirect:/";
    }

	@RequestMapping(value="/view_latest_adLocation", method=RequestMethod.GET)
	public String viewLatest(Model model) throws SQLException {
		model.addAttribute("adLV", adLVDao.getLatest());
		return "view_latest";
	}
//----------------------------------------------------------------------
	@RequestMapping(value="avgKeywords", method=RequestMethod.POST)
	public String getAdloctionVists() throws SQLException {
		List<Keyword> keywords = keywordDao.getAll();
		for(Keyword keyword: keywords){
			Set<Page> pages = new HashSet<Page>();
			pages.addAll(pageDao.getPagesByKeywordId(keyword.getId()));
			Set<AdLocationVisit> ad_location_visits = new HashSet<AdLocationVisit>();
			for(Page page: pages){
				ad_location_visits.addAll(adLVDao.getByPageId(page.getId()));
			}

			double sum = 0;
			for(AdLocationVisit visit: ad_location_visits){
				sum += visit.RatioFormula(0.4, 0.5);
			}
			double average = sum/ad_location_visits.size();
			keyword.setValue(average);
		}

		Collections.sort(keywords, new Comparator<Keyword>() {
  		public int compare(Keyword c1, Keyword c2) {
    	if (c1.getValue() > c2.getValue()) return -1;
    	if (c1.getValue() < c2.getValue()) return 1;
    	return 0;
		}});
		return "redirect:/";
	}
}
