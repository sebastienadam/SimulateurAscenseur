package ElevatorController;

import Elevator.Elevator;
import Floor.Floor;
import java.util.ArrayList;

public class ElevatorController {
  private final IElevatorSelector elevatorSelector;
  private final ArrayList<Elevator> elevators;

  public ElevatorController(int nbElevators, int nbFloors) {
    this.elevatorSelector = new RandomElevatorSelector();
    this.elevators = new ArrayList<>();
    for (int i = 0; i < nbElevators; i++) {
      elevators.add(new Elevator(nbFloors));
    }
  }
  
  public void requestElevator(Floor floor) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public void reset() {
    for(Elevator elevator : elevators) {
      elevator.reset();
    }
  }
}