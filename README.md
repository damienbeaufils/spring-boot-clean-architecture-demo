# clean-architecture-demo

[![Build Status](https://travis-ci.org/damienbeaufils/clean-architecture-demo.svg?branch=master)](https://travis-ci.org/damienbeaufils/clean-architecture-demo)

An example of clean architecture with Spring Boot

## Foreword (french)

Ce backend est conçu selon le pattern d'[**architecture hexagonale**](http://www.maximecolin.fr/uploads/2015/11/56570243d02c0_hexagonal-architecture.png) (également appelé Clean Architecture).
De ce fait, les principes [SOLID](https://en.wikipedia.org/wiki/SOLID_(object-oriented_design)) sont utilisés dans le code, notamment le [Dependency Inversion Principle](https://en.wikipedia.org/wiki/Dependency_inversion_principle) (à ne pas confondre avec la classique injection de dépendances avec Spring par exemple).

Concrètement, on distingue 3 packages principaux : `domain`, `use_cases` et `infrastructure` qui doivent respecter ces règles :
- `domain` contient le métier et son intelligence et n'a aucune dépendance vers l'extérieur : ni vers des frameworks (Hibernate par exemple), ni vers les packages `use_cases` et `infrastructure`.
- `use_cases` est un chef d'orchestre et va dépendre uniquement du `domain` pour répondre à des cas d'utilisation métier. `use_cases` ne doit pas avoir de dépendances vers `infrastructure`.
- `infrastructure` contient toute la technique, et ne doit pas contenir de métier. `infrastructure` a des dépendances vers `domain`, `use_cases` et les frameworks.  

## Install

```
./gradlew assemble
```

## Test

```
./gradlew check
```

## Mutation testing

```
./gradlew pitest
```

## Run

```
./gradlew bootRun
```
