package Elevator;

class DirectionStateDestination extends DirectionState {

  public DirectionStateDestination(Elevator elevator) {
    super(elevator);
  }


  @Override
  void act() {
    // on fait descendre les passagers à destination
    // on fait monter les passagers qui vont dans la direction de l'ascenseur
    // on ajoute les destinations des passagers montant
    // on vérifie l'état de l'ascenseur
  }
}
