package col.tests;

import java.util.Arrays;

import col.Iterator;
import col.Set;
import col.SetChaineSimple;
import col.SortedSet;
import col.SortedSetChaineSimple;

public class EssaisSortedSetChaineSimple {

	public static void main(String[] argv) {
		System.out.println("------------ Création d'un ensemble vide :");
		SortedSet<Integer> s = new SortedSetChaineSimple<>();
		System.out.println(s);

		System.out.println("------------ Méthodes de Collection :");
		System.out.println(s + " (" + s.size() + " élém. : " + (s.isEmpty() ? "vide" : "non vide") + ")");
		System.out.println("Ajout de  5 : " + s.add(5));
		System.out.println("Ajout de  8 : " + s.add(8));
		System.out.println("Ajout de -1 : " + s.add(-1));
		System.out.println("Ajout de  8 : " + s.add(8));
		System.out.println(s + " (" + s.size() + " élém. : " + (s.isEmpty() ? "vide" : "non vide") + ")");
		System.out.println("- contient  8 ?   : " + s.contains(8));
		System.out.println("- contient -1 ?   : " + s.contains(-1));
		System.out.println("Suppression de  9 : " + s.remove((Integer) 9));
		System.out.println("Suppression de  8 : " + s.remove((Integer) 8));
		System.out.println("Suppression de -1 : " + s.remove((Integer) (-1)));
		System.out.println("- contient  8 ?   : " + s.contains(8));
		System.out.println("- contient -1 ?   : " + s.contains(-1));
		System.out.println(s + " (" + s.size() + " élém. : " + (s.isEmpty() ? "vide" : "non vide") + ")");
		s.clear();
		System.out.println("clear -> " + s + " (" + s.size() + " élém.)");
		System.out.println("Ajout de  5, 8 et -1...");
		s.add(5); s.add(8); s.add(-1);
		System.out.println(s + " (" + s.size() + " élém. : " + (s.isEmpty() ? "vide" : "non vide") + ")");		
		Integer[] t1 = { 4 };
		Integer[] t2 = { 10, 11, 12, 13, 14, 15 };
		System.out.println("Tableau t1    : " + Arrays.toString(t1));
		System.out.println("Tableau t2    : " + Arrays.toString(t2));
		System.out.println("l.toArray(t1) : " + Arrays.toString(s.toArray(t1)));
		System.out.println("l.toArray(t2) : " + Arrays.toString(s.toArray(t2)));
		System.out.println("Tableau t1    : " + Arrays.toString(t1));
		System.out.println("Tableau t2    : " + Arrays.toString(t2));

		System.out.println("------------ Méthodes de Set et equals :");
		s.add(5); s.add(8); s.add(-1); s.add(9);
		System.out.println("Parcours de " + s + " et suppression des entiers négatifs :");
		for (Iterator<Integer> it = s.iterator(); it.hasNext();) {
			Integer i = it.next();
			if (i<0) it.remove();
			System.out.print("-> " + i + (i<0 ? " (supprimé) " : " "));
		}
		System.out.println("[fin]");
		System.out.println("Résultat : "+ s);
		Set<Integer> s2 = new SetChaineSimple<>();
		s2.add(9); s2.add(5);
		System.out.println(s + " contient " + s2 + " ? " + s.containsAll(s2));
		s2.add(3); s2.add(-1);
		System.out.println(s + " contient " + s2 + " ? " + s.containsAll(s2));
		System.out.print(s + " union " + s2 + " = ");
		s.addAll(s2);
		System.out.println(s);
		s.clear(); s.add(5); s.add(8); s.add(9);
		System.out.print(s + " inter " + s2 + " = ");
		s.retainAll(s2);
		System.out.println(s);
		s.clear(); s.add(5); s.add(8); s.add(9);
		System.out.print(s + " diff  " + s2 + " = ");
		s.removeAll(s2);
		System.out.println(s);
		s.clear(); s.add(5); s.add(8); s.add(9);
		s2.clear(); s2.add(5); s2.add(9);
		System.out.println(s + " equals " + s2 + " ? " + s.equals(s2));
		s2.add(8); s2.add(2);
		System.out.println(s + " equals " + s2 + " ? " + s.equals(s2));
		s2.remove(2);
		System.out.println(s + " equals " + s2 + " ? " + s.equals(s2));

		System.out.println("------------ Méthodes de SortedSet :");
		s.add(5); s.add(4); s.add(-1); s.add(9);
		System.out.println("Eléments de " + s + " dans [ 4 ;  8[ : " + s.subSet(4, 8));
		System.out.println("Eléments de " + s + " dans [ 0 ;  5[ : " + s.subSet(0, 5));
		System.out.println("Eléments de " + s + " dans [-1 ; 15[ : " + s.subSet(-1, 15));
		System.out.println("Min de " + s + " : " + s.first());
		System.out.println("Max de " + s + " : " + s.last());
	}
}

/*

------------ Création d'un ensemble vide :
{ }
------------ Méthodes de Collection :
{ } (0 élém. : vide)
Ajout de  5 : true
Ajout de  8 : true
Ajout de -1 : true
Ajout de  8 : false
{ -1 8 5 } (3 élém. : non vide)
- contient  8 ?   : true
- contient -1 ?   : true
Suppression de  9 : false
Suppression de  8 : true
Suppression de -1 : true
- contient  8 ?   : false
- contient -1 ?   : false
{ 5 } (1 élém. : non vide)
clear -> { } (0 élém.)
Ajout de  5, 8 et -1...
{ -1 8 5 } (3 élém. : non vide)
Tableau t1    : [4]
Tableau t2    : [10, 11, 12, 13, 14, 15]
l.toArray(t1) : [-1, 8, 5]
l.toArray(t2) : [-1, 8, 5, null, null, null]
Tableau t1    : [4]
Tableau t2    : [-1, 8, 5, null, null, null]
------------ Méthodes de Set et equals :
Parcours de { 9 -1 8 5 } et suppression des entiers négatifs :
-> 9 -> -1 (supprimé) -> 8 -> 5 [fin]
Résultat : { 9 8 5 }
{ 9 8 5 } contient { 5 9 } ? true
{ 9 8 5 } contient { -1 3 5 9 } ? false
{ 9 8 5 } union { -1 3 5 9 } = { 3 -1 9 8 5 }
{ 9 8 5 } inter { -1 3 5 9 } = { 9 5 }
{ 9 8 5 } diff  { -1 3 5 9 } = { 8 }
{ 9 8 5 } equals { 9 5 } ? false
{ 9 8 5 } equals { 2 8 9 5 } ? false
{ 9 8 5 } equals { 8 9 5 } ? true
------------ Méthodes de SortedSet :
Eléments de { -1 4 9 8 5 } dans [ 4 ;  8[ : { 4 5 }
Eléments de { -1 4 9 8 5 } dans [ 0 ;  5[ : { 4 }
Eléments de { -1 4 9 8 5 } dans [-1 ; 15[ : { -1 4 9 8 5 }
Min de { -1 4 9 8 5 } : -1
Max de { -1 4 9 8 5 } : 9

*/