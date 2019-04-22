Projet�: Gestion d�une m�diath�que
JAVA 2
Auteurs : Abel Junior IBOUANGA , Christ Olivier ITOUA


Nous travaillons avec Derby qui est une base de donn�e relationnelle qui peut etre embarqu� dans n'importe quel projet Java.

Plusieurs actions sont possibles comme ajouter un adh�rent,consulter un adherent,supprimer un adherent, ajouter une �uvre,consulter une �uvre ,supprimer une �uvre,enregistrer un pret,supprimer un pret.

Vous trouverez plus bas la vision du projet, la d�marche de r�alisation, ainsi que la r�alisation.

	Vision du projet

Il s�agit de la gestion d�une m�diath�que , comme �nonc� brievement en plus haut, l'utilisateur dispose d'un menu en page d'accueil (Oeuvre,Adherent,Pret,Quitter) dans lequel existe d'autres sous menus.

Dans le menu ��uvres� (ajouter,consulter ou supprimer) , �adherents�(ajouter,consulter ou supprimer) , �pr�t� (enregistrer un pr�t ou une remise) et enfin �Ouitter� pour fermer l�application.
Pour les interfaces graphiques, nous utilisons JavaFx ( https://docs.oracle.com/javafx/2/api/index.html ) .

La base de donn�e utilis� est celle de Derby notre base de donn�e nomm� "mediatheque".


	D�marche de r�alisation


Pour les enregistrements (adh�rents,oeuvres ) nous envoyons des requetes sql pour ins�rer les valeurs dans les tables correspondantes apr�s s'etre assur� que tous les champs �taient renseign�s.
Pour la suppression (oeuvre,adh�rent) nous r�cup�rons � travers des requetes sql les donn�es (liste des adh�rents,liste des oeuvres) par exemple pour les oeuvres ce sera toutes les oeuvres pr�sentes dans la table 'oeuvre', on convertira ensuite le r�sultat de cette requete en chaine de caract�res
et on l'ins�ra dans la Combo box correspondante.
De meme pour pour les adh�rents.
Pour l'enregistrement d'un pret , nous g�n�rons un nombre al�atoire pour l'id,ensuite � travers une requete nous ins�rons les valeurs dans la table � savoir l'id,le nom de l'adh�rent,le nom de l'oeuvre,la date de pret et la date � laquelle le rendu est attendue c'est � dire 30 jours � partir du jour d'emprunt.

Pour enregistrer une remise, nous modifions la structure de la table de pret en y rajoutant une colonne 'date de remise reelle', avec les valeurs (id,nom de l'adh�rent et l'oeuvre) que l'utilisateur choisira ,on identifiera de mani�re unique le pret et mettons � jour la colonne de la table pret.  
Ensuite, pour savoir si le d�lai est d�pass�, on compare la date � laquelle le pret �tait attendu � celle r�elle.
Notre application offre un d�lai de 30 jours � partir de la date de pr�t autrement dit (date de remise attendue = date de pr�t + 30 jours).


De m�me pour la consultation il faut choisir un adh�rent pour pouvoir consulter soit ses pr�ts, soit ses coordonn�es. 
Ensuite on passe l'adherent en param�tre dans un 'label'(invisible) et on r�cup�re d'apr�s la requete corresponadante dans les tables (Pret et/ou Oeuvre) soit les coordonn�es ou les prets en cours.
 
Le menu ��uvre� est fait exactement de la m�me fa�on que le menu �Adh�rent�.
Pour la r�alisation de ce projet, nous avons utilis� Scene Builder version1.1, pour les interfaces graphiques JavaFx et IDE (environnement de developpement) Netbeans version 8.2.

	R�alisation

Pour la table 'adherent' nous avons comme champ (id_adherent,nom,prenom,adresse) avec en cl� primaire le nom et le prenom car on se retrouvait avec des doublons on ne choisissant que l'id ou id_adherent,nom,prenom .
Pour la table 'oeuvre' comme champ (id_oeuvre,le titre ,l'auteur et le nombre d'exemplaire) avec en cl� le titre de l'oeuvre et l'auteur car on y ajoutant l'id (la meme oeuvre pouvait exister autant de fois en ayant juste des id diff�rents. 
Pour la table 'pret' en champ on a (id_pret,nom_adherent,oeuvre,date de pret, date de remise attendue ,date de remise reelle(ajouter seulement en cas de remise,par d�faut null)) comme cl� le nom_adherent,l'oeuvre et la date de pret pour 
permettre � un adherent d'emprunter plusieurs oeuvres � condition que les dates de prets soit diff�rentes.

Pour enregistrer une remise on rentre l'id de pret , l'oeuvre et la date de remise de l'oeuvre l'adherent . Si la date de remise est post�rieure � la date � laquelle la limite �tait fix� il y aura un message notifiant que le d�lai est d�pass�.
Pour consulter les adherents (coordonnees et prets en cours) : on passe l'adherent en param�tre pour l'interface correspondant 'label avec propri�t� invisible' ensuite avec la requete on cherche pour les coordonn�es l'adh�rent dont le nom est"�gale" au label.
On l'ins�re ensuite dans un tableau (Tableview) , pour les prets on cherche les adherents dans la table de 'pret' dont la date de remise reelle du livre est 'NULL'.
De meme pour consulter (caract�ristiques et prets ) : d�s l'initialisation de la page on recherche toutes les oeuvres dans la table des oeuvres, qu'on ins�re dans la Combo Box .
Ensuite, on passe l'oeuvre en param�tre pour l'interface correspondante (caract�ristique,pret ou les deux) 'label avec la propriete invisible' ensuite avec la requete on cherche pour les prets en cours, les oeuvres dans la table 'pret' dont la date de remise est 'NULL'.
	










