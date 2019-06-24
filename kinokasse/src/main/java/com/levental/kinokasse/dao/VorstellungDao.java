package com.levental.kinokasse.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.levental.kinokasse.model.Vorstellung;

public class VorstellungDao implements Dao<Vorstellung> {
	@Override
	public List<Vorstellung> getAll() {
		String fileName = "files/Vorstellungen.txt";
		List<Vorstellung> vorstellungen = readVorstellungen(fileName);
		return vorstellungen;
	}

	private List<Vorstellung> readVorstellungen(String fileName) {
		List<Vorstellung> vorstellungen = new ArrayList<Vorstellung>();

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(line -> {
				Vorstellung vorstellung = fillVorstellung(line);
				vorstellungen.add(vorstellung);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		return vorstellungen;
	}

	private Vorstellung fillVorstellung(String line) {
		Vorstellung vorstellung = new Vorstellung();

		int titleEndPos = line.indexOf(";");
		String name = line.substring(0, titleEndPos);
		vorstellung.setName(name);

		line = line.substring(titleEndPos + 1);
		String[] vorstellungenAsStrings = line.split(",");
		for (String vorstellungAsString : vorstellungenAsStrings) {
			String[] vorstellungAsArray = vorstellungAsString.split("/");

			String tag = vorstellungAsArray[0].trim();
			String zeit = vorstellungAsArray[1].trim();

			Map<String, List<String>> tagZeiten = vorstellung.getTagZeiten();
			List<String> zeiten = tagZeiten.get(tag);
			if (zeiten == null) {
				zeiten = new ArrayList<String>();
			}
			zeiten.add(zeit);
			tagZeiten.put(tag, zeiten);

			vorstellung.setTagZeiten(tagZeiten);
		}

		vorstellung = orderVorstellungTagZeitenByWeekday(vorstellung);

		return vorstellung;
	}

	private Vorstellung orderVorstellungTagZeitenByWeekday(Vorstellung vorstellung) {
		Map<String, List<String>> tagZeiten = vorstellung.getTagZeiten();
		LinkedHashMap<String, List<String>> sortedTagZeiten = new LinkedHashMap<String, List<String>>();

		String[] weekdays = { "Mo", "Di", "Mi", "Do", "Fr", "Sa", "So" };
		for (String weekday : weekdays) {
			if (tagZeiten.containsKey(weekday)) {
				sortedTagZeiten.put(weekday, tagZeiten.get(weekday));
			}
		}

		vorstellung.setTagZeiten(sortedTagZeiten);

		return vorstellung;
	}
}
