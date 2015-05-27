/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elevator;

import Common.DirectionType;
import Common.Person;
import ElevatorController.ElevatorController;
import Floor.Floor;
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
public class ElevatorTest {

  private Elevator elevator;
  @Mock
  private ElevatorController controller;
  private Floor[] floors;
  int NbFloors = 6;

  public ElevatorTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
    floors = new Floor[NbFloors];
    for (int i = 0; i < NbFloors; i++) {
      floors[i] = new Floor(i, controller);
    }
    elevator = new Elevator(floors);
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of act method, of class Elevator.
   */
  @Test
  public void testDirectionStateInit() {
    DirectionState expResult = elevator.getDirectionStateWaiting();
    DirectionState result = elevator.getDirectionState();
    assertEquals(expResult, result);
  }

  /**
   * Test of act method, of class Elevator.
   */
  @Test
  public void testActInitUp() {
    DirectionState expResult;
    DirectionState result;
    elevator.setDestination(2);
    elevator.act();
    expResult = elevator.getDirectionStateUp();
    result = elevator.getDirectionState();
    assertEquals(expResult, result);
  }

  /**
   * Test of act method, of class Elevator.
   */
  @Test
  public void testActUpped() {
    int expResult;
    int result;
    elevator.setDestination(2);
    elevator.act();
    elevator.act();
    expResult = 1;
    result = elevator.getCurrentLevel();
    assertEquals(expResult, result);
  }

  /**
   * Test of act method, of class Elevator.
   */
  @Test
  public void testActUpDestination() {
    elevator.setDestination(2);
    elevator.act();
    elevator.act();
    elevator.act();
    assertEquals(2, elevator.getCurrentLevel());
    assertEquals(elevator.getDirectionStateDestination(), elevator.getDirectionState());
  }

  /**
   * Test of act method, of class Elevator.
   */
  @Test
  public void testActUpDestinationWaiting() {
    elevator.setDestination(2);
    elevator.act();
    elevator.act();
    elevator.act();
    elevator.act();
    assertEquals(2, elevator.getCurrentLevel());
    assertEquals(elevator.getDirectionStateWaiting(), elevator.getDirectionState());
  }

  /**
   * Test of act method, of class Elevator.
   */
  @Test
  public void testActDestinationRemovePassenger() {
    int level = 2;
    elevator.addPassenger(new Person("Pat", level));
    elevator.addPassenger(new Person("Pat", level));
    do {
      elevator.act();
    } while (!elevator.isWaiting());
    assertEquals(level, elevator.getCurrentLevel());
    assertEquals(0, elevator.getNbPassengers());
  }

  /**
   * Test of act method, of class Elevator.
   */
  @Test
  public void testActUpDown() {
    int level1 = 3;
    int level2 = 1;
    elevator.setDestination(level1);
    do {
      elevator.act();
//      System.out.println("L"+elevator.getCurrentLevel()+" - "+elevator.getDirectionState().getClass().getName());
      if(elevator.isInDestination()) {
        elevator.setDestination(level2);
      }
    } while (!elevator.isWaiting());
    assertEquals(level2, elevator.getCurrentLevel());
  }

  /**
   * Test of act method, of class Elevator.
   */
  @Test
  public void testActUpDownUp() {
    int idx = 0;
    int level[] = {2,1,3};
    elevator.setDestination(level[idx]);
    idx++;
    do {
      elevator.act();
//      System.out.println("L"+elevator.getCurrentLevel()+" - "+elevator.getDirectionState().getClass().getName());
      if(elevator.isInDestination() && idx < level.length) {
        elevator.setDestination(level[idx]);
        idx++;
      }
    } while (!elevator.isWaiting());
    assertEquals(level[idx-1], elevator.getCurrentLevel());
  }

  /**
   * Test of addPassenger method, of class Elevator.
   */
  @Test
  public void testAddPassenger() {
    int destination = 2;
    elevator.addPassenger(new Person("pat", destination));
    int expResult = 1;
    int result = elevator.getNbPassengers();
    assertEquals(expResult, result);
    assertTrue(elevator.isDestination(destination));
  }

  /**
   * Test of addPassenger method, of class Elevator.
   */
  @Test
  public void testAddPassengerDown() {
    int destination = 0;
    int expResult;
    int result;
    elevator.goUp();
    elevator.goUp();
    elevator.addPassenger(new Person("pat", destination));
    assertTrue(elevator.isDestination(destination));
    expResult = 1;
    result = elevator.getNbPassengers();
    assertEquals(expResult, result);
    expResult = 0;
    result = elevator.getTotalPersonsUp();
    assertEquals(expResult, result);
    expResult = 1;
    result = elevator.getTotalPersonsDown();
    assertEquals(expResult, result);
  }

  /**
   * Test of addPassenger method, of class Elevator.
   */
  @Test
  public void testAddPassengerUp() {
    int destination = 3;
    int expResult;
    int result;
    elevator.addPassenger(new Person("pat", destination));
    assertTrue(elevator.isDestination(destination));
    expResult = 1;
    result = elevator.getNbPassengers();
    assertEquals(expResult, result);
    expResult = 1;
    result = elevator.getTotalPersonsUp();
    assertEquals(expResult, result);
    expResult = 0;
    result = elevator.getTotalPersonsDown();
    assertEquals(expResult, result);
  }

  /**
   * Test of addPassenger method, of class Elevator.
   */
  @Test(expected = IllegalStateException.class)
  public void testAddPassengerBlocked() {
    elevator.block();
    elevator.addPassenger(new Person("pat", 1));
  }

  /**
   * Test of addPassenger method, of class Elevator.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddPassengerSameLevel() {
    elevator.addPassenger(new Person("pat", 0));
  }

  /**
   * Test of getFloors method, of class Elevator.
   */
  @Test
  public void testGetFloors() {
    Floor[] expResult = floors;
    Floor[] result = elevator.getFloors();
    assertArrayEquals(expResult, result);
  }

  /**
   * Test of getNbPassengers method, of class Elevator.
   */
  @Test
  public void testGetNbPassengers() {
    int expResult = 0;
    int result = elevator.getNbPassengers();
    assertEquals(expResult, result);
    elevator.addPassenger(new Person("pat", 1));
    elevator.addPassenger(new Person("pat", 1));
    expResult = 2;
    result = elevator.getNbPassengers();
    assertEquals(expResult, result);
  }

  /**
   * Test of getTotalDistance method, of class Elevator.
   */
  @Test
  public void testGetTotalDistance() {
    elevator.goUp();
    elevator.goUp();
    elevator.goDown();
    int expResult = 3;
    int result = elevator.getTotalDistance();
    assertEquals(expResult, result);
  }

  /**
   * Test of getTotalPersonsDown method, of class Elevator.
   */
  @Test
  public void testGetTotalPersonsDown() {
    int expResult;
    int result;
    expResult = 0;
    result = elevator.getTotalPersonsDown();
    assertEquals(expResult, result);
    elevator.goUp();
    elevator.goUp();
    elevator.addPassenger(new Person("pat", 0));
    elevator.addPassenger(new Person("pat", 0));
    expResult = 2;
    result = elevator.getTotalPersonsDown();
    assertEquals(expResult, result);
  }

  /**
   * Test of getTotalPersonsUp method, of class Elevator.
   */
  @Test
  public void testGetTotalPersonsUp() {
    int expResult;
    int result;
    expResult = 0;
    result = elevator.getTotalPersonsUp();
    assertEquals(expResult, result);
    elevator.addPassenger(new Person("pat", 2));
    elevator.addPassenger(new Person("pat", 2));
    expResult = 2;
    result = elevator.getTotalPersonsUp();
    assertEquals(expResult, result);
  }

  /**
   * Test of getTotalStop method, of class Elevator.
   */
  @Test
  public void testGetTotalStop() {
    int idx = 0;
    int level[] = {2,1,3};
    elevator.setDestination(level[idx]);
    idx++;
    do {
      elevator.act();
      System.out.println("L"+elevator.getCurrentLevel()+" - "+elevator.getDirectionState().getClass().getName());
      if(elevator.isInDestination() && idx < level.length) {
        elevator.setDestination(level[idx]);
        idx++;
      }
    } while (!elevator.isWaiting());
    assertEquals(3, elevator.getTotalStop());
  }

  /**
   * Test of goDown method, of class Elevator.
   */
  @Test
  public void testGoDown() {
    int expResult;
    int result;
    elevator.goUp();
    expResult = 0;
    result = elevator.goDown();
    assertEquals(expResult, result);
    expResult = 2;
    result = elevator.getTotalDistance();
    assertEquals(expResult, result);
  }

  /**
   * Test of goDown method, of class Elevator.
   */
  @Test(expected = IndexOutOfBoundsException.class)
  public void testGoDownOnFloor() {
    elevator.goDown();
  }

  /**
   * Test of goUp method, of class Elevator.
   */
  @Test
  public void testGoUp() {
    int expResult;
    int result;
    expResult = 1;
    result = elevator.goUp();
    assertEquals(expResult, result);
    expResult = 1;
    result = elevator.getTotalDistance();
    assertEquals(expResult, result);
  }

  /**
   * Test of goUp method, of class Elevator.
   */
  @Test(expected = IndexOutOfBoundsException.class)
  public void testGoUpOnTop() {
    int level;
    do {
      level = elevator.goUp();
    } while (level <= (NbFloors - 1));
    elevator.goUp();
  }

  /**
   * Test of isDestination method, of class Elevator.
   */
  @Test
  public void testIsDestination() {
    int destination = 2;
    elevator.setDestination(destination);
    boolean result = elevator.isDestination(destination);
    assertTrue(result);
    assertFalse(elevator.isDestination(0));
  }

  /**
   * Test of isInDestination method, of class Elevator.
   */
  @Test
  public void testIsInDestination() {
    int destination = 2;
    elevator.setDestination(destination);
    elevator.goUp();
    elevator.goUp();
    boolean result = elevator.isInDestination();
    assertTrue(result);
  }

  /**
   * Test of isOnTop method, of class Elevator.
   */
  @Test
  public void testIsOnTopOnTop() {
    int level;
    do {
      level = elevator.goUp();
    } while (level < floors.length - 1);
    boolean result = elevator.isOnTop();
    assertTrue(result);
    assertTrue(level == (NbFloors - 1));
  }

  /**
   * Test of isOnTop method, of class Elevator.
   */
  @Test
  public void testIsOnTopNotOnTop() {
    boolean result = elevator.isOnTop();
    assertFalse(result);
    assertFalse(elevator.getCurrentLevel() == (NbFloors - 1));
  }

  /**
   * Test of isOnFloor method, of class Elevator.
   */
  @Test
  public void testIsOnFloorOnFloor() {
    boolean result = elevator.isOnFloor();
    assertTrue(result);
    assertEquals(0, elevator.getCurrentLevel());
  }

  /**
   * Test of isOnFloor method, of class Elevator.
   */
  @Test
  public void testIsOnFloorNotOnFloor() {
    int level = elevator.goUp();
    boolean result = elevator.isOnFloor();
    assertFalse(result);
    assertFalse(level == 0);
  }

  /**
   * Test of removePassengers method, of class Elevator.
   */
  @Test
  public void testRemovePassengers() {
    int expResult;
    int result;
    int level = 2;
    elevator.addPassenger(new Person("Pat", level));
    elevator.addPassenger(new Person("Pat", level));
    elevator.addPassenger(new Person("Pat", level + 1));
    elevator.addPassenger(new Person("Pat", level - 1));
    expResult = 4;
    result = elevator.getNbPassengers();
    assertEquals(expResult, result);
    elevator.removePassengers(level);
    expResult = 2;
    result = elevator.getNbPassengers();
    assertEquals(expResult, result);
  }

  /**
   * Test of reset method, of class Elevator.
   */
  @Test
  public void testReset() {
    int level = 2;
    elevator.addPassenger(new Person("Pat", level));
    elevator.addPassenger(new Person("Pat", level));
    elevator.setDestination(level + 2);
    elevator.goUp();
    elevator.goUp();
    elevator.goUp();
    elevator.goDown();
    elevator.block();
    elevator.reset();
    assertTrue(elevator.isOnFloor());
    assertFalse(elevator.isBlocked());
    assertFalse(elevator.isDestination(level));
    assertFalse(elevator.isDestination(level + 2));
    assertEquals(0, elevator.getCurrentLevel());
    assertEquals(0, elevator.getNbPassengers());
    assertEquals(0, elevator.getTotalDistance());
    assertEquals(0, elevator.getTotalPersonsDown());
    assertEquals(0, elevator.getTotalPersonsUp());
    assertEquals(0, elevator.getTotalStop());
    assertEquals(DirectionType.Stopped, elevator.getDirection());
  }

  /**
   * Test of unBlock method, of class Elevator.
   */
  @Test
  public void testBlock() {
    elevator.block();
    assertTrue(elevator.isBlocked());
    assertSame(elevator.getDirectionState(), elevator.getDirectionStateBlocked());
    elevator.unBlock();
    assertFalse(elevator.isBlocked());
    assertNotSame(elevator.getDirectionState(), elevator.getDirectionStateBlocked());
  }

}
