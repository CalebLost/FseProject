package Controller;

import ChessBoards.*;
import Pieces.Piece;
import Pieces.PieceType;
import ChessGame.*;

public interface IController
{
    void start();
    Piece getPieces(Player player, PieceType pieceType, int index);
    void setViewModel(IGameView view,IChessBoard chessBoard);
    Player gameOver();
    int getWidth();
    int getHeight();
    Player getPlayerOne();
    Player getPlayerTwo();
}
