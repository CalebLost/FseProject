package Pieces;

import ChessBoards.GridCell;
import ChessGame.Player;


public class Queen extends Piece{

	@Override
	public void move(int x, int y)
    {
        setPosition(x, y);
	}

	@Override
	public boolean canMove(int x, int y , Player player, GridCell[] g)
    {

        Bishop bis = new Bishop();
        bis.setPosition(getPosition().getX(), getPosition().getY());
        boolean b = bis.canMove(x, y, player, g);
        if(!b)
        {
            Tower tow = new Tower();
            tow.setPosition(getPosition().getX(), getPosition().getY());
            boolean c = tow.canMove(x, y ,player, g);
            return c;
        }
        else
            return true;

	}


    @Override
    public void specialMove(GridCell.CellColor cellColor)
    {
        return;
    }

    @Override
    public PieceType getID()
    {
        return PieceType.QUEEN;
    }

}
