package col.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import col.Iterator;
import col.Set;
import col.SortedSetChaineSimple;
import col.SortedSet;
import sdr.correction.ChaineSimple;

//------------------------ Classe dérivée dédiée aux tests

class SortedSetChaineSimpleTEST<E extends Comparable<E>> extends SortedSetChaineSimple<E> {
	@SafeVarargs
	SortedSetChaineSimpleTEST(E... val) {
		super();
		if (val.length > 0) {
			ChaineSimple<E> c = this.debut = new ChaineSimple<>(val[0]);
			for (int i=1; i<val.length; i++)
				c = c.insereElementSuivant(val[i]);
			this.size = val.length;
		}
	}

	ChaineSimple<E> getDebut() {
		return this.debut;
	}
	
	int getSize() {
		return this.size;
	}
}

//------------------------ Classe de tests

public class JUnitSortedSetChaineSimple {

	// ------------------------ Données de test

	protected SortedSetChaineSimpleTEST<Integer> s1, s2, s3, s4;

	// ------------------------ Fonctions auxiliaires

	private static boolean ko(String m) {
		System.out.println("[" + new Exception().getStackTrace()[2].getMethodName() + "] " + m);
		return false;
	}
	
	@SafeVarargs
	private static <E extends Comparable<E>> boolean valideSetChaineSimple(SortedSetChaineSimpleTEST<E> s, E... v) {
		ChaineSimple<E> deb = s.getDebut();
		int size = s.getSize();
		// On traite le cas de l'ensemble vide :
		if (deb == null || size == 0) { // ensemble supposé vide
			if (deb != null || size != 0)
				return ko("attributs incohérents : size : " + size + " ; debut : " + deb);
			// ici : l'ensemble est vide et cohérent
			return true;
		}
		// On vérifie les valeurs :
		int nbc = 0; // nombre de chaînons
		String r = ""; // représentation littérale de s
		boolean ko = false; // cas d'inégalité des ensembles de valeurs
		for (ChaineSimple<E> c = deb; c != null; c = c.suivant, nbc++) {
			E val = c.valeur;
			r += " " + val;
			ko = ko || val == null || !Arrays.stream(v).anyMatch(val::equals);
			for (ChaineSimple<E> d = deb; !ko && d != c; d = d.suivant)
				if (val.equals(d.valeur))
					return ko("valeur en double : " + val);
		}
		if (ko || nbc != v.length)
			return ko("chaîne : (" + r + " ) ; valeurs attendues : " + Arrays.toString(v));
		return true;
	}
	
	// ------------------------ Setup et validation des données de test

	@Before
	public void setup() {
		this.s1 = new SortedSetChaineSimpleTEST<>();
		this.s2 = new SortedSetChaineSimpleTEST<>(1);
		this.s3 = new SortedSetChaineSimpleTEST<>(1,2);
		this.s4 = new SortedSetChaineSimpleTEST<>(5,7,8,2,1);
	}

	@Test
	public void testSetup() { // vérification de l'état des données de test
		assertTrue(valideSetChaineSimple(this.s1));
		assertTrue(valideSetChaineSimple(this.s2, 1));
		assertTrue(valideSetChaineSimple(this.s3, 1, 2));
		assertTrue(valideSetChaineSimple(this.s4, 5, 7, 8, 2, 1));
	}

	// ------------------------ Test des méthodes de Collection

	@Test
	public void testSize() {
		assertEquals(this.s1.size(), 0);
		assertEquals(this.s2.size(), 1);
		assertEquals(this.s3.size(), 2);
		assertEquals(this.s4.size(), 5);
	}

	@Test
	public void testIsEmpty() {
		assertTrue(this.s1.isEmpty());
		assertFalse(this.s2.isEmpty());
		assertFalse(this.s3.isEmpty());
		assertFalse(this.s4.isEmpty());
	}

	@Test
	public void testClear() {
		this.s1.clear();
		assertTrue(valideSetChaineSimple(this.s1));
		this.s2.clear();
		assertTrue(valideSetChaineSimple(this.s2));
		this.s3.clear();
		assertTrue(valideSetChaineSimple(this.s3));
		this.s4.clear();
		assertTrue(valideSetChaineSimple(this.s4));
	}

	@Test
	public void testAdd() {
		assertTrue(this.s1.add(1));
		assertTrue(valideSetChaineSimple(this.s1, 1));
		assertFalse(this.s1.add(1));
		assertTrue(valideSetChaineSimple(this.s1, 1));
		assertTrue(this.s1.add(2));
		assertTrue(valideSetChaineSimple(this.s1, 1, 2));
		assertFalse(this.s1.add(2));
		assertTrue(valideSetChaineSimple(this.s1, 1, 2));
		assertFalse(this.s4.add(8));
		assertTrue(valideSetChaineSimple(this.s4, 5, 7, 8, 2, 1));
		assertTrue(this.s4.add(9));
		assertTrue(valideSetChaineSimple(this.s4, 5, 7, 8, 2, 1, 9));
		assertFalse(this.s4.add(9));
		assertTrue(valideSetChaineSimple(this.s4, 5, 7, 8, 2, 1, 9));
	}

	@Test
	public void testRemove() {
		assertFalse(this.s3.remove(3));
		assertTrue(valideSetChaineSimple(this.s3, 1, 2));
		assertTrue(this.s3.remove(2));
		assertTrue(valideSetChaineSimple(this.s3, 1));
		assertFalse(this.s3.remove(2));
		assertTrue(valideSetChaineSimple(this.s3, 1));
		assertTrue(this.s3.remove(1));
		assertTrue(valideSetChaineSimple(this.s3));
		assertFalse(this.s3.remove(1));
		assertTrue(valideSetChaineSimple(this.s3));
		assertTrue(this.s4.remove(8));
		assertTrue(valideSetChaineSimple(this.s4, 5, 7, 2, 1));
		assertFalse(this.s4.remove(8));
		assertTrue(valideSetChaineSimple(this.s4, 5, 7, 2, 1));
		assertTrue(this.s4.remove(1));
		assertTrue(valideSetChaineSimple(this.s4, 5, 7, 2));
		assertFalse(this.s4.remove(1));
		assertTrue(valideSetChaineSimple(this.s4, 5, 7, 2));
		assertTrue(this.s4.remove(5));
		assertTrue(valideSetChaineSimple(this.s4, 7, 2));
		assertFalse(this.s4.remove(5));
		assertTrue(valideSetChaineSimple(this.s4, 7, 2));
	}
	
	@Test
	public void testContains() {
		assertFalse(this.s1.contains(5));
		assertFalse(this.s2.contains(5));
		assertTrue(this.s2.contains(1));
		assertFalse(this.s4.contains(4));
		assertTrue(this.s4.contains(5));
		assertTrue(this.s4.contains(1));
		assertFalse(this.s4.contains(0));
	}

	@Test
	public void testToArray() {
		Integer[] t1 = { 4 };
		Integer[] t2 = { 10, 11, 12, 13, 14, 15 };
		Integer[] t3 = this.s3.toArray(t1);
		Integer[] t4 = this.s3.toArray(t2);
		assertTrue(t3!=null);
		assertTrue(t4!=null);
		assertSame(t3.length, 2);
		assertTrue(t3[0] == 1 && t3[1] == 2 || t3[0] == 2 && t3[1] == 1);
		assertSame(t2, t4);
		assertTrue(t4[0] == 1 && t4[1] == 2 || t4[0] == 2 && t4[1] == 1);
		assertNull(t4[2]);
		assertNull(t4[3]);
		assertNull(t4[4]);
		assertNull(t4[5]);
	}

	// ------------------------ Test des méthodes de Set
	
	@Test
	public void testIterator() {
		Iterator<Integer> it = this.s1.iterator();
		assertFalse(it.hasNext());
		it = this.s2.iterator();
		assertTrue(it.hasNext());
		assertSame(it.next(), 1);
		assertFalse(it.hasNext());
		assertTrue(valideSetChaineSimple(this.s2, 1));
		it.remove();
		assertTrue(valideSetChaineSimple(this.s2));
		it = this.s3.iterator();
		assertTrue(it.hasNext());
		if (it.next()==2) it.remove();
		assertTrue(it.hasNext());
		if (it.next()==2) it.remove();
		assertFalse(it.hasNext());
		assertTrue(valideSetChaineSimple(this.s3, 1));
		it = this.s4.iterator();
		assertTrue(it.hasNext());
		int x = it.next();
		if (x==2 || x==8) it.remove();
		assertTrue(it.hasNext());
		x = it.next();
		if (x==2 || x==8) it.remove();
		assertTrue(it.hasNext());
		x = it.next();
		if (x==2 || x==8) it.remove();
		assertTrue(it.hasNext());
		x = it.next();
		if (x==2 || x==8) it.remove();
		assertTrue(it.hasNext());
		x = it.next();
		if (x==2 || x==8) it.remove();
		assertFalse(it.hasNext());
		assertTrue(valideSetChaineSimple(this.s4, 1, 7, 5));
	}

	@Test(expected=java.util.NoSuchElementException.class)
	public void testIteratorException0() {
		Iterator<Integer> it = this.s1.iterator();
		it.next();
	}

	@Test(expected=java.util.NoSuchElementException.class)
	public void testIteratorException1() {
		Iterator<Integer> it = this.s4.iterator();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
	}

	@Test(expected=IllegalStateException.class)
	public void testIteratorException2() {
		Iterator<Integer> it = this.s4.iterator();
		it.remove();
	}

	@Test(expected=IllegalStateException.class)
	public void testIteratorException3() {
		Iterator<Integer> it = this.s4.iterator();
		it.next();
		it.next();
		it.remove();
		it.remove();
	}

	@Test
	public void testContainsAll() {
		assertTrue(this.s4.containsAll(this.s4));
		assertTrue(this.s4.containsAll(this.s3));
		assertTrue(this.s4.containsAll(this.s2));
		assertTrue(this.s4.containsAll(this.s1));
		assertFalse(this.s3.containsAll(this.s4));
		assertTrue(this.s3.containsAll(this.s3));
		assertTrue(this.s3.containsAll(this.s2));
		assertTrue(this.s3.containsAll(this.s1));
		assertFalse(this.s2.containsAll(this.s4));
		assertFalse(this.s2.containsAll(this.s3));
		assertTrue(this.s2.containsAll(this.s2));
		assertTrue(this.s2.containsAll(this.s1));
		assertFalse(this.s1.containsAll(this.s4));
		assertFalse(this.s1.containsAll(this.s3));
		assertFalse(this.s1.containsAll(this.s2));
		assertTrue(this.s1.containsAll(this.s1));
		this.s3.add(5);
		this.s3.add(7);
		assertTrue(valideSetChaineSimple(this.s3, 1, 2, 5, 7));
		assertFalse(this.s3.containsAll(this.s4));
		this.s3.add(8);
		assertTrue(valideSetChaineSimple(this.s3, 1, 2, 5, 7, 8));
		assertTrue(this.s3.containsAll(this.s4));
		this.s4.remove(1);
		assertTrue(valideSetChaineSimple(this.s4, 5, 7, 8, 2));
		assertTrue(this.s3.containsAll(this.s4));
		this.s4.add(0);
		assertTrue(valideSetChaineSimple(this.s4, 5, 7, 8, 2, 0));
		assertFalse(this.s3.containsAll(this.s4));
		this.s3.add(0);
		assertTrue(valideSetChaineSimple(this.s3, 1, 2, 5, 7, 8, 0));
		assertTrue(this.s3.containsAll(this.s4));
	}
	
	@Test
	public void testAddAll() {
		assertTrue(this.s3.addAll(this.s4));
		assertTrue(valideSetChaineSimple(this.s3, 5, 7, 8, 2, 1));
		assertFalse(this.s3.addAll(this.s4));
		assertTrue(valideSetChaineSimple(this.s3, 5, 7, 8, 2, 1));
		assertTrue(this.s1.addAll(this.s4));
		assertTrue(valideSetChaineSimple(this.s1, 5, 7, 8, 2, 1));
		assertFalse(this.s1.addAll(this.s4));
		assertTrue(valideSetChaineSimple(this.s1, 5, 7, 8, 2, 1));
	}

	@Test
	public void testRetainAll() {
		assertTrue(this.s4.retainAll(this.s3));
		assertTrue(valideSetChaineSimple(this.s4, 1, 2));
		assertFalse(this.s4.retainAll(this.s3));
		assertTrue(valideSetChaineSimple(this.s4, 1, 2));
		assertTrue(this.s4.retainAll(this.s2));
		assertTrue(valideSetChaineSimple(this.s4, 1));
		assertFalse(this.s4.retainAll(this.s2));
		assertTrue(valideSetChaineSimple(this.s4, 1));
		assertTrue(this.s4.retainAll(this.s1));
		assertTrue(valideSetChaineSimple(this.s4));
		assertFalse(this.s4.retainAll(this.s1));
		assertTrue(valideSetChaineSimple(this.s4));
	}

	@Test
	public void testRemoveAll() {
		assertTrue(this.s4.removeAll(this.s3));
		assertTrue(valideSetChaineSimple(this.s4, 5, 7, 8));		
		assertFalse(this.s4.removeAll(this.s3));
		assertTrue(valideSetChaineSimple(this.s4, 5, 7, 8));
		assertTrue(this.s2.removeAll(this.s3));
		assertTrue(valideSetChaineSimple(this.s2));
		assertFalse(this.s2.removeAll(this.s3));
		assertTrue(valideSetChaineSimple(this.s2));
	}

	// ------------------------ Test des méthodes de SetChaineSimple

	@Test
	public void testEquals() {
		Set<Integer> c = new SortedSetChaineSimpleTEST<>();
		assertEquals(this.s1, c);
		assertNotEquals(this.s2, c);
		assertNotEquals(this.s3, c);
		assertNotEquals(this.s4, c);
		c = new SortedSetChaineSimpleTEST<>(1);
		assertNotEquals(this.s1, c);
		assertEquals(this.s2, c);
		assertNotEquals(this.s3, c);
		assertNotEquals(this.s4, c);
		c = new SortedSetChaineSimpleTEST<>(2,1);
		assertNotEquals(this.s1, c);
		assertNotEquals(this.s2, c);
		assertEquals(this.s3, c);
		assertNotEquals(this.s4, c);
		c = new SortedSetChaineSimpleTEST<>(1,2,5,7,8);
		assertNotEquals(this.s1, c);
		assertNotEquals(this.s2, c);
		assertNotEquals(this.s3, c);
		assertEquals(this.s4, c);
	}
	
	// ------------------------ Test des méthodes de SortedSet
	
	@Test
	public void TestSubSet() {
		SortedSet<Integer> sub = this.s1.subSet(-2, 9);
		assertTrue(valideSetChaineSimple(this.s1));
		assertEquals(sub, this.s1);
		sub = this.s2.subSet(-2, 9);
		assertTrue(valideSetChaineSimple(this.s2, 1));
		assertEquals(sub, this.s2);
		sub = this.s3.subSet(-2, 9);
		assertTrue(valideSetChaineSimple(this.s3, 1, 2));
		assertEquals(sub, this.s3);
		sub = this.s4.subSet(-2, 9);
		assertEquals(sub, this.s4);
		assertTrue(valideSetChaineSimple(this.s4, 5, 7, 8, 2, 1));
		sub = this.s4.subSet(2, 8);
		assertTrue(valideSetChaineSimple(this.s4, 5, 7, 8, 2, 1));
		assertTrue(this.s4.containsAll(sub));
		assertFalse(sub.containsAll(this.s4));
		assertFalse(sub.contains(8));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void TestFirstEx() {
		this.s1.first();
	}
	
	@Test
	public void TestFirst() {
		assertSame(this.s2.first(), 1);
		assertSame(this.s3.first(), 1);
		assertSame(this.s4.first(), 1);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void TestLastEx() {
		this.s1.last();
	}
	
	@Test
	public void TestLast() {
		assertSame(this.s2.last(), 1);
		assertSame(this.s3.last(), 2);
		assertSame(this.s4.last(), 8);
	}

}
