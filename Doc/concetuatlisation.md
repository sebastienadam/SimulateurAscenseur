# Conceptualisation

Un immeuble possède plusieurs étages et plusieurs ascenseur.

## Les étages

À chaque étage, il y a des boutons pour appeler les ascenseurs. Un bouton pour monter et un bouton pour descendre. La GUI va définir des boutons pour ajouter ou retirer des personnes en attente d'un ascenseur. La personne connait l'étage où elle doit aller (généré aléatoirement). En fonction de la destination des personnes et de l'étage courant, le bouton d'appel vers le haut ou le bas sera activé.

Il est possible que des personnes en attente d'un ascenseur s'en aille avant l'arrivée de ce dernier. Si une personne a demandé à descendre et qu'elle s'en va avant l'arrivée de l'ascenseur, le bouton d'appel reste activé.

Une fois qu'un ascenseur s'arrête à un étage, les personnes montent automatiquement, suivant leur destination et le sens de l'ascenseur.

## Les ascenseurs

Les ascenseurs peuvent avoir différents états:
* En attente
* En montée
* En descente
* Bloqué

Les ascenseurs auront une capacité maximale et un nombre d'occupant.

La destination de l'ascenseur est déterminée par les appels des étages et la destination des personnes présentes dans l'ascenseur.

Un contrôleur va gérer les ascenseurs. Il vérifie si un ascenseur est en route vers les étages où des personnes sont en attente.