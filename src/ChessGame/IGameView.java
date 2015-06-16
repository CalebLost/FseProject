package ChessGame;

import Pieces.Position;
import Controller.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Vector;


public interface IGameView
{
    void setController(IController c);

    void setIsPaused(boolean pause);

    boolean getIsPaused();

    void setChessBoardCommandListener(ActionListener listener);

    void setChessBoardMouseListener(MouseListener listener);

    void selectAt(int x, int y);

    void updateScore(Player currentPlayer);

    void start();

    void paint();

    void update();

    void cleanMoves();

    void addMoves(Vector<Position> moves);

    int getWidth();

    int getHeight();

}
