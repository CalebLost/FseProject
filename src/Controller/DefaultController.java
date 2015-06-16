package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import ChessBoards.*;
import ChessGame.BoardCommands;
import ChessGame.*;
import Pieces.*;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Vector;


public class DefaultController implements IController
{


    private IGameView   m_soGameViewer;
    private IChessBoard m_soGameBoard;

    public DefaultController(IGameView view, IChessBoard model)
    {
        setViewModel(view,model);
    }

    @Override
    public void start()
    {
        m_soGameViewer.start();
    }

    @Override
    public Piece getPieces(Player player, PieceType pieceType, int index)
    {
        return m_soGameBoard.getPiece(player, pieceType, index);
    }

    public void setViewModel(IGameView view,IChessBoard model)
    {
        m_soGameBoard  = model;
        m_soGameViewer = view;
        m_soGameViewer.setController(this);
        m_soGameViewer.setChessBoardCommandListener(new ChessBoardCommandListener()) ;
        m_soGameViewer.setChessBoardMouseListener(new ChessBoardMouseListener(this));
    }

    @Override
    public int getWidth()
    {
        return DefaultBoard.WIDTH;
    }

    @Override
    public int getHeight()
    {
        return DefaultBoard.HEIGHT;
    }

    @Override
    public Player gameOver()
    {
        if(getPlayerOne().getPieces().getKing(0).getIsEaten())
        {
            return getPlayerTwo();
        }
        else if(getPlayerTwo().getPieces().getKing(0).getIsEaten())
        {
            return getPlayerOne();
        }
        else
            return null;
    }

    @Override
    public Player getPlayerOne()
    {
        return m_soGameBoard.getPlayerOne();
    }

    @Override
    public Player getPlayerTwo()
    {
        return m_soGameBoard.getPlayerTwo();
    }

    class ChessBoardCommandListener implements ActionListener
    {

            HashMap<BoardCommands,ICommand> m_oCommands = new HashMap<BoardCommands,ICommand>();

            ChessBoardCommandListener()
            {
                //Command factory better
                m_oCommands.put(BoardCommands.NEW, new NewCommand(m_soGameViewer,m_soGameBoard));
                m_oCommands.put(BoardCommands.RESTART,new RestartCommand(m_soGameViewer,m_soGameBoard));
                m_oCommands.put(BoardCommands.PAUSE,new PauseCommand(m_soGameViewer,m_soGameBoard));
                m_oCommands.put(BoardCommands.UNDO,new UndoCommand(m_soGameViewer,m_soGameBoard));
                m_oCommands.put(BoardCommands.QUIT,new QuitCommand(m_soGameViewer,m_soGameBoard));
            }


            public void actionPerformed(ActionEvent e)
            {
                BoardCommands command  = Enum.valueOf(BoardCommands.class,e.getActionCommand());
                m_oCommands.get(command).execute(null);

            }

    }

    class ChessBoardMouseListener implements MouseListener
    {
          ICommand mouseCommand = null;

        ChessBoardMouseListener(IController controller)
        {
            mouseCommand = new MouseCommand(controller,m_soGameViewer,m_soGameBoard);
        }

        public void mouseClicked(MouseEvent e)
        {
            mouseCommand.execute(e);
        }

        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
    }






}
