import java.util.NoSuchElementException;

/**
 * Rectangle class.
 */
public class Rectangle {
  private final int[] rectangle;
  private final int x;
  private final int y;
  private final int height;
  private final int width;

  /**
   * Constructor for rectangle class.
   *
   * @param x      bottom left x coordinate
   * @param y      bottom left y coordinate
   * @param height height of rectangle
   * @param width  width of rectangle
   * @throws IllegalArgumentException when width or height <=0
   */
  public Rectangle(int x, int y, int width, int height) throws IllegalArgumentException {

    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("Negative or zero height is not accepted");
    }

    this.x = x;
    this.y = y;
    this.height = height;
    this.width = width;
    this.rectangle = new int[]{x, y, x + width, y + height};
  }


  /**
   * Function returns true if overlap exists between two rectangles.
   *
   * @param other is the passed rectangle object
   * @return true if there is overlap, false if there is not
   */
  public Boolean overlap(Rectangle other) {


    int xMax = Math.max(this.rectangle[0], other.rectangle[0]);
    int xMin = Math.min(this.rectangle[2], other.rectangle[2]);
    if (xMin <= xMax) {
      return false;
    } else {
      int yMax = Math.max(this.rectangle[1], other.rectangle[1]);
      int yMin = Math.min(this.rectangle[3], other.rectangle[3]);
      return yMin > yMax;
    }

  }

  /**
   * Returns a Rectangle object that represents the intersection of rectangle and other.
   *
   * @param other is the passed rectangle
   * @return intersection is a rectangle object
   * @throws NoSuchElementException when intersection does not exist
   */
  public Rectangle intersect(Rectangle other) throws NoSuchElementException {
    Boolean isOverlapping = overlap(other);
    if (!isOverlapping) {
      throw new NoSuchElementException("Intersection does not exist");
    }
    int xLeft = Math.max(this.rectangle[0], other.rectangle[0]);
    int yLeft = Math.min(this.rectangle[3], other.rectangle[3]);
    int xMin = Math.min(this.rectangle[2], other.rectangle[2]);
    int yMax = Math.max(this.rectangle[1], other.rectangle[1]);
    int xWidth = xMin - xLeft;
    int yHeight = yLeft - yMax;
    Rectangle intersection = new Rectangle(xLeft, yLeft - yHeight, xWidth, yHeight);
    return intersection;
  }

  /**
   * Returns a Rectangle object that represents the union of rectangle and other.
   *
   * @param other is the passed rectangle
   * @return unionRectangle is the smallest rectangle object that covers both rectangles
   */
  public Rectangle union(Rectangle other) {
    int x1 = Math.min(this.x, other.x);
    int x2 = Math.max(this.x + this.width, other.x + other.width);
    int y1 = Math.min(this.y, other.y);
    int y2 = Math.max(this.y + this.height, other.y + other.height);
    return new Rectangle(x1, y1, x2 - x1, y2 - y1);
  }

  public String toString() {
    return "x:" + this.x + ", y:" + this.y + ", w:" + this.width + ", h:" + this.height;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) { //backward compatibility with default equals
      return true;
    }
    if (!(o instanceof Rectangle)) { // If o isn't the right class then it can't be equal:
      return false;
    }
    Rectangle that = (Rectangle) o; // The successful instanceof check means our cast will succeed:
    return that.toString().equals(this.toString());
  }

  @Override
  public int hashCode() {
    return String.CASE_INSENSITIVE_ORDER.hashCode();
  }

}
