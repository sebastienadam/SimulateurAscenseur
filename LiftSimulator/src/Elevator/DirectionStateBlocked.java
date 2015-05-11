package Elevator;

class DirectionStateBlocked extends DirectionState {

  public DirectionStateBlocked(Elevator elevator) {
    super(elevator);
  }

  @Override
  public boolean IsBlocked() {
    return true;
  }
}
