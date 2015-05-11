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
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public boolean getCallDown() {
    return callDown;
  }

  public boolean getCallUp() {
    return callUp;
  }

  public int getLevel() {
    return level;
  }
  
  public int getNbWaitingPersons() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public int getNbWaitingPersonsDown() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public int getNbWaitingPersonsUp() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public ArrayList<Person> getPersons(int max, DirectionType direction) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public void reset() {
    waitingPersons.clear();
    callDown = false;
    callUp = false;
  }
}