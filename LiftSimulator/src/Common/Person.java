package Common;

public class Person {
  private final String name;
  private final int destination;

  public Person(String name, int destination) {
    this.name = name;
    this.destination = destination;
  }

  public String getName() {
    return name;
  }

  public int getDestination() {
    return destination;
  }
}
