package com.levental.kinokasse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VorstellungenController {

	@RequestMapping("/")
	public String vorstellungen() {
		return "vorstellungen";
	}
}
