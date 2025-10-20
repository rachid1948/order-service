# order-service
1) Contexte

Order & Stock Service — micro-service Spring Boot. Objectif v1 : gestion produits (SKU unique, prix, TVA, stock) + préparation cycle de commande (création, confirmation, annulation) avec règles métier et qualité “entreprise”.

2) Organisation (flux d’équipe)

Branches :
main (prod, protégée) · develop (intégration → env dev) · release/x.y (recette/UAT) · feature/<ticket-id>-<slug> · hotfix/<ticket-id>-<slug>

Sprints : hebdo (8h/jour). Milestones : Sprint 1, 2, 3.

Issues : type:feature | type:tech | type:bug + priority:P0/P1/P2 + area:* + status:blocked si besoin.

Commits : Conventional Commits (ex. feat(product): enforce unique SKU).

3) Definition of Done (DoD) — v1

DTO + validations ✔ · Service transactionnel ✔ · Repository ✔

Tests unitaires (services) + intégration (DB/Testcontainers) ✔

Erreurs JSON normalisées (400/404/409/422) ✔

OpenAPI à jour ✔

Sonar vert (bugs=0, coverage service ≥80%, duplications <3%) ✔

JAR buildable + image Docker buildable en local ✔

PR approuvée (checklist) ✔

4) Roadmap v1 (sprints)

Sprint 1 : Produit (create/list) + erreurs + tests de base

Sprint 2 : Commande (create/confirm/cancel) + règles stock

Sprint 3 : Observabilité + packaging (JAR/image) + scans qualité

Après avoir collé ces 4 sections : Commit changes.
