package Elevator;

import Common.DirectionType;
import Common.Person;
import Floor.Floor;
import java.util.ArrayList;

public class Elevator {
  private DirectionState directionStateBlocked;
  private DirectionState directionStateDestination;
  private DirectionState directionStateDown;
  private DirectionState directionStateUp;
  private DirectionState directionStateWaiting;
  
  private final int capacity;
  private int currentLevel;
  private final Floor[] destinations;
  private DirectionType direction;
  private DirectionState directionState;
  private final ArrayList<Person> passengers;
  private int totalDistance;
  private int totalPersonsDown;
  private int totalPersonUp;
  private int totalStop;

  public Elevator(int nbFloors) {
    directionStateBlocked = new DirectionStateBlocked(this);
    directionStateDestination = new DirectionStateDestination(this);
    directionStateDown = new DirectionStateDown(this);
    directionStateUp = new DirectionStateUp(this);
    directionStateWaiting = new DirectionStateWaiting(this);

    this.capacity = 12;
    destinations = new Floor[nbFloors];
    passengers = new ArrayList<>();
    reset();
  }
  
  public void act() {
    directionState.Act();
  }
  
  void addPassenger(Person person) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public int getCapacity() {
    return capacity;
  }

  public int getCurrentLevel() {
    return currentLevel;
  }

  Floor[] getDestinations() {
    return destinations;
  }

  public DirectionType getDirection() {
    return direction;
  }
  
  public int getNbPassengers() {
    return passengers.size();
  }

  public int getTotalDistance() {
    return totalDistance;
  }

  public int getTotalPersonsDown() {
    return totalPersonsDown;
  }

  public int getTotalPersonUp() {
    return totalPersonUp;
  }

  public int getTotalStop() {
    return totalStop;
  }

  public boolean isBlocked() {
    return directionState.IsBlocked();
  }

  public boolean isInDestination() {
    return destinations[currentLevel] != null;
  }

  void removePassengers(int level) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public final void reset() {
    currentLevel = 0;
    for (int i = 0; i < destinations.length; i++) {
      destinations[i] = null;
    }
    direction = DirectionType.Stopped;
    directionState = directionStateWaiting;
    passengers.clear();
    totalDistance = 0;
    totalPersonsDown = 0;
    totalPersonUp = 0;
    totalStop = 0;
  }

  void setDirection(DirectionType direction) {
    this.direction = direction;
  }
  
  public void setDestination(Floor floor) {
    directionState.setDestination(floor);
  }
}
