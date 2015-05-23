package Floor;

import Common.Person;
import Common.DirectionType;
import ElevatorController.ElevatorController;
import java.util.ArrayList;
import java.util.Iterator;

public class Floor {

  private final int level;
  private final ArrayList<Person> waitingPersons;
  private boolean callDown;
  private boolean callUp;
  private final ElevatorController elevatorController;

  public Floor(int level, ElevatorController elevatorController) {
    this.waitingPersons = new ArrayList<>();
    this.level = level;
    this.elevatorController = elevatorController;
    reset();
  }

  public void addPerson(Person newPerson) {
    if (newPerson == null) {
      throw new NullPointerException("newPerson can not be null");
    } else if(newPerson.getDestination() == level) {
      throw new IllegalArgumentException("Destination cannot be the floor itself");
    } else {
      waitingPersons.add(newPerson);
      DirectionType newPersonDirection = getDirection(newPerson.getDestination());
      if(newPersonDirection == DirectionType.Down) {
        callDown = true;
      } else {
        callUp = true;
      }
      elevatorController.requestElevator(level, newPersonDirection);
    }
  }

  public boolean getCallDown() {
    return callDown;
  }

  public boolean getCallUp() {
    return callUp;
  }

  private DirectionType getDirection(int destination) {
    if (destination < level) {
      return DirectionType.Down;
    } else if (destination > level) {
      return DirectionType.Up;
    } else {
      throw new IllegalArgumentException("Destination cannot be the floor itself");
    }
  }

  public int getLevel() {
    return level;
  }

  public int getNbWaitingPersons() {
    return waitingPersons.size();
  }

  public int getNbWaitingPersonsDown() {
    int nbWaitingPersons = 0;
    nbWaitingPersons = waitingPersons.stream().filter((waitingPerson) -> (getDirection(waitingPerson.getDestination()) == DirectionType.Down)).map((_item) -> 1).reduce(nbWaitingPersons, Integer::sum);
    return nbWaitingPersons;
  }

  public int getNbWaitingPersonsUp() {
    int nbWaitingPersons = 0;
    nbWaitingPersons = waitingPersons.stream().filter((waitingPerson) -> (getDirection(waitingPerson.getDestination()) == DirectionType.Up)).map((_item) -> 1).reduce(nbWaitingPersons, Integer::sum);
    return nbWaitingPersons;
  }

  public ArrayList<Person> getPersons(int max, DirectionType direction) {
    if (max < 0) {
      throw new IllegalArgumentException("max cannot be negative");
    } else {
      ArrayList<Person> returnedList = new ArrayList<>();
      for (Iterator<Person> iterator = waitingPersons.iterator(); iterator.hasNext();) {
        Person waitingPerson = iterator.next();
        if (returnedList.size() >= max) {
          break;
        }
        if ((direction == DirectionType.Down) && (waitingPerson.getDestination() < level)) {
          returnedList.add(waitingPerson);
          iterator.remove();
        } else if ((direction == DirectionType.Up) && (waitingPerson.getDestination() > level)) {
          returnedList.add(waitingPerson);
          iterator.remove();
        }
      }
      if (getNbWaitingPersonsDown() == 0) {
        callDown = false;
      } else if (direction == DirectionType.Down) {
        elevatorController.requestElevator(level, direction);
      }
      if (getNbWaitingPersonsUp() == 0) {
        callUp = false;
      } else if (direction == DirectionType.Up) {
        elevatorController.requestElevator(level, direction);
      }
      return returnedList;
    }
  }

  public final void reset() {
    waitingPersons.clear();
    callDown = false;
    callUp = false;
  }
}
