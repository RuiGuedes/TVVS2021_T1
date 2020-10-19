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
    // Fully tests the first multi-conditional statement (line 20)
    testTriangle(0, 0, 0, INVALID);
    testTriangle(1, 0, 0, INVALID);
    testTriangle(1, 1, 0, INVALID);

    // Fully tests the second multi-conditional statement (line 34)
    testTriangle(1, 2, 3, INVALID);
    testTriangle(3, 1, 2, INVALID);
    testTriangle(2, 3, 1, INVALID);
    
    // Scalene Test
    testTriangle(2, 3, 4, SCALENE);

    // Equilateral Test
    testTriangle(1, 1, 1, EQUILATERAL);
    
    // Isosceles Test
    testTriangle(2, 2, 1, ISOSCELES);
    testTriangle(1, 2, 2, ISOSCELES);
    testTriangle(2, 1, 2, ISOSCELES);

    // Invalid isosceles triangle test
    testTriangle(2, 2, 5, INVALID);
    testTriangle(5, 2, 2, INVALID); 
    testTriangle(2, 5, 2, INVALID);
  }

  @Test
  public void testMutants() {
    // Equivalent mutants: 11 59 76 87 101 111 128 136 145

    // Kills live mutants: 2, 3, 13, 22
    testTriangle(0, 1, 0, INVALID);
    testTriangle(0, 0, 1, INVALID);
    testTriangle(0, 1, 1, INVALID);

    // Kills live mutants: 7, 8, 12
    testTriangle(1, 1, 0, INVALID);
    testTriangle(1, 0, 1, INVALID);

    // Kills live mutants: 4, 9, 18
    testTriangle(-1, 1, 1, INVALID); 
    testTriangle(1, -1, 1, INVALID); 
    testTriangle(1, 1, -1, INVALID); 

    // Kills live mutants: 80
    testTriangle(2, 4, 3, SCALENE);

    // Kills live mutants: 63 67 70 74 81 85
    testTriangle(2, 3, 5, INVALID);
    testTriangle(2, 3, 6, INVALID);
    testTriangle(5, 2, 3, INVALID);
    testTriangle(6, 2, 3, INVALID);
    testTriangle(3, 5, 2, INVALID);
    testTriangle(3, 6, 2, INVALID);

    // Kills live mutants: 105, 122, 139
    testTriangle(3, 3, 7, INVALID);
    testTriangle(7, 3, 3, INVALID);
    testTriangle(3, 7, 3, INVALID);

    // Kills live mutants: 109, 126, 143
    testTriangle(2, 2, 4, INVALID);
    testTriangle(4, 2, 2, INVALID);
    testTriangle(2, 4, 2, INVALID);
  }
}
