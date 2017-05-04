import java.util.HashSet;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solver {

    private PriorityQueue<PuzzleNode> queue;
    private HashSet<String> set;

    public Solver(PuzzleNode start) {
        /*queue = new PriorityQueue<PuzzleNode>(new Comparator<PuzzleNode>() {
            @Override
            public int compare(PuzzleNode p1, PuzzleNode p2) {
                if(p1.state.getState().equals(p2.state.getState()))
                    return 0;
                return p1.state.getHeuristic() - p2.state.getHeuristic();
            }
        });*/
        queue = new PriorityQueue<PuzzleNode>((PuzzleNode p1, PuzzleNode p2) -> p1.state.getHeuristic() - p2.state.getHeuristic());
        set = new HashSet<String>();
        queue.add(start);
        set.add(start.state.getState());
    }

    private void enqueue(PuzzleNode state) {
        if (state == null)
            return;
        if (state.state.getState() == null)
            return;
        if (!set.contains(state.state.getState())) {
            queue.add(state);
            set.add(state.state.getState());
        }
    }

    public PuzzleNode solve() {
        while(!queue.isEmpty()) {
            PuzzleNode current = queue.poll();
            if(current.state.isSolved()) {
                return current;
            }
            String up = current.state.up();
            if(up != null)
                enqueue(new PuzzleNode(up, current.state.getCost(), current));
            String down = current.state.down();
            if(down != null)
                enqueue(new PuzzleNode(down, current.state.getCost(), current));
            String left = current.state.left();
            if(left!= null)
                enqueue(new PuzzleNode(left, current.state.getCost(), current));
            String right = current.state.right();
            if(right != null)
                enqueue(new PuzzleNode(right, current.state.getCost(), current));
        }
        return null;
    }
}
