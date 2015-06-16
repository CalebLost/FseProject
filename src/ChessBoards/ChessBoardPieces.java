package ChessBoards;

import Pieces.*;

public class ChessBoardPieces
{

    public static final int PAWN_PIECES    = 8;
    public static final int CASTLE_PIECES  = 2;
    public static final int HORSE_PIECES   = 2;
    public static final int BISHOP_PIECES  = 2;
    public static final int KING_PIECES    = 1;
    public static final int QUEEN_PIECES   = 1;

    private Pieces.Pawn[] m_oPawns = null;
    private Tower[] m_oTowers = null;
    private Pieces.Horse[] m_oHorses = null;
    private Pieces.Bishop[] m_oBishops = null;
    private Pieces.King[] m_oKings = null;
    private Pieces.Queen[] m_oQueens = null;

    public ChessBoardPieces()
    {
        m_oPawns = new Pieces.Pawn[PAWN_PIECES];
        m_oTowers = new Tower[CASTLE_PIECES];
        m_oHorses = new Pieces.Horse[HORSE_PIECES];
        m_oBishops = new Pieces.Bishop[BISHOP_PIECES];
        m_oKings = new Pieces.King[KING_PIECES];
        m_oQueens = new Pieces.Queen[QUEEN_PIECES];

    }


    public Pawn[] getPawns()
    {
        return m_oPawns;
    }

    public Tower[] getCastles()
    {
        return m_oTowers;
    }

    public Horse[] getHorses()
    {
        return m_oHorses;
    }

    public Bishop[] getBishops()
    {
        return m_oBishops;
    }

    public King[] getKings()
    {
        return m_oKings;
    }

    public Queen[] getQueens()
    {
        return m_oQueens;
    }

    public Queen getQueen(int index)
    {
    return m_oQueens[index];
    }

    public void setQueen(int index,Queen queen)
    {
        m_oQueens[index] = queen;
    }

    public King getKing(int index)
    {
        return m_oKings[index];
    }

    public void setKing(int index,King king)
    {
        m_oKings[index] = king;
    }

    public Pawn getPawn(int index)
    {
    return m_oPawns[index];
    }

    public void setPawn(int index,Pawn pawn)
    {
        m_oPawns[index] = pawn;
    }

    public Horse getHorse(int index)
    {
        return m_oHorses[index];
    }

    public void setHorse(int index,Horse horse)
    {
        m_oHorses[index] = horse;
    }

    public Tower getCastle(int index)
    {
    return this.m_oTowers[index];
    }

    public void setCastle(int index,Tower tower)
    {
        m_oTowers[index] = tower;
    }
    public Bishop getBishop(int index)
    {
        return m_oBishops[index];
    }

    public void setBishop(int index, Bishop bishop)
    {
        m_oBishops[index] = bishop;
    }
}