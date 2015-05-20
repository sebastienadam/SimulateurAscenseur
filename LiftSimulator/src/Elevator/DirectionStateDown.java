package Elevator;

import Common.DirectionType;

class DirectionStateDown extends DirectionState {

  public DirectionStateDown(Elevator elevator) {
    super(elevator);
  }

  @Override
  void act() {
    if (elevator.isOnFloor()) {
      if(hasMoreDestinations(DirectionType.Up)) {
        elevator.setDirection(DirectionType.Up);
        elevator.setDirectionState(elevator.getDirectionStateUp());
      } else {
        elevator.setDirection(DirectionType.Stopped);
        elevator.setDirectionState(elevator.getDirectionStateWaiting());
      }
    } else {
      elevator.goDown();
      if (elevator.isInDestination()) {
        elevator.setDirectionState(elevator.getDirectionStateDestination());
      }
    }
  }
}
