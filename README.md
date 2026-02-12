# Tp-Bank-Design-Pattern

Application Java en mode console pour la gestion d'opérations bancaires, implémentant plusieurs design patterns et une architecture en couches

## Table des Matières
- [Aperçu](#aperçu)
- [Fonctionnalités](#fonctionnalités)

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
