package col;

import java.util.Arrays;
import java.util.NoSuchElementException;

import sdr.correction.ChaineDouble;
import sdr.correction.ChaineSimple;

/**
 * Mise en oeuvre de l'interface <tt>Set</tt> avec une chaîne simple d'objets.
 *
 * @param <E> type des éléments de l'ensemble.
 */
public class SetChaineSimple<E> implements Set<E> {

	/**
	 * Chaîne des éléments de l'ensemble.
	 */
	protected ChaineSimple<E> debut;

	/**
	 * Nombre d'éléments dans l'ensemble.
	 */
	protected int size;

	/**
	 * Constructeur : crée un ensemble vide.
	 */
	public SetChaineSimple() {
		this.debut = null;
		this.size = 0;
	}

	/**
	 * Fournit la représentation littérale d'un ensemble.
	 * 
	 * @return chaîne de caractères de la forme "{ 5 -8 2 }"
	 */
	@Override
	public String toString() {
		// EXERCICE 34
		// TODO à compléter...
		String res ="{ ";
		for (ChaineSimple<E>ch = this.debut; ch != null; ch = ch.suivant) {
			res +=ch.valeur+" ";
		}
		return res +"}"; // <- TODO résultat à mettre à jour
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
		// EXERCICE 36
		// TODO à compléter...
		if(o==this) return true;
		if (o == null) return false;
		if (! (o instanceof Set)) return false;
		SetChaineSimple<E> sc= (SetChaineSimple<E>) o;
		if (sc.size != this.size) return false;
		ChaineSimple<E> ch;
		
		for (ch= this.debut;ch != null; ch=ch.suivant) {
			if (!sc.contains(ch.valeur)) return false;
		}
		return true; // <- TODO résultat à mettre à jour
	}

	// ------------------------------------------------- Méthodes de Collection

	/**
	 * Renvoie le nombre d'éléments de l'ensemble.
	 * 
	 * @return nombre d'éléments de l'ensemble.
	 */
	@Override
	public int size() {
		// EXERCICE 34
		// TODO à compléter...
		return this.size; // <- TODO résultat à mettre à jour
	}

	/**
	 * Vide l'ensemble.
	 */
	@Override
	public void clear() {
		// EXERCICE 34
		// TODO à compléter...
		this.debut=null;
		this.size=0;
		
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
		// EXERCICE 34
		for (ChaineSimple<E> ch = this.debut; ch != null; ch = ch.suivant) {
			if(ch.valeur.equals(e)) {
				return false;
			}
		}
		ChaineSimple<E> c= new ChaineSimple<E>(e);
		if (this.debut==null) this.debut= c;
		else this.debut.insereElementSuivant(e);
		
		this.size++;
		return true;  // <- TODO résultat à mettre à jour
	}

	/**
	 * Supprime si possible un élément de l'ensemble.
	 * 
	 * @param e élément à supprimer.
	 * @return vrai si e a été supprimé, faux sinon.
	 */
	@Override
	public boolean remove(E e) {
		// EXERCICE 34
		// TODO à compléter...
		for (ChaineSimple<E> c = new ChaineSimple<>(null, this.debut); c.suivant != null; c = c.suivant) {
			if (c.suivant.valeur.equals(e)) {
				if (c.suivant == this.debut)
 					 this.debut = this.debut.suivant;
				else c.supprimeElementSuivant();
				this.size--;
				return true;
			}
		}
		return false;
		/*
		// Version avec itérateur :
		for (Iterator<? extends E> it = this.iterator(); it.hasNext();) {
			if (it.next().equals(e)) {
				it.remove();
				return true;
			}
		}
		return false;
		*/
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
		// EXERCICE 34
		// TODO à compléter...
		for (ChaineSimple<E> ch = this.debut; ch != null; ch = ch.suivant) {
			if(ch.valeur.equals(e)) return true;
		}
		return false; // <- TODO résultat à mettre à jour
		/*
		// Version avec itérateur :
		for (Iterator<? extends E> it = this.iterator(); it.hasNext();) {
			if (it.next().equals(e))
				return true;
		}
		return false;
		*/
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
		int i=0;
		E[]o = Arrays.copyOf(a,this.size);		
		if (a.length >= this.size) {
			for (ChaineSimple<E> ch = this.debut; ch != null; ch= ch.suivant) {
				a[i]= ch.valeur;
				i++;
			}
			for (int j =i; j < a.length; j++) {
				a[j]= null;
			}
			return a;
		}
		else {
			for (ChaineSimple<E> ch = this.debut; ch != null; ch= ch.suivant) {
				o[i]= ch.valeur;
				i++;
			}
		}
		return o; // <- TODO résultat à mettre à jour
	}
	// ------------------------------------------------- Méthodes de Set

	/**
	 * Renvoie un nouvel itérateur positionné sur l'ensemble.
	 * 
	 * @return nouvel itérateur.
	 */
	@Override
	public Iterator<E> iterator() {
		return new SetChaineSimpleIterator();
	}

	/**
	 * Itérateur de <tt>SetChaineSimple</tt>.
	 */
	private class SetChaineSimpleIterator implements Iterator<E> {

		/**
		 * Chaîne dont l'élément suivant est le prochain élément à lire.
		 */
		@SuppressWarnings("unused")
		private ChaineSimple<E> prevNext;

		/**
		 * Chaîne dont l'élément suivant est le prochain élément à supprimer.
		 */
		@SuppressWarnings("unused")
		private ChaineSimple<E> prevRemove;

		/**
		 * Constructeur : positionne l'itérateur au début de la chaîne.
		 */
		public SetChaineSimpleIterator() {
			this.prevNext = new ChaineSimple<>(null, SetChaineSimple.this.debut);
			this.prevRemove = null;
		}

		/**
		 * Indique si l'itérateur peut être déplacé.
		 * 
		 * @return vrai l'itérateur n'est pas déjà en fin de parcours, faux sinon.
		 */
		@Override
		public boolean hasNext() {
			// EXERCICE 35
			// TODO à compléter...
			if (this.prevNext.suivant !=null) return true;
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
			// EXERCICE 35
			// TODO à compléter...
			if (!this.hasNext()) throw new NoSuchElementException();
			this.prevRemove= this.prevNext;
			this.prevNext= this.prevNext.suivant;
			return this.prevNext.valeur; // <- TODO résultat à mettre à jour
		}

		/**
		 * Supprime le dernier élément renvoyé par next.
		 * 
		 * @throws IllegalStateException si next n'a pas été exécuté avant, ou si remove
		 *                               a déjà été exécuté depuis.
		 */
		@Override
		public void remove() {
			if (this.prevRemove == null)
				throw new IllegalStateException();
			if (this.prevNext == SetChaineSimple.this.debut) {
				SetChaineSimple.this.debut = SetChaineSimple.this.debut.suivant;
				this.prevNext = this.prevRemove;
				this.prevNext.supprimeElementSuivant();
			}
			else {
				this.prevRemove.supprimeElementSuivant();
				this.prevNext = this.prevRemove;
			}
			this.prevRemove = null;
			SetChaineSimple.this.size--;
		}
	}

}
