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
  private Floor floor0;
  @Mock
  private Floor floor1;
  @Mock
  private Floor floor2;
  @Mock
  private Floor floor3;
  @Mock
  private Floor floor4;

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
    Floor[] floors = new Floor[]{floor1, floor2, floor3};
    ArrayList<Elevator> elevators = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      elevators.add(new Elevator(floors));
    }
    int level = 2;
    DirectionType direction = DirectionType.Down;
    elevatorSelector.SendToFloor(elevators, level, direction);
    sent = false;
    for (Elevator elevator : elevators) {
      if(elevator.isDestination(level)) {
        System.out.println("Ascenseur envoyé : "+elevators.indexOf(elevator));
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
    Elevator elevator;
    Floor[] floors = new Floor[]{floor0, floor1, floor2, floor3, floor4};
    ArrayList<Elevator> elevators = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      elevator = new Elevator(floors);
      elevator.block();
      elevators.add(elevator);
    }
    int level = 2;
    DirectionType direction = DirectionType.Down;
    elevatorSelector.SendToFloor(elevators, level, direction);
  }

  /**
   * Test of SendToFloor method, of class RandomElevatorSelector.
   */
  @Test(expected = IllegalStateException.class)
  public void testSendToFloorAlreaddyOnFloor() {
    Floor[] floors = new Floor[]{floor1, floor2, floor3};
    ArrayList<Elevator> elevators = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      elevators.add(new Elevator(floors));
    }
    int level = 0;
    DirectionType direction = DirectionType.Down;
    elevatorSelector.SendToFloor(elevators, level, direction);
  }
}
