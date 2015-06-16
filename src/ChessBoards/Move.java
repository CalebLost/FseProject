package ChessBoards;

import Pieces.*;

public class Move
{
    private Piece              m_oMovedPiece;
    private Piece              m_oEatenPiece;
    private Position           m_oPreviousPosition;
    private Position           m_oNewPosition;
    private GridCell.CellColor m_oCellColor;

    Move()
    {
        m_oPreviousPosition = new Position(-1, -1);
        m_oNewPosition      = new Position(-1, -1);
    }

    public void update(Piece p, Piece e, GridCell.CellColor c, Position old, Position newP)
    {
        m_oEatenPiece = e;
        m_oMovedPiece = p;
        m_oPreviousPosition.setCoords(old.getX(), old.getY());
        m_oNewPosition.setCoords(newP.getX(), newP.getY());
        m_oCellColor  = c;
    }

    public void movePrev(GridCell[] gridCells)
    {
        m_oMovedPiece.setPosition(m_oPreviousPosition.getX(), m_oPreviousPosition.getY());
        m_oMovedPiece.specialMove(m_oCellColor);
        int ncellPos = m_oNewPosition.getY() * DefaultBoard.WIDTH + m_oNewPosition.getX();
        int pcellPos = m_oPreviousPosition.getY() * DefaultBoard.WIDTH + m_oPreviousPosition.getX();
        gridCells[ncellPos].setColor(GridCell.CellColor.NONE);
        gridCells[pcellPos].setColor(m_oCellColor);

        if(m_oEatenPiece != null)
        {
            GridCell.CellColor opponent = m_oCellColor == GridCell.CellColor.WHITE ? GridCell.CellColor.BLACK : GridCell.CellColor.WHITE;
            m_oEatenPiece.setIsEaten(false);
            m_oEatenPiece.setPosition(m_oNewPosition.getX(),m_oNewPosition.getY());
            gridCells[ncellPos].setColor(opponent);
        }

    }

}
