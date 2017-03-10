package ru.job4j.chess;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test Chess.
 *
 * @author yuzyakav
 * @since 10.03.2017
 * @version 1.0
 */
public class ChessTest {

    /**
     * output for test.
     */
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    /**
     * method for setting stream.
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    /**
     * method for cleaning stream.
     */
    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    /**
     * Test for check array of figures on the board.
     */
    @Test
    public void whenSetOneArrayOfFiguresThenGetIt() {
        Board board = new Board();
        Figure firstBishop = new Bishop(new Cell(7, 2));
        Figure firstRook = new Rook(new Cell(7, 0));
        Figure secondBishop = new Bishop(new Cell(7, 5));
        Figure secondRook = new Rook(new Cell(7, 7));
        Figure[] figuresForBoard = new Figure[] {firstRook, firstBishop, secondBishop, secondRook};
        board.setFigures(figuresForBoard);
        Figure[] resultFigures = board.getFigures();
        Figure[] checkFigures = new Figure[] {firstRook, firstBishop, secondBishop, secondRook};
        assertThat(resultFigures, is(checkFigures));
    }

    /**
     * Test for check method move if source cell hasn't figure.
     */
    @Test
    public void whenUseMoveButInCellNoFigure() {
        Board board = new Board();
        Figure firstBishop = new Bishop(new Cell(7, 2));
        Figure firstRook = new Rook(new Cell(7, 0));
        Figure secondBishop = new Bishop(new Cell(7, 5));
        Figure secondRook = new Rook(new Cell(7, 7));
        Figure[] figuresForBoard = new Figure[] {firstRook, firstBishop, secondBishop, secondRook};
        board.setFigures(figuresForBoard);
        try {
            board.move(new Cell(1, 7), new Cell(2, 5));
        } catch (ImpossibleMoveException e) {
            System.out.print("Catch ImpossibleMoveException");
        } catch (FigureNotFoundException e) {
            System.out.print("Catch FigureNotFoundException");
        } catch (OccupiedWayException e) {
            System.out.print("Catch OccupiedWayException");
        }
        String checkText = "Catch FigureNotFoundException";
        assertThat(output.toString(), is(checkText));
    }

    /**
     * Test for check method move if way is occupied other figures.
     */
    @Test
    public void whenUseMoveButWayIsOccupied() {
        Board board = new Board();
        Figure firstBishop = new Bishop(new Cell(7, 2));
        Figure firstRook = new Rook(new Cell(5, 4));
        Figure secondBishop = new Bishop(new Cell(7, 5));
        Figure secondRook = new Rook(new Cell(7, 7));
        Figure[] figuresForBoard = new Figure[] {firstRook, firstBishop, secondBishop, secondRook};
        board.setFigures(figuresForBoard);
        try {
            board.move(new Cell(7, 2), new Cell(3, 6));
        } catch (ImpossibleMoveException e) {
            System.out.print("Catch ImpossibleMoveException");
        } catch (FigureNotFoundException e) {
            System.out.print("Catch FigureNotFoundException");
        } catch (OccupiedWayException e) {
            System.out.print("Catch OccupiedWayException");
        }
        String checkText = "Catch OccupiedWayException";
        assertThat(output.toString(), is(checkText));
    }

    /**
     * Test for check method move if use illegal figure's way.
     */
    @Test
    public void whenUseMoveButWayOfFigureIsIllegal() {
        Board board = new Board();
        Figure firstBishop = new Bishop(new Cell(7, 2));
        Figure firstRook = new Rook(new Cell(5, 4));
        Figure secondBishop = new Bishop(new Cell(7, 5));
        Figure secondRook = new Rook(new Cell(7, 7));
        Figure[] figuresForBoard = new Figure[] {firstRook, firstBishop, secondBishop, secondRook};
        board.setFigures(figuresForBoard);
        try {
            board.move(new Cell(7, 2), new Cell(6, 2));
        } catch (ImpossibleMoveException e) {
            System.out.print("Catch ImpossibleMoveException");
        } catch (FigureNotFoundException e) {
            System.out.print("Catch FigureNotFoundException");
        } catch (OccupiedWayException e) {
            System.out.print("Catch OccupiedWayException");
        }
        String checkText = "Catch ImpossibleMoveException";
        assertThat(output.toString(), is(checkText));
    }

    /**
     * Test for check method move if use legal figure's way.
     */
    @Test
    public void whenUseMoveForLegalWayThenOk() {
        Board board = new Board();
        Figure firstBishop = new Bishop(new Cell(7, 2));
        Figure firstRook = new Rook(new Cell(7, 4));
        Figure secondBishop = new Bishop(new Cell(7, 5));
        Figure secondRook = new Rook(new Cell(7, 7));
        Figure[] figuresForBoard = new Figure[] {firstRook, firstBishop, secondBishop, secondRook};
        board.setFigures(figuresForBoard);
        boolean resultWay = false;
        try {
            resultWay = board.move(new Cell(7, 2), new Cell(4, 5));
        } catch (ImpossibleMoveException e) {
            System.out.print("Catch ImpossibleMoveException");
        } catch (FigureNotFoundException e) {
            System.out.print("Catch FigureNotFoundException");
        } catch (OccupiedWayException e) {
            System.out.print("Catch OccupiedWayException");
        }
        boolean checkWay = true;
        assertThat(resultWay, is(checkWay));
    }

    /**
     * Test for check cloning in method move.
     */
    @Test
    public void whenUseMoveForLegalWayThebGetClone() {
        Board board = new Board();
        Figure firstBishop = new Bishop(new Cell(7, 2));
        Figure firstRook = new Rook(new Cell(7, 4));
        Figure secondBishop = new Bishop(new Cell(7, 5));
        Figure secondRook = new Rook(new Cell(7, 7));
        Figure[] figuresForBoard = new Figure[] {firstRook, firstBishop, secondBishop, secondRook};
        board.setFigures(figuresForBoard);
        try {
            board.move(new Cell(7, 2), new Cell(4, 5));
        } catch (ImpossibleMoveException e) {
            System.out.print("Catch ImpossibleMoveException");
        } catch (FigureNotFoundException e) {
            System.out.print("Catch FigureNotFoundException");
        } catch (OccupiedWayException e) {
            System.out.print("Catch OccupiedWayException");
        }
        //checking for class
        String resultClass = figuresForBoard[1].getClass().getName();
        assertThat(resultClass, is("ru.job4j.chess.Bishop"));

        //I haven't Getter of Cell for Figures.
        //Use checking move from source cell with bishop1 to move any cell
        //If no any exception isn't caught, bishop is cloned
        boolean resultClone = false;
        try {
            resultClone = board.move(new Cell(4, 5), new Cell(1, 2));
        } catch (ImpossibleMoveException e) {
            System.out.print("Catch ImpossibleMoveException");
        } catch (FigureNotFoundException e) {
            System.out.print("Catch FigureNotFoundException");
        } catch (OccupiedWayException e) {
            System.out.print("Catch OccupiedWayException");
        }
        boolean checkClone = true;
        assertThat(resultClone, is(checkClone));
    }

    /**
     * Test for check Rook's way to left.
     */
    @Test
    public void whenRookGoToLeft() {
        Cell src = new Cell();
        src.setNumberX(3);
        src.setNumberY(4);
        Rook rook = new Rook(src);
        Cell dist = new Cell();
        dist.setNumberX(3);
        dist.setNumberY(2);
        Cell[] checkWay = null;
        try {
            checkWay = rook.way(dist);
        } catch (Exception e) {
            System.out.println("Move right");
        }
        Cell[] resultWay = new Cell[] {new Cell(3, 3), new Cell(3, 2)};
        assertThat(resultWay, is(checkWay));
    }

    /**
     * Test for check Rook's way to right.
     */
    @Test
    public void whenRookGoToRight() {
        Cell src = new Cell();
        src.setNumberX(5);
        src.setNumberY(1);
        Rook rook = new Rook(src);
        Cell dist = new Cell();
        dist.setNumberX(5);
        dist.setNumberY(5);
        Cell[] checkWay = null;
        try {
            checkWay = rook.way(dist);
        } catch (Exception e) {
            System.out.println("Move right");
        }
        Cell[] resultWay = new Cell[] {new Cell(5, 2), new Cell(5, 3),
                new Cell(5, 4), new Cell(5, 5)};
        assertThat(resultWay, is(checkWay));
    }

    /**
     * Test for check Rook's way to up.
     */
    @Test
    public void whenRookGoToUp() {
        Cell src = new Cell();
        src.setNumberX(4);
        src.setNumberY(3);
        Rook rook = new Rook(src);
        Cell dist = new Cell();
        dist.setNumberX(1);
        dist.setNumberY(3);
        Cell[] checkWay = null;
        try {
            checkWay = rook.way(dist);
        } catch (Exception e) {
            System.out.println("Move right");
        }
        Cell[] resultWay = new Cell[] {new Cell(3, 3), new Cell(2, 3),
                new Cell(1, 3)};
        assertThat(resultWay, is(checkWay));
    }

    /**
     * Test for check Rook's way to down.
     */
    @Test
    public void whenRookGoToDown() {
        Cell src = new Cell();
        src.setNumberX(0);
        src.setNumberY(7);
        Rook rook = new Rook(src);
        Cell dist = new Cell();
        dist.setNumberX(4);
        dist.setNumberY(7);
        Cell[] checkWay = null;
        try {
            checkWay = rook.way(dist);
        } catch (Exception e) {
            System.out.println("Move right");
        }
        Cell[] resultWay = new Cell[] {new Cell(1, 7), new Cell(2, 7),
                new Cell(3, 7), new Cell(4, 7)};
        assertThat(resultWay, is(checkWay));
    }

    /**
     * Test for check wrong Rook's way.
     */
    @Test
    public void whenRookGoToWrongWay() {
        Cell src = new Cell();
        src.setNumberX(5);
        src.setNumberY(4);
        Rook rook = new Rook(src);
        Cell dist = new Cell();
        dist.setNumberX(4);
        dist.setNumberY(6);
        Cell[] checkWay = null;
        try {
            checkWay = rook.way(dist);
        } catch (Exception e) {
            System.out.print("Move right");
        }
        assertThat(output.toString(), is("Move right"));
    }

    /**
     * Test for check Bishop's way to UpLeft.
     */
    @Test
    public void whenBishopGoToUpLeft() {
        Cell src = new Cell();
        src.setNumberX(3);
        src.setNumberY(4);
        Bishop bishop = new Bishop(src);
        Cell dist = new Cell();
        dist.setNumberX(0);
        dist.setNumberY(1);
        Cell[] checkWay = null;
        try {
            checkWay = bishop.way(dist);
        } catch (Exception e) {
            System.out.println("Move right");
        }
        Cell[] resultWay = new Cell[] {new Cell(2, 3), new Cell(1, 2),
                new Cell(0, 1)};
        assertThat(resultWay, is(checkWay));
    }

    /**
     * Test for check Bishop's way to UpLeft.
     */
    @Test
    public void whenBishopGoToUpRight() {
        Cell src = new Cell();
        src.setNumberX(4);
        src.setNumberY(1);
        Bishop bishop = new Bishop(src);
        Cell dist = new Cell();
        dist.setNumberX(1);
        dist.setNumberY(4);
        Cell[] checkWay = null;
        try {
            checkWay = bishop.way(dist);
        } catch (Exception e) {
            System.out.println("Move right");
        }
        Cell[] resultWay = new Cell[] {new Cell(3, 2), new Cell(2, 3),
                new Cell(1, 4)};
        assertThat(resultWay, is(checkWay));
    }

    /**
     * Test for check Bishop's way to UpLeft.
     */
    @Test
    public void whenBishopGoToDownLeft() {
        Cell src = new Cell();
        src.setNumberX(3);
        src.setNumberY(4);
        Bishop bishop = new Bishop(src);
        Cell dist = new Cell();
        dist.setNumberX(6);
        dist.setNumberY(1);
        Cell[] checkWay = null;
        try {
            checkWay = bishop.way(dist);
        } catch (Exception e) {
            System.out.println("Move right");
        }
        Cell[] resultWay = new Cell[] {new Cell(4, 3), new Cell(5, 2),
                new Cell(6, 1)};
        assertThat(resultWay, is(checkWay));
    }

    /**
     * Test for check Bishop's way to UpLeft.
     */
    @Test
    public void whenBishopGoToDownRight() {
        Cell src = new Cell();
        src.setNumberX(4);
        src.setNumberY(2);
        Bishop bishop = new Bishop(src);
        Cell dist = new Cell();
        dist.setNumberX(7);
        dist.setNumberY(5);
        Cell[] checkWay = null;
        try {
            checkWay = bishop.way(dist);
        } catch (Exception e) {
            System.out.println("Move right");
        }
        Cell[] resultWay = new Cell[] {new Cell(5, 3), new Cell(6, 4),
                new Cell(7, 5)};
        assertThat(resultWay, is(checkWay));
    }

    /**
     * Test for check wrong Bishop's way.
     */
    @Test
    public void whenBishopGoToWrongWay() {
        Cell src = new Cell();
        src.setNumberX(3);
        src.setNumberY(2);
        Bishop bishop = new Bishop(src);
        Cell dist = new Cell();
        dist.setNumberX(7);
        dist.setNumberY(4);
        Cell[] checkWay = null;
        try {
            checkWay = bishop.way(dist);
        } catch (Exception e) {
            System.out.print("Move right");
        }
        assertThat(output.toString(), is("Move right"));
    }
}