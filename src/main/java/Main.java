import exceptions.AlreadyFilledFieldException;
import exceptions.GameAlreadyEndedException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        while (true) {
            System.out.println(game);
            if (!game.getWinner().equals("")) {
                System.out.printf("Congratulations to player %s on victory.\n", game.getWinner());
                break;
            }

            Scanner in = new Scanner(System.in);
            try {
                System.out.printf("\n\nStep players %s (Enter two numbers separated by Enter.):\n", game.getPlayer());
                int x = in.nextInt();
                int y = in.nextInt();

                game.step(x, y);
            } catch (AlreadyFilledFieldException e) {
                System.err.println("The field you specified is busy.");
            } catch (GameAlreadyEndedException e) {
                System.err.println("Game over!");
                break;
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                System.err.println("Incorrect numbers field.");
            }
        }
    }
}
