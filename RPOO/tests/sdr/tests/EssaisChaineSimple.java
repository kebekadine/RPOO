package sdr.tests;

import sdr.ChaineSimple;

public class EssaisChaineSimple {

	public static void main(String[] argv) {
		System.out.println("Création de chaînes simples : ");
		ChaineSimple<Integer> c1 = new ChaineSimple<>(1);
		ChaineSimple<Integer> c2 = new ChaineSimple<>(2, c1);
		ChaineSimple<Integer> c3 = new ChaineSimple<>(3, c2);
		System.out.println("c1 : " + c1);
		System.out.println("c2 : " + c2);
		System.out.println("c3 : " + c3);

		System.out.println("Insertion de valeurs : ");
		c1.insereElementSuivant(-1);
		c2.insereElementSuivant(-2);
		c3.insereElementSuivant(-3);
		System.out.println("c1 : " + c1);
		System.out.println("c2 : " + c2);
		System.out.println("c3 : " + c3);

		System.out.println("Suppression de valeurs : ");
		c1.supprimeElementSuivant();
		c2.supprimeElementSuivant();
		c3.supprimeElementSuivant();
		System.out.println("c1 : " + c1);
		System.out.println("c2 : " + c2);
		System.out.println("c3 : " + c3);

		System.out.println("Incrémentation de c3 : ");
		c3.maj(i -> i + 1);
		System.out.println("c3 : " + c3);

		System.out.println("Transformation de c3 : ");
		ChaineSimple<Character> cc = c3.map(i -> (char) (i.intValue() + 64));
		System.out.println("c3 : " + c3);
		System.out.println("cc : " + cc);

		System.out.println("Inversion de c3 : ");
		ChaineSimple<Integer> c4 = c3.inverse();
		System.out.println("c3 : " + c3);
		System.out.println("c4 : " + c4);
	}
}

/*

Création de chaînes simples : 
c1 : (1)
c2 : (2, 1)
c3 : (3, 2, 1)
Insertion de valeurs : 
c1 : (1, -1)
c2 : (2, -2, 1, -1)
c3 : (3, -3, 2, -2, 1, -1)
Suppression de valeurs : 
c1 : (1)
c2 : (2, 1)
c3 : (3, 2, 1)
Incrémentation de c3 : 
c3 : (4, 3, 2)
Transformation de c3 : 
c3 : (4, 3, 2)
cc : (D, C, B)
Inversion de c3 : 
c3 : (4, 3, 2)
c4 : (2, 3, 4)

*/
