import org.junit.Test;
import static org.junit.Assert.*;
public class VectorTest {
    double delta = .00000001;
    @Test
    public void testCrossProduct() throws Exception {
        Vector u = new Vector(new Point(0,0), new Point(2,3));
        Vector v = new Vector(new Point(0,0) , new Point(4, 6));
        assertEquals(u.dotProduct(v),26,delta);
    }

    @Test
    public void testInitialize() throws Exception{
        Vector u = new Vector(new Point(3,7), new Point(11,13));
        assertEquals(u.getX(),8,delta);
        assertEquals(u.getY(),6,delta);
    }
}