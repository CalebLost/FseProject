package Pieces;

import java.util.Vector;

import ChessBoards.DefaultBoard;
import ChessBoards.GridCell;
import ChessGame.Player;

public class Pawn extends Piece{
	private boolean m_bIsFirstMove;
	
	public Pawn()
	{
		m_bIsFirstMove = true;
	}

    @Override
    public PieceType getID()
    {
        return PieceType.PAWN;
    }

	public void move(int x, int y) 
	{
		setPosition(x, y);
		m_bIsFirstMove = false;
	}

	public boolean canMove(int x, int y, Player player, GridCell[] g)
	{
        GridCell.CellColor opponent = player.getColor() == GridCell.CellColor.WHITE ? GridCell.CellColor.BLACK : GridCell.CellColor.WHITE;

		if(!player.getIsFirst())
		{
			if(m_bIsFirstMove)
			{
				if(getPosition().getX() == x && getPosition().getY()-2 ==y && !g[y* DefaultBoard.WIDTH +x].isOccupied())
					return true;
				else if(getPosition().getX() == x && getPosition().getY()-1 ==y && !g[y* DefaultBoard.WIDTH +x].isOccupied())
					return true;
				else if(getPosition().getX()-1 ==x && getPosition().getY()-1 == y && g[y* DefaultBoard.WIDTH +x].isOccupied() && g[y* DefaultBoard.WIDTH +x].getCellColor() == opponent)
					return true;
				else if(getPosition().getX()+1 ==x && getPosition().getY()-1 == y && g[y* DefaultBoard.WIDTH +x].isOccupied() && g[y* DefaultBoard.WIDTH +x].getCellColor() == opponent)
					return true;
				else 
					return false;
			}
			else
			{
				if(getPosition().getX() == x && getPosition().getY()-1 ==y && !g[y* DefaultBoard.WIDTH +x].isOccupied())
					return true;
				else if(getPosition().getX()-1 ==x && getPosition().getY()-1 == y && g[y* DefaultBoard.WIDTH +x].isOccupied() && g[y* DefaultBoard.WIDTH +x].getCellColor() == opponent)
					return true;
				else if(getPosition().getX()+1 ==x && getPosition().getY()-1 == y && g[y* DefaultBoard.WIDTH +x].isOccupied() && g[y* DefaultBoard.WIDTH +x].getCellColor() == opponent)
					return true;
				else 
					return false;
			}
		}
		else if(player.getIsFirst())
		{
			if(m_bIsFirstMove)
			{
				if(getPosition().getX() == x && getPosition().getY()+2 ==y && !g[y* DefaultBoard.WIDTH +x].isOccupied())
					return true;
				else if(getPosition().getX() == x && getPosition().getY()+1 ==y && !g[y* DefaultBoard.WIDTH +x].isOccupied())
					return true;
				else if(getPosition().getX()-1 ==x && getPosition().getY()+1 == y && g[y* DefaultBoard.WIDTH +x].isOccupied() && g[y* DefaultBoard.WIDTH +x].getCellColor() == opponent)
					return true;
				else if(getPosition().getX()+1 ==x && getPosition().getY()+1 == y && g[y* DefaultBoard.WIDTH +x].isOccupied() && g[y* DefaultBoard.WIDTH +x].getCellColor() == opponent)
					return true;
				else
					return false;
			}
			else
			{
				if(getPosition().getX() == x && getPosition().getY()+1 ==y && !g[y* DefaultBoard.WIDTH +x].isOccupied())
					return true;
				else if(getPosition().getX()-1 ==x && getPosition().getY()+1 == y && g[y* DefaultBoard.WIDTH +x].isOccupied() && g[y* DefaultBoard.WIDTH +x].getCellColor() == opponent)
					return true;
				else if(getPosition().getX()+1 ==x && getPosition().getY()+1 == y && g[y* DefaultBoard.WIDTH +x].isOccupied() && g[y* DefaultBoard.WIDTH +x].getCellColor() == opponent)
					return true;
				else
					return false;
			}
		}
	
		return false;
	}

    @Override
    public void specialMove(GridCell.CellColor cellColor)
    {
        if(cellColor == GridCell.CellColor.WHITE)
        {
            if(getPosition().getY() == 6)
                m_bIsFirstMove = true;
        }
        else
        {
            if(getPosition().getY() == 1)
                m_bIsFirstMove = true;
        }

    }

    @Override
    public Vector<Position> getMoves(Player player, GridCell[] g) {
        Vector<Position> values = new Vector<Position>();

        for(int i = 0; i < 8;i++ )
        {
            for(int j = 0; j < 8;j++)
            {
                if( canMove(i, j, player, g) )
                    values.add(new Position(i, j) );
            }
        }

        return values;
    }

}
