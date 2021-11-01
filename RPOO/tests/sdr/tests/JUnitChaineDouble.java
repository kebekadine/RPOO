package sdr.tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import sdr.ChaineDouble;

public class JUnitChaineDouble {

	// ------------------------ Données de test

	private ChaineDouble<Integer> c1;
	private ChaineDouble<Integer> c2;

	// ------------------------ Fonctions auxiliaires

	private static <E> ChaineDouble<E> valideChainage(ChaineDouble<E> cd) {
		ChaineDouble<E> c; // itérateur de cd
		boolean ko = false; // cas d'erreur
		for (c = cd; c.suivant != null && !ko; c = c.suivant)
			ko = c.suivant.precedent != c;
		for (c = cd; c.precedent != null && !ko; c = c.precedent)
			ko = c.precedent.suivant != c;
		if (ko) {
			System.out.println("[" + new Exception().getStackTrace()[2].getMethodName()
					+ "] chaînage incorrect autour de " + c.valeur);
			return null;
		}
		return c;
	}
		
	@SafeVarargs
	private static <E> boolean valideChaineDouble(ChaineDouble<E> cd, E... v) {
		if (cd != null) { // on repositionne cd sur son extrêmité gauche
			cd = valideChainage(cd);
			if (cd == null) // chaînage incorrect
				return false;
		}
		int iv;             // indice de parcours de v
		ChaineDouble<?> c;  // itérateur de cd
		String r = "";      // représentation littérale de cd
		boolean ko = false; // cas d'erreur
		for (c = cd, iv = 0; c != null; c = c.suivant, iv++) {
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
		c1 = new ChaineDouble<>(3);
		c2 = new ChaineDouble<>(3);
		c2.suivant = new ChaineDouble<>(4);
		c2.suivant.suivant = new ChaineDouble<>(5);
		c2.suivant.suivant.precedent = c2.suivant;
		c2.suivant.precedent = c2;
		c2.precedent = new ChaineDouble<>(2);
		c2.precedent.suivant = c2;
		c2.precedent.precedent = new ChaineDouble<>(1);
		c2.precedent.precedent.suivant = c2.precedent;
	}

	@Test
	public void testSetup() { // vérification de l'état des données de test
		assertTrue(valideChaineDouble(c1, 3));
		assertTrue(valideChaineDouble(c2, 1, 2, 3, 4, 5));
	}

	// ------------------------ Test des méthodes de ChaineDouble

	@Test
	public void tesToString() {
		assertEquals(c1.toString(), "([3])");
		assertEquals(c2.toString(), "(1, 2, [3], 4, 5)");
	}

	@Test
	public void testInsereElementSuivant() {
		ChaineDouble<Integer> sc = c1.insereElementSuivant(6);
		assertTrue(valideChaineDouble(c1, 3, 6));
		assertTrue(valideChaineDouble(sc, 3, 6));
		assertSame(c1.suivant, sc);
		sc = c1.insereElementSuivant(4);
		assertTrue(valideChaineDouble(c1, 3, 4, 6));
		assertTrue(valideChaineDouble(sc, 3, 4, 6));
		assertSame(c1.suivant, sc);
		sc.insereElementSuivant(5);
		assertTrue(valideChaineDouble(c1, 3, 4, 5, 6));
		assertTrue(valideChaineDouble(sc, 3, 4, 5, 6));
		assertSame(c1.suivant, sc);
	}

	@Test
	public void insereElementPrecedent() {
		ChaineDouble<Integer> pc = c1.insereElementPrecedent(0);
		assertTrue(valideChaineDouble(c1, 0, 3));
		assertTrue(valideChaineDouble(pc, 0, 3));
		assertSame(c1.precedent, pc);
		pc = c1.insereElementPrecedent(2);
		assertTrue(valideChaineDouble(c1, 0, 2, 3));
		assertTrue(valideChaineDouble(pc, 0, 2, 3));
		assertSame(c1.precedent, pc);
		pc.insereElementPrecedent(1);
		assertTrue(valideChaineDouble(c1, 0, 1, 2, 3));
		assertTrue(valideChaineDouble(pc, 0, 1, 2, 3));
		assertSame(c1.precedent, pc);
	}

	@Test
	public void testSupprimeElementSuivant() {
		ChaineDouble<Integer> sc = c2.supprimeElementSuivant();
		assertTrue(valideChaineDouble(c2, 1, 2, 3, 5));
		assertTrue(valideChaineDouble(sc, 4));
		sc = c2.supprimeElementSuivant();
		assertTrue(valideChaineDouble(c2, 1, 2, 3));
		assertTrue(valideChaineDouble(sc, 5));
		sc = c2.supprimeElementSuivant();
		assertTrue(valideChaineDouble(c2, 1, 2, 3));
		assertTrue(valideChaineDouble(sc));
	}

	@Test
	public void supprimeElementPrecedent() {
		ChaineDouble<Integer> pc = c2.supprimeElementPrecedent();
		assertTrue(valideChaineDouble(c2, 1, 3, 4, 5));
		assertTrue(valideChaineDouble(pc, 2));
		pc = c2.supprimeElementPrecedent();
		assertTrue(valideChaineDouble(c2, 3, 4, 5));
		assertTrue(valideChaineDouble(pc, 1));
		pc = c2.supprimeElementPrecedent();
		assertTrue(valideChaineDouble(c2, 3, 4, 5));
		assertTrue(valideChaineDouble(pc));
	}

	@Test
	public void testMaj() {
		c1.maj(i -> i + 1);
		assertTrue(valideChaineDouble(c1, 4));
		c2.maj(i -> i * 2);
		assertTrue(valideChaineDouble(c2, 2, 4, 6, 8, 10));
	}

	@Test
	public void testMap() {
		ChaineDouble<Character> cc = c1.map(i -> (char) (i.intValue() + 64));
		assertTrue(valideChaineDouble(cc, 'C'));
		cc = c2.map(i -> (char) (i.intValue() + 64));
		assertTrue(valideChaineDouble(cc, 'A', 'B', 'C', 'D', 'E'));
	}

	@Test
	public void testInverse() {
		c1.inverse();
		assertTrue(valideChaineDouble(c1, 3));
		c2.inverse();
		assertTrue(valideChaineDouble(c2, 5, 4, 3, 2, 1));
		c2.inverse();
		assertTrue(valideChaineDouble(c2, 1, 2, 3, 4, 5));
	}

}
