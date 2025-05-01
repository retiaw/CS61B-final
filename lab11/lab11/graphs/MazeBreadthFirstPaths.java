package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private Maze maze;
    private int str;
    private int dst;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        str = m.xyTo1D(sourceX, sourceY);
        dst = m.xyTo1D(targetX, targetY);
        edgeTo[str] = str;
        distTo[str] = 0;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> que = new ArrayDeque<>();
        que.add(str);
        marked[str] = true;
        announce();

        while (!que.isEmpty()) {
            int node = que.remove();
            for (Integer adjNode : maze.adj(node)) {
                if (!marked[adjNode]) {
                    edgeTo[adjNode] = node;
                    distTo[adjNode] = distTo[node] + 1;
                    marked[adjNode] = true;
                    announce();
                    que.add(adjNode);
                }
                if (adjNode == dst) {
                    return;
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

