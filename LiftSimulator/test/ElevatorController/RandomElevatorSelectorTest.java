/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElevatorController;

import Common.DirectionType;
import Elevator.Elevator;
import Floor.Floor;
import java.util.ArrayList;
import org.jmock.auto.Mock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sébastien
 */
public class RandomElevatorSelectorTest {

  private RandomElevatorSelector elevatorSelector;
  @Mock
  private Floor floor;
  private Floor[] floors;
  private ArrayList<Elevator> elevators;

  public RandomElevatorSelectorTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
    elevatorSelector = new RandomElevatorSelector();
    floors = new Floor[]{floor, floor, floor, floor, floor};
    elevators = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      elevators.add(new Elevator(floors));
    }
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of SendToFloor method, of class RandomElevatorSelector.
   */
  @Test
  public void testSendToFloor() {
    boolean sent;
    int level = 2;
    DirectionType direction = DirectionType.Down;
    elevatorSelector.SendToFloor(elevators, level, direction);
    sent = false;
    for (Elevator elevator : elevators) {
      if(elevator.isDestination(level)) {
        System.out.println("''testSendToFloor'' Ascenseur envoyé : "+elevators.indexOf(elevator));
        sent = true;
        break;
      }
    }
    assertTrue(sent);
  }

  /**
   * Test of SendToFloor method, of class RandomElevatorSelector.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testSendToFloorAllBlock() {
    elevators.stream().forEach(elevator -> elevator.block());
    int level = 2;
    DirectionType direction = DirectionType.Down;
    elevatorSelector.SendToFloor(elevators, level, direction);
  }

  /**
   * Test of SendToFloor method, of class RandomElevatorSelector.
   */
  @Test
  public void testSendToFloorAlreaddyOnFloorWaiting() {
    boolean sent;
    int level = 0;
    DirectionType direction = DirectionType.Down;
    elevatorSelector.SendToFloor(elevators, level, direction);
    sent = false;
    for (Elevator elevator : elevators) {
      if(elevator.isDestination(level)) {
        System.out.println("''testSendToFloorAlreaddyOnFloorWaiting'' Ascenseur envoyé : "+elevators.indexOf(elevator));
        sent = true;
        break;
      }
    }
    assertTrue(sent);
  }

  /**
   * Test of SendToFloor method, of class RandomElevatorSelector.
   */
  @Test(expected = IllegalStateException.class)
  public void testSendToFloorAlreaddyOnFloorNotWaiting() {
    int level = 0;
    DirectionType direction = DirectionType.Down;
    elevators.stream().map((elevator) -> {
      elevator.setDestination(2);
      return elevator;
    }).forEach(elevator -> {
      elevator.act();
    });
    elevatorSelector.SendToFloor(elevators, level, direction);
  }

  /**
   * Test of SendToFloor method, of class RandomElevatorSelector.
   */
  @Test(expected = ArrayIndexOutOfBoundsException.class)
  public void testSendToFloorOutOfBounds() {
    int level = floors.length;
    DirectionType direction = DirectionType.Down;
    elevatorSelector.SendToFloor(elevators, level, direction);
  }
}
