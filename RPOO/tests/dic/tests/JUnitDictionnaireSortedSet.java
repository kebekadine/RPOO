package dic.tests;

import col.correction.SortedSet;

//------------------------ Classe de tests

abstract class JUnitDictionnaireSortedSet extends JUnitDictionnaire {

	// ------------------------ Fonctions auxiliaires

	protected static boolean ko(String m) {
		System.out.println("[" + new Exception().getStackTrace()[2].getMethodName() + "] " + m);
		return false;
	}

	protected boolean valideDictionnaire(SortedSet<String> s, String... mots) {
		int nbm = s.size();
		int nbma = mots.length;
		if (nbm != nbma)
			return ko("nombres de mots : " + nbm + ", nombre attendu : " + nbma);
		for (String m : mots)
			if (!s.contains(m))
				return ko("mot absent : " + m);
		return true;
	}
}
