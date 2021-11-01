package dic.tests;

import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import col.correction.SortedSet;
import dic.Dictionnaire;
import dic.DictionnaireSortedSetChaineSimple;

//------------------------ Classe dérivée dédiée aux tests

class DictionnaireSortedSetChaineSimpleTEST extends DictionnaireSortedSetChaineSimple {

	@SafeVarargs
	DictionnaireSortedSetChaineSimpleTEST(String... val) {
		super();
		for (String s : val)
			this.set.add(s);
	}

	SortedSet<String> getSet() {
		return this.set;
	}
}

//------------------------ Classe de tests

public class JUnitDictionnaireSortedSetChaineSimple extends JUnitDictionnaireSortedSet {

	// ------------------------ Fonctions auxiliaires

	protected Dictionnaire creer(String... s) {
		return new DictionnaireSortedSetChaineSimpleTEST(s);
	}

	@Override
	protected Dictionnaire creerFile(String f) {
		try {
			return new DictionnaireSortedSetChaineSimpleTEST(Files.lines(Paths.get(f)).collect(Collectors.toList()).toArray(new String[0]));
		} catch (IOException e) {
			throw new IOError(e);
		}
	}

	@Override
	protected boolean valideDictionnaire(Dictionnaire d, String... mots) {
		if (d instanceof DictionnaireSortedSetChaineSimpleTEST)
			return this.valideDictionnaire(((DictionnaireSortedSetChaineSimpleTEST) d).getSet(), mots);
		int nbm = d.nbMots();
		int nbma = mots.length;
		if (nbm != nbma)
			return ko("nombres de mots : " + nbm + ", nombre attendu : " + nbma);
		for (String m : mots)
			if (!(d.existe(m)))
				return ko("mot absent : " + m);
		return true;
	}

}
