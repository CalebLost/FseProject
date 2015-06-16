package Pieces;

public class Position
{
	private int m_iXPos, m_iYPos;
	
	public Position(int x, int y)
    {
		m_iXPos = x;
		m_iYPos = y;

	}
	
	public void setCoords(int x, int y)
	{
		m_iXPos = x;
		m_iYPos = y;
	}
	
	public boolean equal(Position p)
	{
		if(m_iXPos == p.getX() && m_iYPos == p.getY())
        {
			return true;
        }
		else
        {
			return false;
        }
	}
	
	public int getX()
	{
        return m_iXPos;
    }
	
	public int  getY()
	{
        return m_iYPos;
    }
	
	
}
