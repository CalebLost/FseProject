package ChessGame;


import ChessBoards.IChessBoard;

public class UndoCommand extends AGameCommand
{
    public UndoCommand(IGameView gameView,IChessBoard chessBoard)
    {
        super(gameView, chessBoard);
    }

    public void execute(Object param)
    {
        m_oChessBoard.undo();
        m_oGameView.update();

        m_sologger.debug("Execute");
    }
}
