package ElevatorController;

import Common.DirectionType;
import Elevator.Elevator;
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
    int tryCount;
    Elevator selectedElevator;
    int selectedIndex;
    for (Elevator elevator : elevators) {
      if (!elevator.isBlocked()) {
        allBlocked = false;
        break;
      }
    }
    if (allBlocked) {
      throw new UnsupportedOperationException("All elevators are blocked! Please use stairs.");
    }
    selectedIndex = random.nextInt(elevators.size());
    selectedElevator = elevators.get(selectedIndex);
    tryCount = 0;
    while (selectedElevator.isBlocked() || (selectedElevator.getCurrentLevel() == level)) {      
      selectedIndex++;
      if (selectedIndex >= elevators.size()) {
        selectedIndex = 0;
      }
      tryCount++;
      if(tryCount >= elevators.size()) {
        throw new IllegalStateException("No elevator for this floor. Try again later.");
      }
    }
    selectedElevator.setDestination(level);
  }
}
