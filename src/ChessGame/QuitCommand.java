package ChessGame;


import ChessBoards.IChessBoard;

public class QuitCommand extends AGameCommand
{
      public QuitCommand(IGameView gameView, IChessBoard chessBoard)
       {
           super(gameView, chessBoard);
       }

    public void execute(Object param)
    {
        m_oChessBoard.forfeit();
        m_oGameView.updateScore(m_oChessBoard.getOpponentPlayer());

        m_sologger.debug("Execute");
    }
}
