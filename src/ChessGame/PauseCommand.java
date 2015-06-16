package ChessGame;


import ChessBoards.IChessBoard;

public class PauseCommand extends AGameCommand
{
      public PauseCommand(IGameView gameView, IChessBoard chessBoard)
       {
           super(gameView, chessBoard);
       }

    public void execute(Object param)
    {
        m_oGameView.setIsPaused(!m_oGameView.getIsPaused());

        m_sologger.debug("Execute");
    }
}
