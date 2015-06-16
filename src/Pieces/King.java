package Pieces;

import ChessBoards.DefaultBoard;
import ChessBoards.GridCell;
import ChessGame.Player;


public class King extends Piece{

	@Override
	public void move(int x, int y) {
		setPosition(x, y);
		
	}

    @Override
    public PieceType getID()
    {
        return PieceType.KING;
    }

	@Override
	public boolean canMove(int x, int y , Player player, GridCell[] g)
    {
        GridCell.CellColor opponent = player.getColor() == GridCell.CellColor.WHITE ? GridCell.CellColor.BLACK : GridCell.CellColor.WHITE;
        int cellPos = y* DefaultBoard.WIDTH + x;
        if(getPosition().getX()-1 ==x && getPosition().getY() == y && (!g[cellPos].isOccupied() || g[cellPos].getCellColor().equals(opponent)) )
            return true;
        else if(getPosition().getX()+1 ==x && getPosition().getY() == y && (!g[cellPos].isOccupied() || g[cellPos].getCellColor().equals(opponent)) )
            return true;
        else if(getPosition().getX() ==x && getPosition().getY()+1 == y && (!g[cellPos].isOccupied() || g[cellPos].getCellColor().equals(opponent)) )
            return true;
        else if(getPosition().getX() ==x && getPosition().getY()-1 == y && (!g[cellPos].isOccupied() || g[cellPos].getCellColor().equals(opponent)) )
            return true;
        else if(getPosition().getX()+1 ==x && getPosition().getY()+1 == y && (!g[cellPos].isOccupied() || g[cellPos].getCellColor().equals(opponent)) )
            return true;
        else if(getPosition().getX()-1 ==x && getPosition().getY()+1 == y && (!g[cellPos].isOccupied() || g[cellPos].getCellColor().equals(opponent)) )
            return true;
        else if(getPosition().getX()+1 ==x && getPosition().getY()-1 == y && (!g[cellPos].isOccupied() || g[cellPos].getCellColor().equals(opponent)) )
            return true;
        else if(getPosition().getX()-1 ==x && getPosition().getY()-1 == y && (!g[cellPos].isOccupied() || g[cellPos].getCellColor().equals(opponent)) )
            return true;
        else
            return false;
    }



    @Override
    public void specialMove(GridCell.CellColor cellColor)
    {
        return;
    }

}
