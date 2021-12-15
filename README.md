[![semantic-release](https://img.shields.io/badge/%20%20%F0%9F%93%A6%F0%9F%9A%80-semantic--release-e10079.svg)](https://github.com/semantic-release/semantic-release)
![Build](https://github.com/opt-nc/opt-temps-attente-agences-api/actions/workflows/maven.yml/badge.svg)
[![](https://jitpack.io/v/opt-nc/opt-temps-attente-agences-api.svg)](https://jitpack.io/#opt-nc/opt-temps-attente-agences-api)
[![Docker Image](https://img.shields.io/badge/docker-homepage-blue)](https://hub.docker.com/repository/docker/optnc/opt-temps-attente-agences-api)
![Docker Pulls](https://img.shields.io/docker/pulls/optnc/opt-temps-attente-agences-api)
![Docker Image Size (latest by date)](https://img.shields.io/docker/image-size/optnc/opt-temps-attente-agences-api)
![Docker Stars](https://img.shields.io/docker/stars/optnc/opt-temps-attente-agences-api)
![Docker Image Version (latest by date)](https://img.shields.io/docker/v/optnc/opt-temps-attente-agences-api?arch=amd64&sort=date)

# API REST des temps d'attente en agence OPT.nc

## AmbitionsapiVersion: serving.knative.dev/v1
kind: Service
metadata:
  name: helloworld-java-spring
  namespace: default
spec:
  template:
    spec:
      containers:
        - image: docker.io/{username}/helloworld-java-spring
          env:
            - name: TARGET
              value: "Spring Boot Sample v1"

Cette API a pour ambition de booster l'Open Innovation et permettre la réalisation d'intégrations efficaces et originales.

## Lancement via Docker

L'api est disponible sur [Dockerhub](https://hub.docker.com/repository/docker/optnc/opt-temps-attente-agences-api)

```shell
docker run -d --rm -p 8080:8080 optnc/opt-temps-attente-agences-api:latest
```

## Lancenment via Knative

Knative permet de faire du Severless, c'est à dire que l'application est lancée au besoin de manière transparente et arrêté par la suite au bout d'un certain temps

### Soit via l'utilitaire [kn](https://knative.dev/docs/getting-started/#install-the-knative-cli) :
```shell
kn service create opt-temps-attente-agences \
--image optnc/opt-temps-attente-agences-api:stable \
--port 8080 \
--revision-name=stable
```

Puis récupérer l'url du service :
```shell
kn service describe opt-temps-attente-agences -o url
```
Ou directement appeler le service :
```shell
http $(kn service describe opt-temps-attente-agences -o url)
```

### Soit via [kubectl](https://kubernetes.io/docs/tasks/tools/) :
```shell
kubectl apply -f k8s/knative.yaml
```

Puis récupérer l'url du service :
```shell
kubectl get ksvc opt-temps-attente-agences --output=custom-columns=NAME:.metadata.name,URL:.status.url
```

### Suivre la mise à l'echelle automatique
```shell
kubectl get pod -l serving.knative.dev service=opt-temps-attente-agences -w
```

## Démarrer via maven depuis le code source

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

Liste complète sur [Swagger](http://127.0.0.1:8080/doc.tempsattente.html)

## Exemples d'appels
```bash
sudo apt-get install httpie jq -y
http http://127.0.0.1:8080/temps-attente/agence/4161
http http://127.0.0.1:8080/temps-attente/agence/4161 | jq '.idAgence'
http http://127.0.0.1:8080/temps-attente/agences # Accès à la liste des agences
http 'http://127.0.0.1:8080/temps-attente/agences?lon=166.4260482788086&lat=-22.25097078275085&distanceInMeters=3000' # Accès à la liste des communes par distance
http http://127.0.0.1:8080/temps-attente/agences/noumea # Accès à la liste des agences de Nouméa
```
## Marketplace RapidAPI

Cette API est accessible depuis le web et documentée sur [le marketplace RapidAPI](https://rapidapi.com/opt-nc-opt-nc-default/api/temps-attente-en-agence).
Des exemples de codes fonctionnels y sont fournis pour tous les languages.

## Exemples de réalisations

- **Modèle prédictif**: [support de présentation](https://slides.com/monimpaul/deck-4c5e0d#/)
- **Appli mobile Flutter**: video de démo d'un [prototype opérationnel](https://youtu.be/FJzCIQDsMtc)
