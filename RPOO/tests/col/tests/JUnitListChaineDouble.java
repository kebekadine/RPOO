package col.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import col.List;
import col.ListChaineDouble;
import sdr.correction.ChaineDouble;

// ------------------------ Classe dérivée dédiée aux tests

class ListChaineDoubleTEST<E> extends ListChaineDouble<E> {
	@SafeVarargs
	ListChaineDoubleTEST(E... val) {
		super();
		if (val.length > 0) {
			this.debut = this.fin = this.courant = new ChaineDouble<>(val[0]); 
			for (int i=1; i<val.length; i++)
				this.fin = this.fin.insereElementSuivant(val[i]);
			this.indiceCourant = 0;
			this.size = val.length;
		}
	}

	String etatListe() {
		String res = "debut : " + (this.debut == null ? "null" : "non null");
		res += " ; fin : " + (this.fin == null ? "null" : "non null");
		res += " ; courant : " + (this.courant == null ? "null" : "non null");
		res += " ; indice courant : " + this.indiceCourant;
		res += " ; size : " + this.size;
		return res;
	}
	
	ChaineDouble<E> getDebut() {
		return this.debut;
	}
	
	ChaineDouble<E> getCourant() {
		return this.courant;
	}
	
	ChaineDouble<E> getFin() {
		return this.fin;
	}
	
	int getIndiceCourant() {
		return this.indiceCourant;
	}
	
	int getSize() {
		return this.size;
	}
	
	protected void positionne(int i) {
		super.positionne(i);
	}
}

// ------------------------ Classe de tests

public class JUnitListChaineDouble {
	
	// ------------------------ Données de test

	protected ListChaineDoubleTEST<Integer> l1, l2, l3, l4;

	// ------------------------ Fonctions auxiliaires
		
	private static boolean ko(String m) {
		System.out.println("[" + new Exception().getStackTrace()[2].getMethodName() + "] " + m);
		return false;
	}
	
	@SafeVarargs
	private static <E> boolean valideListChaineDouble(ListChaineDoubleTEST<E> l, E... v) {
		ChaineDouble<E> deb = l.getDebut();
		ChaineDouble<E> fin = l.getFin();
		ChaineDouble<E> crt = l.getCourant();
		int indc = l.getIndiceCourant();
		int size = l.getSize();
		// On traite le cas de la liste vide :
		if (deb == null || fin == null || crt == null || indc == -1 || size == 0) { // liste supposée vide
			if (deb != null || fin != null || crt != null || indc != -1 || size != 0)
				return ko("attributs incohérents : " + l.etatListe());
			// ici : la liste est vide et cohérente
			return true;
		}
		// On vérifie les extrêmités :
		if (deb.precedent != null) 
			return ko("l'attribut debut n'est pas le début de la chaîne");
		if (fin.suivant != null) 
			return ko("l'attribut fin n'est pas la fin de la chaîne");
		// On vérifie la cohérence du chaînage et des références de chaînons :
		int iv; // indice de parcours de v
		int indCourantCalcul = -1; // indice calculé de courant
		String r = ""; // représentation littérale de l
		boolean courantTrouve = false; // detection de courant pendant le parcours
		boolean ko = false; // cas d'erreur d'ordre des valeurs
		ChaineDouble<?> c, vraieFin = null;
		for (c = deb, iv = 0; c!=null; vraieFin = c, c = c.suivant, iv++) {
			if (c.suivant!=null && c.suivant.precedent!=c) 
				return ko("chaînage incorrect entre " + c.valeur + " et " + c.suivant.valeur);
			r += " " + c.valeur;
			ko = ko || c.valeur == null || iv == v.length || !c.valeur.equals(v[iv]);
			if (!courantTrouve) {
				indCourantCalcul++;
				if (c == crt)
					courantTrouve = true;
			}		
		}
		if (ko || iv != v.length) 
			return ko("chaîne : (" + r + " ) ; valeurs attendues : " + Arrays.toString(v));
		if (vraieFin != fin)
			return ko("le chaînon de fin n'est pas accessible depuis le début de la chaîne");
		if (!courantTrouve)
			return ko("le chaînon courant n'est pas accessible depuis le début de la chaîne");
		if (indCourantCalcul != indc)
			return ko("l'indice courant ("+indc+") ne correspond pas l'indice réel du chaînon courant ("+indCourantCalcul+")");
		return true;
	}
	
	// ------------------------ Setup et validation des données de test

	@Before
	public void setup() {
		this.l1 = new ListChaineDoubleTEST<>();
		this.l2 = new ListChaineDoubleTEST<>(1);
		this.l3 = new ListChaineDoubleTEST<>(1, 2);
		this.l4 = new ListChaineDoubleTEST<>(5, 7, 3, 3, 8, 5, 2, 1);
	}

	@Test
	public void testSetup() { // vérification de l'état des données de test
		assertTrue(valideListChaineDouble(this.l1));
		assertTrue(valideListChaineDouble(this.l2, 1));
		assertTrue(valideListChaineDouble(this.l3, 1, 2));
		assertTrue(valideListChaineDouble(this.l4, 5, 7, 3, 3, 8, 5, 2, 1));
	}

	// ------------------------ Test des méthodes de Collection

	@Test
	public void testSize() {
		assertEquals(this.l1.size(), 0);
		assertEquals(this.l2.size(), 1);
		assertEquals(this.l3.size(), 2);
		assertEquals(this.l4.size(), 8);
	}

	@Test
	public void testIsEmpty() {
		assertTrue(this.l1.isEmpty());
		assertFalse(this.l2.isEmpty());
		assertFalse(this.l3.isEmpty());
		assertFalse(this.l4.isEmpty());
	}

	@Test
	public void testClear() {
		this.l1.clear();
		assertTrue(valideListChaineDouble(this.l1));
		this.l2.clear();
		assertTrue(valideListChaineDouble(this.l2));
		this.l3.clear();
		assertTrue(valideListChaineDouble(this.l3));
		this.l4.clear();
		assertTrue(valideListChaineDouble(this.l4));
	}

	@Test
	public void testAdd() {
		assertTrue(this.l1.add(5));
		assertTrue(valideListChaineDouble(this.l1, 5));
		assertTrue(this.l3.add(3));
		assertTrue(valideListChaineDouble(this.l3, 1, 2, 3));
	}

	@Test
	public void testRemoveObject() {
		assertFalse(this.l1.remove((Integer)5));
		assertTrue(valideListChaineDouble(this.l1));
		assertFalse(this.l2.remove((Integer)5));
		assertTrue(valideListChaineDouble(this.l2, 1));
		assertTrue(this.l2.remove((Integer)1));
		assertTrue(valideListChaineDouble(this.l2));
		assertFalse(this.l4.remove((Integer)4));
		assertTrue(valideListChaineDouble(this.l4, 5, 7, 3, 3, 8, 5, 2, 1));
		assertTrue(this.l4.remove((Integer)5));
		assertTrue(valideListChaineDouble(this.l4, 7, 3, 3, 8, 5, 2, 1));
		assertTrue(this.l4.remove((Integer)5));
		assertTrue(valideListChaineDouble(this.l4, 7, 3, 3, 8, 2, 1));
		assertFalse(this.l4.remove((Integer)5));
		assertTrue(valideListChaineDouble(this.l4, 7, 3, 3, 8, 2, 1));
	}

	@Test
	public void testContains() {
		assertFalse(this.l1.contains(5));
		assertFalse(this.l2.contains(5));
		assertTrue(this.l2.contains(1));
		assertFalse(this.l4.contains(4));
		assertTrue(this.l4.contains(5));
		assertTrue(this.l4.contains(3));
	}

	@Test
	public void testToArray() {
		Integer[] t1 = { 4 };
		Integer[] t2 = { 10, 11, 12, 13, 14, 15 };
		assertArrayEquals(this.l3.toArray(t2), new Number[] { 1, 2, null, null, null, null });
		assertArrayEquals(this.l3.toArray(t1), new Number[] { 1, 2 });
		assertArrayEquals(t2, new Number[] { 1, 2, null, null, null, null });
		assertArrayEquals(t1, new Number[] { 4 });
		assertArrayEquals(this.l2.toArray(t2), new Number[] { 1, null, null, null, null, null });
		assertArrayEquals(this.l2.toArray(t1), new Number[] { 1 });
		assertArrayEquals(t2, new Number[] { 1, null, null, null, null, null });
		assertArrayEquals(t1, new Number[] { 1 });
		assertArrayEquals(this.l1.toArray(t2), new Number[] { null, null, null, null, null, null });
		assertArrayEquals(this.l1.toArray(t1), new Number[] { null });
		assertArrayEquals(t2, new Number[] { null, null, null, null, null, null });
		assertArrayEquals(t1, new Number[] { null });
	}

	// ------------------------ Test des méthodes de List
	
	@Test
	public void testGet() {
		assertSame(this.l2.get(0), 1);
		assertTrue(valideListChaineDouble(this.l2, 1));
		assertSame(this.l4.get(0), 5);
		assertTrue(valideListChaineDouble(this.l4, 5, 7, 3, 3, 8, 5, 2, 1));
		assertSame(this.l4.get(3), 3);
		assertTrue(valideListChaineDouble(this.l4, 5, 7, 3, 3, 8, 5, 2, 1));
		assertSame(this.l4.get(7), 1);
		assertTrue(valideListChaineDouble(this.l4, 5, 7, 3, 3, 8, 5, 2, 1));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetEx1() {
		this.l1.get(0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetEx2() {
		this.l4.get(8);
	}

	@Test
	public void testSet() {
		assertSame(this.l2.set(0, -18), 1);
		assertTrue(valideListChaineDouble(this.l2, -18));
		assertSame(this.l4.set(0, -2), 5);
		assertTrue(valideListChaineDouble(this.l4, -2, 7, 3, 3, 8, 5, 2, 1));
		assertSame(this.l4.set(3, 99), 3);
		assertTrue(valideListChaineDouble(this.l4, -2, 7, 3, 99, 8, 5, 2, 1));
		assertSame(this.l4.set(7, 34), 1);
		assertTrue(valideListChaineDouble(this.l4, -2, 7, 3, 99, 8, 5, 2, 34));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetEx1() {
		this.l1.set(0, 2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetEx2() {
		this.l4.set(8, 5);
	}
	
	@Test
	public void testAddIndice() {
		this.l1.add(0, 5);
		assertTrue(valideListChaineDouble(this.l1, 5));
		this.l3.add(0, -1);
		assertTrue(valideListChaineDouble(this.l3, -1, 1, 2));
		this.l3.add(2, -2);
		assertTrue(valideListChaineDouble(this.l3, -1, 1, -2, 2));
		this.l3.add(4, -3);
		assertTrue(valideListChaineDouble(this.l3, -1, 1, -2, 2, -3));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddIndiceEx1() {
		this.l1.add(-1, 2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddIndiceEx2() {
		this.l4.add(9, 5);
	}

	@Test
	public void testAddAll() {
		assertTrue(this.l4.addAll(this.l3));
		assertTrue(valideListChaineDouble(this.l4, 5, 7, 3, 3, 8, 5, 2, 1, 1, 2));
	}

	@Test
	public void testAddAllIndice() {
		assertTrue(this.l4.addAll(8, this.l3));
		assertTrue(valideListChaineDouble(this.l4, 5, 7, 3, 3, 8, 5, 2, 1, 1, 2));
		assertTrue(this.l4.addAll(0, this.l2));
		assertTrue(valideListChaineDouble(this.l4, 1, 5, 7, 3, 3, 8, 5, 2, 1, 1, 2));
		assertTrue(this.l3.addAll(1, this.l4));
		assertTrue(valideListChaineDouble(this.l3, 1, 1, 5, 7, 3, 3, 8, 5, 2, 1, 1, 2, 2));
	}

	@Test
	public void testRemove1() {
		assertSame(this.l2.remove(0), 1);
		assertTrue(valideListChaineDouble(this.l2));
		assertSame(this.l4.remove(0), 5);
		assertTrue(valideListChaineDouble(this.l4, 7, 3, 3, 8, 5, 2, 1));
		assertSame(this.l4.remove(3), 8);
		assertTrue(valideListChaineDouble(this.l4, 7, 3, 3, 5, 2, 1));
		assertSame(this.l4.remove(5), 1);
		assertTrue(valideListChaineDouble(this.l4, 7, 3, 3, 5, 2));
	}

	@Test
	public void testRemove2() {
		assertSame(this.l4.remove(7), 1);
		assertSame(this.l4.remove(0), 5);
		assertSame(this.l4.remove(3), 8);
		assertTrue(valideListChaineDouble(this.l4, 7, 3, 3, 5, 2));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveEx1() {
		this.l1.remove(0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveEx2() {
		this.l4.remove(8);
	}

	@Test
	public void testIndexOf() {
		assertEquals(this.l1.indexOf(5), -1);
		assertEquals(this.l3.indexOf(5), -1);
		assertEquals(this.l3.indexOf(1), 0);
		assertEquals(this.l3.indexOf(2), 1);
		assertEquals(this.l4.indexOf(5), 0);
		assertEquals(this.l4.indexOf(7), 1);
		assertEquals(this.l4.indexOf(3), 2);
		assertEquals(this.l4.indexOf(8), 4);
		assertEquals(this.l4.indexOf(1), 7);
		assertEquals(this.l4.indexOf(9), -1);
	}

	@Test
	public void testLastIndexOf() {
		assertEquals(this.l1.lastIndexOf(5), -1);
		assertEquals(this.l3.lastIndexOf(5), -1);
		assertEquals(this.l3.lastIndexOf(1), 0);
		assertEquals(this.l3.lastIndexOf(2), 1);
		assertEquals(this.l4.lastIndexOf(5), 5);
		assertEquals(this.l4.lastIndexOf(7), 1);
		assertEquals(this.l4.lastIndexOf(3), 3);
		assertEquals(this.l4.lastIndexOf(8), 4);
		assertEquals(this.l4.lastIndexOf(1), 7);
		assertEquals(this.l4.lastIndexOf(9), -1);
	}

	// ------------------------ Test des méthodes de ListChaineDouble

	@Test
	public void tesToString() {
		assertEquals(this.l1.toString(), "( )");
		assertEquals(this.l2.toString(), "( 1 )");
		assertEquals(this.l3.toString(), "( 1 2 )");
		assertEquals(this.l4.toString(), "( 5 7 3 3 8 5 2 1 )");
	}

	@Test
	public void testEquals() {
		List<Integer> c = new ListChaineDoubleTEST<>();
		assertEquals(this.l1, c);
		assertNotEquals(this.l2, c);
		assertNotEquals(this.l3, c);
		assertNotEquals(this.l4, c);
		c = new ListChaineDoubleTEST<>(1);
		assertNotEquals(this.l1, c);
		assertEquals(this.l2, c);
		assertNotEquals(this.l3, c);
		assertNotEquals(this.l4, c);
		c = new ListChaineDoubleTEST<>(2, 1);
		assertNotEquals(this.l1, c);
		assertNotEquals(this.l2, c);
		assertNotEquals(this.l3, c);
		assertNotEquals(this.l4, c);
		c = new ListChaineDoubleTEST<>(1, 2);
		assertNotEquals(this.l1, c);
		assertNotEquals(this.l2, c);
		assertEquals(this.l3, c);
		assertNotEquals(this.l4, c);
		c = new ListChaineDoubleTEST<>(5, 7, 3, 3, 8, 5, 2, 1);
		assertNotEquals(this.l1, c);
		assertNotEquals(this.l2, c);
		assertNotEquals(this.l3, c);
		assertEquals(this.l4, c);
		c = new ListChaineDoubleTEST<>(5, 7, 3, 8, 3, 5, 2, 1);
		assertNotEquals(this.l1, c);
		assertNotEquals(this.l2, c);
		assertNotEquals(this.l3, c);
		assertNotEquals(this.l4, c);
	}
	
	@Test
	public void testPositionne() {
		this.l4.positionne(1);
		assertSame(this.l4.getIndiceCourant(), 1);
		assertTrue(valideListChaineDouble(this.l4, 5, 7, 3, 3, 8, 5, 2, 1));
		this.l4.positionne(5);
		assertSame(this.l4.getIndiceCourant(), 5);
		assertTrue(valideListChaineDouble(this.l4, 5, 7, 3, 3, 8, 5, 2, 1));
		this.l4.positionne(0);
		assertSame(this.l4.getIndiceCourant(), 0);
		assertTrue(valideListChaineDouble(this.l4, 5, 7, 3, 3, 8, 5, 2, 1));
		this.l4.positionne(7);
		assertSame(this.l4.getIndiceCourant(), 7);
		assertTrue(valideListChaineDouble(this.l4, 5, 7, 3, 3, 8, 5, 2, 1));
		this.l4.positionne(5);
		assertSame(this.l4.getIndiceCourant(), 5);
		assertTrue(valideListChaineDouble(this.l4, 5, 7, 3, 3, 8, 5, 2, 1));
	}
	
}
