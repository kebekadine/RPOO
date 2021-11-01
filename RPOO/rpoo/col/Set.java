package col;

/**
 * Ensemble : collection ne comportant aucun élément en double.
 * 
 * @param <E> type des éléments de l'ensemble.
 */
public interface Set<E> extends Collection<E> {

	/**
	 * Renvoie un nouvel itérateur positionné sur l'ensemble.
	 * 
	 * @return nouvel itérateur.
	 */
	Iterator<E> iterator();

	/**
	 * Inclusion : indique si l'ensemble contient tous les éléments d'un autre
	 * ensemble fourni en paramètre.
	 * 
	 * @param c ensemble des éléments à rechercher.
	 * @return vrai si l'ensemble contient tous les éléments de c, faux sinon.
	 */
	default boolean containsAll(Set<E> c) {
		// EXERCICE 36
		// TODO à compléter...
		for (Iterator<E>it= c.iterator(); it.hasNext();) {
			if (!this.contains(it.next())) return false;
		}
		return true; // <- TODO résultat à mettre à jour
	}

	/**
	 * Union : ajoute tous les éléments d'un autre ensemble fourni en paramètre.
	 * 
	 * @param c ensemble des éléments à ajouter.
	 * @return vrai si ou moins un élément de c a été ajouté, faux sinon.
	 */
	default boolean addAll(Set<E> c) {
		// EXERCICE 36
		// TODO à compléter...
		int i=0;
		for (Iterator<E>it= c.iterator(); it.hasNext();) {
			if (this.add(it.next()))
				i++;
		}
		if (i> 0) return true;
		return false; // <- TODO résultat à mettre à jour
	}

	/**
	 * Intersection : supprime tous les éléments qui n'apparaissent pas dans un
	 * autre ensemble fourni en paramètre.
	 * 
	 * @param c ensemble des éléments susceptibles d'être conservés.
	 * @return vrai si ou moins un élément a été supprimé, faux sinon.
	 */
	default boolean retainAll(Set<E> c) {
		// EXERCICE 36
		// TODO à compléter...
		int i=0;
		for (Iterator<E>it = this.iterator(); it.hasNext();) {
			E val= it.next();
			if (!c.contains(val)) {
				it.remove();
				i++;
			}
		}
		if (i> 0) return true;
		return false; // <- TODO résultat à mettre à jour
	}

	/**
	 * Différence : supprime tous les éléments qui apparaissent dans un autre
	 * ensemble fourni en paramètre.
	 * 
	 * @param c ensemble des éléments susceptibles d'être supprimés.
	 * @return vrai si ou moins un élément a été supprimé, faux sinon.
	 */
	default boolean removeAll(Set<E> c) {
		int i=0;
		for (Iterator<E>it = this.iterator(); it.hasNext();) {
			E val= it.next();
			if (c.contains(val)) {
				it.remove();
				i++;
			}
		}
		if (i> 0) return true;
		return false; // <- TODO résultat à mettre à jour
	}

}
