package ChessBoards;

import ChessGame.Player;
import Pieces.Piece;
import Pieces.PieceType;
import java.util.Vector;
import Pieces.Position;


public interface IChessBoard
{

    Piece getPiece(Player player, PieceType pieceType, int index);

    void selectAt(int x, int y);

    void moveTo(int xPos, int yPos);

    void undo();

    void forfeit();

    void reset();

    Vector<Position> getValidMoves();

    Player getPlayerOne();

    Player getPlayerTwo();

    Player getCurrentPlayer();

    Player getOpponentPlayer();

}
