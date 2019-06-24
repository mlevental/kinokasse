package com.levental.kinokasse.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vorstellung {
	private String name;
	private Map<String, List<String>> tagZeiten;

	public Vorstellung() {

	}

	public Vorstellung(String name, Map<String, List<String>> tagZeiten) {
		this.name = name;
		this.tagZeiten = tagZeiten;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, List<String>> getTagZeiten() {
		if (tagZeiten == null) {
			tagZeiten = new HashMap<String, List<String>>(); 
		}
		
		return tagZeiten;
	}

	public void setTagZeiten(Map<String, List<String>> tagZeiten) {
		this.tagZeiten = tagZeiten;
	}
}