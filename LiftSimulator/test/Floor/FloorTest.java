package Floor;

import Common.DirectionType;
import Common.Person;
import ElevatorController.ElevatorController;
import java.util.ArrayList;
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
public class FloorTest {
  
  private Floor floor;
  
  public FloorTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    floor = new Floor(2, new ElevatorController(4, 4));
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of addPerson method, of class Floor.
   */
  @Test
  public void testAddPerson() {
    int expResult = 2;
    floor.addPerson(new Person("Pat",0));
    floor.addPerson(new Person("Piet",4));
    int result = floor.getNbWaitingPersons();
    assertEquals(expResult, result);
  }

  /**
   * Test of addPerson method, of class Floor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddPersonSameFloor() {
    floor.addPerson(new Person("Pat",2));
  }

  /**
   * Test of addPerson method, of class Floor.
   */
  @Test(expected = NullPointerException.class)
  public void testAddPersonNull() {
    floor.addPerson(null);
  }

  /**
   * Test of getCallDown method, of class Floor.
   */
  @Test
  public void testGetCallDownInit() {
    boolean expResult = false;
    boolean result = floor.getCallDown();
    assertEquals(expResult, result);
  }

  /**
   * Test of getCallDown method, of class Floor.
   */
  @Test
  public void testGetCallDownAddPersonDown() {
    boolean expResult = true;
    floor.addPerson(new Person("Pat",0));
    boolean result = floor.getCallDown();
    assertEquals(expResult, result);
  }

  /**
   * Test of getCallUp method, of class Floor.
   */
  @Test
  public void testGetCallUpInit() {
    boolean expResult = false;
    boolean result = floor.getCallUp();
    assertEquals(expResult, result);
  }

  /**
   * Test of getCallUp method, of class Floor.
   */
  @Test
  public void testGetCallUpAddPersonUp() {
    boolean expResult = true;
    floor.addPerson(new Person("Pat",4));
    boolean result = floor.getCallUp();
    assertEquals(expResult, result);
  }

  /**
   * Test of getNbWaitingPersons method, of class Floor.
   */
  @Test
  public void testGetNbWaitingPersons() {
    int expResult = 2;
    floor.addPerson(new Person("Pat",0));
    floor.addPerson(new Person("Piet",4));
    int result = floor.getNbWaitingPersons();
    assertEquals(expResult, result);
  }

  /**
   * Test of getNbWaitingPersonsDown method, of class Floor.
   */
  @Test
  public void testGetNbWaitingPersonsDown() {
    int expResult = 1;
    floor.addPerson(new Person("Pat",0));
    floor.addPerson(new Person("Piet",4));
    int result = floor.getNbWaitingPersonsDown();
    assertEquals(expResult, result);
  }

  /**
   * Test of getNbWaitingPersonsUp method, of class Floor.
   */
  @Test
  public void testGetNbWaitingPersonsUp() {
    int expResult = 1;
    floor.addPerson(new Person("Pat",0));
    floor.addPerson(new Person("Piet",4));
    int result = floor.getNbWaitingPersonsUp();
    assertEquals(expResult, result);
  }

  /**
   * Test of getPersons method, of class Floor.
   */
  @Test
  public void testGetPersonsDownAll() {
    int max = 2;
    Person p1 = new Person("Pat",0);
    Person p2 = new Person("Piet",4);
    floor.addPerson(p1);
    floor.addPerson(p2);
    DirectionType direction = DirectionType.Down;
    ArrayList<Person> expResult = new ArrayList<Person>(){{add(p1);}};
    ArrayList<Person> result = floor.getPersons(max, direction);
    assertEquals(expResult, result);
    assertEquals(0, floor.getNbWaitingPersonsDown());
    assertFalse(floor.getCallDown());
  }

  /**
   * Test of getPersons method, of class Floor.
   */
  @Test
  public void testGetPersonsDownOne() {
    int max = 1;
    Person p1 = new Person("Pat",0);
    Person p2 = new Person("Piet",0);
    floor.addPerson(p1);
    floor.addPerson(p2);
    DirectionType direction = DirectionType.Down;
    ArrayList<Person> expResult = new ArrayList<Person>(){{add(p1);}};
    ArrayList<Person> result = floor.getPersons(max, direction);
    assertEquals(expResult, result);
    assertEquals(1, floor.getNbWaitingPersonsDown());
    assertTrue(floor.getCallDown());
  }

  /**
   * Test of getPersons method, of class Floor.
   */
  @Test
  public void testGetPersonsUpAll() {
    int max = 2;
    Person p1 = new Person("Pat",0);
    Person p2 = new Person("Piet",4);
    floor.addPerson(p1);
    floor.addPerson(p2);
    DirectionType direction = DirectionType.Up;
    ArrayList<Person> expResult = new ArrayList<Person>(){{add(p2);}};
    ArrayList<Person> result = floor.getPersons(max, direction);
    assertEquals(expResult, result);
    assertEquals(0, floor.getNbWaitingPersonsUp());
    assertFalse(floor.getCallUp());
  }

  /**
   * Test of getPersons method, of class Floor.
   */
  @Test
  public void testGetPersonsUpOne() {
    int max = 1;
    Person p1 = new Person("Pat",4);
    Person p2 = new Person("Piet",4);
    floor.addPerson(p1);
    floor.addPerson(p2);
    DirectionType direction = DirectionType.Up;
    ArrayList<Person> expResult = new ArrayList<Person>(){{add(p1);}};
    ArrayList<Person> result = floor.getPersons(max, direction);
    assertEquals(expResult, result);
    assertEquals(1, floor.getNbWaitingPersonsUp());
    assertTrue(floor.getCallUp());
  }

  /**
   * Test of reset method, of class Floor.
   */
  @Test
  public void testReset() {
    floor.addPerson(new Person("Pat",0));
    floor.addPerson(new Person("Piet",4));
    floor.reset();
    assertFalse(floor.getCallUp());
    assertFalse(floor.getCallDown());
    assertEquals(2, floor.getLevel());
    assertEquals(0, floor.getNbWaitingPersons());
    assertEquals(0, floor.getNbWaitingPersonsDown());
    assertEquals(0, floor.getNbWaitingPersonsUp());
  }
  
}
