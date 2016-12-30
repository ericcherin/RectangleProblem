import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RectangleTest {

    Rectangle r1;
    Rectangle r2;
    Rectangle r3;
    Rectangle r4;
    Rectangle r5;

    @Before
    public void setUp() throws Exception {
        r1 = new Rectangle(new Point(0,0), new Point(0,8), new Point(6,8), new Point(6,0));
        r2 = new Rectangle(new Point(2,2), new Point(2,4), new Point(4,4), new Point(4,2));
        r3 = new Rectangle(new Point(1,0), new Point(0,2), new Point(4,4), new Point(5,2));
        r4 = new Rectangle(new Point(4,1), new Point(1,4), new Point(4,7), new Point(7,4));
        r5 = new Rectangle(new Point(0,0), new Point(7,8));
    }

    @Test
    public void testPointInside() throws Exception {
        assertTrue(r1.containsPoint(new Point(1, 1)));
        assertFalse(r1.containsPoint(new Point(-1, 1)));
        assertTrue(r1.containsPoint(new Point(6, 8)));
        assertTrue(r4.containsPoint(new Point(6, 4)));
    }

    @Test
    public void testToString() throws Exception{
        assertEquals(r1.toString(),"[ ( 0.0, 0.0 ) -> ( 0.0, 8.0 ) ][ ( 0.0, 8.0 ) -> ( 6.0, 8.0 ) ][ ( 6.0, 8.0 ) -> ( 6.0, 0.0 ) ][ ( 6.0, 0.0 ) -> ( 0.0, 0.0 ) ]");
    }

    @Test
    public void testTwoPointInitializer() throws Exception{
        assertEquals(r1.toString(), new Rectangle(new Point(0,0), new Point(6,8)).toString());
    }
    @Test
    public void testContainsRectangle() throws Exception {
        assertTrue(r1.containsRectangle(r2));
        assertFalse(r2.containsRectangle(r4));
        assertFalse(r1.containsRectangle(r4));
        assertTrue(r1.containsRectangle(r1));
    }

    @Test
    public void testIsAdjacent() throws Exception{
        assertTrue(r1.isAdjacent(new Rectangle(new Point(1,0), new Point(2,2))));
        assertFalse(r1.isAdjacent(new Rectangle(new Point(6,10), new Point(12,23))));
        assertTrue(r1.isAdjacent(new Rectangle(new Point(6,8), new Point(12,23))));
        assertTrue(r1.isAdjacent(r1));
        assertTrue(r1.isAdjacent(new Rectangle(new Point(-2,0), new Point(3,4))));
        assertTrue(r1.isAdjacent(new Rectangle(new Point(6,2), new Point(6,23))));
        assertTrue(r1.isAdjacent(new Rectangle(new Point(6,2), new Point(6,-23))));
        assertTrue(r1.isAdjacent(new Rectangle(new Point(6,4), new Point(6,5))));
    }

    public boolean intersectionHelper(Rectangle r1, Rectangle r2, Point... expected){
        List<Point> points = Arrays.asList(expected);
        List<Point> result = r1.getIntersectionPoints(r2);
        return points.containsAll(result) && result.containsAll(points);
    }

    @Test
    public void testGetIntersectionPoints() throws Exception{

        assertTrue(intersectionHelper(r1, new Rectangle(new Point(2,-1), new Point(4,2)), new Point(2,0), new Point(4,0)));
        assertTrue(intersectionHelper(r1,r2));
        assertTrue(intersectionHelper(r1,r4,new Point(6,3), new Point(6,5)));
        assertTrue(intersectionHelper(r1,r4,new Point(6,3), new Point(6,5)));
        assertTrue(intersectionHelper(r1,r3,new Point(1,0), new Point(0,2)));
        assertTrue(intersectionHelper(r3,r4, new Point(5,2), new Point(2,3), new Point(11.0/3, 4.0/3)));
        assertTrue(intersectionHelper(r3,r2, new Point(2,3), new Point(4,4)));
        assertTrue(intersectionHelper(r4,r2, new Point(3,2), new Point(2,3)));
        assertTrue(intersectionHelper(r1,r5, new Point(0,8), new Point(0,0), new Point(6,8), new Point(6,0)));
    }
}