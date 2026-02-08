
# Pricing Engine — Skeleton (Hexagonal / Clean)

> Projet squelette **Spring Boot 3 / Java 17** pour moteur de **pricing & promotions**,
> conforme à l'architecture **hexagonale** et au flux métier :
> contexte → prix de base → sélection/éligibilité → arbitrage → orchestration & application → traçabilité → restitution/historisation.

## Contenu
- **Aucune implémentation** (classes et interfaces vides, Javadoc TODO)
- **Couche Domain** : modèles et règles métier **sans dépendance Spring**
- **Couche Application** : use cases & ports (in/out)
- **Interfaces** : API/DTO/mappers (squelettes)
- **Infrastructure** : adapters sortants + persistence + config (squelettes)

## Lancement
Le projet ne contient pas d'implémentation. Il compile mais ne réalise aucune logique.

## Configuration
Voir `src/main/resources/application.properties` pour les placeholders MySQL.

## Licence
Usage interne.
