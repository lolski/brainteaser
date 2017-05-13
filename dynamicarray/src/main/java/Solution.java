import com.sun.javaws.exceptions.InvalidArgumentException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    N_And_Q_Count n_and_q_count = IoHelper.get_N_And_Q_Count();
    Q_Input[] q_inputs = IoHelper.get_Q_Input(n_and_q_count.qCount);

    SeqList seqList = new SeqList(n_and_q_count.n);

    for (Q_Input q_input: q_inputs) {
      switch (q_input.type) {
        case QUERY_1:
          seqList.applyQuery1(q_input.x, q_input.y);
          break;
        case QUERY_2:
          int lastAns = seqList.applyQuery2(q_input.x, q_input.y);
          System.out.println(lastAns);
          break;
      }
    }
  }
}

class N_And_Q_Count {
  // non-testable
  public N_And_Q_Count(int n, int q) {
    this.n = n;
    this.qCount = q;
  }

  public int n;
  public int qCount;
}

class Q_Input {
  // non-testable
  public Q_Input(QueryType type, int x, int y) {
    this.type = type;
    this.x = x;
    this.y = y;
  }

  public static enum QueryType { QUERY_1, QUERY_2 };
  public QueryType type;
  public int x;
  public int y;
}

class SeqList {
  public SeqList(int n) {
    this.seqList = SeqListReferentiallyTransparentFn.initSeqList(n);
    this.lastAns = 0;
  }
  // TODO: testable?
  public void applyQuery1(int x, int y) {
    LastAns_SeqList_Pair pair = SeqListReferentiallyTransparentFn.applyQuery1(lastAns, seqList, x, y, seqList.size());

    lastAns = pair.getLastAns();
    seqList = pair.getSeqList();
  }

  // TODO: testable?
  public int applyQuery2(int x, int y) {
    int newLastAns = SeqListReferentiallyTransparentFn.applyQuery2(lastAns, seqList, x, y, seqList.size());

    lastAns = newLastAns;

    return lastAns;
  }

  private ArrayList<ArrayList<Integer>> seqList;
  private int lastAns;
}

class SeqListReferentiallyTransparentFn {
  // TODO: test
  public static ArrayList<ArrayList<Integer>> initSeqList(int n) {
    ArrayList<ArrayList<Integer>> seqList = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      seqList.add(new ArrayList<>());
    }

    return seqList;
  }

  // has test
  public static LastAns_SeqList_Pair applyQuery1(int lastAns, ArrayList<ArrayList<Integer>> seqList, int x, int y, int n) {
    if (n == 0) throw new IllegalArgumentException("n == 0");

    int index = findSubListIndex(lastAns, x, n);
    ArrayList<Integer> innerList = seqList.get(index);
    innerList.add(y); // TODO: do not modify existing list
    return new LastAns_SeqList_Pair(lastAns, seqList);
  }

  // has test
  public static int applyQuery2(int lastAns, ArrayList<ArrayList<Integer>> seqList, int x, int y, int n) {
    if (n == 0) throw new IllegalArgumentException("n == 0");

    int seqListIndex = findSubListIndex(lastAns, x, n);
    ArrayList<Integer> seq = seqList.get(seqListIndex);
    return seq.get(y % seq.size());
  }

  // has test
  public static int findSubListIndex(int lastAns, int x, int n) {
    if (n == 0) throw new IllegalArgumentException("n == 0");

    return ((x ^ lastAns) % n);
  }
}

class LastAns_SeqList_Pair {
  // non-testable
  public LastAns_SeqList_Pair(int lastAns, ArrayList<ArrayList<Integer>> seqList) {
    this.lastAns = lastAns;
    this.seqList = seqList;
  }

  // non-testable
  public int getLastAns() {
    return lastAns;
  }

  // non-testable
  public ArrayList<ArrayList<Integer>> getSeqList() {
    return seqList;
  }

  private int lastAns;
  private ArrayList<ArrayList<Integer>> seqList;
}

class IoHelper {
  private static Scanner scanner = new Scanner(System.in);

  // non-testable
  public static N_And_Q_Count get_N_And_Q_Count() {
    String input = scanner.nextLine();
    return parse_N_And_Q_Count(input);
  }

  // has test
  public static N_And_Q_Count parse_N_And_Q_Count(String input) {
    if (input == null) throw new IllegalArgumentException("input == null");

    String[] splitBySpace = input.split(" ");
    int n = Integer.parseInt(splitBySpace[0]);
    int qCount = Integer.parseInt(splitBySpace[1]);

    return new N_And_Q_Count(n, qCount);
  }

  // non-testable
  public static Q_Input[] get_Q_Input(int n) {
    Q_Input[] q_inputs = new Q_Input[n];
    for (int i = 0; i < n; ++i) {
      String input = scanner.nextLine();
      q_inputs[i] = parse_Q_Input(input);
    }
    return q_inputs;
  }

  // has test
  public static Q_Input parse_Q_Input(String input) {
    if (input == null) throw new IllegalArgumentException("input == null");

    String[] splitBySpace = input.split(" ");
    Q_Input.QueryType type = fromInt(Integer.parseInt(splitBySpace[0]));
    int x = Integer.parseInt(splitBySpace[1]);
    int y = Integer.parseInt(splitBySpace[2]);
    return new Q_Input(type, x, y);
  }

  // TODO: test
  public static Q_Input.QueryType fromInt(int type) {
    switch (type) {
      case 1:
        return Q_Input.QueryType.QUERY_1;
      case 2:
        return Q_Input.QueryType.QUERY_2;
      default:
        throw new IllegalArgumentException("type must be '1' or '2'");
    }
  }
}