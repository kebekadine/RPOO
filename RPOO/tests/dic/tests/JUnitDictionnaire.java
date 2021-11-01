package dic.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import dic.Dictionnaire;

//------------------------ Classe de tests

public abstract class JUnitDictionnaire {

	// ------------------------ Données de test

	private Dictionnaire dE, d7, dM;

	private List<String> tabM;

	// ------------------------ Fonctions auxiliaires

	protected abstract boolean valideDictionnaire(Dictionnaire d, String... mots);
		
	protected abstract Dictionnaire creer(String... s);

	protected abstract Dictionnaire creerFile(String f);

	// ------------------------ Setup et validation des données de test

	@Before
	public void setup() {
		this.dE = creer();
		this.d7 = creer("abandon", "abeille", "lien", "lit", "lits", "marier", "mouche");
		this.dM = creerFile("mots/dicoM.txt");
		try {
			this.tabM = Files.lines(Paths.get("mots/dicoM.txt")).collect(Collectors.toList());
		} catch (IOException e) {
			throw new IOError(e);
		}
	}

	@Test
	public void testSetup() { // vérification de l'état des données de test
		assertTrue(valideDictionnaire(this.dE));
		assertTrue(valideDictionnaire(this.d7, "abandon", "abeille", "lien", "lit", "lits", "marier", "mouche"));
		assertTrue(valideDictionnaire(this.dM, this.tabM.toArray(new String[0])));
	}

	@AfterClass
	public static void clean() {
		File f = new File("testChargeSauvegarde.txt");
		if (f.exists())
			f.delete();
	}

	// ------------------------ Test des méthodes de Dictionnaire

	@Test
	public void testNbMots() {
		assertEquals(this.dE.nbMots(), 0);
		assertEquals(this.d7.nbMots(), 7);
		assertEquals(this.dM.nbMots(), 1356);
	}

	@Test
	public void testExiste() {
		assertFalse(this.dE.existe(null));
		assertFalse(this.dE.existe(""));
		assertFalse(this.dE.existe(""));
		assertFalse(this.dE.existe("lien"));
		assertFalse(this.dE.existe("bol"));
		assertTrue(valideDictionnaire(this.dE));
		assertFalse(this.d7.existe(null));
		assertFalse(this.d7.existe(""));
		assertFalse(this.d7.existe(""));
		assertTrue(this.d7.existe("lien"));
		assertFalse(this.d7.existe("bol"));
		assertTrue(valideDictionnaire(this.d7, "abandon", "abeille", "lien", "lit", "lits", "marier", "mouche"));
		assertFalse(this.dM.existe(null));
		assertFalse(this.dM.existe(""));
		assertFalse(this.dM.existe(""));
		assertTrue(this.dM.existe("lien"));
		assertFalse(this.dM.existe("bol"));
		assertTrue(valideDictionnaire(this.dM, this.tabM.toArray(new String[0])));
	}


	@Test
	public void testAjoute() {
		assertFalse(this.dE.ajoute(null));
		assertFalse(this.dE.ajoute(""));
		assertTrue(this.dE.ajoute("bol"));
		assertFalse(this.dE.ajoute("bol"));
		assertTrue(valideDictionnaire(this.dE, "bol"));
		assertFalse(this.d7.ajoute(null));
		assertFalse(this.d7.ajoute(""));
		assertTrue(this.d7.ajoute("bol"));
		assertFalse(this.d7.ajoute("bol"));
		assertTrue(valideDictionnaire(this.d7, "bol", "abandon", "abeille", "lien", "lit", "lits", "marier", "mouche"));
		assertFalse(this.dM.ajoute(null));
		assertFalse(this.dM.ajoute(""));
		assertTrue(this.dM.ajoute("bol"));
		assertFalse(this.dM.ajoute("bol"));
		this.tabM.add("bol");
		assertTrue(valideDictionnaire(this.dM, this.tabM.toArray(new String[0])));
	}

	@Test
	public void testSupprime() {
		assertFalse(this.dE.supprime(null));
		assertFalse(this.dE.supprime(""));
		assertFalse(this.dE.supprime("lien"));
		assertTrue(valideDictionnaire(this.dE));
		assertFalse(this.d7.supprime(null));
		assertFalse(this.d7.supprime(""));
		assertTrue(this.d7.supprime("lien"));
		assertFalse(this.d7.supprime("lien"));
		assertTrue(valideDictionnaire(this.d7, "abandon", "abeille", "lit", "lits", "marier", "mouche"));
		assertFalse(this.dM.supprime(null));
		assertFalse(this.dM.supprime(""));
		assertTrue(this.dM.supprime("lien"));
		assertFalse(this.dM.supprime("lien"));
		this.tabM.remove("lien");
		assertTrue(valideDictionnaire(this.dM, this.tabM.toArray(new String[0])));
	}

	@Test
	public void testPrefixe() {
		Dictionnaire d;
		d = this.dE.prefixe("z");
		assertNotNull(d);
		assertTrue(valideDictionnaire(d));
		d = this.dE.prefixe("li");
		assertNotNull(d);
		assertTrue(valideDictionnaire(d));
		d = this.d7.prefixe("z");
		assertNotNull(d);
		assertTrue(valideDictionnaire(d));
		d = this.d7.prefixe("li");
		assertNotNull(d);
		assertTrue(valideDictionnaire(d, "lien", "lit", "lits"));
		d = this.dM.prefixe("z");
		assertNotNull(d);
		assertTrue(valideDictionnaire(d));
		d = this.dM.prefixe("li");
		assertNotNull(d);
		assertTrue(valideDictionnaire(d,"liberte","libre","lien","lier","lieu","ligne","lire","lisser","lit","livre","livrer"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testPrefixeEx1() {
		this.d7.prefixe(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testPrefixeEx2() {
		this.d7.prefixe("");
	}

	@Test
	public void testChargeSauvegarde() throws IOException {
		File f = new File("testChargeSauvegarde.txt");
		assertFalse(f.exists());
		this.d7.sauvegarde(f.getName());
		assertTrue(f.exists());
		this.dE.charge(f.getName());
		assertEquals(this.dE.nbMots(), 7);
		assertTrue(this.dE.existe("abandon"));
		assertTrue(this.dE.existe("abeille"));
		assertTrue(this.dE.existe("lien"));
		assertTrue(this.dE.existe("lit"));
		assertTrue(this.dE.existe("lits"));
		assertTrue(this.dE.existe("marier"));
		assertTrue(this.dE.existe("mouche"));
		assertTrue(f.delete());
		assertFalse(f.exists());
	}

}
