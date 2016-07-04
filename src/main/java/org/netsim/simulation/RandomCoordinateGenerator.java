package org.netsim.simulation;

import java.awt.Point;
import java.util.Random;

public class RandomCoordinateGenerator {
  private int xUpperBound;
  private int yUpperBound;
  private Random generator;
  private static RandomCoordinateGenerator instance;
  private final int INT_MAX_STEP_LENGTH = 20;

  private RandomCoordinateGenerator() {
    xUpperBound = 100;
    yUpperBound = 100;
    generator = new Random();
  }

  public static RandomCoordinateGenerator getInstance() {
    if (instance == null)
      instance = new RandomCoordinateGenerator();
    return instance;
  }

  public void setBounds(int x, int y) {
    xUpperBound = x;
    yUpperBound = y;
  }

  public Point getNextPosition() {
    Integer x = generator.nextInt(INT_MAX_STEP_LENGTH);
    Integer y = generator.nextInt(INT_MAX_STEP_LENGTH);
    Point position = new Point(x, y);
    return position;
  }

  public Point getNewPosition() {
    Integer x = generator.nextInt(xUpperBound);
    Integer y = generator.nextInt(yUpperBound);
    Point position = new Point(x, y);
    return position;
  }
}
