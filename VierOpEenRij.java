import java.util.Scanner;

public class VierOpEenRij{
    public static void main(String[] args) {
        new FourinARow().start();
    }
}

class FourinARow {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    Board board = new Board();
    Player player1 = new Player(ANSI_GREEN + "Groen" + ANSI_RESET, ANSI_GREEN + "G" + ANSI_RESET);
    Player player2 = new Player(ANSI_RED + "Rood" + ANSI_RESET, ANSI_RED + "R" + ANSI_RESET);

    void start() {
        String[][] playboard = board.printBoard();
        if (whoBegins() != 1) {
            player2.turn(playboard, board);
        }
        do {
            player1.turn(playboard, board);
            if (player1.winner) {
                System.out.println(player1.color + " heeft gewonnen!!!");
                break;
            }
            player2.turn(playboard, board);
        } while (!player2.winner);
        if (player2.winner) {
            System.out.println(player2.color + " heeft gewonnen!!!");
        }
    }

    int whoBegins() {
        int tmp = (int) (Math.floor(Math.random() + 1.5));
        if (tmp == 1) {
            System.out.println(ANSI_GREEN + "Groen mag beginnen" + ANSI_RESET);
            return 1;
        } else {
            System.out.println(ANSI_RED + "Rood mag beginnen" + ANSI_RESET);
            return 0;
        }
    }
}

class Board {
    String[][] playBoard = {
            {" ", "a", " ", "b", " ", "c", " ", "d", " ", "e", " ", "f", " ", "g", " ", "  "},
            {"|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " 6"},
            {"|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " 5"},
            {"|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " 4"},
            {"|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " 3"},
            {"|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " 2"},
            {"|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " ", "|", " 1"},
            {" ", "-", " ", "-", " ", "-", " ", "-", " ", "-", " ", "-", " ", "-", " ", "  "},
    };

    String[][] printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(playBoard[i][j]);
            }
            System.out.println();
        }
        return playBoard;
    }

    boolean checkIfWon(int row, int column) {
        return horizontal(row, column) || vertical(row, column) || diagnal(row, column);
    }

    private boolean diagnal(int row, int column) {
        boolean diagnal = false;
        int num1 = -2;
        int num2 = -2;
        for(int i = row, j = column;i>0 && j>0;i--, j-=2){
            if (playBoard[row][column].equals(playBoard[i][j])) {
                num1++;
            } else {
                break;
            }
        }
        for(int i = row, j = column;i<7 && j<14;i++, j+=2){
            if (playBoard[row][column].equals(playBoard[i][j])) {
                num1++;
            } else {
                break;
            }
        }
        for(int i = row, j = column;i>0 && j<14;i--, j+=2){
            if (playBoard[row][column].equals(playBoard[i][j])) {
                num2++;
            } else {
                break;
            }
        }
        for(int i = row, j = column;i<7 && j>0;i++, j-=2){
            if (playBoard[row][column].equals(playBoard[i][j])) {
                num2++;
            } else {
                break;
            }
        }
        if(num1>=3||num2>=3){
            diagnal = true;
        }
        return diagnal;
    }

    private boolean vertical(int row, int column) {
        boolean vertical = false;
        if (row < 4) {
            for (int i = row; i < row + 4; i++) {
                if (playBoard[row][column].equals(playBoard[i][column])) {
                    vertical = true;
                } else {
                    vertical = false;
                    break;
                }
            }
        }
        return vertical;
    }

    private boolean horizontal(int row, int column) {
        boolean horizontal = false;
        int num = -2;
        for (int i = column; i > 0; i -= 2) {
            if (playBoard[row][column].equals(playBoard[row][i])) {
                num++;
            } else {
                break;
            }
        }
        for (int j = column; j < 14;j+=2){
            if (playBoard[row][column].equals(playBoard[row][j])) {
                num++;
            } else {
                break;
            }
        }
        if(num>=3){
            horizontal = true;
        }
        return horizontal;
    }
}

class Player {
    Scanner scanner = new Scanner(System.in);
    String color;
    String colorChar;
    boolean winner;

    public Player(String color, String colorChar) {
        this.color = color;
        this.colorChar = colorChar;
    }

    void turn(String[][] playBoard, Board board) {
        String input;
        boolean wrongInput;
        int column;
        System.out.print(color + "'s beurt, vul een geldige kolom in waar je je steen wilt gooien: ");
        do {
            wrongInput = false;
            input = scanner.nextLine();
            if (!input.equals("a") && !input.equals("b") && !input.equals("c") && !input.equals("d") && !input.equals("e") && !input.equals("f") && !input.equals("g")) {
                System.out.print("verkeerde input, probeer het opnieuw: ");
                wrongInput = true;
            }
            column = switch (input) {
                case "a" -> 1;
                case "b" -> 3;
                case "c" -> 5;
                case "d" -> 7;
                case "e" -> 9;
                case "f" -> 11;
                case "g" -> 13;
                default -> 0;
            };
            if (!playBoard[1][column].equals(" ") && !wrongInput) {
                System.out.print("Kolom is al vol, probeer opnieuw: ");
                wrongInput = true;
            }
        } while (wrongInput);

        for (int i = 6; i > 0; i--) {
            if (playBoard[i][column].equals(" ")) {
                playBoard[i][column] = colorChar;
                if (board.checkIfWon(i, column)) {
                    winner = true;
                }
                break;
            }
        }
        board.printBoard();
    }
}