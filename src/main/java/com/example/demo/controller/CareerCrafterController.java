package com.example.demo.controller;



import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.client.RestTemplate;

import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Admin;

@Controller

public class CareerCrafterController {
	
	@GetMapping("/title")
	public ModelAndView title() {
	    ModelAndView view = new ModelAndView("title"); // Check if "title" is the correct view name
	    return view;
	}
	
	
	
	@GetMapping("/menu")
	public ModelAndView menu() {
		ModelAndView view = new ModelAndView("menu");
		return view;
	}
	
	@GetMapping("/contact")
	public ModelAndView contact() {
		ModelAndView view = new ModelAndView("contact");
		return view;
	}
	
	@GetMapping("/about")
	public ModelAndView about() {
		ModelAndView view = new ModelAndView("about");
		return view;
	}
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("index");
		return view;
	}
	
}
