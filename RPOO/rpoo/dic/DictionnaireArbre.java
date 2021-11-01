package dic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import sdr.correction.Arbre;
import sdr.correction.ChaineSimple;

/**
 * Mise en oeuvre de l'interface <tt>Dictionnaire</tt> avec un arbre de mots.
 */
public class DictionnaireArbre implements Dictionnaire {

	/**
	 * Arbre de mots contenant les mots du dictionnaire.
	 */
	protected Arbre<String> arbre;

	/**
	 * Constructeur : crée un dictionnaire vide à partir d'un arbre de mots réduit à
	 * une feuille.
	 */
	public DictionnaireArbre() {
		this.arbre = null;
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
		// EXERCICE 42
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
		// EXERCICE 42
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
		// EXERCICE 42
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
		// EXERCICE 42
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
		// EXERCICE 42
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
		for (ChaineSimple<String> mots = this.arbre.recupMots(""); mots != null; mots = mots.suivant)
			bw.write(mots.valeur + "\n");
		bw.close();
	}

	@Override
	public String toString() {
		int s = this.nbMots();
		String res = s + " mot" + (s > 1 ? "s" : "") + " : { ";
		if (this.arbre != null)
			for (ChaineSimple<String> mots = this.arbre.recupMots(""); mots != null; mots = mots.suivant)
				res += mots.valeur + " ";
		return res + "}";
	}

}
