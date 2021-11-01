package sdr;

import java.util.function.Function;

/**
 * Chaîne double d'objets.
 * <p>
 * Une chaîne double d'objets est un type inductif qui agrège trois composantes
 * : une valeur stockée (objet quelconque), l'éventuelle suite de la chaîne
 * (autre chaine double), et l'éventuelle chaîne précédente (autre chaine
 * double).
 * 
 * @param <E> type des éléments stockés.
 */
public class ChaineDouble<E> {

	/**
	 * Element stocké.
	 */
	public E valeur;

	/**
	 * Suite éventuelle de la chaîne.
	 */
	public ChaineDouble<E> suivant;

	/**
	 * Eventuelle chaîne précédente.
	 */
	public ChaineDouble<E> precedent;

	/**
	 * Constructeur de chaîne réduite à une valeur.
	 * 
	 * @param valeur unique valeur à stocker dans la chaîne.
	 */
	public ChaineDouble(E valeur) {
		this.valeur = valeur;
		this.suivant = null;
		this.precedent = null;
	}

	/**
	 * Ajoute un élément à droite de l'élément stocké.
	 * 
	 * @param valeur valeur à ajouter.
	 * @return nouvelle chaîne suivante.
	 */
	public ChaineDouble<E> insereElementSuivant(E valeur) {
		// EXERCICE 5
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Ajoute un élément à gauche de l'élément stocké.
	 * 
	 * @param valeur valeur à ajouter.
	 * @return nouvelle chaîne précédente.
	 */
	public ChaineDouble<E> insereElementPrecedent(E valeur) {
		// EXERCICE 5
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Supprime s'il existe l'élément situé à droite de l'élément stocké.
	 * 
	 * @return chaîne réduite à l'élémént supprimé s'il existe, ou null sinon.
	 */
	public ChaineDouble<E> supprimeElementSuivant() {
		// EXERCICE 5
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Supprime s'il existe l'élément situé à gauche de l'élément stocké.
	 * 
	 * @return chaîne réduite à l'élémént supprimé s'il existe, ou null sinon.
	 */
	public ChaineDouble<E> supprimeElementPrecedent() {
		// EXERCICE 5
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	@Override
	public String toString() {
		// EXERCICE 6
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	// ------------------------------------------------- Opérations Chaines doubles

	/**
	 * Met à jour tous les éléments de la chaîne.
	 * 
	 * @param f fonction à appliquer.
	 */
	public void maj(Function<E, E> f) {
		// EXERCICE 8
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
	public <F> ChaineDouble<F> map(Function<E, F> f) {
		// EXERCICE 8
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Inverse l'ordre des éléments de la chaîne.
	 */
	public void inverse() {
		// EXERCICE 7
		// TODO à compléter...
	}

}
