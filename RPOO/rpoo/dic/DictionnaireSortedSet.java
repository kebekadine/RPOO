package dic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import col.correction.Iterator;
import col.correction.SortedSet;

/**
 * Mise en oeuvre des méthodes de l'interface <tt>Dictionnaire</tt> avec un
 * ensemble ordonné de type <tt>SortedSet</tt>.
 */
public class DictionnaireSortedSet implements Dictionnaire {

	/**
	 * Ensemble ordonné de mots.
	 */
	protected SortedSet<String> set;

	/**
	 * Constructeur : crée un dictionnaire à partir d'un ensemble de mots fourni en
	 * paramètre.
	 * 
	 * @param set ensemble des mots du dictionnaire.
	 */
	protected DictionnaireSortedSet(SortedSet<String> set) {
		this.set = set;
	}
	
	/**
	 * Ajoute un mot au dictionnaire.
	 * <p>
	 * 
	 * Un mot ne peut être ajouté que s'il existe (paramètre null interdit), s'il
	 * contient au moins une lettre, et s'il n'est pas déjà présent dans le
	 * dictionnaire.
	 * 
	 * @param mot mot à ajouter.
	 * @return vrai si le mot a pu être ajouté, faux sinon.
	 */
	@Override
	public boolean ajoute(String mot) {
		// EXERCICE 41
		// TODO à compléter...
		return false; // <- TODO résultat à mettre à jour
	}

	/**
	 * Supprime un mot du dictionnaire.
	 * 
	 * @param mot mot à suprimer.
	 * @return vrai si le mot a pu être supprimé, faux sinon.
	 */
	@Override
	public boolean supprime(String mot) {
		// EXERCICE 41
		// TODO à compléter...
		return false; // <- TODO résultat à mettre à jour
	}

	/**
	 * Indique le nombre de mots dans le dictionnaire.
	 * 
	 * @return nombre de mots dans le dictionnaire.
	 */
	@Override
	public int nbMots() {
		// EXERCICE 41
		// TODO à compléter...
		return -1; // <- TODO résultat à mettre à jour
	}

	/**
	 * Indique si un mot est présent dans le dictionnaire.
	 * 
	 * @param mot mot à rechercher.
	 * @return vrai si mot est présent, faux sinon.
	 */
	@Override
	public boolean existe(String mot) {
		// EXERCICE 41
		// TODO à compléter...
		return false; // <- TODO résultat à mettre à jour
	}

	/**
	 * Renvoie un nouveau dictionnaire restreint aux mots commençant par un certain
	 * préfixe.
	 * 
	 * @param mot préfixe recherché.
	 * @return dictionnaire éventuellement vide des mots commençant par le préfixe
	 *         fourni.
	 * @throws IllegalArgumentException si mot est null ou si sa taille vaut 0.
	 */
	@Override
	public Dictionnaire prefixe(String mot) {
		// EXERCICE 41
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	//------------------------------------------------------------------------------

	/**
	 * Sauvegarde les mots du dictionnaire dans un fichier texte.
	 * <p>
	 * 
	 * Dans le fichier créé, chaque ligne correspond exactement à un mot du
	 * dictionnaire.
	 * 
	 * @param fichier nom du fichier à créer.
	 * @throws IOException soulevée quand le fichier n'est pas accessible.
	 */
	@Override
	public void sauvegarde(String fichier) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(fichier));
		for (Iterator<String> it = this.set.iterator(); it.hasNext();)
			bw.write(it.next() + "\n");
		bw.close();
	}

	@Override
	public String toString() {
		int s = this.set.size();
		return s + " mot" + (s > 1 ? "s" : "") + " : " + this.set.toString();
	}

}
