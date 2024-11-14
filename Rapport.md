# Rapport 

## Introduction

Ce document a pour objectif de rendre compte et d'expliquer le travail effectué ainsi que les connaissances acquises dans ce premier chapitre de la ressource R5.05: Programmation avancée.

Ce chapitre, dont l'objectif est la découverte, l'apprentissage et la maîtrise de la programmation parallèle, nous a permis de découvrir et d'ajouter a notre arsenal de compétences des nouveaux outils et principes, tels que les Thread et les Sémaphores.

Il se compose de 3 parties, suivant les TP effectués en cours. Le première partie, permet d'introduire et d'appliquer le concept de programmation parallèle à l'aide de simples objets. La deuxième partie nous a ensuite introduit le concept de sémaphores, tandis que la troisième et dernière nous voit appliquer ces deux concepts en même temps.

## 1. TP1: Mobiles

Ce TP introduit le concept de programmation parallèle au travers de la manipulation de simples objets, instanciés grâce aux classes JFrame et JPanel.

### La programmation parallèle et Threads 

Depuis nos début dans le domaine du développement, nous avons principalement eu recours à la programmation séquentielle, qui consiste à donner étape par étapes les indications au programme afin d'effectuer la tâche, qui va les effectuer sur un seul cœur.

La programmation parallèle nous introduit donc un nouveau paradigme de programmation, basé sur l'execution simultanée de plusieurs processus ou **Threads**.

#### Diviser pour mieux régner

La programmation parallèle se base sur la division d'un processus en plusieurs processus ou Threads. Cela a plusieurs avantages, notamment la réduction du temps d'execution, ou bien l'utilisation optimale des ressources, la programmation parallèle permettant par exemple d'utiliser tout le potentiel des processeurs multicœurs.

En revanche, ces avantages ne viennent pas sans nouveaux défis. En effet, la programmation parallèle introduit un nouveau degré de complexité, car le développeur doit gérer la par exemple, la synchronisation des processus et l'accès aux données partagées. Les problématiques d'accès aux données partagés sont abordés plus en détails dans la partie 2 de ce document.

### Le TP

L'objectif de ce TP est de mettre en place des premiers processus parallèles au travers d'objets simples, des carrés se déplaçant sur l'écran, chacun étant exécutés dans un Thread différent.

#### Diagramme UML

#### Développement

Le TP a été effectué suivant les consignes du TP1 Mobiles. La version finale comporte une fenêtre, de la classe UneFenetre, extension de la classe JFrame. Elle possède un layout comportant 4 rangs, occupés respectivement par un mobile, pour son déplacement, et un leurs boutons, permettant de mettre les processus en pause.

Les mobiles, de la classe UnMobile, extension de la classe JPanel, sont de simples carrés se déplaçant de gauche à droite, changeant de sens en rencontrant un bord de la fenêtre. Ils possèdent une méthode run permettant d'implémenter leur comportement. 

Les boutons, de la classe JButton, permettent d'arrêter les mobiles, en changeant leur paramètre isEnMarche. *En raison du non fonctionnement des méthodes suspend() et resume() sur ma version, j'ai du recourir à cette méthode afin de tout de même permettre l'action, malgré que cela ne suis pas l'objectif de suspendre un processus*

Les mobiles et leurs bouton respectif possèdent chacun leur Thread, gérant leur exécution en simultané.

## 2. TP2: Sémaphore

Ce TP a pour objectif l'introduction à l'utilisation des sémaphores, dans un contexte de contrôle des mobiles du TP1.

### Sémaphores et ressources critiques

L'une des grandes contrainte de la programmation parallèle est l'utilisation des ressources critiques. Ces ressources ne peuvent être utilisées que par un nombre limité, en raison du risque de bug et de perte de données que peut entraîner sa manipulation par plusieurs Threads de manière simultanée. Les sémaphores interviennent donc comme des centres de contrôles, en limitant le nombre de processus qui peuvent accéder simultanément à la dite ressource.

### Le TP

L'objectif de ce TP, et de considérer le tiers central du trajet de déplacement des mobiles comme une ressource critique, et de permettre à un seul mobile de se déplacer sur cette section à la fois.

#### Diagramme UML

#### Développement

La classe semaphore, donnée par le professeur a été implantée dans ce TP. C'est une classe abstraite, permettant de donner une base pour une nouvelle classe que nous détailleront plus loin. Elle comporte une variable, valeur, tenant compte des places disponibles pour l'action sur une ressource critique, et une méthode syncWait(), qui une fois appelée, empêche l'action du Thread tant qu'une place n'est pas disponible pour l'action sur la ressource critique.

Le sémaphore que nous utilisons est la classe semaphoreBinaire. Elle hérite de la classe semaphore, et permet de n'avoir qu'un seul processus au maximum travaillant sur la ressource en tout temps. Elle apporte également la méthode syncSignal(), permettant au processus de signaler au sémaphore qu'il a fini d'utiliser la ressource critique.

Pour qu'un processus utilise la ressource critique, il doit donc faire appel à la méthode syncWait() avant son utilisation, et une fois fini, la fonction syncSignal(), afin de signaler la fin de l'utilisation de la ressource.

Concernant les mobiles, le code a été repris du  TP1. Les boutons ont été retirés dans un soucis de lisibilité du logiciel. Le changement important concerne l'implémentation du semaphoreBinaire. Celui-ci est instancié au sein de la classe UneFenetre, et est passé en paramètre de la classe UnMobile, afin que ceux-ci puissent appeler les différentes méthodes. Au sein de la classe UnMobile, le comportement de la méthode run() a été modifiée, afin d'appeler la méthode syncWait() juste avant d'entrer dans la section critique, et la méthode syncSignal() juste après l'avoir franchie.

## 4. Ressources matérielles et logicielles utilisées.

Tous les travaux, du développement à la rédaction du présent rapport ont été effectués sur mon ordinateur personnel, un ThinkPad équipé un processeur i7 11e génération avec 4 cœurs.
Le développement des TP a été effectué sur l'IDE IntelliJ, tandis que la rédaction de ce rapport a été effectué sur VSCode, avec différentes extension pour le MarkDown, ainsi que l'orthographe en français.

L'intelligence artificielle (GPT 3.5), a été utilisée pour certains morceaux de code du TP1, dans une optique de gain de temps, ainsi que pour la recherche et l'explication de certains termes précis.

