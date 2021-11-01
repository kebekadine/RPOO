package col.tests;

import java.util.Arrays;

import col.List;
import col.ListChaineDouble;

public class EssaisListChaineDouble {

	public static void main(String[] argv) {
		System.out.println("---------- Création de liste, ajout (add) et affichage :");
		List<Integer> l = new ListChaineDouble<>();
		System.out.println("Création d'une liste vide   : " + l);
		l.add(0, 4);
		System.out.println("Insertion de 4 à l'indice 0 : " + l);
		l.add(0, 9);
		System.out.println("Insertion de 9 à l'indice 0 : " + l);
		l.add(2, 8);
		System.out.println("Insertion de 8 à l'indice 2 : " + l);
		l.add(3, 7);
		System.out.println("Insertion de 7 à l'indice 3 : " + l);
		l.add(1, 5);
		System.out.println("Insertion de 5 à l'indice 1 : " + l);
		l.add(4, 0);
		System.out.println("Insertion de 0 à l'indice 4 : " + l);
		System.out.print  ("Insertion de 3 à l'indice 7 : ");
		try { l.add(7, 3); System.out.println(l);}
		catch (IndexOutOfBoundsException e) { System.out.println("exception"); }
		
		System.out.println("---------- Accès (get) et mise à jour (set) :");
		System.out.print("Element d'indice -1 : ");
		try { System.out.println(l.get(-1)); }
		catch (IndexOutOfBoundsException e) { System.out.println("exception"); }
		System.out.print("Element d'indice  6 : ");
		try { System.out.println(l.get(6)); }
		catch (IndexOutOfBoundsException e) { System.out.println("exception"); }
		System.out.println("Element d'indice  0 : " + l.get(0));
		System.out.println("Element d'indice  3 : " + l.get(3));
		System.out.println("Element d'indice  5 : " + l.get(5));
		System.out.println("MAJ 3 à l'indice 1 ; ancienne valeur : " + l.set(1, 3) + " ; liste : " + l);
		System.out.println("MAJ 2 à l'indice 5 ; ancienne valeur : " + l.set(5, 2) + " ; liste : " + l);
		System.out.print("MAJ 2 à l'indice 6 : ");
		try { l.set(6, 2); System.out.println(l); }
		catch (IndexOutOfBoundsException e) { System.out.println("exception"); }
		
		System.out.println("---------- Suppressions (remove) :");
		System.out.println("Suppression à l'indice 1 ; valeur : " + l.remove(1) + " ; liste : " + l);
		System.out.println("Suppression à l'indice 1 ; valeur : " + l.remove(1) + " ; liste : " + l);
		System.out.println("Suppression à l'indice 1 ; valeur : " + l.remove(1) + " ; liste : " + l);
		System.out.println("Suppression à l'indice 2 ; valeur : " + l.remove(2) + " ; liste : " + l);
		System.out.println("Suppression à l'indice 0 ; valeur : " + l.remove(0) + " ; liste : " + l);
		System.out.println("Suppression à l'indice 0 ; valeur : " + l.remove(0) + " ; liste : " + l);
		System.out.print("Suppression à l'indice 0 : ");
		try { l.remove(0); System.out.println(l); }
		catch (IndexOutOfBoundsException e) { System.out.println("exception"); }
		
		System.out.println("---------- Méthodes size, clear, addAll, indexOf et lastIndexOf :");
		System.out.println("Liste 1 : " + l + " size : " + l.size());
		List<Integer> l2 = new ListChaineDouble<>();
		System.out.println("Liste 2 : " + l2 + " size : " + l2.size());
		System.out.println("Ajout de d'éléments...");
		l.add(0, 2); l.add(1, 5); l.add(2, 9); 
		l2.add(0, 3); l2.add(1, 9); 
		System.out.println("Liste 1 : " + l + " size : " + l.size());
		System.out.println("Liste 2 : " + l2 + " size : " + l2.size());
		System.out.println("Ajout de la liste 2 à l'indice 1 de la liste 1...");
		l.addAll(1, l2); 
		System.out.println("Liste 1 : " + l + " size : " + l.size());
		System.out.println("Liste 2 : " + l2 + " size : " + l2.size());
		System.out.println("Suppression de tous les éléments de la liste 2...");
		l2.clear(); 
		System.out.println("Liste 1 : " + l + " size : " + l.size());
		System.out.println("Liste 2 : " + l2 + " size : " + l2.size());
		System.out.println(l + " indices :");
		System.out.println("- premier indice de 9 : " + l.indexOf(9));
		System.out.println("- dernier indice de 9 : " + l.lastIndexOf(9));
		System.out.println("- premier indice de 5 : " + l.indexOf(5));
		System.out.println("- dernier indice de 5 : " + l.lastIndexOf(5));
		System.out.println("- premier indice de 4 : " + l.indexOf(4));
		System.out.println("- dernier indice de 4 : " + l.lastIndexOf(4));
		
		System.out.println("---------- Méthodes add, remove, contains, toArray et equals :");
		System.out.println(l);
		l.add(1);
		l.add(8);
		System.out.println("Ajout en fin de 1 puis de 8 : " + l);
		System.out.println("Contient 9 ?     : " + (l.contains(9)?"OUI":"NON"));
		System.out.println("Suppression de 9 : " + (l.remove((Integer) 9)?"OUI":"NON") + " -> " + l);
		System.out.println("Suppression de 9 : " + (l.remove((Integer) 9)?"OUI":"NON") + " -> " + l);
		System.out.println("Suppression de 9 : " + (l.remove((Integer) 9)?"OUI":"NON") + " -> " + l);
		System.out.println("Contient 9 ?     : " + (l.contains(9)?"OUI":"NON"));
		Integer[] t1 = { 4 };
		Integer[] t2 = { 10, 11, 12, 13, 14, 15 };
		System.out.println("Tableau t1    : " + Arrays.toString(t1));
		System.out.println("Tableau t2    : " + Arrays.toString(t2));
		System.out.println("l.toArray(t1) : " + Arrays.toString(l.toArray(t1)));
		System.out.println("l.toArray(t2) : " + Arrays.toString(l.toArray(t2)));
		System.out.println("Tableau t1    : " + Arrays.toString(t1));
		System.out.println("Tableau t2    : " + Arrays.toString(t2));
		l2.clear(); l2.add(2); l2.add(3); l2.add(5); l2.add(1); 
		System.out.println(l + " equals " + l2 + " : " + l.equals(l2));
		l2.add(8); 
		System.out.println(l + " equals " + l2 + " : " + l.equals(l2));
		l2.add(9); 
		System.out.println(l + " equals " + l2 + " : " + l.equals(l2));
		l2.remove((Integer) 9); l2.set(2, 4); 
		System.out.println(l + " equals " + l2 + " : " + l.equals(l2));
	}
}

/*

---------- Création de liste, ajout (add) et affichage :
Création d'une liste vide   : ( )
Insertion de 4 à l'indice 0 : ( 4 )
Insertion de 9 à l'indice 0 : ( 9 4 )
Insertion de 8 à l'indice 2 : ( 9 4 8 )
Insertion de 7 à l'indice 3 : ( 9 4 8 7 )
Insertion de 5 à l'indice 1 : ( 9 5 4 8 7 )
Insertion de 0 à l'indice 4 : ( 9 5 4 8 0 7 )
Insertion de 3 à l'indice 7 : exception
---------- Accès (get) et mise à jour (set) :
Element d'indice -1 : exception
Element d'indice  6 : exception
Element d'indice  0 : 9
Element d'indice  3 : 8
Element d'indice  5 : 7
MAJ 3 à l'indice 1 ; ancienne valeur : 5 ; liste : ( 9 3 4 8 0 7 )
MAJ 2 à l'indice 5 ; ancienne valeur : 7 ; liste : ( 9 3 4 8 0 2 )
MAJ 2 à l'indice 6 : exception
---------- Suppressions (remove) :
Suppression à l'indice 1 ; valeur : 3 ; liste : ( 9 4 8 0 2 )
Suppression à l'indice 1 ; valeur : 4 ; liste : ( 9 8 0 2 )
Suppression à l'indice 1 ; valeur : 8 ; liste : ( 9 0 2 )
Suppression à l'indice 2 ; valeur : 2 ; liste : ( 9 0 )
Suppression à l'indice 0 ; valeur : 9 ; liste : ( 0 )
Suppression à l'indice 0 ; valeur : 0 ; liste : ( )
Suppression à l'indice 0 : exception
---------- Méthodes size, clear, addAll, indexOf et lastIndexOf :
Liste 1 : ( ) size : 0
Liste 2 : ( ) size : 0
Ajout de d'éléments...
Liste 1 : ( 2 5 9 ) size : 3
Liste 2 : ( 3 9 ) size : 2
Ajout de la liste 2 à l'indice 1 de la liste 1...
Liste 1 : ( 2 3 9 5 9 ) size : 5
Liste 2 : ( 3 9 ) size : 2
Suppression de tous les éléments de la liste 2...
Liste 1 : ( 2 3 9 5 9 ) size : 5
Liste 2 : ( ) size : 0
( 2 3 9 5 9 ) indices :
- premier indice de 9 : 2
- dernier indice de 9 : 4
- premier indice de 5 : 3
- dernier indice de 5 : 3
- premier indice de 4 : -1
- dernier indice de 4 : -1
---------- Méthodes add, remove, contains, toArray et equals :
( 2 3 9 5 9 )
Ajout en fin de 1 puis de 8 : ( 2 3 9 5 9 1 8 )
Contient 9 ?     : OUI
Suppression de 9 : OUI -> ( 2 3 5 9 1 8 )
Suppression de 9 : OUI -> ( 2 3 5 1 8 )
Suppression de 9 : NON -> ( 2 3 5 1 8 )
Contient 9 ?     : NON
Tableau t1    : [4]
Tableau t2    : [10, 11, 12, 13, 14, 15]
l.toArray(t1) : [2, 3, 5, 1, 8]
l.toArray(t2) : [2, 3, 5, 1, 8, null]
Tableau t1    : [4]
Tableau t2    : [2, 3, 5, 1, 8, null]
( 2 3 5 1 8 ) equals ( 2 3 5 1 ) : false
( 2 3 5 1 8 ) equals ( 2 3 5 1 8 ) : true
( 2 3 5 1 8 ) equals ( 2 3 5 1 8 9 ) : false
( 2 3 5 1 8 ) equals ( 2 3 4 1 8 ) : false

*/
