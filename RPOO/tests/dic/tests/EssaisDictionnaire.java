package dic.tests;

import java.io.IOException;

import dic.Dictionnaire;
import dic.DictionnaireArbre;
import dic.DictionnaireSortedSetArbreBinaire;
import dic.DictionnaireSortedSetChaineSimple;

public class EssaisDictionnaire {

	private static void affDic(Dictionnaire d, boolean toString) {
		System.out.println("Dictionnaire de type "+d.getClass().getName()+" :");
		if (toString)
			System.out.println("Contenu : " + d);
		System.out.println("Nombre de mots : " + d.nbMots());
		System.out.println("Recherche de \"abri\" : " + (d.existe("abri") ? "présent" : "absent"));
		System.out.println("Recherche de \"lit\"  : " + (d.existe("lit") ? "présent" : "absent"));
	}
	
	private static void testDic(boolean toString, Dictionnaire... dd) {
		for (Dictionnaire d : dd)
			affDic(d, toString);

		System.out.println("--------------------------------------------------------");
		System.out.println("Ajout de abri, lit");
		System.out.println("--------------------------------------------------------");
		for (Dictionnaire d : dd) {
			d.ajoute("abri");
			d.ajoute("lit");
			d.ajoute("abri");
			d.ajoute("lit");
			affDic(d, toString);
		}

		System.out.println("--------------------------------------------------------");
		System.out.println("Suppression de abri, abricot");
		System.out.println("--------------------------------------------------------");
		for (Dictionnaire d : dd) {
			d.supprime("abricot");
			d.supprime("abri");
			d.supprime("abricot");
			affDic(d, toString);
		}

		System.out.println("--------------------------------------------------------");
		System.out.println("Mots préfixés");
		System.out.println("--------------------------------------------------------");
		for (Dictionnaire d : dd) {		
			System.out.println("Dictionnaire de type "+d.getClass().getName()+" :");
			System.out.println("Mots commençant par \"trav\"  : " + d.prefixe("trav"));
			System.out.println("Mots commençant par \"travail\"  : " + d.prefixe("travail"));
			System.out.println("Mots commençant par \"travailleurs\"  : " + d.prefixe("travailleurs"));
			System.out.println("Mots commençant par \"k\"  : " + d.prefixe("k"));
			System.out.println("Mots commençant par \"l\"  : " + d.prefixe("l"));
			System.out.println("Mots commençant par \"z\"  : " + d.prefixe("z"));
			
		}
	}
	
	public static void main(String[] args) throws IOException {

		DictionnaireSortedSetChaineSimple d1 = new DictionnaireSortedSetChaineSimple();
		DictionnaireSortedSetArbreBinaire d2 = new DictionnaireSortedSetArbreBinaire();
		DictionnaireArbre d3 = new DictionnaireArbre();
		testDic(true, d1, d2, d3);
		
		System.out.println("--------------------------------------------------------");
		System.out.println("Chargement de \"dicoS.txt\" :");		
		System.out.println("--------------------------------------------------------");
		d1.charge("mots/dicoS.txt");
		d2.charge("mots/dicoS.txt");
		d3.charge("mots/dicoS.txt");
		testDic(true, d1, d2, d3);

		System.out.println("--------------------------------------------------------");
		System.out.println("Chargement de \"dicom.txt\" :");		
		System.out.println("--------------------------------------------------------");
		d1.charge("mots/dicoM.txt");
		d2.charge("mots/dicoM.txt");
		d3.charge("mots/dicoM.txt");
		testDic(false, d1, d2, d3);
	}

}

/*

Dictionnaire de type rpooc.dic.DictionnaireSortedSetChaineSimple :
Contenu : 0 mot : { }
Nombre de mots : 0
Recherche de "abri" : absent
Recherche de "lit"  : absent
Dictionnaire de type rpooc.dic.DictionnaireSortedSetArbreBinaire :
Contenu : 0 mot : { }
Nombre de mots : 0
Recherche de "abri" : absent
Recherche de "lit"  : absent
Dictionnaire de type rpooc.dic.DictionnaireArbre :
Contenu : 0 mot : { }
Nombre de mots : 0
Recherche de "abri" : absent
Recherche de "lit"  : absent
--------------------------------------------------------
Ajout de abri, lit
--------------------------------------------------------
Dictionnaire de type rpooc.dic.DictionnaireSortedSetChaineSimple :
Contenu : 2 mots : { lit abri }
Nombre de mots : 2
Recherche de "abri" : présent
Recherche de "lit"  : présent
Dictionnaire de type rpooc.dic.DictionnaireSortedSetArbreBinaire :
Contenu : 2 mots : { abri lit }
Nombre de mots : 2
Recherche de "abri" : présent
Recherche de "lit"  : présent
Dictionnaire de type rpooc.dic.DictionnaireArbre :
Contenu : 2 mots : { abri lit }
Nombre de mots : 2
Recherche de "abri" : présent
Recherche de "lit"  : présent
--------------------------------------------------------
Suppression de abri, abricot
--------------------------------------------------------
Dictionnaire de type rpooc.dic.DictionnaireSortedSetChaineSimple :
Contenu : 1 mot : { lit }
Nombre de mots : 1
Recherche de "abri" : absent
Recherche de "lit"  : présent
Dictionnaire de type rpooc.dic.DictionnaireSortedSetArbreBinaire :
Contenu : 1 mot : { lit }
Nombre de mots : 1
Recherche de "abri" : absent
Recherche de "lit"  : présent
Dictionnaire de type rpooc.dic.DictionnaireArbre :
Contenu : 1 mot : { lit }
Nombre de mots : 1
Recherche de "abri" : absent
Recherche de "lit"  : présent
--------------------------------------------------------
Mots préfixés
--------------------------------------------------------
Dictionnaire de type rpooc.dic.DictionnaireSortedSetChaineSimple :
Mots commençant par "trav"  : 0 mot : { }
Mots commençant par "travail"  : 0 mot : { }
Mots commençant par "travailleurs"  : 0 mot : { }
Mots commençant par "k"  : 0 mot : { }
Mots commençant par "l"  : 1 mot : { lit }
Mots commençant par "z"  : 0 mot : { }
Dictionnaire de type rpooc.dic.DictionnaireSortedSetArbreBinaire :
Mots commençant par "trav"  : 0 mot : { }
Mots commençant par "travail"  : 0 mot : { }
Mots commençant par "travailleurs"  : 0 mot : { }
Mots commençant par "k"  : 0 mot : { }
Mots commençant par "l"  : 1 mot : { lit }
Mots commençant par "z"  : 0 mot : { }
Dictionnaire de type rpooc.dic.DictionnaireArbre :
Mots commençant par "trav"  : 0 mot : { }
Mots commençant par "travail"  : 0 mot : { }
Mots commençant par "travailleurs"  : 0 mot : { }
Mots commençant par "k"  : 0 mot : { }
Mots commençant par "l"  : 1 mot : { lit }
Mots commençant par "z"  : 0 mot : { }
--------------------------------------------------------
Chargement de "dicoS.txt" :
--------------------------------------------------------
Dictionnaire de type rpooc.dic.DictionnaireSortedSetChaineSimple :
Contenu : 31 mots : { zeste vue vente vent travers travail table proche oncle mouche marier lits lien large gris grande flot enlever engager dernier depuis combien combat aide agiter abriter abricot abri abeille abandon lit }
Nombre de mots : 31
Recherche de "abri" : présent
Recherche de "lit"  : présent
Dictionnaire de type rpooc.dic.DictionnaireSortedSetArbreBinaire :
Contenu : 31 mots : { abandon abeille abri abricot abriter agiter aide combat combien depuis dernier engager enlever flot grande gris large lien lit lits marier mouche oncle proche table travail travers vent vente vue zeste }
Nombre de mots : 31
Recherche de "abri" : présent
Recherche de "lit"  : présent
Dictionnaire de type rpooc.dic.DictionnaireArbre :
Contenu : 31 mots : { abandon abeille abri abricot abriter agiter aide combat combien depuis dernier engager enlever flot grande gris large lien lit lits marier mouche oncle proche table travail travers vent vente vue zeste }
Nombre de mots : 31
Recherche de "abri" : présent
Recherche de "lit"  : présent
--------------------------------------------------------
Ajout de abri, lit
--------------------------------------------------------
Dictionnaire de type rpooc.dic.DictionnaireSortedSetChaineSimple :
Contenu : 31 mots : { zeste vue vente vent travers travail table proche oncle mouche marier lits lien large gris grande flot enlever engager dernier depuis combien combat aide agiter abriter abricot abri abeille abandon lit }
Nombre de mots : 31
Recherche de "abri" : présent
Recherche de "lit"  : présent
Dictionnaire de type rpooc.dic.DictionnaireSortedSetArbreBinaire :
Contenu : 31 mots : { abandon abeille abri abricot abriter agiter aide combat combien depuis dernier engager enlever flot grande gris large lien lit lits marier mouche oncle proche table travail travers vent vente vue zeste }
Nombre de mots : 31
Recherche de "abri" : présent
Recherche de "lit"  : présent
Dictionnaire de type rpooc.dic.DictionnaireArbre :
Contenu : 31 mots : { abandon abeille abri abricot abriter agiter aide combat combien depuis dernier engager enlever flot grande gris large lien lit lits marier mouche oncle proche table travail travers vent vente vue zeste }
Nombre de mots : 31
Recherche de "abri" : présent
Recherche de "lit"  : présent
--------------------------------------------------------
Suppression de abri, abricot
--------------------------------------------------------
Dictionnaire de type rpooc.dic.DictionnaireSortedSetChaineSimple :
Contenu : 29 mots : { zeste vue vente vent travers travail table proche oncle mouche marier lits lien large gris grande flot enlever engager dernier depuis combien combat aide agiter abriter abeille abandon lit }
Nombre de mots : 29
Recherche de "abri" : absent
Recherche de "lit"  : présent
Dictionnaire de type rpooc.dic.DictionnaireSortedSetArbreBinaire :
Contenu : 29 mots : { abandon abeille abriter agiter aide combat combien depuis dernier engager enlever flot grande gris large lien lit lits marier mouche oncle proche table travail travers vent vente vue zeste }
Nombre de mots : 29
Recherche de "abri" : absent
Recherche de "lit"  : présent
Dictionnaire de type rpooc.dic.DictionnaireArbre :
Contenu : 29 mots : { abandon abeille abriter agiter aide combat combien depuis dernier engager enlever flot grande gris large lien lit lits marier mouche oncle proche table travail travers vent vente vue zeste }
Nombre de mots : 29
Recherche de "abri" : absent
Recherche de "lit"  : présent
--------------------------------------------------------
Mots préfixés
--------------------------------------------------------
Dictionnaire de type rpooc.dic.DictionnaireSortedSetChaineSimple :
Mots commençant par "trav"  : 2 mots : { travers travail }
Mots commençant par "travail"  : 1 mot : { travail }
Mots commençant par "travailleurs"  : 0 mot : { }
Mots commençant par "k"  : 0 mot : { }
Mots commençant par "l"  : 4 mots : { lits lien large lit }
Mots commençant par "z"  : 1 mot : { zeste }
Dictionnaire de type rpooc.dic.DictionnaireSortedSetArbreBinaire :
Mots commençant par "trav"  : 2 mots : { travail travers }
Mots commençant par "travail"  : 1 mot : { travail }
Mots commençant par "travailleurs"  : 0 mot : { }
Mots commençant par "k"  : 0 mot : { }
Mots commençant par "l"  : 4 mots : { large lien lit lits }
Mots commençant par "z"  : 1 mot : { zeste }
Dictionnaire de type rpooc.dic.DictionnaireArbre :
Mots commençant par "trav"  : 2 mots : { travail travers }
Mots commençant par "travail"  : 1 mot : { travail }
Mots commençant par "travailleurs"  : 0 mot : { }
Mots commençant par "k"  : 0 mot : { }
Mots commençant par "l"  : 4 mots : { large lien lit lits }
Mots commençant par "z"  : 1 mot : { zeste }
--------------------------------------------------------
Chargement de "dicom.txt" :
--------------------------------------------------------
Dictionnaire de type rpooc.dic.DictionnaireSortedSetChaineSimple :
Nombre de mots : 1364
Recherche de "abri" : présent
Recherche de "lit"  : présent
Dictionnaire de type rpooc.dic.DictionnaireSortedSetArbreBinaire :
Nombre de mots : 1364
Recherche de "abri" : présent
Recherche de "lit"  : présent
Dictionnaire de type rpooc.dic.DictionnaireArbre :
Nombre de mots : 1364
Recherche de "abri" : présent
Recherche de "lit"  : présent
--------------------------------------------------------
Ajout de abri, lit
--------------------------------------------------------
Dictionnaire de type rpooc.dic.DictionnaireSortedSetChaineSimple :
Nombre de mots : 1364
Recherche de "abri" : présent
Recherche de "lit"  : présent
Dictionnaire de type rpooc.dic.DictionnaireSortedSetArbreBinaire :
Nombre de mots : 1364
Recherche de "abri" : présent
Recherche de "lit"  : présent
Dictionnaire de type rpooc.dic.DictionnaireArbre :
Nombre de mots : 1364
Recherche de "abri" : présent
Recherche de "lit"  : présent
--------------------------------------------------------
Suppression de abri, abricot
--------------------------------------------------------
Dictionnaire de type rpooc.dic.DictionnaireSortedSetChaineSimple :
Nombre de mots : 1363
Recherche de "abri" : absent
Recherche de "lit"  : présent
Dictionnaire de type rpooc.dic.DictionnaireSortedSetArbreBinaire :
Nombre de mots : 1363
Recherche de "abri" : absent
Recherche de "lit"  : présent
Dictionnaire de type rpooc.dic.DictionnaireArbre :
Nombre de mots : 1363
Recherche de "abri" : absent
Recherche de "lit"  : présent
--------------------------------------------------------
Mots préfixés
--------------------------------------------------------
Dictionnaire de type rpooc.dic.DictionnaireSortedSetChaineSimple :
Mots commençant par "trav"  : 4 mots : { traverser travailler travers travail }
Mots commençant par "travail"  : 2 mots : { travailler travail }
Mots commençant par "travailleurs"  : 0 mot : { }
Mots commençant par "k"  : 0 mot : { }
Mots commençant par "l"  : 39 mots : { lutter lutte lune lumiere lui lueur lourd loup lorsque lors longtemps long loin loi livrer livre lisser lire ligne lieu lier libre liberte levre lever leur lettre les lequel lentement lendemain leger le larme la lits lien large lit }
Mots commençant par "z"  : 1 mot : { zeste }
Dictionnaire de type rpooc.dic.DictionnaireSortedSetArbreBinaire :
Mots commençant par "trav"  : 4 mots : { travail travailler travers traverser }
Mots commençant par "travail"  : 2 mots : { travail travailler }
Mots commençant par "travailleurs"  : 0 mot : { }
Mots commençant par "k"  : 0 mot : { }
Mots commençant par "l"  : 39 mots : { la large larme le leger lendemain lentement lequel les lettre leur lever levre liberte libre lien lier lieu ligne lire lisser lit lits livre livrer loi loin long longtemps lors lorsque loup lourd lueur lui lumiere lune lutte lutter }
Mots commençant par "z"  : 1 mot : { zeste }
Dictionnaire de type rpooc.dic.DictionnaireArbre :
Mots commençant par "trav"  : 4 mots : { travail travailler travers traverser }
Mots commençant par "travail"  : 2 mots : { travail travailler }
Mots commençant par "travailleurs"  : 0 mot : { }
Mots commençant par "k"  : 0 mot : { }
Mots commençant par "l"  : 39 mots : { la large larme le leger lendemain lentement lequel les lettre leur lever levre liberte libre lien lier lieu ligne lire lisser lit lits livre livrer loi loin long longtemps lors lorsque loup lourd lueur lui lumiere lune lutte lutter }
Mots commençant par "z"  : 1 mot : { zeste }

*/