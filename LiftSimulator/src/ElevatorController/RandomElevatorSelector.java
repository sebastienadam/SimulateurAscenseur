package ElevatorController;

import Common.DirectionType;
import Elevator.Elevator;
import Floor.Floor;
import java.util.ArrayList;
import java.util.Random;

class RandomElevatorSelector implements IElevatorSelector {
  private final Random random;

  public RandomElevatorSelector() {
    random = new Random();
  }

  @Override
  public void SendToFloor(ArrayList<Elevator> elevators, Floor floor, DirectionType direction) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
