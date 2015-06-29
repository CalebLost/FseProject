package Tests;
import java.lang.reflect.Field;

import ChessBoards.*;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import ChessBoards.IChessBoard;

public class ChessBoardTests
{
	IChessBoard newBoard;

	@Before
	public void setUp() throws Exception
	{
		newBoard = new DefaultBoard();
	}

	@Test
	public void testBoardInit() throws Exception
	{

        Class<?> secretClass = newBoard.getClass();

        Field field = secretClass.getDeclaredField("m_mGridCells");
        field.setAccessible(true);
        GridCell m_mGridCells[] = (GridCell [])(field.get(newBoard));

        Assert.assertTrue("Should be occupied", m_mGridCells[6 * DefaultBoard.WIDTH + 6].isOccupied() == true);
        Assert.assertTrue("Should be occupied", m_mGridCells[6 * DefaultBoard.WIDTH + 6].getCellColor() == GridCell.CellColor.BLACK);
        Assert.assertTrue("Should be occupied",m_mGridCells[0 * DefaultBoard.WIDTH + 0].isOccupied() == true);
        Assert.assertTrue("Should be occupied",m_mGridCells[0 * DefaultBoard.WIDTH + 0].getCellColor() == GridCell.CellColor.WHITE);
        Assert.assertTrue("Should be occupied", m_mGridCells[4 * DefaultBoard.WIDTH + 4].isOccupied() == false);
        Assert.assertTrue("Should be occupied", m_mGridCells[4 * DefaultBoard.WIDTH + 4].getCellColor() == GridCell.CellColor.NONE);

	}

	@Test
	public void testBoardDimension() throws Exception
    {
        Class<?> secretClass = newBoard.getClass();

        Field field = secretClass.getDeclaredField("m_mGridCells");
        field.setAccessible(true);
        GridCell m_mGridCells[] = (GridCell [])(field.get(newBoard));

        Assert.assertTrue("Should be an 8X8 board", ((m_mGridCells.length == DefaultBoard.WIDTH * DefaultBoard.HEIGHT) && m_mGridCells.length == 64));

	}



}
