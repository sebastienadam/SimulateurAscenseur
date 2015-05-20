package Elevator;

class DirectionStateBlocked extends DirectionState {

  public DirectionStateBlocked(Elevator elevator) {
    super(elevator);
  }

  @Override
  public boolean IsBlocked() {
    return true;
  }
  
  @Override
  protected void setDirection() {
    throw new IllegalStateException("Elevator is blocked!");
  }
}
