package Elevator;

import Common.DirectionType;
import Common.Person;
import Floor.Floor;
import java.util.ArrayList;
import java.util.Iterator;

class DirectionStateDestination extends DirectionState {

  public DirectionStateDestination(Elevator elevator) {
    super(elevator);
  }

  @Override
  void act() {
    int currentLevel = elevator.getCurrentLevel();
    Floor currentFloor = elevator.getFloors()[elevator.getCurrentLevel()];
    ArrayList<Person> passengers = elevator.getPassengers();
    boolean destinations[] = elevator.getDestinations();
    DirectionType currentDirection = elevator.getDirection();
    
    // on est arrêté, on ajouter au total
    elevator.addStop();
    
    // l'étage courant n'est plus une destination
    destinations[currentLevel] = false;
    
    // on fait descendre les passagers arrivés à destination
    for (Iterator<Person> iterator = passengers.iterator(); iterator.hasNext();) {
      Person next = iterator.next();
      if (next.getDestination() == currentLevel) {
        iterator.remove();
      }
    }
    
    // on fait monter les personnes qui vont dans le sens de l'ascenseur
    if(elevator.isOnFloor() && currentDirection == DirectionType.Down) {
      elevator.setDirection(DirectionType.Up);
    } else if(elevator.isOnTop() && currentDirection == DirectionType.Up) {
      elevator.setDirection(DirectionType.Down);
    }
    currentDirection = elevator.getDirection();
    currentFloor.getPersons(elevator.getCapacity() - passengers.size(), elevator.getDirection()).stream().map((passenger) -> {
      passengers.add(passenger);
      return passenger;
    }).forEach((passenger) -> {
      destinations[passenger.getDestination()] = true;
    });
    
    // on définit la direction de l'ascenseur
    if (currentDirection == DirectionType.Down || currentDirection == DirectionType.Stopped) {
      if (hasMoreDestinations(DirectionType.Down)) {
        elevator.setDirection(DirectionType.Down);
        elevator.setDirectionState(elevator.getDirectionStateDown());
      } else if(hasMoreDestinations(DirectionType.Up)){
        elevator.setDirection(DirectionType.Up);
        elevator.setDirectionState(elevator.getDirectionStateUp());
      } else {
        elevator.setDirection(DirectionType.Stopped);
        elevator.setDirectionState(elevator.getDirectionStateWaiting());
      }
    } else {
      if (hasMoreDestinations(DirectionType.Up)) {
        elevator.setDirection(DirectionType.Up);
        elevator.setDirectionState(elevator.getDirectionStateUp());
      } else if(hasMoreDestinations(DirectionType.Down)) {
        elevator.setDirection(DirectionType.Down);
        elevator.setDirectionState(elevator.getDirectionStateDown());
      } else {
        elevator.setDirection(DirectionType.Stopped);
        elevator.setDirectionState(elevator.getDirectionStateWaiting());
      }
    }
  }
}
