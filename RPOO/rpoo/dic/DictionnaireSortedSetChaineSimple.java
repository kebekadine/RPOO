package dic;

import col.correction.SortedSetChaineSimple;

/**
 * Mise en oeuvre de l'interface <tt>Dictionnaire</tt> avec un ensemble ordonné
 * de type <tt>SortedSetChaineSimple</tt>.
 */
public class DictionnaireSortedSetChaineSimple extends DictionnaireSortedSet {

	/**
	 * Constructeur : crée un dictionnaire vide à partir d'un ensemble vide.
	 */
	public DictionnaireSortedSetChaineSimple() {
		super(new SortedSetChaineSimple<>());
	}

}
