package sdr.tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import sdr.ArbreBinaire;
import sdr.ChaineSimple;

public class JUnitArbreBinaire {

	// ------------------------ Données de test

	private ArbreBinaire<Integer> a1;
	private ArbreBinaire<Integer> a2;
	private ArbreBinaire<Integer> a3;
	private ArbreBinaire<Integer> a4;

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

	private static Object[] valideChainage(ArbreBinaire<?> a) {
		if (a == null)
			return new Object[0];
		for (; a.pere != null; a = a.pere)
			if (a.pere.gauche != a && a.pere.droit != a) {
				System.out.println("[" + new Exception().getStackTrace()[2].getMethodName() + "] chaînage incorrect de "
						+ a.valeur + " à son père");
				return null;
			}
		return valideChainageDescendants(a);
	}
	
	private static Object[] valideChainageDescendants(ArbreBinaire<?> a) {
		if (a == null)
			return new Object[0];
		if ((a.gauche != null && a.gauche.pere != a) || (a.droit != null && a.droit.pere != a)) {
			System.out.println("[" + new Exception().getStackTrace()[3].getMethodName() + "] chaînage incorrect de "
					+ a.valeur + " à ses enfants");
			return null;
		}
		int hg = (a.gauche == null ? 0 : 1 + a.gauche.hauteur);
		int hd = (a.droit == null ? 0 : 1 + a.droit.hauteur);
		int ha = (hg > hd ? hg : hd);
		if (a.hauteur != ha) {
			System.out.println("[" + new Exception().getStackTrace()[3].getMethodName() + "] hauteur de " + a.valeur
					+ " incorrecte : " + a.hauteur);
			return null;
		}
		Object[] tg = valideChainageDescendants(a.gauche);
		if (tg == null)
			return null;
		Object[] td = valideChainageDescendants(a.droit);
		if (td == null)
			return null;
		Object[] res = Arrays.copyOf(tg, tg.length + td.length + 1);
		res[tg.length] = a.valeur;
		System.arraycopy(td, 0, res, tg.length + 1, td.length);
		return res;
	}

	@SuppressWarnings("unchecked")
	private static <E extends Comparable<E>> boolean valideArbreBinaire(ArbreBinaire<E> a, E... v) {		
		Object[] t = valideChainage(a);
		if (t == null)
			return false;
		if (!Arrays.equals(t, v)) {
			System.out.println(
					"[" + new Exception().getStackTrace()[1].getMethodName() + "] parcours infixe de l'arbre : "
							+ Arrays.toString(t) + " ; valeurs attendues : " + Arrays.toString(v));
			return false;
		}
		return true;
	}

	// ------------------------ Setup et validation des données de test

	@Before
	public void setup() {
		// a1
		a1 = new ArbreBinaire<>(4);
		// a2
		a2 = new ArbreBinaire<>(4);
		a2.droit = new ArbreBinaire<>(6);
		a2.droit.pere = a2;
		a2.droit.droit = new ArbreBinaire<>(7);
		a2.droit.droit.pere = a2.droit;
		a2.hauteur = 2;
		a2.droit.hauteur = 1;
		// a3
		a3 = new ArbreBinaire<>(4);
		a3.gauche = new ArbreBinaire<>(2);
		a3.gauche.pere = a3;
		a3.gauche.gauche = new ArbreBinaire<>(1);
		a3.gauche.gauche.pere = a3.gauche;
		a3.hauteur = 2;
		a3.gauche.hauteur = 1;
		// a4
		a4 = new ArbreBinaire<>(4);
		a4.gauche = new ArbreBinaire<>(2);
		a4.gauche.pere = a4;
		a4.gauche.gauche = new ArbreBinaire<>(1);
		a4.gauche.gauche.pere = a4.gauche;
		a4.gauche.droit = new ArbreBinaire<>(3);
		a4.gauche.droit.pere = a4.gauche;
		a4.droit = new ArbreBinaire<>(6);
		a4.droit.pere = a4;
		a4.droit.gauche = new ArbreBinaire<>(5);
		a4.droit.gauche.pere = a4.droit;
		a4.droit.droit = new ArbreBinaire<>(7);
		a4.droit.droit.pere = a4.droit;
		a4.hauteur = 2;
		a4.gauche.hauteur = 1;
		a4.droit.hauteur = 1;
	}

	@Test
	public void testSetup() { // vérification de l'état des données de test
		assertTrue(valideArbreBinaire(a1, 4));
		assertNull(a1.pere);
		assertNull(a1.gauche);
		assertNull(a1.droit);
		assertTrue(a1 != null && a1.valeur == 4);
		assertSame(a1.hauteur, 0);
		assertTrue(valideArbreBinaire(a2, 4, 6, 7));
		assertTrue(valideArbreBinaire(a3, 1, 2, 4));
		assertTrue(valideArbreBinaire(a4, 1, 2, 3, 4, 5, 6, 7));
	}

	// ------------------------ Test des méthodes de ArbreBinaire

	@Test
	public void testFeuille() {
		assertTrue(a1.feuille());
		assertFalse(a2.feuille());
		assertFalse(a3.feuille());
		assertFalse(a4.feuille());
	}

	@Test
	public void testFacteurEquilibrage() {
		assertSame(a1.facteurEquilibrage(), 0);
		assertSame(a2.facteurEquilibrage(), 2);
		assertSame(a2.droit.facteurEquilibrage(), 1);
		assertSame(a2.droit.droit.facteurEquilibrage(), 0);
		assertSame(a3.facteurEquilibrage(), -2);
		assertSame(a3.gauche.facteurEquilibrage(), -1);
		assertSame(a3.gauche.gauche.facteurEquilibrage(), 0);
		assertSame(a4.facteurEquilibrage(), 0);
		assertSame(a4.gauche.facteurEquilibrage(), 0);
		assertSame(a4.droit.facteurEquilibrage(), 0);
		assertSame(a4.gauche.gauche.facteurEquilibrage(), 0);
		assertSame(a4.gauche.droit.facteurEquilibrage(), 0);
		assertSame(a4.droit.gauche.facteurEquilibrage(), 0);
		assertSame(a4.droit.droit.facteurEquilibrage(), 0);
	}

	@Test
	public void testMajHauteur() {
		a4.hauteur = -1;
		a4.gauche.hauteur = -1;
		a4.gauche.droit.hauteur = -1;
		a4.gauche.droit.majHauteur();
		assertTrue(valideArbreBinaire(a4, 1, 2, 3, 4, 5, 6, 7));
	}

	@Test
	public void testInsereElementGauche() {
		ArbreBinaire<Integer> r = a1.insereElementGauche(2, true);
		assertTrue(r != null && r.valeur == 2);
		assertTrue(valideArbreBinaire(a1, 2, 4));
		assertTrue(valideArbreBinaire(r, 2, 4));
		r = a1.insereElementGauche(3, true);
		assertTrue(r != null && r.valeur == 3);
		assertTrue(valideArbreBinaire(a1, 2, 3, 4));
		assertTrue(valideArbreBinaire(r, 2, 3, 4));
		r = a1.insereElementGauche(1, false);
		assertTrue(r != null && r.valeur == 1);
		assertTrue(valideArbreBinaire(a1, 1, 2, 3, 4));
		assertTrue(valideArbreBinaire(r, 1, 2, 3, 4));
	}

	@Test
	public void testInsereElementDroit() {
		ArbreBinaire<Integer> r = a1.insereElementDroit(6, true);
		assertTrue(r != null && r.valeur == 6);
		assertTrue(valideArbreBinaire(a1, 4, 6));
		assertTrue(valideArbreBinaire(r, 4, 6));
		r = a1.insereElementDroit(5, true);
		assertTrue(r != null && r.valeur == 5);
		assertTrue(valideArbreBinaire(a1, 4, 5, 6));
		assertTrue(valideArbreBinaire(r, 4, 5, 6));
		r = a1.insereElementDroit(7, false);
		assertTrue(r != null && r.valeur == 7);
		assertTrue(valideArbreBinaire(a1, 4, 5, 6, 7));
		assertTrue(valideArbreBinaire(r, 4, 5, 6, 7));
	}

	@Test
	public void testInsereElementPere() {
		ArbreBinaire<Integer> r = a1.insereElementPere(6, true);
		assertTrue(r != null && r.valeur == 6);
		assertTrue(valideArbreBinaire(a1, 4, 6));
		assertTrue(valideArbreBinaire(r, 4, 6));
		r = a1.insereElementPere(5, true);
		assertTrue(r != null && r.valeur == 5);
		assertTrue(valideArbreBinaire(a1, 4, 5, 6));
		assertTrue(valideArbreBinaire(r, 4, 5, 6));
		r = a1.insereElementPere(3, false);
		assertTrue(r != null && r.valeur == 3);
		assertTrue(valideArbreBinaire(a1, 3, 4, 5, 6));
		assertTrue(valideArbreBinaire(r, 3, 4, 5, 6));
	}

	@Test
	public void testSupprimeElementGauche() {
		ArbreBinaire<Integer> r = a1.supprimeElementGauche();
		assertNull(r);
		assertTrue(valideArbreBinaire(a1, 4));
		r = a4.supprimeElementGauche();
		assertNull(r);
		assertTrue(valideArbreBinaire(a4, 1, 2, 3, 4, 5, 6, 7));
		a4.gauche.droit = null;
		r = a4.supprimeElementGauche();
		assertTrue(valideArbreBinaire(r, 2));
		assertTrue(valideArbreBinaire(a4, 1, 4, 5, 6, 7));
		r = a4.droit.supprimeElementGauche();
		assertTrue(valideArbreBinaire(r, 5));
		assertTrue(valideArbreBinaire(a4, 1, 4, 6, 7));
	}

	@Test
	public void testSupprimeElementDroit() {
		ArbreBinaire<Integer> r = a1.supprimeElementDroit();
		assertNull(r);
		assertTrue(valideArbreBinaire(a1, 4));
		r = a4.supprimeElementDroit();
		assertNull(r);
		assertTrue(valideArbreBinaire(a4, 1, 2, 3, 4, 5, 6, 7));
		a4.droit.gauche = null;
		r = a4.supprimeElementDroit();
		assertTrue(valideArbreBinaire(r, 6));
		assertTrue(valideArbreBinaire(a4, 1, 2, 3, 4, 7));
		r = a4.gauche.supprimeElementDroit();
		assertTrue(valideArbreBinaire(r, 3));
		assertTrue(valideArbreBinaire(a4, 1, 2, 4, 7));
	}

	@Test
	public void testSupprimeElementPere() {
		ArbreBinaire<Integer> r = a4.supprimeElementPere();
		assertNull(r);
		assertTrue(valideArbreBinaire(a4, 1, 2, 3, 4, 5, 6, 7));
		r = a4.droit.gauche.supprimeElementPere();
		assertNull(r);
		assertTrue(valideArbreBinaire(a4, 1, 2, 3, 4, 5, 6, 7));
		a4.droit.droit = null;
		r = a4.droit.gauche.supprimeElementPere();
		assertTrue(valideArbreBinaire(r, 6));
		assertTrue(valideArbreBinaire(a4, 1, 2, 3, 4, 5));
	}

	@Test
	public void testPrefixe() {
		assertTrue(valideChaineSimple(a1.prefixe(), 4));
		assertTrue(valideChaineSimple(a4.prefixe(), 4, 2, 1, 3, 6, 5, 7));
	}

	@Test
	public void testInfixe() {
		assertTrue(valideChaineSimple(a1.infixe(), 4));
		assertTrue(valideChaineSimple(a4.infixe(), 1, 2, 3, 4, 5, 6, 7));
	}

	@Test
	public void testPostfixe() {
		assertTrue(valideChaineSimple(a1.postfixe(), 4));
		assertTrue(valideChaineSimple(a4.postfixe(), 1, 3, 2, 5, 7, 6, 4));
	}

	@Test
	public void testLargeur() {
		assertTrue(valideChaineSimple(a1.largeur(), 4));
		assertTrue(valideChaineSimple(a4.largeur(), 4, 2, 6, 1, 3, 5, 7));
	}

	@Test
	public void testGeneration() {
		assertNull(a4.generation(-1));
		assertTrue(valideChaineSimple(a4.generation(0), 4));
		assertTrue(valideChaineSimple(a4.generation(1), 2, 6));
		assertTrue(valideChaineSimple(a4.generation(2), 1, 3, 5, 7));
		assertNull(a4.generation(3));
	}

	@Test
	public void testMinimum() {
		assertTrue(a1 != null && new Integer(4).equals(a1.minimum()));
		assertTrue(a4 != null && new Integer(1).equals(a4.minimum()));
	}

	@Test
	public void testMaximum() {
		assertTrue(a1 != null && new Integer(4).equals(a1.maximum()));
		assertTrue(a4 != null && new Integer(7).equals(a4.maximum()));
	}

	@Test
	public void testContient() {
		assertTrue(a1.contient(4));
		assertFalse(a1.contient(2));
		assertFalse(a1.contient(6));
		assertTrue(a4.contient(4));
		assertTrue(a4.contient(2));
		assertTrue(a4.contient(6));
		assertTrue(a4.contient(3));
		assertTrue(a4.contient(5));
		assertFalse(a4.contient(0));
		assertFalse(a4.contient(8));
	}

	@Test
	public void testExtraire() {
		assertTrue(valideChaineSimple(a1.extraire(0, 4)));
		assertTrue(valideChaineSimple(a1.extraire(0, 8), 4));
		assertTrue(valideChaineSimple(a1.extraire(5, 8)));
		assertTrue(valideChaineSimple(a4.extraire(0, 4), 1, 2, 3));
		assertTrue(valideChaineSimple(a4.extraire(0, 8), 1, 2, 3, 4, 5, 6, 7));
		assertTrue(valideChaineSimple(a4.extraire(5, 8), 5, 6, 7));
		assertTrue(valideChaineSimple(a4.extraire(3, 7), 3, 4, 5, 6));
	}

	@Test
	public void testAjoute() {
		assertFalse(a1.ajoute(4));
		assertTrue(valideArbreBinaire(a1, 4));
		assertTrue(a1.ajoute(2));
		assertTrue(a1.ajoute(6));
		assertTrue(valideArbreBinaire(a1, 2, 4, 6));
		assertFalse(a4.ajoute(4));
		assertFalse(a4.ajoute(2));
		assertFalse(a4.ajoute(6));
		assertTrue(valideArbreBinaire(a4, 1, 2, 3, 4, 5, 6, 7));
		assertTrue(a4.ajoute(-1));
		assertTrue(a4.ajoute(0));
		assertTrue(a4.ajoute(8));
		assertTrue(valideArbreBinaire(a4, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8));
	}

	@Test(expected = IllegalStateException.class)
	public void testSupprimeEchec() {
		a1.supprime(1);
	}

	@Test
	public void testSupprime() {
		assertFalse(a4.supprime(-1));
		assertFalse(a4.supprime(0));
		assertFalse(a4.supprime(8));
		assertTrue(valideArbreBinaire(a4, 1, 2, 3, 4, 5, 6, 7));
		assertTrue(a4.supprime(4));
		assertTrue(a4.supprime(2));
		assertTrue(a4.supprime(6));
		assertTrue(valideArbreBinaire(a4, 1, 3, 5, 7));
	}

	@Test
	public void testRotationGauche() {
		a2.rotationGauche();
		assertTrue(a2 != null && a2.valeur == 6);
		assertTrue(valideArbreBinaire(a2, 4, 6, 7));
	}

	@Test
	public void testRotationDroite() {
		a3.rotationDroite();
		assertTrue(a3 != null && a3.valeur == 2);
		assertTrue(valideArbreBinaire(a3, 1, 2, 4));
	}

	@Test
	public void testDoubleRotationGauche() {
		a4.doubleRotationGauche();
		assertTrue(a4 != null && a4.valeur == 5);
		assertTrue(a4.gauche != null && a4.gauche.valeur == 4);
		assertTrue(a4.droit != null && a4.droit.valeur == 6);
		assertTrue(valideArbreBinaire(a4, 1, 2, 3, 4, 5, 6, 7));
	}

	@Test
	public void testDoubleRotationDroite() {
		a4.doubleRotationDroite();
		assertTrue(a4 != null && a4.valeur == 3);
		assertTrue(a4.gauche != null && a4.gauche.valeur == 2);
		assertTrue(a4.droit != null && a4.droit.valeur == 4);
		assertTrue(valideArbreBinaire(a4, 1, 2, 3, 4, 5, 6, 7));
	}

	@Test
	public void testReequilibre() {
		a2.droit.droit.reequilibre();
		assertTrue(a2 != null && a2.valeur == 6);
		assertTrue(valideArbreBinaire(a2, 4, 6, 7));
		a3.gauche.gauche.reequilibre();
		assertTrue(a3 != null && a3.valeur == 2);
		assertTrue(valideArbreBinaire(a3, 1, 2, 4));
	}

}
