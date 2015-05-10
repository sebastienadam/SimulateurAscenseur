package Elevator;

interface IDirectionState {
  void Act();
  boolean IsBlocked();
  void setDestination(DirectionType direction);
}
