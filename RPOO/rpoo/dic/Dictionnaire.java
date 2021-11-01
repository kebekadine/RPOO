package dic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Représente un dictionnaire de mots pour un correcteur orthographique.
 */
public interface Dictionnaire {

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
	boolean ajoute(String mot);

	/**
	 * Supprime un mot du dictionnaire.
	 * 
	 * @param mot mot à suprimer.
	 * @return vrai si le mot a pu être supprimé, faux sinon.
	 */
	boolean supprime(String mot);

	/**
	 * Indique le nombre de mots dans le dictionnaire.
	 * 
	 * @return nombre de mots dans le dictionnaire.
	 */
	int nbMots();

	/**
	 * Indique si un mot est présent dans le dictionnaire.
	 * 
	 * @param mot mot à rechercher.
	 * @return vrai si mot est présent, faux sinon.
	 */
	boolean existe(String mot);

	/**
	 * Renvoie un nouveau dictionnaire restreint aux mots commençant par un certain
	 * préfixe.
	 * 
	 * @param mot préfixe recherché.
	 * @return dictionnaire éventuellement vide des mots commençant par le préfixe
	 *         fourni.
	 * @throws IllegalArgumentException si mot est null ou si sa taille vaut 0.
	 */
	Dictionnaire prefixe(String mot);

	/**
	 * Charge des mots dans le dictionnaire depuis un fichier texte.
	 * <p>
	 * 
	 * Dans le fichier fourni, chaque ligne doit correspondre à un mot à charger.
	 * 
	 * @param fichier nom du fichier à charger.
	 * @throws IOException soulevée quand le fichier n'est pas accessible.
	 */
	default void charge(String fichier) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fichier));
		String ligne;
		while ((ligne = br.readLine()) != null && !Thread.interrupted())
			this.ajoute(ligne);
		br.close();
	}

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
	void sauvegarde(String fichier) throws IOException;

}
