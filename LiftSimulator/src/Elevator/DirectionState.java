package Elevator;

import Common.DirectionType;
import Floor.Floor;
import java.util.Arrays;

abstract class DirectionState {

  protected Elevator elevator;

  public DirectionState(Elevator elevator) {
    this.elevator = elevator;
  }

  public void Act() {
    setDirection();
  }

  public boolean IsBlocked() {
    return false;
  }

  public void setDestination(Floor floor) {
  }

  protected void setDirection() {
  }

  protected boolean hasMoreDestinations(DirectionType direction) {
    Floor[] nextDestinations;
    boolean result;
    if (direction == DirectionType.Down) {
      if (elevator.isOnFloor()) {
        nextDestinations = null;
      } else {
        nextDestinations = Arrays.copyOfRange(elevator.getDestinations(), 0, elevator.getCurrentLevel());
      }
    } else if (direction == DirectionType.Up) {
      if (elevator.isOnTop()) {
        nextDestinations = null;
      } else {
        nextDestinations = Arrays.copyOfRange(elevator.getDestinations(), elevator.getCurrentLevel() + 1, elevator.getDestinations().length);
      }
    } else {
      throw new IllegalArgumentException("direction must be Up or Down");
    }
    result = false;
    if (nextDestinations != null) {
      for (Floor destination : nextDestinations) {
        if (destination != null) {
          result = true;
          break;
        }
      }
    }
    return result;
  }
}
