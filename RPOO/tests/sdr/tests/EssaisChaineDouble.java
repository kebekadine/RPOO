package sdr.tests;

import sdr.ChaineDouble;

public class EssaisChaineDouble {

	public static void main(String[] argv) {
		System.out.println("Création de chaîne double : ");
		ChaineDouble<Integer> c = new ChaineDouble<>(3);
		System.out.println("c : " + c);

		System.out.println("Insertion de valeurs : ");
		c.insereElementSuivant(5);
		c.insereElementSuivant(4);
		System.out.println("c : " + c);
		c.insereElementPrecedent(1);
		c.insereElementPrecedent(2);
		System.out.println("c : " + c);

		System.out.println("Suppression de valeurs : ");
		c.supprimeElementSuivant();
		System.out.println("c : " + c);
		c.supprimeElementSuivant();
		System.out.println("c : " + c);
		c.supprimeElementPrecedent();
		System.out.println("c : " + c);
		c.supprimeElementPrecedent();
		System.out.println("c : " + c);

		System.out.println("Re-création de c et incrémentation : ");
		c.insereElementSuivant(5);
		c.insereElementSuivant(4);
		c.insereElementPrecedent(1);
		c.insereElementPrecedent(2);
		System.out.println("c : " + c);
		c.maj(i -> i + 1);
		System.out.println("c : " + c);

		System.out.println("Transformation de c : ");
		ChaineDouble<Character> t = c.map(i -> (char) (i.intValue() + 64));
		System.out.println("t : " + t);

		System.out.println("Inversion et réinversion de c : ");
		c.inverse();
		System.out.println("c : " + c);
		c.inverse();
		System.out.println("c : " + c);
	}
}

/*

Création de chaîne double : 
c : ([3])
Insertion de valeurs : 
c : ([3], 4, 5)
c : (1, 2, [3], 4, 5)
Suppression de valeurs : 
c : (1, 2, [3], 5)
c : (1, 2, [3])
c : (1, [3])
c : ([3])
Re-création de c et incrémentation : 
c : (1, 2, [3], 4, 5)
c : (2, 3, [4], 5, 6)
Transformation de c : 
t : (B, C, [D], E, F)
Inversion et réinversion de c : 
c : (6, 5, [4], 3, 2)
c : (2, 3, [4], 5, 6)

*/
