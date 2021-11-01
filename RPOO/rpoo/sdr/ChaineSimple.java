package sdr;

import java.util.function.Function;

/**
 * Chaîne simple d'objets.
 * <p>
 * Une chaîne simple d'objets est un type inductif qui agrège deux composantes :
 * la valeur stockée au début de la chaîne (objet quelconque) et l'éventuelle
 * suite de la chaîne (autre chaine simple).
 * 
 * @param <E> type des éléments stockés.
 */
public class ChaineSimple<E> {

	/**
	 * Element stocké en début de chaîne.
	 */
	public E valeur;

	/**
	 * Suite éventuelle de la chaîne.
	 */
	public ChaineSimple<E> suivant;

	/**
	 * Constructeur de chaîne.
	 * 
	 * @param valeur  valeur à stocker au début de la chaîne.
	 * @param suivant suite de la chaîne.
	 */
	public ChaineSimple(E valeur, ChaineSimple<E> suivant) {
		this.valeur = valeur;
		this.suivant = suivant;
	}

	/**
	 * Constructeur de chaîne réduite à une valeur.
	 * 
	 * @param valeur unique valeur à stocker dans la chaîne.
	 */
	public ChaineSimple(E valeur) {
		this(valeur, null);
	}

	/**
	 * Ajoute un élément à droite du premier élément de la chaîne.
	 * 
	 * @param valeur valeur à ajouter.
	 * @return nouvelle chaîne suivant le premier élément.
	 */
	public ChaineSimple<E> insereElementSuivant(E valeur) {
		// EXERCICE 2
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Supprime s'il existe l'élément situé à droite du premier élément de la
	 * chaîne.
	 * 
	 * @return chaîne réduite à l'élémént supprimé s'il existe, ou null sinon.
	 */
	public ChaineSimple<E> supprimeElementSuivant() {
		// EXERCICE 2
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	@Override
	public String toString() {
		// EXERCICE 1
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	// ------------------------------------------------- Opérations Chaines simples

	/**
	 * Met à jour tous les éléments de la chaîne.
	 * 
	 * @param f fonction à appliquer.
	 */
	public void maj(Function<E, E> f) {
		// EXERCICE 4
		// TODO à compléter...
	}

	/**
	 * Crée une nouvelle chaîne en appliquant une fonction à tous les éléments de la
	 * chaîne.
	 * 
	 * @param f fonction à appliquer.
	 * @param   <F> type des éléments stockés dans le résultat.
	 * @return nouvelle chaîne dont les éléments correspondent aux résultats de
	 *         <tt>f</tt> appliquée à tous les éléments de la chaîne.
	 */
	public <F> ChaineSimple<F> map(Function<E, F> f) {
		// EXERCICE 4
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Crée une nouvelle chaîne dont l'ordre des éléments est inversé.
	 * 
	 * @return chaîne inversée.
	 */
	public ChaineSimple<E> inverse() {
		// EXERCICE 3
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

}
