/**
 *
 * Todo: Refactor line class to have LineSegment with Parent class Line:
 * Todo: move inBounds and is Overlapping to LineSegment
 */
public class Line {
    private final Point a;
    private final Point b;
    private final double highX;
    private final double lowX;
    private final double highY;
    private final double lowY;
    private final double slope;
    private final double xIntercept;
    private boolean slopeUndefined;
    private final double DELTA = .00001;
    /**
     *
     * @param a the x value of a will be less than the x value of b
     */
    public Line(Point a, Point b){
        this.a = a;
        this.b = b;

        if(a.x < b.x){
            lowX = a.x;
            highX = b.x;
        }
        else{
            lowX = b.x;
            highX = a.x;
        }

        if(a.y < b.y){
            lowY = a.y;
            highY = b.y;
        }
        else{
            lowY = b.y;
            highY = a.y;
        }
        slopeUndefined = false;

        this.slope = calculateSlope(a,b);
        this.xIntercept = calculateXIntercept(slope,a);
    }

    public double calculateSlope(Point A, Point B){
        if(B.x - A.x == 0) {
            slopeUndefined = true;
            return 0;
        }
        return (B.y - A.y)/(B.x - A.x);
    }

    public double getSlope(){ return slope;}
    public double getxIntercept(){ return xIntercept;}

    public boolean isParallel(Line other){
        if(slopeUndefined && other.slopeUndefined ){
            return true;
        }
        if(slopeUndefined || other.slopeUndefined){
            return false;
        }
        return slope == other.getSlope();
    }

    public boolean canIntersect(Line other){
        return !isParallel(other);
    }

    public boolean inBounds(double testNumber,  double sLower, double sHigher){
        if(sLower > sHigher){
            double temp = sLower;
            sLower = sHigher;
            sHigher = temp;
        }

        return testNumber <=sHigher && testNumber >= sLower;
    }

    public boolean inBoundsX(Point p,  Line line){
        return inBounds(p.x, line.a.x, line.b.x);
    }
    public boolean inBoundsY( Point p, Line line){
        return inBounds(p.y, line.a.y, line.b.y);
    }

    public boolean containsPoint(Point p){
        if(slopeUndefined || Math.abs(p.x - a.x ) < DELTA ){
            return p.y >= lowY && p.y <= highY ;
        }
        return Math.abs(slope - calculateSlope(a,p)) < DELTA  && p.x >= lowX && p.x <=highX;
    }

    private double calculateXIntercept(double slope, Point p){
        if(slopeUndefined){
            return 0;
        }
        return p.y - slope*p.x;
    }

    public boolean isOverlapping(Line line){
        return inBoundsX(line.a, this) && inBoundsY(line.a ,this) || inBoundsX(line.b,this) && inBoundsY(line.b ,this) || inBoundsX(a,line) && inBoundsY(b ,line);
    }

    public Point getIntersection(Line other){
        if(slopeUndefined){
            return new Point(a.x, other.slope * a.x + other.xIntercept);
        }
        else if(other.slopeUndefined){
            return new Point(other.a.x, slope * other.a.x + xIntercept);
        }
        double i = (other.getxIntercept()-xIntercept)/(slope-other.getSlope());
        double j = slope*(other.getxIntercept() - xIntercept)/(slope - other.getSlope()) + xIntercept;
        return new Point(i,j);
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof Line)){
            return false;
        }
        Line other = (Line)object;
        if(other.slopeUndefined && slopeUndefined ){
            return a.x == other.a.x;
        }
        return isParallel(other) && xIntercept == other.getxIntercept();
    }

    @Override
    public int hashCode(){
        int hash = Double.valueOf(slope).hashCode();
        hash = hash*31 + Double.valueOf(xIntercept).hashCode();
        hash = hash*31 + Boolean.valueOf(slopeUndefined).hashCode();
        return hash;
    }

    @Override
    public String toString(){
        return a.toString() + " -> " + b.toString();
    }

    public String toPointSlope(){
        return  !slopeUndefined ? "y = " + slope + "x " + " + " + xIntercept : " x = " + a.x ;
    }
}
