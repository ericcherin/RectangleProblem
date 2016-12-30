import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LineTest {

    Line l1;
    Line l2;
    Line l3;

    Line verticalLine = new Line(new Point(0,0), new Point(0,5));
    Line horizontalLine = new Line(new Point(0,1), new Point(5,1));
    Line verticalLine2 = new Line(new Point(2,-1), new Point(2,3));
    Line horizontalLine2 = new Line(new Point(0,8), new Point(6,8));

    Line positiveLine1 = new Line(new Point(1,0), new Point(5,2));
    Line negatveLine1 = new Line(new Point(1,4), new Point(4,1));
    @Before
    public void setUp() throws Exception {
        l1 = new Line(new Point(2,5), new Point(6,7));
        l2 = new Line(new Point(6,7), new Point(0,2));
        l3 = new Line(new Point(0,0), new Point(6,6));
    }

    double delta = .00000001;
    @org.junit.Test
    public void testCalculateSlope() throws Exception {

        assertEquals( 1, l1.calculateSlope(new Point(1,2), new Point(3,4)), delta);
        assertEquals( 11.0/3 , l1.calculateSlope(new Point(1,2), new Point(-2,-9)), delta);
        assertEquals(0, l1.calculateSlope(new Point(0,0), new Point(3,0)), delta);
    }

    @Test
    public void testContainsPoint() throws Exception {
        assertTrue(l3.containsPoint(new Point(3,3)));
        assertTrue(l1.containsPoint(new Point(4,6)));
        assertTrue(horizontalLine.containsPoint(new Point(2,1)));
        assertFalse(horizontalLine.containsPoint(new Point(12,1)));
        assertTrue(verticalLine.containsPoint(new Point(0,3)));
        assertFalse(verticalLine.containsPoint(new Point(0,9)));

        assertTrue(new Line(new Point(6,0), new Point(0,0)).containsPoint(new Point(2,0)));
        assertTrue(new Line(new Point(2,-1), new Point(2,2)).containsPoint(new Point(2,0)));

        assertTrue(negatveLine1.containsPoint(new Point(11.0/3, 4.0/3)));
        assertTrue(positiveLine1.containsPoint(new Point(11.0/3, 4.0/3)));


    }

    @Test
    public void testContainsPointSpecial() throws Exception{
        assertTrue(new Line(new Point(1,0), new Point(0,2)).containsPoint(new Point(1,0)));
    }

    @Test
    public void testIntersectionCase1() throws Exception{
        Point p =new Point(11.0/3, 4.0/3);
        assertEquals( p, positiveLine1.getIntersection(negatveLine1));
        assertTrue(negatveLine1.containsPoint(p));
        assertTrue(positiveLine1.containsPoint(p));
    }
    @Test
    public void testIsOverlapping() throws  Exception{
        assertTrue(new Line(new Point(0,0), new Point(3,0)).isOverlapping(new Line(new Point(2,0), new Point(5,0))));
        assertFalse(new Line(new Point(0,0), new Point(3,0)).isOverlapping(new Line(new Point(8,0), new Point(12,0))));
        assertTrue(new Line(new Point(0,0), new Point(6,0)).isOverlapping(new Line(new Point(2,0), new Point(1,0))));
        assertTrue(new Line(new Point(2,0), new Point(1,0)).isOverlapping(new Line(new Point(6,0), new Point(0,0))));
    }

    @Test
    public void testCalculateXIntercept() throws Exception {
        assertEquals(4,l1.getxIntercept(), delta);
    }

    @Test
    public void testGetIntersection() throws Exception {
        Line l4 = new Line(new Point(0,3),new Point(2,7));
        Line l5 = new Line(new Point(0,1), new Point(1,4));
        assertEquals(new Point(2,7), l4.getIntersection(l5));

        assertEquals(new Point(0,1), verticalLine.getIntersection(horizontalLine));
        assertEquals(new Point(0,1), horizontalLine.getIntersection(verticalLine));

        assertEquals(new Point(0,1), horizontalLine.getIntersection(l5));
        assertEquals(new Point(2,7), l4.getIntersection(l5));
        assertEquals(new Point(0,3), verticalLine.getIntersection(l4));
    }

    @Test
    public void testEquals() throws Exception{
        assertEquals(new Line(new Point(0,0), new Point(3,0)), new Line(new Point(4,0), new Point(7,0)));
        assertNotEquals(new Line(new Point(0,0), new Point(3,0)), new Line(new Point(4,1),new Point(7,1)));
    }
}