package ChessGame;

import ChessBoards.*;
import ChessGame.*;
import Controller.*;
import org.apache.log4j.Logger;

public abstract class AGameCommand implements ICommand
{

    protected final Logger m_sologger = Logger.getLogger(getClass());

    protected IGameView m_oGameView;
    protected IChessBoard m_oChessBoard;

   public AGameCommand(IGameView gameView,IChessBoard chessBoard)
        {
              m_oGameView = gameView;
              m_oChessBoard = chessBoard;
        }


   @Override
   public abstract void execute(Object param);
}
