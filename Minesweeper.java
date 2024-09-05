// Time Complexity: O(m*n)
// Space Complexity: O(m*n)

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        int[][] dirs = new int[][] { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }, { 1, 1 }, { 1, -1 }, { -1, 1 },
                { -1, -1 } };

        int m = board.length;
        int n = board[0].length;

        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int mines = countMines(board, curr[0], curr[1]);
            if (mines == 0) {
                for (int[] dir : dirs) {
                    int nr = dir[0] + curr[0];
                    int nc = dir[1] + curr[1];

                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E') {
                        board[nr][nc] = 'B';
                        q.add(new int[] { nr, nc });
                    }
                }
            } else
                board[curr[0]][curr[1]] = (char) (mines + '0');

        }

        return board;

    }

    private int countMines(char[][] board, int r, int c) {
        int[][] dirs = new int[][] { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }, { 1, 1 }, { 1, -1 }, { -1, 1 },
                { -1, -1 } };
        int count = 0;
        for (int[] dir : dirs) {
            int nr = dir[0] + r;
            int nc = dir[1] + c;

            if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length && board[nr][nc] == 'M')
                count++;
        }

        return count;

    }
}