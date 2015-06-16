import ChessBoards.*;
import ChessGame.DefaultView;
import Controller.*;

public class MVCChess

{

    public static void main(String[] args) throws InterruptedException
    {

        IController gameController = new DefaultController(new DefaultView(),new DefaultBoard());
        gameController.start();

    }
}
