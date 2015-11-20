package es.fdi.twitter2.controller;


import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import es.fdi.twitter2.entities.Tweet;
import es.fdi.twitter2.entities.Usuario;
import es.fdi.twitter2.service.TweetService;
import es.fdi.twitter2.service.UsuarioService;


@Controller
public class TweetController {

	@Autowired
	private TweetService tweet_service;
	
	@Autowired
	private UsuarioService user_service;
	
    public TweetController() {
		super();
	}
	
    @RequestMapping({"/","/welcome"})
    public String showTweets(Tweet t) {
        return "welcome";
    }
    
    	@RequestMapping(value="/welcome", params={"publicar"})
	public String publicar(Tweet t){
    		
		this.tweet_service.addTweet(t, user_service.getUsuarioLogueado());
		
		return "redirect:/welcome";
	}
	
	
}
