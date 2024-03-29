package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.exception.GridException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the <code>UltimateTicTacToe</code> class.
 *
 * @author Logan Farci (47923)
 */
public class UltimateTicTacToeTest {

    /**
     * The <code>UltimateTicTacToe</code> is constructed as expected.
     */
    @Test
    public void construction() {
        Grid m = new UltimateTicTacToe();
        assertFalse(m.hasOwner());
        assertFalse(m.hasFullColumnOwnedBy(Player.O));
        assertFalse(m.hasFullColumnOwnedBy(Player.X));
        assertFalse(m.hasFullRowOwnedBy(Player.O));
        assertFalse(m.hasFullRowOwnedBy(Player.X));
        assertFalse(m.hasFullDiagonalOwnedBy(Player.O));
        assertFalse(m.hasFullDiagonalOwnedBy(Player.X));
    }

    /**
     * A <code>UltimateTicTacToe</code> is initialized as expected.
     */
    @Test
    public void initialization() {
        Grid u = new UltimateTicTacToe();
        int current = 0;
        for (int row = 0; row < MiniTicTacToe.SIZE; row++) {
            for (int column = 0; column < MiniTicTacToe.SIZE; column++) {
                Grid grid = (Grid) u.getCells().get(current);
                assertFalse(grid.hasOwner());
                assertEquals(new Position(row, column), grid.getPosition());
                current++;
            }
        }
        assertEquals(9, u.getCells().size());
    }

    /**
     * Trying to get a <code>MiniTicTacToe</code> cell at a given position
     * should get the expected one.
     */
    @Test
    public void getCellAt() {
        Grid u = new UltimateTicTacToe();
        assertEquals(new Position(), u.getCellAt(new Position()).getPosition());
        assertEquals(new Position(2, 2), u.getCellAt(new Position(2, 2)).getPosition());
        assertEquals(new Position(2, 1), u.getCellAt(new Position(2, 1)).getPosition());
        assertEquals(new Position(0, 1), u.getCellAt(new Position(0, 1)).getPosition());
    }

    /**
     * Trying to get a cell at a null position causes an exception.
     */
    @Test(expected = NullPointerException.class)
    public void getCellAt_nullPosition() {
        Grid u = new UltimateTicTacToe();
        u.getCellAt(null);
    }

    /**
     * A UltimateTicTacToe should be full when all of its MiniTicTacToe are
     * owned and should not have a owner if no full row, column or diagonal is
     * owned.
     */
    @Test
    public void isFull_allCellsOwned_noOwner() {
        UltimateTicTacToe u = new UltimateTicTacToe();
        for (int row = 0; row < UltimateTicTacToe.SIZE; row++) {
            for (int column = 0; column < UltimateTicTacToe.SIZE; column++) {
                Position current = new Position(row, column);
                u.setOwnerAt(Player.O, current, new Position(0, 0));
                u.setOwnerAt(Player.X, current, new Position(0, 1));
                u.setOwnerAt(Player.O, current, new Position(0, 2));
                u.setOwnerAt(Player.X, current, new Position(1, 0));
                u.setOwnerAt(Player.O, current, new Position(1, 1));
                u.setOwnerAt(Player.X, current, new Position(1, 2));
                u.setOwnerAt(Player.X, current, new Position(2, 0));
                u.setOwnerAt(Player.O, current, new Position(2, 1));
                u.setOwnerAt(Player.X, current, new Position(2, 2));
            }
        }
        assertTrue(u.isFull());
        assertFalse(u.isOwnedBy(Player.O));
        assertFalse(u.isOwnedBy(Player.X));
        assertFalse(u.hasOwner());
    }

    /**
     * A UltimateTicTacToe should be full when all of its cells are owned.
     */
    @Test
    public void isFull_allCellsOwned() {
        UltimateTicTacToe u = new UltimateTicTacToe();
        u.fillWith(Player.O);
        assertTrue(u.isFull());
    }

    /**
     * Setting an null owner causes an exception.
     */
    @Test(expected = NullPointerException.class)
    public void setOwnerAt_nullOwner() {
        UltimateTicTacToe u = new UltimateTicTacToe();
        u.setOwnerAt(null, new Position(), new Position());
    }

    /**
     * Setting an owner at a null <code>MiniTicTacToe</code> position causes an
     * exception.
     */
    @Test(expected = NullPointerException.class)
    public void setOwnerAt_nullMiniTicTacToePosition() {
        UltimateTicTacToe u = new UltimateTicTacToe();
        u.setOwnerAt(Player.O, null, new Position());
    }

    /**
     * Setting an owner at a null <code>Cell</code> position causes an
     * exception.
     */
    @Test(expected = NullPointerException.class)
    public void setOwnerAt_nullCellPosition() {
        UltimateTicTacToe u = new UltimateTicTacToe();
        u.setOwnerAt(Player.O, new Position(), null);
    }

    /**
     * Setting an owner at an owned <code>MiniTicTacToe</code> position causes
     * an exception.
     */
    @Test(expected = GridException.class)
    public void setOwnerAt_miniTicTacToeIsOwned() {
        UltimateTicTacToe u = new UltimateTicTacToe();
        u.getCellAt(new Position(0, 0)).setOwner(Player.X);
        u.setOwnerAt(Player.O, new Position(0, 0), new Position(1, 1));
    }

    /**
     * Setting an owner at a full <code>MiniTicTacToe</code> position causes
     * an exception.
     */
    @Test(expected = GridException.class)
    public void setOwnerAt_miniTicTacToeIsFull() {
        UltimateTicTacToe u = new UltimateTicTacToe();
        u.getCellAt(new Position(0, 0)).fillWith(Player.X);
        u.setOwnerAt(Player.O, new Position(0, 0), new Position(1, 1));
    }

    /**
     * Setting an owner should set the owner in the specified <code>Cell</code>
     * of the specified <code>MiniTicTacToe</code>.
     */
    @Test
    public void setOwnerAt_noErrors() {
        UltimateTicTacToe u = new UltimateTicTacToe();
        Position m = new Position(1, 0);
        Position c = new Position(1, 2);
        Player owner = Player.O;
        u.setOwnerAt(owner, m, c);
        assertEquals(owner, u.getCellAt(m).getCellAt(c).getOwner());
    }

    /**
     * Calling hasFullRowOwnedBy should return false for both players when the
     * grid is empty.
     */
    @Test
    public void hasFullRowOwnedBy_emptyGrid() {
        Grid u = new UltimateTicTacToe();
        assertFalse(u.hasFullRowOwnedBy(Player.O));
        assertFalse(u.hasFullRowOwnedBy(Player.X));
    }

    /**
     * Calling hasFullRowOwnedBy should return true if a player owns a full row.
     */
    @Test
    public void hasFullRowOwnedBy_aFullRowIsOwned() {
        UltimateTicTacToe u = new UltimateTicTacToe();
        for (int column = 0; column < UltimateTicTacToe.SIZE; column++) {
            Position mini = new Position(1, column);
            u.setOwnerAt(Player.O, mini, new Position(1, 0));
            u.setOwnerAt(Player.O, mini, new Position(1, 1));
            u.setOwnerAt(Player.O, mini, new Position(1, 2));
        }
        assertTrue(u.hasFullRowOwnedBy(Player.O));
    }

    /**
     * Calling hasFullColumnOwnedBy should return false for both players when
     * the grid is empty.
     */
    @Test
    public void hasFullColumnOwnedBy_emptyGrid() {
        Grid m = new UltimateTicTacToe();
        assertFalse(m.hasFullRowOwnedBy(Player.O));
        assertFalse(m.hasFullRowOwnedBy(Player.X));
    }

    /**
     * Calling hasFullRowOwnedBy should return true if a player owns a full row.
     */
    @Test
    public void hasFullRowOwnedBy_aFullColumnIsOwned() {
        UltimateTicTacToe u = new UltimateTicTacToe();
        for (int row = 0; row < UltimateTicTacToe.SIZE; row++) {
            Position mini = new Position(row, 1);
            u.setOwnerAt(Player.O, mini, new Position(1, 0));
            u.setOwnerAt(Player.O, mini, new Position(1, 1));
            u.setOwnerAt(Player.O, mini, new Position(1, 2));
        }
        assertTrue(u.hasFullColumnOwnedBy(Player.O));
    }

    /**
     * Calling hasFullDiagonalOwnedBy should return false for both players when
     * the grid is empty
     */
    @Test
    public void hasFullDiagonalOwnedBy_emptyGrid() {
        Position position = new Position();
        Grid m = new MiniTicTacToe(position);
        assertFalse(m.hasFullDiagonalOwnedBy(Player.O));
        assertFalse(m.hasFullDiagonalOwnedBy(Player.X));
    }

    /**
     * Calling hasFullDiagonalOwnedBy should return true if a player owns the
     * ascending diagonal.
     */
    @Test
    public void hasFullDiagonalOwnedBy_aFullAscendingDiagonalIsOwned() {
        UltimateTicTacToe u = new UltimateTicTacToe();
        for (int row = 0, column = 0; row < UltimateTicTacToe.SIZE; row++, column++) {
            Position mini = new Position(row, column);
            u.setOwnerAt(Player.O, mini, new Position(1, 0));
            u.setOwnerAt(Player.O, mini, new Position(1, 1));
            u.setOwnerAt(Player.O, mini, new Position(1, 2));
        }
        assertTrue(u.hasFullDiagonalOwnedBy(Player.O));
    }

    /**
     * Calling hasFullDiagonalOwnedBy should return true if a player owns the
     * descending diagonal.
     */
    @Test
    public void hasFullDiagonalOwnedBy_aFullDescendingDiagonalIsOwned() {
        UltimateTicTacToe u = new UltimateTicTacToe();
        int size = UltimateTicTacToe.SIZE - 1;
        for (int row = size, column = size; row >= 0; row--, column--) {
            Position mini = new Position(row, column);
            u.setOwnerAt(Player.O, mini, new Position(1, 0));
            u.setOwnerAt(Player.O, mini, new Position(1, 1));
            u.setOwnerAt(Player.O, mini, new Position(1, 2));
        }
        assertTrue(u.hasFullDiagonalOwnedBy(Player.O));
    }

    /**
     * Calling isOwnedBy should return true if a player owns a full row.
     */
    @Test
    public void isOwnedBy() {
        UltimateTicTacToe u = new UltimateTicTacToe();
        int size = UltimateTicTacToe.SIZE - 1;
        for (int row = size, column = size; row >= 0; row--, column--) {
            Position mini = new Position(row, column);
            u.setOwnerAt(Player.O, mini, new Position(1, 0));
            u.setOwnerAt(Player.O, mini, new Position(1, 1));
            u.setOwnerAt(Player.O, mini, new Position(1, 2));
        }
        assertTrue(u.hasOwner());
        assertEquals(Player.O, u.getOwner());
        assertTrue(u.isOwnedBy(Player.O));
    }

}
