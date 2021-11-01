package col;

import java.util.NoSuchElementException;

import sdr.correction.ArbreBinaire;
import sdr.correction.ChaineSimple;

/**
 * Mise en oeuvre de l'interface <tt>SortedSet</tt> avec un arbre binaire de
 * recherche.
 *
 * @param <E> type des éléments de l'ensemble.
 */
public class SortedSetArbreBinaire<E extends Comparable<E>> implements SortedSet<E> {

	/**
	 * Arbre des éléments de l'ensemble.
	 */
	protected ArbreBinaire<E> arbre;

	/**
	 * Nombre d'éléments dans l'ensemble.
	 */
	protected int size;

	/**
	 * Constructeur : crée un ensemble vide.
	 */
	public SortedSetArbreBinaire() {
		this.arbre = null;
		this.size = 0;
	}

	/**
	 * Fournit la représentation littérale d'un ensemble.
	 * 
	 * @return chaîne de caractères de la forme "{ 5 -8 2 }"
	 */
	@Override
	public String toString() {
		// EXERCICE 38
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Critère d'égalité de deux ensembles : deux ensembles sont égaux si et
	 * seulement s'ils contiennent exactement les mêmes éléments.
	 * 
	 * @param o objet à comparer à this
	 * @return résultat du critère d'égalité ou false si o est null ou n'a pas le
	 *         type Set
	 */
	@Override
	public boolean equals(Object o) {
		// EXERCICE 38
		// TODO à compléter...
		return false; // <- TODO résultat à mettre à jour
	}

	// ------------------------------------------------- Méthodes de Collection

	/**
	 * Renvoie le nombre d'éléments de l'ensemble.
	 * 
	 * @return nombre d'éléments de l'ensemble.
	 */
	@Override
	public int size() {
		// EXERCICE 38
		// TODO à compléter...
		return -1; // <- TODO résultat à mettre à jour
	}

	/**
	 * Vide l'ensemble.
	 */
	@Override
	public void clear() {
		// EXERCICE 38
		// TODO à compléter...
	}

	/**
	 * Ajoute si possible un élément à l'ensemble (il ne doit pas être déjà
	 * présent).
	 * 
	 * @param e élément à ajouter.
	 * @return vrai si e a été ajouté, faux sinon.
	 */
	@Override
	public boolean add(E e) {
		// EXERCICE 38
		// TODO à compléter...
		return false; // <- TODO résultat à mettre à jour
	}

	/**
	 * Supprime si possible un élément de l'ensemble.
	 * 
	 * @param e élément à supprimer.
	 * @return vrai si e a été supprimé, faux sinon.
	 */
	@Override
	public boolean remove(E e) {
		// EXERCICE 38
		// TODO à compléter...
		return false; // <- TODO résultat à mettre à jour
	}

	/**
	 * Indique si l'ensemble contient un certain élément.
	 * <p>
	 * Plus formellement, renvoie vrai si et seulement si l'ensemble contient au
	 * moins un élément <tt>e</tt> tel que
	 * <tt>(o==null ? e==null : o.equals(e))</tt>.
	 *
	 * @param e élément recherché.
	 * @return vrai si e est présent, faux sinon.
	 */
	@Override
	public boolean contains(E e) {
		// EXERCICE 38
		// TODO à compléter...
		return false; // <- TODO résultat à mettre à jour
	}

	/**
	 * Renvoie un tableau contenant tous les éléments de l'ensemble.
	 *
	 * <p>
	 * Si l'ensemble peut être contenu dans le tableau fourni en paramètre, alors le
	 * tableau est mis à jour et renvoyé en résultat. Les premières cases du tableau
	 * contiennent les éléments de l'ensemble. Les autres contiennent null.
	 *
	 * <p>
	 * Si l'ensemble ne peut pas être contenu dans le tableau, alors un nouveau
	 * tableau est créé et renvoyé en résultat. Il contient exactement tous les
	 * éléments de l'ensemble.
	 *
	 * @param a tableau à mettre à jour s'il est assez grand.
	 * @return tableau contenant les éléments de l'ensemble.
	 */
	@Override
	public E[] toArray(E[] a) {
		// EXERCICE 38
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	// ------------------------------------------------- Méthodes de Set
	
	@Override
	public Iterator<E> iterator() {
		return new SortedSetArbreBinaireIterator();
	}
	/**
	 * Itérateur dédié à la mise en oeuvre de <tt>SortedSetArbreBinaire</tt>.
	 */
	private class SortedSetArbreBinaireIterator implements Iterator<E> {

		/**
		 * Chaîne des valeurs à parcourir.
		 */
		@SuppressWarnings("unused")
		private ChaineSimple<E> valeurs;

		/**
		 * Chaîne dont la valeur peut être supprimée au prochain appel de remove.
		 */
		@SuppressWarnings("unused")
		private ChaineSimple<E> remove;

		/**
		 * Constructeur : positionne l'itérateur au début de la chaîne.
		 */
		public SortedSetArbreBinaireIterator() {
			if (SortedSetArbreBinaire.this.arbre == null)
				this.valeurs = null;
			else
				this.valeurs = SortedSetArbreBinaire.this.arbre.infixe();
			this.remove = null;
		}

		/**
		 * Indique si l'itérateur peut être déplacé.
		 * 
		 * @return vrai l'itérateur n'est pas déjà en fin de parcours, faux sinon.
		 */
		@Override
		public boolean hasNext() {
			// EXERCICE 39
			// TODO à compléter...
			return false; // <- TODO résultat à mettre à jour
		}

		/**
		 * Déplace l'itérateur et renvoie la valeur située devant l'itérateur avant son
		 * déplacement.
		 * 
		 * @return valeur située devant l'itérateur avant son déplacement.
		 * @throws java.util.NoSuchElementException si l'itérateur est déjà en fin de
		 *         parcours
		 */
		@Override
		public E next() {
			// EXERCICE 39
			// TODO à compléter...
			return null; // <- TODO résultat à mettre à jour
		}

		/**
		 * Supprime le dernier élément renvoyé par next.
		 * 
		 * @throws IllegalStateException si next n'a pas été exécuté avant, ou si remove
		 *                               a déjà été exécuté depuis.
		 */
		@Override
		public void remove() {
			// EXERCICE 39
			// TODO à compléter...
		}

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
		// EXERCICE 40
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
		// EXERCICE 40
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
		// EXERCICE 40
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

}
