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
## Structure

### Tableaux

#### CRUD

Chaque entité a un CRUD associé grâce à l'extends de [BaseController](aluilcine/src/main/java/fr/iut/aluilcine/controllers/BaseController.java)
| Verbe | Route API | Definition |
| --- | --- | :---: |
| GET | /{nomDeLaCollection} | Obtenir toutes les documents d'une collection |
| GET | /{nomDeLaCollection}/{id} | Obtenir un document d'une collection selon son ID |
| POST | /{nomDeLaCollection} | Ajouter un document dans la collection (les champs sont en camelcase) |
| PUT | /{nomDeLaCollection}/{id} | Modifier un document | 
| DELETE | /{nomDeLaCollection}/{id} | Supprimer un document |

#### [CinemaController](aluilcine/src/main/java/fr/iut/aluilcine/controllers/CinemaController.java)

| Verbe | Route API | Paramètre de requête |
| --- | --- | :---: |
| GET | /cinemas/location | *latitude*, *longitude* et *limit*, limit pour la quantité de documents retournés |

#### [MovieSessionController](aluilcine/src/main/java/fr/iut/aluilcine/controllers/MovieSessionController.java)

| Verbe | Route API | Paramètre de requête |
| --- | --- | :---: |
| GET | /moviesessions/condition/ | *day*, *cinemaId* et *movieId*, le day est au format (dd-MM-yyyy) comme (23-08-2022) |

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

          
