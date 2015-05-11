package ElevatorController;

import Elevator.Elevator;
import Common.DirectionType;
import Floor.Floor;
import java.util.ArrayList;

interface IElevatorSelector {
  void SendToFloor(ArrayList<Elevator> elevators, Floor floor, DirectionType direction);
}