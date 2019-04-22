Projet : Gestion d’une médiathèque
JAVA 2
Auteurs : Abel Junior IBOUANGA , Christ Olivier ITOUA


Nous travaillons avec Derby qui est une base de donnée relationnelle qui peut etre embarqué dans n'importe quel projet Java.

Plusieurs actions sont possibles comme ajouter un adhérent,consulter un adherent,supprimer un adherent, ajouter une œuvre,consulter une œuvre ,supprimer une œuvre,enregistrer un pret,supprimer un pret.

Vous trouverez plus bas la vision du projet, la démarche de réalisation, ainsi que la réalisation.

	Vision du projet

Il s’agit de la gestion d’une médiathèque , comme énoncé brievement en plus haut, l'utilisateur dispose d'un menu en page d'accueil (Oeuvre,Adherent,Pret,Quitter) dans lequel existe d'autres sous menus.

Dans le menu ‘œuvres’ (ajouter,consulter ou supprimer) , ‘adherents’(ajouter,consulter ou supprimer) , ‘prêt’ (enregistrer un prêt ou une remise) et enfin ‘Ouitter’ pour fermer l’application.
Pour les interfaces graphiques, nous utilisons JavaFx ( https://docs.oracle.com/javafx/2/api/index.html ) .

La base de donnée utilisé est celle de Derby notre base de donnée nommé "mediatheque".


	Démarche de réalisation


Pour les enregistrements (adhérents,oeuvres ) nous envoyons des requetes sql pour insérer les valeurs dans les tables correspondantes après s'etre assuré que tous les champs étaient renseignés.
Pour la suppression (oeuvre,adhérent) nous récupérons à travers des requetes sql les données (liste des adhérents,liste des oeuvres) par exemple pour les oeuvres ce sera toutes les oeuvres présentes dans la table 'oeuvre', on convertira ensuite le résultat de cette requete en chaine de caractères
et on l'inséra dans la Combo box correspondante.
De meme pour pour les adhérents.
Pour l'enregistrement d'un pret , nous générons un nombre aléatoire pour l'id,ensuite à travers une requete nous insérons les valeurs dans la table à savoir l'id,le nom de l'adhérent,le nom de l'oeuvre,la date de pret et la date à laquelle le rendu est attendue c'est à dire 30 jours à partir du jour d'emprunt.

Pour enregistrer une remise, nous modifions la structure de la table de pret en y rajoutant une colonne 'date de remise reelle', avec les valeurs (id,nom de l'adhérent et l'oeuvre) que l'utilisateur choisira ,on identifiera de manière unique le pret et mettons à jour la colonne de la table pret.  
Ensuite, pour savoir si le délai est dépassé, on compare la date à laquelle le pret était attendu à celle réelle.
Notre application offre un délai de 30 jours à partir de la date de prêt autrement dit (date de remise attendue = date de prêt + 30 jours).


De même pour la consultation il faut choisir un adhérent pour pouvoir consulter soit ses prêts, soit ses coordonnées. 
Ensuite on passe l'adherent en paramètre dans un 'label'(invisible) et on récupère d'après la requete corresponadante dans les tables (Pret et/ou Oeuvre) soit les coordonnées ou les prets en cours.
 
Le menu ‘Œuvre’ est fait exactement de la même façon que le menu ‘Adhérent’.
Pour la réalisation de ce projet, nous avons utilisé Scene Builder version1.1, pour les interfaces graphiques JavaFx et IDE (environnement de developpement) Netbeans version 8.2.

	Réalisation

Pour la table 'adherent' nous avons comme champ (id_adherent,nom,prenom,adresse) avec en clé primaire le nom et le prenom car on se retrouvait avec des doublons on ne choisissant que l'id ou id_adherent,nom,prenom .
Pour la table 'oeuvre' comme champ (id_oeuvre,le titre ,l'auteur et le nombre d'exemplaire) avec en clé le titre de l'oeuvre et l'auteur car on y ajoutant l'id (la meme oeuvre pouvait exister autant de fois en ayant juste des id différents. 
Pour la table 'pret' en champ on a (id_pret,nom_adherent,oeuvre,date de pret, date de remise attendue ,date de remise reelle(ajouter seulement en cas de remise,par défaut null)) comme clé le nom_adherent,l'oeuvre et la date de pret pour 
permettre à un adherent d'emprunter plusieurs oeuvres à condition que les dates de prets soit différentes.

Pour enregistrer une remise on rentre l'id de pret , l'oeuvre et la date de remise de l'oeuvre l'adherent . Si la date de remise est postérieure à la date à laquelle la limite était fixé il y aura un message notifiant que le délai est dépassé.
Pour consulter les adherents (coordonnees et prets en cours) : on passe l'adherent en paramètre pour l'interface correspondant 'label avec propriété invisible' ensuite avec la requete on cherche pour les coordonnées l'adhérent dont le nom est"égale" au label.
On l'insère ensuite dans un tableau (Tableview) , pour les prets on cherche les adherents dans la table de 'pret' dont la date de remise reelle du livre est 'NULL'.
De meme pour consulter (caractéristiques et prets ) : dès l'initialisation de la page on recherche toutes les oeuvres dans la table des oeuvres, qu'on insère dans la Combo Box .
Ensuite, on passe l'oeuvre en paramètre pour l'interface correspondante (caractéristique,pret ou les deux) 'label avec la propriete invisible' ensuite avec la requete on cherche pour les prets en cours, les oeuvres dans la table 'pret' dont la date de remise est 'NULL'.
	










