package Elevator;

import Common.DirectionType;

class DirectionStateWaiting extends DirectionState {

  public DirectionStateWaiting(Elevator elevator) {
    super(elevator);
  }

  @Override
  void act() {
    if (hasMoreDestinations(DirectionType.Down)) {
      elevator.setDirection(DirectionType.Down);
      elevator.setDirectionState(elevator.getDirectionStateDown());
    } else if (hasMoreDestinations(DirectionType.Up)) {
      elevator.setDirection(DirectionType.Up);
      elevator.setDirectionState(elevator.getDirectionStateUp());
    } else {
      elevator.setDirection(DirectionType.Stopped);
    }
  }
}
