package ElevatorController;

import Common.DirectionType;
import Elevator.Elevator;
import Floor.Floor;
import java.util.ArrayList;

public class ElevatorController {
  private final IElevatorSelector elevatorSelector;
  private final ArrayList<Elevator> elevators;
  private final Floor[] floors;

  public ElevatorController(int nbElevators, int nbFloors) {
    this.elevatorSelector = new RandomElevatorSelector();
    this.floors = new Floor[nbFloors];
    for (int i = 0; i < nbFloors; i++) {
      floors[i] = new Floor(i, this);
    }
    this.elevators = new ArrayList<>();
    for (int i = 0; i < nbElevators; i++) {
      elevators.add(new Elevator(floors));
    }
  }
  
  public void requestElevator(int level, DirectionType direction) {
    elevatorSelector.SendToFloor(elevators, level, direction);
  }
  
  public void reset() {
    elevators.stream().forEach((elevator) -> elevator.reset());
    for (Floor floor : floors) {
      floor.reset();
    }
  }
}