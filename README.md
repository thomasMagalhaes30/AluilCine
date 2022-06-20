# ![AluilCine](https://github.com/thomasMagalhaes30/AluilCine/blob/dev/asset/AluilCineText.png)

AluilCine est un projet de Licence Pro de l'IUT Clermont-Ferrand 2022. (Période 3 - Client Serveur)

⚠ Le projet doit être rendu pour le **vendredi 24 juin 2022 minuit** !

## Liens utiles

- [Site du cours](http://clientserveur.milka.ovh/)
- [Documentation officielle MongoDB](https://www.mongodb.com/docs/manual/)
- [Documentation officielle spring-boot web](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#web)

## Installation
⚠ Dans le repertoire du projet

Télécharger les dépendances
```bash
mvn clean install
```
ou
```bash
./mvnw clean install
```
Configurer le .env à partir du .env.sample
```bash
cp .env.sample .env
```

## Lancement
⚠ Dans le repertoire du projet

Lancer le projet spring boot
```bash
mvn spring-boot:run
```
ou
```bash
./mvnw spring-boot:run
```

## Informations & documentation
- Le code se trouve dans [`AluilCine/aluilcine/`](aluilcine/)
- Un postman configuré est disponilbe trouve dans [`AluilCine/postman/`](postman/)
- La javadoc se trouve dans [`AluilCine/doc/javadoc/`](doc/javadoc/)
- Le MCD  se trouve dans [`AluilCine/doc/MCD/`](doc/MCD/)
- l'UML se trouve dans [`AluilCine/doc/UML/`](doc/UML/)

Les diiférents point d'évaluations sont justifié dans les issues qui ont certainnes Milestones
- [issue avec milestone DOC](https://github.com/thomasMagalhaes30/AluilCine/issues?q=is%3Aopen+is%3Aissue+milestone%3ADOC)
- [issue avec milestone Check](https://github.com/thomasMagalhaes30/AluilCine/issues?q=is%3Aopen+is%3Aissue+milestone%3ACheck) (correspond à la justifiaction des différents points demmandés dans le sujet)


## Structure

### Tableaux

#### CRUD

Chaque entité a un CRUD associé grâce à l'extends de [BaseController](aluilcine/src/main/java/fr/iut/aluilcine/controllers/BaseController.java)

Les collections disponibles, sont :
 - *movies*
 - *cinemas*
 - *movieSessions*
 - *reviews*
 - *users*

| Verbe | Route API | Definition |
| --- | --- | :---: |
| GET | /{nomDeLaCollection} | Obtenir toutes les documents d'une collection |
| GET | /{nomDeLaCollection}/{id} | Obtenir un document d'une collection selon son ID |
| POST | /{nomDeLaCollection} | Ajouter un document dans la collection (les champs sont en camelcase) |
| PUT | /{nomDeLaCollection}/{id} | Modifier un document | 
| DELETE | /{nomDeLaCollection}/{id} | Supprimer un document |

#### [MovieController](aluilcine/src/main/java/fr/iut/aluilcine/controllers/MovieController.java)
| Verbe | Route API | Paramètre de requête | Exemple |
| --- | --- | :---: | --- |
| GET | /movies/pageableByMark{number} | **number** le nombre de movie souhaités sur la page. un paramètre optionnel page existe, il permet d'obtenir les films selon une pagination, un entier est attendu pour se paramètre.|  `/movies/pageableByMark10?page=2` |
| GET | /movies/last{number}MovieReleased/ | retourne une liste des derniers film sorti en fonction du number, sachant que le number doit être compris de 1 à 20 | `/movies/last10MovieReleased` |
| GET | /movies/pageableByCategory/{category} | Obtenir les films pour une catégorie donnée, paramètre **page** pour obtenir la page de la pagination avec le paramètre **numberOfMovieByPage** qui indique le nombre de films par page | `/movies/pageableByCategory/Action?page=2&numberOfMovieByPage=6` |
| GET | /movies/markAvgByCategory | retourne la note moyenne des films par catégorie | `/movies/markAvgByCategory` |
| GET | /movies/searchByTitle/{searchTitle} | retourne les films qui contiennent **seachTitle** dans leur titre | `/movies/searchByTitle/toto` |
#### [CinemaController](aluilcine/src/main/java/fr/iut/aluilcine/controllers/CinemaController.java)

| Verbe | Route API | Paramètre de requête |
| --- | --- | :---: |
| GET | /cinemas/location | *latitude*, *longitude* et *limit*, limit pour la quantité de documents retournés |

#### [MovieSessionController](aluilcine/src/main/java/fr/iut/aluilcine/controllers/MovieSessionController.java)

| Verbe | Route API | Paramètre de requête |
| --- | --- | :---: |
| GET | /movieSessions/condition/ | *day*, *cinemaId* et *movieId*, le day est au format (dd-MM-yyyy) comme (23-08-2022) |

#### [ReviewController](aluilcine/src/main/java/fr/iut/aluilcine/controllers/ReviewController.java)
| Verbe | Route API | Paramètre de requête |
| --- | --- | --- |
| GET | /reviews/pageableByMovieId/ | *movieId*, *page* : Integer, *numberOfReviewsByPage* : Integer - *page* et *numberOfReviewsByPage* sont optionnels|

## Auteurs

- [DA SILVA Antoine](https://github.com/antoine6348)
- [DA SILVA Kevin](https://github.com/dasilvaKevin)
- [MAGALHAES Thomas](https://github.com/thomasMagalhaes30)
- [THEUWS Gabriel](https://github.com/Amiralgaby)

## Technologies et outils

<img style="height:64px; padding-right:16px;" align="left"
     src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg"  alt="logo git"/>
<img style="height:64px; padding-right:16px;" align="left"
     src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg"  alt="logo spring boot"/>
<img style="height:64px; padding-right:16px;" align="left"
     src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mongodb/mongodb-original-wordmark.svg" alt="logo mongodb"/>

          
