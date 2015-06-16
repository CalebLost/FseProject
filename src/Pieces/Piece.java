package Pieces;

import java.util.Vector;

import ChessBoards.DefaultBoard;
import ChessBoards.GridCell;
import ChessGame.Player;

public abstract class Piece {
	
	private Position m_oPosition;
	private boolean m_bIsEaten;
	private boolean m_bIsSelected;

	public Piece() 
	{
		m_bIsEaten    = false;
		m_bIsSelected = false;
		m_oPosition   = new Position(0,0);
	}
	
	public abstract void move(int x, int y);

	public abstract boolean canMove(int x, int y, Player player, GridCell[] g);

    public abstract void specialMove(GridCell.CellColor cellColor);

    public abstract PieceType getID();

    public Position getPosition()
    {
        return m_oPosition;
    }

    public void setPosition(Position position)
    {
        m_oPosition = position;
    }
    public void setPosition(int x,int y)
    {
        m_oPosition.setCoords(x,y);
    }
    public boolean getIsEaten()
    {
        return m_bIsEaten;
    }

    public void setIsEaten(boolean isEaten)
    {
        m_bIsEaten = isEaten;

        if(m_bIsEaten)
        {
            m_oPosition.setCoords(-1,-1);
        }

    }

    public boolean getIsSelected()
    {
        return m_bIsSelected;
    }

    public void setIsSelected(boolean isSelected)
    {
        m_bIsSelected = isSelected;
    }

    public Vector<Position> getMoves(Player player, GridCell[] g)
    {
        Vector<Position> values = new Vector<Position>();

        for(int i = 0; i < DefaultBoard.WIDTH; i++ )
        {
            for(int j = 0; j < DefaultBoard.HEIGHT;j++)
            {
                if( canMove(i, j, player, g) )
                {
                    values.add(new Position(i, j) );
                }
            }
        }

        return values;
    }
}
