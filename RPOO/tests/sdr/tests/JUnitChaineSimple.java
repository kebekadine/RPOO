package sdr.tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import sdr.ChaineSimple;

public class JUnitChaineSimple {

	// ------------------------ Données de test

	private ChaineSimple<Integer> c1;
	private ChaineSimple<Integer> c2;
	private ChaineSimple<Integer> c3;

	// ------------------------ Fonction auxiliaire

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

	// ------------------------ Setup et validation des données de test

	@Before
	public void setup() {
		c1 = new ChaineSimple<>(1);
		c2 = new ChaineSimple<>(2, c1);
		c3 = new ChaineSimple<>(3, c2);
	}

	@Test
	public void testSetup() { // vérification de l'état des données de test
		assertTrue(valideChaineSimple(c1, 1));
		assertTrue(valideChaineSimple(c2, 2, 1));
		assertTrue(valideChaineSimple(c3, 3, 2, 1));
		assertNull(c1.suivant);
		assertSame(c2.suivant, c1);
		assertSame(c3.suivant, c2);
	}

	// ------------------------ Test des méthodes de ChaineSimple

	@Test
	public void tesToString() {
		assertEquals(c1.toString(), "(1)");
		assertEquals(c2.toString(), "(2, 1)");
		assertEquals(c3.toString(), "(3, 2, 1)");
	}

	@Test
	public void testInsereElementSuivant() {
		ChaineSimple<Integer> sc1 = c1.insereElementSuivant(-1);
		ChaineSimple<Integer> sc2 = c2.insereElementSuivant(-2);
		ChaineSimple<Integer> sc3 = c3.insereElementSuivant(-3);
		assertTrue(valideChaineSimple(c1, 1, -1));
		assertTrue(valideChaineSimple(c2, 2, -2, 1, -1));
		assertTrue(valideChaineSimple(c3, 3, -3, 2, -2, 1, -1));
		assertSame(c1.suivant, sc1);
		assertSame(c2.suivant, sc2);
		assertSame(c3.suivant, sc3);
	}

	@Test
	public void testSupprimeElementSuivant() {
		ChaineSimple<Integer> sc1 = c1.supprimeElementSuivant();
		assertTrue(valideChaineSimple(c1, 1));
		assertNull(sc1);
		ChaineSimple<Integer> sc2 = c2.supprimeElementSuivant();
		assertTrue(valideChaineSimple(c1, 1));
		assertTrue(valideChaineSimple(c2, 2));
		assertTrue(valideChaineSimple(c3, 3, 2));
		assertTrue(valideChaineSimple(sc2, 1));
		ChaineSimple<Integer> sc3 = c3.supprimeElementSuivant();
		assertTrue(valideChaineSimple(c1, 1));
		assertTrue(valideChaineSimple(c2, 2));
		assertTrue(valideChaineSimple(c3, 3));
		assertTrue(valideChaineSimple(sc3, 2));
	}

	@Test
	public void testMaj() {
		c1.maj(i -> i + 1);
		assertTrue(valideChaineSimple(c1, 2));
		assertTrue(valideChaineSimple(c2, 2, 2));
		assertTrue(valideChaineSimple(c3, 3, 2, 2));
		c3.maj(i -> i * 2);
		assertTrue(valideChaineSimple(c1, 4));
		assertTrue(valideChaineSimple(c2, 4, 4));
		assertTrue(valideChaineSimple(c3, 6, 4, 4));
	}

	@Test
	public void testMap() {	
		ChaineSimple<Character> cc = c1.map(i -> (char) (i.intValue() + 64));
		assertTrue(valideChaineSimple(c1, 1));
		assertTrue(valideChaineSimple(cc, 'A'));
		cc = c3.map(i -> (char) (i.intValue() + 64));
		assertTrue(valideChaineSimple(c1, 1));
		assertTrue(valideChaineSimple(c2, 2, 1));
		assertTrue(valideChaineSimple(c3, 3, 2, 1));
		assertTrue(valideChaineSimple(cc, 'C', 'B', 'A'));
	}

	@Test
	public void testInverse() {
		ChaineSimple<Integer> i1 = c1.inverse();
		ChaineSimple<Integer> i2 = c2.inverse();
		ChaineSimple<Integer> i3 = c3.inverse();
		assertTrue(valideChaineSimple(c1, 1));
		assertTrue(valideChaineSimple(c2, 2, 1));
		assertTrue(valideChaineSimple(c3, 3, 2, 1));
		assertTrue(valideChaineSimple(i1, 1));
		assertTrue(valideChaineSimple(i2, 1, 2));
		assertTrue(valideChaineSimple(i3, 1, 2, 3));
	}

}
