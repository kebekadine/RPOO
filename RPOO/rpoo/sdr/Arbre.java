 package sdr;

/**
 * Arbre quelconque.
 * <p>
 * Un arbre quelconque est un type inductif qui agrège trois composantes : une
 * valeur stockée dans la racine (objet quelconque), un éventuel fils (autre
 * arbre) et un éventuel frère (autre arbre).
 * 
 * @param <E> type des éléments stockés.
 */
public class Arbre<E> implements Cloneable {

	/**
	 * Element stocké dans la racine de l'arbre.
	 */
	public E valeur;

	/**
	 * Eventuel fils de l'arbre.
	 */
	public Arbre<E> fils;

	/**
	 * Eventuel frère de l'arbre.
	 */
	public Arbre<E> frere;

	/**
	 * Constructeur d'arbre.
	 * 
	 * @param valeur valeur de la racine.
	 * @param fils   fils de l'arbre.
	 * @param frere  frère de l'arbre.
	 */
	public Arbre(E valeur, Arbre<E> fils, Arbre<E> frere) {
		this.valeur = valeur;
		this.fils = fils;
		this.frere = frere;
	}

	/**
	 * Constructeur d'arbre réduit à une feuille.
	 * 
	 * @param valeur valeur de la racine.
	 */
	public Arbre(E valeur) {
		this(valeur, null, null);
	}

	/**
	 * Indique si l'arbre est réduit à une feuille.
	 * 
	 * @return vrai si l'arbre est une feuille, faux sinon.
	 */
	public boolean feuille() {
		// EXERCICE 22
		// TODO à compléter...
		return false; // <- TODO résultat à mettre à jour
	}

	/**
	 * Ajoute un élément en position de fils.
	 * 
	 * @param valeur valeur à ajouter.
	 * @return nouveau fils.
	 */
	public Arbre<E> insereElementFils(E valeur) {
		// EXERCICE 23
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Ajoute un élément en position de frère.
	 * 
	 * @param valeur valeur à ajouter.
	 * @return nouveau frère.
	 */
	public Arbre<E> insereElementFrere(E valeur) {
		// EXERCICE 23
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Supprime s'il existe l'arbre situé en position de fils.
	 * 
	 * @return arbre supprimé s'il existe, ou null sinon.
	 */
	public Arbre<E> supprimeFils() {
		// EXERCICE 23
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Supprime s'il existe l'arbre situé en position de frère.
	 * 
	 * @return arbre supprimé s'il existe, ou null sinon.
	 */
	public Arbre<E> supprimeFrere() {
		// EXERCICE 23
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	// ------------------------------------------------- Opérations Arbre quelconque

	@Override
	public String toString() {
		class UtilRepresentation {
			ChaineSimple<String> niveaux(Arbre<?> a) {
				String etiq = a.valeur.toString();
				if (etiq.length()==0) etiq = " ";
				if (a.feuille()) return new ChaineSimple<>(etiq);
				ChaineSimple<String> accumulateur = niveaux(a.fils);
				ChaineSimple<String> finDeChaine = accumulateur;
				String traits = (a.fils.frere == null ? "|" : "+");
				int largeurTrait = accumulateur.valeur.length();
				for (Arbre<?> enfant = a.fils.frere; enfant != null; enfant = enfant.frere) {
					int largeur1 = accumulateur.valeur.length();
					ChaineSimple<String> afusionner = niveaux(enfant);
					int largeur2 = afusionner.valeur.length();
					traits += new String(new char[largeurTrait]).replace('\0', '-') + "+";
					ChaineSimple<String> cacc, cafu;
					for (cacc = accumulateur, cafu = afusionner; cacc != null
							&& cafu != null; cacc = cacc.suivant, cafu = cafu.suivant) {
						cacc.valeur = cacc.valeur + " " + cafu.valeur;
						finDeChaine = cacc;
					}
					for (; cacc != null; cacc = cacc.suivant) {
						cacc.valeur = cacc.valeur + new String(new char[largeur2 + 1]).replace('\0', ' ');
						finDeChaine = cacc;
					}
					for (cacc = finDeChaine; cafu != null; cafu = cafu.suivant)
						finDeChaine = cacc = cacc.insereElementSuivant(
								new String(new char[largeur1 + 1]).replace('\0', ' ') + cafu.valeur);
					largeurTrait = largeur2;
				}
				int taille = finDeChaine.valeur.length() - traits.length();
				traits += new String(new char[taille < 0 ? 0 : taille]).replace('\0', ' ');
				accumulateur = new ChaineSimple<>(traits, accumulateur);
				taille = finDeChaine.valeur.length() - etiq.length();
				accumulateur = new ChaineSimple<>(
						etiq + new String(new char[taille < 0 ? 0 : taille]).replace('\0', ' '), accumulateur);
				return accumulateur;
			}
		}
		UtilRepresentation ur = new UtilRepresentation();
		String res = "";
		for (ChaineSimple<String> c = ur.niveaux(this); c != null; c = c.suivant)
			res += c.valeur + "\n";
		return res;
	}

	@Override
	public Arbre<E> clone() {
		// EXERCICE 24
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Indique le nombre de fils de l'arbre.
	 * 
	 * @return nombre de fils.
	 */
	public int nbFils() {
		// EXERCICE 22
		// TODO à compléter...
		return -1; // <- TODO résultat à mettre à jour
	}

	/**
	 * Indique le nombre de feuilles dans l'arbre.
	 * 
	 * @return nombre de feuilles dans l'arbre.
	 */
	public int nbFeuilles() {
		// EXERCICE 22
		// TODO à compléter...
		return -1; // <- TODO résultat à mettre à jour
	}

	/**
	 * Indique le nombre d'éléments stockés dans l'arbre.
	 * 
	 * @return nombre d'éléments dans l'arbre.
	 */
	public int nbElements() {
		// EXERCICE 22
		// TODO à compléter...
		return -1; // <- TODO résultat à mettre à jour
	}
	
	// ------------------------------------------------- Opérations Arbre de mots

	/**
	 * Détermine le préfixe commun entre un mot fourni en paramètre et le mot stocké
	 * dans la racine de l'arbre.
	 * 
	 * @param mot mot dont on recherche un préfixe commun avec la racine
	 * @return tableau de 3 chaines de caractères avec dans l'ordre : le préfixe
	 *         commun, le suffixe du mot et le suffixe de la racine
	 */
	public String[] factorise(String mot) {
		// EXERCICE 25
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}
	
	/**
	 * Ajoute un mot dans l'arbre de mots.
	 * 
	 * @param mot mot à ajouter
	 * @return vrai si le mot a été ajouté, faux sinon.
	 */
	public boolean ajouteMot(String mot) {
		// EXERCICE 26
		// TODO à compléter...
		return false; // <- TODO résultat à mettre à jour
	}
	
	/**
	 * Supprime un mot de l'arbre de mots.
	 * 
	 * @param mot mot à supprimer.
	 * @return vrai si le mot a été supprimé, faux sinon.
	 */
	public boolean supprimeMot(String mot) {
		// EXERCICE 27
		// TODO à compléter...
		return false; // <- TODO résultat à mettre à jour
	}
	
	/**
	 * Indique si un mot est présent dans l'arbre.
	 * 
	 * @param mot mot à rechercher.
	 * @return vrai si mot est présent, faux sinon.
	 */
	public boolean existeMot(String mot) {
		// EXERCICE 28
		// TODO à compléter...
		return false; // <- TODO résultat à mettre à jour
	}
	
	/**
	 * Renvoie s'il existe l'arbre se situant à l'extrémité d'un chemin
	 * correspondant à un mot.
	 * 
	 * @param mot chemin à parcourir
	 * @return arbre situé au bout du mot s'il existe, ou null sinon
	 */
	public Arbre<String> boutDuChemin(String mot) {
		// EXERCICE 28
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Reconstruit les mots de l'arbre.
	 * 
	 * @param accumulateur mot en cours de reconstruction.
	 * @return ensemble des mots construits à partir de l'accumulateur et de tous
	 *         les chemins de l'arbre.
	 */
	public ChaineSimple<String> recupMots(String accumulateur) {
		// EXERCICE 29
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}
	
}
