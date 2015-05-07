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

Les ascenseurs auront une capacité maximale et un nombre d'occupants. Il aura également une liste d'étages de destination.

La destination de l'ascenseur est déterminée par les appels des étages et la destination des personnes présentes dans l'ascenseur.

### Le contrôleur

Un contrôleur va gérer les ascenseurs. Lorsqu'un bouton d'appel est activé sur un étage, le contrôleur va vérifier qu'un ascenseur est en route pour cet étage. Cela se fera en ajoutant une destination à un des ascenseur et en changeant éventuellement son état.

À intervalles réguliers il donnera l'ordre d'action aux ascenseurs. Suivant l'état de ces derniers l'ascenseur montera, descendra ou restera à son étage. Si l'ascenseur est arrivé à destination, il chargera et déchargera les personnes.