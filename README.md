# API REST des temps d'attente en agence OPT.nc

## Docker

L'api est disponible directement sur [Dockerhub](https://hub.docker.com/repository/docker/rastadidi/opt-temps-attente-agences-api)

## Démarrer le service

```
mvn spring-boot:run
```

## Endpoints

```
/
/csv
/communes
/temps-attente/agences
/temps-attente/agences/{communeName}
/temps-attente/agence/{idAgence}
```

## Exemples d'appels


<pre>
<code><b>/</b></code> <i>Accès à la documentation Swagger</i>
<code><b>/csv</b></code> <i>Accès au téléchargement de la liste des agences au format CSV</i>
<code><b>/communes</b></code> <i>Accès à la liste des communes</i>
<code><b>/temps-attente/agences</b></code> <i>Accès à la liste des agences</i>
<code><b>/temps-attente/agences/noumea</b></code> <i>Accès à la liste des agences de Nouméa</i>
<code><b>/temps-attente/agence/4314</b></code> <i>Accès à l'agence correspondant à l'id 4314</i>
</pre>

Aller directement sur le [Marketplace](https://rapidapi.com/adriens/api/temps-d-attente-agences-opt-nc) pour des exemples live.

## Accéder

Cette API est accessible depuis le web et documentée sur [le marketplace RapidAPI](https://rapidapi.com/adriens/api/temps-d-attente-agences-opt-nc).
Des exemples de codes fonctionnels y sont fournis pour tous les languages.

## Ambitions

Cette API a pour ambition de booster l'Open Innovation et permettre la réalisation d'intégrations efficaces et originales.

## Exemples de réalisations

- **Modèle prédictif**: [support de présentation](https://slides.com/monimpaul/deck-4c5e0d#/)
- **Appli mobile Flutter**: video de démo d'un [prototype opérationnel](https://youtu.be/FJzCIQDsMtc)


# jib build

In your `~/.m2/settings.xml` put your Docker hub

```xml
<server>
    <id>registry.hub.docker.com</id>
    <username>rastadidi</username>
    <password>XXXXXXX</password>
</server>
```

Then build/push to DockerHub :

`mvn compile jib:build`

# Docker pull command

```
docker pull rastadidi/opt-temps-attente-agences-api:latest
docker images
