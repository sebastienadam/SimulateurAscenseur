package Floor;

import Common.Person;
import Common.DirectionType;
import ElevatorController.ElevatorController;
import java.util.ArrayList;

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
      elevatorController.requestElevator(this, getDirection(newPerson.getDestination()));
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
      for (Person waitingPerson : waitingPersons) {
        if (returnedList.size() >= max) {
          break;
        }
        if ((direction == DirectionType.Down) && (waitingPerson.getDestination() < level)) {
          returnedList.add(waitingPerson);
          waitingPersons.remove(waitingPerson);
        } else if ((direction == DirectionType.Up) && (waitingPerson.getDestination() > level)) {
          returnedList.add(waitingPerson);
          waitingPersons.remove(waitingPerson);
        }
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
