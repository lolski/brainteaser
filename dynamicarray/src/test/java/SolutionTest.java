import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

public class SolutionTest extends TestCase {
  protected void setUp() {
    System.out.println("setUp()");
  }

  protected void tearDown() {
    System.out.println("tearDown()");
  }

  public void testParse_N_And_Q_Count() {
    String input = "5 2";

    N_And_Q_Count output = IoHelper.parse_N_And_Q_Count(input);

    assertEquals(5, output.n);
    assertEquals(2, output.qCount);
  }

  public void testParse_Q_Input() {
    String input = "1 0 3";

    Q_Input output = IoHelper.parse_Q_Input(input);

    assertEquals(1, output.type);
    assertEquals(0, output.x);
    assertEquals(3, output.y);
  }

  public void testFindIndex() {
    int index1 = SeqListReferentiallyTransparentFn.findSubListIndex(0, 0, 2);
    int index2 = SeqListReferentiallyTransparentFn.findSubListIndex(0, 1, 2);
    int index3 = SeqListReferentiallyTransparentFn.findSubListIndex(0, 7, 10);
    int index4 = SeqListReferentiallyTransparentFn.findSubListIndex(3, 4, 10);
    int index5 = SeqListReferentiallyTransparentFn.findSubListIndex(3, 4, 3);
    assertEquals(0, index1);
    assertEquals(1, index2);
    assertEquals(7, index3);
    assertEquals(7, index4);
    assertEquals(1, index5);
  }

  public void testSeqListReferentiallyTransparentApplyQuery1() {
    // initialization
    ArrayList<Integer> subList1 = new ArrayList<>();
    ArrayList<Integer> subList2 = new ArrayList<>();
    ArrayList<ArrayList<Integer>> seqList = new ArrayList<>(Arrays.asList(subList1, subList2));

    // input
    int lastAns = 0;
    int x = 1;
    int y = 2;
    int n = seqList.size();

    // expected output
    int expectedLastAns = 0;
    int expectedSubListLocation = 1;
    int expectedAppendedElemValue = 2;

    // test
    LastAns_SeqList_Pair pair = SeqListReferentiallyTransparentFn.applyQuery1(lastAns, seqList, x, y, n);

    // assertions
    assertEquals(expectedLastAns, pair.getLastAns()); // assert lastAns

    ArrayList<Integer> appendedElementSubList = pair.getSeqList().get(expectedSubListLocation);
    int lastElementInSubList = appendedElementSubList.get(appendedElementSubList.size()-1);
    assertEquals(expectedAppendedElemValue, lastElementInSubList); // assert seqList
  }

  public void testSeqListReferentiallyTransparentApplyQuery2() {
    // initialization
    ArrayList<Integer> subList1 = new ArrayList<>(Arrays.asList(1));
    ArrayList<Integer> subList2 = new ArrayList<>(Arrays.asList(2));
    ArrayList<Integer> subList3 = new ArrayList<>(Arrays.asList(3));
    ArrayList<Integer> subList4 = new ArrayList<>(Arrays.asList(4));
    ArrayList<ArrayList<Integer>> seqList = new ArrayList<>(Arrays.asList(subList1, subList2, subList3, subList4));

    // input
    int lastAns = 0;
    int x = 1;
    int y = 13;
    int n = seqList.size();

    // expected output
    int expectedLastAns = 4;

    // initialize subList with expected output before running the test
    int expectedSubListIndex = SeqListReferentiallyTransparentFn.findSubListIndex(lastAns, x, n);
    ArrayList<Integer> seq = seqList.get(expectedSubListIndex);
    int expectedElemIndexInSubList = y % seq.size();
    seq.set(expectedElemIndexInSubList, expectedLastAns);

    // test
    int newLastAns = SeqListReferentiallyTransparentFn.applyQuery2(lastAns, seqList, x, y, n);

    // assertions
    assertEquals(expectedLastAns, newLastAns); // assert newLastAns
  }
}

