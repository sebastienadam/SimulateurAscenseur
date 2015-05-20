package Elevator;

import Floor.Floor;

class DirectionStateBlocked extends DirectionState {

  public DirectionStateBlocked(Elevator elevator) {
    super(elevator);
  }
  
  @Override
  public boolean IsBlocked() {
    return true;
  }
  
  @Override
  void setDestination(Floor floor) {
    throw new IllegalStateException("Elevator is blocked!");
  }
}
