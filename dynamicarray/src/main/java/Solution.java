import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    ArrayList<Integer> innerList1 = new ArrayList<>();
    ArrayList<Integer> innerList2 = new ArrayList<>();
    ArrayList<ArrayList<Integer>> seqList = new ArrayList<>(Arrays.asList(innerList1, innerList2));

    LastAns_SeqList_Pair pair = SeqListReferentiallyTransparentFn.applyQuery1(0, seqList, 1, 2, seqList.size());

    System.out.println(pair);
//    N_And_Q_Count n_and_q_count = IoHelper.get_N_And_Q_Count();
//    Q_Input[] q_inputs = IoHelper.get_Q_Input(n_and_q_count.qCount);
//
//    SeqList seqList = new SeqList();
//
//    for (Q_Input q_input: q_inputs) {
//      Optional<Integer> queryResult = seqList.applyQuery(q_input);
//      queryResult.ifPresent(System.out::println);
//    }
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
  public Q_Input(int type, int x, int y) {
    this.type = type;
    this.x = x;
    this.y = y;
  }

  public int type;
  public int x;
  public int y;
}

class SeqList {
  // TODO: testable?
  public Optional<Integer> applyQuery(Q_Input queryInput) {
    return Optional.empty();
  }

  // TODO: testable?
  public void applyQuery1(int x, int y) {
    LastAns_SeqList_Pair pair = SeqListReferentiallyTransparentFn.applyQuery1(lastAns, seqList, x, y, seqList.size());

    lastAns = pair.getLastAns();
    seqList = pair.getSeqList();
  }

  // TODO: testable?
  public int applyQuery2(int x, int y) {
    return SeqListReferentiallyTransparentFn.applyQuery2(lastAns, seqList, x, y, seqList.size());
  }

  private ArrayList<ArrayList<Integer>> seqList;
  private int lastAns;
}

class SeqListReferentiallyTransparentFn {
  // TODO: test
  public static LastAns_SeqList_Pair applyQuery1(int lastAns, ArrayList<ArrayList<Integer>> seqList, int x, int y, int n) {
    int index = findSubListIndex(lastAns, x, n);
    ArrayList<Integer> innerList = seqList.get(index);
    innerList.add(y); // TODO: do not modify existing list
    return new LastAns_SeqList_Pair(lastAns, seqList);
  }

  // TODO: test
  public static int applyQuery2(int lastAns, ArrayList<ArrayList<Integer>> seqList, int x, int y, int n) {
    int seqListIndex = findSubListIndex(lastAns, x, n);
    ArrayList<Integer> seq = seqList.get(seqListIndex);
    return seq.get(y % seq.size());
  }

  // has test
  public static int findSubListIndex(int lastAns, int x, int n) {
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
    String[] splitBySpace = input.split(" ");
    int type = Integer.parseInt(splitBySpace[0]);
    int x = Integer.parseInt(splitBySpace[1]);
    int y = Integer.parseInt(splitBySpace[2]);
    return new Q_Input(type, x, y);
  }
}