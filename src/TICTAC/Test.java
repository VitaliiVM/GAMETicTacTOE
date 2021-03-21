package TICTAC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;


public class Test {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static String firstPlayer;
    public static String secondPlayer;
    private static int movesNumber = 0;
    public static String[][] gameField;

    public static void gameStart() throws IOException {
        System.out.println("Игра: КРЕСТИКИ НОЛИКИ");

        System.out.println("Введите размер игрового поля:");
        int size = parseInt(reader.readLine());

        gameField = initGameField(size);

        System.out.println("Введите имя первого игрока: X");
        firstPlayer = reader.readLine();

        System.out.println("Введите имя второго игрока: O");
        secondPlayer = reader.readLine();

        System.out.println("Игра началась!!!");
        System.out.println();
        String res = "";
        String player = "X";

        do {
            showGameField();
            var number = Integer.parseInt(reader.readLine());
            var cellValue = findCellValueByNumber(number, gameField);
            if (cellValue.equals("0") || cellValue.equals("X")) {
                System.err.println("Введенное значение не верно");
                continue;
            }
            res = move(player, cellValue);
            if(!res.equals("")) {
                System.out.println("Winner: " + (res.equals("X") ? firstPlayer : res.equals("O") ?  secondPlayer : "Ничья!!!"));
            }

            player = player.equals("X") ? "O" : "X";

        } while (res.equals(""));
    }

    public static String move(String player, String cellValue) {

        int row = 0;
        int coll = 0;

        for (int i = 0; i < gameField.length; i++) {
            String[] inner = gameField[i];

            for (int j = 0; j < inner.length; j++) {
                if (inner[j].equals(cellValue)) {
                    row = i;
                    coll = j;
                }
            }
        }
        gameField[row][coll] = player;

        //check row
        boolean win = true;
        for(int i = 0; i < gameField.length; i++){
            if(!gameField[row][i].equals(player)){
                win = false;
                break;
            }
        }

        if (win) return player;

        //check column
        win = true;
        for (int i = 0; i < gameField.length; i++) {
            if(!gameField[i][coll].equals(player)){
                win = false;
                break;
            }
        }

        if (win) return player;

        //check back diagonal
        win = true;
        for(int i = 0; i < gameField.length; i++) {
            if(!gameField[i][i].equals(player)) {
                win = false;
                break;
            }
        }

        if (win) return player;

        //check forward diagonal
        win = true;
        for(int i = 0; i < gameField.length; i++){
            if(!gameField[i][gameField.length - i - 1].equals(player)){
                win = false;
                break;
            }
        }

        if (win) return player;

        movesNumber += 1;
        if ((gameField.length * gameField[0].length) == movesNumber) {
            return "draw";
        }

        return "";
    }

    public static void showGameField() {

        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField.length; j++) {

                System.out.print(" | " + (gameField[i][j]) + " | ");
            }
            System.out.println();
            if (i != gameField.length - 1) {
                System.out.println("---------------------");
            }
        }
        System.out.println();
    }

    public static String[][] initGameField(Integer number) {
        String[][] field = new String[number][number];
        int counter = 0;
        for (int k = 0; k < number; k++) {
            for (int i = 0; i < number; i++) {
                counter += 1;
                field[k][i] = Integer.toString(counter);
            }
        }
        return field;
    }

    public static String findCellValueByNumber(Integer number, String[][] gameField) {
        int counter = 0;
        for (String[] cellArray : gameField) {
            for (String s : cellArray) {
                if (number == ++counter) {
                    return s;
                }
            }
        }
        return "";
    }

}