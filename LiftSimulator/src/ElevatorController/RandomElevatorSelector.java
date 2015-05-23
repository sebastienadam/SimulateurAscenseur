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
  public void SendToFloor(ArrayList<Elevator> elevators, int level, DirectionType direction) {
    boolean allBlocked = true;
    Elevator selectedElevator;
    int selectedIndex;
    for (Elevator elevator : elevators) {
      if (!elevator.isBlocked()) {
        allBlocked = false;
        break;
      }
    }
    if (allBlocked) {
      throw new UnsupportedOperationException("All elevators are blocked! please use stairs.");
    }
    selectedIndex = random.nextInt(elevators.size());
    selectedElevator = elevators.get(selectedIndex);
    while (selectedElevator.isBlocked() && (selectedElevator.getCurrentLevel() != level)) {      
      selectedIndex++;
      if (selectedIndex >= elevators.size()) {
        selectedIndex = 0;
      }
    }
    selectedElevator.setDestination(level);
  }
}
