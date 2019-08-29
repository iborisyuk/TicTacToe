import exceptions.AlreadyFilledFieldException;
import exceptions.GameAlreadyEndedException;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private Game game;

    @Before
    public void createNewGame() {
        game = new Game();
    }

    @Test
    public void mustGenerateTicTacToeField() {
        int expectedSize = 3;

        String[][] field = game.getField();

        assertEquals(field.length, expectedSize);

        for (String[] f: field) {
            assertEquals(f.length, expectedSize);
        }
    }

    @Test
    public void mustGenerateAllFieldsEmptyStrings() {
        String[][] field = game.getField();

        for (int i = 0; i < field.length; i++) {
            String[] row = field[i];
            for (int j = 0; j < row.length; j++) {
                assertEquals("", field[i][j]);
            }
        }
    }

    @Test
    public void mustSetSymbolByCords() {
        String expectSymbol = "X";

        game.step(1, 1);

        String[][] field = game.getField();

        assertEquals(field[1][1], expectSymbol);
    }

    @Test
    public void mustChangePlayerAfterMove() {
        game.step(1, 1);
        game.step(1, 2);

        String[][] field = game.getField();

        assertEquals("X", field[1][1]);
        assertEquals("O", field[1][2]);
    }

    @Test(expected = AlreadyFilledFieldException.class)
    public void mustNotOverrideExistingSteps() {
        game.step(1, 1);
        game.step(1, 1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void mustNotSetOutOfBoundsOfField() {
        game.step(5, 5);
    }

    @Test(expected = GameAlreadyEndedException.class)
    public void mustNotAbleToDoMoveAfterExdOfTheGameByRow() {
        game.step(0, 0);
        game.step(1, 0);
        game.step(0, 1);
        game.step(1, 1);
        game.step(0, 2);
        game.step(2, 2);
    }

    @Test(expected = GameAlreadyEndedException.class)
    public void mustNotAbleToDoMoveAfterExdOfTheGameByColumn() {
        game.step(0, 0);
        game.step(0, 1);
        game.step(1, 0);
        game.step(1, 1);
        game.step(2, 0);
        game.step(2, 2);
    }

    @Test
    public void mustReturnWinPlayersByRow() {
        // row
        game.step(0, 0);
        game.step(1, 0);
        game.step(0, 1);
        game.step(1, 1);
        game.step(0, 2);

        assertEquals("X", game.getWinner());
    }

    @Test
    public void mustReturnWinPlayersByColumn() {
        game.step(0, 0);
        game.step(0, 1);
        game.step(1, 0);
        game.step(1, 1);
        game.step(2, 0);

        assertEquals("X", game.getWinner());
    }

    @Test
    public void mustReturnWinPlayersByDiagonal() {
        game.step(0, 0);
        game.step(0, 1);
        game.step(1, 1);
        game.step(1, 2);
        game.step(2, 2);

        assertEquals("X", game.getWinner());
    }

    @Test
    public void mustGetNextPlayerAfterStep() {
        assertEquals(game.getPlayer(), "X");
        game.step(0,0);
        assertEquals(game.getPlayer(), "O");
    }

    @Test(expected = AlreadyFilledFieldException.class)
    public void mustNotChangesNextPlayerAfterStep() {
        assertEquals(game.getPlayer(), "X");
        game.step(0,0);
        assertEquals(game.getPlayer(), "O");
        game.step(0,0);
        assertEquals(game.getPlayer(), "O");
    }
}
