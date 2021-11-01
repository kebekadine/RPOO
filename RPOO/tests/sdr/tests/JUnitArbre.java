package sdr.tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import sdr.Arbre;
import sdr.ChaineSimple;

public class JUnitArbre {

	// ------------------------ Données de test

	private Arbre<Integer> a1;
	private Arbre<Integer> a2;
	private Arbre<Integer> a3;
	private Arbre<String> adm;

	// ------------------------ Fonctions auxiliaires

	@SafeVarargs
	private static <E> boolean valideChaineSimple(ChaineSimple<E> cs, E... v) {
		int iv;             // indice de parcours de v
		ChaineSimple<?> c;  // itérateur de cs
		String r = "";      // représentation littérale de cs
		boolean ko = false; // cas d'erreur
		for (c = cs, iv = 0; c != null; c = c.suivant, iv++) {
			r += " " + c.valeur;
			ko = ko || c.valeur == null || iv == v.length || !c.valeur.equals(v[iv]);
		}
		ko = ko || iv != v.length; // plus de valeurs dans v que dans cs
		if (ko)
			System.out.println("[" + new Exception().getStackTrace()[1].getMethodName() 
					+ "] chaîne : (" + r + " ) ; valeurs attendues : " + Arrays.toString(v));
		return !ko;
	}

	private static Object[] valideChainage(Arbre<?> a) {
		if (a == null)
			return new Object[0];
		Object[] tres = { a.valeur };
		if (a.fils != null) {
			Object[] tfils = valideChainageFreres(a.fils);
			tres = Arrays.copyOf(tres, tfils.length + 1);
			System.arraycopy(tfils, 0, tres, 1, tfils.length);
		}
		return tres;
	}

	private static Object[] valideChainageFreres(Arbre<?> a) {
		if (a == null)
			return new Object[0];
		Object[] tres = valideChainage(a);
		if (a.frere != null) {
			Object[] tfrere = valideChainageFreres(a.frere);
			tres = Arrays.copyOf(tres, tfrere.length + tres.length);
			System.arraycopy(tfrere, 0, tres, tres.length - tfrere.length, tfrere.length);
		}
		return tres;
	}

	@SuppressWarnings("unchecked")
	private static <E extends Comparable<E>> boolean valideArbre(Arbre<E> a, E... v) {		
		Object[] t = valideChainage(a);
		if (!Arrays.equals(t, v)) {
			System.out.println(
					"[" + new Exception().getStackTrace()[1].getMethodName() + "] parcours préfixe de l'arbre : "
							+ Arrays.toString(t) + " ; valeurs attendues : " + Arrays.toString(v));
			return false;
		}
		return true;
	}

	private static boolean factoriseValide(String[] t, String s0, String s1, String s2) {
		return t != null && t[0].equals(s0) && t[1].equals(s1) && t[2].equals(s2);
	}

	// ------------------------ Setup et validation des données de test

	@Before
	public void setup() {
		// a1
		a1 = new Arbre<>(5);
		// a2
		a2 = new Arbre<>(0);
		a2.fils = new Arbre<>(1);
		a2.fils.frere = new Arbre<>(2);
		a2.fils.frere.frere = new Arbre<>(1);
		a2.fils.frere.frere.frere = new Arbre<>(3);
		// a3
		a3 = new Arbre<>(7);
		a3.fils = new Arbre<>(8);
		a3.fils.frere = new Arbre<>(5);
		a3.fils.frere.frere = new Arbre<>(1);
		a3.fils.fils = new Arbre<>(-1);
		a3.fils.fils.frere = new Arbre<>(-2);
		a3.fils.frere.frere.fils = new Arbre<>(4);
		a3.fils.frere.frere.fils.frere = new Arbre<>(9);
		a3.fils.frere.frere.fils.frere.fils = new Arbre<>(6);
		// adm
		adm = new Arbre<>("");
		adm.fils = new Arbre<>("abri");
		adm.fils.frere = new Arbre<>("tro");
		adm.fils.fils = new Arbre<>("");
		adm.fils.fils.frere = new Arbre<>("cots");
		adm.fils.fils.frere.frere = new Arbre<>("tation");
		adm.fils.frere.fils = new Arbre<>("mpette");
		adm.fils.frere.fils.frere = new Arbre<>("pical");
	}

	@Test
	public void testSetup() { // vérification de l'état des données de test
		assertTrue(valideArbre(a1, 5));
		assertNull(a1.fils);
		assertNull(a1.frere);
		assertTrue(valideArbre(a2, 0, 1, 2, 1, 3));
		assertNull(a2.frere);
		assertTrue(valideArbre(a3, 7, 8, -1, -2, 5, 1, 4, 9, 6));
		assertNull(a3.frere);
	}

	// ------------------------ Test des méthodes de Arbre

	@Test
	public void testFeuille() {
		assertTrue(a1.feuille());
		assertFalse(a2.feuille());
		assertFalse(a3.feuille());
		assertTrue(a2.fils.feuille());
		assertTrue(a2.fils.frere.feuille());
		assertTrue(a2.fils.frere.frere.feuille());
		assertTrue(a2.fils.frere.frere.frere.feuille());
		assertFalse(a3.fils.feuille());
		assertTrue(a3.fils.fils.feuille());
	}

	@Test
	public void testInsereElementFils() {
		Arbre<Integer> r = a1.insereElementFils(6);
		assertTrue(valideArbre(a1, 5, 6));
		assertTrue(valideArbre(r, 6));
		r = a2.insereElementFils(6);
		assertTrue(valideArbre(a2, 0, 6, 1, 2, 1, 3));
		assertTrue(valideArbre(r, 6));
		r = a3.insereElementFils(6);
		assertTrue(valideArbre(a3, 7, 6, 8, -1, -2, 5, 1, 4, 9, 6));
		assertTrue(valideArbre(r, 6));
	}

	@Test
	public void testInsereElementFrere() {
		Arbre<Integer> r = a1.insereElementFrere(6);
		assertTrue(valideArbre(a1, 5));
		assertTrue(valideArbre(r, 6));
		r = a2.fils.insereElementFrere(6);
		assertTrue(valideArbre(a2, 0, 1, 6, 2, 1, 3));
		assertTrue(valideArbre(r, 6));
		r = a3.fils.insereElementFrere(6);
		assertTrue(valideArbre(a3, 7, 8, -1, -2, 6, 5, 1, 4, 9, 6));
		assertTrue(valideArbre(r, 6));
	}

	@Test
	public void testSupprimeFils() {
		Arbre<Integer> r = a1.supprimeFils();
		assertTrue(valideArbre(a1, 5));
		assertNull(r);
		r = a2.supprimeFils();
		assertTrue(valideArbre(a2, 0, 2, 1, 3));
		assertTrue(valideArbre(r, 1));
		r = a3.supprimeFils();
		assertTrue(valideArbre(a3, 7, 5, 1, 4, 9, 6));
		assertTrue(valideArbre(r, 8, -1, -2));
	}

	@Test
	public void testSupprimeFrere() {
		Arbre<Integer> r = a1.supprimeFrere();
		assertTrue(valideArbre(a1, 5));
		assertNull(r);
		r = a2.fils.supprimeFrere();
		assertTrue(valideArbre(a2, 0, 1, 1, 3));
		assertTrue(valideArbre(r, 2));
		r = a3.fils.supprimeFrere();
		assertTrue(valideArbre(a3, 7, 8, -1, -2, 1, 4, 9, 6));
		assertTrue(valideArbre(r, 5));
	}

	@Test
	public void testNbFeuilles() {
		assertEquals(a1.nbFeuilles(), 1);
		assertEquals(a2.nbFeuilles(), 4);
		assertEquals(a3.nbFeuilles(), 5);
	}

	@Test
	public void testNbElements() {
		assertEquals(a1.nbElements(), 1);
		assertEquals(a2.nbElements(), 5);
		assertEquals(a3.nbElements(), 9);
	}

	@Test
	public void testNbFils() {
		assertEquals(a1.nbFils(), 0);
		assertEquals(a2.nbFils(), 4);
		assertEquals(a3.nbFils(), 3);
	}

	@Test
	public void testFactorise() {
		Arbre<String> a = adm.fils;
		assertTrue(factoriseValide(a.factorise(""), "", "", "abri"));
		assertTrue(factoriseValide(a.factorise("maison"), "", "maison", "abri"));
		assertTrue(factoriseValide(a.factorise("arbre"), "a", "rbre", "bri"));
		assertTrue(factoriseValide(a.factorise("abrupte"), "abr", "upte", "i"));
		assertTrue(factoriseValide(a.factorise("abricot"), "abri", "cot", ""));
	}

	@Test
	public void testAjouteMot() {
		assertTrue(adm.ajouteMot("vol"));
		assertEquals(adm.fils.frere.frere.valeur, "vol");
		assertFalse(adm.ajouteMot("abri"));
		assertEquals(adm.fils.valeur, "abri");
		assertEquals(adm.fils.fils.valeur, "");
		assertEquals(adm.fils.fils.frere.valeur, "cots");
		assertEquals(adm.fils.fils.frere.frere.valeur, "tation");
		assertTrue(adm.ajouteMot("ab"));
		assertEquals(adm.fils.valeur, "ab");
		assertEquals(adm.fils.fils.valeur, "");
		assertEquals(adm.fils.fils.frere.valeur, "ri");
		assertNull(adm.fils.fils.frere.frere);
		assertEquals(adm.fils.fils.frere.fils.valeur, "");
		assertEquals(adm.fils.fils.frere.fils.frere.valeur, "cots");
		assertEquals(adm.fils.fils.frere.fils.frere.frere.valeur, "tation");
		assertTrue(adm.ajouteMot("trompettes"));
		assertEquals(adm.fils.frere.valeur, "tro");
		assertEquals(adm.fils.frere.fils.valeur, "mpette");
		assertEquals(adm.fils.frere.fils.fils.valeur, "");
		assertEquals(adm.fils.frere.fils.fils.frere.valeur, "s");
		assertTrue(adm.ajouteMot("trousse"));
		assertEquals(adm.fils.frere.valeur, "tro");
		assertEquals(adm.fils.frere.fils.valeur, "mpette");
		assertEquals(adm.fils.frere.fils.fils.valeur, "");
		assertEquals(adm.fils.frere.fils.fils.frere.valeur, "s");
		assertEquals(adm.fils.frere.fils.frere.valeur, "pical");
		assertEquals(adm.fils.frere.fils.frere.frere.valeur, "usse");
	}

	@Test
	public void testExisteMot() {
		assertFalse(adm.existeMot(""));
		assertFalse(adm.existeMot("a"));
		assertFalse(adm.existeMot("abr"));
		assertTrue(adm.existeMot("abri"));
		assertFalse(adm.existeMot("abris"));
		assertFalse(adm.existeMot("abricot"));
		assertTrue(adm.existeMot("abricots"));
		assertTrue(adm.existeMot("abritation"));
		assertFalse(adm.existeMot("abritations"));
		assertFalse(adm.existeMot("nuit"));
		assertFalse(adm.existeMot("table"));
		assertFalse(adm.existeMot("tro"));
		assertFalse(adm.existeMot("trop"));
		assertTrue(adm.existeMot("trompette"));
		assertFalse(adm.existeMot("trompettes"));
		assertTrue(adm.existeMot("tropical"));
		assertFalse(adm.existeMot("tropicale"));
		assertFalse(adm.existeMot("vol"));
	}

	@Test
	public void testSupprimeMot() {
		assertFalse(adm.supprimeMot(""));
		assertTrue(adm.supprimeMot("abri"));
		assertEquals(adm.fils.valeur, "abri");
		assertEquals(adm.fils.fils.valeur, "cots");
		assertEquals(adm.fils.fils.frere.valeur, "tation");
		assertNull(adm.fils.fils.frere.frere);
		assertFalse(adm.supprimeMot("abricot"));
		assertTrue(adm.supprimeMot("abricots"));
		assertEquals(adm.fils.valeur, "abritation");
		assertNull(adm.fils.fils);
		assertTrue(adm.supprimeMot("trompette"));
		assertEquals(adm.fils.frere.valeur, "tropical");
		assertNull(adm.fils.frere.fils);
	}

	@Test
	public void testBoutDuChemin() {
		assertTrue(valideArbre(adm.boutDuChemin(""), "", "abri", "", "cots", "tation", "tro", "mpette", "pical"));
		assertTrue(valideArbre(adm.boutDuChemin("ab"), "ri", "", "cots", "tation"));
		assertTrue(valideArbre(adm.boutDuChemin("abri"), "", "", "cots", "tation"));
		assertTrue(valideArbre(adm.boutDuChemin("abrita"), "tion"));
		assertNull(adm.boutDuChemin("abris"));
		assertTrue(valideArbre(adm.boutDuChemin("trom"), "pette"));
		assertNull(adm.boutDuChemin("tromm"));
	}

	@Test
	public void testRecupMots() {
		ChaineSimple<String> s = adm.recupMots("");
		assertTrue(valideChaineSimple(s, "abri", "abricots", "abritation", "trompette", "tropical"));
	}

}
