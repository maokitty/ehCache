package com.own.ehCacheP.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EncacheController {
    @RequestMapping(value="/link")
	public String link(Model model){
    	model.addAttribute("test",System.currentTimeMillis());	
	 return "status";
   }
    @RequestMapping(value="save",method=RequestMethod.GET)
    public String index(Model model
    		          ){
    System.out.println(System.currentTimeMillis());
    model.addAttribute("msg",System.currentTimeMillis());
    return "stumessage";
    }
}
