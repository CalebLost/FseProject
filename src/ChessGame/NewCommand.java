package ChessGame;


import ChessBoards.IChessBoard;

public class NewCommand extends AGameCommand
{
      public NewCommand(IGameView gameView,IChessBoard chessBoard)
       {
           super(gameView, chessBoard);
       }

    public void execute(Object param)
    {
        m_oGameView.updateScore(m_oChessBoard.getOpponentPlayer());
        m_oChessBoard.reset();
        m_oGameView.setIsPaused(false);
        m_oGameView.update();

        m_sologger.debug("Execute");
    }
}
