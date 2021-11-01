package sdr;

/**
 * Arbre binaire de recherche AVL.
 * <p>
 * Un arbre binaire de recherche AVL est un type inductif qui agrège cinq
 * composantes : une valeur stockée dans la racine (objet quelconque), la
 * hauteur de l'arbre, l'éventuel fils gauche (autre arbre), l'éventuel fils
 * droit (autre arbre), et l'éventuel père (autre arbre).
 * 
 * @param <E> type des éléments stockés.
 */
public class ArbreBinaire<E extends Comparable<E>> {

	/**
	 * Element stocké dans la racine de l'arbre.
	 */
	public E valeur;

	/**
	 * Hauteur de l'arbre.
	 */
	public int hauteur;

	/**
	 * Eventuel père de l'arbre.
	 */
	public ArbreBinaire<E> pere;

	/**
	 * Eventuel fils gauche de l'arbre.
	 */
	public ArbreBinaire<E> gauche;

	/**
	 * Eventuel fils droit de l'arbre.
	 */
	public ArbreBinaire<E> droit;

	/**
	 * Constructeur d'arbre réduit à une feuille.
	 * 
	 * @param valeur valeur de la racine.
	 */
	public ArbreBinaire(E valeur) {
		this.valeur  = valeur;
		this.hauteur = 0;
		this.pere    = null;
		this.gauche  = null;
		this.droit   = null;
	}

	/**
	 * Indique si l'arbre est réduit à une feuille.
	 * 
	 * @return vrai si l'arbre est une feuille, faux sinon.
	 */
	public boolean feuille() {
		// EXERCICE 9
		// TODO à compléter...
		return false; // <- TODO résultat à mettre à jour
	}

	/**
	 * Fournit le facteur d'équilibrage de l'arbre.
	 * 
	 * @return facteur d'équilibrage.
	 */
	public int facteurEquilibrage() {
		// EXERCICE 21
		// TODO à compléter...
		return 0; // <- TODO résultat à mettre à jour
	}

	/**
	 * Met à jour la hauteur de l'arbre et au besoin de ses ancêtres.
	 */
	public void majHauteur() {
		// EXERCICE 10
		// TODO à compléter...
	}

	/**
	 * Ajoute un élément en position de fils gauche.
	 * 
	 * @param valeur              valeur à ajouter.
	 * @param ancienGaucheAGauche indique si l'ancien fils gauche doit être
	 *                            repositionné à gauche du nouveau fils gauche.
	 * @return nouveau fils gauche.
	 */
	public ArbreBinaire<E> insereElementGauche(E valeur, boolean ancienGaucheAGauche) {
		// EXERCICE 11
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Ajoute un élément en position de fils droit.
	 * 
	 * @param valeur             valeur à ajouter.
	 * @param ancienDroitADroite indique si l'ancien fils droit doit être
	 *                           repositionné à droite du nouveau fils droit.
	 * @return nouveau fils droit.
	 */
	public ArbreBinaire<E> insereElementDroit(E valeur, boolean ancienDroitADroite) {
		// EXERCICE 11
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Ajoute un élément en position de père.
	 * 
	 * @param valeur         valeur à ajouter.
	 * @param thisFilsGauche indique si l'arbre doit être le fils gauche du nouveau
	 *                       père.
	 * @return nouveau père.
	 */
	public ArbreBinaire<E> insereElementPere(E valeur, boolean thisFilsGauche) {
		// EXERCICE 11
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Supprime s'il existe l'élément situé en position de fils gauche, à condition
	 * que le fils gauche n'ait pas deux fils.
	 * 
	 * @return arbre réduit à l'élémént supprimé si la suppression est possible, ou
	 *         null sinon.
	 */
	public ArbreBinaire<E> supprimeElementGauche() {
		// EXERCICE 12
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Supprime s'il existe l'élément situé en position de fils droit, à condition
	 * que le fils droit n'ait pas deux fils.
	 * 
	 * @return arbre réduit à l'élémént supprimé si la suppression est possible, ou
	 *         null sinon.
	 */
	public ArbreBinaire<E> supprimeElementDroit() {
		// EXERCICE 12
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Supprime s'il existe l'élément situé en position de père, à condition
	 * que le père n'ait pas deux fils.
	 * 
	 * @return arbre réduit à l'élémént supprimé si la suppression est possible, ou
	 *         null sinon.
	 */
	public ArbreBinaire<E> supprimeElementPere() {
		// EXERCICE 12
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Fournit une représentation littérale arborescente de l'arbre.
	 * 
	 * @param avecArbreEnglobant indique si l'arbre englobant doit apparaître dans
	 *                           la représentation.
	 * @param avecH              indique si la hauteur de chaque noeud doit
	 *                           apparaître dans la représentation.
	 * @param avecFE             indique si le facteur d'équilibrage de chaque noeud
	 *                           doit apparaître dans la représentation.
	 * @return représentation littérale de l'arbre.
	 */
	public String toString(boolean avecArbreEnglobant, boolean avecH, boolean avecFE) {
		class UtilRepresentation {
			ChaineSimple<String> niveaux(ArbreBinaire<?> a, ArbreBinaire<?> avecArbreEnglobant, boolean avecH, boolean avecFE) {
				String etiq = a.valeur.toString();
				if (a == avecArbreEnglobant) etiq = "[" + etiq + "]";
				if (avecH) etiq += "|h:" + a.hauteur;
				if (avecFE) etiq += "|e:" + a.facteurEquilibrage();
				if (a.feuille()) return new ChaineSimple<>(etiq);
				ChaineSimple<String> niveauxL = (a.gauche == null ? null
						: niveaux(a.gauche, avecArbreEnglobant, avecH, avecFE));
				ChaineSimple<String> niveauxR = (a.droit == null ? null
						: niveaux(a.droit, avecArbreEnglobant, avecH, avecFE));
				ChaineSimple<String> accumulateur = new ChaineSimple<>(null);
				ChaineSimple<String> finDeChaine = accumulateur;
				ChaineSimple<String> cl, cr;
				int largeurL = (niveauxL == null ? 1 : niveauxL.valeur.length());
				int largeurR = (niveauxR == null ? -1 : niveauxR.valeur.length());
				for (cl = niveauxL, cr = niveauxR; cl != null && cr != null; cl = cl.suivant, cr = cr.suivant)
					finDeChaine = finDeChaine.insereElementSuivant(cl.valeur + " " + cr.valeur);
				for (; cr != null; cr = cr.suivant)
					finDeChaine = finDeChaine
							.insereElementSuivant(new String(new char[largeurL + 1]).replace('\0', ' ') + cr.valeur);
				for (; cl != null; cl = cl.suivant)
					finDeChaine = finDeChaine
							.insereElementSuivant(cl.valeur + new String(new char[largeurR + 1]).replace('\0', ' '));
				String traits = (a.droit == null ? "|" : "+" + new String(new char[largeurL]).replace('\0', '-') + "+");
				int taille = largeurL + 1 + largeurR - traits.length();
				traits += new String(new char[taille < 0 ? 0 : taille]).replace('\0', ' ');
				accumulateur.insereElementSuivant(traits);
				taille = largeurL + 1 + largeurR - etiq.length();
				accumulateur
						.insereElementSuivant(etiq + new String(new char[taille < 0 ? 0 : taille]).replace('\0', ' '));
				return accumulateur.suivant;
			}
		}
		UtilRepresentation ur = new UtilRepresentation();
		ChaineSimple<String> c;
		if (avecArbreEnglobant) {
			ArbreBinaire<?> racine = this;
			while (racine.pere != null) racine = racine.pere;
			c = ur.niveaux(racine, this, avecH, avecFE);
		}
		else c = ur.niveaux(this, null, avecH, avecFE);
		String res = "";
		for (; c != null; c = c.suivant)
			res += c.valeur + "\n";
		return res;
	}

	@Override
	public String toString() {
		return this.toString(false, false, false);
	}

	// ------------------------------------------------- Opérations Arbre binaire

	/**
	 * Fournit les valeurs stockées dans l'arbre dans un ordre correspondant au
	 * parcours préfixe de l'arbre (parcours en profondeur).
	 * 
	 * @return chaîne des éléments de l'arbre ordonnés selon le parcours préfixe.
	 */
	public ChaineSimple<E> prefixe() {
		// EXERCICE 13
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Fournit les valeurs stockées dans l'arbre dans un ordre correspondant au
	 * parcours infixe de l'arbre (parcours en profondeur).
	 * 
	 * @return chaîne des éléments de l'arbre ordonnés selon le parcours infixe.
	 */
	public ChaineSimple<E> infixe() {
		// EXERCICE 13
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Fournit les valeurs stockées dans l'arbre dans un ordre correspondant au
	 * parcours postfixe de l'arbre (parcours en profondeur).
	 * 
	 * @return chaîne des éléments de l'arbre ordonnés selon le parcours postfixe.
	 */
	public ChaineSimple<E> postfixe() {
		// EXERCICE 13
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Fournit les valeurs stockées dans l'arbre dans un ordre correspondant au
	 * parcours en largeur de l'arbre (niveaux par niveaux).
	 * 
	 * @return chaîne des éléments de l'arbre ordonnés selon le parcours en largeur.
	 */
	public ChaineSimple<E> largeur() {
		// EXERCICE 14
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Fournit les valeurs correspondant à une génération dans l'arbre.
	 * <p>
	 * 
	 * La génération 0 correspond à la racine. La génération 1 correspond aux
	 * enfants. La génération 2 correspond aux petits-enfants, etc. Selon l'arbre,
	 * la génération demandée peut ne pas exister auquel cas null sera renvoyé en
	 * résultat. Par extension, les générations négatives correspondent à null.
	 * 
	 * @param n numéro de génération.
	 * @return chaîne des valeurs à la génération n si elle existe, null sinon.
	 */
	public ChaineSimple<E> generation(int n) {
		// EXERCICE 15
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	// ------------------------------------------------- Opérations Arbre binaire de recherche

	/**
	 * Fournit la plus petite valeur contenue dans l'arbre.
	 * 
	 * @return plus petite valeur de l'arbre.
	 */
	public E minimum() {
		// EXERCICE 16
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Fournit la plus grande valeur contenue dans l'arbre.
	 * 
	 * @return plus grande valeur de l'arbre.
	 */
	public E maximum() {
		// EXERCICE 16
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Indique si un objet est contenu dans l'arbre.
	 * 
	 * @param e objet recherché.
	 * @return vrai si e est contenu dans l'arbre, faux sinon.
	 */
	public boolean contient(E e) {
		// EXERCICE 16
		// TODO à compléter...
		return false; // <- TODO résultat à mettre à jour
	}

	/**
	 * Fournit l'ensemble des valeurs comprises dans un certain intervalle.
	 * 
	 * @param minInclu borne min de l'intervalle (borne incluse).
	 * @param maxExclu borne max de l'intervalle (borne exclue).
	 * @return chaîne des valeurs comprises entre minInclu et maxExclu, ou null.
	 */
	public ChaineSimple<E> extraire(E minInclu, E maxExclu) {
		// EXERCICE 19
		// TODO à compléter...
		return null; // <- TODO résultat à mettre à jour
	}

	/**
	 * Tente d'ajouter une valeur dans l'arbre.
	 * <p>
	 * 
	 * L'ajout n'est possible que si la valeur n'est pas déjà présente dans l'arbre.
	 * 
	 * @param e valeur à ajouter.
	 * @return vrai si l'ajout a été fait, faux sinon.
	 */
	public boolean ajoute(E e) {
		// EXERCICE 17
		// TODO à compléter...
		return false; // <- TODO résultat à mettre à jour
	}

	/**
	 * Tente de supprimer une valeur de l'arbre.
	 * <p>
	 * 
	 * La suppression n'est possible que si l'arbre n'est pas réduit à une feuille,
	 * et si la valeur est bien présente dans l'arbre.
	 * 
	 * @param e valeur à supprimer
	 * @return vrai si la suppression a été faite, faux sinon.
	 * @throws IllegalStateException si l'arbre est réduit à une feuille.
	 */
	public boolean supprime(E e) {
		// EXERCICE 18
		// TODO à compléter...
		return false; // <- TODO résultat à mettre à jour
	}

	// ------------------------------------------------- Opérations AVL

	/**
	 * Applique un rééquilibrage partiel sur l'arbre et sur ses ancêtres.
	 * <p>
	 * 
	 * Cette méthode traite les 2 cas de déséquilibre possibles suite à l'ajout ou à
	 * la suppression d'une valeur. Ces deux cas correspondent aux cas ou le facteur
	 * d'équilibrage vaut 2 ou -2.
	 */
	public void reequilibre() {
		// EXERCICE 21
		// TODO à compléter...
	}

	/**
	 * Applique une rotation gauche sur la racine de l'arbre.
	 */
	public void rotationGauche() {
		// EXERCICE 20
		// TODO à compléter...
	}

	/**
	 * Applique une rotation droite sur la racine de l'arbre.
	 */
	public void rotationDroite() {
		// EXERCICE 20
		// TODO à compléter...
	}

	/**
	 * Applique une double rotation gauche sur la racine de l'arbre.
	 */
	public void doubleRotationGauche() {
		// EXERCICE 20
		// TODO à compléter...
	}

	/**
	 * Applique une double rotation droite sur la racine de l'arbre.
	 */
	public void doubleRotationDroite() {
		// EXERCICE 20
		// TODO à compléter...
	}

}
