import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Puzzle solver implementation.
 */
public class Solver {

    /**
     * Min priority queue instance.
     */
    private MinPQ<Board> boardMinPQ;

    /**
     * Is solvable indicator.
     */
    private boolean isSolvable = false;

    /**
     * List of minimal steps for solution of puzzle.
     */
    private List<Board> minSolution = new ArrayList<>();

    /**
     * Min number of moves to solve initial board.
     */
    private int minMoves = -1;

    /**
     * Find a solution to the initial board (using the A* algorithm).
     *
     * @param initialBoard initial board
     */
    public Solver(Board initialBoard) {
        if (initialBoard == null) {
            throw new NullPointerException("Initial board cannot be null.");
        }
        if (initialBoard.isGoal()) {
            minMoves = 0;
            isSolvable = true;
            minSolution.add(initialBoard);
            return;
        }
        boardMinPQ = new MinPQ<>(new HammingComparator());
        boardMinPQ.insert(initialBoard);
        nextStep();
    }

    /**
     * Is the initial board solvable?
     *
     * @return true if initial board is solvable
     */
    public boolean isSolvable() {
        return isSolvable;
    }

    /**
     * Min number of moves to solve initial board.
     *
     * @return min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves() {
        return minMoves;
    }

    /**
     * Gets sequence of boards in a shortest solution.
     *
     * @return sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution() {
        return minSolution;
    }

    private void nextStep() {
        Board min = getNextBoard();
        if (isDetectedCycleInSolution(min)) {
            return;
        }
        if (!min.isGoal()) {
            addNeighbours(min);
            nextStep();
        } else {
            boardMinPQ = null;
            isSolvable = true;
        }
    }

    private void addNeighbours(Board min) {
        for (Board neighbor : min.neighbors()) {
            if (!minSolution.contains(neighbor)) {
                boardMinPQ.insert(neighbor);
            }
        }
    }

    private boolean isDetectedCycleInSolution(Board min) {
        if (minSolution.contains(min)) {
            minMoves = -1;
            minSolution = null;
            return true;
        } else {
            minSolution.add(min);
            return false;
        }
    }

    private Board getNextBoard() {
        minMoves++;
        return boardMinPQ.delMin();
    }

    /**
     * Hamming comparator.
     */
    private class HammingComparator implements Comparator<Board> {
        @Override
        public int compare(Board o1, Board o2) {
            return o1.hamming() - o2.hamming();
        }
    };
}