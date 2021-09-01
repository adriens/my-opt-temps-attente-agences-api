![Build](https://github.com/opt-nc/opt-temps-attente-agences-api/actions/workflows/maven.yml/badge.svg)
[![](https://jitpack.io/v/opt-nc/opt-temps-attente-agences-api.svg)](https://jitpack.io/#opt-nc/opt-temps-attente-agences-api)
[![Docker Image](https://img.shields.io/badge/docker-homepage-blue)](https://hub.docker.com/repository/docker/optnc/opt-temps-attente-agences-api)
![Docker Pulls](https://img.shields.io/docker/pulls/optnc/opt-temps-attente-agences-api)
![Docker Image Size (latest by date)](https://img.shields.io/docker/image-size/optnc/opt-temps-attente-agences-api)
![Docker Stars](https://img.shields.io/docker/stars/optnc/opt-temps-attente-agences-api)
![Docker Image Version (latest by date)](https://img.shields.io/docker/v/optnc/opt-temps-attente-agences-api?arch=amd64&sort=date)

# API REST des temps d'attente en agence OPT.nc

## Ambitions

Cette API a pour ambition de booster l'Open Innovation et permettre la réalisation d'intégrations efficaces et originales.

## Docker

L'api est disponible sur [Dockerhub](https://hub.docker.com/repository/docker/optnc/opt-temps-attente-agences-api)

```shell
docker run --rm -p 8081:8081 optnc/opt-temps-attente-agences-api:latest
```

## Démarrer le service

```
mvn spring-boot:run
```

## Endpoints

```
GET /csv
GET /communes
GET /temps-attente/agences
GET /temps-attente/agences/{communeName}
GET /temps-attente/agence/{idAgence}
```

Liste complète sur [Swagger](http://127.0.0.1:8081/doc.tempsattente.html)


## Exemples d'appels
```bash
sudo apt-get install httpie jq -y
http http://127.0.0.1:8081/temps-attente/agence/4161
http http://127.0.0.1:8081/temps-attente/agence/4161 | jq '.idAgence'
http http://127.0.0.1:8081/temps-attente/agences # Accès à la liste des agences
http http://127.0.0.1:8081/temps-attente/agences?lon=166.4260482788086&lat=-22.25097078275085&distanceInMeters=3000 # Accès à la liste des communes par distance
http http://127.0.0.1:8081/temps-attente/agences/noumea # Accès à la liste des agences de Nouméa
```

## Marketplace RapidAPI

Cette API est accessible depuis le web et documentée sur [le marketplace RapidAPI](https://rapidapi.com/adriens/api/temps-d-attente-agences-opt-nc).
Des exemples de codes fonctionnels y sont fournis pour tous les languages.

## Exemples de réalisations

- **Modèle prédictif**: [support de présentation](https://slides.com/monimpaul/deck-4c5e0d#/)
- **Appli mobile Flutter**: video de démo d'un [prototype opérationnel](https://youtu.be/FJzCIQDsMtc)
