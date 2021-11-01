package col;

import java.util.NoSuchElementException;

/**
 * Mise en oeuvre de l'interface <tt>SortedSet</tt> avec une chaîne simple
 * d'objets héritée de <tt>ChaineSet</tt>.
 *
 * @param <E> type des éléments de l'ensemble.
 */
public class SortedSetChaineSimple<E extends Comparable<E>> extends SetChaineSimple<E> implements SortedSet<E> {

	/**
	 * Constructeur : crée un ensemble vide.
	 */
	public SortedSetChaineSimple() {
		super();
	}

	// ------------------------------------------------- Méthodes de SortedSet

	/**
	 * Renvoie s'il existe le plus petit élément de l'ensemble.
	 * 
	 * @return plus petit élément de l'ensemble.
	 * @throws NoSuchElementException si l'ensemble est vide.
	 */
	@Override
	public E first() {
		// EXERCICE 37
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Renvoie s'il existe le plus grand élément de l'ensemble.
	 * 
	 * @return plus grand élément de l'ensemble.
	 * @throws NoSuchElementException si l'ensemble est vide.
	 */
	@Override
	public E last() {
		// EXERCICE 37
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Renvoie l'ensemble des éléments compris entre deux bornes.
	 * 
	 * @param fromElement borne inférieure (valeur incluse).
	 * @param toElement   borne supérieure (valeur exclue).
	 * @return éléments appartenant à [fromElement : toElement[.
	 */
	@Override
	public SortedSet<E> subSet(E fromElement, E toElement) {
		// EXERCICE 37
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

}
