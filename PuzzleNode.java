public class PuzzleNode {
    public FifteenPuzzle state;
    public PuzzleNode parent;

    public PuzzleNode(FifteenPuzzle p) {
        state = p;
        parent = null;
    }

    public PuzzleNode(String newState, int prevCost, PuzzleNode node) {
        state = new FifteenPuzzle(newState, prevCost);
        parent = node;
    }
}
