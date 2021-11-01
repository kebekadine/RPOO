package sdr.tests;

import sdr.ArbreBinaire;

public class EssaisArbreBinaire {

	public static void main(String[] argv) {
		System.out.println("--------------------------\nCréation d'arbre puis insertion de valeurs : \n");
		ArbreBinaire<Integer> a = new ArbreBinaire<>(4);
		System.out.print(a.toString(true, false, false));
		System.out.println("Feuille ? " + a.feuille() + "\n");
		a.insereElementGauche(1, true);
		a.insereElementDroit(6, true);
		a.insereElementPere(8, true);
		System.out.print(a.toString(true, false, false));
		System.out.println("Feuille ? " + a.feuille());
		
		System.out.println("--------------------------\nSuppression du fil droit de 4 puis suppression des fils de 8 :\n");
		System.out.print("Element droit  de 4 supprimé : " + a.supprimeElementDroit());
		a = a.pere;
		System.out.println("Element droit  de 8 supprimé : " + a.supprimeElementDroit());
		System.out.println("Element gauche de 8 supprimé : " + a.supprimeElementGauche());
		System.out.println(a.toString(true, false, false));

		System.out.println("--------------------------\nCréation d'arbre binaire et ajout de valeurs : \n");
		a = new ArbreBinaire<>(4);
		a.ajoute(1);
		a.ajoute(2);
		a.ajoute(3);
		a.ajoute(4);
		a.ajoute(5);
		a.ajoute(6);
		a.ajoute(7);
		a.ajoute(8);
		a.ajoute(9);
		System.out.println(a);
		System.out.println("Avec hauteur et facteur d'équilibrage : \n");
		System.out.println(a.toString(false, true, true));
		System.out.println("Ajout de 10 : \n");
		a.ajoute(10);
		System.out.println(a);
		System.out.println("Ajout de 11 et 12 : \n");
		a.ajoute(11);
		a.ajoute(12);
		System.out.println(a);
		System.out.println("--------------------------\nParcours et tests : \n");
		System.out.println("préfixe       : " + a.prefixe());
		System.out.println("infixe        : " + a.infixe());
		System.out.println("postfixe      : " + a.postfixe());
		System.out.println("largeur       : " + a.largeur());
		System.out.println("génération -1 : " + a.generation(-1));
		System.out.println("génération  0 : " + a.generation(0));
		System.out.println("génération  1 : " + a.generation(1));
		System.out.println("génération  2 : " + a.generation(2));
		System.out.println("génération  3 : " + a.generation(3));
		System.out.println("génération  4 : " + a.generation(4));
		System.out.println("minimum       : " + a.minimum());
		System.out.println("maximum       : " + a.maximum());
		System.out.println("contient -1   : " + a.contient(-1));
		System.out.println("contient 1    : " + a.contient(1));
		System.out.println("contient 6    : " + a.contient(6));
		System.out.println("contient 9    : " + a.contient(9));
		System.out.println("contient 13   : " + a.contient(13));
		System.out.println("[-1 : 15 [    : " + a.extraire(-1, 15));
		System.out.println("[ 1 :  7 [    : " + a.extraire(1, 7));
		System.out.println("[ 6 : 10 [    : " + a.extraire(6, 10));
		System.out.println("--------------------------\nSuppression de 1, 2, 3 : \n");
		a.supprime(1);
		a.supprime(2);
		a.supprime(3);
		System.out.println(a);
		System.out.println("--------------------------\nSuppression de 6, 5, 7 : \n");
		a.supprime(6);
		a.supprime(5);
		a.supprime(7);
		System.out.println(a);
	}
}

/*

--------------------------
Création d'arbre puis insertion de valeurs : 

[4]
Feuille ? true

8  
|  
[4]
+-+
1 6
Feuille ? false
--------------------------
Suppression du fil droit de 4 puis suppression des fils de 8 :

Element droit  de 4 supprimé : 6
Element droit  de 8 supprimé : null
Element gauche de 8 supprimé : 4

[8]
|
1

--------------------------
Création d'arbre binaire et ajout de valeurs : 

4        
+---+    
2   6    
+-+ +-+  
1 3 5 8  
      +-+
      7 9

Avec hauteur et facteur d'équilibrage : 

4|h:3|e:1                                        
+-------------------+                            
2|h:1|e:0           6|h:2|e:1                    
+---------+         +---------+                  
1|h:0|e:0 3|h:0|e:0 5|h:0|e:0 8|h:1|e:0          
                              +---------+        
                              7|h:0|e:0 9|h:0|e:0

Ajout de 10 : 

4           
+---+       
2   8       
+-+ +---+   
1 3 6   9   
    +-+ +-+ 
    5 7   10

Ajout de 11 et 12 : 

8             
+-------+     
4       10    
+---+   +-+   
2   6   9 11  
+-+ +-+   +-+ 
1 3 5 7     12

--------------------------
Parcours et tests : 

préfixe       : (8, 4, 2, 1, 3, 6, 5, 7, 10, 9, 11, 12)
infixe        : (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
postfixe      : (1, 3, 2, 5, 7, 6, 4, 9, 12, 11, 10, 8)
largeur       : (8, 4, 10, 2, 6, 9, 11, 1, 3, 5, 7, 12)
génération -1 : null
génération  0 : (8)
génération  1 : (4, 10)
génération  2 : (2, 6, 9, 11)
génération  3 : (1, 3, 5, 7, 12)
génération  4 : null
minimum       : 1
maximum       : 12
contient -1   : false
contient 1    : true
contient 6    : true
contient 9    : true
contient 13   : false
[-1 : 15 [    : (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
[ 1 :  7 [    : (1, 2, 3, 4, 5, 6)
[ 6 : 10 [    : (6, 7, 8, 9)
--------------------------
Suppression de 1, 2, 3 : 

8           
+-----+     
6     10    
+---+ +-+   
4   7 9 11  
+-+     +-+ 
  5       12

--------------------------
Suppression de 6, 5, 7 : 

10      
+---+   
8   11  
+-+ +-+ 
4 9   12

*/
