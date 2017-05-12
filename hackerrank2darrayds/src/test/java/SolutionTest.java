import junit.framework.TestCase;

import java.util.Arrays;

public class SolutionTest extends TestCase {
  public SolutionTest(String testName) {
    super(testName);
  }

  protected void setUp() {
    System.out.println("setUp()");
  }

  protected void tearDown() {
    System.out.println("tearDown()");
  }

  public void testMax() {
    int[][] array = {
        {1, 2, 1},
        {1, 1, 2},
        {1, 1, 1}
    };
    int max = Hourglass.max(array);
    assertEquals(2, max);
  }

  public void testHourglassSum() {
    int[][] array = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };

    int correctSum = 1+2+3+5+7+8+9;
    int sum = Hourglass.getHourglassSum(0, 0, array);
    assertEquals(correctSum, sum);
  }

  public void testHourglassSumList() {
    int[][] array = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };

    int correctSum = 1+2+3+5+7+8+9;
    int[][] sum = Hourglass.getHourglassSumList(array);
    assertEquals(sum.length, 1);
    assertEquals(sum[0].length, 1);
    assertEquals(sum[0][0], correctSum);
  }

  public void testHourglassSumList2() {
    int[][] array = {
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 16}
    };

    int correctSum00 = 1+2+3+6+9+10+11;
    int correctSum01 = 2+3+4+7+10+11+12;
    int correctSum10 = 5+6+7+10+13+14+15;
    int correctSum11 = 6+7+8+11+14+15+16;

    int[][] sum = Hourglass.getHourglassSumList(array);
    System.out.println(sum[0][0] + ", " + sum[0][1] + ", " + sum[1][0] + ", " + sum[1][1]);
    assertEquals(2, sum.length);
    assertEquals(2, sum[0].length);
    assertEquals(correctSum00, sum[0][0]);
    assertEquals(correctSum01, sum[0][1]);
    assertEquals(correctSum10, sum[1][0]);
    assertEquals(correctSum11, sum[1][1]);
  }

  public void testTwoDimensionalArrayFromString() {
    int[][] array = IoHelper.twoDimensionalArrayFromString(new String[] {"1 2", "3 4"});
    int[][] correct = new int[][] {{1, 2}, {3, 4}};
    boolean equality = Arrays.equals(correct, array);
    assertEquals(true, equality);
  }
}
