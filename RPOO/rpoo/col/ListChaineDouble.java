package col;

import java.util.Arrays;

import sdr.correction.ChaineDouble;

/**
 * Mise en oeuvre de l'interface <tt>List</tt> avec une chaîne double d'objets.
 *
 * @param <E> type des éléments de la liste.
 */
public class ListChaineDouble<E> implements List<E> {

	/**
	 * Début de la chaîne des éléments de la liste.
	 */
	protected ChaineDouble<E> debut;

	/**
	 * Fin de la chaîne des éléments de la liste.
	 */
	protected ChaineDouble<E> fin;

	/**
	 * Dernier chaînon atteint par add, get, set ou remove.
	 */
	protected ChaineDouble<E> courant;

	/**
	 * Indice du chainon courant.
	 */
	protected int indiceCourant;

	/**
	 * Nombre d'éléments dans la liste.
	 */
	protected int size;

	/**
	 * Constructeur : crée une liste vide.
	 */
	public ListChaineDouble() {
		this.debut = null;
		this.fin = null;
		this.courant = null;
		this.indiceCourant = -1;
		this.size = 0;
	}

	/**
	 * Fournit la représentation littérale d'une liste.
	 * 
	 * @return chaîne de caractères de la forme "( 5 -8 2 )"
	 */
	@Override
	public String toString() {
		// EXERCICE 30
		// TODO à compléter...
		String res="( ";
		for (ChaineDouble<E>ch = this.debut; ch!=null; ch=ch.suivant ) {
			res += ch.valeur+" ";
		}
		return res+=")"; // <- TODO résultat à mettre à jour
	}

	/**
	 * Critère d'égalité de deux listes : deux listes sont égales si et seulement si
	 * elles stockent les mêmes valeurs dans le même ordre.
	 * 
	 * @param o objet à comparer à this
	 * @return résultat du critère d'égalité ou false si o est null ou n'a pas le
	 *         type List
	 */
	@Override
	public boolean equals(Object o) {
		// EXERCICE 32
		// TODO à compléter...
		if (o== null) return false;
		if (this== o) return true;
		if (! (o instanceof List)) return false;
		ListChaineDouble<E>ch = (ListChaineDouble<E>) o;
		if (ch.size() != this.size) return false;
		ChaineDouble<E>c;
		int i;
		for (i = 0, c= this.debut; i < ch.size(); i++, c=c.suivant) {
			if (!c.valeur.equals(ch.get(i))) 
				return false;
		}
		return true; // <- TODO résultat à mettre à jour
	}

	// ------------------------------------------------- Méthode utilitaire

	/**
	 * Positionnement du chainon courant à un indice valide.
	 * 
	 * @param i indice valide d'élément à atteindre.
	 */
	protected void positionne(int i) {
		// EXERCICES 30 ET 33
		// TODO à compléter au début (ex. 30)...
		// TODO à mettre à jour à la fin (ex. 33)...
		for (this.courant = this.debut, this.indiceCourant = 0; this.indiceCourant < i; this.indiceCourant++)
			this.courant = this.courant.suivant;
	}

	// ------------------------------------------------- Méthodes de Collection

	/**
	 * Renvoie le nombre d'éléments de la liste.
	 * 
	 * @return nombre d'éléments de la liste.
	 */
	@Override
	public int size() {
		// EXERCICE 31
		// TODO à compléter...
		return this.size; // <- TODO résultat à mettre à jour
	}

	/**
	 * Vide la liste.
	 */
	@Override
	public void clear() {
		// EXERCICE 31
		// TODO à compléter...
		this.debut=null;
		this.courant=null;
		this.indiceCourant=-1;
		this.fin=null;
		this.size=0;
	}

	/**
	 * Ajoute un élément à la fin de la liste.
	 * 
	 * @param e élément à ajouter.
	 * @return vrai.
	 */
	@Override
	public boolean add(E e) {
		// EXERCICE 32
		// TODO à compléter...
		this.add(this.size, e);
		return true;
	}

	/**
	 * Supprime si possible un élément de la liste (le premier s'il y en a plusieurs
	 * identiques).
	 * 
	 * @param e élément à supprimer.
	 * @return vrai si e a été supprimé, faux sinon.
	 */
	@Override
	public boolean remove(E e) {
		// EXERCICE 32
		// TODO à compléter...
		int i = this.indexOf(e);
		if (i !=-1) {
			this.remove(i);
			return true;
		}
		return false; // <- TODO résultat à mettre à jour
	}

	/**
	 * Indique si la liste contient un certain élément.
	 * <p>
	 * Plus formellement, renvoie vrai si et seulement si la liste contient au
	 * moins un élément <tt>e</tt> tel que
	 * <tt>(o==null ? e==null : o.equals(e))</tt>.
	 *
	 * @param e élément recherché.
	 * @return vrai si e est présent, faux sinon.
	 */
	@Override
	public boolean contains(E e) {
		// EXERCICE 32
		// TODO à compléter...
		if (this.size==0) return false;
		for (ChaineDouble<E> ch = this.debut; ch != null; ch = ch.suivant) {
			if (ch.valeur.equals(e)) return true;
		}
		return false; // <- TODO résultat à mettre à jour
	}

	/**
	 * Renvoie un tableau contenant tous les éléments de la liste.
	 *
	 * <p>
	 * Si la liste peut être contenue dans le tableau fourni en paramètre, alors le
	 * tableau est mis à jour et renvoyé en résultat. Les premières cases du tableau
	 * contiennent les éléments de la liste. Les autres contiennent null.
	 *
	 * <p>
	 * Si la liste ne peut pas être contenue dans le tableau, alors un nouveau
	 * tableau est créé et renvoyé en résultat. Il contient exactement tous les
	 * éléments de la liste.
	 *
	 * @param a tableau à mettre à jour s'il est assez grand.
	 * @return tableau contenant les éléments de la liste.
	 */
	@Override
	public E[] toArray(E[] a) {
		// EXERCICE 32
		// TODO à compléter...
		int i=0;
		Object []o= new Object[this.size];
		//if (this.size==0) return null;
		if (a.length >= this.size) {
			for (ChaineDouble<E> ch = this.debut; ch != null; ch= ch.suivant) {
				a[i]= ch.valeur;
				i++;
			}
			for (int j =i; j < a.length; j++) {
				a[j]= null;
			}
			return a;
		}
		else {
			for (ChaineDouble<E> ch = this.debut; ch != null; ch= ch.suivant) {
				o[i]= ch.valeur;
				i++;
			}
		}
		return (E[]) o; // <- TODO résultat à mettre à jour
	}

	// ------------------------------------------------- Méthodes de List

	/**
	 * Renvoie s'il existe l'élément situé à un certain indice.
	 * 
	 * @param index indice de l'élément recherché.
	 * @return élément d'indice index.
	 * @throws IndexOutOfBoundsException si l'indice est invalide
	 *                                   (<tt>index &lt; 0 || index &ge; size()</tt>)
	 */
	@Override
	public E get(int index) {
		// EXERCICE 31
		// TODO à compléter...
		if (index >= this.size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		this.positionne(index);
		
		return this.courant.valeur; // <- TODO résultat à mettre à jour
	}

	/**
	 * Met à jour s'il existe l'élément situé à un certain indice.
	 * 
	 * @param index   indice de l'élément recherché.
	 * @param element nouvelle valeur de l'élément d'indice index.
	 * @return ancienne valeur de l'élément d'indice index.
	 * @throws IndexOutOfBoundsException si l'indice est invalide
	 *                                   (<tt>index &lt; 0 || index &ge; size()</tt>)
	 */
	@Override
	public E set(int index, E element) {
		// EXERCICE 31
		// TODO à compléter...
		if (index >= this.size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		this.positionne(index);
		E res= this.courant.valeur;
		this.courant.valeur= element;
		return res; // <- TODO résultat à mettre à jour
	}

	/**
	 * Insère un élément à un certain indice.
	 * 
	 * @param index   indice de l'élément à insérer.
	 * @param element valeur de l'élément à insérer.
	 * @throws IndexOutOfBoundsException si l'indice est invalide
	 *                                   (<tt>index &lt; 0 || index &gt; size()</tt>)
	 */
	@Override
	public void add(int index, E element) {
		// EXERCICE 31
		if (index < 0 || index > this.size)
			throw new IndexOutOfBoundsException();
		if (this.size == 0) {
			this.debut = this.fin = this.courant = new ChaineDouble<>(element);
			this.indiceCourant = 0;
		}
		else if (index == 0) {
			this.debut = this.debut.insereElementPrecedent(element);
			this.indiceCourant++;
		}
		else if (index == this.size) {
			this.fin = this.fin.insereElementSuivant(element);
		}
		else {
			this.positionne(index);
			this.courant = this.courant.insereElementPrecedent(element);
		}
		this.size++;
	}
	/**
	 * Insère à un certain indice tous les éléments d'une autre liste fournie en
	 * paramètre.
	 * 
	 * @param index indice d'insertion.
	 * @param c     liste des éléments à ajouter.
	 * @return vrai si ou moins un élément de c a été ajouté, faux sinon.
	 * @throws IndexOutOfBoundsException si l'indice est invalide
	 *                                   (<tt>index &lt; 0 || index &gt; size()</tt>)
	 */
	@Override
	public boolean addAll(int index, List<E> c) {
		// EXERCICE 31
		// TODO à compléter...
		if (c.isEmpty()) return false;
		for (int i=0; i < c.size(); i++) {
			this.add(index+i, c.get(i));
		}
		return true; // <- TODO résultat à mettre à jour
	}

	/**
	 * Supprime et renvoie s'il existe l'élément situé à un certain indice.
	 * 
	 * @param index indice de l'élément à supprimer.
	 * @return élément supprimé.
	 * @throws IndexOutOfBoundsException si l'indice est invalide
	 *                                   (<tt>index &lt; 0 || index &ge; size()</tt>)
	 */
	@Override
	public E remove(int index) {
		// EXERCICE 31
		// TODO à compléter...
		// EXERCICE 31
				if (index < 0 || index >= this.size)
					throw new IndexOutOfBoundsException();
				E res;
				if (this.size == 1) {
					res = this.debut.valeur;
					this.debut = this.fin = this.courant = null;
					this.indiceCourant = -1;
				}
				else if (index == 0) {
					res = this.debut.valeur;
					this.debut = this.debut.suivant;
					this.debut.supprimeElementPrecedent();
					if (this.indiceCourant == 0) this.courant = this.debut;
					else this.indiceCourant--;
				} 
				else if (index == this.size - 1) {
					res = this.fin.valeur;
					this.fin = this.fin.precedent;
					this.fin.supprimeElementSuivant();
					if (this.indiceCourant == this.size - 1) {
						this.courant = this.fin;
						this.indiceCourant--;
					}
				} 
				else {
					this.positionne(index - 1);
					res = this.courant.suivant.valeur;
					this.courant.supprimeElementSuivant();
				}
				this.size--;
				return res;
			}

	/**
	 * Renvoie l'indice de la première occurrence dans la liste d'un certain objet
	 * fourni en paramètre.
	 * 
	 * @param e objet recherché.
	 * @return plus petit indice de o ou -1.
	 */
	@Override
	public int indexOf(E e) {
		// EXERCICE 31
		// TODO à compléter...
		int i =0;
		for (ChaineDouble<E>ch = this.debut; ch != null; ch = ch.suivant) {
			if (ch.valeur.equals(e)) {
				return i;
			}
			i++;
		}
		return -1; // <- TODO résultat à mettre à jour
	}

	/**
	 * Renvoie l'indice de la dernière occurrence dans la liste d'un certain objet
	 * fourni en paramètre.
	 * 
	 * @param e objet recherché.
	 * @return plus grand indice de o ou -1.
	 */
	@Override
	public int lastIndexOf(E e) {
		// EXERCICE 31
		// TODO à compléter...
		ChaineDouble<E>ch;
		int j= this.size-1;
		for ( ch = this.fin; ch !=null;  ch= ch.precedent) {
			if (ch.valeur.equals(e)) return j;
			else j--;
		
		}
		return -1; // <- TODO résultat à mettre à jour
	}

}
