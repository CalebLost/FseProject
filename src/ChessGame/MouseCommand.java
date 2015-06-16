package ChessGame;


import ChessBoards.IChessBoard;
import Controller.IController;

import java.awt.event.MouseEvent;

public class MouseCommand extends AGameCommand
{
      IController m_oGameController = null;
      int i_xMouse;
      int i_yMouse;

      public MouseCommand(IController gameController, IGameView gameView, IChessBoard chessBoard)
       {
           super(gameView, chessBoard);
           m_oGameController = gameController;

       }

    public void execute(Object param)
    {
        if(!m_oGameView.getIsPaused())
        {
            m_oGameView.cleanMoves();

            int selectedXCell = (int) ((m_oGameController.getWidth() *  ((MouseEvent)param).getX()) / m_oGameView.getWidth() ) ;
            int selectedYCell = (int) ((m_oGameController.getHeight() * (((MouseEvent)param).getY()-40)) / m_oGameView.getHeight() );

            boolean movePiece = ((MouseEvent)param).isShiftDown();

            if(movePiece)
            {
                m_oChessBoard.moveTo(selectedXCell, selectedYCell);
            }
            else
            {
                m_oGameView.selectAt(selectedXCell, selectedYCell);
                m_oChessBoard.selectAt(selectedXCell, selectedYCell);
                m_oGameView.addMoves(m_oChessBoard.getValidMoves());
                m_oGameView.update();
            }
        }
        else
        {
            m_oGameView.setIsPaused(true);
        }

        m_sologger.debug("Execute");
    }
}
