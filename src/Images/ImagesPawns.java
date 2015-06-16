package Images;
import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

public class ImagesPawns {

    private static Logger m_soLogger = Logger.getLogger(ImagesPawns.class);

	private BufferedImage whitePawn;
	private BufferedImage whiteCastle;
	private BufferedImage whiteHorse;
	private BufferedImage whiteBishop;
	private BufferedImage whiteQueen;
	private BufferedImage whiteKing;

	private BufferedImage blackPawn;
	private BufferedImage blackCastle;
	private BufferedImage blackHorse;
	private BufferedImage blackBishop;
	private BufferedImage blackQueen;
	private BufferedImage blackKing;

    //TODO all should be externally configured, xml...
    private static final String whitePawnImg     = "White_pawn.png";
    private static final String whiteCastleImg   = "White_castle.png";
    private static final String whiteHorseImg    = "White_horse.png";
    private static final String whiteBishopImg   = "White_bishop.png";
    private static final String whiteQueenImg    = "White_queen.png";
    private static final String whiteKingImg     = "White_king.png";

    private static final String blackPawnImg     = "Black_pawn.png";
    private static final String blackCastleImg   = "Black_castle.png";
    private static final String blackHorseImg    = "Black_horse.png";
    private static final String blackBishopImg   = "Black_bishop.png";
    private static final String blackQueenImg    = "Black_queen.png";
    private static final String blackKingImg     = "Black_king.png";

    private boolean m_bIsInit;

    private java.net.URL readResource(String resName) throws Exception
    {
        return getClass().getResource(resName);
    }

    static String getPrefix()
    {
        return getSeparator();
    }

    static String getSeparator()
    {
        return "/";
    }
    public void init(String path)
    {
        path = new StringBuilder().append(getPrefix()).append(path).append(getSeparator()).toString();

        try
        {
            whitePawn = ImageIO.read(readResource(new StringBuilder().append(path).append(whitePawnImg).toString()));
        } catch (Exception e)
        {
             m_soLogger.debug(e.getMessage());
        }


        try
        {
            whiteCastle = ImageIO.read(readResource(new StringBuilder().append(path).append(whiteCastleImg).toString()));
        } catch (Exception e)
        {
           m_soLogger.debug(e.getMessage());
        }


        try
        {
            whiteHorse = ImageIO.read(readResource(new StringBuilder().append(path).append(whiteHorseImg).toString()));
        } catch (Exception e)
        {
            m_soLogger.debug(e.getMessage());
        }

        try
        {
            whiteBishop = ImageIO.read(readResource(new StringBuilder().append(path).append(whiteBishopImg).toString()));
        } catch (Exception e)
        {
            m_soLogger.debug(e.getMessage());
        }


        try
        {
            whiteQueen = ImageIO.read(readResource(new StringBuilder().append(path).append(whiteQueenImg).toString()));
        } catch (Exception e)
        {
            m_soLogger.debug(e.getMessage());
        }

        try
        {
            whiteKing = ImageIO.read(readResource(new StringBuilder().append(path).append(whiteKingImg).toString()));
        } catch (Exception e)
        {
            m_soLogger.debug(e.getMessage());
        }

        try
        {
            blackPawn = ImageIO.read(readResource(new StringBuilder().append(path).append(blackPawnImg).toString()));
        } catch (Exception e)
        {
            m_soLogger.debug(e.getMessage());
        }


        try
        {
            blackCastle = ImageIO.read(readResource(new StringBuilder().append(path).append(blackCastleImg).toString()));
        } catch (Exception e)
        {
            m_soLogger.debug(e.getMessage());
        }


        try
        {
            blackHorse = ImageIO.read(readResource(new StringBuilder().append(path).append(blackHorseImg).toString()));
        } catch (Exception e)
        {
            m_soLogger.debug(e.getMessage());
        }

        try
        {
            blackBishop = ImageIO.read(readResource(new StringBuilder().append(path).append(blackBishopImg).toString()));
        } catch (Exception e)
        {
            m_soLogger.debug(e.getMessage());
        }


        try
        {
            blackQueen = ImageIO.read(readResource(new StringBuilder().append(path).append(blackQueenImg).toString()));
        } catch (Exception e)
        {
           m_soLogger.debug(e.getMessage());
        }

        try
        {
            blackKing = ImageIO.read(readResource(new StringBuilder().append(path).append(blackKingImg).toString()));
        } catch (Exception e)
        {
            m_soLogger.debug(e.getMessage());
        }

        m_bIsInit = true;
    }

    public boolean isInit()
    {
        return m_bIsInit;
    }

	public ImagesPawns()
    {
        m_bIsInit = false;
	}

    public BufferedImage getWhitePawn()
    {
        return whitePawn;
    }

    public BufferedImage getWhiteCastle()
    {
        return whiteCastle;
    }

    public BufferedImage getWhiteHorse()
    {
        return whiteHorse;
    }

    public BufferedImage getWhiteBishop()
    {
        return whiteBishop;
    }

    public BufferedImage getWhiteQueen()
    {
        return whiteQueen;
    }

    public BufferedImage getWhiteKing()
    {
        return whiteKing;
    }

    public BufferedImage getBlackPawn()
    {
        return blackPawn;
    }

    public BufferedImage getBlackCastle()
    {
        return blackCastle;
    }

    public BufferedImage getBlackHorse()
    {
        return blackHorse;
    }

    public BufferedImage getBlackBishop()
    {
        return blackBishop;
    }

    public BufferedImage getBlackQueen()
    {
        return blackQueen;
    }

    public BufferedImage getBlackKing()
    {
        return blackKing;
    }
}
