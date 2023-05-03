import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * Test class for Rectangle implementation.
 */
public class RectangleTest {

  private Rectangle rectangle;

  @Test
  public void illegalArgumentExceptionForheightZero() {
    assertThrows(IllegalArgumentException.class, () -> new Rectangle(1, 7, 2, 0));
  }

  @Test
  public void illegalArgumentExceptionForheightNegative() {
    assertThrows(IllegalArgumentException.class, () -> new Rectangle(1, 7, 2, -1));
  }

  @Test
  public void illegalArgumentExceptionForWidthZero() {
    assertThrows(IllegalArgumentException.class, () -> new Rectangle(1, 7, 0, 2));
  }

  @Test
  public void illegalArgumentExceptionForWIdthNegative() {
    assertThrows(IllegalArgumentException.class, () -> new Rectangle(1, 7, -1, 1));
  }

  @Test
  public void overlapTouchingEdges() {
    rectangle = new Rectangle(1, 7, 2, 2);
    Rectangle rec2 = new Rectangle(3, 6, 1, 2);
    assertEquals(false, rectangle.overlap(rec2));
  }

  @Test
  public void overlapTouchingEdgesAdjacent() {
    rectangle = new Rectangle(5, 1, 5, 4);
    Rectangle rec2 = new Rectangle(10, 1, 5, 4);
    assertEquals(false, rectangle.overlap(rec2));
  }

  @Test
  public void intersectingRectangles() {
    rectangle = new Rectangle(0, 0, 2, 2);
    Rectangle rec2 = new Rectangle(1, 1, 3, 3);
    assertEquals(true, rectangle.overlap(rec2));
  }

  @Test
  public void intersectingRectanglesExample() {
    rectangle = new Rectangle(7, 1, 8, 4);
    Rectangle rec2 = new Rectangle(1, 1, 9, 4);
    assertEquals(true, rectangle.overlap(rec2));
  }

  @Test
  public void nonIntersectingRectanglesExample() {
    rectangle = new Rectangle(12, 1, 3, 4);
    Rectangle rec2 = new Rectangle(1, 1, 9, 4);
    assertEquals(false, rectangle.overlap(rec2));
  }

  @Test
  public void nonIntersectingRectanglesNegativeValues() {
    rectangle = new Rectangle(5, -10, 5, 9);
    Rectangle rec2 = new Rectangle(5, 0, 5, 10);
    assertEquals(false, rectangle.overlap(rec2));
  }

  @Test
  public void containedInRectangleForNegativeX() {
    rectangle = new Rectangle(-5, 1, 4, 4);
    Rectangle rec2 = new Rectangle(-4, 2, 2, 2);
    assertEquals(true, rectangle.overlap(rec2));
  }

  @Test
  public void secondContainedInFirstForPositiveValues() {
    rectangle = new Rectangle(5, 1, 5, 4);
    Rectangle rec2 = new Rectangle(4, 0, 7, 6);
    assertEquals(true, rectangle.overlap(rec2));
  }

  @Test
  public void firstContainedInSecondForPositiveValues() {
    rectangle = new Rectangle(4, 0, 7, 6);
    Rectangle rec2 = new Rectangle(5, 1, 5, 4);
    assertEquals(true, rectangle.overlap(rec2));
  }

  @Test
  public void testIntersect() {
    Rectangle rec1 = new Rectangle(4, 0, 7, 6);
    Rectangle rec2 = new Rectangle(5, 1, 5, 4);
    assertEquals(true, rec1.overlap(rec2));
    try {
      Rectangle actualOutput = rec1.intersect(rec2);
      assertEquals(rec2, actualOutput);
    } catch (NoSuchElementException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testInvalidIntersectThrowsException() {
    rectangle = new Rectangle(5, -10, 5, 9);
    Rectangle rec2 = new Rectangle(5, 0, 5, 10);
    assertThrows(NoSuchElementException.class, () -> rectangle.intersect(rec2));
  }

  @Test
  public void testOverlappingUnion() {
    Rectangle rec1 = new Rectangle(4, 0, 7, 6);
    Rectangle rec2 = new Rectangle(5, 1, 5, 4);
    Rectangle actualOutput = rec1.union(rec2);
    assertEquals(rec1, actualOutput);
  }

  @Test
  public void testNonOverlappingUnion() {
    Rectangle rec1 = new Rectangle(1, 1, 4, 4);
    Rectangle rec2 = new Rectangle(3, 3, 5, 4);
    Rectangle rec3 = new Rectangle(1, 1, 7, 6);
    Rectangle actualOutput = rec1.union(rec2);
    assertEquals(rec3, actualOutput);
  }

  @Test
  public void testToString() {
    assertEquals("x:5, y:1, w:5, h:4", String.valueOf(new Rectangle(5, 1, 5, 4)));
  }

  @Test
  public void testEquals() {
    Rectangle rec1 = new Rectangle(1, 1, 4, 4);
    Rectangle rec2 = new Rectangle(1, 1, 4, 4);
    assertEquals(rec1, rec2);
    assertTrue(rec1.equals(rec2));
  }

  @Test
  public void testHashCode() {
    Rectangle rec1 = new Rectangle(1, 1, 4, 4);
    Rectangle rec2 = new Rectangle(1, 1, 4, 4);
    assertEquals(rec1.hashCode(), rec2.hashCode());
    assertEquals(rec1, rec2);
  }
}