# Rapport n°2: Programmation avancée

## TP4.1: Calcul de pi par une méthode de Monte Carlo

### Question 1:

La méthode de Monte Carlo, dans le cas ou nous voulons calculer pi, est une méthode reposant sur une stimulation statistique. Cette méthode fonctionne en générant des points aléatoires dans un carré de rayon 2r, et de compter le nombre de points présents au sein du cercle inscrit. En calculant la proportion de points présents dans le cercle par rapport aux points générés, on obtient une valeur approximative de PI.

**Algorithme séquentiel**


Entrée: N (nombre de lancers)
Sortie: Estimation de la valeur de π

- Initialiser le compteur de points à 0
- Boucle for pour N intérations:
    - Générer un point sur le carrés
    - Si le point est présent dans le cercle
        - Incrémenter le compteur de points
- Calculer la valeur approximative de π
- Retourner la valeur de π

### Question 2

L'une des méthodes utilisables 
