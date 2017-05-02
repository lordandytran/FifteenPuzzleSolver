import java.util.Stack;

public class Driver {

    public static String INITIAL_STATE = " 123456789ABCDEF";

    public static void main(String[] args) {
        FifteenPuzzle p = new FifteenPuzzle(INITIAL_STATE);
        Solver a = new Solver(new PuzzleNode(p));

        PuzzleNode finalState = a.solve();

        Stack<PuzzleNode> s = new Stack<PuzzleNode>();
        while(finalState != null) {
            s.push(finalState);
            finalState = finalState.parent;
        }

        while(!s.empty()) {
            System.out.println(s.pop().state.puzzleForm());
        }
    }
}
