package ChessBoards;

import java.util.Vector;

import ChessGame.Player;
import Pieces.PieceType;
import Pieces.Position;
import Pieces.Piece;
import org.apache.log4j.Logger;


public class DefaultBoard implements IChessBoard
{

    private static Logger m_sologger = Logger.getLogger(DefaultBoard.class);

    private StringBuilder dbgString = new StringBuilder();
    public Player m_oPlayer1;
    public Player m_oPlayer2;

	public static final int WIDTH  = 8;
	public static final int HEIGHT = 8;

	private GridCell [] m_mGridCells = new GridCell[WIDTH*HEIGHT];
    private int m_iXPos, m_iYPos;
    private int m_iTurn;
    private Move m_oUndoMove;




	public DefaultBoard()
    {
        reset();
	}


    public void reset()
    {
        m_oUndoMove = new Move();
        m_iTurn = 0;

        for(int i = 0; i < WIDTH * HEIGHT; i++)
        {
                m_mGridCells[i] = new GridCell();
        }

        m_oPlayer1 = new Player(GridCell.CellColor.WHITE, m_mGridCells);
        m_oPlayer2 = new Player(GridCell.CellColor.BLACK, m_mGridCells);

    }

	public Piece getPiece(Player player, PieceType pieceType, int index)
	{
		Piece p;
		switch (pieceType)
        {
        case PAWN:
        			p = player.getPieces().getPawn(index);
                    break;
        case TOWER:
                    p = player.getPieces().getCastle(index);
    			    break;
        case HORSE:
                    p = player.getPieces().getHorse(index);
        		    break;
        case BISHOP:
                    p = player.getPieces().getBishop(index);
		            break;
        case QUEEN:
                    p = player.getPieces().getQueen(index);
        		    break;
        case KING:
                    p = player.getPieces().getKing(index);
    		 	    break;
        default : 
        		p = null;
                m_sologger.debug("Invalid getPiece Selection");
        		break;
		}

		return p;
	}

    public void selectAt(int x, int y)
    {
        m_iXPos = x;
        m_iYPos = y;
    }

    public void moveTo(int xPos, int yPos)
    {

        Piece pieceSelected = selection();

        boolean moved = false;

        if(pieceSelected != null)
        {

                moved = tryMovePiece(pieceSelected, xPos, yPos);

                if(moved)
                {
                    m_mGridCells[m_iYPos*WIDTH+m_iXPos].setColor(GridCell.CellColor.NONE);
                    m_mGridCells[yPos*WIDTH+xPos].setColor(getCurrentColor());

                    Piece eaten = getOpponentPlayer().checkEaten(xPos, yPos);
                    m_oUndoMove.update(pieceSelected, eaten, getCurrentColor(),new Position(m_iXPos, m_iYPos), new Position(xPos, yPos) );
                    pieceSelected.setIsSelected(false);
                    m_iTurn++;
                }


            }

        logGrid();

    }

    private Piece selection()
    {
      return getCurrentPlayer().validPieceSelected(m_iXPos, m_iYPos);
    }

    public boolean tryMovePiece(Piece pSelected, int x, int y)
    {
      return getCurrentPlayer().movePiece(pSelected, x, y, m_mGridCells);
    }

    public Player getPlayerOne()
    {
        return m_oPlayer1;
    }

    public Player getPlayerTwo()
    {
        return m_oPlayer2;
    }

    public Player getCurrentPlayer()
    {
        return m_iTurn%2==0 ? m_oPlayer1 : m_oPlayer2;
    }
    public Player getOpponentPlayer()
    {
        return m_iTurn%2==0 ? m_oPlayer2 : m_oPlayer1;
    }

    private GridCell.CellColor getCurrentColor()
    {
        return getCurrentPlayer().getColor();
    }

    public Vector<Position> getValidMoves()
    {
       Vector<Position> moves = null;


       Piece selected = selection();

        if(selected != null)
        {
           moves = selected.getMoves(getCurrentPlayer(), m_mGridCells);
           return moves;
        }

        return moves;

    }


    public void undo()
    {
        if(m_iTurn == 0)
        {
            return;
        }

        m_oUndoMove.movePrev(m_mGridCells);
        m_iTurn++;
    }


    public void forfeit()
    {
       getCurrentPlayer().getPieces().getKing(0).setIsEaten(true);
    }


	private void logGrid()
	{
        m_sologger.debug("------------------");

		for(int j = 0; j < HEIGHT; j++)
		{
            dbgString = new StringBuilder();
            dbgString.append("|");

			for(int i =0; i < WIDTH;i++ )
			{
				if(m_mGridCells[j * WIDTH + i].isOccupied())
				{
                    dbgString.append(m_mGridCells[j * DefaultBoard.WIDTH + i].getCellColor().toString().charAt(0)).append(" ");
				}
				else
                    dbgString.append(". ");
			}

            dbgString.append("|");
            m_sologger.debug(dbgString.toString());
		}
        m_sologger.debug("------------------");
	}

}
