package es.fdi.twitter2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import es.fdi.twitter2.entities.Usuario;
import es.fdi.twitter2.service.TweetService;
import es.fdi.twitter2.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private TweetService tweet_service;
	
	@Autowired
	private UsuarioService user_service;
	
    public UsuarioController() {
		super();
	}
    
    
    @RequestMapping(value="/login")
    public String login(){
    	return "login";
    }
    
    @RequestMapping(value="/login", params={"entrar"}, method=RequestMethod.POST)
	public ModelAndView loguearse(Usuario u){
    	 	
    	Usuario user = user_service.getUsuarioLogueado();
    	if(user != null){
    		user_service.loguear(user);
    	    return new ModelAndView("welcome","userlogin", user);	
    	}
    	else
    	    return new ModelAndView("login","error", "Usuario o contrasena incorrectos!!");
	}
    
    @RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView desloguear(){
    	 	
    	user_service.loguear(null);
    	ModelAndView modelo=new ModelAndView("welcome");
    	modelo.addObject("all_tweets", tweet_service.getLista());
    	modelo.addObject("userlogin", null);
    	return modelo;
	}
    
    @RequestMapping(value="/registro")
    public String registro(){
    	return "registro";
    }
    
    
    @RequestMapping(value="/registro", params={"registrar"}, method=RequestMethod.POST)
    public ModelAndView registrar(Usuario u){
    	
    	ModelAndView model = new ModelAndView("registro");
    	if(user_service.addUsuario(u))
    	 model.addObject("exito", "Usuario registrado con exito");
    	else
    	  model.addObject("error", "No se ha podido registrar el usuario");
    	
    	return model;
    	
    }
    
}
