package Elevator;

import Floor.Floor;

interface IDirectionState {
  void Act();
  boolean IsBlocked();
  void setDestination(Floor floor);
}
