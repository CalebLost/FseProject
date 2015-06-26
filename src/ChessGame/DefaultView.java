package ChessGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ChessBoards.ChessBoardPieces;
import Controller.*;
import Images.ImagesPawns;
import Pieces.PieceType;
import Pieces.Position;
import Pieces.Piece;
import org.apache.log4j.Logger;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.Vector;

public class DefaultView extends JPanel implements IGameView
{
    private static Logger m_sologger = Logger.getLogger(DefaultView.class);

    Graphics                 m_gGraphics = null;
    public int               m_iXPos, m_iYPos;
    private ActionListener   m_oActionListener;
    private MouseListener    m_oMouseListener;
    public boolean           m_bPause;
    private ImagesPawns      m_oImagePawns;
    private int              m_iXDimension, m_iYDimension;
    private IController       m_oController;
    private JFrame           m_soMainFrame;
    private JFrame           m_soInfoFrame;
    private JFrame           m_soScoreBoardFrame;
    private String           m_NamePlayer1;
    private String           m_NamePlayer2;
    private int              m_iScorePlayer1;
    private int              m_iScorePlayer2;
    private Vector<Position> m_vMoves;
    ViewConfig               m_oViewConfig;

    private boolean m_bIsInit;

    //TODO: serialization load from res file
    class ViewConfig
    {
        public static final String configNames          = "NAMES";
        public static final String configNameOne        = "PLAYER 1";
        public static final String configNameTwo        = "PLAYER 2";
        public static final String configButtonStart    = "START";
        public static final String configScoreBoardName = "SCORE BOARD";
        public static final String configPieces         = "ChessPieces";
        public static final String configTitle          = "Chess :)";
        public static final String fontName             = "Algerian";
        public static final String pauseName            = "PAUSE";
        public static final String configMenuBoard      = "BOARD";
        public static final String configMenuSettings   = "Settings";
        public static final String configMenuNew        = "New";
        public static final String configMenuRestart    = "Restart";
        public static final String configMenuPause      = "Pause";
        public static final String configMenuQuit       = "Quit";
        public static final String configMenuEdit       = "Edit";
        public static final String configMenuUndo       = "Undo";
    }

    public DefaultView()
    {
        m_oViewConfig = new ViewConfig();
    }

    @Override
    public void setController(IController c)
    {
        m_oController = c;
    }

    @Override
    public void update()
    {
        repaint();
    }

    @Override
    public void start()
    {
        if(!m_bIsInit)
        {

          initializeBoard();

          m_iScorePlayer1 = 0;
          m_iScorePlayer2 = 0;

         setupNames();

         m_bIsInit = true;

        }
    }

    @Override
    public void setIsPaused(boolean pause)
    {
        m_bPause = pause;
    }

    @Override
    public boolean getIsPaused()
    {
      return m_bPause;
    }
    @Override
    public void setChessBoardCommandListener(ActionListener listener)
    {
         m_oActionListener = listener;
    }
    @Override
    public void setChessBoardMouseListener(MouseListener listener)
    {
        m_oMouseListener = listener;
    }
    @Override
    public void selectAt(int x, int y)
        {
            m_iXPos = x;
            m_iYPos = y;
        }

    private void setupNames()
    {
        m_soInfoFrame = new JFrame(m_oViewConfig.configNames);
        m_soInfoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel p1 = new JLabel(m_oViewConfig.configNameOne);
        final JLabel p2 = new JLabel(m_oViewConfig.configNameTwo);

        final JTextField player1 = new JTextField(m_oViewConfig.configNameOne, 10);
        final JTextField player2 = new JTextField(m_oViewConfig.configNameTwo, 10);

        Container content = m_soInfoFrame.getContentPane();
        SpringLayout layout = new SpringLayout();
        content.setLayout(layout);

        Dimension s1 = player1.getPreferredSize();
        Dimension s2 = player2.getPreferredSize();

        player1.setBounds(100, 20 , s1.width, s1.height);
        player2.setBounds(100, 50, s2.width, s2.height);

        content.add(p1);
        content.add(player1);
        content.add(p2);
        content.add(player2);


        SpringLayout.Constraints  labelCons = layout.getConstraints(p1);
        labelCons.setX(Spring.constant(5));
        labelCons.setY(Spring.constant(10));

        SpringLayout.Constraints textFieldCons = layout.getConstraints(player1);
        textFieldCons.setX(Spring.sum(Spring.constant(5),labelCons.getConstraint(SpringLayout.EAST)));
        textFieldCons.setY(Spring.constant(10));

        SpringLayout.Constraints  labelCon = layout.getConstraints(p2);
        labelCon.setX(Spring.constant(5));
        labelCon.setY(Spring.constant(50));

        SpringLayout.Constraints textFieldCon = layout.getConstraints(player2);
        textFieldCon.setX(Spring.sum(Spring.constant(5),labelCons.getConstraint(SpringLayout.EAST)));
        textFieldCon.setY(Spring.constant(50));

        JButton submit = new JButton(m_oViewConfig.configButtonStart);
        content.add(submit);

        SpringLayout.Constraints buttonCon = layout.getConstraints(submit);
        buttonCon.setX(Spring.sum(Spring.constant(10),labelCons.getConstraint(SpringLayout.EAST)));
        buttonCon.setY(Spring.constant(90));

        submit.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent e)
            {
                m_NamePlayer1 = player1.getText();
                m_NamePlayer2 = player2.getText();
                m_soInfoFrame.dispose();
                setupScoreBoard();
                setupMainFrame();
            }
        });

        m_soInfoFrame.setSize(250, 250);
        m_soInfoFrame.setLocation(560,0);
        m_soInfoFrame.setVisible(true);
    }

    private void setupScoreBoard()
    {
        String textOne = ""+m_iScorePlayer1;
        String textTwo = ""+m_iScorePlayer2;

        if(m_soScoreBoardFrame!=null)
        {
            ((JLabel)(m_soScoreBoardFrame.getContentPane().getComponent(2))).setText(textOne);
            ((JLabel)(m_soScoreBoardFrame.getContentPane().getComponent(3))).setText(textTwo);
        }
        else
        {

        m_soScoreBoardFrame = new JFrame(m_oViewConfig.configScoreBoardName);
        m_soScoreBoardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel p1 = new JLabel(m_NamePlayer1);
        JLabel p2 = new JLabel(m_NamePlayer2);

        JLabel p1s = new JLabel(textOne);
        JLabel p2s = new JLabel(textTwo);

        Container content = m_soScoreBoardFrame.getContentPane();
        SpringLayout layout = new SpringLayout();
        content.setLayout(layout);

        content.add(p1);
        content.add(p2);
        content.add(p1s);
        content.add(p2s);

        SpringLayout.Constraints labelCons = layout.getConstraints(p1);
        labelCons.setX(Spring.constant(5));
        labelCons.setY(Spring.constant(25));

        SpringLayout.Constraints labelCon = layout.getConstraints(p2);
        labelCon.setX(Spring.constant(5));
        labelCon.setY(Spring.constant(75));

        SpringLayout.Constraints scoreCons = layout.getConstraints(p1s);
        scoreCons.setX(Spring.constant(50));
        scoreCons.setY(Spring.constant(50));

        SpringLayout.Constraints scoreCon = layout.getConstraints(p2s);
        scoreCon.setX(Spring.constant(50));
        scoreCon.setY(Spring.constant(100));

        m_soScoreBoardFrame.setSize(250, 250);
        m_soScoreBoardFrame.setLocation(560, 0);
        m_soScoreBoardFrame.setVisible(true);

        }
    }

    @Override
    public void updateScore(Player currentPlayer)
    {
        cleanMoves();

        if(currentPlayer.getIsFirst())
        {
            m_iScorePlayer1++;
        }
        else
        {
            m_iScorePlayer2++;
        }

        setupScoreBoard();
    }

    private void initializeBoard()
    {
        m_oImagePawns      = new ImagesPawns();
        m_oImagePawns.init(m_oViewConfig.configPieces);
        m_bPause           = false;
    }


    private void setupMainFrame()
    {
        m_soMainFrame = new JFrame(m_oViewConfig.configTitle);
        m_soMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_soMainFrame.add(this);
        m_soMainFrame.setSize(550, 550);
        m_soMainFrame.setJMenuBar(buildMenuBar());
        m_soMainFrame.addMouseListener(m_oMouseListener);
        m_soMainFrame.setVisible(true);
    }

    private JMenuBar buildMenuBar()
    {
        JMenuBar menuBar;
        JMenu menu;

        menuBar = new JMenuBar();

        menu = new JMenu(m_oViewConfig.configMenuBoard);
        JMenuItem menuitem = new JMenuItem(m_oViewConfig.configMenuSettings);

        menuitem = new JMenuItem(m_oViewConfig.configMenuNew);
        menuitem.setActionCommand(BoardCommands.NEW.toString());
        menuitem.addActionListener(m_oActionListener);
        menu.add(menuitem);

        menuitem = new JMenuItem(m_oViewConfig.configMenuRestart);
        menuitem.setActionCommand(BoardCommands.RESTART.toString());
        menuitem.addActionListener(m_oActionListener);
        menu.add(menuitem);

        JCheckBoxMenuItem cbMenuItem = new JCheckBoxMenuItem(m_oViewConfig.configMenuPause);
        cbMenuItem.setActionCommand(BoardCommands.PAUSE.toString());
        cbMenuItem.addActionListener(m_oActionListener);
        menu.add(cbMenuItem);

        menuitem = new JMenuItem(m_oViewConfig.configMenuQuit);
        menuitem.setActionCommand(BoardCommands.QUIT.toString());
        menuitem.addActionListener(m_oActionListener);
        menu.add(menuitem);

        menuBar.add(menu);

        menu = new JMenu(m_oViewConfig.configMenuEdit);
        menuitem = new JMenuItem(m_oViewConfig.configMenuSettings);

        menuitem = new JMenuItem(m_oViewConfig.configMenuUndo);
        menuitem.setActionCommand(BoardCommands.UNDO.toString());
        menuitem.addActionListener(m_oActionListener);
        menu.add(menuitem);

        menuBar.add(menu);

        return menuBar;
    }

    @Override
    public void paint(Graphics g)
    {
        m_gGraphics = g;
        paint();
        repaint();
    }

    @Override
    public void paint()
    {
        boardBackground();
        highlightMoves();
        displayPieces();
        GameOver();
    }

    private void boardBackground()
    {
        m_iXDimension=( getWidth() / m_oController.getWidth());
        m_iYDimension=( getHeight()/ m_oController.getHeight());

        int count = 0;

        for(int i=0; i< m_oController.getWidth();i++)
        {
            for (int j=0; j<m_oController.getHeight(); j++)
            {
                if(count%2==0)
                {
                    m_gGraphics.setColor(new Color(255,204,153));
                }
                else
                {
                    m_gGraphics.setColor(new Color(153,76,0));
                }
                m_gGraphics.fillRect(i * m_iXDimension, j * m_iYDimension, m_iXDimension, m_iYDimension);
                count++;
            }
            count++;
        }
        m_gGraphics.setColor(Color.yellow);
        m_gGraphics.drawRect(m_iXPos * m_iXDimension, m_iYPos * m_iYDimension,m_iXDimension, m_iYDimension);
    }

    private void displayPieces()
    {
        Player p =  m_oController.getPlayerOne();

        getPieces(p, PieceType.PAWN);
        getPieces(p, PieceType.TOWER);
        getPieces(p, PieceType.BISHOP);
        getPieces(p, PieceType.HORSE);
        getPieces(p, PieceType.KING);
        getPieces(p, PieceType.QUEEN);

        p =  m_oController.getPlayerTwo();

        getPieces(p, PieceType.PAWN);
        getPieces(p, PieceType.TOWER);
        getPieces(p, PieceType.BISHOP);
        getPieces(p, PieceType.HORSE);
        getPieces(p, PieceType.KING);
        getPieces(p, PieceType.QUEEN);


        if(m_bPause)
        {
            m_gGraphics.setColor( new Color(255, 55, 0) );
            Font p3 = new Font(m_oViewConfig.fontName,Font.PLAIN,100);
            m_gGraphics.setFont(p3);
            m_gGraphics.drawString(m_oViewConfig.pauseName, getWidth()/3 - 100,getHeight()/2);
        }

    }

    private void getPieces(Player player, PieceType pieceType)
    {
        int numElements = 0;
        Image img;
        switch (pieceType) {
            case PAWN:
                numElements = ChessBoardPieces.PAWN_PIECES;
                if(player.getIsFirst())
                   img = m_oImagePawns.getWhitePawn();
                else
                   img = m_oImagePawns.getBlackPawn();
                break;
            case TOWER:
                numElements = ChessBoardPieces.CASTLE_PIECES;
                if(player.getIsFirst())
                    img = m_oImagePawns.getWhiteCastle();
                else
                    img = m_oImagePawns.getBlackCastle();
                break;
            case HORSE:
                numElements = ChessBoardPieces.HORSE_PIECES;
                if(player.getIsFirst())
                    img = m_oImagePawns.getWhiteHorse();
                else
                    img = m_oImagePawns.getBlackHorse();
                break;
            case BISHOP:
                numElements = ChessBoardPieces.BISHOP_PIECES;
                if(player.getIsFirst())
                    img = m_oImagePawns.getWhiteBishop();
                else
                    img = m_oImagePawns.getBlackBishop();
                break;
            case QUEEN:
                numElements = ChessBoardPieces.QUEEN_PIECES;
                if(player.getIsFirst())
                    img = m_oImagePawns.getWhiteQueen();
                else
                    img = m_oImagePawns.getBlackQueen();
                break;
            case KING:
                numElements = ChessBoardPieces.KING_PIECES;
                if(player.getIsFirst())
                    img = m_oImagePawns.getWhiteKing();
                else
                    img = m_oImagePawns.getBlackKing();
                break;
            default :
                 img = null;
                 m_sologger.debug("Invalid Image Selection");
                break;
        }

        for(int index = 0; index < numElements; index++)
        {

            Piece piece = m_oController.getPieces(player, pieceType, index);

            if(piece != null && !piece.getIsEaten())
            {
                m_gGraphics.drawImage(img, m_iXDimension*piece.getPosition().getX(), m_iYDimension*piece.getPosition().getY(), null);
            }
        }

    }
    @Override
    public void cleanMoves()
	{
        this.m_vMoves = null;
	}
    @Override
    public void addMoves(Vector<Position> moves)
    {
        this.m_vMoves = moves;
    }

    private void highlightMoves()
    {
        if(m_vMoves != null)
        {
            for(int i = 0; i < m_vMoves.size(); i++)
            {
                Position p = m_vMoves.elementAt(i);
                m_gGraphics.setColor(Color.yellow);
                m_gGraphics.fillRect(p.getX() * m_iXDimension, p.getY() * m_iYDimension, m_iXDimension, m_iYDimension);
            }
        }
    }

    //no more draw and pause
    //automatically new game after victory
    private void GameOver()
    {
        Player player = m_oController.gameOver();

        if(player !=null && player.getIsFirst())
        {
           // drawVictory(m_NamePlayer1);
           // m_bPause = true;
        }
        else if(player != null)
        {
          //  drawVictory(m_NamePlayer2);
          //  m_bPause = true;
        }
    }
    //Last mod:removed draw
    /*
    private void drawVictory(String name)
    {
        m_gGraphics.setColor(Color.GREEN);
        Font p3= new Font(m_oViewConfig.fontName,Font.PLAIN,50);
        m_gGraphics.setFont(p3);
        m_gGraphics.drawString(name +" Wins!", getWidth() / 3 - 100, getHeight() / 3);
        m_sologger.debug(name +" Wins!");
    }
    */
}

