# EcoPoint

## Configuration

L'application est configurée sur le port 9090 avec la base de données sur le port 3306 avec comme identifiant :
- user : root
- password : root

Créer la base de données suivante :

- nom : ecopoint
- encodage : utf8_general_vi

## Log

### Mise en place d'un logger

Mise en place des logs de l'application paramétrable dans le fichier log4j2.xml.

### Mise en place de test unitaire

Les tests unitaires fonctionnent pour le test des entités mais aussi pour le test des contrôleurs des différentes pages WEB.

### Ticket EP-50 inscription

Création de la fonctionnalité pour s'inscrire à l'application.

### Ticket EP-51 connexion

Création de la fonctionnalité pour s'authentifier sur le site.

Différents test :

- Test unitaire
- Test du contrôleur associé à la page de connexion