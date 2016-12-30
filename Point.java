public class Point {
    public final double x;
    public final double y;

    Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof Point)){
            return false;
        }
        Point other = (Point)object;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode(){
        int hash = Double.valueOf(x).hashCode();
        hash = hash * 31 + Double.valueOf(y).hashCode();
        return hash;
    }

    @Override
    public String toString(){
        return "( " + x +", " + y + " )";
    }


}
