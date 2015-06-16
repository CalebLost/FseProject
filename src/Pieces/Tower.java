package Pieces;

import ChessBoards.DefaultBoard;
import ChessBoards.GridCell;
import ChessGame.Player;

public class Tower extends Piece{

	@Override
	public void move(int x, int y)
    {
		setPosition(x, y);
	}

    @Override
    public PieceType getID()
    {
        return PieceType.TOWER;
    }

	@Override
	public boolean canMove(int x, int y , Player player, GridCell[] g)
    {
        if(getPosition().getX() != x && getPosition().getY() != y)
        {
            return false;
        }
        else if(getPosition().getX() == x && getPosition().getY() == y)
        {
            return false;
        }

        return recurseCheck(new Position(getPosition().getX(), getPosition().getY()), new Position(x, y), player.getColor() == GridCell.CellColor.WHITE ? GridCell.CellColor.BLACK : GridCell.CellColor.WHITE, getDirection(x, y), g, 0);
    }



    private int getDirection(int x, int y)
    {
        if(x > getPosition().getX())
            return 1;

        else if(x < getPosition().getX())
            return 3;
        else
        {
            if(y > getPosition().getY())
                return 2;
            else
                return 4;
        }
    }

    private boolean recurseCheck(Position s, Position e, GridCell.CellColor opponent, int direction, GridCell[] g, int count)
    {

        if(s.equal(e) && !g[e.getY()* DefaultBoard.WIDTH +e.getX()].isOccupied())
            return true;
        else if(s.equal(e) && g[e.getY()* DefaultBoard.WIDTH +e.getX()].isOccupied() && g[e.getY()* DefaultBoard.WIDTH +e.getX()].getCellColor() == opponent)
            return true;
        else if(g[s.getY()* DefaultBoard.WIDTH +s.getX()].isOccupied() && count > 0)
            return false;
        else
        {
            if(direction == 1)
            {
                s.setCoords(s.getX()+1, s.getY());
                if(s.getX() > e.getX())
                    return false;
                return recurseCheck(s, e, opponent, direction, g, 1);
            }
            else if(direction == 2)
            {
                s.setCoords(s.getX(), s.getY()+1);
                if(s.getY() > e.getY())
                    return false;
                return recurseCheck(s, e, opponent, direction, g, 1);
            }
            else if(direction == 3)
            {
                s.setCoords(s.getX()-1, s.getY());
                if(s.getX() < e.getX())
                    return false;
                return recurseCheck(s, e, opponent, direction, g,1);
            }
            else
            {
                s.setCoords(s.getX(), s.getY()-1);
                if(s.getY() < e.getY())
                    return false;
                return recurseCheck(s, e, opponent, direction, g,1);
            }
        }
    }


    @Override
    public void specialMove(GridCell.CellColor cellColor)
    {
        return;
    }


}
