package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    Maze maze;
    private int str;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        str = 0;
        edgeTo[str] = str;
        distTo[str] = 0;
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        marked[str] = true;
        announce();
        solveHelperDFS(str, str);
    }

    // Helper methods go here
    private void solveHelperDFS(int node, int parentNode) {

        // judge whether contains a cycle:
        for (Integer i : maze.adj(node)) {
            if (!i.equals(parentNode) && marked[i]) {
                return;
            }
        }
        // if does not contain yet:
        for (Integer i : maze.adj(node)) {
            if (!marked[i]) {
                edgeTo[i] = node;
                distTo[i] = distTo[node] + 1;
                marked[i] = true;
                announce();
                solveHelperDFS(i, node);
            }
        }
    }
}

