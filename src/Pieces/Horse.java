package Pieces;

import ChessBoards.DefaultBoard;
import ChessBoards.GridCell;
import ChessGame.Player;


public class Horse extends Piece
{

	@Override
	public void move(int x, int y)
    {
		setPosition(x, y);
	}

    @Override
    public PieceType getID()
    {
        return PieceType.HORSE;
    }

	@Override
	public boolean canMove(int x, int y, Player player, GridCell[] g)
    {
        GridCell.CellColor opponent = player.getIsFirst() ? GridCell.CellColor.BLACK : GridCell.CellColor.WHITE;

        Position end = new Position(x, y);
        Position start = new Position(getPosition().getX()-1, getPosition().getY()-2);
        if( helperMove(start, end, opponent, g) )
            return true;

        start.setCoords(getPosition().getX()+1, getPosition().getY()-2);
        if( helperMove(start, end, opponent, g) )
            return true;

        start.setCoords(getPosition().getX()+2, getPosition().getY()-1);
        if( helperMove(start, end, opponent, g) )
            return true;

        start.setCoords(getPosition().getX()+2, getPosition().getY()+1);
        if( helperMove(start, end, opponent, g) )
            return true;

        start.setCoords(getPosition().getX()+1, getPosition().getY()+2);
        if( helperMove(start, end, opponent, g) )
            return true;

        start.setCoords(getPosition().getX()-1, getPosition().getY()+2);
        if( helperMove(start, end, opponent, g) )
            return true;

        start.setCoords(getPosition().getX()-2, getPosition().getY()-1);
        if( helperMove(start, end, opponent, g) )
            return true;

        start.setCoords(getPosition().getX()-2, getPosition().getY()+1);
        if( helperMove(start, end, opponent, g) )
            return true;
        else
            return false;


	}

    private boolean helperMove(Position s, Position e, GridCell.CellColor opponent, GridCell[] g)
    {
        if( s.getX() == e.getX() && s.getY() == e.getY() && !g[e.getY()* DefaultBoard.WIDTH + e.getX()].isOccupied())
            return true;
        else if( s.getX() == e.getX() && s.getY() == e.getY() && g[e.getY()* DefaultBoard.WIDTH + e.getX()].isOccupied() && g[e.getY()+ DefaultBoard.WIDTH + e.getX()].getCellColor() == opponent )
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
