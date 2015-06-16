
package Tests;

import org.junit.Assert;

import ChessBoards.*;
import org.junit.Before;
import org.junit.Test;

public class GameOverTest
{
	IChessBoard newBoard;

	@Before
	public void setUp() throws Exception
	{
		newBoard = new DefaultBoard();
	}

	@Test
	public void test()
    {
		newBoard.getPlayerOne().getPieces().getKing(0).setIsEaten(true);
		Assert.assertTrue("King should be eaten", newBoard.getPlayerOne().getPieces().getKing(0).getIsEaten());

        newBoard.getPlayerTwo().getPieces().getKing(0).setIsEaten(true);
        Assert.assertTrue("King should be eaten", newBoard.getPlayerTwo().getPieces().getKing(0).getIsEaten());
	}

}
