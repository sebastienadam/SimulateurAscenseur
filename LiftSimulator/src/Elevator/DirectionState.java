package Elevator;

import Floor.Floor;

abstract class DirectionState implements IDirectionState {
  protected Elevator elevator;

  public DirectionState(Elevator elevator) {
    this.elevator = elevator;
  }

  @Override
  public void Act() {
    setDirection();
  }
  
  @Override
  public boolean IsBlocked() {
    return false;
  }
  
  @Override
  public void setDestination(Floor floor) {}
  
  protected void setDirection() {}
}
