package Tests;

import org.junit.Assert;

import ChessBoards.*;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

public class PieceMovements
{
    IChessBoard newBoard;

	@Before
	public void setUp() throws Exception
	{
		newBoard = new DefaultBoard();
	}

	@Test
	public void testPawn()
    {
		newBoard.selectAt(3, 1);
		newBoard.moveTo(3, 2);
		Assert.assertTrue("Piece didn't move to correct place!", (newBoard.getPlayerOne().getPieces().getPawn(3).getPosition().getY() == 2 && newBoard.getPlayerOne().getPieces().getPawn(3).getPosition().getX() == 3));

        newBoard.selectAt(3, 6);
		newBoard.moveTo(3, 4);
        Assert.assertTrue("Piece did move to incorrect place!", (newBoard.getPlayerTwo().getPieces().getPawn(3).getPosition().getY() == 4 && newBoard.getPlayerOne().getPieces().getPawn(3).getPosition().getX() == 3));

    }

	@Test
	public void testCastle() throws Exception
    {
        newBoard.selectAt(0, 0);
		newBoard.moveTo(0, 5);
        Assert.assertFalse("Piece didn't move to correct place!", (newBoard.getPlayerOne().getPieces().getCastle(0).getPosition().getY() == 5 && newBoard.getPlayerOne().getPieces().getCastle(0).getPosition().getX() == 0));

        //clean the path
        Class<?> secretClass = newBoard.getClass();

        Field field = secretClass.getDeclaredField("m_mGridCells");
        field.setAccessible(true);
        GridCell m_mGridCells[][] = (GridCell [][])(field.get(newBoard));
        m_mGridCells[0][1].setColor(GridCell.CellColor.NONE);

        newBoard.moveTo(0, 5);
        Assert.assertTrue("Piece didn't move to correct place!", (newBoard.getPlayerOne().getPieces().getCastle(0).getPosition().getY() == 5 && newBoard.getPlayerOne().getPieces().getCastle(0).getPosition().getX() == 0));
	}

	@Test
	public void testHorse()
    {
		newBoard.selectAt(1, 0);
		newBoard.moveTo(2, 2);
		Assert.assertTrue("Piece didn't move to correct place!", (newBoard.getPlayerOne().getPieces().getHorse(0).getPosition().getY() == 2 && newBoard.getPlayerOne().getPieces().getHorse(0).getPosition().getX() == 2));

	}


	@Test
	public void testBishop() throws Exception
    {
        newBoard.selectAt(2, 0);
		newBoard.moveTo(5, 3);
		Assert.assertFalse("Piece didn't move to correct place!", (newBoard.getPlayerOne().getPieces().getBishop(0).getPosition().getY() == 2 && newBoard.getPlayerOne().getPieces().getBishop(0).getPosition().getX() == 5));

        //clean the path
        Class<?> secretClass = newBoard.getClass();

        Field field = secretClass.getDeclaredField("m_mGridCells");
        field.setAccessible(true);
        GridCell m_mGridCells[][] = (GridCell [][])(field.get(newBoard));
        m_mGridCells[3][1].setColor(GridCell.CellColor.NONE);

        newBoard.moveTo(5, 3);
        Assert.assertTrue("Piece didn't move to correct place!", (newBoard.getPlayerOne().getPieces().getBishop(0).getPosition().getY() == 3 && newBoard.getPlayerOne().getPieces().getBishop(0).getPosition().getX() == 5));
    }

	@Test
	public void testQueen() throws Exception
    {
		newBoard.selectAt(3, 0);
        newBoard.moveTo(5, 2);
        Assert.assertFalse("Piece didn't move to correct place!", (newBoard.getPlayerOne().getPieces().getQueen(0).getPosition().getY() == 2 && newBoard.getPlayerOne().getPieces().getQueen(0).getPosition().getX() == 5));

        //clean the path
        Class<?> secretClass = newBoard.getClass();

        Field field = secretClass.getDeclaredField("m_mGridCells");
        field.setAccessible(true);
        GridCell m_mGridCells[][] = (GridCell [][])(field.get(newBoard));
        m_mGridCells[4][1].setColor(GridCell.CellColor.NONE);

        newBoard.selectAt(3, 0);
        newBoard.moveTo(5, 2);
        Assert.assertTrue("Piece didn't move to correct place!", (newBoard.getPlayerOne().getPieces().getQueen(0).getPosition().getY() == 2 && newBoard.getPlayerOne().getPieces().getQueen(0).getPosition().getX() == 5));
	}

	@Test
	public void testKing() throws Exception
    {
        newBoard.selectAt(4,0);
		newBoard.moveTo(4, 1);
		Assert.assertFalse("Piece didn't move to correct place!", (newBoard.getPlayerOne().getPieces().getKing(0).getPosition().getY() == 1 && newBoard.getPlayerOne().getPieces().getKing(0).getPosition().getX() == 4));

        //clean the path
        Class<?> secretClass = newBoard.getClass();

        Field field = secretClass.getDeclaredField("m_mGridCells");
        field.setAccessible(true);
        GridCell m_mGridCells[][] = (GridCell [][])(field.get(newBoard));
        m_mGridCells[4][1].setColor(GridCell.CellColor.NONE);

        newBoard.moveTo(4, 1);
        Assert.assertTrue("Piece didn't move to correct place!", (newBoard.getPlayerOne().getPieces().getKing(0).getPosition().getY() == 1 && newBoard.getPlayerOne().getPieces().getKing(0).getPosition().getX() == 4));
	}

}
