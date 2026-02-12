# Tp-Bank-Design-Pattern

Application Java en mode console pour la gestion d'opérations bancaires, implémentant plusieurs design patterns et une architecture en couches

## Table des Matières
- [Aperçu](#aperçu)
- [Fonctionnalités](#fonctionnalités)
- [Architecture](#architecture)

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

