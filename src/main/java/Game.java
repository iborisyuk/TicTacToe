import exceptions.AlreadyFilledFieldException;
import exceptions.GameAlreadyEndedException;

public class Game {

    private final String[][] field = {{"", "", ""}, {"", "", ""}, {"", "", ""}};
    private int stepCount = 0;

    private String winner = "";

    public String[][] getField() {
        return field;
    }

    public void step(int x, int y) {
        if (!field[x][y].equals(""))
            throw new AlreadyFilledFieldException();

        if (!winner.equals(""))
            throw new GameAlreadyEndedException();

        field[x][y] = getPlayer();
        stepCount++;

        winner = checkEndOfTheGame();
    }

    public String getPlayer() {
        if (stepCount % 2 == 0)
            return "X";
        else
            return  "O";
    }

    /**
     * Return empty String if exist empty fields.
     *
     * @return win player X|O or empty string.
     */
    private String checkEndOfTheGame() {
        for (int i = 0; i < field.length; i++) {
            if (!field[i][0].equals("") && field[i][0].equals(field[i][1]) && field[i][1].equals(field[i][2])) {
                return field[i][0];
            }

            if (!field[0][i].equals("") && field[0][i].equals(field[1][i]) && field[1][i].equals(field[2][i])) {
                return field[0][i];
            }
        }

        if (!field[0][0].equals("") && field[0][0].equals(field[1][1]) && field[1][1].equals(field[2][2])) {
            return field[0][0];
        }

        if (!field[0][2].equals("") && field[0][2].equals(field[1][1]) && field[1][1].equals(field[2][1])) {
            return field[0][2];
        }

        int status = 0;
        for (String[] f : field) {
            if (!f[0].equals("") && !f[1].equals("") && !f[2].equals(""))
                status++;
        }

        if (status == 3)
            throw new GameAlreadyEndedException();

        return "";
    }

    public String getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s][%s]\n[%s][%s][%s]\n[%s][%s][%s]", field[0][0], field[0][1], field[0][2],
                field[1][0], field[1][1], field[1][2], field[2][0], field[2][1], field[2][2]);
    }
}
