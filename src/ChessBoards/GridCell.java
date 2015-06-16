package ChessBoards;

public class GridCell
{
    public enum CellColor
    {
        NONE,
        WHITE,
        BLACK
    };

    public CellColor getCellColor()
    {
        return m_oCellColor;
    }

    public void setColor(CellColor cellColor)
    {
        m_oCellColor = cellColor;
    }

    private CellColor m_oCellColor;

    public boolean isOccupied()
    {
        return !(m_oCellColor == CellColor.NONE);
    }

	public GridCell()
	{
		m_oCellColor = CellColor.NONE;
	}
	
	public GridCell(CellColor cellColor)
    {
        m_oCellColor = cellColor;
	}

}
