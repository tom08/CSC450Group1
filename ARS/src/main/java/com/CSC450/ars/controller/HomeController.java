package com.CSC450.ars.controller;

import java.text.DateFormat;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.ZoneId;
import java.util.Locale;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.CSC450.support.UpdateClient;

import com.CSC450.ars.domain.Page;
import com.CSC450.ars.domain.AdLocationVisit;
import com.CSC450.dao.impl.PageDao;
import com.CSC450.dao.impl.AdLocationVisitDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	private PageDao pageDao = new PageDao();
	private AdLocationVisitDao adLocationDao = new AdLocationVisitDao();
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		model.addAttribute("page", new Page());
		model.addAttribute("pages", pageDao.getAll());
		AdLocationVisit last_ad = adLocationDao.getLatest();
		if(last_ad == null)
            model.addAttribute("needs_update", true);
        else{
            java.util.Date last_updated_date = last_ad.getCreatedAt();
            LocalDateTime last_updated = LocalDateTime.ofInstant(last_updated_date.toInstant(), ZoneId.systemDefault());
            Duration time_since_update = Duration.between(last_updated, LocalDateTime.now());
            if(time_since_update.toMinutes() > 120){
                model.addAttribute("needs_update", true);
            }
            else{
                model.addAttribute("needs_update", false);
            }
        }
		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/save_page", method = RequestMethod.POST)
	public String saveAdSpace(Model model, @ModelAttribute("page") Page page, BindingResult bindingResult) {
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
	
}
