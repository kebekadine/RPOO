package dic.tests;

import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import dic.Dictionnaire;
import dic.DictionnaireArbre;
import sdr.correction.Arbre;

//------------------------ Classe dérivée dédiée aux tests

class DictionnaireArbreTEST extends DictionnaireArbre {
	@SafeVarargs
	DictionnaireArbreTEST(String... val) {
		super();
		if (val.length > 0) {
			this.arbre = new Arbre<>(val[0]); 
			for (int i=1; i<val.length; i++)
				this.arbre.ajouteMot(val[i]);
		}
	}

	Arbre<String> getArbre() {
		return this.arbre;
	}
	
	int nbMotsTEST() {
		if (this.arbre == null)
			return 0;
		return this.arbre.nbFeuilles();
	}
}

//------------------------ Classe de tests

public class JUnitDictionnaireArbre extends JUnitDictionnaire {

	// ------------------------ Fonctions auxiliaires

	private static boolean ko(String m) {
		System.out.println("[" + new Exception().getStackTrace()[2].getMethodName() + "] " + m);
		return false;
	}
	
	protected Dictionnaire creer(String... s) {
		return new DictionnaireArbreTEST(s);
	}

	@Override
	protected Dictionnaire creerFile(String f) {
		try {
			return new DictionnaireArbreTEST(Files.lines(Paths.get(f)).collect(Collectors.toList()).toArray(new String[0]));
		} catch (IOException e) {
			throw new IOError(e);
		}
	}

	@Override
	protected boolean valideDictionnaire(Dictionnaire d, String... mots) {
		boolean isda = d instanceof DictionnaireArbreTEST;
		int nbm = (isda ? ((DictionnaireArbreTEST) d).nbMotsTEST() : d.nbMots());
		int nbma = mots.length;
		if (nbm != nbma)
			return ko("nombres de mots : " + nbm + ", nombre attendu : " + nbma);
		for (String m : mots)
			if (!(isda ? ((DictionnaireArbreTEST) d).getArbre().existeMot(m) : d.existe(m)))
				return ko("mot absent : " + m);
		return true;
	}
}
