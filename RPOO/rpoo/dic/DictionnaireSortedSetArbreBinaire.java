package dic;

import col.correction.SortedSetArbreBinaire;

/**
 * Mise en oeuvre de l'interface <tt>Dictionnaire</tt> avec un ensemble ordonné
 * de type <tt>SortedSetArbreBinaire</tt>.
 */
public class DictionnaireSortedSetArbreBinaire extends DictionnaireSortedSet {

	/**
	 * Constructeur : crée un dictionnaire vide à partir d'un ensemble vide.
	 */
	public DictionnaireSortedSetArbreBinaire() {
		super(new SortedSetArbreBinaire<>());
	}

}