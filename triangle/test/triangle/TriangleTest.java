package triangle;

import org.junit.Test;
import static org.junit.Assert.*;

import static triangle.Triangle.Type;
import static triangle.Triangle.Type.*;

/**
 * Test class for the Triangle implementation.
 */
public class TriangleTest {

  /**
   * Wrapper to avoid code duplication in test methods.
   */
  private void testTriangle(int a, int b, int c, Type expected) {
    Type actual = Triangle.classify(a, b, c);

    assertEquals(expected, actual);
  }

  @Test
  public void testDummy() {
    // Cover the default constructor to make Cobertura happy.
    Triangle a = new Triangle();
  }

  @Test
  public void testTable() {
    testTriangle(0, 0, 0, INVALID);
    testTriangle(1, 0, 0, INVALID);
    testTriangle(1, 1, 0, INVALID);
    testTriangle(1, 2, 3, INVALID);
    testTriangle(1, 1, 1, EQUILATERAL);
    testTriangle(2, 2, 1, ISOSCELES);
    testTriangle(2, 3, 4, SCALENE);
  }
}
