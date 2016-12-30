
public class Vector{
    private final Point p;

    /**
     *@param a begining of the vector
     *@param b end of the vector
     */
    public Vector(Point a, Point b){
        p = new Point(b.x - a.x, b.y - a.y);
    }

    public double getX(){ return p.x;}
    public double getY(){ return p.y;}

    public double dotProduct(Vector other){
        return p.x * other.getX() + p.y * other.getY();
    }
}
