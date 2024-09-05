// Time Complexity: O(6*n*n) 
// Space Complexity: O(n*n)

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;

        // flattening the board
        int[] flattenedBoard = new int[n * n];

        int idx = 0;
        int i = n - 1;
        int j = 0;
        boolean flag = true;
        while (idx < n * n) {
            if (board[i][j] == -1) {
                flattenedBoard[idx] = -1;
            } else
                flattenedBoard[idx] = board[i][j] - 1;

            if (flag) {
                j++;
                if (j == n) {
                    i--;
                    j--;
                    flag = false;
                }
            } else {
                j--;
                if (j == -1) {
                    i--;
                    j++;
                    flag = true;
                }
            }
            idx++;
        }

        // bfs
        Queue<Integer> q = new LinkedList<>();
        int moves = 0;
        q.add(0);
        flattenedBoard[0] = -2;

        while (!q.isEmpty()) {
            int size = q.size();

            for (i = 0; i < size; i++) {
                int curr = q.poll();

                System.out.println(curr);

                for (int k = 1; k <= 6; k++) {
                    int newPos = curr + k;

                    if (flattenedBoard[newPos] != -2) {
                        if (flattenedBoard[newPos] == -1) {
                            if (newPos >= n * n - 1)
                                return moves + 1;
                            q.add(newPos);
                            flattenedBoard[newPos] = -2;
                        } else if (flattenedBoard[flattenedBoard[newPos]] != -2) {
                            if (flattenedBoard[newPos] >= n * n - 1)
                                return moves + 1;
                            q.add(flattenedBoard[newPos]);
                            flattenedBoard[flattenedBoard[newPos]] = -2;
                        }
                    }
                }
            }
            moves++;
        }

        return -1;

    }
}