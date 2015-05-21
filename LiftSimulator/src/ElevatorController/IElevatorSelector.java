package ElevatorController;

import Elevator.Elevator;
import Common.DirectionType;
import java.util.ArrayList;

interface IElevatorSelector {
  void SendToFloor(ArrayList<Elevator> elevators, int level, DirectionType direction);
}