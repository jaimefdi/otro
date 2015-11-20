package es.fdi.twitter2.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.fdi.twitter2.entities.Tweet;
import es.fdi.twitter2.entities.Usuario;
import es.fdi.twitter2.entities.repositories.TweetRepository;

@Service
public class TweetService {

	@Autowired
	private TweetRepository tweet_repository;
	
	public TweetService(){
		super();
	}

	public List<Tweet> getLista() {
		return tweet_repository.getLista();
	}

	
	public void addTweet(Tweet t, Usuario u){
		t.setUsuario(u);
		t.setFecha(Calendar.getInstance().getTime());
		tweet_repository.addTweet(t);
	}
	
}
