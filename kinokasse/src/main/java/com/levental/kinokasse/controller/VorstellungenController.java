package com.levental.kinokasse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.levental.kinokasse.dao.VorstellungDao;

@Controller
public class VorstellungenController {

	@RequestMapping("/")
	public String vorstellungen(Model model) {
		model.addAttribute("vorstellungen", new VorstellungDao().getAll());
		return "vorstellungen";
	}
}