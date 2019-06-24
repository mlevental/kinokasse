package com.levental.kinokasse;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.levental.kinokasse.dao.VorstellungDao;
import com.levental.kinokasse.model.Vorstellung;

public class DaoTests {

	@Test
	public void testReadVorstellungen() {
		List<Vorstellung> vorstellungenActual = new VorstellungDao().getAll();
		
		Vorstellung vorstellungFirst = vorstellungenActual.get(0);
		assertEquals(vorstellungFirst.getName(), "Titanic");
		assertEquals(vorstellungFirst.getTagZeiten().get("Fr"), Arrays.asList( "17:00", "20:00", "23:00"));
		
		Vorstellung vorstellungSecond = vorstellungenActual.get(1);
		assertEquals(vorstellungSecond.getName(), "Matrix");
		assertEquals(vorstellungSecond.getTagZeiten().get("Do"), Arrays.asList("14:00"));
		
		Vorstellung vorstellungThird = vorstellungenActual.get(2);
		assertEquals(vorstellungThird.getName(), "Der Schuh des Königs");
		assertEquals(vorstellungThird.getTagZeiten().get("Sa"), Arrays.asList("17:00", "20:00"));
	}

}
