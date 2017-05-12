import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    String[] stdIn = IoHelper.readFromStdin(6);
    int[][] array = IoHelper.twoDimensionalArrayFromString(stdIn);

    int[][] sums = Hourglass.getHourglassSumList(array);
    int max = Hourglass.max(sums);
    System.out.println(max);
  }
}

class Hourglass {
  public static int[][] getHourglassSumList(int[][] array) {
    int horizontalTraverseLimit = array.length - (HOURGLASS_WIDTH-1);
    int verticalTraverseLimit = array[0].length - (HOURGLASS_HEIGHT-1);

    int[][] sum = new int[horizontalTraverseLimit][verticalTraverseLimit];

    for (int i = 0; i < horizontalTraverseLimit; ++i) {
      for (int j = 0; j < verticalTraverseLimit; ++j) {
        sum[i][j] = getHourglassSum(i, j, array);
      }
    }

    return sum;
  }

  public static int getHourglassSum(int startI, int startJ, int[][] array) {
    int totalSum = 0;

    for (int i = 0; i < HOURGLASS_WIDTH; ++i) {
      int _i = startI;
      int _j = startJ+i;
      int cell = array[_i][_j];
      totalSum += cell;
    }

    int middleCell = array[startI+1][startJ+1];
    totalSum += middleCell;

    for (int i = 0; i < HOURGLASS_WIDTH; ++i) {
      int _i = startI+2;
      int _j = startJ+i;
      int cell = array[_i][_j];
      totalSum += cell;
    }

    return totalSum;
  }

  public static int max(int[][] array) {
    int max = array[0][0];

    for (int i = 0; i < array.length; ++i) {
      for (int j = 0; j < array.length; ++j) {
        if (max < array[i][j]) {
          max = array[i][j];
        }
      }
    }

    return max;
  }

  private static int HOURGLASS_WIDTH = 3;
  private static int HOURGLASS_HEIGHT = 3;
}

class IoHelper {
  public static String twoDimensionalArrayToString(int[][] array) {
    StringBuilder tmp = new StringBuilder();

    for (int i = 0; i < array.length; ++i) {
      for (int j = 0; j < array[0].length; ++j) {
        tmp.append(array[i][j] + ",");
      }
      tmp.append("\n");
    }

    return tmp.toString();
  }

  public static int[][] twoDimensionalArrayFromString(String[] array) {
    String[] splitByRows = array;
    int[][] output = new int[splitByRows.length][];

    for (int i = 0; i < splitByRows.length; ++i) {
      int[] column = splitNumberListBy(splitByRows[i], " ");
      output[i] = column;
    }

    return output;
  }

  public static int[] splitNumberListBy(String numberList, String delimiter) {
    String[] splitByDelimiter = numberList.split(delimiter);
    int[] output = new int[splitByDelimiter.length];

    for (int i = 0; i < output.length; ++i) {
      output[i] = Integer.parseInt(splitByDelimiter[i]);
    }

    return output;
  }

  public static String[] readFromStdin(int numOfLines) {
    Scanner scanner = new Scanner(System.in);
    String[] input = new String[numOfLines];

    for (int i = 0; i < input.length; ++i) {
      String row = scanner.nextLine();
      input[i] = row;
    }

    return input;
  }
}