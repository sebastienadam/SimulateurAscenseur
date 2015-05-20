package Elevator;

import Common.DirectionType;

class DirectionStateUp extends DirectionState {

  public DirectionStateUp(Elevator elevator) {
    super(elevator);
  }

  @Override
  void act() {
    if (elevator.isOnTop()) {
      if(hasMoreDestinations(DirectionType.Down)) {
        elevator.setDirection(DirectionType.Down);
        elevator.setDirectionState(elevator.getDirectionStateDown());
      } else {
        elevator.setDirection(DirectionType.Stopped);
        elevator.setDirectionState(elevator.getDirectionStateWaiting());
      }
    } else {
      elevator.goUp();
      if (elevator.isInDestination()) {
        elevator.setDirectionState(elevator.getDirectionStateDestination());
      }
    }
  }
}
