package Elevator;

import Common.DirectionType;
import Common.Person;
import Floor.Floor;
import java.util.ArrayList;
import java.util.Iterator;

public class Elevator {
  private final DirectionState directionStateBlocked;
  private final DirectionState directionStateDestination;
  private final DirectionState directionStateDown;
  private final DirectionState directionStateUp;
  private final DirectionState directionStateWaiting;
  
  private final int capacity;
  private int currentLevel;
  private final boolean[] destinations;
  private DirectionType direction;
  private DirectionState directionState;
  private final Floor[] floors;
  private final ArrayList<Person> passengers;
  private int totalDistance;
  private int totalPersonsDown;
  private int totalPersonUp;
  private int totalStop;

  public Elevator(Floor[] floors) {
    directionStateBlocked = new DirectionStateBlocked(this);
    directionStateDestination = new DirectionStateDestination(this);
    directionStateDown = new DirectionStateDown(this);
    directionStateUp = new DirectionStateUp(this);
    directionStateWaiting = new DirectionStateWaiting(this);

    this.capacity = 12;
    destinations = new boolean[floors.length];
    this.floors = floors;
    passengers = new ArrayList<>();
    reset();
  }
  
  public void act() {
    directionState.act();
  }
  
  void addStop() {
    totalStop++;
  }
  
  void addPassenger(Person person) {
    int destination = person.getDestination();
    if (destination == currentLevel) {
      throw new IllegalArgumentException("Passenger cannot go to the current level");
    } else {
      setDestination(destination);
      passengers.add(person);
      if(destination < currentLevel) {
        totalPersonsDown++;
      } else {
        totalPersonUp++;
      }
    }
  }

  public void block() {
    directionState = directionStateBlocked;
  }

  public int getCapacity() {
    return capacity;
  }

  public int getCurrentLevel() {
    return currentLevel;
  }

  boolean[] getDestinations() {
    return destinations;
  }

  public DirectionType getDirection() {
    return direction;
  }

  DirectionState getDirectionState() {
    return directionState;
  }

  DirectionState getDirectionStateBlocked() {
    return directionStateBlocked;
  }

  DirectionState getDirectionStateDown() {
    return directionStateDown;
  }

  DirectionState getDirectionStateDestination() {
    return directionStateDestination;
  }

  DirectionState getDirectionStateUp() {
    return directionStateUp;
  }

  DirectionState getDirectionStateWaiting() {
    return directionStateWaiting;
  }

  Floor[] getFloors() {
    return floors;
  }
  
  public int getNbPassengers() {
    return passengers.size();
  }

  ArrayList<Person> getPassengers() {
    return passengers;
  }

  public int getTotalDistance() {
    return totalDistance;
  }

  public int getTotalPersonsDown() {
    return totalPersonsDown;
  }

  public int getTotalPersonsUp() {
    return totalPersonUp;
  }

  public int getTotalStop() {
    return totalStop;
  }

  int goDown() {
    if(isOnFloor()) {
      throw new IndexOutOfBoundsException("Elevator on floor");
    }
    currentLevel--;
    totalDistance++;
    return currentLevel;
  }

  int goUp() {
    if(isOnTop()) {
      throw new IndexOutOfBoundsException("Elevator on top");
    }
    currentLevel++;
    totalDistance++;
    return currentLevel;
  }
  public boolean isBlocked() {
    return directionState.IsBlocked();
  }

  public boolean isDestination(int level) {
    return destinations[level];
  }
  
  public boolean isInDestination() {
    return destinations[currentLevel];
  }

  public boolean isOnTop() {
    return currentLevel == (destinations.length - 1);
  }
  
  public boolean isWaiting() {
    return directionState == directionStateWaiting;
  }

  public boolean isOnFloor() {
    return currentLevel == 0;
  }

  void removePassengers(int level) {
    for (Iterator<Person> iterator = passengers.iterator(); iterator.hasNext();) {
      Person next = iterator.next();
      if (next.getDestination() == level) {
        iterator.remove();
      }
    }
  }

  public final void reset() {
    currentLevel = 0;
    for (int i = 0; i < destinations.length; i++) {
      destinations[i] = false;
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

  void setDirectionState(DirectionState directionState) {
    this.directionState = directionState;
  }
  
  public void setDestination(int level) {
    directionState.setDestination(level);
  }
  
  public void unBlock() {
    directionState = directionStateWaiting;
  }
}
