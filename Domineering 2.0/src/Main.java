import java.util.Scanner;

public class Main {

    public static int[][] initializeBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = -1;
            }
        }
        return board;
    }
    public static void printBoard(int[][] board) {
        System.out.println("  1  2  3  4  5");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i+1);
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] == -1) {
                    System.out.print(" ..");
                } else if(board[i][j] == 0) {
                    System.out.print(" HH");
                } else {
                    System.out.print(" VV");
                }
            }
            System.out.println();
        }
    }
    public static boolean isMoveInBounds(int[][] board, int player, int moveR, int moveC) {
        if(player == 0) {
            if(moveR < 0 || moveR > board.length-1) {
                return false;
            } else if(moveC < 0 || moveC >= board[moveR].length-1){
                return false;
            }
        } else {
            if(moveR < 0 || moveR >= board.length-1) {
                return false;
            } else if(moveC < 0 || moveC > board[moveR].length-1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMoveOpen(int[][] board, int player, int moveR, int moveC) {
        if(isMoveInBounds(board, player, moveR, moveC)) {
            if(player == 0) {
                if(board[moveR][moveC] != -1 || board[moveR][moveC+1] != -1) {
                    return false;
                }
            } else {
                if(board[moveR][moveC] != -1 || board[moveR+1][moveC] != -1) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public static void placePiece(int[][] board, int player, int moveR, int moveC) {
        if(isMoveOpen(board, player, moveR, moveC)) {
            board[moveR][moveC] = player;
            if (player == 0) {
                board[moveR][moveC+1] = player;
            } else {
                board[moveR+1][moveC] = player;
            }
        }
    }

    public static void makeMove(int[][] board, int player, Scanner C, Scanner R) {
        if (player == 0) {
            System.out.println("Horizontal player (specify left coordinate)");
        } else {
            System.out.println("Vertical player (specify top coordinate)");
        }
        System.out.print("Enter the Row of your coordinate: ");
        int moveR = Integer.parseInt(C.nextLine()) - 1;
        System.out.print("Enter the Column of your coordinate: ");
        int moveC = Integer.parseInt(R.nextLine()) - 1;
        while (!isMoveOpen(board, player, moveR, moveC)) {
            System.out.println("*** Move (" + (moveR+1) + ", " + (moveC+1) + ") is not legal. ***\n");
            System.out.print("Enter the Row of your coordinate: ");
            moveR = Integer.parseInt(C.nextLine()) - 1;
            System.out.print("Enter the Column of your coordinate: ");
            moveC = Integer.parseInt(R.nextLine()) - 1;
        }
        placePiece(board, player, moveR, moveC);
    }

    public static boolean isGameOver(int player, int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(player == 0) {
                    if(i < board.length-1) {
                        if (board[i][j] == -1 && board[i+1][j] == -1) {
                            return false;
                        }
                    }
                } else {
                    if (j < board[i].length-1) {
                        if (board[i][j] == -1 && board[i][j + 1] == -1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int player = 0;
        int[][] board = new int[5][5];
        board = initializeBoard(board);
        printBoard(board);
        Scanner R = new Scanner(System.in);
        Scanner C = new Scanner(System.in);
        while(!isGameOver(player, board)) {
            makeMove(board, player, R, C);
            printBoard(board);
            player = (player+1)%2;
        }
        makeMove(board, player, R, C);
        if(player == 0) {
            System.out.println("------ Horizontal player wins! ------");
        } else {
            System.out.println("------ Vertical player wins! ------");
        }
        printBoard(board);
    }
}