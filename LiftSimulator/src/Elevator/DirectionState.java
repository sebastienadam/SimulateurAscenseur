package Elevator;

abstract class DirectionState implements IDirectionState {
  private Elevator concernedElevator;
  private DirectionType direction;

  @Override
  public void Act() {}
  
  @Override
  public boolean IsBlocked() {
    return false;
  }
  
  @Override
  public void setDestination(DirectionType direction) {}
}
