package sdr.tests;

import java.util.Arrays;

import sdr.Arbre;

public class EssaisArbre {

	private static void affiche(Arbre<Integer> a) {
		System.out.println(a);
		System.out.println("-> feuille ?   : " + (a.feuille() ? "oui" : "non"));
		System.out.println("-> nb fils     : " + a.nbFils());
		System.out.println("-> nb feuilles : " + a.nbFeuilles());
		System.out.println("-> nb éléments : " + a.nbElements());
		System.out.println();
	}

	public static void main(String[] argv) {

		System.out.println("--------------------------\nCréation d'arbre puis insertion de valeurs : \n");
		Arbre<Integer> a1 = new Arbre<>(1);
		affiche(a1);
		Arbre<Integer> a2 = a1.insereElementFils(2);
		Arbre<Integer> a5 = a2.insereElementFils(5);
		a5.insereElementFrere(6);
		Arbre<Integer> a3 = a2.insereElementFrere(3);
		a3.insereElementFrere(4);
		Arbre<Integer> a7 = a3.insereElementFils(7);
		a1.insereElementFils(256);
		a7.insereElementFils(1000);
		a7.insereElementFrere(2000);
		affiche(a1);

		System.out.println("--------------------------\nClonage et suppression d'éléments :\n");
		Arbre<Integer> clonea1 = a1.clone();
		a1.fils.frere.supprimeFrere();
		a1.fils.frere.supprimeFils();
		System.out.println("Après suppression :");
		affiche(a1);
		System.out.println("Version d'origine :");
		affiche(clonea1);

		System.out.println("--------------------------\nArbres de mots, factorise : \n");
		Arbre<String> a = new Arbre<>("abri");
		System.out.println(a);
		System.out.println("- factorisation avec \"\"        : " + Arrays.toString(a.factorise("")));
		System.out.println("- factorisation avec \"maison\"  : " + Arrays.toString(a.factorise("maison")));
		System.out.println("- factorisation avec \"arbre\"   : " + Arrays.toString(a.factorise("arbre")));
		System.out.println("- factorisation avec \"abrupte\" : " + Arrays.toString(a.factorise("abrupte")));
		System.out.println("- factorisation avec \"abricot\" : " + Arrays.toString(a.factorise("abricot")));

		System.out.println("--------------------------\nArbres de mots, ajouts : \n");
		System.out.println("Ajout abri : " + a.ajouteMot("abri"));
		System.out.println("Ajout abricots : " + a.ajouteMot("abricots"));
		System.out.println("Ajout abritation : " + a.ajouteMot("abritation"));
		System.out.println("Ajout trompette : " + a.ajouteMot("trompette"));
		System.out.println("Ajout tropical : " + a.ajouteMot("tropical"));
		System.out.println(a);

		System.out.println("--------------------------\nArbres de mots, mises à jour : \n");
		System.out.println("Supprime abri : " + a.supprimeMot("abri"));
		System.out.println("Supprime abri : " + a.supprimeMot("abri"));
		System.out.println(a);
		System.out.println("Ajout abri : " + a.ajouteMot("abri"));
		System.out.println("Supprime abricots : " + a.supprimeMot("abricots"));
		System.out.println("Supprime abricots : " + a.supprimeMot("abricots"));
		System.out.println(a);
		System.out.println("Ajout abricots : " + a.ajouteMot("abricots"));
		System.out.println("Supprime abritation : " + a.supprimeMot("abritation"));
		System.out.println("Supprime abritation : " + a.supprimeMot("abritation"));
		System.out.println(a);
		System.out.println("Ajout abritation : " + a.ajouteMot("abritation"));
		System.out.println("Supprime trompette : " + a.supprimeMot("trompette"));
		System.out.println("Supprime trompette : " + a.supprimeMot("trompette"));
		System.out.println(a);
		System.out.println("Ajout trompette : " + a.ajouteMot("trompette"));
		System.out.println("Supprime tropical : " + a.supprimeMot("tropical"));
		System.out.println("Supprime tropical : " + a.supprimeMot("tropical"));
		System.out.println(a);
		System.out.println("Ajout tropical : " + a.ajouteMot("tropical"));
		System.out.println(a);

		System.out.println("--------------------------\nArbres de mots, suppressions : \n");
		System.out.println("Supprime abri : " + a.supprimeMot("abri"));
		System.out.println("Supprime abri : " + a.supprimeMot("abri"));
		System.out.println("Supprime abricots : " + a.supprimeMot("abricots"));
		System.out.println("Supprime abricots : " + a.supprimeMot("abricots"));
		System.out.println("Supprime tropical : " + a.supprimeMot("tropical"));
		System.out.println("Supprime tropical : " + a.supprimeMot("tropical"));
		System.out.println(a);
		System.out.println("Supprime trompette : " + a.supprimeMot("trompette"));
		System.out.println("Supprime trompette : " + a.supprimeMot("trompette"));
		System.out.println(a);
		System.out.println("Ajout trompette : " + a.ajouteMot("trompette"));
		System.out.println("Ajout trompette : " + a.ajouteMot("trompette"));
		System.out.println(a);
		System.out.println("Supprime abritation : " + a.supprimeMot("abritation"));
		System.out.println("Supprime abritation : " + a.supprimeMot("abritation"));
		System.out.println(a);
		System.out.println("Ajout abritation : " + a.ajouteMot("abritation"));
		System.out.println("Ajout abritation : " + a.ajouteMot("abritation"));
		System.out.println(a);
		System.out.println("Supprime trompette : " + a.supprimeMot("trompette"));
		System.out.println("Supprime trompette : " + a.supprimeMot("trompette"));
		System.out.println("Supprime abritation : " + a.supprimeMot("abritation"));
		System.out.println("Supprime abritation : " + a.supprimeMot("abritation"));
		System.out.println(a);

		System.out.println("--------------------------\nArbres de mots, appartenance et extraction : \n");
		a = new Arbre<>("abri");
		System.out.println("Ajout abri : " + a.ajouteMot("abri"));
		System.out.println("Ajout abricots : " + a.ajouteMot("abricots"));
		System.out.println("Ajout abritation : " + a.ajouteMot("abritation"));
		System.out.println("Ajout trompette : " + a.ajouteMot("trompette"));
		System.out.println("Ajout tropical : " + a.ajouteMot("tropical"));
		System.out.println("Ajout abriter : " + a.ajouteMot("abriter"));
		System.out.println("Ajout abritations : " + a.ajouteMot("abritations"));
		System.out.println(a);
		System.out.println("\"abri\"        présent ? " + a.existeMot("abri"));
		System.out.println("\"abricots\"    présent ? " + a.existeMot("abricots"));
		System.out.println("\"abritation\"  présent ? " + a.existeMot("abritation"));
		System.out.println("\"trompette\"   présent ? " + a.existeMot("trompette"));
		System.out.println("\"tropical\"    présent ? " + a.existeMot("tropical"));
		System.out.println("\"abriter\"     présent ? " + a.existeMot("abriter"));
		System.out.println("\"abritations\" présent ? " + a.existeMot("abritations"));
		System.out.println("\"\"            présent ? " + a.existeMot(""));
		System.out.println("\"aac\"         présent ? " + a.existeMot("aac"));
		System.out.println("\"abc\"         présent ? " + a.existeMot("abc"));
		System.out.println("\"bord\"        présent ? " + a.existeMot("bord"));
		System.out.println("\"t\"           présent ? " + a.existeMot("t"));
		System.out.println("\"tro\"         présent ? " + a.existeMot("tro"));
		System.out.println("\"tropic\"      présent ? " + a.existeMot("tropic"));
		System.out.println("\"tropicale\"   présent ? " + a.existeMot("tropicale"));
		System.out.println("Extraction des mots : " + a.recupMots(""));
		System.out.println("Arbre au bout de \"\" :\n" + a.boutDuChemin(""));
		System.out.println("Arbre au bout de \"abris\" :\n" + a.boutDuChemin("abris"));
		System.out.println("Arbre au bout de \"abr\" :\n" + a.boutDuChemin("abr"));
		System.out.println("Arbre au bout de \"abri\" :\n" + a.boutDuChemin("abri"));
		System.out.println("Arbre au bout de \"abrit\" :\n" + a.boutDuChemin("abrit"));

		System.out.println("--------------------------\nBout du chemin, reconstitution de mots : \n");
		String recherche = "abrit";
		Arbre<String> aa = a.boutDuChemin(recherche);
		System.out.println("Arbre :\n" + a);
		System.out.println("Au bout de \"" + recherche + "\" :\n" + aa);
		System.out.println("Extraction des mots au bout de \""+recherche+"\" : " + aa.recupMots(""));
		System.out.println("Reconstitution des mots avec \""+recherche+"\"   : " + aa.recupMots(recherche));
		aa.valeur = recherche + aa.valeur;
		System.out.print("MAJ racine :\n" + aa);
		System.out.println("Reconstitution des mots avec \"\" : " + aa.recupMots("")+"\n");	
		
		recherche = "t";
		aa = a.boutDuChemin(recherche);
		System.out.println("Arbre :\n" + a);
		System.out.println("Au bout de \"" + recherche + "\" :\n" + aa);
		System.out.println("Extraction des mots au bout de \""+recherche+"\" : " + aa.recupMots(""));
		System.out.println("Reconstitution des mots avec \""+recherche+"\"   : " + aa.recupMots(recherche));
		aa.valeur = recherche + aa.valeur;
		System.out.print("MAJ racine :\n" + aa);
		System.out.println("Reconstitution des mots avec \"\" : " + aa.recupMots(""));		

	}
}

/*

--------------------------
Création d'arbre puis insertion de valeurs : 

1

-> feuille ?   : oui
-> nb fils     : 0
-> nb feuilles : 1
-> nb éléments : 1

1                  
+---+---+---------+
256 2   3         4
    +-+ +----+     
    5 6 7    2000  
        |          
        1000       

-> feuille ?   : non
-> nb fils     : 4
-> nb feuilles : 6
-> nb éléments : 10

--------------------------
Clonage et suppression d'éléments :

Après suppression :
1      
+---+-+
256 2 4
    |  
    6  

-> feuille ?   : non
-> nb fils     : 3
-> nb feuilles : 3
-> nb éléments : 5

Version d'origine :
1                  
+---+---+---------+
256 2   3         4
    +-+ +----+     
    5 6 7    2000  
        |          
        1000       

-> feuille ?   : non
-> nb fils     : 4
-> nb feuilles : 6
-> nb éléments : 10

--------------------------
Arbres de mots, factorise : 

abri

- factorisation avec ""        : [, , abri]
- factorisation avec "maison"  : [, maison, abri]
- factorisation avec "arbre"   : [a, rbre, bri]
- factorisation avec "abrupte" : [abr, upte, i]
- factorisation avec "abricot" : [abri, cot, ]
--------------------------
Arbres de mots, ajouts : 

Ajout abri : false
Ajout abricots : true
Ajout abritation : true
Ajout trompette : true
Ajout tropical : true
                          
+-------------+           
abri          tro         
+-+----+      +------+    
  cots tation mpette pical

--------------------------
Arbres de mots, mises à jour : 

Supprime abri : true
Supprime abri : false
                        
+-----------+           
abri        tro         
+----+      +------+    
cots tation mpette pical

Ajout abri : true
Supprime abricots : true
Supprime abricots : false
                     
+--------+           
abri     tro         
+-+      +------+    
  tation mpette pical

Ajout abricots : true
Supprime abritation : true
Supprime abritation : false
                   
+------+           
abri   tro         
+-+    +------+    
  cots mpette pical

Ajout abritation : true
Supprime trompette : true
Supprime trompette : false
                      
+-------------+       
abri          tropical
+-+----+              
  cots tation         

Ajout trompette : true
Supprime tropical : true
Supprime tropical : false
                       
+-------------+        
abri          trompette
+-+----+               
  cots tation          

Ajout tropical : true
                          
+-------------+           
abri          tro         
+-+----+      +------+    
  cots tation mpette pical

--------------------------
Arbres de mots, suppressions : 

Supprime abri : true
Supprime abri : false
Supprime abricots : true
Supprime abricots : false
Supprime tropical : true
Supprime tropical : false
                    
+----------+        
abritation trompette

Supprime trompette : true
Supprime trompette : false
abritation

Ajout trompette : true
Ajout trompette : false
                    
+----------+        
abritation trompette

Supprime abritation : true
Supprime abritation : false
trompette

Ajout abritation : true
Ajout abritation : false
                    
+----------+        
abritation trompette

Supprime trompette : true
Supprime trompette : false
Supprime abritation : true
Supprime abritation : false
 

--------------------------
Arbres de mots, appartenance et extraction : 

Ajout abri : false
Ajout abricots : true
Ajout abritation : true
Ajout trompette : true
Ajout tropical : true
Ajout abriter : true
Ajout abritations : true
                          
+-------------+           
abri          tro         
+-+----+      +------+    
  cots t      mpette pical
       +-----+             
       ation er             
       +-+                
         s                

"abri"        présent ? true
"abricots"    présent ? true
"abritation"  présent ? true
"trompette"   présent ? true
"tropical"    présent ? true
"abriter"     présent ? true
"abritations" présent ? true
""            présent ? false
"aac"         présent ? false
"abc"         présent ? false
"bord"        présent ? false
"t"           présent ? false
"tro"         présent ? false
"tropic"      présent ? false
"tropicale"   présent ? false
Extraction des mots : (abri, abricots, abritation, abritations, abriter, trompette, tropical)
Arbre au bout de "" :
                          
+-------------+           
abri          tro         
+-+----+      +------+    
  cots t      mpette pical
       +-----+             
       ation er             
       +-+                
         s                

Arbre au bout de "abris" :
null
Arbre au bout de "abr" :
i            
+-+----+     
  cots t     
       +-----+
       ation er
       +-+   
         s   

Arbre au bout de "abri" :
             
+-+----+     
  cots t     
       +-----+
       ation er
       +-+   
         s   

Arbre au bout de "abrit" :
      
+-----+
ation er
+-+   
  s   

--------------------------
Bout du chemin, reconstitution de mots : 

Arbre :
                          
+-------------+           
abri          tro         
+-+----+      +------+    
  cots t      mpette pical
       +-----+             
       ation er             
       +-+                
         s                

Au bout de "abrit" :
      
+-----+
ation er
+-+   
  s   

Extraction des mots au bout de "abrit" : (ation, ations, er)
Reconstitution des mots avec "abrit"   : (abritation, abritations, abriter)
MAJ racine :
abrit 
+-----+
ation er
+-+   
  s   
Reconstitution des mots avec "" : (abritation, abritations, abriter)

Arbre :
                          
+-------------+           
abri          tro         
+-+----+      +------+    
  cots t      mpette pical
       +-----+             
       ation er             
       +-+                
         s                

Au bout de "t" :
ro          
+------+    
mpette pical

Extraction des mots au bout de "t" : (rompette, ropical)
Reconstitution des mots avec "t"   : (trompette, tropical)
MAJ racine :
tro         
+------+    
mpette pical
Reconstitution des mots avec "" : (trompette, tropical)

*/
