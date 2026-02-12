# Tp-Bank-Design-Pattern

Application Java en mode console pour la gestion d'opérations bancaires, implémentant plusieurs design patterns et une architecture en couches

## Table des Matières
- [Aperçu](#aperçu)
- [Fonctionnalités](#fonctionnalités)
- [Architecture](#architecture)
- [Design Patterns](#design-patterns)
- [Technologies](#technologies)
- [Prérequis](#prérequis)
- [Installation](#installation)
- [Configuration de la Base de Données](#configuration-de-la-base-de-données)
- [Configuration](#configuration)
- [Utilisation](#utilisation)
- [Diagrammes UML](#diagrammes-uml)

## Aperçu

TP Bank est un système bancaire de base en Java. L'application gère les clients, les comptes (Courant et Épargne), et les opérations bancaires (dépôts et retraits) tout en appliquant des règles métier strictes telles que la validation du solde et les limites de découvert

## Fonctionnalités

### Gestion des Clients
- Affichage de tous les clients avec leurs informations
- Consultation des détails d'un client (nom, email, téléphone)

### Gestion des Comptes
- Support de plusieurs types de comptes :
  - **Compte Courant** : Avec découvert
  - **Compte Épargne** : Avec calcul du taux d'intérêt
- Affichage de tous les comptes d'un client spécifique
- Consultation du solde d'un compte en temps réel

### Opérations Bancaires
- **Dépôt d'argent** : Ajout de fonds sur n'importe quel compte
- **Retrait d'argent** : Retrait de fonds avec validation
  - Vérification du solde pour les comptes épargne
  - Support du découvert pour les comptes courants
- **Historique des transactions** : Journal complet des opérations avec horodatage
- Enregistrement automatique de toutes les opérations en base de données

### Règles Metier Appliquées
- Montants positifs uniquement
- Vérification de l'existence du compte
- Détection de solde insuffisant
- Applicatio de la limite de decouvert pour les comptes courants
- Toutes les opérations enregistrées dans l'historique
- Mise à jour du solde en temps réel

## Architecture

L'application suit une **architecture multi-couches** :
- Interface Console (Couche View): pour gérer les interactions utilisateur
- Logique metier (couche Service): les règles metier
- Accès aux Données (Couche Dao): les opérations de base de données
- Base de Données (MySQL): pour la persistance des données

### Responsabilités par Couche

**Couche View**
- Gestion des interactions utilisateur
- Navigation dans les menus
- Formatage de l'affichage
- Validation de saisies

**Couche Service**
- Implementation de la logique métier
- Gestion des transarctions
- Application des règles métier
- Gestion des exceptions

**Couche DAO**
- Construction des requêtes SQL avec un Pattern Builder
- Opérations sur la base de données
- Mapping ResultSet vers objets
- Gestion de la connexion avec Singleton

**Couche Model**
- Les entités
- Structures de données
- Relations entre les entités

## Design Patterns

Ce projet implémente **5 design patterns** :

### 1. Pattern Singleton
**Classe** : `DatabaseConnection`
- **Objectif** : garantir une instance unique de connexion à la BDD
- **Avantage** : éviter la surcharge de connexions et le gaspillage de ressources
```java
DatabaseConnection.getInstance().getConnection();
```
### 2. Pattern Factory
**Classe** : `AccountFactory`
- **Objectif** : centralise la logique de création des comptes
- **Avantage** : facilite l'ajout de nouveaux types de comptes (Pro, étudiant,...)

### 3. Pattern DAO
**Classes** : `ClientDao`, `AccountDao`, `OperationDao`
- **Objectif** : separer l'accès aux données de la logique métier
- **Avantage** : la techno de la bdd peut être changée sans affecter le code métier

### 4. Pattern Builder
**Classe** : `QueryBuilder`
- **Objectif** : construire des requêtes SQL facilement lisiblent
- **Avantage** : code lisible et moins sujet aux erreurs

### 5. Pattern Helper/Utility
**Classes** : `MapResultSetHelper`, `InputHelper`
- **Objectif** : centraliser les operations communes
- **Avantage** : le code peut etre réutilisé 

## Technologies

- **Langage** : Java 8+
- **Outil de Build** : Maven
- **Base de Données** : MySQL 8.x ou MariaDB 10.x
- **JDBC** : MySQL Connector/J 8.x
- **Contrôle de Version** : Git

## Prérequis

- Java JDK 8 ou sup
- Maven 3.6+
- MySQL 8.x ou MariaDB 10.x
- Git

## Installation

### 1. Cloner le dépôt

```bash
git clone https://github.com/john7440/Tp-Bank-Design-Pattern.git
cd Tp-bank-Design-Pattern
```
### 2. Build avec Maven

```bash
mvn clean install
```
### 3. Ouvrir dans IntelliJ IDEA

1. File → Open → sélectionner le dossier du projet
2. Attendre que Maven télécharge les dependances
3. Intellij configerera automatiquement le projet

## Configuration de la Base de Données

### Option 1 : Utiliser un client MySQL
1. Ouvrir votre client MySQL/MariaDB
2. Copier et coller le contenu de `tpbank_database.sql` qui se trouve dans docs/sql
3. Executer le script

### Option 2 : Ligne de commande

```bash
# Pour MySQL
mysql -u root -p < docs/sql/tpbank_database.sql

# Pour MariaDB
mariadb -u root -p < docs/sql/tpbank_database.sql
```
**Note** : Le script va :
- créer la base de données `tpbank`
- ceéer un utilisateur dédié `tpbank_user` avec le mot de passe `TpBank2026!`
- créer toutes les tables avec les contraintes
- insérer des données de test

## Configuration

### Connexion à la Base de Données

Modifier `DatabaseConnection.java` si nécessaire :

```java
private static final String URL = "jdbc:mysql://localhost:3306/tpbank";
private static final String USER = "tpbank_user";
private static final String PASSWORD = "TpBank2026!";
```

### Dépendances Maven

Ajouter dans votre `pom.xml` :

```xml
<dependencies>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>
</dependencies>
```

## Utilisation

### Lancer l'application depuis Intellij 

1. Naviguer vers `ConsoleApp.java` dans src/main/java
2. Clic droit sur le fichier
3. Sélectionner **Run 'ConsoleApp.main()'**

## Diagrammes UML

### Diagramme de Classes

Le diagramme de classes complet est disponible dans le dossier `docs/` :
- **Source PlantUML** : `ClassDiagram.puml`
- **Image PNG** : `TP_Bank.png`

#### Relations Clés

**Couche Model :**
- Client 1 ─ * Account
- Account 1 ─ * Operation
- CurrentAccount ─|> Account
- SavingsAccount ─|> Account

**Design Patterns :**
- DatabaseConection (Singleton)
- AccountFactory (Factory)
- QueryBuilder (Builder)
- Tous les DAOs (Pattern DAO)

**Couches de l'Architzcture :**
- ConsoleApp → MenuView → Views
- Views → BankService
- BankService → DAOs
- DAOs → DatabaseConnection

