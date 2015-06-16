package ChessGame;
import ChessBoards.ChessBoardPieces;
import ChessBoards.DefaultBoard;
import ChessBoards.GridCell;
import Pieces.*;
import org.apache.log4j.Logger;

public class Player {

    private static Logger m_sologger = Logger.getLogger(Player.class);

    private ChessBoardPieces   m_oChessBoardPieces = new ChessBoardPieces();
	private GridCell.CellColor m_oCellColor;
    private boolean m_bIsFirst;

	public Player(GridCell.CellColor cellColor, GridCell[] gridCells)
	{
		m_oCellColor   = cellColor;
        m_bIsFirst     = m_oCellColor == GridCell.CellColor.WHITE ? true : false;

        initPieces(gridCells);
	}
    public  GridCell.CellColor getColor()
    {
        return m_oCellColor;
    }
    public boolean getIsFirst()
    {
        return m_bIsFirst;
    }

    public ChessBoardPieces getPieces()
    {
        return m_oChessBoardPieces;
    }
	
	private void initPieces(GridCell[] gridCells)
    {
        switch (m_oCellColor)
        {
            case WHITE:
                setupWhitePieces(gridCells);
                break;

            case BLACK:
                setupBlackPieces(gridCells);
                break;

            default:
                break;
        }
    }

	private void setupWhitePieces(GridCell[] gridCells)
	{	
		int i;

		for(i = 0; i < ChessBoardPieces.PAWN_PIECES; i++)
		{
            m_oChessBoardPieces.setPawn(i,new Pawn());
            m_oChessBoardPieces.getPawn(i).setPosition(i, 1);
			gridCells[1* DefaultBoard.WIDTH +i].setColor(GridCell.CellColor.WHITE);
		}

		for(i = 0; i < ChessBoardPieces.CASTLE_PIECES; i++)
		{
            m_oChessBoardPieces.setCastle(i,new Tower());

			if(i == 0)
			{
                m_oChessBoardPieces.getCastle(i).setPosition(0, 0);
				gridCells[0].setColor(m_oCellColor);
			}
			else
			{
                m_oChessBoardPieces.getCastle(i).setPosition(7, 0);
				gridCells[7].setColor(m_oCellColor);
			}
		}

		for(i = 0; i < ChessBoardPieces.HORSE_PIECES; i++)
		{
            m_oChessBoardPieces.setHorse(i,new Horse());
			if(i == 0)
			{
                m_oChessBoardPieces.getHorse(i).setPosition(1, 0);
				gridCells[1].setColor(m_oCellColor);
			}
			else
			{
                m_oChessBoardPieces.getHorse(i).setPosition(6, 0);
				gridCells[6].setColor(m_oCellColor);
			}
		}

		for(i = 0; i < ChessBoardPieces.BISHOP_PIECES; i++)
		{
            m_oChessBoardPieces.setBishop(i,new Bishop());
			if(i == 0)
			{
                m_oChessBoardPieces.getBishop(i).setPosition(2, 0);
				gridCells[2].setColor(m_oCellColor);
			}
			else
			{
                m_oChessBoardPieces.getBishop(i).setPosition(5, 0);
				gridCells[5].setColor(m_oCellColor);
			}
		}
		

        m_oChessBoardPieces.setKing(0,new King());
        m_oChessBoardPieces.getKing(0).setPosition(4, 0);
		gridCells[4].setColor(m_oCellColor);

        m_oChessBoardPieces.setQueen(0, new Queen());
        m_oChessBoardPieces.getQueen(0).setPosition(3, 0);
        gridCells[3].setColor(m_oCellColor);


	}

	private void setupBlackPieces( GridCell[] gridCells)
	{
		int i;

		for(i = 0; i < ChessBoardPieces.PAWN_PIECES; i++)
		{
			m_oChessBoardPieces.setPawn(i,new Pawn());
            m_oChessBoardPieces.getPawn(i).setPosition(i, 6);
			gridCells[6* DefaultBoard.WIDTH +i].setColor(m_oCellColor);
		}

		
		for(i = 0; i < ChessBoardPieces.CASTLE_PIECES; i++)
		{
            m_oChessBoardPieces.setCastle(i, new Tower());
			if(i == 0)
			{
                m_oChessBoardPieces.getCastle(i).setPosition(0, 7);
				gridCells[7* DefaultBoard.WIDTH +0].setColor(m_oCellColor);
			}
			else
			{
                m_oChessBoardPieces.getCastle(i).setPosition(7, 7);
				gridCells[7* DefaultBoard.WIDTH +7].setColor(m_oCellColor);
			}
		}

		for(i = 0; i < ChessBoardPieces.HORSE_PIECES; i++)
		{
			m_oChessBoardPieces.getHorses()[i] = new Horse();
			if(i == 0)
			{
				m_oChessBoardPieces.getHorse(i).setPosition(1, 7);
				gridCells[7* DefaultBoard.WIDTH +1].setColor(m_oCellColor);
			}
			else
			{
				m_oChessBoardPieces.getHorse(i).setPosition(6, 7);
				gridCells[7* DefaultBoard.WIDTH +6].setColor(m_oCellColor);

			}
		}

		for(i = 0; i < ChessBoardPieces.BISHOP_PIECES; i++)
		{
			m_oChessBoardPieces.getBishops()[i] = new Bishop();
			if(i == 0)
			{
				m_oChessBoardPieces.getBishop(i).setPosition(2, 7);
				gridCells[7* DefaultBoard.WIDTH +2].setColor(m_oCellColor);

			}
			else
			{
				m_oChessBoardPieces.getBishop(i).setPosition(5, 7);
				gridCells[7* DefaultBoard.WIDTH +5].setColor(m_oCellColor);

			}
		}


		m_oChessBoardPieces.setKing(0,new King());
        m_oChessBoardPieces.getKing(0).setPosition(4, 7);
		gridCells[7* DefaultBoard.WIDTH +4].setColor(m_oCellColor);
		

        m_oChessBoardPieces.setQueen(0,new Queen());
        m_oChessBoardPieces.getQueen(0).setPosition(3, 7);
		gridCells[7* DefaultBoard.WIDTH +3].setColor(m_oCellColor);

		
	}



	public Piece validPieceSelected(int xPos, int yPos)
	{
		int index;

		for(index = 0; index < ChessBoardPieces.PAWN_PIECES; index++)
		{	
			if( (xPos == m_oChessBoardPieces.getPawn(index).getPosition().getX()) && (yPos == m_oChessBoardPieces.getPawn(index).getPosition().getY()) )
			{
                m_oChessBoardPieces.getPawn(index).setIsSelected(true);
				return m_oChessBoardPieces.getPawn(index);
			}
		}

		
		for(index = 0; index < ChessBoardPieces.CASTLE_PIECES; index++)
		{	
			if( (xPos == m_oChessBoardPieces.getCastle(index).getPosition().getX()) && (yPos == m_oChessBoardPieces.getCastle(index).getPosition().getY()) )
			{
				m_oChessBoardPieces.getCastle(index).setIsSelected(true);
				return m_oChessBoardPieces.getCastle(index);
			}
		}
		
		for(index = 0; index < ChessBoardPieces.HORSE_PIECES; index++)
		{	
			if( (xPos == m_oChessBoardPieces.getHorse(index).getPosition().getX()) && (yPos == m_oChessBoardPieces.getHorse(index).getPosition().getY()) )
			{
				m_oChessBoardPieces.getHorse(index).setIsSelected(true);
				return m_oChessBoardPieces.getHorse(index);
			}
		}
		
		for(index = 0; index < ChessBoardPieces.BISHOP_PIECES; index++)
		{	
			if( (xPos == m_oChessBoardPieces.getBishop(index).getPosition().getX()) && (yPos == m_oChessBoardPieces.getBishop(index).getPosition().getY()) )
			{
                m_oChessBoardPieces.getBishop(index).setIsSelected(true);
				return m_oChessBoardPieces.getBishop(index);
			}
		}
		
		if( (xPos == m_oChessBoardPieces.getQueen(0).getPosition().getX()) && (yPos == m_oChessBoardPieces.getQueen(0).getPosition().getY()) )
		{
            m_oChessBoardPieces.getQueen(0).setIsSelected(true);
			return m_oChessBoardPieces.getQueen(0);
		}
		if( (xPos == m_oChessBoardPieces.getKing(0).getPosition().getX()) && (yPos == m_oChessBoardPieces.getKing(0).getPosition().getY()) )
		{
            m_oChessBoardPieces.getKing(0).setIsSelected(true);
			return  m_oChessBoardPieces.getKing(0);
		}
		
		
		
		return null;
	}



	public boolean movePiece(Piece p, int newX, int newY, GridCell[] gridCells)
	{
		
		if(p.canMove(newX, newY, this, gridCells))
		{
			p.move(newX, newY);
			return true;
		}
		
		return false;
	}



	public Piece checkEaten(int newX, int newY)
	{
		int i;
		for(i = 0; i < ChessBoardPieces.PAWN_PIECES; i++)
		{	
			if((newX == m_oChessBoardPieces.getPawn(i).getPosition().getX()) && (newY == m_oChessBoardPieces.getPawn(i).getPosition().getY()))
			{
                m_oChessBoardPieces.getPawn(i).setIsEaten(true);
				return m_oChessBoardPieces.getPawn(i);
			}
		}

		for(i = 0; i < ChessBoardPieces.CASTLE_PIECES; i++)
		{
			if((newX == m_oChessBoardPieces.getCastle(i).getPosition().getX()) && (newY == m_oChessBoardPieces.getCastle(i).getPosition().getY()))
			{
                m_oChessBoardPieces.getCastle(i).setIsEaten(true);
				return m_oChessBoardPieces.getCastle(i);
			}
		}

		for(i = 0; i < ChessBoardPieces.HORSE_PIECES; i++)
		{
			if((newX == m_oChessBoardPieces.getHorse(i).getPosition().getX()) && (newY == m_oChessBoardPieces.getHorse(i).getPosition().getY()))
			{
                m_oChessBoardPieces.getHorse(i).setIsEaten(true);
				return m_oChessBoardPieces.getHorse(i);
			}
		}
		

		for(i = 0; i <  ChessBoardPieces.BISHOP_PIECES; i++)
		{
			if((newX == m_oChessBoardPieces.getBishop(i).getPosition().getX()) && (newY == m_oChessBoardPieces.getBishop(i).getPosition().getY()))
			{
                m_oChessBoardPieces.getBishop(i).setIsEaten(true);
				return m_oChessBoardPieces.getBishop(i);
			}

		}


		if((newX == m_oChessBoardPieces.getKing(0).getPosition().getX()) && (newY == m_oChessBoardPieces.getKing(0).getPosition().getY()))
		{
            m_oChessBoardPieces.getKing(0).setIsEaten(true);
			return m_oChessBoardPieces.getKing(0);
		}

		if((newX == m_oChessBoardPieces.getQueen(0).getPosition().getX()) && (newY == m_oChessBoardPieces.getQueen(0).getPosition().getY()))
		{
            m_oChessBoardPieces.getQueen(0).setIsEaten(true);
			return m_oChessBoardPieces.getQueen(0);
		}

        return null;

	}

}
