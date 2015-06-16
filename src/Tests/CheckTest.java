
package Tests;


import ChessBoards.*;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

public class CheckTest
{
IChessBoard newBoard;

	@Before
	public void setUp() throws Exception
	{
		newBoard = new DefaultBoard();
	}


	@Test
	public void testMove()
    {
		newBoard.getCurrentPlayer().getPieces().getQueen(0).setIsEaten(true);
        Assert.assertTrue("Queen was not eaten", newBoard.getCurrentPlayer().getPieces().getQueen(0).getIsEaten());
        Assert.assertTrue("Queen was not eaten", newBoard.getPlayerOne().getPieces().getQueen(0).getIsEaten());

        newBoard.getCurrentPlayer().getPieces().getHorse(0).setPosition(4, 5);
        Assert.assertTrue("Horse can eat king if king is moved to 3, 7", newBoard.getCurrentPlayer().getPieces().getHorse(0).getPosition().getX() == 4);

        newBoard.selectAt(4, 7);
		newBoard.moveTo(3, 7);
        Assert.assertFalse("King cannot move there, or else will be eaten!",(newBoard.getPlayerOne().getPieces().getPawn(0).getPosition().getY() == 7 && newBoard.getPlayerOne().getPieces().getPawn(0).getPosition().getX() == 3 ));

	}

}
