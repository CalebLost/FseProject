package ChessGame;


import ChessBoards.IChessBoard;

public class RestartCommand extends AGameCommand
{
      public RestartCommand(IGameView gameView, IChessBoard chessBoard)
       {
           super(gameView, chessBoard);
       }

    public void execute(Object param)
    {
        m_oChessBoard.reset();
        m_oGameView.setIsPaused(false);
        m_oGameView.update();

        m_sologger.debug("Execute");
    }
}
